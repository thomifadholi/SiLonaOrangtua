package com.thoms.silonaorangtua;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;
import com.thoms.silonaorangtua.Model.OrangtuaDaftar;

public class EditProfile extends AppCompatActivity {
    FirebaseAuth auth;
    FirebaseUser user;
    Marker currentMarker;
    LatLng latLng;
    ImageView ep_profil;
    Uri resultUri;
    Uri imageUri;

    TextView ep_nama, ep_email, ep_sandi;
    DatabaseReference reference;
    FirebaseStorage firebaseStorage;

    StorageReference storageReference;
    DatabaseReference orangtuaReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        orangtuaReference = FirebaseDatabase.getInstance().getReference().child("Informasi_Orangtua").child(auth.getCurrentUser().getUid());
        StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("Image").child(auth.getCurrentUser().getUid());

        ep_profil = (ImageView)findViewById(R.id.ep_profil);
        ep_nama = (TextView)findViewById(R.id.ep_nama);
        ep_email = (TextView)findViewById(R.id.ep_email);
        ep_sandi = (TextView) findViewById(R.id.ep_sandi);
        SpannableString content = new SpannableString("Content");
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        ep_nama.setText(content);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.floatingActionButtonProfile);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        orangtuaReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                OrangtuaDaftar orangtuaDaftar =dataSnapshot.getValue(OrangtuaDaftar.class);


              ep_nama.setText(orangtuaDaftar.nama);
              ep_email.setText(orangtuaDaftar.email);
              ep_sandi.setText(orangtuaDaftar.password);
              Picasso.get().load(orangtuaDaftar.imageUrl).into(ep_profil);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    public void selectimage(View v){
        CropImage.activity()
                .setGuidelines(CropImageView.Guidelines.ON)
                .setActivityTitle("My Crop")
                .setCropShape(CropImageView.CropShape.OVAL)
                .setCropMenuCropButtonTitle("Done")
                .setAspectRatio(1,1)
                .start(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // handle result of CropImageActivity
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {

                resultUri = result.getUri();
                ep_profil.setImageURI(resultUri);
                StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("Image").child(auth.getCurrentUser().getUid() + ".jpg");

                ((ImageView) findViewById(R.id.ep_profil)).setImageURI(result.getUri());
                Toast.makeText(
                        this, "Gambar Profile Berhasil DiPerbaharui", Toast.LENGTH_LONG)
                        .show();
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Toast.makeText(this, "Gambar Profile Gagal ", Toast.LENGTH_LONG).show();
            }
        }
    }


    public void onBackPressed(){
        Intent intent = new Intent(EditProfile.this, Home.class);
        startActivity(intent);
        finish();
    }
}
