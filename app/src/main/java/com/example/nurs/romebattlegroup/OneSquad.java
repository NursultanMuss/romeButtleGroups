package com.example.nurs.romebattlegroup;

import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class OneSquad extends AppCompatActivity {

    Toolbar toolbar;
//    TabLayout tabLayout;
//    private int mMaxScrollSize;
    String frac;
    String squadName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.materialTabs);
        ViewPager viewPager = (ViewPager) findViewById(R.id.materialup_viewpager);
        AppBarLayout appbarLayout = (AppBarLayout) findViewById(R.id.appBarSquad);

        setContentView(R.layout.activity_one_squad);

        toolbar = (Toolbar) findViewById(R.id.squad_toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
//        mMaxScrollSize = appbarLayout.getTotalScrollRange();
        Intent intent = this.getIntent();
        if(intent.hasExtra(Intent.EXTRA_TEXT)){
            frac = intent.getStringExtra(Intent.EXTRA_TEXT);
        }
        if(intent.hasExtra("Battle group name")){
            squadName=intent.getStringExtra("Battle group name");
        }

        viewPager.setAdapter(new TabsAdapter(getSupportFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);

    }
    private static class TabsAdapter extends FragmentPagerAdapter {
        private static final int TAB_COUNT = 2;

        TabsAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return TAB_COUNT;
        }

        @Override
        public Fragment getItem(int i) {
            return FakePageFragment.newInstance();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "Tab " + String.valueOf(position);
        }
    }
}
