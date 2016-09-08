package com.gatewaycitychurch.liftmobile;

import android.app.IntentService;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import com.parse.ParseUser;

/**
 * Created by Anthony on 9/5/2016.
 */

public class CheckCurrentSession extends AppCompatActivity {

    public boolean checkLogin(){
        if(ParseUser.getCurrentUser() == null){
            return false;
        }
        return true;
    }
}
