package com.example.healthcareandnutritionapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class HealthyFoodRecipes extends AppCompatActivity {
    TextView viewRecipes1, viewRecipes2, viewRecipes3, viewRecipes4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_healthy_food_recipes);

        viewRecipes1 = findViewById(R.id.textView1);
        viewRecipes2 = findViewById(R.id.textView2);
        viewRecipes3 = findViewById(R.id.textView3);
        viewRecipes4 = findViewById(R.id.textView4);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent = new Intent(HealthyFoodRecipes.this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_out_left, R.anim.slide_in_right);
    }
}
