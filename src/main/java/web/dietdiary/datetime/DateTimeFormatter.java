package web.dietdiary.datetime;

import java.sql.Date;
import java.sql.Time;

public interface DateTimeFormatter {
	String DateToString(Date date);
	String TimeToString(Time date);
}
