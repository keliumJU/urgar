package com.example.keyboard_capture.model;

import com.google.gson.annotations.SerializedName;

public class Captura {

    @SerializedName("hash_owner")
    private String hash_owner;

    @SerializedName("ssid")
    private String ssid;

    @SerializedName("tipo_captura")
    private String tipo_captura;

    @SerializedName("cadena_capturado")
    private String cadena_capturado;

    @SerializedName("url_imagen")
    private String url_imagen;

    @SerializedName("fecha_captura")
    private String fecha_captura;

    @SerializedName("hora_captura")
    private String hora_captura;

    @SerializedName("aplicacion")
    private String aplicacion;

    @SerializedName("msg")
    private String msg;

    @SerializedName("hash")
    private String hash;

    public Captura(String hash_owner, String ssid, String tipo_captura, String cadena_capturado, String url_imagen, String fecha_captura, String hora_captura, String aplicacion) {
        this.hash_owner = hash_owner;
        this.ssid = ssid;
        this.tipo_captura = tipo_captura;
        this.cadena_capturado = cadena_capturado;
        this.url_imagen = url_imagen;
        this.fecha_captura = fecha_captura;
        this.hora_captura = hora_captura;
        this.aplicacion = aplicacion;
    }

    public String getHash_owner() {
        return hash_owner;
    }

    public void setHash_owner(String hash_owner) {
        this.hash_owner = hash_owner;
    }

    public String getSsid() {
        return ssid;
    }

    public void setSsid(String ssid) {
        this.ssid = ssid;
    }

    public String getTipo_captura() {
        return tipo_captura;
    }

    public void setTipo_captura(String tipo_captura) {
        this.tipo_captura = tipo_captura;
    }

    public String getCadena_capturado() {
        return cadena_capturado;
    }

    public void setCadena_capturado(String cadena_capturado) {
        this.cadena_capturado = cadena_capturado;
    }

    public String getUrl_imagen() {
        return url_imagen;
    }

    public void setUrl_imagen(String url_imagen) {
        this.url_imagen = url_imagen;
    }

    public String getFecha_captura() {
        return fecha_captura;
    }

    public void setFecha_captura(String fecha_captura) {
        this.fecha_captura = fecha_captura;
    }

    public String getHora_captura() {
        return hora_captura;
    }

    public void setHora_captura(String hora_captura) {
        this.hora_captura = hora_captura;
    }

    public String getAplicacion() {
        return aplicacion;
    }

    public void setAplicacion(String aplicacion) {
        this.aplicacion = aplicacion;
    }

    @Override
    public String toString() {
        return "Captura{" +
                "msg='" + msg + '\'' +
                ", hash='" + hash + '\'' +
                '}';
    }
}
