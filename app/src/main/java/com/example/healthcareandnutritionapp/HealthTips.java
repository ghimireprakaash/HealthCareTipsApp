package com.example.healthcareandnutritionapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HealthTips extends AppCompatActivity {
    private Toolbar toolbar;

    TextView health_Tips, menHealthTips, womenHealthTips, heart_Tips, relieve_Stress, seasonal_Tips;

    //Body TextViews where data that are inserted to be shown...
    //TextView resultGeneralTips1, resultGeneralTips2, resultGeneralTips3, resultGeneralTips4, resultGeneralTips5,
    //        resultGeneralTips6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_tips);

        toolbar = findViewById(R.id.healthTips_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(false);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HealthTips.this, MainActivity.class));
                finish();
            }
        });



        health_Tips = findViewById(R.id.generalHealthTips);
        menHealthTips = findViewById(R.id.menHealthTips);
        womenHealthTips = findViewById(R.id.womenHealthTips);
        heart_Tips = findViewById(R.id.tipsHeart);
        relieve_Stress = findViewById(R.id.stressTips);
        seasonal_Tips = findViewById(R.id.seasonTips);






        //OnClick event is called for generalHealthTips i.e it switched to its respective activity.
        health_Tips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HealthTips.this, NeededHealthTips.class));
            }
        });


        menHealthTips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HealthTips.this, HealthTipsForMen.class));
            }
        });


        womenHealthTips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HealthTips.this, HealthTipsForWomen.class));
            }
        });


        heart_Tips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HealthTips.this, HealthyHeartTips.class));
            }
        });


        relieve_Stress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HealthTips.this, RelieveStressTips.class));
            }
        });


        seasonal_Tips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HealthTips.this, SeasonalTips.class));
            }
        });



        //general_Tips : Taking findViewById as a Reference for a TextView to connect to java class ko xml layout.
//        resultGeneralTips1 = findViewById(R.id.resultGeneralTips1);
//        resultGeneralTips2 = findViewById(R.id.resultGeneralTips2);
//        resultGeneralTips3 = findViewById(R.id.resultGeneralTips3);
//        resultGeneralTips4 = findViewById(R.id.resultGeneralTips4);
//        resultGeneralTips5 = findViewById(R.id.resultGeneralTips5);
//        resultGeneralTips6 = findViewById(R.id.resultGeneralTips6);

//        general_Tips.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                myRef = FirebaseDatabase.getInstance().getReference().child("HealthTips").child("GeneralHealthTips");
//                myRef.addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                        String generalTips1 = dataSnapshot.child("generalTipsValue1").getValue().toString();
//                        String generalTips2 = dataSnapshot.child("generalTipsValue2").getValue().toString();
//                        String generalTips3 = dataSnapshot.child("generalTipsValue3").getValue().toString();
//                        String generalTips4 = dataSnapshot.child("generalTipsValue4").getValue().toString();
//                        String generalTips5 = dataSnapshot.child("generalTipsValue5").getValue().toString();
//                        String generalTips6 = dataSnapshot.child("generalTipsValue6").getValue().toString();
//
//                        resultGeneralTips1.setText(generalTips1);
//                        resultGeneralTips2.setText(generalTips2);
//                        resultGeneralTips3.setText(generalTips3);
//                        resultGeneralTips4.setText(generalTips4);
//                        resultGeneralTips5.setText(generalTips5);
//                        resultGeneralTips6.setText(generalTips6);
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                    }
//                });
//            }
//        });


//        //Calling Intent to get the data from the activity GeneralHealthTips.
//        Intent intent = getIntent();
//
//        String generalTips1 = intent.getStringExtra("generalTipsValue1");
//        String generalTips2 = intent.getStringExtra("generalTipsValue2");
//        String generalTips3 = intent.getStringExtra("generalTipsValue3");
//        String generalTips4 = intent.getStringExtra("generalTipsValue4");
//        String generalTips5 = intent.getStringExtra("generalTipsValue5");
//        String generalTips6 = intent.getStringExtra("generalTipsValue6");
//
//        //Now adding data to the TextView.
//        resultGeneralTips1.setText(generalTips1);
//        resultGeneralTips2.setText(generalTips2);
//        resultGeneralTips3.setText(generalTips3);
//        resultGeneralTips4.setText(generalTips4);
//        resultGeneralTips5.setText(generalTips5);
//        resultGeneralTips6.setText(generalTips6);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent = new Intent(HealthTips.this, MainActivity.class);
        startActivity(intent);
//        overridePendingTransition(R.anim.slide_out_left, R.anim.slide_in_right);
    }
}