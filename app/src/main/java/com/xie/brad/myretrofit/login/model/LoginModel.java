package com.xie.brad.myretrofit.login.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by MVPHelper on 2016/09/13
 */

public class LoginModel implements Serializable {

    /**
     * admin_description : 深圳仓管
     * admin_level : 29
     * admin_name : King.Li
     * authority : [{"id":"1","name":"订单","show":true},{"id":"2","name":"半成品仓管","show":false},{"id":"3","name":"成品仓管","show":true},{"id":"4","name":"包装出库","show":false},{"id":"5","name":"盘点","show":true},{"id":"6","name":"打印条形码","show":true},{"id":"7","name":"扫描单个产品信息","show":true}]
     */
    private String admin_description;
    private String admin_level;
    private String admin_name;
    private String weight_unit;
    private String sessionID;

    public String getSessionID() {
        return sessionID;
    }

    public void setSessionID(String sessionID) {
        this.sessionID = sessionID;
    }

    public String getWeight_unit() {
        return weight_unit;
    }

    public void setWeight_unit(String weight_unit) {
        this.weight_unit = weight_unit;
    }

    /**
     * id : 1
     * name : 订单
     * show : true
     */

    private List<AuthorityBean> authority;

    public String getAdmin_description() {
        return admin_description;
    }

    public void setAdmin_description(String admin_description) {
        this.admin_description = admin_description;
    }

    public String getAdmin_level() {
        return admin_level;
    }

    public void setAdmin_level(String admin_level) {
        this.admin_level = admin_level;
    }

    public String getAdmin_name() {
        return admin_name;
    }

    public void setAdmin_name(String admin_name) {
        this.admin_name = admin_name;
    }

    public List<AuthorityBean> getAuthority() {
        return authority;
    }

    public void setAuthority(List<AuthorityBean> authority) {
        this.authority = authority;
    }

    public static class AuthorityBean implements Serializable {
        @Override
        public String toString() {
            return "AuthorityBean{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", show=" + show +
                    ", imageId=" + image +
                    '}';
        }

        private int id;
        private String name;
        private boolean show;
        private String image;
        private boolean button1;

        public boolean isButton1() {
            return button1;
        }

        public void setButton1(boolean button1) {
            this.button1 = button1;
        }

        public String getImage() {
            return image;
        }

        public void setImageId(int imageId) {
            this.image = image;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public boolean isShow() {
            return show;
        }

        public void setShow(boolean show) {
            this.show = show;
        }
    }
}