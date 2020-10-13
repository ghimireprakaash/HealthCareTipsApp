package com.example.healthcareandnutritionapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;

public class NeededNutritionTips extends AppCompatActivity {
    private Toolbar toolbar;

    RecyclerView nutritionTipsRecyclerView;

    // Creating object of RecyclerViewAdapter
    AdapterRecyclerView adapterRecyclerView;


    ProgressBar progressBar_NutritionTips;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_needed_nutrition_tips);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(false);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NeededNutritionTips.this, NutritionTips.class));
                finish();
            }
        });


        progressBar_NutritionTips = findViewById(R.id.progressBar_NutritionTips);

        nutritionTipsRecyclerView = findViewById(R.id.nutritionTipsRecyclerView);
        nutritionTipsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<Model> options =
                new FirebaseRecyclerOptions.Builder<Model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Nutrition Tips").child("General Nutrition Tips"), Model.class)
                        .build();

        adapterRecyclerView = new AdapterRecyclerView(options);



        progressBar_NutritionTips.setVisibility(View.VISIBLE);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                progressBar_NutritionTips.setVisibility(View.GONE);

                nutritionTipsRecyclerView.setAdapter(adapterRecyclerView);

            }
        }, 1000);




        FloatingActionButton generalNutritionTips_fab = findViewById(R.id.generalNutritionTips_fab);

        //OnClick fab button, the screen or activity switches to its respective data insert activity
        generalNutritionTips_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), InsertDataForNutritionTips.class));
                finish();
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();

        adapterRecyclerView.startListening();
    }
}
