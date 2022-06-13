package com.example.capstone2022.api.user;

import androidx.annotation.NonNull;

import com.example.capstone2022.util.GsonUtil;
import com.google.gson.JsonObject;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ContactData {

    private UUID uuid;
    private String name;
    private String phoneNo;

    private ContactCoronaInfo coronaInfo;

    @Data
    @AllArgsConstructor
    public static class ContactCoronaInfo {
        private LocalDateTime confirmationDate;
        private LocalDateTime finalVaccineDate;
        private LocalDateTime quarantineReleaseDate;
        private boolean overseasEntry;
        private boolean closeContact;
    }

    @NonNull
    public static ContactData parseContact(@NonNull JsonObject jsonObject) {
        UUID uuid = UUID.fromString(jsonObject.get("id").getAsString());
        String name = jsonObject.get("name").getAsString();
        String phoneNo = jsonObject.get("phoneNo").getAsString();

        JsonObject coronaInfo = jsonObject.getAsJsonObject("coronaInfo");

        LocalDateTime confirmationDate = LocalDateTime.parse(coronaInfo.get("confirmationDate").getAsString());
        LocalDateTime finalVaccineDate = LocalDateTime.parse(coronaInfo.get("finalVaccineDate").getAsString());
        LocalDateTime quarantineReleaseDate = LocalDateTime.parse(coronaInfo.get("quarantineReleaseDate").getAsString());
        boolean overseasEntry = jsonObject.get("overseasEntry").getAsBoolean();
        boolean closeContact = jsonObject.get("closeContact").getAsBoolean();

        return new ContactData(uuid, name, phoneNo,
                new ContactData.ContactCoronaInfo(confirmationDate, finalVaccineDate, quarantineReleaseDate, overseasEntry, closeContact));
    }

}
