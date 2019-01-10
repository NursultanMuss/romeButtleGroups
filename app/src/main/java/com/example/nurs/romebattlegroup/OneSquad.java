package com.example.nurs.romebattlegroup;

import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class OneSquad extends AppCompatActivity {

    Toolbar toolbar;
//    TabLayout tabLayout;
//    private int mMaxScrollSize;
    String frac;
    String squadName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_squad);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.materialTabs);
        ViewPager viewPager = (ViewPager) findViewById(R.id.materialup_viewpager);
        AppBarLayout appbarLayout = (AppBarLayout) findViewById(R.id.appBarSquad);


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

        tabLayout.setupWithViewPager(viewPager);
        TabsAdapter adapter = new TabsAdapter(getSupportFragmentManager());
        adapter.addFragment(new FakePageFragment(),"Описание");
        adapter.addFragment(new FakePageFragment(), "Видео");
        viewPager.setAdapter(adapter);


    }
    private static class TabsAdapter extends FragmentStatePagerAdapter{

        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        TabsAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        @Override
        public Fragment getItem(int i) {
            return mFragmentList.get(i);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }

        public void addFragment(Fragment fragment, String title){
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);

        }

    }
}
