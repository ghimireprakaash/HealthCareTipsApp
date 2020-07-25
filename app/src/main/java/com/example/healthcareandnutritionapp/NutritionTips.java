package com.example.healthcareandnutritionapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class NutritionTips extends AppCompatActivity {
    Button buttonBackNutritionTips;

    TextView general_Nutrition, nutrition_Children, nutrition_Men, nutrition_Women, nutritionTips_Hair, nutritionTips_skin, weight_Gain;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutrition_tips);

        general_Nutrition = findViewById(R.id.generalNutritionTips);
        nutrition_Children = findViewById(R.id.nutritionTips_Children);
        nutrition_Men = findViewById(R.id.nutritionTips_Men);
        nutrition_Women = findViewById(R.id.nutritionTips_Women);
        nutritionTips_Hair = findViewById(R.id.nutritionTips_Hair);
        nutritionTips_skin = findViewById(R.id.nutritionTips_Skin);
        weight_Gain = findViewById(R.id.weightGainTips);

        general_Nutrition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NutritionTips.this, NeededNutritionTips.class));
            }
        });

        nutrition_Children.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NutritionTips.this, NutritionTipsForChildren.class));
            }
        });

        nutrition_Men.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NutritionTips.this, NutritionTipsForMen.class));
            }
        });

        nutrition_Women.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NutritionTips.this, NutritionTipsForWomen.class));
            }
        });

        nutritionTips_Hair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NutritionTips.this, NutritionTipsForHair.class));
            }
        });

        nutritionTips_skin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NutritionTips.this, NutritionTipsForSkin.class));
            }
        });

        weight_Gain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NutritionTips.this, NutritionForGainingWeight.class));
            }
        });


        buttonBackNutritionTips = findViewById(R.id.backArrow);
        buttonBackNutritionTips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NutritionTips.this, MainActivity.class));
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent = new Intent(NutritionTips.this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_out_left, R.anim.slide_in_right);
    }
}
