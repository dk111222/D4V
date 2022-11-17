package com.tvc.network.response;

/**
 * 登陆或注册
 */
public class LoginResponse extends BaseResponse {
    private DataInner data;

    public DataInner getData() {
        return data;
    }

    public void setData(DataInner data) {
        this.data = data;
    }

    //  {"msg":"success","total":0,"data":{"access":"ACCESS6051f93514fe1c5cbf281eb6","invitecode":"TRLID","openid":"AV365.6051f93514fe1c5cbf281eb2","expireAt":1615992149221,"vipExpireAt":0},"success":true,"state":200}
    public static class DataInner {
        private String access;
        private String openid;
        private String sn;
        private long expireAt;

        private String invitecode;
        private long vipExpireAt;

        private int inviteCount;  // 已邀请人数
        private int inviteAward;  // 已获得奖励天数
        private boolean invited = false; // 是否被邀请

        private String[] slogans;
        private String[] names;

        public String getAccess() {
            return access;
        }

        public void setAccess(String access) {
            this.access = access;
        }

        public String getOpenid() {
            return openid;
        }

        public void setOpenid(String openid) {
            this.openid = openid;
        }

        public String getSn() {
            return sn;
        }

        public void setSn(String sn) {
            this.sn = sn;
        }

        public long getExpireAt() {
            return expireAt;
        }

        public void setExpireAt(long expireAt) {
            this.expireAt = expireAt;
        }

        public String[] getSlogans() {
            return slogans;
        }

        public void setSlogans(String[] slogans) {
            this.slogans = slogans;
        }

        public String[] getNames() {
            return names;
        }

        public void setNames(String[] names) {
            this.names = names;
        }

        public String getInvitecode() {
            return invitecode;
        }

        public void setInvitecode(String invitecode) {
            this.invitecode = invitecode;
        }

        public long getVipExpireAt() {
            return vipExpireAt;
        }

        public void setVipExpireAt(long vipExpireAt) {
            this.vipExpireAt = vipExpireAt;
        }

        public int getInviteCount() {
            return inviteCount;
        }

        public void setInviteCount(int inviteCount) {
            this.inviteCount = inviteCount;
        }

        public int getInviteAward() {
            return inviteAward;
        }

        public void setInviteAward(int inviteAward) {
            this.inviteAward = inviteAward;
        }

        public boolean isInvited() {
            return invited;
        }

        public void setInvited(boolean invited) {
            this.invited = invited;
        }
    }
}
