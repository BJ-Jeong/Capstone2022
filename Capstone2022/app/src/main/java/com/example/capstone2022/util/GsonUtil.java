package com.example.capstone2022.util;

import com.example.capstone2022.util.gson.GsonLocalDate;
import com.example.capstone2022.util.gson.GsonLocalDateTime;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.experimental.UtilityClass;

@UtilityClass
public class GsonUtil {

    @Getter
    private static final Gson gson = getGsonBuilder().create();

    @Getter
    private static final Gson gsonNull = getGsonBuilder().serializeNulls().create();

    @NotNull
    private static GsonBuilder getGsonBuilder() {
        return new GsonBuilder()
                .disableHtmlEscaping()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDateTime.class, new GsonLocalDateTime())
                .registerTypeAdapter(LocalDate.class, new GsonLocalDate());
    }

    public static String parsePretty(String inputString) {
        final JsonObject inputJson = gson.fromJson(inputString, JsonObject.class);

        return gson.toJson(inputJson);
    }

    public static JsonObject toJson(String jsonString) {
        return gson.fromJson(jsonString, JsonObject.class);
    }

    public static String toText(JsonObject jsonObject) {
        return gson.toJson(jsonObject);
    }

}
