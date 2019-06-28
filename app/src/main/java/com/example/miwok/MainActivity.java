package com.example.miwok;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private ViewPager mViewPager;
    private PagerAdapter mPagerAdapter;
    private final int NUM_PAGES = 4;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_screen_slide);

        mViewPager = findViewById(R.id.pager);
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mPagerAdapter);

        TabLayout tabLayout= findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(mViewPager);

    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter{
        public ScreenSlidePagerAdapter (FragmentManager fragmentManager){
            super(fragmentManager);
            mContext = getApplicationContext();
        }

        @Override
        public Fragment getItem(int i) {
            Fragment fragments[] = new Fragment[] {new NumbersFragment(), new FamilyFragment(),
                    new ColorsFragment(), new PhrasesFragment()};

            return fragments[i];
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            String pageTitles[] = new String[] {getString(R.string.category_numbers),
                    getString(R.string.category_family),
                    getString(R.string.category_colors),
                    getString(R.string.category_phrases)};
            return pageTitles[position];
        }
    }
}
