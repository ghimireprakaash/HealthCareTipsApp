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
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {
//    EditText registerEmail, registerPassword;

    private static final Pattern PATTERNS_EMAIL = Pattern.compile("[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
            "\\@" +
            "gmail"+
//            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
            "(" +
            "\\." +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
            ")+");

    private TextInputLayout textInputLayout_Email, textInputLayout_Password;
    Button registerBtn;


    //String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\\\.+[a-z]+";

    //Declaring FirebaseAuth Object
    private FirebaseAuth firebaseAuth;

    ProgressBar register_ProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        textInputLayout_Email = findViewById(R.id.textInputLayout_Email);
        textInputLayout_Password = findViewById(R.id.textInputLayout_Password);

//        registerEmail = findViewById(R.id.registerEmail);
//        registerPassword = findViewById(R.id.registerPassword);

        registerBtn = findViewById(R.id.registerBtn);


        register_ProgressBar = findViewById(R.id.register_progressBar);

        firebaseAuth = FirebaseAuth.getInstance();

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerAccount();
            }
        });
    }


    //Validate Email
    private boolean validateEmail() {
        String emailInput = textInputLayout_Email.getEditText().getText().toString().trim();

        if (emailInput.isEmpty()) {
            textInputLayout_Email.setError("Field's can't be left Empty");
            return false;
        } else if (!emailInput.matches(String.valueOf(PATTERNS_EMAIL))) {
            textInputLayout_Email.setError("Please enter a valid email address");
            return false;
        } else {
            textInputLayout_Email.setError(null);
            return true;
        }
    }


    private boolean validatePassword() {
        String passwordInput = textInputLayout_Password.getEditText().getText().toString().trim();

        if (passwordInput.isEmpty()) {
            textInputLayout_Password.setError("Field's can't be left Empty");
            return false;
        } else if (passwordInput.length() <= 6) {
            textInputLayout_Password.setError("Password too short!");
            return false;
        } else {
            textInputLayout_Password.setError(null);
            return true;
        }
    }


    public void registerAccount() {
//        String email = registerEmail.getText().toString().trim();
//        String password = registerPassword.getText().toString().trim();


//        if (TextUtils.isEmpty(email)) {
//
//            registerEmail.setError("Email required");
//            return;
//        } else {
//            if (registerEmail.getText().toString().trim().matches(emailPattern)){
//                Toast.makeText(getApplicationContext(), "Valid Email Address", Toast.LENGTH_SHORT).show();
//            } else {
//                Toast.makeText(getApplicationContext(), "Invalid Email Address", Toast.LENGTH_SHORT).show();
//            }
//        }
//
//        if (TextUtils.isEmpty(password)){
//
//            registerPassword.setError("P  assword required");
//            return;
//        }
//
//        if (password.length() < 6) {
//
//            registerPassword.setError("Password too short!");
//            return;
//        }


        String email = textInputLayout_Email.getEditText().getText().toString().trim();
        String password = textInputLayout_Password.getEditText().getText().toString().trim();


        if (!validateEmail() | !validatePassword()) {

            register_ProgressBar.setVisibility(View.INVISIBLE);

        } else {
            register_ProgressBar.setVisibility(View.VISIBLE);

            if (!isConnected(RegisterActivity.this)) {

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        buildDialog(RegisterActivity.this).show();

                        register_ProgressBar.setVisibility(View.INVISIBLE);

                    }
                },1500);

            } else {

                firebaseAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                //register_ProgressBar.setVisibility(View.VISIBLE);

                                if (task.isSuccessful()) {

                                    new Handler().postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(RegisterActivity.this, "Admin Created", Toast.LENGTH_SHORT).show();
                                            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                                            finish();

                                            register_ProgressBar.setVisibility(View.INVISIBLE);
                                        }
                                    }, 2500);

                                } else {
                                    Handler handler = new Handler();
                                    handler.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(RegisterActivity.this, "Admin Creation Failed", Toast.LENGTH_SHORT).show();

                                            register_ProgressBar.setVisibility(View.INVISIBLE);
                                        }
                                    }, 2500);
                                }
                            }
                        });
            }
        }
    }

//    private void createAdminAccount(String email, String password) {
//
//        firebaseAuth.createUserWithEmailAndPassword(email, password)
//                .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful()) {
//
//                            Toast.makeText(RegisterActivity.this, "Admin Created", Toast.LENGTH_SHORT).show();
//
//                        } else {
//
//                            Toast.makeText(RegisterActivity.this, "Admin Creation Failed", Toast.LENGTH_SHORT).show();
//
//                        }
//                    }
//                });
//    }



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
        builder.setTitle("Registration Failed");
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
}