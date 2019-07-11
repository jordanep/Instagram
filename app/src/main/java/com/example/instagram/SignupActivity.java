package com.example.instagram;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignupActivity extends AppCompatActivity {

    private EditText etUsername;
    private EditText etEmail;
    private EditText etPassword;
    private EditText etConfirmPassword;
    private Button bSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        etUsername = findViewById(R.id.etUsername);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        bSignup = findViewById(R.id.bSignup);

        bSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (confirmPasswordsMatch()) {
                    String username = etUsername.getText().toString();
                    String password = etPassword.getText().toString();
                    String email = etEmail.getText().toString();

                    signup(username, password, email);
                } else {
                    Toast.makeText(SignupActivity.this,
                            "Passwords do not match", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private boolean confirmPasswordsMatch() {
        String password = etPassword.getText().toString();
        String confirm_password = etConfirmPassword.getText().toString();

        if (password.equals(confirm_password)) {
            return true;
        } else {
            return false;
        }
    }

    private void signup(String username, String password, String email) {
        // Create the ParseUser
        ParseUser user = new ParseUser();
        // Set core properties
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        // Set custom properties
        // user.put("phone", "###-###-####");
        // Invoke signUpInBackground
        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    // Hooray! Let them use the app now.
                    Log.d("SignupActivity", "Signup successful");
                    final Intent intent = new Intent(SignupActivity.this,
                            MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    // Sign up didn't suceed. Look at the ParseException
                    // to figure out what went wrong
                    Log.e("SignupActivity", "Signup failure");
                    e.printStackTrace(); // TODO: implement specific error handling
                }
            }
        });
    }
}
