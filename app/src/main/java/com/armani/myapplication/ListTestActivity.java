package com.armani.myapplication;

import android.content.pm.ApplicationInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.armani.myapplication.config.BaseConfig;
import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;

import java.util.ArrayList;
import java.util.List;

public class ListTestActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_test);
        SwipeMenuListView listView = (SwipeMenuListView) findViewById(R.id.listView);
        ArrayList<String> list = new ArrayList<>();
        list.add("hamid");
        list.add("iman");
        list.add("ali");


        ArrayAdapter adapter = new ArrayAdapter(ListTestActivity.this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);


        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                // create "delete" item
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


                // create "delete" item
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

        listView.setMenuCreator(creator);

        listView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                        Toast.makeText(getApplicationContext(), "hi" + index, Toast.LENGTH_LONG).show();

                        break;
                    case 1:
                        Toast.makeText(getApplicationContext(), "hi" + index, Toast.LENGTH_LONG).show();
                        break;
                }
                return false;
            }
        });
    }
}



