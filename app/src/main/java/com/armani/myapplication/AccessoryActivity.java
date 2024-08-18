package com.armani.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.armani.myapplication.accessory.dto.AccessorySimpleDTO;
import com.armani.myapplication.config.BaseConfig;
import com.armani.myapplication.config.Rest;
import com.armani.myapplication.config.ServiceResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AccessoryActivity extends AppCompatActivity {

    ListView accessoriesListView;
    List<AccessorySimpleDTO> accessorySimpleDTOList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accessory);
        accessoriesListView = findViewById(R.id.accessoriesList);
        showAccessoryList();
        final Intent accessoryDetailIntent = new Intent(AccessoryActivity.this, AccessoryDetailActivity.class);
    }

    private void showAccessoryList() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ServiceResponse serviceResponse = Rest.get(BaseConfig.ACCESSORY_SIMPLE_LIST, null, false);
                    if (serviceResponse.getResponseCode() == 200) {
                        JSONArray result = serviceResponse.getJsonArrayResult();
                        for (int i = 0; i < result.length(); i++) {
                            JSONObject jsonObject = result.getJSONObject(i);
                            accessorySimpleDTOList.add(new AccessorySimpleDTO(jsonObject.getLong("id"), jsonObject.getString("name")));
                        }
                        changeAccessoryList();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

    private void changeAccessoryList() {
        AccessoryActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                final ArrayList<String> accessoryList = new ArrayList<>();
                for (AccessorySimpleDTO accessorySimpleDTO : accessorySimpleDTOList) {
                    accessoryList.add(accessorySimpleDTO.toString());
                }
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                        getApplicationContext(),
                        android.R.layout.simple_list_item_1,
                        accessoryList);

                accessoriesListView.setAdapter(arrayAdapter);
            }
        });
    }

    @Override
    public void onBackPressed() {
    }

}
