package com.example.keyboard_capture.utility;

import android.util.Log;

import com.example.keyboard_capture.api.retrofit.RestApiAdapter;
import com.example.keyboard_capture.model.Padre;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CapsuledFather {

    private Padre padre;
    private static final String TAG = "PadreLog";

    public  CapsuledFather(Padre padre){
        this.padre = padre;
    }

    public String callPostNewFather(){
        //Esto es horrible cambiarlo por una sola instancia -- objeto global y unico buscarlo en la arqutectura
        RestApiAdapter restApiAdapter = new RestApiAdapter();

        //Hacer inyeccion de dependencias para reutilizar el mismo objeto y no saturar la memoria con una nuva instancia.
        //imei = constants.getIMEI();
        final String[] request = {""};
        restApiAdapter
                .getClientService()
                .createPadre(padre)
                .enqueue(new Callback<Padre>() {
                    @Override
                    public void onResponse(Call<Padre> call, Response<Padre> response) {
                        request[0] = response.body().toString();
                        //Log.i(TAG,"request: " + response.body().toString() + "==> u_u");
                    }
                    @Override
                    public void onFailure(Call<Padre> call, Throwable t) {
                        //Log.i(TAG,"request: Error :(");
                        request[0] = "Fail call api";

                    }
                });

        return request[0];
    }

}
