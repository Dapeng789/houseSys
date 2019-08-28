package sys.qx.dao;

import java.util.List;

import sys.qx.model.C15TableBean;

//部分回款
public interface HKBuFenDaoMapper {

	
	//插入已回款客户数据
		public void insertBFHKUser(List<C15TableBean> c15Beanlist);
		
		
		
	//查看部分还款所有客户
		public List  getBuFenAllUser(int id);
		
	
		//安表示符  删除指定数据
		public void deleteBSUser(Integer biaoShi);
}
