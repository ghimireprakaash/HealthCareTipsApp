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

public class NutritionTipsForSkin extends AppCompatActivity {
    Button buttonBackHealthySkinTips;

    RecyclerView skinNutritionRecyclerView;

    // Creating object of RecyclerViewAdapter
    AdapterRecyclerView adapterRecyclerView;


    ProgressBar progressBar_SkinNutritionTips;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutrition_tips_for_skin);

        buttonBackHealthySkinTips = findViewById(R.id.backArrow);
        buttonBackHealthySkinTips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NutritionTipsForSkin.this, NutritionTips.class));
            }
        });


        progressBar_SkinNutritionTips = findViewById(R.id.progressBar_SkinNutritionTips);

        skinNutritionRecyclerView = findViewById(R.id.skinNutritionRecyclerView);
        skinNutritionRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<Model> options =
                new FirebaseRecyclerOptions.Builder<Model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Nutrition Tips").child("Healthy Skin Nutrition Tips"), Model.class)
                        .build();

        adapterRecyclerView = new AdapterRecyclerView(options);

        skinNutritionRecyclerView.setAdapter(adapterRecyclerView);


        progressBar_SkinNutritionTips.setVisibility(View.VISIBLE);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                progressBar_SkinNutritionTips.setVisibility(View.GONE);
                onStart();
            }
        }, 2000);

        FloatingActionButton nutritionTipsSkin_fab = findViewById(R.id.nutritionTipsSkin_fab);

        //OnClick fab button, the screen or activity switches to its respective data insert activity
        nutritionTipsSkin_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), InsertDataForHealthySkinNutritionTips.class));
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();

        adapterRecyclerView.startListening();
    }
}
