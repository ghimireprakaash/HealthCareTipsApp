package com.example.healthcareandnutritionapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class RelieveStressTips extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relieve_stress_tips);

        FloatingActionButton relieveStress_fab = findViewById(R.id.relieveStress_fab);

        //OnClick fab button, the screen or activity switches to its respective data insert activity
        relieveStress_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), InsertDataForRelievingStress.class);
                startActivity(intent);
            }
        });
    }
}
