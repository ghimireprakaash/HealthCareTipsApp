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

public class NutritionTipsForChildren extends AppCompatActivity {
    Button buttonBackChildrenTips;

    RecyclerView childrenNutritionRecyclerView;

    // Creating object of RecyclerViewAdapter
    AdapterRecyclerView adapterRecyclerView;

    ProgressBar childrenNutritionTips_ProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutrition_tips_for_children);


        buttonBackChildrenTips = findViewById(R.id.buttonBackChildrenTips);
        buttonBackChildrenTips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NutritionTipsForChildren.this, NutritionTips.class));
            }
        });



        childrenNutritionTips_ProgressBar = findViewById(R.id.childrenNutritionTips_ProgressBar);


        childrenNutritionRecyclerView = findViewById(R.id.childrenNutritionRecyclerView);
        childrenNutritionRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<Model> options =
                new FirebaseRecyclerOptions.Builder<Model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Nutrition Tips").child("Nutrition Tips focused for Children"), Model.class)
                        .build();

        adapterRecyclerView = new AdapterRecyclerView(options);

        childrenNutritionTips_ProgressBar.setVisibility(View.VISIBLE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                childrenNutritionTips_ProgressBar.setVisibility(View.GONE);
                childrenNutritionRecyclerView.setAdapter(adapterRecyclerView);
            }
        },2000);
    }

    @Override
    protected void onStart() {
        super.onStart();

        adapterRecyclerView.startListening();
    }
}
