package web.dietdiary.util.gson;

import java.sql.Date;
import java.sql.Time;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSerializer;

import web.dietdiary.gson.serializer.JsonSerializerForSqlDate;
import web.dietdiary.gson.serializer.JsonSerializerForSqlTime;
import web.dietdiary.util.gson.deserializer.JsonDeserializerForSqTime;
import web.dietdiary.util.gson.deserializer.JsonDeserializerForSqlDate;

public class GsonForSqlDateAndSqlTime {
	public static Gson gson = new GsonBuilder()
			.registerTypeAdapter(Date.class, JsonDeserializerForSqlDate.dateDeserializer)
			.registerTypeAdapter(Time.class, JsonDeserializerForSqTime.timeDeserializer)
			.registerTypeAdapter(Date.class, JsonSerializerForSqlDate.dateDeserializer)
			.registerTypeAdapter(Time.class, JsonSerializerForSqlTime.timeDeserializer)
			.create();
}
