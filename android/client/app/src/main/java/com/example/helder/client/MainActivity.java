package com.example.helder.client;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.Fragment;

import android.support.v7.widget.Toolbar;

import android.support.v4.app.FragmentManager;

import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import android.widget.ImageButton;
import android.widget.Toast;

import com.example.helder.client.Fragments.animalFragment;
import com.example.helder.client.Fragments.mapFragment;
import com.example.helder.client.Fragments.routeFragment;
import com.example.helder.client.DataBase.DB;

public class MainActivity extends AppCompatActivity{
    public static String UserID;

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    ImageButton btacept;
    ImageButton btclear;

    DB mDBHelper;
    SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        UserID = getIntent().getExtras().getString(Utils.param_Userid);

        mDBHelper = new DB(this);
        db = mDBHelper.getReadableDatabase();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.action_logout:
                Intent d = new Intent(this, LoginActivity.class);
                d.putExtra(Utils.logout, true);
                startActivity(d);

                finish();
                return true;
            case R.id.action_info:
                Intent i = new Intent(this, InforActivity.class);
                startActivity(i);
                //Toast.makeText(this, "info", Toast.LENGTH_SHORT).show();
                return true;
            default: return super.onOptionsItemSelected(item);
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch(position){
                case 0: return mapFragment.newInstance();
                case 1: return animalFragment.newInstance();
                case 2: return routeFragment.newInstance();
                default: return mapFragment.newInstance();
            }
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            // 0 - Default -> mapFragment
            // 1 -> animalFragment
            // 2 -> routeFragment
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            //Subistituir por strings
            switch (position) {
                case 0:
                    return getString(R.string.fragment_map);
                case 1:
                    return getString(R.string.fragment_animals);
                case 2:
                    return getString(R.string.fragment_route);
            }
            return null;
        }
    }

}
