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

import com.ramsey.artifox.foodbook.adapter.FoodCardViewAdapter;
import com.ramsey.artifox.foodbook.adapter.NavigationDrawerAdapter;
import com.ramsey.artifox.foodbook.utils.PrepareLists;


public class FoodActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;

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
        setContentView(R.layout.foot_activity);

        mToolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(mToolbar);

        mRecyclerView = (RecyclerView) findViewById(R.id.foot_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        //============================================
        mNavDrawerRecyclerView = (RecyclerView) findViewById(R.id.nav_drawer_foot_recycler_view);
        mNavDrawerRecyclerView.setHasFixedSize(true);

        mNavDrawerAdapter = new NavigationDrawerAdapter(titles, icons);
        mNavDrawerRecyclerView.setAdapter(mNavDrawerAdapter);
//---------------------------------------- Navigation Drawer
        final GestureDetector mGestureDetector = new GestureDetector(FoodActivity.this,
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
                            intent = new Intent(FoodActivity.this,MainActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                            break;
                        case 2:
                            intent = new Intent(FoodActivity.this,MapsActivity.class);
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


//------------------------------------------


        mNavDrawerLayoutManager = new LinearLayoutManager(this);
        mNavDrawerRecyclerView.setLayoutManager(mNavDrawerLayoutManager);

        mNavDrawerLayout = (DrawerLayout) findViewById(R.id.footDrawerLayout);
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

        //============================================





        Intent intent = getIntent();
        int category = intent.getIntExtra("category", -1);

        extraIntent = new Intent(FoodActivity.this,FoodAbout.class);
        switch (category) {
            case 0:
                mAdapter = new FoodCardViewAdapter(PrepareLists.getSoups(), FoodActivity.this);
                extraIntent.putExtra("category", 0);
                break;
            case 1:
                mAdapter = new FoodCardViewAdapter(PrepareLists.getRolls(), FoodActivity.this);
                extraIntent.putExtra("category", 1);
                break;
            case 2:
                mAdapter = new FoodCardViewAdapter(PrepareLists.getSalads(), FoodActivity.this);
                extraIntent.putExtra("category", 2);
                break;
            case 3:
                mAdapter = new FoodCardViewAdapter(PrepareLists.getWasms(), FoodActivity.this);
                extraIntent.putExtra("category", 3);
                break;
            case 4:
                mAdapter = new FoodCardViewAdapter(PrepareLists.getSnaks(), FoodActivity.this);
                extraIntent.putExtra("category", 4);
                break;
        }


        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {

            @Override
            public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
                View child = recyclerView.findChildViewUnder(motionEvent.getX(), motionEvent.getY());

                if (child != null && mGestureDetector.onTouchEvent(motionEvent)) {
                    int position = recyclerView.getChildAdapterPosition(child);

                    extraIntent.putExtra("position", position);
                    startActivity(extraIntent);
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

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
    }

}
