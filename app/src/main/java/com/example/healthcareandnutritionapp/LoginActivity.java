package com.example.healthcareandnutritionapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class LoginActivity extends AppCompatActivity {
    EditText editName, editPassword;

    Button logInBtn;
    ProgressBar progressBar;

    //Creating Firebase Instance...
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        editName = findViewById(R.id.usernameField);
        editPassword = findViewById(R.id.passwordField);
        logInBtn = findViewById(R.id.logInBtn);
        progressBar = findViewById(R.id.progressBar);


        //initialize FirebaseAuth instance...
        firebaseAuth = FirebaseAuth.getInstance();

        logInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              getLoginDatas();
            }
        });
    }

    public void getLoginDatas(){
        String email = editName.getText().toString().trim();
        String password = editPassword.getText().toString().trim();

        if (TextUtils.isEmpty(email)){
            editName.setError("Email Required");
            return;
        }
        if (TextUtils.isEmpty(password)){
            editPassword.setError("Password Required");
            return;
        }

        //This progress bar will be visible when login button is clicked
        progressBar.setVisibility(View.VISIBLE);

        //Authentication of User...
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(LoginActivity.this, "Logged in Successfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));

                    progressBar.setVisibility(View.INVISIBLE);

                    finish();

                            /*Initializing ProgressBar Visibility to GONE or INVISIBLE after log In button
                             is pressed and when there is network connection available - means when onBackPressed
                             method is called progress bar is gone.*/

                } else {
                    if (!isConnected(LoginActivity.this)){
                        buildDialog(LoginActivity.this).show();

                        progressBar.setVisibility(View.INVISIBLE);
                    }

                    if(isConnected(LoginActivity.this)) {
                        Toast.makeText(LoginActivity.this,
                                "Email or Password doesn't match our database.", Toast.LENGTH_LONG).show();
                    }
                            /*Initializing Progress Bar Visibility to GONE or INVISIBLE after log In button
                             is pressed and when network connection isn't available */

                    progressBar.setVisibility(View.INVISIBLE);
                }
            }
        });

    }


    public boolean isConnected(Context context){

        //Setting or Initializing ConnectivityManager
        ConnectivityManager connectivityManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        //Getting Active Network Info...
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();

        if (activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting()) {
            android.net.NetworkInfo wifi = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            android.net.NetworkInfo mobile = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

            return (mobile != null && mobile.isConnectedOrConnecting()) || (wifi != null && wifi.isConnectedOrConnecting());
            }else {
            return false;
        }
    }

    public AlertDialog.Builder buildDialog(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Login Failed");
        builder.setMessage("Please turn on WIFI or MOBILE Data.");

        //set touch outside
        builder.setCancelable(false);

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        return builder;
    }


    public void signUpAccount(View view) {
        startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
    }




    //Get Current User

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if (currentUser != null) {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }
    }
}
