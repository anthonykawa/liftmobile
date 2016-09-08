package com.gatewaycitychurch.liftmobile;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.parse.ParseUser;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

/**
 * Created by Anthony on 9/4/2016.
 */

public class GeneratedQRCodeActivity extends ToolbarActivity implements OnClickListener{

    private String LOG_TAG = "GenerateQRCode";
    Button scanButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode);
        CheckCurrentSession getLogin = new CheckCurrentSession();
        if(!getLogin.checkLogin()){
            finish();
        }
        Button button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(this);
        scanButton = (Button)findViewById(R.id.scanButton);
        scanButton.setOnClickListener(this);

    }

    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.button1:
                EditText qrInput = (EditText) findViewById(R.id.qrInput);
                String qrInputText = qrInput.getText().toString();
                Log.v(LOG_TAG, qrInputText);

                //Find screen size
                WindowManager manager = (WindowManager) getSystemService(WINDOW_SERVICE);
                Display display = manager.getDefaultDisplay();
                Point point = new Point();
                display.getSize(point);
                int width = point.x;
                int height = point.y;
                int smallerDimension = width < height ? width : height;
                smallerDimension = smallerDimension * 3/4;

                //Encode with a QR Code image
                QRCodeEncoder qrCodeEncoder = new QRCodeEncoder(qrInputText,
                        null,
                        Contents.Type.TEXT,
                        BarcodeFormat.QR_CODE.toString(),
                        smallerDimension);
                try {
                    Bitmap bitmap = qrCodeEncoder.encodeAsBitmap();
                    ImageView myImage = (ImageView) findViewById(R.id.imageView1);
                    myImage.setImageBitmap(bitmap);

                } catch (WriterException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.scanButton:
                startActivity(new Intent("com.gatewaycitychurch.SimpleScanner"));

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()){
            case R.id.action_logout:
                onLogoutAction();
                break;
            default:
        }

        return super.onOptionsItemSelected(item);
    }

    public void onLogoutAction(){
        ParseUser.logOut();
        CheckCurrentSession currentUser = new CheckCurrentSession();
        if(!currentUser.checkLogin()){
            finish();
        }
    }
}
