package com.example.keyboard_capture;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.keyboard_capture.model.Captura;
import com.example.keyboard_capture.model.Hijo;
import com.example.keyboard_capture.model.Padre;
import com.example.keyboard_capture.utility.CapsuledCaptured;
import com.example.keyboard_capture.utility.CapsuledChildren;
import com.example.keyboard_capture.utility.CapsuledFather;
import com.example.keyboard_capture.utility.CiperCyfre;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.InvalidParameterSpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class MainActivity extends AppCompatActivity {

    public String phoneNumber;
    public static MainActivity context;
    public static String imeiStatico;
    //Obtener el codigo IMEI con permisos

    TextView mostrar_imei;
    Button btn_obtener;
    public static String imei;
    public static String phone;

    static final Integer PHONESTATS = 0x1;
    private final String TAG=MainActivity.class.getSimpleName();

    //Objetos de la interfaz de prueba ->crear un objeto general o algo parecido .. revisar la arquitectura
    private Button btnPadre;
    private Button btnHijo;
    private Button btnCapture;
    private TextView padreView;
    private TextView hijoView;
    public static TextView captureView;

    //Llamada a los endpoints por medio de los siguientes objetos , mejorar esto con arquitectura ...
    private CapsuledCaptured capsuledCaptured;
    private CapsuledChildren capsuledChildren;
    private CapsuledFather capsuledFather;

    //Objetos para enviar y resivir datos json del api --> aplicando inyeccion de dependecias superficial
    private Hijo hijo;
    private Padre padre;
    private Captura captura;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //context = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Permisos para obtener el codigo imei y el numero de celular si es posible.
        consultarPermiso(Manifest.permission.READ_PHONE_STATE, PHONESTATS);
        //Si el dispositivo no esta asociado a la tarjeta sim no es posible obtener el numero de telefono.
        if(phone == null)
            phone = "3203719763";
/*
Verificar captura de imei y numero de celular... descomentar para debug en desarrollo
        Log.i(TAG, "payload: " + imei);
        Log.i(TAG, "payload: " + phone);

        Prueba pp = new Prueba("12354");
        Log.i(TAG, "payload clase de prueba: " + pp.getImePrueba());
        Log.i(TAG, "payload: " + phone);
*/


        //Llamada de los metodos para la prueba del api
        //Nota: Esto se puede realizar con un template para crear un solo metodo de llamada ..., la respuesta siempre sera
        //la misma eje Template<hijo, padre, capture> para un solo metodo que devuelve un request. Revisar la arquitectura

        btnPadre = findViewById(R.id.padrebtn);
        btnHijo = findViewById(R.id.hijobtn);
        btnCapture = findViewById(R.id.datacaptured);

        padreView = findViewById(R.id.padreTxt);
        hijoView = findViewById(R.id.hijoTxt);
        captureView = findViewById(R.id.captureTxt);

        btnPadre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                padre = new Padre(imei, "padre: " + "ip33322111"+"."+"pp122345", "p122345", "padre@gmail.com");
                capsuledFather = new CapsuledFather(padre);
                capsuledFather.callPostNewFather(padreView);
                //Log.i(TAG, "2call Padre: " + .val);
                //padreView.setText(CapsuledFather.val);
            }
        });

        btnHijo.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idPadreHashTest = get_SHA_512_SecurePassword("hash_padre", imei);
                hijo = new Hijo(imei, "hijo: " + imei+"."+phone, phone, "hijo@gmail.com",idPadreHashTest);
                capsuledChildren = new CapsuledChildren(hijo);
                capsuledChildren.callPostNewChildren(hijoView);
            }
        }));

        btnCapture.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {

                //captureView.setText(MyAccessibilityService.resolveMain);
            }
        });
    }


    public String getPhoneNumber() {
        return phoneNumber;
    }

    public static MainActivity getContext() { return context; }

    //Metodo para cifrar el hash del padre solo prueba... el id del padre viene cifrado

    public String get_SHA_512_SecurePassword(String passwordToHash, String salt){

        String generatedPassword = null;

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(salt.getBytes("UTF-8"));
            byte[] bytes = md.digest(passwordToHash.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++){
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException | UnsupportedEncodingException e){
            e.printStackTrace();
        }
        return generatedPassword;
    }

    //Obtener el codigo IMEI con permisos
    // Con este método mostramos en un Toast con un mensaje que el usuario ha concedido los permisos a la aplicación
    private void consultarPermiso(String permission, Integer requestCode) {
        if (ContextCompat.checkSelfPermission(MainActivity.this, permission) != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, permission)) {

                ActivityCompat.requestPermissions(MainActivity.this, new String[]{permission}, requestCode);

            } else {

                ActivityCompat.requestPermissions(MainActivity.this, new String[]{permission}, requestCode);
            }
        } else {
            imei = obtenerIMEI();
            Toast.makeText(this,permission + " El permiso a la aplicación esta concedido.", Toast.LENGTH_SHORT).show();
        }
    }


    // Con este método consultamos al usuario si nos puede dar acceso a leer los datos internos del móvil
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        String cellTmp = "";
        switch (requestCode) {
            case 1: {

                // Validamos si el usuario acepta el permiso para que la aplicación acceda a los datos internos del equipo, si no denegamos el acceso
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    imei = obtenerIMEI();
                    phone = obtenerPhone();

                } else {
                    Toast.makeText(MainActivity.this, "Has negado el permiso a la aplicación", Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
    }


    @SuppressLint("MissingPermission")
    private String obtenerIMEI() {
        final TelephonyManager telephonyManager= (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //Hacemos la validación de métodos, ya que el método getDeviceId() ya no se admite para android Oreo en adelante, debemos usar el método getImei()
            return telephonyManager.getImei();
        }
        else {
            return telephonyManager.getDeviceId();
        }

    }

    @SuppressLint("MissingPermission")
    private String obtenerPhone() {
        final TelephonyManager telephonyManager = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //Hacemos la validación de métodos, ya que el método getDeviceId() ya no se admite para android Oreo en adelante, debemos usar el método getImei()
            return telephonyManager.getLine1Number();
        } else {
            return telephonyManager.getLine1Number();
        }
    }

}
