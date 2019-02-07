package com.example.nurs.romebattlegroup;

import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nurs.romebattlegroup.data.DataAccess;

import java.util.ArrayList;
import java.util.List;

public class OneSquad extends AppCompatActivity {

    Toolbar toolbar;
//    TabLayout tabLayout;
//    private int mMaxScrollSize;
    private String frac;
    String squadName;
    String youTube;
    String description;
    String squadImage;
    private DataAccess dbAccess;
    private Cursor c_squad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_squad);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.materialTabs);
        ViewPager viewPager = (ViewPager) findViewById(R.id.materialup_viewpager);
        ImageView imageView = (ImageView) findViewById(R.id.imageViewSquad);
        AppBarLayout appbarLayout = (AppBarLayout) findViewById(R.id.appBarSquad);
        TextView titleText = (TextView) findViewById(R.id.toolbar_title);



        toolbar = (Toolbar) findViewById(R.id.squad_toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);

        }
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
        if(intent.hasExtra("YouTubeId")){
            youTube=intent.getStringExtra("YouTubeId");
        }
        if(intent.hasExtra("Description")){
            description=intent.getStringExtra("Description");
        }
        if(intent.hasExtra("squadImage")){
            squadImage=intent.getStringExtra("squadImage");
        }

        int imgId =imageView.getResources().getIdentifier(squadImage,"drawable", "com.example.nurs.romebattlegroup");
        imageView.setImageResource(imgId);
        tabLayout.setupWithViewPager(viewPager);
        TabsAdapter adapter = new TabsAdapter(getSupportFragmentManager(),description,youTube, squadName);
        viewPager.setAdapter(adapter);


    }


    private static class TabsAdapter extends FragmentStatePagerAdapter{
        String description;
        String youTube;
        String squadName;
//        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        TabsAdapter(FragmentManager fm, String desc, String youTubeVideo, String name) {
            super(fm);
            squadName = name;
            description = desc;
            youTube = youTubeVideo;
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public Fragment getItem(int i) {
            switch(i){
                case 0: return DescriptionFragment.newInstance(description);
                case 1: return PropertiesFragment.newInstance(squadName);
                case 2: return VideoFragment.newInstance(youTube);
            }
            return null;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch(position){
                case 0: return "Описание";
                case 1: return "Умения";
                case 2: return "Video";
            }
            return null;
        }
    }


}
