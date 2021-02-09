package com.example.healthcareandnutritionapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;
import java.util.Objects;

public class NutritionTipsForChildren extends AppCompatActivity implements AdapterRecyclerView.RecyclerViewHolder.OnItemLongClickListener {
    private Toolbar toolbar;

    RecyclerView childrenNutritionRecyclerView;

    // Creating object of RecyclerViewAdapter
    AdapterRecyclerView adapterRecyclerView;


    ProgressBar progressBar_ChildrenNutritionTips;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips_for_children);

        toolbar = findViewById(R.id.childrenNutrition_toolbar);
        setSupportActionBar(toolbar);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(false);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NutritionTipsForChildren.this, NutritionTips.class));
                finish();
            }
        });



        progressBar_ChildrenNutritionTips = findViewById(R.id.progressBar_ChildrenNutritionTips);

        childrenNutritionRecyclerView = findViewById(R.id.childrenNutritionRecyclerView);
        childrenNutritionRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<Model> options =
                new FirebaseRecyclerOptions.Builder<Model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Nutrition Tips").child("Nutrition Tips focused for Children"), Model.class)
                        .build();

        adapterRecyclerView = new AdapterRecyclerView(options, this);


        progressBar_ChildrenNutritionTips.setVisibility(View.VISIBLE);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                progressBar_ChildrenNutritionTips.setVisibility(View.GONE);

                childrenNutritionRecyclerView.setAdapter(adapterRecyclerView);

            }
        }, 1000);




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

    @Override
    public void onItemLongClick(final int position) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setTitle("Remove Item");
        dialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                adapterRecyclerView.getRef(position).removeValue();
                adapterRecyclerView.notifyItemRemoved(position);
            }
        });
        dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialogBuilder.create().show();
    }
}
