package com.example.capstone2022;

import android.os.Bundle;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

public class VirusHome extends AppCompatActivity {
    private String TAG = GuideHome.class.getSimpleName();

    private WebView webView = null;

    @Override    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_virus);

        webView = (WebView) findViewById(R.id.webView);

        webView.loadUrl("http://ncov.mohw.go.kr/bdBoardList_Real.do?brdId=1&brdGubun=11&ncvContSeq=&contSeq=&board_id=&gubun=");
    }
    }
