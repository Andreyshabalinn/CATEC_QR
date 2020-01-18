package com.example.qr_code_catec;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;


public class kekActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kek);
        Intent stintent = getIntent();
        String s1= stintent.getStringExtra("Tekst");
        TextView stat = findViewById(R.id.Tekst);
        stat.setText(s1);
        Drawable drawable =getResources().getDrawable(R.drawable.mlogo);
        ImageView imageView = findViewById(R.id.imageView2);
        imageView.setImageDrawable(drawable);

    }

    @Override
    public void onBackPressed(){
        Intent intent1= new Intent(kekActivity.this,MainActivity.class);
        startActivity(intent1);
        finish();
    }
}
