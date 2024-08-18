package com.armani.myapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import com.armani.myapplication.config.BaseConfig;
import com.armani.myapplication.config.Rest;
import java.util.HashMap;
import java.util.Map;

public class MobileContentsActivity extends AppCompatActivity {

    ImageView testImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile_contents);
        testImage = findViewById(R.id.imgTest);
        showMobileImage();
    }

    private void showMobileImage() {
                Thread thread = new Thread(new Runnable() {
            public void run() {
                try {
                    Map<String, String> param = new HashMap<>();
                    param.put("imageName", "S9.jpg");
                    byte[] serviceResponse = Rest.postWithQueryParam(BaseConfig.MOBILE_IMAGE, param, true);
                    changeMobileImage(serviceResponse);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

    private void changeMobileImage(final byte[] serviceResponse) {
        MobileContentsActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Bitmap bmp = BitmapFactory.decodeByteArray(serviceResponse, 0, serviceResponse.length);
                testImage.setImageBitmap(Bitmap.createScaledBitmap(bmp, testImage.getWidth(), testImage.getHeight(), false));
            }
        });
    }

    @Override
    public void onBackPressed() {
    }
}
