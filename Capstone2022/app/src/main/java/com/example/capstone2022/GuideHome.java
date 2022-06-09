package com.example.capstone2022;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class GuideHome extends AppCompatActivity {
    private String TAG = GuideHome.class.getSimpleName();

    private WebView webView = null;
    ImageView back_g;



    @Override    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_guide);

        back_g = findViewById(R.id.back_guide);
        webView = (WebView) findViewById(R.id.webView);


        webView.setWebViewClient(new WebViewClient());
        webView.setWebChromeClient(new WebChromeClient());

        //화면크기맞춤
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);

        webView.loadUrl("http://ncov.mohw.go.kr/tcmBoardView.do?brdId=&brdGubun=&dataGubun=&ncvContSeq=371078&contSeq=371078&board_id=&gubun=ALL");

        back_g.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent MyTntent = new Intent(GuideHome.this, MainActivity.class);
                startActivity(MyTntent);
            }
        });
    }
}
