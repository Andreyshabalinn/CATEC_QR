package com.example.qr_code_catec;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.TextView;
import android.util.Log;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import java.io.IOException;
import java.util.List;

import Data.DatabaseHandler;
import Model.Statya;


public class MainActivity extends AppCompatActivity {
    SurfaceView surfaceView;
    TextView textView;
    CameraSource cameraSource;
    BarcodeDetector barcodeDetector;
    boolean ondetimedone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DatabaseHandler databaseHandler= new DatabaseHandler(this);
        databaseHandler.addStaiya(new Statya("О нас", "ЦАТЭК КОЛЛЕДЖ РАСПОЛОЖЕННЫЙ НА УЛИЦЕ ДОСТЫК ОСНОВАН ДАВНО ДАВНО"));
        databaseHandler.addStaiya(new Statya("Контакты", "ТЕЛЕФОН 87714010218"));
        databaseHandler.addStaiya(new Statya("Преподаватели", "ЦАТЭК КОЛЛЕДЖ РАСПОЛОЖЕННЫЙ НА УЛИЦЕ ДОСТЫК ОСНОВАН ДАВНО ДАВНО"));
        databaseHandler.addStaiya(new Statya("Кабинеты", "ЦАТЭК КОЛЛЕДЖ РАСПОЛОЖЕННЫЙ НА УЛИЦЕ ДОСТЫК ОСНОВАН ДАВНО ДАВНО"));



        surfaceView=(SurfaceView)findViewById(R.id.camerapreview);
        textView=(TextView)findViewById(R.id.textView);
        barcodeDetector=new BarcodeDetector.Builder(this).setBarcodeFormats(Barcode.QR_CODE).build();
        cameraSource=new CameraSource.Builder(this,barcodeDetector).setRequestedPreviewSize(640,480).build();
        ondetimedone=false;

        List<Statya> statyaList = databaseHandler.getAllStatyas();
        Statya s1=databaseHandler.getStatya(1);
        textView.append(s1.getTekst());
        surfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                if(ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED){
                    return;
                }
                try{cameraSource.start(holder);}
                catch (IOException e){e.printStackTrace();}
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                cameraSource.stop();
            }
        });
        barcodeDetector.setProcessor(new Detector.Processor<Barcode>() {
            @Override
            public void release() {

            }
            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections) {

                final SparseArray<Barcode> qrCodes=detections.getDetectedItems();
                if(qrCodes.size()!=0){

                        Vibrator vibrator=(Vibrator)getApplicationContext().getSystemService(VIBRATOR_SERVICE);
                        String s;
                        s=(qrCodes.valueAt(0).displayValue);
                        textView.setText(s);
                    if(ondetimedone==false){
                    vibrator.vibrate(1000);
                        if(s.equals("kekActivity")){
                            Intent intent=new Intent(MainActivity.this,kekActivity.class);
                            startActivity(intent);
                            finish();

                        }
                        ondetimedone=true;}}
            }
        });

    }

}
