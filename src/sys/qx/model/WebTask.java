package sys.qx.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import sys.qx.controller.UtilsUser;
import sys.qx.dao.C03TableUserDaoMapper;
import sys.qx.dao.JDCQCaoQianUserDaoMapper;

@Component
public class WebTask {
	
	@Autowired
	private C03TableUserDaoMapper c03TableUserDaoMapper;
	
	
	
	@Autowired
	private JDCQCaoQianUserDaoMapper jDCQCaoQianUserDaoMapper;
	
	
	// 每五秒执行一次
		/*@Scheduled(cron = "0/5 * * * * ?")*/
		@Scheduled(cron = "0 0 0/1 * * ?")
		public void TaskJob() {
			//获取90天以上的客户，并且签约状态为草签的客户
			try {
				List list = c03TableUserDaoMapper.getUser90Tian(this.getDate());
				
				//向超期草签表中插入数据
				if(list.size()>0) {
					//保存到超期草签表中
					jDCQCaoQianUserDaoMapper.insertChaoQiUser(list);
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}
		
		//做一个时间  当前时间减去90天
		//定义默认查询时间方法
		public  UtilDate   getDate() throws ParseException {
			UtilDate utilDate = new UtilDate();
			SimpleDateFormat sdf  = new SimpleDateFormat("yyyy-MM-dd");
			String maxDateStr = sdf.format(new Date());
			String minDateStr = "";
			Calendar calc =Calendar.getInstance();
			calc.setTime(sdf.parse(maxDateStr));
			//35 / 7 = 5周
			calc.add(calc.DATE, -90);
			Date minDate = calc.getTime();
			minDateStr = sdf.format(minDate);
			System.out.println("minDateStr:"+minDateStr); 
			utilDate.setSrartDate(minDateStr);
			utilDate.setZhuagtai("草签");
			utilDate.setId(UtilsUser.getBiaoshi());
			return utilDate;
		}

}
