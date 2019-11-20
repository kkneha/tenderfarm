package com.example.tenderfarm1;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
//import com.google.firebase.storage.FirebaseStorage;
//import com.google.firebase.storage.StorageReference;
//import com.squareup.picasso.Picasso;

public class ProfileActivity extends AppCompatActivity {

    private ImageView profilePic;
    private TextView pemail,pname,pusertype,paddress,pphone;
    private Button profileUpdate, changePassword;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    //private FirebaseStorage firebaseStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        profilePic = findViewById(R.id.p_img);
        pname = findViewById(R.id.p_name);
        pusertype = findViewById(R.id.p_user_type);
        paddress = findViewById(R.id.p_address);
        pphone = findViewById(R.id.p_ph_no);
        profileUpdate = findViewById(R.id.p_button);



        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        //firebaseStorage = FirebaseStorage.getInstance();

        DatabaseReference databaseReference = firebaseDatabase.getReference(firebaseAuth.getUid());

//        StorageReference storageReference = firebaseStorage.getReference();
//        storageReference.child(firebaseAuth.getUid()).child("Images/Profile Pic").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//            @Override
//            public void onSuccess(Uri uri) {
//                Picaso.get().load(uri).fit().centerCrop().into(profilePic);
//            }
//        });

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Userdata userdata = dataSnapshot.getValue(Userdata.class);
                pname.setText("Name: " + userdata.getName());
                pusertype.setText("User_Type: " + userdata.getType());
                paddress.setText("Email: " + userdata.getEmail());
                pphone.setText("Phone: " + userdata.getPhone());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(ProfileActivity.this, databaseError.getCode(), Toast.LENGTH_SHORT).show();
            }
        });

        profileUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfileActivity.this, Profile_update.class));
            }
        });

    }

}
