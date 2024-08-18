package com.armani.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import com.armani.myapplication.config.BaseConfig;
import com.armani.myapplication.config.Rest;
import com.armani.myapplication.config.ServiceResponse;
import com.armani.myapplication.config.dto.UserDTO;
import com.armani.myapplication.moblie.dto.MobileSimpleDTO;
import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MobileActivity extends AppCompatActivity {

    SwipeMenuListView mobilesListView;
    List<MobileSimpleDTO> mobileSimpleDTOList = new ArrayList<>();
    Long positionId;
    Intent mobileDetailIntent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile);

        //region Home Button
        final Intent homeIntent = new Intent(getApplicationContext(), HomeActivity.class);
        ImageView homeButton = findViewById(R.id.homeImage);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(homeIntent);
            }
        });
        //endregion

        mobileDetailIntent = new Intent(MobileActivity.this, MobileDetailActivity.class);

        mobilesListView = findViewById(R.id.mobileList);
        showMobileList();

    }

    private void showMobileList() {
        Thread thread = new Thread(new Runnable() {
            public void run() {
                try {
                    ServiceResponse serviceResponse = Rest.get(BaseConfig.MOBILE_SIMPLE_LIST, null, false);
                    if (serviceResponse.getResponseCode() == 200) {
                        JSONArray result = serviceResponse.getJsonArrayResult();
                        for (int i = 0; i < result.length(); i++) {
                            JSONObject jsonObject = result.getJSONObject(i);
                            mobileSimpleDTOList.add(new MobileSimpleDTO(jsonObject.getLong("id"), jsonObject.getString("name"), jsonObject.getString("companyName")));
                        }
                        changeMobileList();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();

    }

    private void changeMobileList() {
        MobileActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                final ArrayList<String> mobileList = new ArrayList<>();
                for (MobileSimpleDTO mobileSimpleDTO : mobileSimpleDTOList) {
                    mobileList.add(mobileSimpleDTO.toString());
                }

                ArrayAdapter adapter = new ArrayAdapter(MobileActivity.this, android.R.layout.simple_list_item_1, mobileList);
                mobilesListView.setAdapter(adapter);
                effectToListView();
            }
        });
    }

    public void effectToListView() {
        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {

                SwipeMenuItem addToBasketItem = new SwipeMenuItem(
                        getApplicationContext());
                // set item background
                addToBasketItem.setBackground(R.color.swipeListSecondItem);
                // set item width
                addToBasketItem.setWidth(170);
                // set a icon
                addToBasketItem.setIcon(R.drawable.listview_basket);
                // add to menu
                menu.addMenuItem(addToBasketItem);

                // create "detail" item
                SwipeMenuItem detailItem = new SwipeMenuItem(
                        getApplicationContext());
                // set item background
                detailItem.setBackground(R.color.swipeListFirstItem);
                // set item width
                detailItem.setWidth(170);
                // set a icon
                detailItem.setIcon(R.drawable.listview_detail);
                // add to menu
                menu.addMenuItem(detailItem);
            }
        };

        mobilesListView.setMenuCreator(creator);

        mobilesListView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                        //add to basket
                        positionId = mobileSimpleDTOList.get(position).getId();
                        addToBasket();
                        break;
                    case 1:
                        //detail
                        positionId = mobileSimpleDTOList.get(position).getId();
                        mobileDetailIntent.putExtra("positionId", positionId);
                        startActivity(mobileDetailIntent);
                        break;
                }
                return false;
            }
        });
    }

    private void addToBasket() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String url = BaseConfig.BASKET_ADD + BaseConfig.userDTO.getUserName() + BaseConfig.MOBILE_ADD + positionId;
                    ServiceResponse serviceResponse = Rest.postWithoutAnyThing(url);
                    if (serviceResponse.getResponseCode() == 201) {
                        changeBasketDTO();
                    } else {
                        errorHandleOnBasket();
                    }
                } catch (Exception e) {
                    errorHandleOnBasket();
                }
            }
        });
        thread.start();
    }

    private void changeBasketDTO() {
        MobileActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MobileActivity.this, getString(R.string.goodAddedToBasketSuccessfully), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void errorHandleOnBasket() {
        MobileActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                BaseConfig.shortMsgBox(getApplicationContext(), getString(R.string.anErrorOccurredInBasketAdd));
            }
        });
    }

    @Override
    public void onBackPressed() {
    }
}
