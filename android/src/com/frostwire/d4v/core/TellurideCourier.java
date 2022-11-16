/*
 * Created by Angel Leon (@gubatron)
 * Copyright (c) 2011-2022, FrostWire(R). All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.frostwire.d4v.core;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.frostwire.d4v.gui.adapters.SearchResultListAdapter;
import com.frostwire.d4v.gui.services.Engine;
import com.frostwire.d4v.util.SystemUtils;
import com.frostwire.search.AbstractSearchPerformer;
import com.frostwire.search.CrawlableSearchResult;
import com.frostwire.search.telluride.TellurideSearchPerformer;
import com.frostwire.search.telluride.TellurideSearchResult;
import com.frostwire.util.Logger;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

public final class TellurideCourier {
    private static final Logger LOG = Logger.getLogger(TellurideCourier.class);
    private static Gson gson = null;
    private static TellurideCourierCallback lastKnownCallback = null;

    public static void abortCurrentQuery() {
        if (lastKnownCallback == null) {
            return;
        }

        lastKnownCallback.abort();
    }

    // runs on SEARCH_PERFORMER HandlerThread
    public static void queryPage(String url, TellurideCourierCallback callback) {
        if (SystemUtils.isUIThread()) {
            SystemUtils.postToHandler(SystemUtils.HandlerThreadName.SEARCH_PERFORMER, () -> queryPage(url, callback));
            return;
        }
        if (callback != null) {
            lastKnownCallback = callback;
        }
        SystemUtils.ensureBackgroundThreadOrCrash("TellurideCourier::queryPage");
        boolean error = false;
        long a = System.currentTimeMillis();
        if (!Python.isStarted()) {
            Engine.startPython();
        }
        Python python = Python.getInstance();
        long b = System.currentTimeMillis();
        long pythonInstanceFetchTime = b - a;
        if (callback != null && callback.aborted()) {
            lastKnownCallback = null;
            LOG.info("TellurideCourier::queryPage aborted by TellurideCourierCallback (stage 1)");
            return;
        }
        LOG.info("TellurideCourier::queryPage - Got Python instance in " + pythonInstanceFetchTime + " ms");
        PyObject telluride_module = python.getModule("telluride");
        if (callback != null && callback.aborted()) {
            lastKnownCallback = null;
            LOG.info("TellurideCourier::queryPage aborted by TellurideCourierCallback (stage 2)");
            return;
        }
        PyObject query_video_result = telluride_module.callAttr("query_video", url);
        if (query_video_result == null && callback != null) {
            callback.onResults(null, true);
            lastKnownCallback = null;
            return;
        }
        if (query_video_result == null) {
            return;
        }
        String json_query_video_result = query_video_result.toString();
        if (callback != null && callback.aborted()) {
            lastKnownCallback = null;
            LOG.info("TellurideCourier::queryPage aborted by TellurideCourierCallback (stage 3)");
            return;
        }

        if (gson == null) {
            gson = new GsonBuilder().create();
        }

        List<TellurideSearchResult> validResults = TellurideSearchPerformer.getValidResults(json_query_video_result, gson, null, -1, url);
        LOG.info("TellurideCourier::queryPage: TellurideSearchPerformer.getValidResults() -> " + validResults.size());

        if (callback != null && !callback.aborted()) {
            callback.onResults(validResults, error);
            lastKnownCallback = null;
        }
    }


    public static class SearchPerformer extends AbstractSearchPerformer {
        private final String pageUrl;
        private final TellurideCourierCallback courierCallback;

        public SearchPerformer(long token, String pageUrl, SearchResultListAdapter adapter) {
            super(token);
            this.pageUrl = pageUrl;
            this.courierCallback = new TellurideCourierCallback(this, pageUrl, adapter);
        }

        @Override
        public void perform() {
            SystemUtils.ensureBackgroundThreadOrCrash("TellurideCourirer.SearchPerformer.perform");
            TellurideCourier.queryPage(pageUrl, courierCallback);
        }

        @Override
        public void crawl(CrawlableSearchResult sr) {
            // nothing to crawl
        }

        @Override
        public void stop() {
            super.stop();
            courierCallback.abort();
            lastKnownCallback = null;
        }
    }
}
