package com.example.capstone2022;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.capstone2022.util.VolleyUtil;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    private FragmentManager fm;
    private FragmentTransaction ft;
    private HomeFragment homeFragment;
    private ContactFragment contactFragment;
    private MypageFragment mypageFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.tab_home:
                        setFrag(0);
                        break;
                    case R.id.tab_contact:
                        setFrag(1);
                        break;
                    case R.id.tab_mypage:
                        setFrag(2);
                        break;
                }
                return true;
            }
        });

        homeFragment = new HomeFragment();
        contactFragment = new ContactFragment();
        mypageFragment = new MypageFragment();
        setFrag(0); // 첫 프래그먼트 화면 지정

        VolleyUtil.setContext(getApplicationContext());
    }

    // 프레그먼트 교체
    private void setFrag(int n) {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        switch (n) {
            case 0:
                ft.replace(R.id.main_frame, homeFragment);
                ft.commit();
                break;

            case 1:
                ft.replace(R.id.main_frame, contactFragment);
                ft.commit();
                break;

            case 2:
                ft.replace(R.id.main_frame, mypageFragment);
                ft.commit();
                break;
        }
    }

    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    public void VirusButton(View view) {
        Intent intent = new Intent(getApplicationContext(), VirusHome.class);
        startActivity(intent);
    }

    public void GuideButton(View view) {
        Intent intent = new Intent(getApplicationContext(), GuideHome.class);
        startActivity(intent);
    }

    public void NoticeButton(View view) {
        Intent intent = new Intent(getApplicationContext(), NoticeHome.class);
        startActivity(intent);
    }

    public void HelpHomeButton(View view) {
        Intent intent = new Intent(getApplicationContext(), HelpHome.class);
        startActivity(intent);
    }
    public void MeMypageButton(View view){
        Intent intent = new Intent(getApplicationContext(), MeMypage.class);
        startActivity(intent);
    }

}
