package com.thoms.silonaorangtua;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class Pilihan extends AppCompatActivity {
    ImageView imageView1, imageView2;


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/GothamBook.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build());
        setContentView(R.layout.activity_pilihan);

        imageView1 = (ImageView)findViewById(R.id.ImageView1);
        imageView2 = (ImageView)findViewById(R.id.ImageView2);
    }

    public void daftarOrangtua(View v){
        Intent myIntent = new Intent(Pilihan.this,DaftarOrangtua.class);
        startActivity(myIntent);
    }

    public void daftarAnak(View v){
        Intent myIntent = new Intent(Pilihan.this, DaftarAnak.class);
        startActivity(myIntent);
    }

    public void halamanMasuk(View v){
        Intent myIntent = new Intent(Pilihan.this, Beranda.class);
        startActivity(myIntent);
    }


    public void onBackPressed(){
        Intent intent = new Intent(Pilihan.this, Beranda.class);
        startActivity(intent);
        finish();
    }
}
