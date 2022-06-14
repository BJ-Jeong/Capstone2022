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

public class ServerConnector {

    private static final String requestTag = ServerConnector.class.getSimpleName();

    public static void GET(String path, @NonNull Consumer<JsonObject> after) {
        String url = BuildConfig.SERVER_URL + path;

        Log.d(ServerConnector.class.getSimpleName(), "requesting GET: " + path);

        StringRequest request = new StringRequest(url,
                response -> {
                    try {
                        after.accept(GsonUtil.toJson(response));
                    } catch (RuntimeException e) {
                        Log.e(ServerConnector.class.getSimpleName(), path + " GET after error: " + e.getMessage());
                    }
                },
                error -> Log.w(ServerConnector.class.getSimpleName(), path + " GET data failed: " + error.getMessage()));

        request.setTag(requestTag);
        request.setShouldCache(false);
        VolleyUtil.getQueue().add(request);
    }

    public static void POST(String path, JsonObject json, Runnable after) {
        run(path, json, after, Request.Method.POST);
    }

    public static void PATCH(String path, JsonObject json, Runnable after) {
        run(path, json, after, Request.Method.PATCH);
    }

    private static void run(String path, JsonObject json, Runnable after, int method) {
        String url = BuildConfig.SERVER_URL + path;

        StringRequest request = new StringRequest(method, url,
                response -> after.run(),
                error -> Log.e(ServerConnector.class.getSimpleName(), path + " Method: " + method + " data failed: " + error)
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
        request.setTag(requestTag);
        VolleyUtil.getQueue().add(request);
    }

    public static void cancelAll() {
        VolleyUtil.getQueue().cancelAll(requestTag);
    }

}
