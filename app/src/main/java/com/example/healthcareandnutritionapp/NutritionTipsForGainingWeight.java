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

public class NutritionTipsForGainingWeight extends AppCompatActivity {
    Button buttonBackWeightGainTips;

    RecyclerView weightGainNutritionRecyclerView;

    // Creating object of RecyclerViewAdapter
    AdapterRecyclerView adapterRecyclerView;

    ProgressBar weightGainNutritionTips_ProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutrition_tips_for_gaining_weight);


        buttonBackWeightGainTips = findViewById(R.id.buttonBackWeightGainTips);
        buttonBackWeightGainTips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NutritionTipsForGainingWeight.this, NutritionTips.class));
            }
        });



        weightGainNutritionTips_ProgressBar = findViewById(R.id.weightGainTips_ProgressBar);


        weightGainNutritionRecyclerView = findViewById(R.id.weightGainNutritionRecyclerView);
        weightGainNutritionRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<Model> options =
                new FirebaseRecyclerOptions.Builder<Model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Nutrition Tips").child("Tips for Gaining Weight"), Model.class)
                        .build();

        adapterRecyclerView = new AdapterRecyclerView(options);

        weightGainNutritionTips_ProgressBar.setVisibility(View.VISIBLE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                weightGainNutritionTips_ProgressBar.setVisibility(View.GONE);
                weightGainNutritionRecyclerView.setAdapter(adapterRecyclerView);
            }
        },2000);
    }

    @Override
    protected void onStart() {
        super.onStart();

        adapterRecyclerView.startListening();
    }
}
