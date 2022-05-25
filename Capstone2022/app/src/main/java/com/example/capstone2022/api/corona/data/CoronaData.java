package com.example.capstone2022.api.corona.data;

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

    public long getDeathCnt() {
        return deathCnt;
    }

    public long getDecideCnt() {
        return decideCnt;
    }

    public long getAddDeath() {
        return addDeath;
    }

    public long getAddDecide() {
        return addDecide;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

}
