package com.example.jswin.designproject1;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.jswin.designproject1.Fragments.CameraFrag;
import com.example.jswin.designproject1.Fragments.ChatFrag;
import com.example.jswin.designproject1.Fragments.StoryFrag;

// This doesnt do anything yet
// Maybe I will redesign this to be the main page
// where the user can take pictures and send tem
public class MainActivity extends AppCompatActivity {

    // Controlls pager and adapter
    FragmentPagerAdapter adapterViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create the class and call the function start fetching to fetch data
        PalInformation palInformationListener = new PalInformation();
        palInformationListener.startFetching();

        // activity_main.xml import the id name viewPager
        ViewPager viewPager = findViewById(R.id.viewPager);

        // Associate the adapter view pager with fragment pager adapter
        adapterViewPager = new MyPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapterViewPager);
        // Manually set to pager case 1 -> which is the camera (See switch statement)
        viewPager.setCurrentItem(1);

    }
    // Three methods MyPagerAdapter, getItem, and getCount were needed
    // Threw an error for the static class MyPagerAdapter without them
    public static class MyPagerAdapter extends FragmentPagerAdapter{

        // Don't touch for now, not sure what this does but it keeps the program happy
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        // Returns (depending on position of fragment "frag") the fragment that we need
        public Fragment getItem(int frag) {
            switch(frag){

                case 0:
                    // return the fragment that holds the chat
                    return ChatFrag.newInstance();
                case 1:
                    // return the fragment that holds the camera
                    return CameraFrag.newInstance();
                case 2:
                    // return the fragment that holds the story
                    return StoryFrag.newInstance();

            }
            return null;
        }

        @Override
        // This returns the number of pages we have within our view pager
        public int getCount() {
            // I manually set this from 0 to 3 because for now I want 3 pages
            return 3;
        }
    }
}
