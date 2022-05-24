package com.example.capstone2022.api.corona;

import androidx.annotation.NonNull;

import com.example.capstone2022.api.corona.data.CoronaData;
import com.example.capstone2022.util.GsonUtil;
import com.google.gson.JsonObject;

import java.time.LocalDateTime;

public class CoronaParser {

    @NonNull
    public static CoronaData parseData(String data) {

        JsonObject jsonObject = GsonUtil.toJson(data);
        long deathCnt = jsonObject.get("deathCnt").getAsLong();
        long decideCnt = jsonObject.get("decideCnt").getAsLong();
        long addDeath = jsonObject.get("addDeath").getAsLong();
        long addDecide = jsonObject.get("addDecide").getAsLong();
        LocalDateTime updateTime = LocalDateTime.parse(jsonObject.get("updateTime").getAsString());

        return new CoronaData(deathCnt, decideCnt, addDeath, addDecide, updateTime);
    }

}
