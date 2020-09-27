package com.example.healthcareandnutritionapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class InsertDataForHeartTips extends AppCompatActivity {
    private Toolbar toolbar;

    EditText heartTipsValue;
    long maxId = 0;

    Button btnInsert;


    //Declaring Firebase Instance
    FirebaseDatabase database;

    //Declaring Firebase Reference
    DatabaseReference databaseReference;

    //Declaring variable of the Member Class
    Model healthyHeartTipsModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_data_for_heart_tips);

        toolbar = findViewById(R.id.healthHeartTips_dataInsert_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(false);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(InsertDataForHeartTips.this, HealthyHeartTips.class));
                finish();
            }
        });


        heartTipsValue = findViewById(R.id.heartTipsValue);
        btnInsert = findViewById(R.id.btnInsert);


        //Initializing variable of the Member class i.e GeneralHealthTips
        healthyHeartTipsModel = new Model();


        //Write a message to the firebase:
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("Health Tips").child("Healthy Heart Tips");
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



        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String healthHeartTipsValue = heartTipsValue.getText().toString();

                if (TextUtils.isEmpty(healthHeartTipsValue)){
                    Toast.makeText(InsertDataForHeartTips.this, "To insert data you'll need to type some value in the field", Toast.LENGTH_LONG).show();

                } else {
                    healthyHeartTipsModel.setHealthTipsValue(healthHeartTipsValue);
                    databaseReference.child(String.valueOf(maxId + 1)).setValue(healthyHeartTipsModel);

                    Toast.makeText(InsertDataForHeartTips.this, "Data Inserted Successfully", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
