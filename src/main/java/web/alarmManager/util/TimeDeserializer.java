package web.alarmManager.util;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;

import java.lang.reflect.Type;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class TimeDeserializer implements JsonDeserializer<Time> {
    private static final SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");

    @Override
    public Time deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {
        try {
            return new Time(format.parse(json.getAsString()).getTime());
        } catch (ParseException e) {
            throw new RuntimeException("Failed to parse time: " + json.getAsString(), e);
        }
    }
}
