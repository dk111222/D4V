package com.tvc.network.bean;

/**
 * 视频信息
 */
public class PlayUrlBean {

    //    @API(value = "sn", readonly = true)
    private String file;

    //    @API(value = "排序", visible = true)
    private String label;

    //    @API(value = "女优", visible = true, search = true)
    private String type;

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nlabel:").append(label).append("\ntype: ").append(type);
        return sb.toString();
    }
}
