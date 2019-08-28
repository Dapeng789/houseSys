package sys.qx.dao;

import sys.qx.model.UserBean;


public interface UserDaoMapper {

	//查询用户
	public UserBean getUser(String userName);
	
	//删除用户
	public int delUser(UserBean user);
	
	//修改用户
	public int updUser(UserBean user);
	
	
	//增加用户
	public int addUser(UserBean user);
	
	//查询所有用户
	public  UserBean getAllUser();
	
	//获取最大的标识
	public UserBean  getMax();
	
	//删除用户
	public int delOneUser(Integer biaoShi);
	
}
