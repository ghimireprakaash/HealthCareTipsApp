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

public class InsertDataForSeasonalHealthTips extends AppCompatActivity {
    EditText seasonalTipsValue;

    long maxId = 0;

    Button btnINSERT;

    //Declaring Firebase Instance
    FirebaseDatabase database;

    //Declaring Firebase Reference
    DatabaseReference databaseReference;

    //Declaring variable of the Member Class
    Model seasonalHealthTipsModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_data_for_seasonal_health_tips);

        seasonalTipsValue = findViewById(R.id.seasonalTipsValue);
        btnINSERT = findViewById(R.id.btnInsert);


        //Initializing variable of the Member class i.e GeneralHealthTips
        seasonalHealthTipsModel = new Model();


        //Write a message to the firebase:
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("Health Tips").child("Seasonal Health Tips");
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
                String seasonalTips = seasonalTipsValue.getText().toString();

                if (TextUtils.isEmpty(seasonalTips)) {
                    Toast.makeText(InsertDataForSeasonalHealthTips.this, "To insert data you'll need to type some value on the field", Toast.LENGTH_LONG).show();

                } else {
                    seasonalHealthTipsModel.setSeasonalHealthTipsValue(seasonalTips);
                    databaseReference.child(String.valueOf(maxId + 1)).setValue(seasonalHealthTipsModel);

                    Toast.makeText(InsertDataForSeasonalHealthTips.this, "Data Inserted Successfully", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
