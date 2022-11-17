/*
 * Created by Angel Leon (@gubatron), Alden Torres (aldenml)
 * Copyright (c) 2011-2016, FrostWire(R). All rights reserved.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.frostwire.frostclick;

import com.frostwire.licenses.License;
import com.frostwire.licenses.Licenses;
import com.frostwire.search.torrent.TorrentSearchResult;

import org.apache.commons.io.FilenameUtils;

/**
 * @author gubatron
 * @author aldenml
 */
public class DhtTorrentPromotionSearchResult implements TorrentSearchResult {

    private static final String FROSTCLICK_VENDOR = "FrostClick";
    private int uid = -1;
    private final Slide slide;
    private final long creationTime;

    public DhtTorrentPromotionSearchResult(Slide slide) {
        this.slide = slide;
        this.creationTime = System.currentTimeMillis();
    }

    public String getDisplayName() {
        return slide.dhtData.getName();
    }

    @Override
    public double getSize() {
        return slide.dhtData.getSize();
    }

    @Override
    public String getFilename() {
        return slide.dhtData.getName();
    }

    @Override
    public long getCreationTime() {
        return creationTime;
    }

    @Override
    public String getHash() {
        return slide.dhtData.getHash();
    }

    @Override
    public String getDetailsUrl() {
        return slide.dhtData.getHashUrl();
    }

    @Override
    public String getTorrentUrl() {
        return slide.dhtData.getHashUrl();
    }

    @Override
    public String getReferrerUrl() {
        return slide.dhtData.getHashUrl();
    }

    @Override
    public String getSource() {
        return FROSTCLICK_VENDOR;
    }

    @Override
    public int getSeeds() {
        return -1;
    }

    @Override
    public License getLicense() {
        return Licenses.UNKNOWN;
    }

    @Override
    public String getThumbnailUrl() {
        return null;
    }
}
