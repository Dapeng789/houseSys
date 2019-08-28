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
    <link rel="stylesheet" href="css/pintuer.css">
    <link rel="stylesheet" href="css/admin.css">
    <script src="js/jquery.js"></script>	
    <script src="js/pintuer.js"></script>  
   	
   	<style type="text/css">
   	#location {
			text-align:center;border:1px solid #dddddd;padding:10px 5px;width:270px;border-radius:6px;position:relative;left:100px;top:80px;font-size: 15px;
    	}
    	
    	#storpid {
    		text-align:center;border:1px solid #dddddd;padding:10px 5px;width:270px;border-radius:6px;position:relative;left:100px;top:100px;font-size: 15px;
    	}
    	
    	#style {
    		font-size: 17px;position:relative;left:70px;top:80px;
    	}
    	 	
    	#style1 {
    		font-size: 17px;position:relative;left:70px;top:100px;
    	}	
    	
    	 	
    	#sub { 
    		text-align:center;padding:10px 5px; width:230px;border-radius:12px;font-size: 16px;position:relative;left:180px;top:170px;	
    	}
    	
    	#put {
    		text-align:center;padding:10px 5px; width:110px;border-radius:12px;	font-size: 14px;position:relative;left:1050px;
    	}
		
		/* #tab {
		      text-align:center;
		      padding-left:40px
		     } */
		     
		table tr td span{padding:7px;} 
		
		#location1 {
			width:700px;
			height:400px;	
			border:3px #ccc solid;
			position:absolute; 
			left:510px;
			top:120px;
			border-radius:6px;
		}
   	</style>
</head>	
<body>
	<div id = "location1">
	  <br>
	    <form action="queryExcelDataController/getC15TableData.do" method="post" id= "f" >
	    		<span id= "style">查询C15-按揭明细表开始时间：</span> 
	    		<input type = "text" name = "startdate" id = "location"  disabled="disabled"  value="默认查询全部Excel数据" /><br>
	    		<span id= "style1">查询C15-按揭明细表结束时间：</span>
	    		<input type = "text" name = "storpdate" id = "storpid"   disabled="disabled"  value="默认查询全部Excel数据" />
	    		<input class = "button bg-main icon-check-square-o" type = "submit" value = "一键查询所有数据" id= "sub">
	    </form>
	 </div>
	 
	 
	 <script type="text/javascript">
	    //提交表单的事件监听
	   /*  f.onsubmit = function (){
	    	var inputStart=$("#location").val();
	  	    var inputStorp=$("#storpid").val();
	  	    
	  	    //首先判断开始值是否为空，为空直接提示
	  	    //判断
	  	    if(inputStart != ""){
	  	    //去掉指定字符
		  	  	var inputStart1=inputStart.replace("-","");
		  	  	var inputStart2=inputStart1.replace("-","");
		  	  	//判断最早查询的值
		  	  	var value =20180101;
		  	  	if(inputStart2 >= value){
		  	  	//再判断结束时间为不为空，如果不为空，获取，为空 不获取
			  	  	if(inputStorp != ""){
				  	  	var inputStorp1=inputStorp.replace("-","");
				  	  	var inputStorp2=inputStorp1.replace("-","");
				  	  	
				  		//开始时间大于结束时间 则提示
				  	  	if(inputStart > inputStorp){
				  	  		alert("查询开始时间不能大于结束时间！");
				  	  		return false;
				  	  	}
			  	  	}
		  	  	}else{
		  	  		alert("可查询最早时间为2018年1月1号！");
		  	  		return false;
		  	  	}
	  	    }else{
	  	    	alert("最少需输入开始时间! 按时间查询");
	  	    	return false;
	  	    }
	    } */
	 </script>
</body></html>