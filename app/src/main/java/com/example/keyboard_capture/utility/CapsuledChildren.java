package com.example.keyboard_capture.utility;

import com.example.keyboard_capture.api.retrofit.RestApiAdapter;
import com.example.keyboard_capture.model.Hijo;
import com.example.keyboard_capture.model.Padre;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CapsuledChildren {

    private Hijo hijo;

    public CapsuledChildren(Hijo hijo) {
        this.hijo = hijo;
    }

    public String callPostNewChildren(){
        //Esto es horrible cambiarlo por una sola instancia -- objeto global y unico buscarlo en la arqutectura
        RestApiAdapter restApiAdapter = new RestApiAdapter();

        //Hacer inyeccion de dependencias para reutilizar el mismo objeto y no saturar la memoria con una nuva instancia.
        //imei = constants.getIMEI();
        final String[] request = {""};
        restApiAdapter
                .getClientService()
                .newChildren(hijo)
                .enqueue(new Callback<Hijo>() {
                    @Override
                    public void onResponse(Call<Hijo> call, Response<Hijo> response) {
                        request[0] = response.body().toString();
                        //Log.i(TAG,"request: " + response.body().toString() + "==> u_u");
                    }
                    @Override
                    public void onFailure(Call<Hijo> call, Throwable t) {
                        //Log.i(TAG,"request: Error :(");
                        request[0] = "Fail call api";

                    }
                });

        return request[0];
    }

}
