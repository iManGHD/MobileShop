package com.armani.myapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.armani.myapplication.config.BaseConfig;
import com.armani.myapplication.config.Rest;
import com.armani.myapplication.config.ServiceResponse;
import com.armani.myapplication.moblie.dto.MobileInfoDTO;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MobileDetailActivity extends AppCompatActivity {

    TextView txtName, txtColor, txtGuarantee, txtWeight, txtSize, txtSimCounts, txtCamera, txtOsType, txtWaterProof, txtNetworkType, txtCompanyName, txtCompanyCountry;
    TextView lblName, lblColor, lblGuarantee, lblWeight, lblSize, lblSimCounts, lblCamera, lblOsType, lblWaterProof, lblNetworkType, lblCompanyName, lblCompanyCountry;
    Long positionId;
    MobileInfoDTO mobileInfoDTO;
    int i = 0;

    ImageView mobileImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile_detail);

        lblName = findViewById(R.id.lblName);
        lblColor = findViewById(R.id.lblCamera);
        lblGuarantee = findViewById(R.id.lblGuarantee);
        lblWeight = findViewById(R.id.lblWeight);
        lblSize = findViewById(R.id.lblSize);
        lblSimCounts = findViewById(R.id.lblSimCounts);
        lblCamera = findViewById(R.id.lblColor);
        lblOsType = findViewById(R.id.lblOsType);
        lblWaterProof = findViewById(R.id.lblWaterProof);
        lblNetworkType = findViewById(R.id.lblNetworkType);
        lblCompanyName = findViewById(R.id.lblCompanyName);
        lblCompanyCountry = findViewById(R.id.lblCompanyCountry);

        txtName = findViewById(R.id.txtName);
        txtColor = findViewById(R.id.txtColor);
        txtGuarantee = findViewById(R.id.txtGuarantee);
        txtWeight = findViewById(R.id.txtWeight);
        txtSize = findViewById(R.id.txtSize);
        txtSimCounts = findViewById(R.id.txtSimCounts);
        txtCamera = findViewById(R.id.txtCamera);
        txtOsType = findViewById(R.id.txtOsType);
        txtWaterProof = findViewById(R.id.txtWaterProof);
        txtNetworkType = findViewById(R.id.txtNetworkType);
        txtCompanyName = findViewById(R.id.txtCompanyName);
        txtCompanyCountry = findViewById(R.id.txtCompanyCountry);

        mobileImage = findViewById(R.id.imgTest);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            positionId = (Long) extras.get("positionId");
        }
        mobileInfoDTO = new MobileInfoDTO();
        showMobileDTO();
        showMobileImage();
    }

    private void showMobileDTO() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ServiceResponse serviceResponse = Rest.get(BaseConfig.MOBILE_SIMPLE_DTO + positionId, null, true);
                    if (serviceResponse.getResponseCode() == 200) {
                        i = serviceResponse.getResponseCode();
                        JSONObject result = serviceResponse.getJsonResult();
                        mobileInfoDTO.setName(result.getString("name"));
                        mobileInfoDTO.setColor(result.getString("color"));
                        mobileInfoDTO.setGuarantee(result.getBoolean("guarantee"));
                        mobileInfoDTO.setWeight(result.getLong("weight"));
                        mobileInfoDTO.setSize(result.getLong("size"));
                        mobileInfoDTO.setSimCounts(result.getLong("simCounts"));
                        mobileInfoDTO.setCamera(result.getLong("camera"));
                        mobileInfoDTO.setOsType(result.getString("osType"));
                        mobileInfoDTO.setWaterProof(result.getBoolean("waterProof"));
                        mobileInfoDTO.setNetworkType(result.getString("networkType"));
                        mobileInfoDTO.setCompanyName(result.getString("companyName"));
                        mobileInfoDTO.setCompanyCountry(result.getString("companyCountry"));
                        changeMobileDTO();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

    private void showMobileImage() {
        Thread thread = new Thread(new Runnable() {
            public void run() {
                try {
                    String url = BaseConfig.MOBILE_ONE_IMAGE + positionId;
                    byte[] serviceResponse = Rest.getForRetrieveImage(url);
                    changeMobileImage(serviceResponse);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

    private void changeMobileDTO() {
        MobileDetailActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                txtName.setText(mobileInfoDTO.getName());
                txtColor.setText(mobileInfoDTO.getColor());
                txtGuarantee.setText(mobileInfoDTO.getGuarantee().toString());
                txtWeight.setText(mobileInfoDTO.getWeight().toString());
                txtSize.setText(mobileInfoDTO.getSize().toString());
                txtSimCounts.setText(mobileInfoDTO.getSimCounts().toString());
                txtCamera.setText(mobileInfoDTO.getCamera().toString());
                txtOsType.setText(mobileInfoDTO.getOsType());
                txtWaterProof.setText(mobileInfoDTO.getWaterProof().toString());
                txtNetworkType.setText(mobileInfoDTO.getNetworkType());
                txtCompanyName.setText(mobileInfoDTO.getCompanyName());
                txtCompanyCountry.setText(mobileInfoDTO.getCompanyCountry());
            }
        });
    }

    private void changeMobileImage(final byte[] serviceResponse) {
        MobileDetailActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Bitmap bmp = BitmapFactory.decodeByteArray(serviceResponse, 0, serviceResponse.length);
                mobileImage.setImageBitmap(Bitmap.createScaledBitmap(bmp, mobileImage.getWidth(), mobileImage.getHeight(), false));
            }
        });
    }
}