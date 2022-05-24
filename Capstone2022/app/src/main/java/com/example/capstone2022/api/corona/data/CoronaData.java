package com.example.capstone2022.api.corona.data;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class CoronaData {

    private long deathCnt;
    private long decideCnt;
    private long addDeath;
    private long addDecide;
    private LocalDateTime updateTime;

    public CoronaData(long deathCnt, long decideCnt, long addDeath, long addDecide, LocalDateTime updateTime) {
        this.deathCnt = deathCnt;
        this.decideCnt = decideCnt;
        this.addDeath = addDeath;
        this.addDecide = addDecide;
        this.updateTime = updateTime;
    }

}
