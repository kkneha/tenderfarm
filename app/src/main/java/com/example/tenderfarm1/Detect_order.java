package com.example.tenderfarm1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Detect_order extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detect_order);

        String product_name="";
        String product_id="";
        String product_price="";
        int inc=1;

        FirebaseDatabase database=FirebaseDatabase.getInstance();

        DatabaseReference myref=database.getReference("Orders");
        DatabaseReference ch;
/*
        check_order(disease);
        ch=myref.child("order"+inc);
        ch.child("product_name").setValue(product_name);
        ch.child("product_id").setValue(product_id);
        ch.child("product_price").setValue(product_price);
        inc++;

    }
    public String check_order(String t){
        if (t =="a") {
            product_name="aaa";
            product_id="111";
            product_price="999";
        } else if(t=="b")
        {
            product_name="bbb";
            product_id="222";
            product_price="888";
        }
        return product_name, product_id,product_price;*/
    }
}
