package com.armani.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.armani.myapplication.basket.BasketAccessory;
import com.armani.myapplication.basket.BasketDTO;
import com.armani.myapplication.basket.BasketEnum;
import com.armani.myapplication.basket.BasketMobile;
import com.armani.myapplication.config.BaseConfig;
import com.armani.myapplication.config.Rest;
import com.armani.myapplication.config.ServiceResponse;
import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class YourBasketActivity extends AppCompatActivity {
    JSONObject mobiles, accessories;
    BasketDTO basketDTO;
    SwipeMenuListView basketMobilesList, basketAccessoriesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_basket);
        basketAccessoriesList = findViewById(R.id.basketAccessoriesList);
        basketMobilesList = findViewById(R.id.basketMobileList);
        basketDTO = new BasketDTO();
        showBasket();
    }

    private void showBasket() {
        Thread thread = new Thread(new Runnable() {
            public void run() {
                try {
                    String url = BaseConfig.BASKET_INFO + BaseConfig.userDTO.getUserName();
                    ServiceResponse serviceResponse = Rest.get(url, null, true);
                    if (serviceResponse.getResponseCode() == 200) {
                        JSONObject basketInfo = serviceResponse.getJsonResult();
                        JSONArray mobiles = basketInfo.getJSONArray("mobiles");
                        JSONArray accessories = basketInfo.getJSONArray("accessories");
                        List<BasketAccessory> basketAccessories = new ArrayList<>();
                        List<BasketMobile> basketMobiles = new ArrayList<>();
                        for (int i = 0; i < mobiles.length(); i++) {
                            JSONObject eachMobile = mobiles.getJSONObject(i);
                            BasketMobile basketMobile = new BasketMobile();
                            basketMobile.setId(eachMobile.getLong("id"));
                            basketMobile.setName(eachMobile.getString("name"));
                            basketMobile.setCompanyName(eachMobile.getString("companyName"));
                            basketMobiles.add(basketMobile);
                        }
                        for (int i = 0; i < accessories.length(); i++) {
                            JSONObject eachAccessory = accessories.getJSONObject(i);
                            BasketAccessory basketAccessory = new BasketAccessory();
                            basketAccessory.setId(eachAccessory.getLong("id"));
                            basketAccessory.setName(eachAccessory.getString("name"));
                            basketAccessories.add(basketAccessory);
                        }
                        basketDTO.setBasketAccessories(basketAccessories);
                        basketDTO.setBasketMobiles(basketMobiles);
                        changeBasketLists();
                    }
                } catch (Exception e) {
                    BaseConfig.shortMsgBox(getApplicationContext(), getString(R.string.anErrorOccurredInOpenBasket));
                }
            }
        });
        thread.start();
    }

    private void changeBasketLists() {
        YourBasketActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                final ArrayList<String> mobileList = new ArrayList<>();
                for (BasketMobile basketMobile : basketDTO.getBasketMobiles()) {
                    mobileList.add(basketMobile.toString());
                }
                basketMobilesList.setAdapter(new ArrayAdapter(YourBasketActivity.this, android.R.layout.simple_list_item_1, mobileList));

                final ArrayList<String> accessoryList = new ArrayList<>();
                for (BasketAccessory basketAccessory : basketDTO.getBasketAccessories()) {
                    accessoryList.add(basketAccessory.toString());
                }
                basketAccessoriesList.setAdapter(new ArrayAdapter(YourBasketActivity.this, android.R.layout.simple_list_item_1, accessoryList));
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
                addToBasketItem.setBackground(R.color.basketSwipeRemoveItem);
                // set item width
                addToBasketItem.setWidth(100);
                // set a icon
                addToBasketItem.setIcon(R.drawable.backet_delete_ic);
                // add to menu
                menu.addMenuItem(addToBasketItem);

            }
        };

        basketMobilesList.setMenuCreator(creator);
        basketAccessoriesList.setMenuCreator(creator);

        basketAccessoriesList.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                Long positionId = basketDTO.getBasketAccessories().get(position).getId();
                removeFromBasket(BasketEnum.ACCESSORY, positionId);
                return false;
            }
        });
        basketMobilesList.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                Long positionId = basketDTO.getBasketMobiles().get(position).getId();
                removeFromBasket(BasketEnum.MOBILE, positionId);
                return false;
            }
        });
    }

    private void removeFromBasket(final BasketEnum basketEnum, final Long positionId) {
        Thread thread = new Thread(new Runnable() {
            public void run() {
                try {
                    String url = "";
                    Map<String, String> param = new HashMap<>();
                    //TODO
                    if (basketEnum == BasketEnum.MOBILE) {
                        param.put("mobileId", String.valueOf(positionId));
                        url = BaseConfig.BASKET_INFO;
                    } else {
                        param.put("accessoryId", String.valueOf(positionId));
                        url = BaseConfig.BASKET_INFO;
                    }
                    ServiceResponse serviceResponse = Rest.get(url, param, true);
                    if (serviceResponse.getResponseCode() == 200) {
                        JSONObject basketInfo = serviceResponse.getJsonResult();
                        JSONArray mobiles = basketInfo.getJSONArray("mobiles");
                        JSONArray accessories = basketInfo.getJSONArray("accessories");
                        List<BasketAccessory> basketAccessories = new ArrayList<>();
                        List<BasketMobile> basketMobiles = new ArrayList<>();
                        for (int i = 0; i < mobiles.length(); i++) {
                            JSONObject eachMobile = mobiles.getJSONObject(i);
                            BasketMobile basketMobile = new BasketMobile();
                            basketMobile.setId(eachMobile.getLong("id"));
                            basketMobile.setName(eachMobile.getString("name"));
                            basketMobile.setCompanyName(eachMobile.getString("companyName"));
                            basketMobiles.add(basketMobile);
                        }
                        for (int i = 0; i < accessories.length(); i++) {
                            JSONObject eachAccessory = accessories.getJSONObject(i);
                            BasketAccessory basketAccessory = new BasketAccessory();
                            basketAccessory.setId(eachAccessory.getLong("id"));
                            basketAccessory.setName(eachAccessory.getString("name"));
                            basketAccessories.add(basketAccessory);
                        }
                        basketDTO.setBasketAccessories(basketAccessories);
                        basketDTO.setBasketMobiles(basketMobiles);
                        changeBasketLists();
                    }
                } catch (Exception e) {
                    BaseConfig.shortMsgBox(getApplicationContext(), getString(R.string.anErrorOccurredInOpenBasket));
                }
            }
        });
        thread.start();
    }
}

