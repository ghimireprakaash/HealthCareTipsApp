package com.example.healthcareandnutritionapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class InsertDataForWomenHealthTips extends AppCompatActivity {
    Button buttonBack;

    EditText womenHealthTips;

    long maxId = 0;

    Button btnINSERT;


    //Declaring Firebase Instance
    FirebaseDatabase database;


    //Declaring Firebase Reference
    DatabaseReference databaseReference;


    //Declaring variable of the Member Class
    Model womenHealthTipsModel;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_data_for_women_health_tips);

        buttonBack = findViewById(R.id.backArrow);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(InsertDataForWomenHealthTips.this, HealthTipsForWomen.class));
            }
        });


        womenHealthTips = findViewById(R.id.womenHealthTipsValue);
        btnINSERT = findViewById(R.id.btnInsert);


        //Initializing variable of the Member class i.e GeneralHealthTips
        womenHealthTipsModel = new Model();


        //Write a message to the firebase:
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("Health Tips").child("Health Tips focused for Women");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    maxId = dataSnapshot.getChildrenCount();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        btnINSERT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String healthTipsForMenValue = womenHealthTips.getText().toString();

                if (TextUtils.isEmpty(healthTipsForMenValue)) {
                    Toast.makeText(InsertDataForWomenHealthTips.this, "To insert data you'll need to type some value on the field", Toast.LENGTH_LONG).show();

                } else {
                    womenHealthTipsModel.setWomenHealthTipsValue(healthTipsForMenValue);
                    databaseReference.child(String.valueOf(maxId + 1)).setValue(womenHealthTipsModel);
                    Toast.makeText(InsertDataForWomenHealthTips.this, "Data Inserted Successfully", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}