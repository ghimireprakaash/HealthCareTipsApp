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
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;

public class NutritionForGainingWeight extends AppCompatActivity {
    Button buttonBackGainingWeight;
    RecyclerView weightGainNutritionRecyclerView;

    // Creating object of RecyclerViewAdapter
    AdapterRecyclerView adapterRecyclerView;


    ProgressBar progressBar_WeightGainNutritionTips;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutrition_for_gaining_weight);

        buttonBackGainingWeight = findViewById(R.id.backArrow);
        buttonBackGainingWeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NutritionForGainingWeight.this, MainActivity.class));
            }
        });


        progressBar_WeightGainNutritionTips = findViewById(R.id.progressBar_WeightGainNutritionTips);

        weightGainNutritionRecyclerView = findViewById(R.id.weightGainNutritionRecyclerView);
        weightGainNutritionRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<Model> options =
                new FirebaseRecyclerOptions.Builder<Model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Nutrition Tips").child("Tips for Gaining Weight"), Model.class)
                        .build();

        adapterRecyclerView = new AdapterRecyclerView(options);

        weightGainNutritionRecyclerView.setAdapter(adapterRecyclerView);


        progressBar_WeightGainNutritionTips.setVisibility(View.VISIBLE);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                progressBar_WeightGainNutritionTips.setVisibility(View.GONE);
                onStart();
            }
        }, 2000);



        FloatingActionButton nutritionTipsWeightGain_fab = findViewById(R.id.nutritionTipsWeightGain_fab);

        //OnClick fab button, the screen or activity switches to its respective data insert activity
        nutritionTipsWeightGain_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), InsertDataForGainingWeight.class));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        adapterRecyclerView.startListening();
    }
}
