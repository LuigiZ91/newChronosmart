package com.example.newchronosmart;

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

public class MainActivity extends AppCompatActivity
{
    private FirebaseAuth myAuth;

    private FirebaseAuth.AuthStateListener mAuthListener;

    private EditText myEmail;
    private EditText myPassword;

    private Button LoginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myAuth = FirebaseAuth.getInstance();

        myEmail = (EditText) findViewById(R.id.username);
        myPassword = (EditText) findViewById(R.id.password);

        LoginBtn = (Button) findViewById(R.id.login_btn);



        mAuthListener = new FirebaseAuth.AuthStateListener()
        {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth)
            {

                if(firebaseAuth.getCurrentUser() != null)
                {
                    startActivity(new Intent(MainActivity.this, HomeActivity.class ));
                }
            }

        };


        LoginBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startSignIn();
            }
        });



    }


    private void startSignIn()
    {
        String email = myEmail.getText().toString();
        String password = myPassword.getText().toString();

        if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password ))
        {

            Toast.makeText(MainActivity.this, "fields are empty", Toast.LENGTH_LONG).show();

        }
        else
        {
            myAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>()
            {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task)
                {

                    if (!task.isSuccessful())
                    {
                        //mProgressBar.setVisibility(View.INVISIBLE);
                        Toast.makeText(MainActivity.this, "Sign In Problem", Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        startActivity(new Intent(MainActivity.this, HomeActivity.class));
                    }
                }
            });
        }
    }

    @Override
    public void onBackPressed()
    {
        //this.finish();
        //System.exit(0);

    }



}
