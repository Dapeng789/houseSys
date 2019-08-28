package sys.qx.dao;

import java.util.List;

import sys.qx.model.C03TableUser;

//增加C15数据
public interface C15TableBeanDaoMapper {

	
	

    //保存数据
	public  void  saveC15TableExcelData(List list);
	
	//查看所有数据 
	public  List  getC15TableExcelData();
	
	
	//检查是否有  房间编号
	public List ifFangjianBianHao(String bianhao);
	
	
	//获取总金额
	public Long getZongjine();
	
	
	//清空C15表中的所有数据
	public void qingKongC15(int id);
	
	
	//将已还款部分表清空
	public void qingkongHuanKuan();
	
	//将已还款表清空
	public void qingKongBufuHuanKuan();
	
	
	//根据 房间代码 删除指定的用户
	public void  deletefjbhUser(C03TableUser fjbh);
	

	//安表示符  删除指定数据
		public void deleteBSUser(Integer biaoShi);
}
