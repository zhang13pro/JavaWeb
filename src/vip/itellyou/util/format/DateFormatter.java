package vip.itellyou.util.format;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ���ڸ�ʽ����
 * @author Ma Wenhai
 *
 */
public class DateFormatter {

	//��������ת�������ڸ�ʽ���ַ���
	public static String toShortDate(long time){
		SimpleDateFormat df = new SimpleDateFormat("yyyy��MM��dd��");
		return df.format(new Date(time));
	}
	
	//���ַ���������ת���ɳ�����
	public static Long toLong(String date) throws Exception{
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return df.parse(date).getTime();
	}
}






