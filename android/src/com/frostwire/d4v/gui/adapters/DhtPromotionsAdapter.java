/*
 * Created by Angel Leon (@gubatron), Alden Torres (aldenml)
 *            Marcelina Knitter (@marcelinkaaa)
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

package com.frostwire.d4v.gui.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.frostwire.d4v.R;
import com.frostwire.d4v.core.Constants;
import com.frostwire.d4v.gui.activities.MainActivity;
import com.frostwire.d4v.gui.util.UIUtils;
import com.frostwire.d4v.gui.views.AbstractAdapter;
import com.frostwire.d4v.offers.FWBannerView;
import com.frostwire.d4v.offers.PlayStore;
import com.frostwire.d4v.util.AppUtils;
import com.frostwire.d4v.util.ImageLoader;
import com.frostwire.frostclick.Slide;
import com.frostwire.util.StringUtils;

import java.util.List;

/**
 * Adapter in control of the List View shown when we're browsing the files of
 * one peer.
 *
 * @author gubatron
 * @author aldenml
 * @author marcelinkaaa
 */
public class DhtPromotionsAdapter extends AbstractAdapter<Slide> {

    private static final int NO_SPECIAL_OFFER = 97999605;
    private final List<Slide> slides;
    private final PromotionDownloader promotionDownloader;
    private final ImageLoader imageLoader;
    private FWBannerView fwBannerView;
    private int specialOfferLayout;
    private static final double PROMO_HEIGHT_TO_WIDTH_RATIO = 0.52998;

    public DhtPromotionsAdapter(Context ctx, List<Slide> slides, PromotionDownloader promotionDownloader) {
        super(ctx, R.layout.view_dht_promotions_item);
        this.slides = slides;
        this.imageLoader = ImageLoader.getInstance(ctx);
        this.promotionDownloader = promotionDownloader;
    }

    @Override
    public void setupView(View convertView, ViewGroup parent, Slide viewItem) {
        View vInfo = findView(convertView, R.id.dht_info_container);
        TextView name = findView(convertView, R.id.name);
        TextView time = findView(convertView, R.id.time);
        TextView reqs = findView(convertView, R.id.reqs);
        TextView size = findView(convertView, R.id.size);
        name.setText(viewItem.dhtData.getName());
        time.setText(viewItem.dhtData.getAtime());
        reqs.setText(viewItem.dhtData.getRegs());
        size.setText(AppUtils.toConvertSize(viewItem.dhtData.getSize()));

        TextView download = findView(convertView, R.id.view_promotions_item_download_textview);
        final Slide theSlide = viewItem;
        View.OnClickListener downloadPromoClickListener = view -> startPromotionDownload(theSlide);
        if (download != null) {
            download.setOnClickListener(downloadPromoClickListener);
        }

        vInfo.setOnClickListener(downloadPromoClickListener);

    }

    private void openClickURL(String clickURL) {
        UIUtils.openURL(getContext(), clickURL);
    }

    private void startVideoPreview(String videoURL) {
        // frostwire-preview is not a mobile friendly experience, let's take them straight to youtube
        if (videoURL.startsWith(Constants.FROSTWIRE_PREVIEW_DOT_COM_URL)) {
            videoURL = videoURL.substring(videoURL.indexOf("detailsUrl=") + "detailsUrl=".length());
        }

        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(videoURL));
        ((MainActivity) getContext()).startActivityForResult(i, MainActivity.PROMO_VIDEO_PREVIEW_RESULT_CODE);
    }

    private void startPromotionDownload(Slide theSlide) {
        promotionDownloader.startPromotionDownload(theSlide);
    }

    @Override
    public int getCount() {
        boolean inLandscapeMode =
                Configuration.ORIENTATION_LANDSCAPE ==
                        getContext().getResources().getConfiguration().orientation;
        int addSpecialOffer = (Constants.IS_GOOGLE_PLAY_DISTRIBUTION) ? 1 : 0;
        int addFrostWireFeaturesTitle = !inLandscapeMode ? 1 : 0;
        int slideCount = (slides != null) ? slides.size() : 0;
        if (inLandscapeMode && slideCount % 2 == 0) {
            slideCount--;
        }
        int addAllFeaturesButtonAtTheEnd = 1;
        return addSpecialOffer + addFrostWireFeaturesTitle + slideCount + addAllFeaturesButtonAtTheEnd;
    }

    @Override
    public Slide getItem(int position) {
        return slides.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        // Plus (or when on landscape orientation) needs no special offer as we can't sell remove ads yet
        boolean inLandscapeMode = Configuration.ORIENTATION_LANDSCAPE == getContext().getResources().getConfiguration().orientation;
        // "ALL FREE DOWNLOADS" button shown last
        if (position == getCount() - 1) {
            if (!Constants.IS_GOOGLE_PLAY_DISTRIBUTION || Constants.IS_BASIC_AND_DEBUG) {
                return View.inflate(getContext(), R.layout.view_frostwire_features_all_downloads, null);
            } else {
                return View.inflate(getContext(), R.layout.view_invisible_promo, null);
            }
        }
        return (!inLandscapeMode) ? getPortraitView(position, convertView, parent) :
                getLandscapeView(position, convertView, parent);
    }

    private View getPortraitView(int position, View convertView, ViewGroup parent) {
        // "FROSTWIRE FEATURES" view logic.
        int offsetFeaturesTitleHeader = 0;
        // OPTIONAL OFFER ON TOP
        if (Constants.IS_GOOGLE_PLAY_DISTRIBUTION || PlayStore.available()) {
            View basicPortraitView = getFWBasicPortraitView(position, convertView, parent);
            if (basicPortraitView != null) {
                return basicPortraitView;
            }
            offsetFeaturesTitleHeader++; // has to move down one slot since we'll have special offer above
        }
        // "FROSTWIRE FEATURES" title view
        if (position == offsetFeaturesTitleHeader) {
            if (Constants.IS_GOOGLE_PLAY_DISTRIBUTION && !Constants.IS_BASIC_AND_DEBUG) {
                return View.inflate(getContext(), R.layout.view_invisible_promo, null);
            } else {
                return View.inflate(getContext(), R.layout.view_frostwire_features_title, null);
            }
        }
        return super.getView(position - offsetFeaturesTitleHeader, null, parent);
    }

    private View getFWBasicPortraitView(int position, View convertView, ViewGroup parent) {
        // if you paid for ads we show no special layout (NO_SPECIAL_OFFER)
        int specialOfferLayout = pickSpecialOfferLayout();

        // Show special offer or banner, Google play logic included in pickSpecialOfferLayout()
        if (position == 0 && specialOfferLayout == NO_SPECIAL_OFFER) {
            return View.inflate(getContext(), R.layout.view_invisible_promo, null);
        } else if (position > 1) { // everything after the "FROSTWIRE FEATURES" title view.
            return super.getView(position - 2, null, parent);
        }
        return null;
    }

    private FWBannerView getFwBannerView() {
        if (fwBannerView == null) {
            fwBannerView = new FWBannerView(
                    getContext(),
                    null,
                    true,
                    false,
                    false,
                    FWBannerView.UNIT_ID_HOME);
            fwBannerView.setOnBannerLoadedListener(() -> {
                        fwBannerView.setShowDismissButton(false);
                        fwBannerView.setLayersVisibility(FWBannerView.Layers.APPLOVIN, true);
                    }
            );
            fwBannerView.loadFallbackBanner(FWBannerView.UNIT_ID_HOME);
            fwBannerView.setLayersVisibility(FWBannerView.Layers.FALLBACK, true);
            fwBannerView.loadMaxBanner();
        }
        return fwBannerView;
    }

    private View getLandscapeView(int position, View convertView, ViewGroup parent) {
        try {
            return super.getView(position, null, parent);
        } catch (Throwable t) {
            return convertView;
        }
    }

    /**
     * Decide what the special offer layout we should use.
     */
    private int pickSpecialOfferLayout() {
        // Optimistic: If we're plus, we can't offer ad removal yet.
        specialOfferLayout = NO_SPECIAL_OFFER;

        return specialOfferLayout;
    }

    public void onAllFeaturedDownloadsClick(String from) {
        UIUtils.openURL(getContext(), Constants.ALL_FEATURED_DOWNLOADS_URL + "?from=" + from);
    }

    public void onSpecialOfferClick() {
    }

    public void onDestroyView() {
        if (fwBannerView != null) {
            fwBannerView.destroy();
        }
    }
}
