package com.example.iot;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MainActivity extends AppCompatActivity {

    // Write a message to the database
    FirebaseDatabase fbd;
    DatabaseReference myRef;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText personName = findViewById(R.id.personName);
        Button button = findViewById(R.id.btn);
        fbd= FirebaseDatabase.getInstance();
        myRef = fbd.getReference("info");
        button.setOnClickListener(view -> {
            String name = personName.getText().toString();
            if(TextUtils.isEmpty(name)){
                Toast.makeText(MainActivity.this, "Please add some data", Toast.LENGTH_SHORT).show();
            }
            else{
                myRef.child("Member").setValue(name);
                Toast.makeText(MainActivity.this, "Data Added", Toast.LENGTH_SHORT).show();
            }
        });
    }
}