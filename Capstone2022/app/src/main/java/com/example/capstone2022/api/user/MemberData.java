package com.example.capstone2022.api.user;

import androidx.annotation.NonNull;

import com.example.capstone2022.util.GsonUtil;
import com.google.gson.JsonObject;

import java.time.LocalDate;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class MemberData {

    private UUID uuid;

    private Integer vaccineCount;
    private String hospitalName;
    private String hospitalContact;
    private LocalDate vaccineDate;
    private LocalDate confirmDate;
    private LocalDate quarantineReleaseDate;
    private Boolean kitPositive; // 자가진단키트 양성
    private Boolean fastPositive; // 신속항원검사 양성
    private Boolean pcrPositive; // PCR 양성

    @NonNull
    public static MemberData parseMember(@NonNull JsonObject jsonObject) {
        return GsonUtil.getGsonNull().fromJson(jsonObject, MemberData.class);
    }

    public JsonObject toJson() {
        return (JsonObject) GsonUtil.getGson().toJsonTree(this);
    }

}
