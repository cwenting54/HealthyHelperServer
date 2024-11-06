package web.dietdiary.gson.serializer;

import java.lang.reflect.Type;
import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import web.dietdiary.constant.SqlTimePattern;


public class JsonSerializerForSqlTime {
	public static JsonSerializer<Time> timeDeserializer = new JsonSerializer<Time>() {
		@Override
		public JsonElement serialize(Time arg0, Type arg1, JsonSerializationContext arg2) {
			SimpleDateFormat timeFormat = new SimpleDateFormat(SqlTimePattern.sqlTimePattern);
			return new JsonPrimitive(timeFormat.format(arg0).toString());
		}
	};
}
