package com.yuzhiyun.chemistry.model.entity;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobRelation;

/**
 * Created by yuzhiyun on 2016-07-26.
 */
public class User extends BmobUser {
    BmobRelation time ;
    //由于bmob的密码不可见，所以我在这里新加一个字段，用于查看密码，以防有人忘记密码
    String pwd;

    public BmobRelation getTime() {
        return time;
    }

    public void setTime(BmobRelation time) {
        this.time = time;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}