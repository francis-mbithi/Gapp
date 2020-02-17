package com.moringaschool.garbage.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.moringaschool.garbage.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener {

    public final String TAG = SignupActivity.class.getSimpleName();
    private FirebaseAuth mAuth;
    private  FirebaseAuth.AuthStateListener  mAuthListner;
    private ProgressDialog mAuthProgressDialog;

    private String mName;

    @BindView(R.id.username)
    EditText usernameEditText;
    @BindView(R.id.email) EditText emailEditText;
    @BindView(R.id.password) EditText passwordEditText;
    @BindView(R.id.signupBtn)
    Button signUpBtn;
    @BindView(R.id.signupTv)
    TextView toLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);
        mAuth = FirebaseAuth.getInstance();
        signUpBtn.setOnClickListener(this);
        toLogin.setOnClickListener(this);
        createAuthStateListner();
        createAuthProgressDialog();
    }
    //sent username to firebase
    private void createFirebaseUserProfile(final FirebaseUser user){
        UserProfileChangeRequest addUserProfile = new UserProfileChangeRequest.Builder()
                .setDisplayName(mName)
                .build();
        user.updateProfile(addUserProfile)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Log.d(TAG, user.getDisplayName());
                        }
                    }
                });
    }

    private void createAuthProgressDialog() {
        mAuthProgressDialog = new ProgressDialog(this);
        mAuthProgressDialog.setTitle("Loading...");
        mAuthProgressDialog.setMessage("Authenticating via firebase...");
        mAuthProgressDialog.setCancelable(false);
    }

    private void createAuthStateListner() {
        mAuthListner = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                final FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null){
                    Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
//                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
                    startActivity(intent);
                    finish();
                }
            }
        };

    }

    @Override
    public void onClick(View v) {
        if (v == toLogin){
            Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }
        if (v == signUpBtn){
            createNewUser();
        }
    }

    private void createNewUser() {
        final String name = usernameEditText.getText().toString().trim();
        final String userEmail = emailEditText.getText().toString().trim();
        final String userPassword = passwordEditText.getText().toString().trim();

        mName = usernameEditText.getText().toString().trim();

        boolean validEmail = isValidEmail(userEmail);
        boolean validName = isValidUserName(mName);
        boolean validPassword = isValidPassword(userPassword);

        if (!validEmail || !validName || !validPassword){
            return;
        }

        mAuthProgressDialog.show();

        mAuth.createUserWithEmailAndPassword(userEmail, userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isComplete()){
                    mAuthProgressDialog.dismiss();
                    if (task.isSuccessful()){
                        createFirebaseUserProfile(task.getResult().getUser());
                    }
                }
                else {
                    Toast.makeText(SignupActivity.this, "Authentication failed", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public boolean isValidEmail(String email){
        boolean isGoodEmail = (email != null && Patterns.EMAIL_ADDRESS.matcher(email).matches());
        if(!isGoodEmail){
            emailEditText.setError("Please enter a valid email address");
            return false;
        }
        return isGoodEmail;
    }

    public boolean isValidUserName(String username){
        boolean isGoodUsername = (!username.isEmpty() && username.length()>1);
        if (!isGoodUsername){
            usernameEditText.setError("Please Enter a valid username");
        }
        return isGoodUsername;
    }

    public boolean isValidPassword(String password){
        boolean isGoodPassword = password.length()>5;
        if (!isGoodPassword){
            passwordEditText.setError("Password must be greater than six");
        }
        return isGoodPassword;
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListner);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mAuth.removeAuthStateListener(mAuthListner);
    }
}
