package com.tvc.network.response;


import java.util.List;

//    {"msg":"success","total":0,"data":
//    [{"update_time":"2021-03-11T06:18:14.796","create_time":"2021-03-11T06:18:14.796","price":15,"origin":36,"subtitle":"3个月","ccy":"USD",
//    "prodid":"多余字段","sn":"6049b626970a8bc5ff2193ad","title":"最划算","pack":"season"},
//    {"update_time":"2021-03-11T06:19:43.913","create_time":"2021-03-11T06:19:43.913","price":66,"origin":108,"subtitle":"9个月","ccy":"USD",
//    "prodid":"多余字段","sn":"6049b67f970a8bc5ff2193ba","title":"大会员","pack":"bigvip"},
//    {"update_time":"2021-03-13T15:07:35.546","create_time":"2021-03-13T15:07:35.546","price":8,"origin":12,"subtitle":"1个月","ccy":"USD",
//    "prodid":"none","sn":"604cd537970a8bc5ff2195ab","title":"尝鲜","pack":"month"}],"success":true,"state":200}
public class PaymentResponse extends BaseResponse {

    private List<VIPGoods> data;

    public List<VIPGoods> getData() {
        return data;
    }

    public void setData(List<VIPGoods> data) {
        this.data = data;
    }

    public static class VIPGoods {
        // 价格
        private String price;
        //    @API(value = "原价", visible = true)
        private String origin;
        //    @API(value = "货币", visible = true)
        private String ccy;

        //    @API(value = "标题", visible = true, search = true)
        private String title;

        private String en_title;
        //    @API(value = "副标题", visible = true, search = true)
        private String subtitle;
        private String en_subtitle;

        //    @API(value = "套餐", visible = true)
        private String sn;

        private String pack;

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getOrigin() {
            return origin;
        }

        public void setOrigin(String origin) {
            this.origin = origin;
        }

        public String getCcy() {
            return ccy;
        }

        public void setCcy(String ccy) {
            this.ccy = ccy;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }


        public String getSubtitle() {
            return subtitle;
        }

        public void setSubtitle(String subtitle) {
            this.subtitle = subtitle;
        }

        public String getSn() {
            return sn;
        }

        public void setSn(String sn) {
            this.sn = sn;
        }

        public String getEn_title() {
            return en_title;
        }

        public void setEn_title(String en_title) {
            this.en_title = en_title;
        }

        public String getEn_subtitle() {
            return en_subtitle;
        }

        public void setEn_subtitle(String en_subtitle) {
            this.en_subtitle = en_subtitle;
        }

        public String getPack() {
            return pack;
        }

        public void setPack(String pack) {
            this.pack = pack;
        }
    }

}
