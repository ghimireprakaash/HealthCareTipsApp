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

public class NutritionTipsForMen extends AppCompatActivity {
    Button buttonBackMenTips;

    RecyclerView menNutritionRecyclerView;


    // Creating object of RecyclerViewAdapter
    AdapterRecyclerView adapterRecyclerView;

    ProgressBar menNutritionTips_ProgressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutrition_tips_for_men);

        buttonBackMenTips = findViewById(R.id.buttonBackMenTips);
        buttonBackMenTips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NutritionTipsForMen.this, NutritionTips.class));
            }
        });



        menNutritionTips_ProgressBar = findViewById(R.id.menNutritionTips_ProgressBar);


        menNutritionRecyclerView = findViewById(R.id.menNutritionRecyclerView);
        menNutritionRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<Model> options =
                new FirebaseRecyclerOptions.Builder<Model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Nutrition Tips").child("Nutrition Tips focused for Men"), Model.class)
                        .build();

        adapterRecyclerView = new AdapterRecyclerView(options);

        menNutritionTips_ProgressBar.setVisibility(View.VISIBLE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                menNutritionTips_ProgressBar.setVisibility(View.GONE);
                menNutritionRecyclerView.setAdapter(adapterRecyclerView);
            }
        },2000);

    }

    @Override
    protected void onStart() {
        super.onStart();

        adapterRecyclerView.startListening();
    }
}
