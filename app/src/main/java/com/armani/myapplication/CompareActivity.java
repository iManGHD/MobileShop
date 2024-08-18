package com.armani.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.armani.myapplication.config.BaseConfig;
import com.armani.myapplication.config.Rest;
import com.armani.myapplication.config.ServiceResponse;

import org.json.JSONException;
import org.json.JSONObject;

public class CompareActivity extends AppCompatActivity {
    TextView firstMobileText;
    TextView secondMobileText;
    String firstMobile;
    String secondMobile;
    TextView firstMobileColor, firstMobileGuarantee, firstMobileWeight, firstMobileSize,
            firstMobileSimCounts, firstMobileCpuType, firstMobileMemory, firstMobileCamera,
            firstMobileOsType, firstMobileWaterProof, firstMobileNetworkType, firstMobileCompanyName,
            firstMobileCountry, secondMobileColor, secondMobileGuarantee,
            secondMobileWeight, secondMobileSize, secondMobileSimCounts, secondMobileCpuType,
            secondMobileMemory, secondMobileCamera, secondMobileOsType, secondMobileWaterProof,
            secondMobileNetworkType, secondMobileCompanyName, secondMobileCompanyCountry;
    JSONObject firstMobileSpec;
    JSONObject secondMobileSpec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compare);

        //region init
        firstMobileText = findViewById(R.id.firstMobileName);
        firstMobileColor = findViewById(R.id.firstMobileColor);
        firstMobileGuarantee = findViewById(R.id.firstMobileGuarantee);
        firstMobileWeight = findViewById(R.id.firstMobileWeight);
        firstMobileSize = findViewById(R.id.firstMobileSize);
        firstMobileSimCounts = findViewById(R.id.firstMobileSimCounts);
        firstMobileCpuType = findViewById(R.id.firstMobileCpuType);
        firstMobileMemory = findViewById(R.id.firstMobileMemory);
        firstMobileCamera = findViewById(R.id.firstMobileCamera);
        firstMobileOsType = findViewById(R.id.firstMobileOsType);
        firstMobileWaterProof = findViewById(R.id.firstMobileWaterProof);
        firstMobileNetworkType = findViewById(R.id.firstMobileNetworkType);
        firstMobileCompanyName = findViewById(R.id.firstMobileCompanyName);
        firstMobileCountry = findViewById(R.id.firstMobileCountry);
        secondMobileText = findViewById(R.id.secondMobileName);
        secondMobileColor = findViewById(R.id.secondMobileColor);
        secondMobileGuarantee = findViewById(R.id.secondMobileGuarantee);
        secondMobileWeight = findViewById(R.id.secondMobileWeight);
        secondMobileSize = findViewById(R.id.secondMobileSize);
        secondMobileSimCounts = findViewById(R.id.secondMobileSimCounts);
        secondMobileCpuType = findViewById(R.id.secondMobileCpuType);
        secondMobileMemory = findViewById(R.id.secondMobileMemory);
        secondMobileCamera = findViewById(R.id.secondMobileCamera);
        secondMobileOsType = findViewById(R.id.secondMobileOsType);
        secondMobileWaterProof = findViewById(R.id.secondMobileWaterProof);
        secondMobileNetworkType = findViewById(R.id.secondMobileNetworkType);
        secondMobileCompanyName = findViewById(R.id.secondMobileCompanyName);
        secondMobileCompanyCountry = findViewById(R.id.secondMobileCompanyCountry);
        //endregion

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            firstMobile = (String) extras.get("firstMobile");
        }
        if (extras != null) {
            secondMobile = (String) extras.get("secondMobile");
        }

        firstMobileText.setText(firstMobile);
        secondMobileText.setText(secondMobile);

        compareMobiles();
    }

    private void compareMobiles() {
        Thread thread = new Thread(new Runnable() {
            public void run() {
                try {
                    JSONObject mobileCompareJsonRequest = processRequest();
                    ServiceResponse serviceResponse = Rest.post(BaseConfig.MOBILE_COMPARE, null, mobileCompareJsonRequest, true);
                    if (serviceResponse.getResponseCode() == 200) {
                        JSONObject result = serviceResponse.getJsonResult();
                        firstMobileSpec = result.getJSONObject("firstMobileSpec");
                        secondMobileSpec = result.getJSONObject("secondMobileSpec");
                        changeUIAfterResponse();
                    } else {
                        BaseConfig.shortMsgBox(getApplicationContext(), getString(R.string.anErrorOccurredInCompare));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();

    }

    private void changeUIAfterResponse() {
        CompareActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    firstMobileText.setText(firstMobileSpec.get("name").toString());
                    firstMobileColor.setText(firstMobileSpec.get("color").toString());
                    firstMobileGuarantee.setText(firstMobileSpec.get("guarantee").toString());
                    firstMobileWeight.setText(firstMobileSpec.get("weight").toString());
                    firstMobileSize.setText(firstMobileSpec.get("size").toString());
                    firstMobileSimCounts.setText(firstMobileSpec.get("simCounts").toString());
                    firstMobileCpuType.setText(firstMobileSpec.get("cpuType").toString());
                    firstMobileMemory.setText(firstMobileSpec.get("memory").toString());
                    firstMobileCamera.setText(firstMobileSpec.get("camera").toString());
                    firstMobileOsType.setText(firstMobileSpec.get("osType").toString());
                    firstMobileWaterProof.setText(firstMobileSpec.get("waterProof").toString());
                    firstMobileNetworkType.setText(firstMobileSpec.get("networkType").toString());
                    firstMobileCompanyName.setText(firstMobileSpec.get("companyName").toString());
                    firstMobileCountry.setText(firstMobileSpec.get("companyCountry").toString());

                    secondMobileText.setText(secondMobileSpec.get("name").toString());
                    secondMobileColor.setText(secondMobileSpec.get("color").toString());
                    secondMobileGuarantee.setText(secondMobileSpec.get("guarantee").toString());
                    secondMobileWeight.setText(secondMobileSpec.get("weight").toString());
                    secondMobileSize.setText(secondMobileSpec.get("size").toString());
                    secondMobileSimCounts.setText(secondMobileSpec.get("simCounts").toString());
                    secondMobileCpuType.setText(secondMobileSpec.get("cpuType").toString());
                    secondMobileMemory.setText(secondMobileSpec.get("memory").toString());
                    secondMobileCamera.setText(secondMobileSpec.get("camera").toString());
                    secondMobileOsType.setText(secondMobileSpec.get("osType").toString());
                    secondMobileWaterProof.setText(secondMobileSpec.get("waterProof").toString());
                    secondMobileNetworkType.setText(secondMobileSpec.get("networkType").toString());
                    secondMobileCompanyName.setText(secondMobileSpec.get("companyName").toString());
                    secondMobileCompanyCountry.setText(secondMobileSpec.get("companyCountry").toString());


                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(), "مشکلی در پردازش اطلاعات بوجود آمده است", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private JSONObject processRequest() {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("firstMobileName", firstMobile);
            jsonObject.put("secondMobileName", secondMobile);
            return jsonObject;

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
