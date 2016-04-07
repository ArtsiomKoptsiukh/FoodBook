package com.ramsey.artifox.foodbook;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ramsey.artifox.foodbook.adapter.NavigationDrawerAdapter;
import com.ramsey.artifox.foodbook.model.Food;
import com.ramsey.artifox.foodbook.utils.ImageLoader;
import com.ramsey.artifox.foodbook.utils.PrepareLists;

import java.util.List;


public class FoodAbout extends AppCompatActivity {

    private int mPosition;
    private int mCategory;
    List<Food> mFoots;
    Food mFoot;
    Intent mIntent;

    ImageView image;
    int loader;
    String image_url;

    TextView mTvFootAbout;
    TextView mTvDesFootAbout;
    TextView mTvWeightFootAbout;
    TextView mTvPriceFootAbout;


    RecyclerView mNavDrawerRecyclerView;
    RecyclerView.Adapter mNavDrawerAdapter;
    RecyclerView.LayoutManager mNavDrawerLayoutManager;
    DrawerLayout mNavDrawerLayout;

    ActionBarDrawerToggle mDrawerToggle;

    Intent intent;

    private String[] titles = {"Каталог", "Контакты"};
    private int[] icons = {R.drawable.ic_home_black_24dp, R.drawable.ic_contacts_black_24dp};

    Toolbar mToolbar;
    Intent extraIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.foot_about_card_view);

        mToolbar = (Toolbar) findViewById(R.id.tool_bar_about);
        setSupportActionBar(mToolbar);

        //============================================
        mNavDrawerRecyclerView = (RecyclerView) findViewById(R.id.nav_drawer_foot_about_recycler_view);
        mNavDrawerRecyclerView.setHasFixedSize(true);

        mNavDrawerAdapter = new NavigationDrawerAdapter(titles, icons);
        mNavDrawerRecyclerView.setAdapter(mNavDrawerAdapter);
//---------------------------------------- Navigation Drawer
        final GestureDetector mGestureDetector = new GestureDetector(FoodAbout.this,
                new GestureDetector.SimpleOnGestureListener() {

                    @Override
                    public boolean onSingleTapUp(MotionEvent e) {
                        return true;
                    }

                });
        mNavDrawerRecyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {

            @Override
            public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
                View child = recyclerView.findChildViewUnder(motionEvent.getX(), motionEvent.getY());

                if (child != null && mGestureDetector.onTouchEvent(motionEvent)) {
                    mNavDrawerLayout.closeDrawers();
                    switch (recyclerView.getChildAdapterPosition(child)) {
                        case 1:
                            intent = new Intent(FoodAbout.this,MainActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                            break;
                        case 2:
                            intent = new Intent(FoodAbout.this,MapsActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                            startActivity(intent);
                            break;
                    }

                    return true;

                }

                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });


        mNavDrawerLayoutManager = new LinearLayoutManager(this);
        mNavDrawerRecyclerView.setLayoutManager(mNavDrawerLayoutManager);

        mNavDrawerLayout = (DrawerLayout) findViewById(R.id.footAboutDrawerLayout);
        mDrawerToggle = new ActionBarDrawerToggle(this, mNavDrawerLayout, mToolbar, R.string.openDrawer, R.string.closeDrawer) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };

        mNavDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
//============================================================

        mTvFootAbout = (TextView) findViewById(R.id.tv_foot_about);
        mTvDesFootAbout = (TextView) findViewById(R.id.tv_des_foot_about);
        mTvWeightFootAbout = (TextView) findViewById(R.id.tv_weight_foot_about);
        mTvPriceFootAbout = (TextView) findViewById(R.id.tv_price_foot_about);

        mIntent = getIntent();
        mCategory = mIntent.getIntExtra("category", 0);
        mPosition = mIntent.getIntExtra("position", 0);

        switch (mCategory) {
            case 0:
                mFoots = PrepareLists.getSoups();

                break;
            case 1:
                mFoots = PrepareLists.getRolls();

                break;
            case 2:
                mFoots =PrepareLists.getSalads();

                break;
            case 3:
                mFoots =PrepareLists.getWasms();

                break;
            case 4:
                mFoots =PrepareLists.getSnaks();

                break;
        }

        for (int i = 0; i < mFoots.size(); i++) {
            if (i == mPosition) {
                mFoot = mFoots.get(i);
                break;
            }
        }

        loader = R.drawable.ic_loader;
        image = (ImageView) findViewById(R.id.img_foot_about);
        image_url = mFoot.getImageId();



        mTvFootAbout.setText(mFoot.getName());
        mTvDesFootAbout.setText(mFoot.getDescription());
        mTvWeightFootAbout.setText(mFoot.getWeight());
        mTvPriceFootAbout.setText(mFoot.getPrice());
        ImageLoader imgLoader = new ImageLoader(FoodAbout.this);
        imgLoader.DisplayImage(image_url, loader, image);
    }
}
