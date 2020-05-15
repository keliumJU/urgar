package com.example.keyboard_capture.utility;

import android.util.Log;
import android.widget.TextView;

import com.example.keyboard_capture.api.retrofit.RestApiAdapter;
import com.example.keyboard_capture.model.Padre;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CapsuledFather {

    private Padre padre;
    public static String val;

    private static final String TAG = "PadreLog";

    public CapsuledFather(Padre padre) {
        this.padre = padre;
        //val = "none";
    }

    public void callPostNewFather(final TextView viewPrueba) {
        //Esto es horrible cambiarlo por una sola instancia -- objeto global y unico buscarlo en la arqutectura
        RestApiAdapter restApiAdapter = new RestApiAdapter();

        //Hacer inyeccion de dependencias para reutilizar el mismo objeto y no saturar la memoria con una nuva instancia.
        //imei = constants.getIMEI();
        restApiAdapter
                .getClientService()
                .createPadre(padre)
                .enqueue(new Callback<Padre>() {
                    @Override
                    public void onResponse(Call<Padre> call, Response<Padre> response) {
                        //padre.setMsg(response.body().toString());
                        //padre.setHash(response.body().getHash());
                        viewPrueba.setText(response.body().toString());
                        Log.i(TAG,"request: " + response.body().toString() + "==> u_u");
                    }

                    @Override
                    public void onFailure(Call<Padre> call, Throwable t) {
                        Log.i(TAG,"request: Error :(");

                    }
                });
    }

    public Padre getPadre() {
        return padre;
    }

    public void setPadre(Padre padre) {
        this.padre = padre;
    }


}
