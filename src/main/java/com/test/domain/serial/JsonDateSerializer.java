package com.test.domain.serial;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class JsonDateSerializer extends JsonSerializer<Instant> {

    @Override
    public void serialize(Instant value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MM/dd/yy");
        if(value != null) {
            Instant instant = value;
            ZonedDateTime zdt = instant.atZone(ZoneId.systemDefault());
            String text = zdt.format(format);
            gen.writeString(text);
        }
    }

}