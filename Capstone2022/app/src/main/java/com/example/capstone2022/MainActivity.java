package com.example.capstone2022;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.capstone2022.util.VolleyUtil;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import lombok.Getter;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    @Getter
    private FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(null);

        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                .detectLeakedClosableObjects()
                .penaltyLog()
                .build());

        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnItemSelectedListener((BottomNavigationView.OnNavigationItemSelectedListener) menuItem -> {
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
        });

        setFrag(0); // 첫 프래그먼트 화면 지정

        VolleyUtil.setContext(getApplicationContext());
    }

    // 프레그먼트 교체
    private void setFrag(int n) {
        fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        switch (n) {
            case 0:
                ft.replace(R.id.main_frame, new HomeFragment());
                ft.commit();
                break;
            case 1:
                ft.replace(R.id.main_frame, new ContactFragment());
                ft.commit();
                break;
            case 2:
                ft.replace(R.id.main_frame, new MypageFragment());
                ft.commit();
                break;
        }
    }

    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    public void ClickButton1(View view) {
        Intent intent = new Intent(getApplicationContext(), VirusHome.class);
        startActivity(intent);
    }

    public void ClickButton2(View view) {
        Intent intent = new Intent(getApplicationContext(), GuideHome.class);
        startActivity(intent);
    }

    public void ClickButton3(View view) {
        Intent intent = new Intent(getApplicationContext(), NoticeHome.class);
        startActivity(intent);
    }

    public void ClickButton4(View view) {
        Intent intent = new Intent(getApplicationContext(), HelpHome.class);
        startActivity(intent);
    }

    public void MeMypageButton(View view) {
        Intent intent = new Intent(getApplicationContext(), MeMypage.class);
        startActivity(intent);
    }

    public void ConMypageButton(View view) {
        Intent intent = new Intent(getApplicationContext(), ContactMypage.class);
        startActivity(intent);
    }

}
