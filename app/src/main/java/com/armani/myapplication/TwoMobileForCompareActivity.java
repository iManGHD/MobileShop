package com.armani.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TwoMobileForCompareActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_mobile_for_compare);
        final EditText firstMobile = findViewById(R.id.firstMobileName);
        final EditText secondMobile = findViewById(R.id.secondMobileName);


        Button btnCompare = findViewById(R.id.btnCompare);
        final Intent compareActivityIntent = new Intent(TwoMobileForCompareActivity.this, CompareActivity.class);

        btnCompare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (firstMobile.getText().toString() != null && !firstMobile.getText().toString().isEmpty() &&
                        secondMobile.getText().toString() != null && !secondMobile.getText().toString().isEmpty()) {
                    compareActivityIntent.putExtra("firstMobile", firstMobile.getText().toString());
                    compareActivityIntent.putExtra("secondMobile", secondMobile.getText().toString());
                    startActivity(compareActivityIntent);
                } else
                    Toast.makeText(TwoMobileForCompareActivity.this, getString(R.string.pleaseEnterMobilesNameError), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
