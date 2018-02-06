package com.trust.tnighttalk.tool.kttest;

import io.reactivex.Observable;

/**
 * Created by Trust on 2018/2/5.
 */

public class Test {

    /**
     * status : 1
     * info : 查询已发布最新客户端版本信息成功
     * code : null
     * type : null
     * content : {"uid":124,"versionNo":"1.0.5","description":"有更新:1.0.5","url":"apk/20180125141950_SDCloudPlatform-1.0SenDiApp_1.0.5_core_3.apk","appType":1,"appPlatform":2,"uploadTime":"Jan 25, 2018 2:19:51 PM","md5Hex":"c8d9fcddd97609f14b0cf1e3f057915a","operatorUid":125,"operator":"trust","publishTime":"Jan 25, 2018 2:38:01 PM","status":1,"version":1}
     */

    private int status;
    private String info;
    private Object code;
    private Object type;
    private String content;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Object getCode() {
        return code;
    }

    public void setCode(Object code) {
        this.code = code;
    }

    public Object getType() {
        return type;
    }

    public void setType(Object type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
