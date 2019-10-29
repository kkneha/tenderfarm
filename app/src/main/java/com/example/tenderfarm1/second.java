package com.example.tenderfarm1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class second extends AppCompatActivity {

    private Button profile_button,detect_button,direct_order_button,logout_button;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        firebaseAuth=FirebaseAuth.getInstance();


        profile_button=(Button)findViewById(R.id.update_profile_btn);
        detect_button=(Button)findViewById(R.id.detect_btn);
        direct_order_button=(Button)findViewById(R.id.direct_order_btn);
        logout_button=(Button)findViewById(R.id.logout_btn);

        profile_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(second.this,Profile_update.class));
            }
        });


        detect_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(second.this,Detect_disease.class));
            }
        });

        direct_order_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(second.this,Direct_order.class));
            }
        });

        logout_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseAuth.signOut();
                finish();
                startActivity(new Intent(second.this,login.class));
            }
        });
    }
}
