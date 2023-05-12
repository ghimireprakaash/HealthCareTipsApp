package com.example.healthcareandnutritionapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class SubClassHealthTips extends AppCompatActivity {
    private Toolbar toolbar;
    RecyclerView healthTipsRecyclerView;

    // Creating object of RecyclerViewAdapter
    AdapterRecyclerView adapterRecyclerView;

    ProgressBar healthTips_ProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_class_health_tips);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SubClassHealthTips.this, HealthTips.class));
                finish();
            }
        });


        healthTips_ProgressBar = findViewById(R.id.healthTips_ProgressBar);


        healthTipsRecyclerView = findViewById(R.id.healthTipsRecyclerView);
        healthTipsRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        FirebaseRecyclerOptions<Model> options =
                new FirebaseRecyclerOptions.Builder<Model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Health Tips").child("SubClass Health Tips"), Model.class)
                        .build();

        adapterRecyclerView = new AdapterRecyclerView(options);
        //healthTipsRecyclerView.setAdapter(adapterRecyclerView);


        healthTips_ProgressBar.setVisibility(View.VISIBLE);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                healthTips_ProgressBar.setVisibility(View.GONE);

                healthTipsRecyclerView.setAdapter(adapterRecyclerView);

            }
        },1000);

    }



    // Checking Network Stats...

//    public boolean isConnected(Context context) {
//
//        //Setting or Initializing ConnectivityManager
//        ConnectivityManager connectivityManager =
//                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
//
//
//        //Getting Active Network Info...
//        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
//
//        if (activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting()) {
//            android.net.NetworkInfo wifi = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
//            android.net.NetworkInfo mobile = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
//
//            return (mobile != null && mobile.isConnectedOrConnecting()) || (wifi != null && wifi.isConnectedOrConnecting());
//        }else {
//            return false;
//        }
//    }



    // Message when there's no internet connection

//    public AlertDialog.Builder buildDialog(Context context) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(context);
//        builder.setTitle("No INTERNET Connection");
//        builder.setMessage("Data couldn't load, please try again when internet is available. Press ok.");
//
//
//        //set touch outside
//        builder.setCancelable(false);
//
//        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                finish();
//            }
//        });
//        return builder;
//    }


    @Override
    protected void onStart() {
        super.onStart();

        adapterRecyclerView.startListening();

    }


    @Override
    protected void onStop() {
        super.onStop();

        adapterRecyclerView.stopListening();

    }
}
