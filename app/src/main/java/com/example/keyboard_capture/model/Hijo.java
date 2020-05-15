package com.example.keyboard_capture.model;

import com.google.gson.annotations.SerializedName;

public class Hijo {
    @SerializedName("ssid")
    private String ssid;

    @SerializedName("nick")
    private String nick;

    @SerializedName("ntelefono")
    private String ntelefono;

    @SerializedName("correo")
    private String correo;

    @SerializedName("hash_padre")
    private String hash_padre;

    @SerializedName("msg")
    private String msg;

    @SerializedName("hash")
    private String hash;

    public Hijo(String ssid, String nick, String ntelefono, String correo, String hash_padre) {
        this.ssid = ssid;
        this.nick = nick;
        this.ntelefono = ntelefono;
        this.correo = correo;
        this.hash_padre = hash_padre;
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

    public String getHash_padre() {
        return hash_padre;
    }

    public void setHash_padre(String hash_padre) {
        this.hash_padre = hash_padre;
    }

    @Override
    public String toString() {
        return "Hijo{" +
                "msg='" + msg + '\'' +
                ", hash='" + hash + '\'' +
                '}';
    }
}
