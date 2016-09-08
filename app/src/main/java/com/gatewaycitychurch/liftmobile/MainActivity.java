package com.gatewaycitychurch.liftmobile;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import com.parse.ParseObject;

import com.parse.Parse;
import com.parse.ParseUser;

public class MainActivity extends ToolbarActivity implements View.OnClickListener{

    Button login;
    Button signup;
    Intent mServiceIntent;
    ParseUser currentUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Parse.initialize(new Parse.Configuration.Builder(this).applicationId("lift-317E9")
                .server("https://still-spire-14887.herokuapp.com/parse").build());
        if(ParseUser.getCurrentUser() != null){
            startActivity(new Intent("com.gatewaycitychurch.GeneratedQRCodeActivity"));
        }
        login = (Button)findViewById(R.id.login);
        login.setOnClickListener(this);

        signup = (Button)findViewById(R.id.signup);
        signup.setOnClickListener(this);


    };
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    private void loginClick()
    {
        startActivity(new Intent("com.gatewaycitychurch.LoginUser"));
}

    private void signupClick()
    {
        startActivity(new Intent("com.gatewaycitychurch.RegisterUser"));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.login:
                loginClick();
                break;
            case R.id.signup:
                signupClick();
                break;
        }
    }
}
