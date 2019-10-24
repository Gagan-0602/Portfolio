package com.gagankaushal.portfolio;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Home extends AppCompatActivity {
    TabLayout tabLayout;
    TextView rateus;
    Context context;
    ViewPager viewpager;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
       context=Home.this;
      rateus=(TextView)findViewById(R.id.rateus);
       imageView=(ImageView)findViewById(R.id.listimg);
        tabLayout=(TabLayout)findViewById(R.id.tablayout);
        FragmentManager fragmentManager = getSupportFragmentManager();
        MyAdapter pagerAdaptr=new MyAdapter(context,fragmentManager,3);
        viewpager= (ViewPager) findViewById(R.id.viewpager);
        viewpager.setAdapter(pagerAdaptr);
        tabLayout.setupWithViewPager(viewpager);
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        rateus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Home.this,Rating.class);
                startActivity(intent);
            }
        });

    }

    public  void DASHBOARD(View view)
    {
        Intent intent=new Intent(Home.this,Dashboard.class);
        startActivity(intent);
    }
    public void logOut(View view) {
        LocalSession localSession= new LocalSession(Home.this);
        localSession.logOutUser();
        Intent intent= new Intent(Home.this,Login.class);
        startActivity(intent);
        finish();
    }
}
