package com.lzx.onematerial.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.lzx.onematerial.R;
import com.lzx.onematerial.adapter.MainViewPagerAdapter;
import com.lzx.onematerial.fragment.navigation.AuthorFragment;
import com.lzx.onematerial.fragment.navigation.OneFragment;
import com.lzx.onematerial.fragment.navigation.TopicFragment;
import com.lzx.onematerial.listener.ToolbarDateListener;
import com.lzx.onematerial.ui.MyViewPager;
import com.lzx.onematerial.utils.DeviceUtil;

import java.util.ArrayList;
import java.util.List;

import androidlib.activity.BaseActivity;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener, ToolbarDateListener{

    //?????????
    public static MainActivity INSTANCE = null;

    private MyViewPager mMainViewPager;

    private Toolbar toolbar;

    private TextView mDate;

    @Override
    protected void initViews(Bundle saveBundleInstance) {
        setContentView(R.layout.activity_main);

        INSTANCE = this;

        mDate = findViewById(R.id.main_date);

        Log.d("size", "mainactivity");
        DeviceUtil.init(this);

        setDoubleClickExit(800);

        initToolbar();

        initDrawer();

        initViewPager();

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    }

    private void initToolbar() {
        toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
    }


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mMainViewPager.setCurrentItem(0);
                    return true;
                case R.id.navigation_dashboard:
                    mMainViewPager.setCurrentItem(1);
                    return true;
                case R.id.navigation_notifications:
                    mMainViewPager.setCurrentItem(2);
                    return true;
            }
            return false;
        }
    };


    private void initViewPager() {
        mMainViewPager = findViewById(R.id.main_viewpager);
        mMainViewPager.setCancelScroll(true);

        List<Fragment> list = new ArrayList<>();
        list.add(new OneFragment());
        list.add(new TopicFragment());
        list.add(new AuthorFragment());

        MainViewPagerAdapter adapter = new MainViewPagerAdapter(getSupportFragmentManager(), list);
        mMainViewPager.setAdapter(adapter);
        mMainViewPager.setOffscreenPageLimit(2);
    }

    private void initDrawer() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }


    //日期设置和获取的接口实现
    public void setDate(String date) {
        mDate.setText(date);
    }

    public String getDate() {
        return mDate.getText().toString();
    }


    @Override
    protected void initData() {

    }

    @Override
    protected void initListeners() {

    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            newActivity(SearchActivity.class);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id) {
            /*case R.id.nav_settings:
                newActivity(SettingsActivity.class);
                break;*/
            case R.id.nav_tip:
                showTips();
                break;

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void showTips(){
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle(getString(R.string.motion_tip))
                .setMessage(getString(R.string.motion_tip_content))
                .show();
    }
}
