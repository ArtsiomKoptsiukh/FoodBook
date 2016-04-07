package com.ramsey.artifox.foodbook;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import com.ramsey.artifox.foodbook.adapter.CategoryCardViewAdapter;
import com.ramsey.artifox.foodbook.adapter.FoodCardViewAdapter;
import com.ramsey.artifox.foodbook.adapter.NavigationDrawerAdapter;
import com.ramsey.artifox.foodbook.model.Food;
import com.ramsey.artifox.foodbook.utils.ConnectionDetector;
import com.ramsey.artifox.foodbook.utils.PrepareLists;
import com.ramsey.artifox.foodbook.utils.XMLParser;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    Toolbar mToolbar;
    RecyclerView mNavDrawerRecyclerView;
    RecyclerView.Adapter mNavDrawerAdapter;
    RecyclerView.LayoutManager mNavDrawerLayoutManager;
    DrawerLayout mNavDrawerLayout;

    RecyclerView mCardRecyclerView;
    RecyclerView.LayoutManager mCardLayoutManager;
    RecyclerView.Adapter mCardAdapter;

    ActionBarDrawerToggle mDrawerToggle;

    Intent intent;
    final int DIALOG_EXIT = 1;

    XMLParser mXMLParser;
    List<Food> mFoots;
    PrepareLists prepareLists;

    private String[] titles = {"Каталог", "Контакты"};
    private int[] icons = {R.drawable.ic_home_black_24dp, R.drawable.ic_contacts_black_24dp};
    //====================================
    private RecyclerView mFootRecyclerView;
    private LinearLayoutManager mFootLayoutManager;
    private FoodCardViewAdapter mFootAdapter;

    //=====================================

    private boolean mState;

    Boolean isInternetPresent = false;
    ConnectionDetector cd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        mState = true;
        cd = new ConnectionDetector(getApplicationContext());
        isInternetPresent = cd.isConnectingToInternet();



        if (savedInstanceState != null) {
            mState = savedInstanceState.getBoolean("state");
        }
        if (mState && isInternetPresent) {
            try {
                mXMLParser = new XMLParser(this);
                mXMLParser.execute();
                mFoots = mXMLParser.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            prepareLists = new PrepareLists(mFoots);
            mState = false;
        } else if (!mState) {

        }else{
            showDialog(DIALOG_EXIT);
        }



        mToolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(mToolbar);

        mNavDrawerRecyclerView = (RecyclerView) findViewById(R.id.nav_drawer_recycler_view);
        mNavDrawerRecyclerView.setHasFixedSize(true);

        mNavDrawerAdapter = new NavigationDrawerAdapter(titles, icons);
        mNavDrawerRecyclerView.setAdapter(mNavDrawerAdapter);
//---------------------------------------- Navigation Drawer
        final GestureDetector mGestureDetector = new GestureDetector(MainActivity.this,
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
                            intent = new Intent(MainActivity.this, MainActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                            startActivity(intent);
                            break;
                        case 2:
                            intent = new Intent(MainActivity.this, MapsActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                            break;
                    }
                    //Toast.makeText(MainActivity.this, "The Item Clicked is: " + recyclerView.getChildPosition(child), Toast.LENGTH_SHORT).show();

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

        mNavDrawerLayout = (DrawerLayout) findViewById(R.id.DrawerLayout);
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

        mCardRecyclerView = (RecyclerView) findViewById(R.id.card_recycler_view);
        mCardRecyclerView.setHasFixedSize(true);

        mCardLayoutManager = new LinearLayoutManager(this);
        mCardRecyclerView.setLayoutManager(mCardLayoutManager);

        mCardAdapter = new CategoryCardViewAdapter(this);
        mCardRecyclerView.setAdapter(mCardAdapter);
        //================================================
        mCardRecyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {

            @Override
            public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
                View child = recyclerView.findChildViewUnder(motionEvent.getX(), motionEvent.getY());

                if (child != null && mGestureDetector.onTouchEvent(motionEvent)) {
                    switch (recyclerView.getChildAdapterPosition(child)) {
                        case 0:
                            intent = new Intent(MainActivity.this, FoodActivity.class);
                            intent.putExtra("category", 0);
                            startActivity(intent);
                            break;
                        case 1:
                            intent = new Intent(MainActivity.this, FoodActivity.class);
                            intent.putExtra("category", 1);
                            startActivity(intent);
                            break;
                        case 2:
                            intent = new Intent(MainActivity.this, FoodActivity.class);
                            intent.putExtra("category", 2);
                            startActivity(intent);
                            break;
                        case 3:
                            intent = new Intent(MainActivity.this, FoodActivity.class);
                            intent.putExtra("category", 3);
                            startActivity(intent);
                            break;
                        case 4:
                            intent = new Intent(MainActivity.this, FoodActivity.class);
                            intent.putExtra("category", 4);
                            startActivity(intent);
                            break;
                    }

                    return true;

                }

                return false;

            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });
        //==============================================
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putBoolean("state", mState);
    }

    @Nullable
    @Override
    protected Dialog onCreateDialog(int id) {
        if (id == DIALOG_EXIT) {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Отсутствует соединение с интернетом");
            builder.setMessage("Подключитесь к интеренету и попробуйте снова");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    System.exit(0);
                }
            });

            AlertDialog alertDialog = builder.create();
            return alertDialog;
        }
        return super.onCreateDialog(id);
    }


}
