package com.example.healthcareandnutritionapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

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

import java.util.Objects;

public class InsertDataForWomenNutritionTips extends AppCompatActivity {
    private Toolbar toolbar;

    EditText nutritionTipsForWomen;

    long maxId = 0;

    Button btnINSERT;

    //Declaring Firebase Instance
    FirebaseDatabase database;

    //Declaring Firebase Reference
    DatabaseReference databaseReference;

    //Declaring variable of the Member Class
    Model womenNutritionTipsModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_data_for_women_nutrition_tips);

        toolbar = findViewById(R.id.womenNutrition_dataInsert_toolbar);
        setSupportActionBar(toolbar);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(false);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        nutritionTipsForWomen = findViewById(R.id.womenNutritionTipsValue);
        btnINSERT = findViewById(R.id.btnInsert);


        //Initializing variable of the Member class i.e GeneralHealthTips
        womenNutritionTipsModel = new Model();


        //Write a message to the firebase:
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("Nutrition Tips").child("Nutrition Tips focused for Women");
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
                String nutritionTipsForWomenValue = nutritionTipsForWomen.getText().toString();

                if (TextUtils.isEmpty(nutritionTipsForWomenValue)) {
                    Toast.makeText(InsertDataForWomenNutritionTips.this, "To insert data you'll need to type some value on the field", Toast.LENGTH_LONG).show();

                } else {
                    womenNutritionTipsModel.setNutritionTipsForWomenValue(nutritionTipsForWomenValue);
                    databaseReference.child(String.valueOf(maxId + 1)).setValue(womenNutritionTipsModel);
                    Toast.makeText(InsertDataForWomenNutritionTips.this, "Data Inserted Successfully", Toast.LENGTH_SHORT).show();

                    nutritionTipsForWomen.setText("");
                }
            }
        });
    }
}
