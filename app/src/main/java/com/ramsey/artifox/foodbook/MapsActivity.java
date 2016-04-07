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

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.ramsey.artifox.foodbook.adapter.NavigationDrawerAdapter;

public class MapsActivity extends AppCompatActivity {
    static final LatLng MINSK = new LatLng(53.905631, 27.553950);
    static final LatLng PERSON = new LatLng(53.901360, 27.563199);
    private GoogleMap map;

    Toolbar mToolbar;

    RecyclerView mNavDrawerRecyclerView;
    RecyclerView.Adapter mNavDrawerAdapter;
    RecyclerView.LayoutManager mNavDrawerLayoutManager;
    DrawerLayout mNavDrawerLayout;

    ActionBarDrawerToggle mDrawerToggle;

    private String[] titles = {"Каталог", "Контакты"};
    private int[] icons = {R.drawable.ic_home_black_24dp, R.drawable.ic_contacts_black_24dp};

    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        mToolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(mToolbar);

        //===================================
        //============================================
        mNavDrawerRecyclerView = (RecyclerView) findViewById(R.id.nav_drawer_map_recycler_view);
        mNavDrawerRecyclerView.setHasFixedSize(true);

        mNavDrawerAdapter = new NavigationDrawerAdapter(titles, icons);
        mNavDrawerRecyclerView.setAdapter(mNavDrawerAdapter);
//---------------------------------------- Navigation Drawer
        final GestureDetector mGestureDetector = new GestureDetector(MapsActivity.this,
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
                            intent = new Intent(MapsActivity.this,MainActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                            break;
                        case 2:
                            intent = new Intent(MapsActivity.this,MapsActivity.class);
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

        mNavDrawerLayout = (DrawerLayout) findViewById(R.id.mapDrawerLayout);
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
        //=====================================

        map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
                .getMap();
        Marker minsk = map.addMarker(new MarkerOptions().position(MINSK)
                .title("Minsk Cafe"));
        Marker person = map.addMarker(new MarkerOptions()
                .position(PERSON)
                .title("Person")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));

        // Move the camera instantly to minsk with a zoom of 15.
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(MINSK, 15));

        // Zoom in, animating the camera.
        map.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);
    }

}
