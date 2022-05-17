package com.example.capstone2022.api;

import com.example.capstone2022.api.corona.enums.ContentType;

import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.stream.Collectors;

// TODO: replaced Valley library
@Deprecated
public class APIConnector {

    /**
     * URL 로부터 데이터를 JSON 으로 얻어옵니다.
     *
     * <p><b>주의: </b>비동기로 처리 되어야 합니다.</p>
     *
     * @param urlString net.qsef1256.capstone2022server.api url
     * @return result StringBuilder
     * @throws IOException when can't connect to net.qsef1256.capstone2022server.api url
     * @see #getResult(String, ContentType)
     */
    @NotNull
    public String getResult(@NotNull String urlString) throws IOException {
        return getResult(urlString, ContentType.JSON);
    }

    @NotNull
    public String getResult(@NotNull String urlString, @NotNull ContentType contentType) throws IOException {
        URL url = new URL(urlString);

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", contentType.getData());

        int code = conn.getResponseCode();

        BufferedReader br = (code >= 200 && code <= 300) ?
                new BufferedReader(new InputStreamReader(conn.getInputStream())) :
                new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        String line = br.lines().collect(Collectors.joining());
        conn.disconnect();
        br.close();
        return line;
    }

}
