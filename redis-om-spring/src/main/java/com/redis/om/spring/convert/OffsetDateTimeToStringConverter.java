package com.redis.om.spring.convert;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;

@Component
@WritingConverter
public class OffsetDateTimeToStringConverter implements Converter<OffsetDateTime, String> {
    @Override
    public String convert(OffsetDateTime source) {
        Instant instant = source.atZoneSameInstant(ZoneId.systemDefault()).toInstant();
        long unixTime = instant.getEpochSecond();
        return Long.toString(unixTime);
    }
}
