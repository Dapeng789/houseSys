package sys.qx.dao;

import java.util.List;

import sys.qx.model.C03TableUser;
import sys.qx.model.UtilDate;

//增加C03用户
public interface C03TableUserDaoMapper {

	
	//保存数据
		public  void  saveDrugGetData(List list);
		
		
	//按照方便代码  查询有没有此用户
		public  List getC03User(C03TableUser excelData);
		
		//更新已有的客户状态
		public void updateC03User(C03TableUser excelData);
		
		
		//查询指定按揭客户数据
		public List getDrugGetData(C03TableUser excelData);
		
		
		//删除按揭客户
		public  void  deleteC03TavleData(C03TableUser excelData);
		
		
		
		//查看所有数据 
		public  List<C03TableUser>  getDrugGetData1(Integer biaoShi);
		
		
		public void  deleteByUserId(int c03id);
		
		
		//获取所有数据  只获取   id    (到期欠交金额   deliveryDebtMathod+ 未到期欠交金额 noDeliveryDebtMathod)
		public List getbufenUserData(Integer biaoShi);
		
		
		//插入总金额
		public void updateZongJinE(C03TableUser lackOfDetailObj);
		
		
		//计算住房客户总金额
		public C03TableUser zhufangAllJinE(Integer biaoShi);
		
		//计算住非房客户总金额
		public C03TableUser nozhufangAllJinE(Integer biaoShi);
		
		
		//获取倒叙数据
		public  C03TableUser   daoxuData(Integer biaoShi);
		
		
		//id删除一个客户
		public  void   deleteByIdUser(int id);

		//----------------------------------------------------------------------------------------
		
		
		//查询Al列 不为0 的客户     AL under181To270  , 
		public  List  getALData(Integer biaoShi);
		
		//查询Al列 不为0 的客户    AM  under271To360  
		public  List  getAMData(Integer biaoShi);
		
		//查询Al列 不为0 的客户   , AN  under361Up
		public  List  getANData(Integer biaoShi);
		
		
		
		
		//删除Al列 不为0 的客户     AL under181To270  , 
		public  void  deleteALData(Integer biaoShi);
		
		//删除Al列 不为0 的客户    AM  under271To360  
		public  void  deleteAMData(Integer biaoShi);
		
		//删除Al列 不为0 的客户   , AN  under361Up
		public  void  deleteANData(Integer biaoShi);
		
		//----------------------------------------------------------------------------------------
		
		
		//获取签约状态为正签状态的客户
		public C03TableUser  getZhengQianUser();
		
		//获取签约状态为草签的客户
		public C03TableUser getCaoQianUser();
		
		
		//id删除一个客户
		public  void   deleteByIdUser();
		
		//更新总欠款金额状态
		public void  updateQKZJE(C03TableUser c03TableUser);
		
		
		//获取大于90天以上  并为草签的客户
		public  List getUser90Tian(UtilDate utilDate);
		
		//安表示符  删除指定数据
		public void deleteBSUser(Integer biaoShi);
}
