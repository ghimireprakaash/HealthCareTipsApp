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

public class NutritionTipsForHair extends AppCompatActivity {
    Button buttonBackHealthyHairTips;

    RecyclerView hairNutritionRecyclerView;

    // Creating object of RecyclerViewAdapter
    AdapterRecyclerView adapterRecyclerView;


    ProgressBar progressBar_HairNutritionTips;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutrition_tips_for_hair);

        buttonBackHealthyHairTips = findViewById(R.id.backArrow);
        buttonBackHealthyHairTips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NutritionTipsForHair.this, NutritionTips.class));
            }
        });


        progressBar_HairNutritionTips = findViewById(R.id.progressBar_HairNutritionTips);

        hairNutritionRecyclerView = findViewById(R.id.hairNutritionRecyclerView);
        hairNutritionRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<Model> options =
                new FirebaseRecyclerOptions.Builder<Model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Nutrition Tips").child("Healthy Hair"), Model.class)
                        .build();

        adapterRecyclerView = new AdapterRecyclerView(options);

        hairNutritionRecyclerView.setAdapter(adapterRecyclerView);


        progressBar_HairNutritionTips.setVisibility(View.VISIBLE);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                progressBar_HairNutritionTips.setVisibility(View.GONE);
                onStart();
            }
        }, 2000);

        FloatingActionButton nutritionTipsHair_fab = findViewById(R.id.nutritionTipsHair_fab);

        //OnClick fab button, the screen or activity switches to its respective data insert activity
        nutritionTipsHair_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), InsertDataForHealthyHairNutritionTips.class));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        adapterRecyclerView.startListening();
    }
}
