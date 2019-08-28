package sys.qx.dao;

import java.util.List;

import sys.qx.controller.UtilsUser;
import sys.qx.model.C03TableUser;
import sys.qx.model.UtilDate;

//C03 初始筛选无效客户
public interface C03WuXiaoUserDaoMapper {

	//插入无效客户数据
	public void putOneAndAllC03NoUserData(List list);
	
	public void  putNoUserData(int id);
	
	//查询所有数据
	public   List  getAllUser(int id);
	
	//按时间查询初始无效数据
	public List getDateUser(UtilDate utilDate);
	// <!-- 查询AL数据 -->
	  
	//安表示符  删除指定数据
		public void deleteBSUser(Integer biaoShi);
	
}
