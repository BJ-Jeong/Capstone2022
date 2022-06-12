package com.example.capstone2022;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class HelpHome extends AppCompatActivity {
    private String TAG = HelpHome.class.getSimpleName();
    ImageView back_h;

    @Override    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_help);

        back_h = findViewById(R.id.back_help);


        back_h.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent MyTntent = new Intent(HelpHome.this, MainActivity.class);
                startActivity(MyTntent);
            }
        });
    }
}
