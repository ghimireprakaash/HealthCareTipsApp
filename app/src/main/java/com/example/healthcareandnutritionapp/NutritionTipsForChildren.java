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

public class NutritionTipsForChildren extends AppCompatActivity {
    Button buttonBackChildrenTips;

    RecyclerView childrenNutritionRecyclerView;

    // Creating object of RecyclerViewAdapter
    AdapterRecyclerView adapterRecyclerView;


    ProgressBar progressBar_ChildrenNutritionTips;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips_for_children);

        buttonBackChildrenTips = findViewById(R.id.backArrow);
        buttonBackChildrenTips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NutritionTipsForChildren.this, NutritionTips.class));
            }
        });



        progressBar_ChildrenNutritionTips = findViewById(R.id.progressBar_ChildrenNutritionTips);

        childrenNutritionRecyclerView = findViewById(R.id.childrenNutritionRecyclerView);
        childrenNutritionRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<Model> options =
                new FirebaseRecyclerOptions.Builder<Model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Nutrition Tips").child("Nutrition Tips focused for Children"), Model.class)
                        .build();

        adapterRecyclerView = new AdapterRecyclerView(options);

        childrenNutritionRecyclerView.setAdapter(adapterRecyclerView);


        progressBar_ChildrenNutritionTips.setVisibility(View.VISIBLE);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                progressBar_ChildrenNutritionTips.setVisibility(View.GONE);
                onStart();
            }
        }, 2000);




        FloatingActionButton nutritionTipsChildren_fab = findViewById(R.id.nutritionTipsChildren_fab);

        //OnClick fab button, the screen or activity switches to its respective data insert activity
        nutritionTipsChildren_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), InsertDataForChildrenNutritionTips.class));
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();

        adapterRecyclerView.startListening();
    }
}
