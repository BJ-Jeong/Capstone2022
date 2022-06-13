package com.example.capstone2022.api.corona;

import androidx.annotation.NonNull;

import com.google.gson.JsonObject;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class CoronaData {

    private final long deathCnt;
    private final long decideCnt;
    private final long addDeath;
    private final long addDecide;
    private final LocalDateTime updateTime;

    public CoronaData(long deathCnt, long decideCnt, long addDeath, long addDecide, LocalDateTime updateTime) {
        this.deathCnt = deathCnt;
        this.decideCnt = decideCnt;
        this.addDeath = addDeath;
        this.addDecide = addDecide;
        this.updateTime = updateTime;
    }

    @NonNull
    public static CoronaData parseData(@NonNull JsonObject jsonObject) {

        long deathCnt = jsonObject.get("deathCnt").getAsLong();
        long decideCnt = jsonObject.get("decideCnt").getAsLong();
        long addDeath = jsonObject.get("addDeath").getAsLong();
        long addDecide = jsonObject.get("addDecide").getAsLong();
        LocalDateTime updateTime = LocalDateTime.parse(jsonObject.get("updateTime").getAsString());

        return new CoronaData(deathCnt, decideCnt, addDeath, addDecide, updateTime);
    }

}
