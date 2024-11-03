package web.plan.usecase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;

public class GsonTimeBuilder {
	// 建立 Gson 時指定日期格式
	public static Gson gson = new GsonBuilder()
			.registerTypeAdapter(Date.class, (JsonDeserializer<Date>) (json, type, context) -> {
		        try {
		            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		            sdf.setTimeZone(TimeZone.getTimeZone("Asia/Taipei")); // 設置時區，根據需求進行調整
		            return sdf.parse(json.getAsString());
		        } catch (Exception e) {
		            throw new RuntimeException(e);
		        }
		    })
		    .create();

}
