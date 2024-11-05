package web.dietdiary.gson.serializer;

import java.lang.reflect.Type;
import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import web.dietdiary.constant.SqlDatePattern;
import web.dietdiary.constant.SqlTimePattern;

public class JsonSerializerForSqlDate {
	public static JsonSerializer<Date> dateDeserializer = new JsonSerializer<Date>() {
		@Override
		public JsonElement serialize(Date arg0, Type arg1, JsonSerializationContext arg2) {
				SimpleDateFormat dateFormat = new SimpleDateFormat(SqlDatePattern.sqlDatePattern);
				return new JsonPrimitive(dateFormat.format(arg0).toString());
		}
	};
}
