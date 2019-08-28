<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset = "UTF-8">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <meta name="renderer" content="webkit">
    <title>数据直连中心</title>  
    <link rel="stylesheet" href="${pageContext.request.contextPath }/css/pintuer.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/css/admin.css">
    <script src="${pageContext.request.contextPath }/js/jquery.js"></script>   
</head>
<body>
	<table border="0" align="center" cellspacing="0" class="list_table"
         id="senfe" style='width: 90%'>
         <thead>
             <tr>
                  <td colspan='7'>
                      <form method="POST" name="form" action="getStudent">
                          <!-- 开发阶段 没有对用户的非法输入进行过滤-->
                          <input type="hidden" name="flag" value="1"> <input
                               type="text" name="stuid" value="学号"
                              onclick="if(this.value == '学号')this.value ='';"
                             onBlur="if(this.value == ' '||this.value == '')this.value ='学号';">
                          <input type="text" name="stuname" value="姓名"
                              onclick="if(this.value == '姓名')this.value ='';"
                             onBlur="if(this.value == ' '||this.value == '')this.value ='姓名';">
                         <input type="text" name="stusex" value="性别"
                              onclick="if(this.value == '性别')this.value ='';"
                             onBlur="if(this.value == ' '||this.value == '')this.value ='性别';">
                          <input type="text" name="stuage" value="年龄"
                              onclick="if(this.value == '年龄')this.value ='';"
                             onBlur="if(this.value == ' '||this.value == '')this.value ='年龄';">
                          <input type="text" name="stumajor" value="专业"
                            onclick="if(this.value == '专业')this.value ='';"
                             onBlur="if(this.value == ' '||this.value == '')this.value ='专业';">
                        <input type="radio" name="selAdd" checked onclick="selop()">
                         <input type="submit" value="查询" id="selcon"> <input
                              type="radio" name="selAdd" onclick="selop()"><input
                             type="submit" disabled value="增加" id="addcon" onclick="setPar()">
                    </form>
                   </td>
              </tr>
             <tr>
                  <th>学号</th>
                 <th>姓名</th>
                  <th>性别</th>
                 <th>年龄</th>
               <th>专业</th>
                  <th>删除</th>
                  <th>修改</th>
              </tr>
         </thead>
         <tbody>
             <form method="post" name="DelUp" action="getStudent_extend">
                 <c:forEach var="student" items="${students }">
                     <tr align="center">
                         <td>${student.stuno }</td>
                         <td>${student.name }</td>
                          <td>${student.sex }</td>
                         <td>${student.age }</td>
                          <td align="left">${student.major }</td>
                         <td><input type="submit" name=${student.stuno } value="删除"
                             onclick="del(this)"></td>
                          <td><input type="button" name=${student.stuno } value="修改"
                              onclick="updata(this)"></td>
                     </tr>
                  </c:forEach>
                 
                      <div id="updateDiv">
                     学号：<input type="text" name="ud" readOnly>
                     姓名：<input type="text" name="ud_name">
                      性别：<input type="text" name="ud_sex">
                     年龄：<input type="text" name="ud_age">
                    专业：<input type="text" name="ud_major">
                      
                      <input type="submit" value="更新">
                     <span id="cancelUpdate" onclick="cancelupdate()">取消</span>
                     </div>
                      
                  <input type="hidden" name="flag_delUp"> <input type="hidden"
                      name="parameter_del">
              </form>
         </tbody>
      </table>
</body>
</html>