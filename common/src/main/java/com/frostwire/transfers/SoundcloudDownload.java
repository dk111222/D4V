/*
 * Created by Angel Leon (@gubatron), Alden Torres (aldenml)
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

package com.frostwire.transfers;

import com.frostwire.mp3.ID3Wrapper;
import com.frostwire.mp3.ID3v1Tag;
import com.frostwire.mp3.ID3v23Tag;
import com.frostwire.mp3.Mp3File;
import com.frostwire.search.soundcloud.SoundcloudSearchResult;
import com.frostwire.util.Logger;

import java.io.ByteArrayOutputStream;
import java.io.File;

/**
 * @author gubatron
 * @author aldenml
 */
public class SoundcloudDownload extends HttpDownload {
    private static final Logger LOG = Logger.getLogger(SoundcloudDownload.class);
    private static final long COVERART_FETCH_THRESHOLD = 20971520; //20MB
    private final SoundcloudSearchResult sr;

    public SoundcloudDownload(SoundcloudSearchResult sr) {
        super(convert(sr));
        this.sr = sr;
    }

    private static void downloadAndUpdateCoverArt(SoundcloudSearchResult sr, File file) {
        if (file != null && file.exists() && file.length() <= COVERART_FETCH_THRESHOLD) {
            byte[] cover = downloadCoverArt(sr.getThumbnailUrl());
            if (cover != null && cover.length > 0) {
                File temp = new File(file.getAbsolutePath() + ".tmp");
                if (file.renameTo(temp)) {
                    if (setAlbumArt(sr, cover, temp.getAbsolutePath(), file.getAbsolutePath())) {
                        temp.delete();
                    } else {
                        temp.renameTo(file);
                    }
                } else {
                    LOG.warn("Error moving temporary file to stage one for cover update");
                }
            }
        }
    }

    private static byte[] downloadCoverArt(String url) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            simpleHTTP(url, baos, 3000);
            return baos.toByteArray();
        } catch (Throwable e) {
            LOG.error("Error downloading SoundCloud cover art (url=" + url + ")", e);
        }
        return null;
    }

    private static boolean setAlbumArt(SoundcloudSearchResult sr, byte[] cover, String inPath, String outPath) {
        try {
            Mp3File mp3 = new Mp3File(inPath);
            ID3Wrapper newId3Wrapper = new ID3Wrapper(new ID3v1Tag(), new ID3v23Tag());
            newId3Wrapper.setAlbum(sr.getUsername() + ": " + sr.getDisplayName() + " via SoundCloud.com");
            newId3Wrapper.setArtist(sr.getUsername());
            newId3Wrapper.setTitle(sr.getDisplayName());
            newId3Wrapper.setAlbumImage(cover, "image/jpg");
            newId3Wrapper.setUrl(sr.getDetailsUrl());
            newId3Wrapper.getId3v2Tag().setPadding(true);
            mp3.setId3v1Tag(newId3Wrapper.getId3v1Tag());
            mp3.setId3v2Tag(newId3Wrapper.getId3v2Tag());
            mp3.save(outPath);
            return true;
        } catch (Throwable e) {
            LOG.error("Error setting art information for soundcloud download", e);
            return false;
        }
    }

    private static Info convert(SoundcloudSearchResult sr) {
        return new Info(sr.getStreamUrl(), sr.getFilename(), sr.getDisplayName(), sr.getSize());
    }

    @Override
    protected void onFinishing() {
        downloadAndUpdateCoverArt(sr, tempPath);
        super.onFinishing();
    }
}
