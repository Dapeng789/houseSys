package sys.qx.dao;

import java.util.List;

//C03新客户
public interface C03TableNewUserDaoMapper {


	//清空表
	public void  deleteTable(Integer biaoshi);
	
	//增加用户
	public  void  addUser(List list);
	
	//获取所有新客户
	public List getAllNewUser(int id);
	
	//安表示符  删除指定数据
	public void deleteBSUser(Integer biaoShi);
}
