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

public class NeededHealthTipsDATAINSERTField extends AppCompatActivity {
    private Toolbar toolbar;
//    EditText generalTipsValue1, generalTipsValue2, generalTipsValue3, generalTipsValue4, generalTipsValue5,
//            generalTipsValue6;

    EditText healthTipsValueInsert;

    long maxId = 0;

    Button btnINSERT;

    //Declaring Firebase Instance
    FirebaseDatabase database;

    //Declaring Firebase Reference
    DatabaseReference databaseReference;

    //Declaring variable of the Member Class
    Model healthTipsModel;

    //OnCreate Method StartUp
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_needed_health_tips_d_a_t_a_i_n_s_e_r_t_field);

        toolbar = findViewById(R.id.healthTips_dataInsert_toolbar);
        setSupportActionBar(toolbar);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(false);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        healthTipsValueInsert = findViewById(R.id.healthTipsValue);
//        generalTipsValue1 = findViewById(R.id.generalTipsValue1);
//        generalTipsValue2 = findViewById(R.id.generalTipsValue2);
//        generalTipsValue3 = findViewById(R.id.generalTipsValue3);
//        generalTipsValue4 = findViewById(R.id.generalTipsValue4);
//        generalTipsValue5 = findViewById(R.id.generalTipsValue5);
//        generalTipsValue6 = findViewById(R.id.generalTipsValue6);

        btnINSERT = findViewById(R.id.btnInsert);

        //Initializing variable of the Member class i.e GeneralHealthTips
        healthTipsModel = new Model();

        //Write a message to the firebase:
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("Health Tips").child("SubClass Health Tips");
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
                String healthTipsValue = healthTipsValueInsert.getText().toString();

                if (TextUtils.isEmpty(healthTipsValue)){
                    Toast.makeText(NeededHealthTipsDATAINSERTField.this, "To insert data you'll need to type some value in the field", Toast.LENGTH_LONG).show();

                } else {
                    healthTipsModel.setHealthTipsValue(healthTipsValue);
                    databaseReference.child(String.valueOf(maxId + 1)).setValue(healthTipsModel);

                    Toast.makeText(NeededHealthTipsDATAINSERTField.this, "Data Inserted Successfully", Toast.LENGTH_SHORT).show();

                    healthTipsValueInsert.setText("");
                }


//                startActivity(new Intent(NeededHealthTipsDATAINSERTField.this, NeededHealthTips.class));
//                finish();
            }
        });
    }

//    private void getValues() {
//        healthTipsInsertData.setGeneralTipsValue1(generalTipsValue1.getText().toString());
//        healthTipsInsertData.setGeneralTipsValue2(generalTipsValue2.getText().toString());
//        healthTipsInsertData.setGeneralTipsValue3(generalTipsValue3.getText().toString());
//        healthTipsInsertData.setGeneralTipsValue4(generalTipsValue4.getText().toString());
//        healthTipsInsertData.setGeneralTipsValue5(generalTipsValue5.getText().toString());
//        healthTipsInsertData.setGeneralTipsValue6(generalTipsValue6.getText().toString());
//    }

//    public void InsertData(View view) {
//        /*The below process to insert data is the manual process for inserting data as
//            the edit text fields more the one or two are created where the data's are to
//            be typed manually and hence clicked insert button to insert the data
//         */
//
//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                getValues();
//                databaseReference.child("HealthTips").child("GeneralHealthTips").setValue(healthTipsInsertData);
//                Toast.makeText(GeneralHealthTips.this, "Data Inserted Successfully", Toast.LENGTH_LONG).show();
//
//                String generalTips1 = generalTipsValue1.getText().toString();
//                String generalTips2 = generalTipsValue2.getText().toString();
//                String generalTips3 = generalTipsValue3.getText().toString();
//                String generalTips4 = generalTipsValue4.getText().toString();
//                String generalTips5 = generalTipsValue5.getText().toString();
//                String generalTips6 = generalTipsValue6.getText().toString();
//
//                //Using Intent to pass the data to HealthTips.
//                Intent intent = new Intent(GeneralHealthTips.this, HealthTips.class);
//                intent.putExtra("generalTipsValue1", generalTips1);
//                intent.putExtra("generalTipsValue2", generalTips2);
//                intent.putExtra("generalTipsValue3", generalTips3);
//                intent.putExtra("generalTipsValue4", generalTips4);
//                intent.putExtra("generalTipsValue5", generalTips5);
//                intent.putExtra("generalTipsValue6", generalTips6);
//
//                //Calling startActivity to call intent.
//                startActivity(intent);
//                finish();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//
//
//
//
//        healthTipsInsertData.setGeneralTipsValue1(generalTipsValue1.getText().toString());
//        healthTipsInsertData.setGeneralTipsValue2(generalTipsValue2.getText().toString());
//        healthTipsInsertData.setGeneralTipsValue3(generalTipsValue3.getText().toString());
//        healthTipsInsertData.setGeneralTipsValue4(generalTipsValue4.getText().toString());
//        healthTipsInsertData.setGeneralTipsValue5(generalTipsValue5.getText().toString());
//        healthTipsInsertData.setGeneralTipsValue6(generalTipsValue6.getText().toString());
//    }
}
