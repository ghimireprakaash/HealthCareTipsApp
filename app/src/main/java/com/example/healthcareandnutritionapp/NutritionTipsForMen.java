package com.example.healthcareandnutritionapp;

import androidx.appcompat.app.AppCompatActivity;
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

public class NutritionTipsForMen extends AppCompatActivity {
    RecyclerView menNutritionRecyclerView;


    // Creating object of RecyclerViewAdapter
    AdapterRecyclerView adapterRecyclerView;


    ProgressBar progressBar_MenNutritionTips;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips_for_men);

        progressBar_MenNutritionTips = findViewById(R.id.progressBar_MenNutritionTips);

        menNutritionRecyclerView = findViewById(R.id.menNutritionRecyclerView);
        menNutritionRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<Model> options =
                new FirebaseRecyclerOptions.Builder<Model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Nutrition Tips").child("Nutrition Tips focused for Men"), Model.class)
                        .build();

        adapterRecyclerView = new AdapterRecyclerView(options);

        menNutritionRecyclerView.setAdapter(adapterRecyclerView);


        progressBar_MenNutritionTips.setVisibility(View.VISIBLE);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                progressBar_MenNutritionTips.setVisibility(View.GONE);
                onStart();
            }
        }, 2000);




        FloatingActionButton nutritionTipsMen_fab = findViewById(R.id.nutritionTipsMen_fab);

        //OnClick fab button, the screen or activity switches to its respective data insert activity
        nutritionTipsMen_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), InsertDataForMenNutritionTips.class));
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();

        adapterRecyclerView.startListening();
    }
}
