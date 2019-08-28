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
    <link rel="stylesheet" href="${pageContext.request.contextPath }/css/pintuer.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/css/admin.css">
    <script src="${pageContext.request.contextPath }/js/jquery.js"></script>	
    <script src="${pageContext.request.contextPath }/js/pintuer.js"></script>  
   	
   	<style type="text/css">
   	#location {
			text-align:center;border:1px solid #dddddd;padding:10px 5px;width:270px;border-radius:6px;position:relative;left:100px;top:80px;font-size: 15px;
    	}
    	
    	#storpid {
    		text-align:center;border:1px solid #dddddd;padding:10px 5px;width:270px;border-radius:6px;position:relative;left:100px;top:100px;font-size: 15px;
    	}
    	
    	#style {
    		font-size: 17px;position:relative;left:3px;top:32px;
    	}
    	 	
    	#style1 {
    		font-size: 17px;position:relative;left:70px;top:100px;
    	}	
    	#sub { 
    		text-align:center;padding:7px 5px; width:50px ;border-radius:12px;font-size: 16px;position:relative;left:3px;top:0px;	
    	}
    	
    	#put {
    		text-align:center;padding:10px 5px; width:110px;border-radius:12px;	font-size: 14px;position:relative;left:1050px;
    	}
		
		 #tab {
		      text-align:center;
		      padding-left:40px
		     } 
		     
		table tr td span{padding:7px;} 
		
		#location1 {
			width:800px;
			height:400px;	
			border:3px #ccc solid;
			position:absolute; 
			left:450px;
			top:100px;
			border-radius:6px;
		}
		
		#2222 {
			font-size:20px;
			font-style: normal;
		}
   	</style>
</head>	
<body>
	<!--  <div id = "location1">
	  <br>
	    <form action="drug/getcaigou.do" method="post"  id="f">
	    		<span id= "style">查询采购开始时间：</span>
	    		<input type = "date" name = "startdate" id = "location"/><br>
	    		<span id= "style1">查询采购结束时间：</span>
	    		<input type = "date" name = "storpdate" id = "storpid"/>
	    		<input class = "button bg-main icon-check-square-o" type="submit"  value = "查询采购数据" id= "sub">
	    </form>
	 </div>  -->
	 
<!-- 	 <div id="addStu_mid">
	  <form id="form_email" name="form_email" action="/HelpProject/HelpThree/EmailAddStus" method="post">
	  <input type="text" name="send_email" id="send_email"  value="   E-mail:" onfocus="if (value =='   E-mail:'){value =''}"onblur="if (value ==''){value='   E-mail:'}" />
	  <input type="submit" name="send" id="send" value="Send" />
	 </form>
    	<input type="button" name="file_copy" id="file_copy" value="upload" />
    	<input type="button" name="download" id="download" value="submit" />
</div>
    	  隐藏的form表单  上传excel文件 
<div id="hidden_file_div" style="display:none">
       <form id="form_excel" name="form_excel"  action="/HelpProject/HelpThree/DoExcel" method="post" enctype="multipart/form-data">
    	 <input type="file" id="file_excel" name="file_excel" />
    	 <input type="submit"/>
    	 </form>
    	 <input type="text" name="filename" id="filename" />
</div>
 -->
	 
	 <div id = "location1">
	 	<form class="form form-horizontal" id="form-article-add" enctype="multipart/form-data">
		<div class="row cl">
			<label id = "style" class="form-label col-xs-4 col-sm-2">文件：</label>
			<div class="formControls col-xs-8 col-sm-9"> 
				<span class="btn-upload form-group">
					<input id="sub"  class="input-text upload-url" type="text" name="uploadfile" id="uploadfile" readonly nullmsg="请添加附件！" style="width:200px">
					
					
					<a href="javascript:void();" class="btn btn-primary radius upload-btn"><i class="Hui-iconfont" ></i></a>
						<input type="file" multiple name="file" class="input-file" id="file"> 
				</span> 
			</div>
		</div>
		<div class="row cl">
			<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
				<button id = "2222" onClick="importUsers();" class="btn btn-primary radius" type="button"><i class="Hui-iconfont"></i>导入数据</button>
				<button onClick="cancel();" class="btn btn-default radius" type="button">  取消  </button>
			</div>
		</div>
	</form>
	 
	 
	 </div>
	 
	 
	 <script type="text/javascript">
	 
	    /* //点击上传
	 	$('#file_copy').click(function(){
	 		$('#file_excel').click();
	 	})
	 	 $("#file_excel").bind("change",function(){
				 $("#filename").attr("value",$("#file_excel").val());
			 });	
	 	//点击提交
	 	$('#download').click(function(){
	 		
	 		$("#form_excel").submit();
	 		
	 	})
	  */
	  
	  
	  
	  function importUsers(){
			//var clientid = $("#clientid").val();
			var FormDatas=new FormData($("#form-article-add")[0]);

			//alert("一执行");
			var fileName=$("#file").val();
			//alert(fileName);
			if(fileName == '') {
				alert("请选择文件！");
		          return false;
		      }
			//验证文件格式
		       var fileType = (fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length)).toLowerCase();
			if (fileType == 'xlsx' || fileType == 'xls') {
				//正确  继续向下执行！
			}else{
				alert("文件格式不正确");
				return false;
			}
			$.ajax({
				type:"POST",
				url:"/houseSys/c15HuiKuanTableController/putC15TableData.do",
				async : false,
				cache : false,
				contentType : false,
				processData : false,
                data:FormDatas,
    			success: function(data){
					if(data == "chenggong"){
						alert("导入成功");						
					}else{
						alert("导入失败");
					}
				},
			});
		}

                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
				/* success: function(){
					alert("---------------------fsdjfl;sdkfsd --------------------");
					if(result == "error"){
						layer.msg("文件导入失败，请重新上传！"), {
							icon: OPER_SB,
							shade: [0.3, '#393D49'], // 透明度  颜色
							time:5000
							});
						return false;
					}else{
						layer.msg("文件导入成功！"), {
							icon: OPER_CG,
							shade: [0.3, '#393D49'], // 透明度  颜色
							time:5000
							};)
						window.location.reload();
						return false;
					}
				},
				error : function(data){
					console.log(data.msg);
				}
			});
		}
	   */
	 	
	 	
	  
	 
	   /*  //提交表单的事件监听
	    f.onsubmit = function (){
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