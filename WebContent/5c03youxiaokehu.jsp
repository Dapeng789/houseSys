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
    <table border="1"  width="7500">
    	<tr>
    	    <th><span>序号  </span></th>
    		<th><span>所属区域  </span></th>
			<th><span>区域总裁  </span></th>
		  	<th><span>楼盘 </span></th> 
			<th><span>付款方式 </span></th> 
			<th><span>房间编号</span></th>  
			<th><span>苑区</span></th>	
			<th><span>路址</span></th> 
			<th><span>业主名称</span></th>  
			<th><span>认购价 </span></th> 
			<th><span>应交款项</span></th>  
			<th><span>推售日期 </span></th> 
			<th><span>预售证日期 </span></th> 
			<th><span>竣工备案日期 </span></th> 
			<th><span>应交日期 </span></th> 
			<th><span>到期应交金额 </span></th> 
			<th><span>到期已交金额</span></th>  
			<th><span>到期欠交金额</span></th>  
			<th><span>未到期应交金额</span></th>  
			<th><span>未到期已交金额</span></th>  
			<th><span>未到期欠交金额</span></th>  
			<th><span>认购日期 </span></th> 
			<th><span>签约日期</span></th>  
			<th><span>按揭银行</span></th>  
			<th><span>房间类型</span></th>  
			<th><span>合同收楼日期</span></th>  
			<th><span>收楼通知书日期 </span></th> 
			<th><span>装修情况</span></th>  
			<th><span>跟办人</span></th>  
			<th><span>客户联系电话</span></th>  
			<th><span>公积金</span></th>  
			<th><span>合同路址</span></th>  
			<th><span>已交金额</span></th>  
			<th><span>已交日期 </span></th> 
			<th><span>结算价</span></th>  
			<th><span>到期0-30天欠交  </span></th>
			<th><span>到期31-90天欠交  </span></th>
			<th><span>到期91-180天欠交  </span></th>
			<th><span>到期181 270天欠交 </span></th>
			<th><span>到期271 360天欠交 </span></th>
			<th><span>到期361天以上欠交</span></th> 
			<th><span>预售建筑面积 </span></th> 
			<th><span>实测建筑面积</span></th>  
			<th><span>合同建筑面积 </span></th> 
			<th><span>客户联系地址</span></th>  
			<th><span>预计竣工日期 </span></th> 
			<th><span>草签日期  </span></th>
			<th><span>欠款原因  </span></th>
			<th><span>是否低首付  </span></th>
			<th><span>低首付类型  </span></th>
			<th><span>低首付金额 </span></th> 
			<th><span>签约状态</span></th>  
			<th><span>利润中心 </span></th>
    		<th><span>欠款总金额</span></th>
    	</tr>
    	
    	<c:forEach items="${list}" var="usercg" varStatus="st"  >
    	 	<tr>
    	 		<td>${st.index + 1}</td>
				<td>${usercg.area}</td>
				<td>${usercg.areaPresident}</td>
				<td>${usercg.building}</td>
				<td>${usercg.paymentMethod}</td>
				<td>${usercg.theRoomNumber}</td>
				<td>${usercg.park}</td>
				<td>${usercg.url}</td>
				<td>${usercg.ownerName}</td>
				<td>${usercg.subscriptionPrice}</td>
				<td>${usercg.fund}</td>
				<td>${usercg.sellingDate}</td>
				<td>${usercg.prePinDate}</td>
				<td>${usercg.closeoutDate}</td>
				<td>${usercg.deliveryDate}</td>
				<td>${usercg.deliveryOughtToMathod}</td>
				<td>${usercg.deliveryYetMathod}</td>
				<td>${usercg.deliveryDebtMathod}</td>
				<td>${usercg.noDeliveryOughtToMathod}</td>
				<td>${usercg.noDeliveryYetMathod}</td>
				<td>${usercg.noDeliveryDebtMathod}</td>
				<td>${usercg.subscribeDate}</td>
				<td>${usercg.signedDate}</td>
				<td>${usercg.bank}</td>
				<td>${usercg.roomType}</td>
				<td>${usercg.repossessionDate}</td>
				<td>${usercg.informDate}</td>
				<td>${usercg.decoration}</td>
				<td>${usercg.assistParent}</td>
				<td>${usercg.clientPhone}</td>
				<td>${usercg.reservedFunds}</td>
				<td>${usercg.contractaddress}</td>
				<td>${usercg.paidMathod}</td>
				<td>${usercg.yjDate}</td>
				<td>${usercg.callPrice}</td>
				<td>${usercg.under30}</td>
				<td>${usercg.under30To90}</td>
				<td>${usercg.under91To180}</td>
				<td>${usercg.under181To270}</td>
				<td>${usercg.under271To360}</td>
				<td>${usercg.under361Up}</td>
				<td>${usercg.prePinArea}</td>
				<td>${usercg.realityArea}</td>
				<td>${usercg.contractArea}</td>
				<td>${usercg.clientAddress}</td>
				<td>${usercg.finishDate}</td>
				<td>${usercg.cqDate}</td>
				<td>${usercg.debtCause}</td>
				<td>${usercg.lowPayment}</td>
				<td>${usercg.lowPaymentType}</td>
				<td>${usercg.lowPaymentMathod}</td>
				<td>${usercg.signedStart}</td>
				<td>${usercg.profitCentre}</td>
				<td>${usercg.moneyAll}</td>
    	 	</tr>
    	</c:forEach>
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
        a.download = "有效客户数据.xls";
    </script>
    
</body></html>