package com.tvc.network.response;

import java.util.List;

//
//{
//        "hash": "6296ea1def838c1756f00132282cb08e9398c840",
//        "name": "Easy Listening Lounge Affair Luxury Chillout Cafe Music Selection for Perfect Relaxation (2014)",
//        "ip": "118.221.86.161",
//        "ctime": "2022-10-21T05:27:56.007Z",
//        "atime": "2022-10-21T05:27:56.007Z",
//        "reqs": 1,
//        "ext": ".mp3",
//        "cat": "music",
//        "id": 1,
//        "size": 321594340,
//        "hashUrl": "magnet:?xt=urn:btih:6296ea1def838c1756f00132282cb08e9398c840"
//        }
public class DhtData {
    private String hash;
    private String name;
    private String ip;
    private String ctime;
    private String atime;
    private int regs;
    private String ext;
    private String cat;
    private String id;
    private long size;
    private String hashUrl;
    private List<DhtFile> files;

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }

    public String getAtime() {
        return atime;
    }

    public void setAtime(String atime) {
        this.atime = atime;
    }

    public int getRegs() {
        return regs;
    }

    public void setRegs(int regs) {
        this.regs = regs;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getHashUrl() {
        return hashUrl;
    }

    public void setHashUrl(String hashUrl) {
        this.hashUrl = hashUrl;
    }

    public List<DhtFile> getFiles() {
        return files;
    }

    public void setFiles(List<DhtFile> files) {
        this.files = files;
    }

    /**
     * {
     * "length": 78,
     * "path": "offkab@sukebei.txt"
     * }
     */
    public static class DhtFile {
        private long length;
        private String path;

        public long getLength() {
            return length;
        }

        public void setLength(long length) {
            this.length = length;
        }
    }
}

