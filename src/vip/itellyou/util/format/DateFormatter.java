package vip.itellyou.util.format;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期格式处理
 * @author Ma Wenhai
 *
 */
public class DateFormatter {

	//将长整形转换成日期格式的字符串
	public static String toShortDate(long time){
		SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日");
		return df.format(new Date(time));
	}
	
	//将字符串的日期转换成长整数
	public static Long toLong(String date) throws Exception{
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return df.parse(date).getTime();
	}
}






