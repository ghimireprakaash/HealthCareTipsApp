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

public class RelieveStress extends AppCompatActivity {
    Button buttonBackStressRelieveTips;

    RecyclerView stressRelieveRecyclerView;

    // Creating object of RecyclerViewAdapter
    AdapterRecyclerView adapterRecyclerView;

    ProgressBar stressRelieveTips_ProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relieve_stress);

        buttonBackStressRelieveTips = findViewById(R.id.buttonBackStressRelieveTips);
        buttonBackStressRelieveTips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RelieveStress.this, HealthTips.class));
            }
        });



        stressRelieveTips_ProgressBar = findViewById(R.id.stressRelieveTips_ProgressBar);

        stressRelieveRecyclerView = findViewById(R.id.stressRelieveRecyclerView);
        stressRelieveRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<Model> options =
                new FirebaseRecyclerOptions.Builder<Model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Health Tips").child("Stress Relieve"), Model.class)
                        .build();

        adapterRecyclerView = new AdapterRecyclerView(options);


        stressRelieveTips_ProgressBar.setVisibility(View.VISIBLE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                stressRelieveTips_ProgressBar.setVisibility(View.GONE);
                stressRelieveRecyclerView.setAdapter(adapterRecyclerView);
            }
        },2000);
    }

    @Override
    protected void onStart() {
        super.onStart();

        adapterRecyclerView.startListening();
    }
}
