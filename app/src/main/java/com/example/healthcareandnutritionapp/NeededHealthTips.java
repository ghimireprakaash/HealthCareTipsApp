package com.example.healthcareandnutritionapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class NeededHealthTips extends AppCompatActivity {
//    EditText generalTipsValue1, generalTipsValue2, generalTipsValue3, generalTipsValue4, generalTipsValue5, generalTipsValue6;

    //OnCreate Method StartUp
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_needed_health_tips);

        FloatingActionButton generalHealthTips_fab = findViewById(R.id.generalHealthTips_fab);

        //OnClick fab button, the screen or activity switches to its respective data insert activity
        generalHealthTips_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), NeededHealthTipsDATAINSERTField.class);
                startActivity(intent);
            }
        });

//        generalTipsValue1 = findViewById(R.id.generalTipsValue1);
//        generalTipsValue2 = findViewById(R.id.generalTipsValue2);
//        generalTipsValue3 = findViewById(R.id.generalTipsValue3);
//        generalTipsValue4 = findViewById(R.id.generalTipsValue4);
//        generalTipsValue5 = findViewById(R.id.generalTipsValue5);
//        generalTipsValue6 = findViewById(R.id.generalTipsValue6);

    }
}
