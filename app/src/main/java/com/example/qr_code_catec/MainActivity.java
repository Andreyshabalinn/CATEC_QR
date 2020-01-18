package com.example.qr_code_catec;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.DialogFragment;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.TextView;
import android.util.Log;
import android.widget.Toast;
import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;
import android.view.View;
import java.io.IOException;
import java.util.List;

import Data.DatabaseHandler;
import Model.Statya;
import Utils.Util;

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
//        databaseHandler.addStaiya(new Statya("Контакты", "ТЕЛЕФОН 87714010218"));
//        databaseHandler.addStaiya(new Statya("Преподаватели", "ИВАН АНДРЕЕВИЧ, УСАТОВА ОЛЬГА, НЕХОРОШЕВ ВЛАДИМИР"));
//        databaseHandler.addStaiya(new Statya("Кабинеты", "ПРОГРАММИСТ, ТЗИ, РЭС, ИСТ"));
        surfaceView=(SurfaceView)findViewById(R.id.camerapreview);
        textView=(TextView)findViewById(R.id.textView);
        barcodeDetector=new BarcodeDetector.Builder(this).setBarcodeFormats(Barcode.QR_CODE).build();
        cameraSource=new CameraSource.Builder(this,barcodeDetector).setRequestedPreviewSize(640,480).build();
        ondetimedone=false;


//        Statya delstatya= databaseHandler.getStatya(1);
//        databaseHandler.deleteStatya(delstatya);
        List<Statya> StatyaList = databaseHandler.getAllStatyas();
        for(Statya statya : StatyaList){
            Log.d("Statya info", "ID"+statya.getId()+", Zag "+statya.getZag()+", Tekst "+statya.getTekst());
        }
//            Statya statya = databaseHandler.getStatya(3);
//            statya.setZag("Преподаватели");
//            statya.setTekst("ИВАН АНДРЕЕВИЧ, УСАТОВА ОЛЬГА, НЕХОРОШЕВ ВЛАДИМИР");
//            int updatedStatyaId = databaseHandler.updateStatya(statya);
//            Log.d("StatyaInfo: ","ID"+statya.getId()+", Zag" + statya.getZag()
//            +", Tekst" + statya.getTekst() + ", updatedStatyaId " + updatedStatyaId);

        final Statya  s1;
        final Statya  s2;
        final Statya  s3;
        final Statya  s4;


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
                        Intent intent=new Intent(MainActivity.this,kekActivity.class);

//                        if(s.equals("About")){
//                            intent.putExtra("Tekst",s1.getTekst().toString());
//                            startActivity(intent);
//                        }
//                        else if(s.equals("Contacts")){
//                            intent.putExtra("Tekst",s2.getTekst().toString());
//                            startActivity(intent);
//                        }
//                        else if(s.equals("Teachers")){
//                            intent.putExtra("Tekst",s3.getTekst().toString());
//                            startActivity(intent);
//                        }
//                        else if(s.equals("Specialyties")){
//                            intent.putExtra("Tekst",s4.getTekst().toString());
//                            startActivity(intent);
//                        }
//                        else {
                            intent.putExtra("Tekst","Отсканнированного Вами QR кода нет в нашей базе, Содержание QR кода: "+s);
                            startActivity(intent);
//                        }
                        ondetimedone=true;
                       finish();
                    }}
            }
        });

    }

}
