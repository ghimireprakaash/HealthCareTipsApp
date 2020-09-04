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
import com.google.firebase.database.FirebaseDatabase;

public class HealthTipsForWomen extends AppCompatActivity {
    private Toolbar toolbar;

    RecyclerView womenHealthTipsRecyclerView;

    // Creating object of RecyclerViewAdapter
    AdapterRecyclerView adapterRecyclerView;


    ProgressBar progressBar_womenHealthTips;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_tips_for_women);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HealthTipsForWomen.this, HealthTips.class));
                finish();
            }
        });


        progressBar_womenHealthTips = findViewById(R.id.progressBar_womenHealthTips);


        womenHealthTipsRecyclerView = findViewById(R.id.womenHealthTipsRecyclerView);
        womenHealthTipsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<Model> options =
                new FirebaseRecyclerOptions.Builder<Model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Health Tips").child("Health Tips focused for Women"), Model.class)
                        .build();

        adapterRecyclerView = new AdapterRecyclerView(options);

        womenHealthTipsRecyclerView.setAdapter(adapterRecyclerView);


        progressBar_womenHealthTips.setVisibility(View.VISIBLE);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                progressBar_womenHealthTips.setVisibility(View.GONE);
                onStart();
            }
        }, 2000);
    }


    @Override
    protected void onStart() {
        super.onStart();

        adapterRecyclerView.startListening();
    }
}