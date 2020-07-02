package com.example.healthcareandnutritionapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class GeneralNutrition extends AppCompatActivity {
    Button buttonBackGeneralNutritionTips;

    RecyclerView nutritionTipsRecyclerView;

    // Creating object of RecyclerViewAdapter
    AdapterRecyclerView adapterRecyclerView;

    ProgressBar generalNutritionTips_ProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general_nutrition);

        buttonBackGeneralNutritionTips = findViewById(R.id.buttonBackGeneralNutritionTips);
        buttonBackGeneralNutritionTips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GeneralNutrition.this, NutritionTips.class));
            }
        });



        generalNutritionTips_ProgressBar = findViewById(R.id.generalNutritionTips_ProgressBar);

        nutritionTipsRecyclerView = findViewById(R.id.nutritionTipsRecyclerView);
        nutritionTipsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<Model> options =
                new FirebaseRecyclerOptions.Builder<Model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Nutrition Tips").child("General Nutrition Tips"), Model.class)
                        .build();

        adapterRecyclerView = new AdapterRecyclerView(options);

        generalNutritionTips_ProgressBar.setVisibility(View.VISIBLE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                generalNutritionTips_ProgressBar.setVisibility(View.GONE);
                nutritionTipsRecyclerView.setAdapter(adapterRecyclerView);
            }
        },2000);

    }

    @Override
    protected void onStart() {
        super.onStart();

        adapterRecyclerView.startListening();
    }
}
