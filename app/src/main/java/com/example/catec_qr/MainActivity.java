package com.example.catec_qr;

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

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    SurfaceView surfaceView;
    TextView textView;
    CameraSource cameraSource;
    BarcodeDetector barcodeDetector;
<<<<<<< HEAD
    boolean ondetimedone=false;
=======
>>>>>>> c038e2092265526aa094c2fdee99978692119454
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        surfaceView=(SurfaceView)findViewById(R.id.camerapreview);
        textView=(TextView)findViewById(R.id.textView);
        barcodeDetector=new BarcodeDetector.Builder(this).setBarcodeFormats(Barcode.QR_CODE).build();
<<<<<<< HEAD
=======
        final String className;

>>>>>>> c038e2092265526aa094c2fdee99978692119454
        cameraSource=new CameraSource.Builder(this,barcodeDetector).setRequestedPreviewSize(640,480).build();

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
<<<<<<< HEAD
            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections) {

                final SparseArray<Barcode> qrCodes=detections.getDetectedItems();
                if(qrCodes.size()!=0){
                    if(ondetimedone==false){
                    Vibrator vibrator=(Vibrator)getApplicationContext().getSystemService(VIBRATOR_SERVICE);
                    vibrator.vibrate(500);
                    String s;
                    s=(qrCodes.valueAt(0).displayValue);
                    textView.setText(s);
                    if(s.equals("kekActivity")){
                        Intent intent=new Intent(MainActivity.this,kekActivity.class);
                        startActivity(intent);
                    }
                     ondetimedone=true;}}
                     }

=======

            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections) {
                final SparseArray<Barcode> qrCodes=detections.getDetectedItems();
                if(qrCodes.size()!=0){
                    textView.post(new Runnable() {
                        @Override
                        public void run() {
                            Vibrator vibrator=(Vibrator)getApplicationContext().getSystemService(VIBRATOR_SERVICE);
                            vibrator.vibrate(500);
                            String s;
                            s=(qrCodes.valueAt(0).displayValue);
                            textView.setText(s);
                            if(s.equals("kekActivity")) {
                                Intent kek = new Intent(MainActivity.this,kekActivity.class);
                                startActivity(kek);
                            }
                                                }


                    });
                                                           }
            }
>>>>>>> c038e2092265526aa094c2fdee99978692119454
        });

    }

}
