package com.example.iot;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Dictionary;


public class MainActivity extends AppCompatActivity {

    // Write a message to the database
    FirebaseDatabase fbd;
    DatabaseReference myRef;
    DatabaseReference nameRef;
    DatabaseReference userRef ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText personName = findViewById(R.id.personName);
        EditText age = findViewById(R.id.dobIn);
        EditText height = findViewById(R.id.heightIn);
        Button button = findViewById(R.id.btn);
        TextView TextBox = findViewById(R.id.nameBox);
        TextView DobBox = findViewById(R.id.dobBox);
        TextView HeightBox = findViewById(R.id.heightBox);
        fbd= FirebaseDatabase.getInstance();
        myRef = fbd.getReference("info");
        nameRef = fbd.getReference("name");
        userRef = fbd.getReference("users");
        button.setOnClickListener(view -> {
            String name = personName.getText().toString();
            String dob =  age.getText().toString();
            String h = height.getText().toString();
//C2
            if(TextUtils.isEmpty(name)){
                Toast.makeText(MainActivity.this, "add some data", Toast.LENGTH_SHORT).show();
            }
            else{
//                myRef.child("Member").setValue(name);
                userRef.child("name").setValue(name);
                userRef.child("DOB").setValue(dob);
                userRef.child("height").setValue(h);
                Toast.makeText(MainActivity.this, "Data Added", Toast.LENGTH_SHORT).show();
            }
        });

        userRef.child("name").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                }
                else {
                    Log.d("firebase", String.valueOf(task.getResult().getValue()));
                    TextBox.setText(String.valueOf(task.getResult().getValue()));
                }
            }
        });

        userRef.child("DOB").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                }
                else {
                    Log.d("firebase", String.valueOf(task.getResult().getValue()));
                    DobBox.setText(String.valueOf(task.getResult().getValue()));
                }
            }
        });
        userRef.child("height").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                }
                else {
                    Log.d("firebase", String.valueOf(task.getResult().getValue()));
                    HeightBox.setText(String.valueOf(task.getResult().getValue()));
                }
            }
        });
    }
}