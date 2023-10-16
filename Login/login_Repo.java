package com.example.teacher_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginUserActivity extends AppCompatActivity {
    TextView gotologin;
    TextInputEditText emaillogin, passlogin;
    Button loginuser;
    FirebaseAuth auth;        // Getting the fireabase auth service initalize
    FirebaseUser currentuser;   // get the current user id from system

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_user);

        auth = FirebaseAuth.getInstance();      //Initalizing fireabase and acessing the current user
        currentuser = auth.getCurrentUser();  // geting teh id of current user




        // Findindg the rerfernce of all componenet
        gotologin = findViewById(R.id.gotologin);
        emaillogin = findViewById(R.id.emailogin);
        passlogin = findViewById(R.id.passlogin);
        loginuser = findViewById(R.id.loginuser);

        gotologin.setOnClickListener(new View.OnClickListener() {           // Moving to new Activity
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginUserActivity.this, RegisterUserActivity.class);
                startActivity(intent);
            }

        });

        //   loginuser.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (validateField()) {
//                    Toast.makeText(LoginUserActivity.this, "Fill All Field", Toast.LENGTH_SHORT).show();
//
//                } else
//                {
//                    auth.signInWithEmailAndPassword(emaillogin.getText().toString().trim(), passlogin
//                            .getText().toString().trim()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                        @Override
//                        public void onComplete(@NonNull Task<AuthResult> task) {
//
//                            if (task.isSuccessful()) {
//                                Intent intent = new Intent(LoginUserActivity.this, MainActivity.class);
//                                startActivity(intent);
//                                finish();
//                            } else {
//                                Toast.makeText(LoginUserActivity.this, "you are not user", Toast.LENGTH_SHORT).show();
//                            }
//
//                        }
//                    });
//                }
//            }
//        });


    }
// It ensure the user which login should reamain login event after closing the application
    // It aaslo ensure that the user do not need to re=ligon itself again 
    @Override
    protected void onStart() {
        super.onStart();
        if (currentuser != null) {
            Intent intent = new Intent(LoginUserActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
//    private boolean validateField() {              // Used to check the entry fields while login
//        int flag=0;
//
//        if (emaillogin.getText().toString().isEmpty())
//        {
//            emaillogin.setError("Enter Email");
//            flag =1;
//        }
//        else if (!Patterns.EMAIL_ADDRESS.matcher(emaillogin.getText().toString()).matches())
//        {
//            emaillogin.setError("Enter Valid Email");
//            flag=1;
//        }
//        if (passlogin.getText().toString().isEmpty())
//        {
//            passlogin.setError("Enter Password");
//            flag=1;
//        }
//
//        if (flag==1)
//        {
//            return true;
//        }
//        else
//        {
//            return false;
//        }
//    }
    }
}
