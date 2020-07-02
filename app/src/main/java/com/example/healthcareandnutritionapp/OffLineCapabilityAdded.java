package com.example.healthcareandnutritionapp;

import android.app.Application;
import com.google.firebase.database.FirebaseDatabase;

public class OffLineCapabilityAdded extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}
