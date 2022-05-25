package com.example.capstone2022;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    final String TAG = this.getClass().getSimpleName();

    LinearLayout home_ly;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init(); //객체 정의
        SettingListener(); //리스너 등록

        //맨 처음 시작할 탭 설정
        bottomNavigationView.setSelectedItemId(R.id.tab_home);
    }

    private void init() {
        // home_ly = findViewById(R.id.home_ly); TODO
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
    }

    private void SettingListener() {
        //선택 리스너 등록
        bottomNavigationView.setOnNavigationItemSelectedListener(new TabSelectedListener());
    }

    // TODO: 여기 있던 home_ly 들은 빌드를 위해 replace 되었으므로 수정할 것
    class TabSelectedListener implements BottomNavigationView.OnNavigationItemSelectedListener{
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.tab_home: {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.tab_home, new HomeFragment())
                            .commit();
                    return true;
                }
                case R.id.tab_contact: {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.tab_contact, new ContactFragment())
                            .commit();
                    return true;
                }
                case R.id.tab_mypage: {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.tab_mypage, new MypageFragment())
                            .commit();
                    return true;
                }
            }

            return false;
        }
    }
}