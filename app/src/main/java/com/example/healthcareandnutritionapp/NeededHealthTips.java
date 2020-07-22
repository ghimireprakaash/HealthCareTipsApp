package com.example.healthcareandnutritionapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;

public class NeededHealthTips extends AppCompatActivity {
//    EditText generalTipsValue1, generalTipsValue2, generalTipsValue3, generalTipsValue4, generalTipsValue5, generalTipsValue6;

    RecyclerView healthTipsRecyclerView;

    // Creating object of RecyclerViewAdapter
    AdapterRecyclerView adapterRecyclerView;


    ProgressBar progressBar_HealthTips;


    //OnCreate Method StartUp
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_needed_health_tips);

        progressBar_HealthTips = findViewById(R.id.progressBar_HealthTips);


        healthTipsRecyclerView = findViewById(R.id.healthTipsRecyclerView);
        healthTipsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<Model> options =
                new FirebaseRecyclerOptions.Builder<Model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Health Tips").child("SubClass Health Tips"), Model.class)
                        .build();

        adapterRecyclerView = new AdapterRecyclerView(options);

        healthTipsRecyclerView.setAdapter(adapterRecyclerView);


        progressBar_HealthTips.setVisibility(View.VISIBLE);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                progressBar_HealthTips.setVisibility(View.GONE);
                onStart();
            }
        }, 2000);





        FloatingActionButton generalHealthTips_fab = findViewById(R.id.generalHealthTips_fab);

        //OnClick fab button, the screen or activity switches to its respective data insert activity
        generalHealthTips_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), NeededHealthTipsDATAINSERTField.class);
                startActivity(intent);
            }
        });

//        generalTipsValue1 = findViewById(R.id.generalTipsValue1);
//        generalTipsValue2 = findViewById(R.id.generalTipsValue2);
//        generalTipsValue3 = findViewById(R.id.generalTipsValue3);
//        generalTipsValue4 = findViewById(R.id.generalTipsValue4);
//        generalTipsValue5 = findViewById(R.id.generalTipsValue5);
//        generalTipsValue6 = findViewById(R.id.generalTipsValue6);

    }

    @Override
    protected void onStart() {
        super.onStart();

        adapterRecyclerView.startListening();
    }
}
