package com.example.tenderfarm1;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class create_account extends AppCompatActivity {

    public EditText regemail,regname,regpassword,regusertype,regaddress,regphone;
    public String utype="";
    private Button reg_button;
    private RadioGroup rg;
    private RadioButton rb1;
    private RadioButton rb2;
    private TextView regtextView;
    public RadioButton rb;

    private ProgressDialog loadingbar;


    private FirebaseAuth firebaseAuth;


    String name,email,password,usertype,address,phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);


        regemail=(EditText)findViewById(R.id.reg_email);
        regname=(EditText)findViewById(R.id.reg_name);
        regpassword=(EditText)findViewById(R.id.reg_password);
        regusertype=(EditText)findViewById(R.id.reg_user_type);
        regaddress=(EditText)findViewById(R.id.reg_address);
        regphone=(EditText)findViewById(R.id.reg_ph_no);
        reg_button=(Button)findViewById(R.id.reg_btn);
        regtextView=(TextView)findViewById(R.id.reg_textView);


        firebaseAuth=FirebaseAuth.getInstance();

        reg_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (validate()){
                    //upload data to database
                    String user_email = regemail.getText().toString().trim();
                    String user_password = regpassword.getText().toString().trim();

                    firebaseAuth.createUserWithEmailAndPassword(user_email,user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()) {
                                sendUserData();
                                Toast.makeText(create_account.this, "Successfully Registered, Upload complete!", Toast.LENGTH_SHORT).show();
                                finish();
                                startActivity(new Intent(create_account.this, login.class));

                            }else{
                                Toast.makeText(create_account.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

        regtextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(create_account.this, login.class));
            }
        });

    }

    private Boolean validate(){
        Boolean result=false;

        name = regname.getText().toString();
        email=regemail.getText().toString();
        password=regpassword.getText().toString();
        usertype=regusertype.getText().toString();
        address=regaddress.getText().toString();
        phone=regphone.getText().toString();

        if(name.isEmpty() || password.isEmpty() || email.isEmpty() || usertype.isEmpty() || address.isEmpty() || phone.isEmpty()){
            Toast.makeText(this, "Please enter all the details", Toast.LENGTH_SHORT).show();
        }else{
            result = true;
        }

        return result;

    }

    private void sendUserData(){
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = firebaseDatabase.getReference("Users");
        Userdata userdata = new Userdata(name, email, usertype, phone, address);

        myRef.child(firebaseAuth.getUid()).setValue(userdata);

    }

}