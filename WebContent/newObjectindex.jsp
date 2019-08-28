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
  
  <h2><span class="icon-user" style = "font-size:17px;">房产项目管理</span></h2>
  <ul style="display:block">
    <li><a href="${pageContext.request.contextPath }/addObejct.jsp" target="right" style = "font-size:17px;"><span class="icon-caret-right"></span>增加新项目</a></li>
    <li><a href="${pageContext.request.contextPath }/deleteObject.jsp" target="right" style = "font-size:17px;"><span class="icon-caret-right"></span>删除指定项目</a></li>
    
  	<%--   <li><a href="${pageContext.request.contextPath }/page.jsp" target="right" style = "font-size:17px;"><span class="icon-caret-right"></span>权限管理</a></li>
    <li><a href="${pageContext.request.contextPath }/page.jsp" target="right" style = "font-size:17px;"><span class="icon-caret-right"></span>菜单管理</a></li>   --%>
    
  </ul>   
 
 

</div>
<script type="text/javascript">
$(function(){
  $(".leftnav h2").click(function(){
	  $(this).next().slideToggle(200);	
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