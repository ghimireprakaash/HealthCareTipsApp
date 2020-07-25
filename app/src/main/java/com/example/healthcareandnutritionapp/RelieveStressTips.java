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

public class RelieveStressTips extends AppCompatActivity {
    Button buttonBackStressRelieveTips;

    RecyclerView stressRelieveRecyclerView;

    // Creating object of RecyclerViewAdapter
    AdapterRecyclerView adapterRecyclerView;


    ProgressBar progressBar_StressRelieve;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relieve_stress_tips);

        buttonBackStressRelieveTips = findViewById(R.id.backArrow);
        buttonBackStressRelieveTips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RelieveStressTips.this, HealthTips.class));
            }
        });


        progressBar_StressRelieve = findViewById(R.id.progressBar_StressRelieveTips);

        stressRelieveRecyclerView = findViewById(R.id.stressRelieveRecyclerView);
        stressRelieveRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<Model> options =
                new FirebaseRecyclerOptions.Builder<Model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Health Tips").child("Stress Relieve"), Model.class)
                        .build();

        adapterRecyclerView = new AdapterRecyclerView(options);
        stressRelieveRecyclerView.setAdapter(adapterRecyclerView);


        progressBar_StressRelieve.setVisibility(View.VISIBLE);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                progressBar_StressRelieve.setVisibility(View.GONE);
                onStart();
            }
        }, 2000);


        FloatingActionButton relieveStress_fab = findViewById(R.id.relieveStress_fab);

        //OnClick fab button, the screen or activity switches to its respective data insert activity
        relieveStress_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), InsertDataForRelievingStress.class);
                startActivity(intent);
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();

        adapterRecyclerView.startListening();
    }
}
