package com.example.capstone2022.util;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import lombok.Getter;

public class VolleyUtil {

    @Getter
    private static RequestQueue queue;

    public static void setContext(Context context) {
        if (queue != null) queue.stop();

        queue = Volley.newRequestQueue(context);
    }

}
