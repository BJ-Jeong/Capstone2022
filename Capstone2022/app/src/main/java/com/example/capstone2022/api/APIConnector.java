package com.example.capstone2022.api;

import android.util.Log;

import androidx.annotation.NonNull;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.example.capstone2022.BuildConfig;
import com.example.capstone2022.util.GsonUtil;
import com.example.capstone2022.util.VolleyUtil;
import com.google.gson.JsonObject;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class APIConnector {

    public static void GET(String path, @NonNull Consumer<JsonObject> after) {
        String url = BuildConfig.SERVER_URL + path;

        StringRequest request = new StringRequest(url,
                response -> after.accept(GsonUtil.toJson(response)),
                error -> Log.w("APIConnector", path + " GET data failed: " + error.getMessage()));

        VolleyUtil.getQueue().add(request);
    }

    public static void POST(String path, JsonObject json, Runnable after) {
        String url = BuildConfig.SERVER_URL + path;

        StringRequest request = new StringRequest(Request.Method.POST, url,
                response -> after.run(),
                error -> Log.e("APIConnector", path + " POST data failed: " + error)
        ) {
            @NonNull
            @Override
            public Map<String, String> getHeaders() {
                HashMap<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/json");

                return headers;
            }

            @Override
            public byte[] getBody() {
                return GsonUtil.toText(json).getBytes(StandardCharsets.UTF_8);
            }
        };

        request.setShouldCache(false);
        VolleyUtil.getQueue().add(request);
    }

}
