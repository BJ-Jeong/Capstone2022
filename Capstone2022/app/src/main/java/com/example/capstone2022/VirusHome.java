package com.example.capstone2022;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class VirusHome extends AppCompatActivity {

    private final String TAG = VirusHome.class.getSimpleName();

    ImageView back_v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_virus);

        WebView webView = findViewById(R.id.webView);
        back_v = findViewById(R.id.back_virus);

        webView.loadUrl("http://ncov.mohw.go.kr/bdBoardList_Real.do?brdId=1&brdGubun=11&ncvContSeq=&contSeq=&board_id=&gubun=");

        back_v.setOnClickListener(view -> {
            Intent MyIntent = new Intent(VirusHome.this, MainActivity.class);
            startActivity(MyIntent);
        });
    }
}
