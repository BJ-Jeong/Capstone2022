package com.example.capstone2022.api.user;

import androidx.annotation.NonNull;

import com.example.capstone2022.util.GsonUtil;
import com.google.gson.JsonObject;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
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
        return GsonUtil.getGsonNull().fromJson(jsonObject, ContactData.class);
    }

}
