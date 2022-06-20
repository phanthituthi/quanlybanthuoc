package DAO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JFormattedTextField.AbstractFormatter;

public class DinhDangDate extends AbstractFormatter {

	private String pattern = "dd-MM-yyyy";
	private SimpleDateFormat format = new SimpleDateFormat(pattern);
	@Override
	public Object stringToValue(String text) throws ParseException {
		return format.parseObject(text);
	}

	@Override
	public String valueToString(Object value) throws ParseException {
		if (value != null) {
			Calendar calendar = (Calendar) value;
			String strDate = format.format(calendar.getTime());
			return strDate;
		}
		return "";
	}

	public static void main(String[] args) throws ParseException {
		DinhDangDate dinhDangDate = new DinhDangDate();
		Object object = dinhDangDate.stringToValue("20-10-2019");
		System.out.println(object);
		String string = dinhDangDate.valueToString(object);
		System.out.println(string);
	}
}
