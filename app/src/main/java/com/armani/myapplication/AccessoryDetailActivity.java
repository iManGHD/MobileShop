package com.armani.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import com.armani.myapplication.accessory.dto.AccessoryInfoDTO;
import com.armani.myapplication.config.BaseConfig;
import com.armani.myapplication.config.Rest;
import com.armani.myapplication.config.ServiceResponse;
import org.json.JSONObject;

public class AccessoryDetailActivity extends AppCompatActivity {

    TextView txtName,txtPrice;
    TextView lblName,lblPrice;
    ImageView imageView;
    Long positionId;
    AccessoryInfoDTO accessoryInfoDTO ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accessory_detail);

        txtName = findViewById(R.id.txtName);
        txtPrice = findViewById(R.id.txtPrice);
        lblName = findViewById(R.id.lblName);
        lblPrice = findViewById(R.id.lblPrice);
        imageView = findViewById(R.id.imgAccessory);
        Bundle extras  =  getIntent().getExtras();
        if (extras != null) {
            positionId = (Long) extras.get("positionId");
        }
        accessoryInfoDTO =  new AccessoryInfoDTO();
        showAccessoryDTO();
    }

    private void showAccessoryDTO(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ServiceResponse serviceResponse = Rest.get(BaseConfig.ACCESSORY_SIMPLE_DTO + positionId, null, true);
                    if (serviceResponse.getResponseCode() == 200) {
                        JSONObject result = serviceResponse.getJsonResult();
                        accessoryInfoDTO.setName(result.getString("name"));
                        accessoryInfoDTO.setPrice(result.getLong("price"));
                        changeAccessoryDTO();
                }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }
    private void changeAccessoryDTO(){
        AccessoryDetailActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                txtName.setText(accessoryInfoDTO.getName());
                txtPrice.setText(accessoryInfoDTO.getPrice().toString());
            }
        });
    }

   /* @Override
    public void onBackPressed() {
    }*/
}
