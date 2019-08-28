<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
	<meta charset = "UTF-8">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <meta name="renderer" content="webkit">
    <title>网站信息</title>  
  
   	
   	<style type="text/css">
   	
    	#put {
    		text-align:center;padding:10px 5px; width:210px;border-radius:12px;font-size: 17px;position:relative;left:0px; background-color:#00CCFF;
    	}
		
		table tr td{padding:8px;
					text-align:center;
					border: 2px solid #669966;	
		} 
		
		table
	  {	
	  		border-collapse:collapse;
	  }
		
	  #mytyle {
	  	position:relative;
	  	border-radius:6px;
	  	border:1px solid #dddddd;
	  	padding:10px 5px;
	  	width:210px;
	  	text-align:center;
	  	background-color:#00CCFF;
	  }
		
   	</style>
</head>	
<body>
 <div id = "mytyle">
    	<a>导出表格</a>
    </div>
    <table border="1"  width="4000">
    	<tr>
    	<th><span>条数序号</span></th>
			<th><span>序号</span></th>
			<th><span>区域</span></th>
			<th><span>省份</span></th>
			<th><span>项目名称</span></th>
			<th><span>房间代码</span></th>
			<th><span>路址</span></th>
			<th><span>总价</span></th>
			<th><span>应签约日期</span></th>
			<th><span>签约日期</span></th>
			<th><span>付款方式</span></th>
			<th><span>按揭银行</span></th>
			<th><span>银行分类</span></th>
			<th><span>草签日期</span></th>
			<th><span>正式签约日期</span></th>
			<th><span>按揭金额</span></th>
			<th><span>放款日期</span></th>
			<th><span>客户名称</span></th>
			<th><span>业务员</span></th>
			<th><span>滞纳金余额</span></th>
			<th><span>按揭类型</span></th>
			<th><span>放款金额</span></th>
			<th><span>公积金金额</span></th>
			<th><span>导入时间</span></th>
    </tr>
    	
    	<c:forEach items="${list}" var="usercg" varStatus="st">
    	 	<tr>
    	 	<td>${st.index + 1}</td>
				<td>${usercg.xuhao}</td>	
				<td>${usercg.quyu}</td>
				<td>${usercg.shengfen}</td>
				<td>${usercg.objectName}</td>
				<td>${usercg.fangjiandaima}</td>
				<td>${usercg.addressUrl}</td>
				<td>${usercg.zongjia}</td>
				<td>${usercg.yingqianyueriqi}</td>
				<td>${usercg.qianyueriqi}</td>
				<td>${usercg.fukuanfangshi}</td>
				<td>${usercg.anjieyinhang}</td>
				<td>${usercg.yinhangfenlei}</td>
				<td>${usercg.caoqianriqi}</td>
				<td>${usercg.zhengshiqianyueriqi}</td>
				<td>${usercg.anjiejine}</td>
				<td>${usercg.fangkanriqi}</td>
				<td>${usercg.kehumingcheng}</td>
				<td>${usercg.yewuyuan}</td>
				<td>${usercg.dainajinjine}</td>
				<td>${usercg.anjieleixing}</td>
				<td>${usercg.fangkuanjine}</td>
				<td>${usercg.gongjijinjine}</td>
				<td>${usercg.lurudate}</td>
    	 	</tr>
    	</c:forEach>
    	<tr>
    		<td></td>	
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td style="font-weight:bold ; font-color:red ;">总金额：</td>
			<td>${moneyAll}</td>
    	</tr>
    </table>

    <script>
        // 使用outerHTML属性获取整个table元素的HTML代码（包括<table>标签），然后包装成一个完整的HTML文档，设置charset为urf-8以防止中文乱码
        var html = "<html><head><meta charset='utf-8' /></head><body>" + document.getElementsByTagName("table")[0].outerHTML + "</body></html>";
        // 实例化一个Blob对象，其构造函数的第一个参数是包含文件内容的数组，第二个参数是包含文件类型属性的对象
        var blob = new Blob([html], { type: "application/vnd.ms-excel" });
        var a = document.getElementsByTagName("a")[0];
        // 利用URL.createObjectURL()方法为a元素生成blob URL
        a.href = URL.createObjectURL(blob);
        // 设置文件名
        a.download = "导出回款客户.xls";
    </script>
    
</body></html>