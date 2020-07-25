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

public class HealthTipsForMen extends AppCompatActivity {
    Button buttonBackHealthTipsMen;

    RecyclerView menHealthTipsRecyclerView;

    // Creating object of RecyclerViewAdapter
    AdapterRecyclerView adapterRecyclerView;


    ProgressBar progressBar_menHealthTips;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_tips_for_men);

        buttonBackHealthTipsMen = findViewById(R.id.backArrow);
        buttonBackHealthTipsMen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HealthTipsForMen.this, HealthTips.class));
            }
        });


        progressBar_menHealthTips = findViewById(R.id.progressBar_menHealthTips);


        menHealthTipsRecyclerView = findViewById(R.id.menHealthTipsRecyclerView);
        menHealthTipsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<Model> options =
                new FirebaseRecyclerOptions.Builder<Model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Health Tips").child("Health Tips focused for Men"), Model.class)
                        .build();

        adapterRecyclerView = new AdapterRecyclerView(options);

        menHealthTipsRecyclerView.setAdapter(adapterRecyclerView);


        progressBar_menHealthTips.setVisibility(View.VISIBLE);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                progressBar_menHealthTips.setVisibility(View.GONE);
                onStart();
            }
        }, 2000);





        FloatingActionButton menHealthTips_fab = findViewById(R.id.menHealthTips_fab);

        //OnClick fab button, the screen or activity switches to its respective data insert activity
        menHealthTips_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), InsertDataForMenHealthTips.class);
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