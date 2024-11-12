package web.alarmManager.util;

import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.sql.Time;
import java.text.SimpleDateFormat;

public class TimeSerializer implements JsonSerializer<Time> {
    private static final SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");

    @Override
    public JsonElement serialize(Time time, Type typeOfSrc, JsonSerializationContext context) {
        return context.serialize(format.format(time));
    }
}