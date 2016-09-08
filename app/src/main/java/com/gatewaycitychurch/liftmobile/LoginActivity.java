package com.gatewaycitychurch.liftmobile;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import android.view.View.OnClickListener;

/**
 * Created by Anthony on 9/5/2016.
 */

public class LoginActivity extends ToolbarActivity implements OnClickListener {

    Button login;
    EditText username;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CheckCurrentSession getLogin = new CheckCurrentSession();
        if(getLogin.checkLogin()){
            finish();
            startActivity(new Intent("com.gatewaycitychurch.GeneratedQRCodeActivity"));
        } else {
            setContentView(R.layout.activity_login);


            username = (EditText) findViewById(R.id.loginUsername);
            password = (EditText) findViewById(R.id.loginPass);
            login = (Button) findViewById(R.id.loginButton);
            login.setOnClickListener(this);
        }


    };

    public void onLoginAction(){
        String usernameText = username.getText().toString();
        String passwordText = password.getText().toString();

        ParseUser user = new ParseUser();
        user.logInInBackground(usernameText, passwordText, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if(user != null){
                    finish();
                    startActivity(new Intent("com.gatewaycitychurch.GeneratedQRCodeActivity"));
                } else {
                    Log.e("PARSE", e.getMessage());
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.loginButton:
                onLoginAction();
                break;
        }
    }
}
