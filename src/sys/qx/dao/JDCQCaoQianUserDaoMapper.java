package sys.qx.dao;

import java.util.List;

//季度检查  草签客户
public interface JDCQCaoQianUserDaoMapper {
	
	public void insertChaoQiUser(List list);

	
	public List getAllUser(Integer myid);
	
	//安表示符  删除指定数据
		public void deleteBSUser(Integer biaoShi);
}
