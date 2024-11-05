package web.dietdiary.util.gson;

import java.sql.Date;
import java.sql.Time;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import web.dietdiary.util.gson.deserializer.JsonDeserializerForSqTime;
import web.dietdiary.util.gson.deserializer.JsonDeserializerForSqlDate;
import web.dietdiary.util.gson.serializer.JsonSerializerForSqlDate;
import web.dietdiary.util.gson.serializer.JsonSerializerForSqlTime;

public class GsonForSqlDateAndSqlTime {
	public static Gson gson = new GsonBuilder()
			.registerTypeAdapter(Date.class, JsonDeserializerForSqlDate.dateDeserializer)
			.registerTypeAdapter(Time.class, JsonDeserializerForSqTime.timeDeserializer)
			.registerTypeAdapter(Date.class, JsonSerializerForSqlDate.dateDeserializer)
			.registerTypeAdapter(Time.class, JsonSerializerForSqlTime.timeDeserializer)
			.create();
}
