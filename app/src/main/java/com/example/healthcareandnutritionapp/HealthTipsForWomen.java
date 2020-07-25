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

public class HealthTipsForWomen extends AppCompatActivity {
    Button buttonBackHealthTipsWomen;

    RecyclerView womenHealthTipsRecyclerView;

    // Creating object of RecyclerViewAdapter
    AdapterRecyclerView adapterRecyclerView;


    ProgressBar progressBar_womenHealthTips;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_tips_for_women);

        buttonBackHealthTipsWomen = findViewById(R.id.backArrow);
        buttonBackHealthTipsWomen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HealthTipsForWomen.this, MainActivity.class));
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





        FloatingActionButton womenHealthTips_fab = findViewById(R.id.womenHealthTips_fab);

        //OnClick fab button, the screen or activity switches to its respective data insert activity
        womenHealthTips_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), InsertDataForWomenHealthTips.class);
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