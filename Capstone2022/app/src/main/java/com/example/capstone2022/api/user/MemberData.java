package com.example.capstone2022.api.user;

import androidx.annotation.NonNull;

import com.example.capstone2022.util.GsonUtil;
import com.google.gson.JsonObject;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MemberData {

    private UUID uuid;

    private String vaccineInfo;
    private String hospitalName;
    private String hospitalContact;
    private LocalDate vaccineDate;

    @NonNull
    public static MemberData parseMember(@NonNull JsonObject jsonObject) {
        UUID uuid = UUID.fromString(jsonObject.get("id").getAsString());

        String vaccineInfo = jsonObject.get("vaccineInfo").getAsString();
        String hospitalName = jsonObject.get("hospitalName").getAsString();
        String hospitalContact = jsonObject.get("hospitalContact").getAsString();

        LocalDate vaccineDate = LocalDate.parse(jsonObject.get("vaccineDate").getAsString());

        return new MemberData(uuid, vaccineInfo, hospitalName, hospitalContact, vaccineDate);
    }

}
