package com.yuzhiyun.chemistry.model.entity.bmobEntity;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobRelation;

/**
 * Created by yuzhiyun on 2016-07-26.
 */
public class User extends BmobUser {
    //关联Record
    BmobRelation relationRecord;
    //由于bmob的密码不可见，所以我在这里新加一个字段，用于查看密码，以防有人忘记密码
    String pwd;
    //手机设备id
    String installationId;

    public String getInstallationId() {
        return installationId;
    }

    public void setInstallationId(String installationId) {
        this.installationId = installationId;
    }

    public BmobRelation getRelationRecord() {
        return relationRecord;
    }

    public void setRelationRecord(BmobRelation relationRecord) {
        this.relationRecord = relationRecord;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}