package com.example.trialapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class signupPg extends AppCompatActivity {


    EditText editemail, editpass;
    Button signin;

    FirebaseAuth auth;

    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_pg);

        editemail =findViewById(R.id.emlbox1);
        editpass = findViewById(R.id.pwdbox1);
        signin = findViewById(R.id.button2);
        auth = FirebaseAuth.getInstance();
        dialog =new ProgressDialog(this);

        signin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                CheckValidation();
            }
        });
    }

    private void CheckValidation() {
        String mail = editemail.getText().toString();
        String password = editpass.getText().toString();

        if (mail.isEmpty()){
            editemail.setError("Required!");
        } else if (password.isEmpty()) {
            editpass.setError("Required!");
        } else if (password.length()< 8 ) {
            editpass.setError("Password too Short!");
        } else {
            AddUser (mail,password);
        }
    }

    private void AddUser(String mail, String password) {
        dialog.setMessage("Please wait...");
        dialog.setCancelable(false);
        dialog.show();

        auth.createUserWithEmailAndPassword(mail,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    dialog.dismiss();
                    Toast.makeText(signupPg.this, "Registered!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(signupPg.this, LoginPgActivity.class));
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                dialog.dismiss();
                Toast.makeText(signupPg.this, "Error" + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
