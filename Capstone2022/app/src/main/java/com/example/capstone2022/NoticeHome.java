package com.example.capstone2022;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class NoticeHome extends AppCompatActivity {

    ImageView back_n;

    @Override    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_notice);

        back_n = findViewById(R.id.back_notice);

        back_n.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent MyTntent = new Intent(NoticeHome.this, MainActivity.class);
                startActivity(MyTntent);
            }
        });



    }
}
