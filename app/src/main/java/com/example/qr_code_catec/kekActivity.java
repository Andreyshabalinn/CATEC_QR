package com.example.qr_code_catec;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;


public class kekActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kek);


    }

    @Override
    public void onBackPressed(){
        Intent intent1= new Intent(kekActivity.this,MainActivity.class);
        startActivity(intent1);
        finish();
    }
}
