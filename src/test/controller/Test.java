package test.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import sys.qx.model.C15TableBean;

public class Test {

	
	
	
	public static void main(String[] args) throws ParseException {
		
		/*List list = new  ArrayList();
		C15TableBean  c15TableBean = new C15TableBean();
		SimpleDateFormat  date = new  SimpleDateFormat("yyyy-MM-dd");
		c15TableBean.setLurudate(date.format(new Date()));
		list.add(c15TableBean);*/
		//new一个当前时间
		
/*		SimpleDateFormat  date = new  SimpleDateFormat("yyyyMMdd");
		Integer in = new Integer(date.format(new Date()));
		int newValue = (in-35);
		System.out.println(newValue);
		String newDate = date.format(new Date());
		*/
		
		
		SimpleDateFormat sdf  = new SimpleDateFormat("yyyy-MM-dd");
		String maxDateStr = sdf.format(new Date());
		String minDateStr = "";
		Calendar calc =Calendar.getInstance();
			calc.setTime(sdf.parse(maxDateStr));
			calc.add(calc.DATE, -35);
			Date minDate = calc.getTime();
			minDateStr = sdf.format(minDate);
			System.out.println("minDateStr:"+minDateStr);//minDateStr:2017-01-09   正确！！！！
		
	}
}
