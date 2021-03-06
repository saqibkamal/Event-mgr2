package kamal.saqib.eventmanager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

/**
 * Created by Dell on 6/19/2017.
 */

public class LoginActivity extends AppCompatActivity {
    private EditText inputEmail, inputPassword;
    private FirebaseAuth auth;
    private ProgressBar progressBar;
    private Button btnSignup, btnLogin, btnReset;
    private boolean loggedIn;

    //*
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;
    private String userId;
    //*

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);


        auth = FirebaseAuth.getInstance();




        setContentView(R.layout.login);

       // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
      //  setSupportActionBar(toolbar);

        inputEmail = (EditText) findViewById(R.id.email);
        inputPassword = (EditText) findViewById(R.id.password);
        btnSignup = (Button) findViewById(R.id.btn_signup);
        btnLogin = (Button) findViewById(R.id.btn_login);
        btnReset = (Button) findViewById(R.id.btn_reset_password);
        loggedIn = false;


        //*
       // mFirebaseInstance = FirebaseDatabase.getInstance();

       // mFirebaseDatabase = mFirebaseInstance.getReference("users");

       // mFirebaseInstance.getReference("app_title").setValue("Users and Tickets");
        //*


        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SignupActivity.class));
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ResetPasswordActivity.class));
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = inputEmail.getText().toString();
                final String password = inputPassword.getText().toString();

                if(TextUtils.isEmpty(email)){
                    Toast.makeText(getApplicationContext(), "Enter Email Address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(password)){
                    Toast.makeText(getApplicationContext(), "Enter Password!", Toast.LENGTH_SHORT).show();
                    return;
                }



                auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {


                             //   progressBar.setVisibility(View.GONE);

                                if(!task.isSuccessful()){
                                    Toast.makeText(LoginActivity.this, getString(R.string.auth_failed), Toast.LENGTH_LONG).show();
                                } else {
                                    Toast.makeText(LoginActivity.this,"Success", Toast.LENGTH_LONG);
                                    Log.i("YES","NO");
                                    loggedIn = true;
                                    Query q = mFirebaseDatabase.orderByChild("email").equalTo(email);
                                    q.addChildEventListener(new ChildEventListener() {
                                        @Override
                                        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                                            Employee cus = dataSnapshot.getValue(Employee.class);
                                            userId = dataSnapshot.getKey();

                                        }

                                        @Override
                                        public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                                        }

                                        @Override
                                        public void onChildRemoved(DataSnapshot dataSnapshot) {

                                        }

                                        @Override
                                        public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                                        }

                                        @Override
                                        public void onCancelled(DatabaseError databaseError) {

                                        }
                                    });


                                    Log.i("Loginey", String.valueOf(loggedIn));

                                    backToMain();

                                }
                            }
                        });


            }
        });

    }

    private void backToMain(){
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        Log.i("Loginex", String.valueOf(loggedIn));
        i.putExtra("LoggedInStatus", loggedIn);
        i.putExtra("UserId", userId);
        setResult(Activity.RESULT_OK, i);
        finish();
    }

    public void onBackPressed(){
        backToMain();
    }


}
