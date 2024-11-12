package web.plan.usecase;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class DateDeserializer implements JsonDeserializer<java.sql.Date> {
    @Override
    public java.sql.Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        try {
            String dateString = json.getAsString();
            return java.sql.Date.valueOf(dateString); // 轉換為 java.sql.Date
        } catch (IllegalArgumentException e) {
            return null; // 或處理錯誤
        }
    }
}
