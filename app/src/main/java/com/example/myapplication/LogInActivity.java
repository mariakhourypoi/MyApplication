package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.util.LogPrinter;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.model.ButtCap;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

public class LogInActivity extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {
    RadioGroup rG;
    EditText editTextEmail  , editTextPassword ;
    Button buttonLogIn;
    RadioButton parent,child;
    TextView signUp;
    private FirebaseAuth mAuth;
    String body="";
    String code = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);



        getSupportActionBar().hide();

        editTextEmail=findViewById(R.id.editTextEmail);
        editTextPassword=findViewById(R.id.editTextPassword);

        buttonLogIn=findViewById(R.id.buttonLogIn);
        buttonLogIn.setOnClickListener(this);
        rG= (RadioGroup) findViewById(R.id.radioGroupCheck);
        rG.setOnCheckedChangeListener(this);
        parent=findViewById(R.id.radioButtonParent);


        child=findViewById(R.id.radioButtonParent);
        parent=findViewById(R.id.radioButtonParent);
        child=findViewById(R.id.radioButtonChild);
        parent.setOnClickListener(this);
        child.setOnClickListener(this);

        signUp=findViewById(R.id.textViewSignUp);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(LogInActivity.this,SignUpActivity.class);
                startActivity(intent);
            }
        });
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        // updateUI(currentUser);
    }
    public void signIn(String email,String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            // Sign in success, update UI with the signed-in user's information
                            Log.d("fireBase", "signInWithEmail:success");
                            //updateUI(user);
                            FirebaseUser user = mAuth.getCurrentUser();
                            if(parent.isChecked())
                            {
                                Log.d("fireBase", "Login Parent:success");

                                Intent i = new Intent(LogInActivity.this, MainActivity.class);
                                startActivity(i);
                            }
                            if (child.isChecked()) {
                                {
                                    Log.d("fireBase", "Login Child:success");
                                    Intent i = new Intent(LogInActivity.this, MainActivityForChild.class);
                                    startActivity(i);

                                }

                            }

                        }else {
                            // If sign in fails, display a message to the user.
                            Log.w("fireBase", "signInWithEmail:failure", task.getException());
                            Toast.makeText(LogInActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            // updateUI(null);
                        }

                        // ...
                    }
                });

    }

    public void onClick(View v) {
        if (v == buttonLogIn) {
            if ((editTextPassword.getText().toString().equals("") || editTextEmail.getText().toString().equals(""))&& parent.isChecked()) {
                Toast.makeText(LogInActivity.this, "Empty Password or Email", Toast.LENGTH_LONG).show();
            }else {
                if (!parent.isChecked() && !child.isChecked()) {
                    Toast.makeText(LogInActivity.this, "please check one of the buttons", Toast.LENGTH_LONG).show();
                } else if (parent.isChecked() && !child.isChecked()) {


                    if (!code.equals(body)) {
                        Toast.makeText(LogInActivity.this, body + ":" + code, Toast.LENGTH_LONG).show();
                        //alertDialog.dismiss();
                    } else {
                        Toast.makeText(LogInActivity.this, "Going to Sign In Method", Toast.LENGTH_LONG).show();
                        signIn(editTextEmail.getText().toString(), editTextPassword.getText().toString());
                    }

                } else {
                    signIn(editTextEmail.getText().toString(), editTextPassword.getText().toString());
                }
            }
        }

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if (R.id.radioButtonParent == checkedId) {
            String to = editTextEmail.getText().toString();
            final Intent result = new Intent(android.content.Intent.ACTION_SEND);
            body = "sk" + (int) (Math.random() * 10000 + 1);
            result.setType("plain/text");
            result.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{"mariakh189@gmail.com"});
            result.putExtra(android.content.Intent.EXTRA_SUBJECT, "Testing");
            result.putExtra(android.content.Intent.EXTRA_TEXT, body);
            startActivity(result);
            Toast.makeText(LogInActivity.this, "Sending Email with code", Toast.LENGTH_LONG).show();


            final AlertDialog.Builder alert = new AlertDialog.Builder(LogInActivity.this);
            View mView = getLayoutInflater().inflate(R.layout.custom_dialog1, null);

            final EditText txt_securityCode = (EditText) mView.findViewById(R.id.editTextSecurityCode);
            Button btnCancel = (Button) mView.findViewById(R.id.buttonCancel);
            Button btnDone = (Button) mView.findViewById(R.id.buttonDone);
            alert.setView(mView);
            final AlertDialog alertDialog = alert.create();
            alertDialog.setCanceledOnTouchOutside(false);
            btnCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alertDialog.dismiss();
                }
            });
            btnDone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    code = txt_securityCode.getText().toString();
                    alertDialog.dismiss();


                }
            });

            alertDialog.show();


        }
    }
}
/*
    public void btn_showDialog(View view)
    {


        final AlertDialog.Builder alert=new AlertDialog.Builder(LogInActivity.this);
        View mView = getLayoutInflater().inflate(R.layout.custom_dialog1,null);

        final EditText txt_securityCode =(EditText)mView.findViewById(R.id.editTextSecurityCode);
        Button btnCancel=(Button)mView.findViewById(R.id.buttonCancel);
        Button btnDone=(Button)mView.findViewById(R.id.buttonDone);
        alert.setView(mView);
        final AlertDialog alertDialog= alert.create();
        alertDialog.setCanceledOnTouchOutside(false);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code = txt_securityCode.getText().toString();
                if(!code.equals(body)){

                    alertDialog.dismiss();
                }else{

                    Intent i =new Intent(LogInActivity.this,MainActivity.class);
                    alertDialog.dismiss();
                    startActivity(i);
                }

            }
        });
        alertDialog.dismiss();
    }
*/





/*
            String to = editTextEmail.getText().toString();
                String subject= "security code:";
                body="sk"+ (Math.random() * 10 + 1)+(Math.random() * 10 + 1)+(Math.random() * 10 + 1)+(Math.random() * 10 + 1);
                String mailTo = "mailto:" + to +
                        "?&subject=" + Uri.encode(subject) +
                        "&body=" + Uri.encode(body);
                Intent emailIntent = new Intent(Intent.ACTION_VIEW);
                emailIntent.setData(Uri.parse(mailTo));
                startActivity(emailIntent);

               // signIn(editTextEmail.getText().toString(), editTextPassword.getText().toString());

               /* if(parent.isChecked()&& !editTextPassword.getText().toString().equals("")&&  !editTextEmail.getText().toString().equals("")) {
                    signIn(editTextEmail.getText().toString(),editTextPassword.getText().toString());
                    Intent i = new Intent(LogInActivity.this, MainActivity.class);
                    i.putExtra("email", editTextEmail.getText().toString());
                    i.putExtra("password", editTextPassword.getText().toString());
                    startActivity(i);
                     }
                if(child.isChecked()&& !editTextPassword.getText().toString().equals("")&&  !editTextEmail.getText().toString().equals("")) {
                    signIn(editTextEmail.getText().toString(),editTextPassword.getText().toString());
                    Intent i = new Intent(LogInActivity.this, MainActivityForChild.class);
                    i.putExtra("email", editTextEmail.getText().toString());
                    i.putExtra("password", editTextPassword.getText().toString());
                    startActivity(i);
                }*/