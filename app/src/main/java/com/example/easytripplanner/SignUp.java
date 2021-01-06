package com.example.easytripplanner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUp extends AppCompatActivity {
    EditText mFirstName , mLastName , mEmail , mPassword , mConfirmPassword;
    Button msignUp;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
         //connect the variables with resources
        mFirstName = findViewById(R.id.first_name);
        mLastName = findViewById(R.id.last_name);
        mEmail = findViewById(R.id.email_address);
        mPassword = findViewById(R.id.password);
        mConfirmPassword = findViewById(R.id.confirm_password);
        msignUp = findViewById(R.id.sign_up);
        fAuth= FirebaseAuth.getInstance();
        if(fAuth.getCurrentUser() != null)
        {
            startActivity(new Intent(getApplicationContext(),UserProfile.class));
            finish();

        }

        msignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstName = mFirstName.getText().toString().trim();
                String lastName = mLastName.getText().toString().trim();
                String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();
                String confirmPassword = mConfirmPassword.getText().toString().trim();

                //validation for user information
                if(TextUtils.isEmpty(email))
                {
                    mEmail.setError("Email is Required.");
                    return;
                }
                if(TextUtils.isEmpty(password))
                {
                    mPassword.setError("Password is Required.");
                    return;
                }
                if(TextUtils.isEmpty(confirmPassword))
                {
                    mConfirmPassword.setError("Confirm password is Required");
                    return;
                }
                if(password.length()<6)
                {
                    mPassword.setError("Password must be more than 6 characters");
                }
                if(!confirmPassword.equals(password))
                {
                    mConfirmPassword.setError("Confirm password must match your password");
                    return;
                }

                //register the user in firebase
                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(SignUp.this, "User created.", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(getApplicationContext(),UserProfile.class));
                        }
                        else
                        {
                            Toast.makeText(SignUp.this,"Error!" +task.getException().getMessage(),Toast.LENGTH_LONG).show();

                        }
                    }
                });

            }
        });

    }
}