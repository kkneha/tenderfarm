package com.example.tenderfarm1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Profile_update extends AppCompatActivity {

    public EditText updemail,updname,updpassword,updusertype,updaddress,updphone;
    Button updbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_update);


        updemail=(EditText)findViewById(R.id.upd_email);
        updname=(EditText)findViewById(R.id.upd_name);
        updpassword=(EditText)findViewById(R.id.upd_password);
        updusertype=(EditText)findViewById(R.id.upd_user_type);
        updaddress=(EditText)findViewById(R.id.upd_address);
        updphone=(EditText)findViewById(R.id.upd_ph_no);
        updbutton=(Button)findViewById(R.id.upd_button);

        String uname = updname.getText().toString();
        String uemail=updemail.getText().toString();
        String upassword=updpassword.getText().toString();
        String uusertype=updusertype.getText().toString();
        String uaddress=updaddress.getText().toString();
        String uphone=updphone.getText().toString();

        updbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Profile_update.this,Detect_disease.class));
            }
        });

    }
}
