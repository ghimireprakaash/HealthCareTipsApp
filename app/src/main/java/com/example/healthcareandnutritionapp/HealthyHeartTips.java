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

public class HealthyHeartTips extends AppCompatActivity {
    Button buttonBackHealthyHeartTips;
    RecyclerView heartTipsRecyclerView;

    // Creating object of RecyclerViewAdapter
    AdapterRecyclerView adapterRecyclerView;


    ProgressBar progressBar_HeartTips;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_healthy_heart_tips);

        buttonBackHealthyHeartTips = findViewById(R.id.backArrow);
        buttonBackHealthyHeartTips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HealthyHeartTips.this, HealthTips.class));
            }
        });


        progressBar_HeartTips = findViewById(R.id.progressBar_HeartTips);

        heartTipsRecyclerView = findViewById(R.id.heartTipsRecyclerView);
        heartTipsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        heartTipsRecyclerView = findViewById(R.id.heartTipsRecyclerView);
        heartTipsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<Model> options =
                new FirebaseRecyclerOptions.Builder<Model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Health Tips").child("Healthy Heart Tips"), Model.class)
                        .build();


        adapterRecyclerView = new AdapterRecyclerView(options);
        heartTipsRecyclerView.setAdapter(adapterRecyclerView);



        progressBar_HeartTips.setVisibility(View.VISIBLE);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                progressBar_HeartTips.setVisibility(View.GONE);
                onStart();
            }
        }, 2000);





        FloatingActionButton heartTips_fab = findViewById(R.id.heartTips_fab);
        heartTips_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HealthyHeartTips.this, InsertDataForHeartTips.class));
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();

        adapterRecyclerView.startListening();

    }
}
