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
    <title>房产信息处理系统</title>  
    <link rel="stylesheet" href="${pageContext.request.contextPath }/css/pintuer.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/css/admin.css">
    <script src="${pageContext.request.contextPath }/js/jquery.js"></script>   
</head>

<body style="background-color:#f2f9fd;">
<div class="header bg-main">
  <div class="logo margin-big-left fadein-top">
    <h1><img src="images/fangzi.jpg" class="radius-circle rotate-hover" height="50" alt="" />&nbsp&nbsp&nbsp欢迎使用 购房尾款 信息化管理系统  &nbsp&nbsp&nbsp</h1>
  </div>
  
  <div class="head-l">&nbsp;&nbsp;
  	<a class="button button-little bg-red" href="${pageContext.request.contextPath }/login.jsp"  style="text-align:right;" style = "font-size:17px;">
  		<span class="icon-power-off"></span> 
  	退出登录
  	</a> 
  </div>
</div>   
                                                                                                                
<div class="leftnav">
   <div class="leftnav-title" style = "font-size:17px;"><strong><span class="icon-list"></span>购房尾款管理</strong></div>
  
  <%--<h2><span class="icon-user" style = "font-size:17px;">权限管理</span></h2>
  <ul style="display:block">
    <li><a href="${pageContext.request.contextPath }/user.jsp" target="right" style = "font-size:17px;"><span class="icon-caret-right"></span>用户管理</a></li>
    
    <li><a href="${pageContext.request.contextPath }/page.jsp" target="right" style = "font-size:17px;"><span class="icon-caret-right"></span>权限管理</a></li>
    <li><a href="${pageContext.request.contextPath }/page.jsp" target="right" style = "font-size:17px;"><span class="icon-caret-right"></span>菜单管理</a></li>  
  </ul>   
 
   --%>
  
  <h2><span class="icon-pencil-square-o" style = "font-size:18px;">初始导入统计</span></h2>
  <ul style="display:block">
  	<li><a href="${pageContext.request.contextPath }/excelUploading.jsp" target="right" style = "font-size:17px;"><span class="icon-caret-right"></span>导入C03Excel表数据</a></li>
  	<li><a href="${pageContext.request.contextPath }/initImportC03TableController/getExcelData.do" target="right" style = "font-size:17px;"><span class="icon-caret-right"></span>查询C03表中有效客户</a></li>
  	<li><a href="${pageContext.request.contextPath }/initImportC03TableController/getWuxiaoUser.do" target="right" style = "font-size:17px;"><span class="icon-caret-right"></span>查询C03初始无效客户</a></li>
  	<li><a href="${pageContext.request.contextPath }/5ChuShiWuXiaoUser.jsp" target="right" style = "font-size:17px;"><span class="icon-caret-right"></span>按时间查询初始无效客户</a></li>
  	<li><a href="${pageContext.request.contextPath }/initImportC03TableController/getAllNewUser.do" target="right" style = "font-size:17px;"><span class="icon-caret-right"></span>本次新增客户</a></li>
  	<li><a href="${pageContext.request.contextPath }/initImportC03TableController/getC03TableAll.do" target="right" style = "font-size:17px;"><span class="icon-caret-right"></span>筛选欠款客户</a></li>
  </ul>
  
  
  
    <h2><span class="icon-pencil-square-o" style = "font-size:18px;">计算回款客户数据</span></h2>
  <ul style="display:block">
    <li><a href="${pageContext.request.contextPath }/c15excelTableshangchuan.jsp" target="right" style = "font-size:17px;"><span class="icon-caret-right"></span>导入C15按揭明细表</a></li>
    <li><a href="${pageContext.request.contextPath }/c15HuiKuanTableController/zhouHuiKuanData.do" target="right" style = "font-size:17px;"><span class="icon-caret-right"></span>查看最近5周回款的客户</a></li>
  	<li><a href="${pageContext.request.contextPath }/5ZhouHuKuanData.jsp" target="right" style = "font-size:17px;"><span class="icon-caret-right"></span>按时间查询回款客户</a></li>
  	<li><a href="${pageContext.request.contextPath }/c15HuiKuanTableController/getBuFenHuanKuanKuanHu.do" target="right" style = "font-size:17px;"><span class="icon-caret-right"></span>查看已还部分欠款客户</a></li>
  </ul>  
  
  
  
 <h2><span class="icon-pencil-square-o" style = "font-size:18px;">季度性检查</span></h2>
  <ul style="display:block">
  	<li><a href="${pageContext.request.contextPath }/initImportC03TableController/getAllUser.do" target="right" style = "font-size:17px;"><span class="icon-caret-right"></span>超期草签客户表</a></li>
  	<li><a href="${pageContext.request.contextPath }/chaoqiSetZhengQian.jsp" target="right" style = "font-size:17px;"><span class="icon-caret-right"></span>超期已改为正签表</a></li>
  	<li><a href="${pageContext.request.contextPath }/initImportC03TableController/getChaoQiNoFuKuanUser.do" target="right" style = "font-size:17px;"><span class="icon-caret-right"></span>超期未付客户表</a></li>
  </ul>
  

</div>
<script type="text/javascript">
$(function(){
  $(".leftnav h2").click(function(){
	  $(this).next().slideToggle(200);	ss
	  $(this).toggleClass("on"); 
  })
  $(".leftnav ul li a").click(function(){
	    $("#a_leader_txt").text($(this).text());
  		$(".leftnav ul li a").removeClass("on");
		$(this).addClass("on");
  })
});
</script>
<ul class="bread">
  <li><a href="${pageContext.request.contextPath }/image.jsp" target="right" class="icon-home" style = "font-size:17px;"> 首页</a></li>
</ul>
<div class="admin">	
  <iframe scrolling="auto" rameborder="0" src="${pageContext.request.contextPath }/image.jsp" name="right" width="100%" height="100%"></iframe>
</div>
</body>
</html>