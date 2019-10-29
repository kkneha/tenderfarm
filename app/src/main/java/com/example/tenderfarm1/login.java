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
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class login extends AppCompatActivity {

    private EditText Loginid1,password1;
    private Button Loginbutton;
    private TextView info,textView;
    private int counter=5;
    public RadioGroup rg;
    public RadioButton rb;
    ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        Loginid1 = (EditText)findViewById(R.id.login_ph_no);
        password1 = (EditText)findViewById(R.id.login_password);
        info = (TextView) findViewById(R.id.login_info);
        textView = (TextView) findViewById(R.id.textView);
        Loginbutton = (Button) findViewById(R.id.login_btn);

        progressDialog = new ProgressDialog(this);
        info.setText("no. of attempts remaining:5");

        firebaseAuth=FirebaseAuth.getInstance();

        FirebaseUser user = firebaseAuth.getCurrentUser();

        if(user!=null){
            finish();
            startActivity(new Intent(login.this,second.class));
        }

        Loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(Loginid1.getText().toString(),password1.getText().toString());
                startActivity(new Intent(login.this, second.class));
            }
        });

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(login.this, create_account.class));
            }
        });

    }

    private void validate(String userName, String userPassword) {

        progressDialog.setMessage("Logging you in !!!");
        progressDialog.show();
        firebaseAuth.signInWithEmailAndPassword(userName, userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    progressDialog.dismiss();
                    //Toast.makeText(login.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(login.this,second.class));


                }else{
                    Toast.makeText(login.this, "Login Failed", Toast.LENGTH_SHORT).show();
                    counter--;
                    info.setText("No of attempts remaining: " + counter);
                    progressDialog.dismiss();
                    if(counter == 0){
                        Loginbutton.setEnabled(false);
                    }
                }
            }
        });

    }

}

