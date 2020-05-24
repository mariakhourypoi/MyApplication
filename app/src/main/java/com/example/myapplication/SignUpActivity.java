package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static android.widget.Toast.LENGTH_LONG;

public class SignUpActivity extends AppCompatActivity {
     EditText editTextEmail  , editTextPassword ;
    ImageButton back ;
    Button signUpB;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth=FirebaseAuth.getInstance();
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        signUpB=findViewById(R.id.buttonSignUp);
        signUpB.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {

              if(editTextPassword.getText().toString().equals("") || editTextEmail.getText().toString().equals("")){
                  Toast.makeText(SignUpActivity.this ,"Empty Password or Email", LENGTH_LONG).show();
              }
              else {
                  signUp(editTextEmail.getText().toString(),editTextPassword.getText().toString());
                  Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                  startActivity(intent);

              }
          }
          });

        back=findViewById(R.id.buttonBackLog);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this, LogInActivity.class);
                startActivity(intent);
            }
        });


    }
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        // updateUI(currentUser);
    }
    public void signUp(String email,String password)
    {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("firebaseauth", "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                            startActivity(intent);
                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("firebaseauth", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(SignUpActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }


                        // ...
                    }
                });
    }


}
