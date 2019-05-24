package com.thoms.silonaorangtua;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
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
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.thoms.silonaorangtua.Adapters.DaftarTergabungAnggota;
import com.thoms.silonaorangtua.Adapters.ListRincianRiwayat;
import com.thoms.silonaorangtua.Model.AnakDaftar;
import com.thoms.silonaorangtua.Model.Rincian;
import com.thoms.silonaorangtua.Model.RiwayatAnak;

import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class RincianRiwayat extends AppCompatActivity {
    FirebaseUser firebaseUser;
    ListView listView;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    Rincian rincian;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rincian_riwayat);


        rincian = new Rincian();
        listView = (ListView)findViewById(R.id.listView1);
        firebaseDatabase = FirebaseDatabase.getInstance();

        list = new ArrayList<>();
        adapter = new ArrayAdapter<String>(this,R.layout.list_rincian_riwayat, R.id.waktu_title, list);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Informasi_anak").child("Jumat").child("lasttime");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               // GenericTypeIndicator<Map<String, String>> genericTypeIndicator = new GenericTypeIndicator<Map<String, String>>() {};
                //Map<String, String> map = dataSnapshot.getValue(genericTypeIndicator );
              //  ArrayList<Object> objectArrayList = new ArrayList<Object>(genericTypeIndicator);

                Iterable<DataSnapshot> snapshotIterator = dataSnapshot.getChildren();
                Iterator<DataSnapshot> iterator = snapshotIterator.iterator();

                while (iterator.hasNext()) {
                    DataSnapshot next = (DataSnapshot) iterator.next();

                    String match = next.child("Waktu").getValue(String.class);
                    String key = next.getKey();
                    list.add(key);
                    list.add(match);

                }
                listView.setAdapter(adapter);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "Tidak Keluar" ,Toast.LENGTH_SHORT).show();
            }
        });


      //  System.out.println(rincian.Waktu);
       // Toast.makeText(getApplicationContext(), "keluar apa" ,Toast.LENGTH_SHORT).show();


    }
}
