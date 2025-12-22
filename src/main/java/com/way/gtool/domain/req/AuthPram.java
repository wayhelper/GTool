package com.way.gtool.domain.req;

/**
 * @author JingWei Guo
 * @date 2024/12/26 15:29
 * @desciption: 认证入参
 */
public class AuthPram {
    String appKey;

    public AuthPram(String appKey) {
        this.appKey = appKey;
    }

    public AuthPram() {
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    @Override
    public String toString() {
        return "AuthPram{" +
                "appKey='" + appKey + '\'' +
                '}';
    }
}
