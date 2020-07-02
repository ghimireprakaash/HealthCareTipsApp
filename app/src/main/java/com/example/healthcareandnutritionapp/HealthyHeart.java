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

public class HealthyHeart extends AppCompatActivity {
    Button buttonBackHealthyHeartTips;
    RecyclerView heartTipsRecyclerView;

    // Creating object of RecyclerViewAdapter
    AdapterRecyclerView adapterRecyclerView;

    ProgressBar healthyHeartTips_ProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_healthy_heart);


        buttonBackHealthyHeartTips = findViewById(R.id.buttonBackHealthyHeartTips);
        buttonBackHealthyHeartTips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HealthyHeart.this, HealthTips.class));
            }
        });

        healthyHeartTips_ProgressBar = findViewById(R.id.healthyHeartTips_ProgressBar);

        heartTipsRecyclerView = findViewById(R.id.heartTipsRecyclerView);
        heartTipsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<Model> options =
                new FirebaseRecyclerOptions.Builder<Model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Health Tips").child("Healthy Heart Tips"), Model.class)
                        .build();

        adapterRecyclerView = new AdapterRecyclerView(options);

        healthyHeartTips_ProgressBar.setVisibility(View.VISIBLE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                healthyHeartTips_ProgressBar.setVisibility(View.GONE);
                heartTipsRecyclerView.setAdapter(adapterRecyclerView);
            }
        },2000);
    }

    @Override
    protected void onStart() {
        super.onStart();

        adapterRecyclerView.startListening();
    }
}
