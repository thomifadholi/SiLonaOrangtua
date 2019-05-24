package com.thoms.silonaorangtua;

import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.LocationRequest;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.thoms.silonaorangtua.Adapters.DaftarRiwayatAnggota;
import com.thoms.silonaorangtua.Adapters.DaftarTergabungAnggota;
import com.thoms.silonaorangtua.Adapters.DetailRiwayat;
import com.thoms.silonaorangtua.Model.AnakDaftar;
import com.thoms.silonaorangtua.Model.RiwayatAnak;

import java.util.ArrayList;
import java.util.List;

public class IsiRiwayat extends AppCompatActivity {

    DatabaseReference senin, selasa, rabu, kamis, jumat, sabtu, minggu, currentReference, reference,connectedReference,usersReference;
    FirebaseAuth auth;
    FirebaseUser user;
    TextView lokasi, waktu;
    AnakDaftar anakDaftar;
    String userid, nama, date;
    LocationRequest request;
    RiwayatAnak riwayatAnak;
    ArrayList<DetailRiwayat> listriwayat;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_isi_riwayat);

        Intent intent = getIntent();



        if(intent != null) {
            nama = intent.getStringExtra("nama");
            userid = intent.getStringExtra("userid");
        }



        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        connectedReference = FirebaseDatabase.getInstance().getReference().child("Informasi_Orangtua").child(user.getUid()).child("TergabungAnggota");
        usersReference = FirebaseDatabase.getInstance().getReference().child("Informasi_Anak");
        senin = FirebaseDatabase.getInstance().getReference().child("Informasi_Anak").child(userid);
        selasa = FirebaseDatabase.getInstance().getReference().child("Informasi_Anak").child(userid);
        rabu = FirebaseDatabase.getInstance().getReference().child("Informasi_Anak").child(userid);
        kamis = FirebaseDatabase.getInstance().getReference().child("Informasi_Anak").child(userid);
        jumat = FirebaseDatabase.getInstance().getReference().child("Informasi_Anak").child(userid);
        sabtu = FirebaseDatabase.getInstance().getReference().child("Informasi_Anak").child(userid);
        minggu = FirebaseDatabase.getInstance().getReference().child("Informasi_Anak").child(userid);

        senin.child("Senin").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String waktu = dataSnapshot.child("lasttime").getValue(String.class);
                String lokasi = dataSnapshot.child("latitude").getValue(String.class)+" "+dataSnapshot.child("longitude").getValue(String.class);

//                Toast.makeText(getApplicationContext(), waktu,Toast.LENGTH_SHORT).show();
//                Toast.makeText(getApplicationContext(), lokasi,Toast.LENGTH_SHORT).show();
//
                TextView LokasiSenin = (TextView) findViewById(R.id.locSeninTxt);
                LokasiSenin.setText(lokasi);
                TextView WaktuSenin = (TextView) findViewById(R.id.timeSeninTxt);
                WaktuSenin.setText(waktu);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(),databaseError.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });

        selasa.child("Selasa").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String waktu = dataSnapshot.child("lasttime").getValue(String.class);
                String lokasi = dataSnapshot.child("latitude").getValue(String.class)+" "+dataSnapshot.child("longitude").getValue(String.class);

//                Toast.makeText(getApplicationContext(), waktu,Toast.LENGTH_SHORT).show();
//                Toast.makeText(getApplicationContext(), lokasi,Toast.LENGTH_SHORT).show();
//
                TextView LokasiSelasa = (TextView) findViewById(R.id.locSelasaTxt);
                LokasiSelasa.setText(lokasi);
                TextView WaktuSelasa = (TextView) findViewById(R.id.timeSelasaTxt);
                WaktuSelasa.setText(waktu);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(),databaseError.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });

        rabu.child("Rabu").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String waktu = dataSnapshot.child("lasttime").getValue(String.class);
                String lokasi = dataSnapshot.child("latitude").getValue(String.class)+" "+dataSnapshot.child("longitude").getValue(String.class);

//                Toast.makeText(getApplicationContext(), waktu,Toast.LENGTH_SHORT).show();
//                Toast.makeText(getApplicationContext(), lokasi,Toast.LENGTH_SHORT).show();
//
                TextView LokasiRabu = (TextView) findViewById(R.id.locRabuTxt);
                LokasiRabu.setText(lokasi);
                TextView WaktuRabu = (TextView) findViewById(R.id.timeRabuTxt);
                WaktuRabu.setText(waktu);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(),databaseError.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });

        kamis.child("Kamis").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


          //    String waktu = dataSnapshot.child("lasttime").child("lasttime").getValue(String.class);
//              String lokasi = dataSnapshot.child("latitude").getValue(String.class)+" "+dataSnapshot.child("longitude").getValue(String.class);

//                Toast.makeText(getApplicationContext(), waktu,Toast.LENGTH_SHORT).show();
//                Toast.makeText(getApplicationContext(), lokasi,Toast.LENGTH_SHORT).show();
//
                TextView LokasiKamis = (TextView) findViewById(R.id.locKamisTxt);
             //  LokasiKamis.setText(lokasi);
                TextView WaktuKamis = (TextView) findViewById(R.id.timeKamisTxt);
               // WaktuKamis.setText(waktu);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(),databaseError.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });

        rabu.child("Jumat").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                String waktu = dataSnapshot.child("lasttime").getValue(String.class);
             //   String lokasi = dataSnapshot.child("latitude").getValue(String.class)+" "+dataSnapshot.child("longitude").getValue(String.class);

//                Toast.makeText(getApplicationContext(), waktu,Toast.LENGTH_SHORT).show();
//                Toast.makeText(getApplicationContext(), lokasi,Toast.LENGTH_SHORT).show();
//
                TextView LokasiJumat = (TextView) findViewById(R.id.locJumatTxt);
              //  LokasiJumat.setText(lokasi);
                TextView WaktuJumat = (TextView) findViewById(R.id.timeJumatTxt);
              //  WaktuJumat.setText(waktu);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(),databaseError.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });

        rabu.child("Sabtu").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                String waktu = dataSnapshot.child("lasttime").getValue(String.class);
//                String lokasi = dataSnapshot.child("latitude").getValue(String.class)+" "+dataSnapshot.child("longitude").getValue(String.class);

//                Toast.makeText(getApplicationContext(), waktu,Toast.LENGTH_SHORT).show();
//                Toast.makeText(getApplicationContext(), lokasi,Toast.LENGTH_SHORT).show();
//
                TextView LokasiSabtu = (TextView) findViewById(R.id.locSabtuTxt);
              //  LokasiSabtu.setText(lokasi);
                TextView WaktuSabtu = (TextView) findViewById(R.id.timeSabtuTxt);
              //  WaktuSabtu.setText(waktu);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(),databaseError.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });

        rabu.child("Minggu").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String waktu = dataSnapshot.child("lasttime").getValue(String.class);
                String lokasi = dataSnapshot.child("latitude").getValue(String.class)+" "+dataSnapshot.child("longitude").getValue(String.class);

//                Toast.makeText(getApplicationContext(), waktu,Toast.LENGTH_SHORT).show();
//                Toast.makeText(getApplicationContext(), lokasi,Toast.LENGTH_SHORT).show();
//
                TextView LokasiMinggu = (TextView) findViewById(R.id.locMingguTxt);
                LokasiMinggu.setText(lokasi);
                TextView WaktuMinggu = (TextView) findViewById(R.id.timeMingguTxt);
                WaktuMinggu.setText(waktu);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(),databaseError.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });

    }


    public void rincianKamis(View v){
        Intent myIntent = new Intent(IsiRiwayat.this,RincianRiwayat.class);
        startActivity(myIntent);
    }
    public void rincianJumat(View v){
        Intent myIntent = new Intent(IsiRiwayat.this,RincianRiwayat.class);
        startActivity(myIntent);
    }
    public void rincianSabtu(View v){
        Intent myIntent = new Intent(IsiRiwayat.this,RincianRiwayat.class);
        startActivity(myIntent);
    }
}