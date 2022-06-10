package com.example.capstone2022;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import io.reactivex.rxjava3.core.Single;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    private FragmentManager fm;
    private FragmentTransaction ft;
    private HomeFragment frag1;
    private ContactFragment frag2;
    private MypageFragment frag3;

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

        frag1 = new HomeFragment();
        frag2 = new ContactFragment();
        frag3 = new MypageFragment();
        setFrag(0); // 첫 프래그먼트 화면 지정

    }

    // 프레그먼트 교체
    private void setFrag(int n) {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        switch (n) {
            case 0:
                ft.replace(R.id.main_frame, frag1);
                ft.commit();
                break;

            case 1:
                ft.replace(R.id.main_frame, frag2);
                ft.commit();
                break;

            case 2:
                ft.replace(R.id.main_frame, frag3);
                ft.commit();
                break;

        }
    }

    public void onConfigurationChanged(Configuration newConfig) {
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
        Intent intent = new Intent(getApplicationContext(), MeMypage.class);
        startActivity(intent);
    }

}
