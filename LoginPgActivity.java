package com.example.trialapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class LoginPgActivity extends AppCompatActivity {


    EditText editemail , editpass ,signup;
    Button login;

    FirebaseAuth auth;

    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_pg);

        editemail = findViewById(R.id.emailbox);
        editpass = findViewById(R.id.pwd);
        login = findViewById(R.id.button1);
        signup = findViewById(R.id.dnthaveacc);
        auth = FirebaseAuth.getInstance();
        dialog = new ProgressDialog(this);

        signup.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginPgActivity.this, signupPg.class));
            }
        });
        
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckValidation();
            }
        });
    }

    private void CheckValidation() {
        String mail = editemail.getText().toString();
        String password = editpass.getText().toString();

        if (mail.isEmpty()) {
            editemail.setError("Required");
        } else if (password.isEmpty()) {
            editpass.setError("Required");
        } else if (password.length() < 8) {
            editpass.setError("Password too Short!");
        } else {
            AuthenticateUser(mail, password);
        }
    }

    private void AuthenticateUser(String mail, String password) {
        dialog.setMessage("Please wait ....");
        dialog.setCancelable(false);
        dialog.show();

        auth.signInWithEmailAndPassword(mail,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    dialog.dismiss();
                    Toast.makeText(LoginPgActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginPgActivity.this, HomePgActivity.class));
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                dialog.dismiss();
                Toast.makeText(LoginPgActivity.this, "Error" + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}