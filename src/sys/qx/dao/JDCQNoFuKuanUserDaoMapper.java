package sys.qx.dao;

import java.util.List;

//超期付款客户
public interface JDCQNoFuKuanUserDaoMapper {

	
	//获取所有数据
	public List geyUserAll();
	
	//安表示符  删除指定数据
		public void deleteBSUser(Integer biaoShi);
}
