package com.example.capstone2022.api.user.data;

import androidx.annotation.NonNull;

import com.example.capstone2022.util.GsonUtil;
import com.google.gson.JsonObject;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ContactData {

    private Long id;
    private String name;
    private String phoneNo;

    private ContactCoronaInfo coronaInfo;

    @Data
    @AllArgsConstructor
    public static class ContactCoronaInfo {
        private LocalDateTime confirmationDate;
        private LocalDateTime finalVaccineDate;
        private LocalDateTime quarantineReleaseDate;
        private String overseasEntry;
        private String closeContact;
    }

    @NonNull
    public static ContactData parseContact(String data) {

        JsonObject jsonObject = GsonUtil.toJson(data);

        Long id = jsonObject.get("id").getAsLong();
        String name = jsonObject.get("name").getAsString();
        String phoneNo = jsonObject.get("phoneNo").getAsString();

        JsonObject coronaInfo = jsonObject.getAsJsonObject("coronaInfo");

        LocalDateTime confirmationDate = LocalDateTime.parse(coronaInfo.get("confirmationDate").getAsString());
        LocalDateTime finalVaccineDate = LocalDateTime.parse(coronaInfo.get("finalVaccineDate").getAsString());
        LocalDateTime quarantineReleaseDate = LocalDateTime.parse(coronaInfo.get("quarantineReleaseDate").getAsString());
        String overseasEntry = jsonObject.get("overseasEntry").getAsString();
        String closeContact = jsonObject.get("closeContact").getAsString();

        return new ContactData(id, name, phoneNo,
                new ContactData.ContactCoronaInfo(confirmationDate, finalVaccineDate, quarantineReleaseDate, overseasEntry, closeContact));
    }

}
