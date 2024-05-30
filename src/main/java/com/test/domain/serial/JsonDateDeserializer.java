package com.test.domain.serial;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Instant;

public class JsonDateDeserializer extends JsonDeserializer<Instant> {
    @Override
    public Instant deserialize(JsonParser jsonParser,
                               DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yy");
        String date = jsonParser.getText();
        try {
            return format.parse(date).toInstant();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
