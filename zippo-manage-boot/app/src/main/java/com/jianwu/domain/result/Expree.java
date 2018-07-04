package com.jianwu.domain.result;

import java.util.List;

/**
 * @Author:lijin
 * @Date:10:14 2018/6/15
 * @Remark:
 */
public class Expree {


    /**
     * message : ok
     * nu : VA44378644332
     * ischeck : 1
     * condition : F00
     * com : jd
     * status : 200
     * state : 3
     * data : [{"time":"2018-06-14 12:31:08","ftime":"2018-06-14 12:31:08","context":"订单已由本人签收，感谢您在京东购物，欢迎您再次光临！","location":""},{"time":"2018-06-14 09:19:19","ftime":"2018-06-14 09:19:19","context":"配送员开始配送，请您准备收货，配送员，裴仁军，手机号，13071895743","location":""},{"time":"2018-06-14 09:02:53","ftime":"2018-06-14 09:02:53","context":"货物已分配，等待配送","location":""},{"time":"2018-06-14 02:50:40","ftime":"2018-06-14 02:50:40","context":"货物已完成分拣，离开【杭州萧山分拣中心】","location":""},{"time":"2018-06-14 02:49:57","ftime":"2018-06-14 02:49:57","context":"货物已到达【杭州萧山分拣中心】","location":""},{"time":"2018-06-13 21:53:26","ftime":"2018-06-13 21:53:26","context":"货物已完成分拣，离开【华东外单分拣中心】","location":""},{"time":"2018-06-13 21:52:56","ftime":"2018-06-13 21:52:56","context":"货物已交付京东物流","location":""}]
     */

    private String message;
    private String nu;
    private String ischeck;
    private String condition;
    private String com;
    private String status;
    private String state;
    private List<DataBean> data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getNu() {
        return nu;
    }

    public void setNu(String nu) {
        this.nu = nu;
    }

    public String getIscheck() {
        return ischeck;
    }

    public void setIscheck(String ischeck) {
        this.ischeck = ischeck;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getCom() {
        return com;
    }

    public void setCom(String com) {
        this.com = com;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * time : 2018-06-14 12:31:08
         * ftime : 2018-06-14 12:31:08
         * context : 订单已由本人签收，感谢您在京东购物，欢迎您再次光临！
         * location :
         */

        private String time;
        private String ftime;
        private String context;
        private String location;

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getFtime() {
            return ftime;
        }

        public void setFtime(String ftime) {
            this.ftime = ftime;
        }

        public String getContext() {
            return context;
        }

        public void setContext(String context) {
            this.context = context;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }
    }
}
