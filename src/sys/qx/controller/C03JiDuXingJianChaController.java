package sys.qx.controller;

import org.springframework.stereotype.Controller;


import org.springframework.web.bind.annotation.RequestMapping;

  

//季度性检查Controller
@Controller
@RequestMapping("c03JiDuXingJianChaController")
public class C03JiDuXingJianChaController {

	
	
	/**
	 * 
	 * 1： -----》系统每天晚上12点(认为这个时间点 系统用的较少，系统需判断所有C03中的所
		有数据，消耗资源较大，不建议工作人员使用系统时，系统判断)自动判断 从导入时间开始
		计算，有没有超过90天以上的客户，如果有，系统自动判断当前客户 签约状态 是否为 草
		签，如果是草签，就保存到“超期草签客户表” 判断完所有客户之后，系统获取“超期草
		签客户表”中的所有数据，展示到页面上，供用户查看， 修改，删除。 
		
		
		 
		
			1.1：------》系统用户可对指定的一条数据进行删除，系统首先删除“超期草签客户表”中的数据，再根据id删除C03表中的本条数据，删除完成之后，系统自动返回到“超期草签客户表”页面。
		
			1.2：------》将草签的状态 修改为正签状态。
		                               页面中提供下拉框 ，值为“正签”“草签”两个值，可			   修改指定客户状态。
		
		 后台首先将本条数据保存到“已改为正签表”中，再更新C03表中的状态为正签 。
		再将“超期草签客户表”中将本条数据删除（因为已经改为正签了，没有必要在当前表中保
		留）。
		
		
		2：-----》系统需要根据时间  判断超期付款为180天以上的客户，如是180天以上的，系
		统将其数据保存到 “超期未付客户表”中 供用户查看。  用户判断完决定删除客户时，从
		“超期未付客户表”表中删除，系统会根据id先将C03中本条数据删除，再删除“超期未
		付客户表”中的本条数据。
		
	 */
	
	
}
