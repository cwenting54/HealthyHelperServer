package web.dietdiary.datetime;

import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;

import web.dietdiary.constant.SqlDatePattern;
import web.dietdiary.constant.SqlTimePattern;

public class DateTimeFormatterImpl implements DateTimeFormatter{

	@Override
	public String DateToString(Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(SqlDatePattern.sqlDatePattern);
		String retValue = dateFormat.format(date);
		return retValue;
	}

	@Override
	public String TimeToString(Time time) {
		SimpleDateFormat timeFormat = new SimpleDateFormat(SqlTimePattern.sqlTimePattern);
		String retValue = timeFormat.format(time);
		return retValue;
	}
}
