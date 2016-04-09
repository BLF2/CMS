<%@ page import="net.blf2.model.entry.UserInfo" %>
<%--
  Created by IntelliJ IDEA.
  User: blf2
  Date: 16-4-9
  Time: 上午10:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
  <jsp:include page="top.html"></jsp:include>
  <title>LookAndEditPersonalInfo Page</title>
  <script type="text/javascript">
    function dialog(){
      return confirm("确定修改?");
    }
  </script>
</head>
<body>
<jsp:include page="bodytop.html"></jsp:include>
<%
  UserInfo userInfo = (UserInfo) request.getSession().getAttribute("loginInfo");
%>
<div class="row">
  <div class="col-md-4"></div>
<div class="col-md-4">
<form action="/User/updatePersonalInfo.action" method="post">
  <input name="userId" type="hidden" value="<%=userInfo.getUserId()%>" />
  <table class="table table-bordered">
    <tr>
      <td>登录邮箱：</td>
      <td><%=userInfo.getUserEmail()%></td>
    </tr>
    <tr>
        <td>登录密码：</td>
        <td><input type="text" name="userPswd" value="<%=userInfo.getUserPswd()%>" required="required"/></td>
    </tr>
    <tr>
      <td>登录昵称：</td>
      <td><input type="text" name="userName" value="<%=userInfo.getUserName()%>" required="required"></td>
    </tr>
    <tr>
      <td>权限：</td>
      <%if(userInfo.getUserRule().isUser()){%>
      <td>普通用户</td>
      <%}else if(userInfo.getUserRule().isAdmian()){%>
      <td>管理员</td>
      <%}%>
    </tr>
    <tr>
      <td><button class="btn btn-primary" type="submit" name="Submit" onclick="return dialog()" value="yes">确认修改</button></td>
      <td><button class="btn btn-primary" type="submit" name="Submit" value="no">返回</button></td>
    </tr>
  </table>

</form>
  </div>
  <div class="col-md-4"></div>
  </div>
</body>
</html>
