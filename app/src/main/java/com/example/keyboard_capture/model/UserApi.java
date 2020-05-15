package com.example.keyboard_capture.model;

import com.google.gson.annotations.SerializedName;

public class UserApi {

    @SerializedName("userId")
    private String ssId;

    @SerializedName("userId")
    private String nickName;

    @SerializedName("userId")
    private String urlAvatar;

    @SerializedName("userId")
    private String rolId;

    public UserApi(String ssId){
        this.ssId = ssId;
    }

    public String getSsId() {
        return ssId;
    }

    public void setSsId(String ssId) {
        this.ssId = ssId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getUrlAvatar() {
        return urlAvatar;
    }

    public void setUrlAvatar(String urlAvatar) {
        this.urlAvatar = urlAvatar;
    }

    public String getRolId() {
        return rolId;
    }

    public void setRolId(String rolId) {
        this.rolId = rolId;
    }

    @Override
    public String toString() {
        return "UserApi{" +
                "nickName='" + nickName + '\'' +
                ", urlAvatar='" + urlAvatar + '\'' +
                ", rolId='" + rolId + '\'' +
                '}';
    }
}