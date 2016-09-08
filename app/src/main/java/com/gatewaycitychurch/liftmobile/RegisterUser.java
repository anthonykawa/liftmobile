package com.gatewaycitychurch.liftmobile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

/**
 * Created by Anthony on 9/5/2016.
 */

public class RegisterUser extends ToolbarActivity implements View.OnClickListener {

    EditText name;
    EditText email;
    EditText pass;
    EditText userName;
    Button submit;

    String nameText;
    String emailText;
    String passText;
    String usernameText;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CheckCurrentSession getLogin = new CheckCurrentSession();
        if(getLogin.checkLogin()){
            finish();
            startActivity(new Intent("com.gatewaycitychurch.GeneratedQRCodeActivity"));
        } else {
            setContentView(R.layout.activity_signup);

            name = (EditText) findViewById(R.id.signupName);
            email = (EditText) findViewById(R.id.signupEmail);
            userName = (EditText) findViewById(R.id.signupUsername);
            pass = (EditText) findViewById(R.id.signupPass);
            submit = (Button) findViewById(R.id.signupSubmit);
            submit.setOnClickListener(this);
        }
    };

    private void signupSubmit() {
        nameText = name.getText().toString();
        emailText = email.getText().toString();
        usernameText = userName.getText().toString();
        passText = pass.getText().toString();

        ParseUser user = new ParseUser();

        user.setEmail(emailText);
        user.setUsername(usernameText);
        user.setPassword(passText);
        user.put("name", nameText);
        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null){
                    ParseUser.logInInBackground(usernameText, passText, new LogInCallback() {
                        @Override
                        public void done(ParseUser user, ParseException e) {
                            if(user != null){
                                finish();
                                startActivity(new Intent("com.gatewaycitychurch.GeneratedQRCodeActivity"));
                            } else {
                                Log.d("PARSE", e.getMessage());
                            }
                        }
                    });
                } else {
                    Log.d("PARSE", e.getMessage());
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.signupSubmit:
                signupSubmit();
                break;
        }
    }


}
