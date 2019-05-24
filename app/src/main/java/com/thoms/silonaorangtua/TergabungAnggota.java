package com.thoms.silonaorangtua;

import android.os.Handler;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.thoms.silonaorangtua.Adapters.DaftarTergabungAnggota;
import com.thoms.silonaorangtua.Model.AnakDaftar;

import java.util.ArrayList;

public class TergabungAnggota extends AppCompatActivity {

    RecyclerView recyclerView;

    RecyclerView.LayoutManager layoutManager;

    FirebaseAuth auth;
    DatabaseReference connectedReference,usersReference;
    ArrayList<AnakDaftar> nameList;
    FirebaseUser firebaseUser;
    AnakDaftar anakDaftar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tergabung_anggota);

        recyclerView = (RecyclerView)findViewById(R.id.recyclerview1);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        // myList = new ArrayList<>();

        nameList = new ArrayList<>();

        recyclerView.setLayoutManager(layoutManager);

        auth = FirebaseAuth.getInstance();
        firebaseUser = auth.getCurrentUser();
        connectedReference = FirebaseDatabase.getInstance().getReference().child("Informasi_Orangtua").child(firebaseUser.getUid()).child("TergabungAnggota");
        usersReference = FirebaseDatabase.getInstance().getReference().child("Informasi_Anak");

        connectedReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                nameList.clear();

                if (dataSnapshot.exists()){
                    for (DataSnapshot dss: dataSnapshot.getChildren()){
                        String memberUserid = dss.child("idanggota").getValue(String.class);

                        usersReference.child(memberUserid).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                if (dataSnapshot.exists()){
                                    anakDaftar = dataSnapshot.getValue(AnakDaftar.class);
                                    nameList.add(anakDaftar);
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {
                                Toast.makeText(getApplicationContext(),databaseError.getMessage(),Toast.LENGTH_SHORT).show();

                            }
                        });
                    }

                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            RecyclerView.Adapter adaptermy = new DaftarTergabungAnggota(nameList, TergabungAnggota.this);
                            recyclerView.setAdapter(adaptermy);
                            adaptermy.notifyDataSetChanged();
                        }
                    }, 800);
                }
                else {
                    Toast.makeText(getApplicationContext(),"Anda Belum Bergabung Dengan Daftar Pengguna Mana Pun!",Toast.LENGTH_SHORT).show();
                    recyclerView.setAdapter(null);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(),databaseError.toString(),Toast.LENGTH_LONG).show();
            }
        });

    }
}
