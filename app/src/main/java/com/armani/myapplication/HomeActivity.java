package com.armani.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.armani.myapplication.config.BaseConfig;

public class HomeActivity extends AppCompatActivity {
    String username;
    String family;
    TextView txtInfo;
    Button btnMobile;
    Button btnAccessory;
    Button btnCompare;
    Button btnShowYourBasket;
    Intent compareIntent;
    Intent yourBasketIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        compareIntent = new Intent(getApplicationContext(), TwoMobileForCompareActivity.class);
        yourBasketIntent = new Intent(HomeActivity.this, YourBasketActivity.class);

        txtInfo = findViewById(R.id.txtInfo);
        btnMobile = findViewById(R.id.btnMobile);
        btnAccessory = findViewById(R.id.btnAccessory);
        btnCompare = findViewById(R.id.btnCompare);
        btnShowYourBasket = findViewById(R.id.btnYourBasket);
        btnShowYourBasket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(yourBasketIntent);
            }
        });
        btnCompare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(compareIntent);
            }
        });

        final Intent mobileIntent = new Intent(HomeActivity.this, MobileActivity.class);
        final Intent accessoryIntent = new Intent(HomeActivity.this, AccessoryActivity.class);

        btnMobile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(mobileIntent);
            }
        });

        btnAccessory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(accessoryIntent);
            }
        });

        try {
            username = BaseConfig.getUserDTO().getUserName();
            family = BaseConfig.getUserDTO().getFamily();
            String s = " " + family + " " + getString(R.string.welcome_message);
            txtInfo.setText(s);
        } catch (Exception e) {
            System.out.println("nothing");
        }
    }

    @Override
    public void onBackPressed() {
    }
}
