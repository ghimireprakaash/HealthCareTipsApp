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

public class NutritionTipsForWomen extends AppCompatActivity {
    Button buttonBackWomenTips;

    RecyclerView womenNutritionRecyclerView;

    // Creating object of RecyclerViewAdapter
    AdapterRecyclerView adapterRecyclerView;

    ProgressBar womenNutritionTips_ProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutrition_tips_for_women);

        buttonBackWomenTips = findViewById(R.id.buttonBackWomenTips);
        buttonBackWomenTips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NutritionTipsForWomen.this, NutritionTips.class));
            }
        });



        womenNutritionTips_ProgressBar = findViewById(R.id.womenNutritionTips_ProgressBar);


        womenNutritionRecyclerView = findViewById(R.id.womenNutritionRecyclerView);
        womenNutritionRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<Model> options =
                new FirebaseRecyclerOptions.Builder<Model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Nutrition Tips").child("Nutrition Tips focused for Women"), Model.class)
                        .build();

        adapterRecyclerView = new AdapterRecyclerView(options);

        womenNutritionTips_ProgressBar.setVisibility(View.VISIBLE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                womenNutritionTips_ProgressBar.setVisibility(View.GONE);
                womenNutritionRecyclerView.setAdapter(adapterRecyclerView);
            }
        },2000);
    }

    @Override
    protected void onStart() {
        super.onStart();

        adapterRecyclerView.startListening();
    }
}
