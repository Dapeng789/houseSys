package sys.qx.dao;

import java.util.List;

import sys.qx.model.C15TableBean;
import sys.qx.model.UtilDate;

//全部回款
public interface HKSuoYouDaoMapper {

	
	//插入已回款客户数据
	public void insertHkUser(List<C15TableBean> c15Beanlist);
	
	//查询所有数据  保罗部分还款数据
	public List getUserAllData(UtilDate utilDate);
	
	
	//安表示符  删除指定数据
		public void deleteBSUser(Integer biaoShi);
}
