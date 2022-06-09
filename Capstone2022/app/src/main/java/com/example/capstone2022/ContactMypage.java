package com.example.capstone2022;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class ContactMypage extends AppCompatActivity {

    ImageView back_c;

    @Override    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mypage_con);

        back_c = findViewById(R.id.back_con);

        back_c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ContactMypage.this, MypageFragment.class);
                startActivity(intent);
            }
        });



    }
}