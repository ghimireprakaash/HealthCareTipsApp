package com.example.healthcareandnutritionapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class InsertDataForRelievingStress extends AppCompatActivity {
    EditText stressRelieveValue;

    long maxId = 0;

    Button btnINSERT;

    //Declaring Firebase Instance
    FirebaseDatabase database;

    //Declaring Firebase Reference
    DatabaseReference databaseReference;

    //Declaring variable of the Member Class
    HealthTips__Model healthTipsModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_data_for_relieving_stress);

        stressRelieveValue = findViewById(R.id.stressRelieveValue);
        btnINSERT = findViewById(R.id.btnInsert);

        //Initializing variable of the Member class i.e GeneralHealthTips
        healthTipsModel = new HealthTips__Model();

        //Write a message to the firebase:
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("HealthTips").child("StressRelieve");
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
                String stressRelieveTips = stressRelieveValue.getText().toString();
                healthTipsModel.setStressReliefTipsValue(stressRelieveTips);
                databaseReference.child(String.valueOf(maxId + 1)).setValue(healthTipsModel);

                Toast.makeText(InsertDataForRelievingStress.this, "Data Inserted Successfully", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
