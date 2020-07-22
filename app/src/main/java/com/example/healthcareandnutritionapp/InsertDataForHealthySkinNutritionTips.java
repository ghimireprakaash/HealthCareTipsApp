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

public class InsertDataForHealthySkinNutritionTips extends AppCompatActivity {
    public EditText healthySkinTips;

    long maxId = 0;

    Button btnINSERT;

    //Declaring Firebase Instance
    FirebaseDatabase database;

    //Declaring Firebase Reference
    DatabaseReference databaseReference;

    //Declaring variable of the Member Class
    Model healthySkinNutritionTipsModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_data_for_healthy_skin_nutrition_tips);

        healthySkinTips = findViewById(R.id.healthySkinNutritionTipsValue);
        btnINSERT = findViewById(R.id.btnInsert);


        //Initializing variable of the Member class i.e GeneralHealthTips
        healthySkinNutritionTipsModel = new Model();


        //Write a message to the firebase:
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("Nutrition Tips").child("Healthy Skin Nutrition Tips");
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
                String skinTipsValue = healthySkinTips.getText().toString();

                if (TextUtils.isEmpty(skinTipsValue)) {
                    Toast.makeText(InsertDataForHealthySkinNutritionTips.this, "To insert data you'll need to type some value on the field", Toast.LENGTH_LONG).show();

                } else {
                    healthySkinNutritionTipsModel.setNutritionTipsForHealthySkinValue(skinTipsValue);
                    databaseReference.child(String.valueOf(maxId + 1)).setValue(healthySkinNutritionTipsModel);
                    Toast.makeText(InsertDataForHealthySkinNutritionTips.this, "Data Inserted Successfully", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
