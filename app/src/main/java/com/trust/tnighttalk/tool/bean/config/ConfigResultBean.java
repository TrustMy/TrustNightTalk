package com.trust.tnighttalk.tool.bean.config;

import com.trust.tnighttalk.tool.bean.Bean;

/**
 * Created by Trust on 2018/1/26.
 */

public class ConfigResultBean extends Bean {
    private  int status,code;
    private  Object object;

    public ConfigResultBean(int status, int code, Object object) {
        this.status = status;
        this.code = code;
        this.object = object;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
