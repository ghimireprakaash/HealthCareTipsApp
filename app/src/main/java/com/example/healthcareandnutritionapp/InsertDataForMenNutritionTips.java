package com.example.healthcareandnutritionapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
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

public class InsertDataForMenNutritionTips extends AppCompatActivity {

    EditText nutritionTipsForMen;

    long maxId = 0;

    Button btnINSERT;

    //Declaring Firebase Instance
    FirebaseDatabase database;

    //Declaring Firebase Reference
    DatabaseReference databaseReference;

    //Declaring variable of the Member Class
    Model healthTipsModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_data_for_men_nutrition_tips);

        nutritionTipsForMen = findViewById(R.id.nutritionTipsForMen);
        btnINSERT = findViewById(R.id.btnInsert);

        //Initializing variable of the Member class i.e GeneralHealthTips
        healthTipsModel = new Model();

        //Write a message to the firebase:
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("Nutrition Tips").child("Nutrition Tips focused for Men");
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
                String nutritionTipsForMenValue = nutritionTipsForMen.getText().toString();

                if (TextUtils.isEmpty(nutritionTipsForMenValue)) {
                    Toast.makeText(InsertDataForMenNutritionTips.this, "To insert data you'll need to type some value on the field", Toast.LENGTH_LONG).show();

                } else {
                    healthTipsModel.setNutritionTipsForMenValue(nutritionTipsForMenValue);
                    databaseReference.child(String.valueOf(maxId + 1)).setValue(healthTipsModel);
                    Toast.makeText(InsertDataForMenNutritionTips.this, "Data Inserted Successfully", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
