package com.example.healthcareandnutritionapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

public class MainActivity extends AppCompatActivity {

    TextView health_tips, nutrition_Tips, healthy_foods;
    ViewFlipper viewFlipper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        health_tips = findViewById(R.id.health_tips);
        nutrition_Tips = findViewById(R.id.nutrition_tips);
        //healthy_foods = findViewById(R.id.healthy_foods);

        //Image Slider
        int[] images = {R.drawable.one, R.drawable.two, R.drawable.three, R.drawable.four,
                R.drawable.five, R.drawable.six, R.drawable.seven};

        viewFlipper = findViewById(R.id.view_Flipper);

        //for loop
        /*for (int i = 0; i < images.length; i++){
            flipperImages(images[i]);
        }*/


        //Advanced Version of For Loop
        //foreach loop
        for (int image: images){
            flipperImages(image);
        }





        health_tips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HealthTips.class);
                startActivity(intent);
            }
        });

        nutrition_Tips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NutritionTips.class);
                startActivity(intent);
            }
        });

//        healthy_foods.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, HealthyFoodRecipes.class);
//                startActivity(intent);
//            }
//        });
    }



    //ViewFlipper external method
    public void flipperImages(int image){
        ImageView imageView = new ImageView(this);
        imageView.setBackgroundResource(image);

        viewFlipper.addView(imageView);
        viewFlipper.setFlipInterval(4000); // 4 seconds
        viewFlipper.setAutoStart(true);

        //animation
        viewFlipper.setInAnimation(this, android.R.anim.slide_out_right);
        viewFlipper.setOutAnimation(this, android.R.anim.slide_out_right);
    }




    //AlertDialog for exit application

    @Override
    public void onBackPressed() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage("Are you sure you want to Exit?")
                .setCancelable(true)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MainActivity.super.onBackPressed();
                    }
                })

                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
