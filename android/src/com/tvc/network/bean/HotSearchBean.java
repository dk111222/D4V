package com.tvc.network.bean;

import java.io.Serializable;

/**
 * 热搜关键字
 */
class HotSearchBean implements Serializable {
//    @API(value = "sn", readonly = true)
    private  String sn ;

//    @API(value = "排序", visible = true)
    private  int sort;

//    @API(value = "名称", visible = true, search = true)
    private  String name;


}
