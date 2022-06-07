package com.example.capstone2022;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class HomeFragment2 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_home);

        //확진자 추이 버튼 눌렀을때 액티비티 전환
        ImageButton Virus = (ImageButton) findViewById(R.id.virus);
        Virus.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), VirusHome.class);
                startActivity(intent);
            }
        });

        //방역지침 버튼 눌렀을때 액티비티 전환
        ImageButton Guide = (ImageButton) findViewById(R.id.guide);
        Guide.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), GuideHome.class);
                startActivity(intent);
            }
        });

        //안내문 버튼 눌렀을때 액티비티 전환
        ImageButton Notice = (ImageButton) findViewById(R.id.notice);
        Notice.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), NoticeHome.class);
                startActivity(intent);
            }
        });

        //도움말 버튼 눌렀을때 액티비티 전환
        ImageButton Help = (ImageButton) findViewById(R.id.help);
        Help.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), HelpHome.class);
                startActivity(intent);
            }
        });


    }
}
