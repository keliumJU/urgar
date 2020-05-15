package com.example.keyboard_capture.model;

import com.google.gson.annotations.SerializedName;

public class Padre {

    @SerializedName("ssid")
    private String ssid;

    @SerializedName("nick")
    private String nick;

    @SerializedName("ntelefono")
    private String ntelefono;

    @SerializedName("correo")
    private String correo;

    @SerializedName("msg")
    private String msg;

    @SerializedName("hash")
    private String hash;

    public Padre() {
    }

    public Padre(String ssid, String nick, String ntelefono, String correo){
        this.ssid = ssid;
        this.nick = nick;
        this.ntelefono = ntelefono;
        this.correo = correo;
        this.msg = "lola";
        this.hash = "hash lola";
    }



    public String getSsid() {
        return ssid;
    }

    public void setSsid(String ssid) {
        this.ssid = ssid;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getNtelefono() {
        return ntelefono;
    }

    public void setNtelefono(String ntelefono) {
        this.ntelefono = ntelefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    @Override
    public String toString() {
        return "Padre{" +
                "msg='" + msg + '\'' +
                ", hash='" + hash + '\'' +
                '}';
    }
}
