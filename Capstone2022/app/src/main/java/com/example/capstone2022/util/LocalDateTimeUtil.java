package com.example.capstone2022.util;

import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import lombok.experimental.UtilityClass;

@UtilityClass
public class LocalDateTimeUtil {

    /**
     * 해당 시간을 `2021-01-24 10:15:30` 꼴로 변환합니다.
     *
     * @param time Time to convert
     * @return ISO LocalDateTime without space
     */
    public String getTimeString(@NotNull LocalDateTime time) {
        return time.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME).replace('T', ' ');
    }

}
