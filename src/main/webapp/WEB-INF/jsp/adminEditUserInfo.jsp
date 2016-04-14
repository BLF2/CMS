<%@ page import="net.blf2.model.entity.UserInfo" %>
<%--
  Created by IntelliJ IDEA.
  User: blf2
  Date: 16-4-14
  Time: 上午8:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
  <jsp:include page="top.html"></jsp:include>
  <title>Admin Edit User Info Page</title>
  <script type="text/javascript">
    function dialog(){
      return confirm("确定修改?");
    }
  </script>
</head>
<body>
<jsp:include page="bodytop.html"></jsp:include>
<%
  UserInfo adminEditUserInfo = (UserInfo) request.getSession().getAttribute("adminEditUserInfo");
  if(adminEditUserInfo == null){%>
    <h3>不可编辑！！！</h3>
  <%}else{
%>
<div class="row">
  <div class="col-md-4"></div>
  <div class="col-md-4">
    <form action="/User/adminUpdateUserInfo.action" method="post">
      <input name="userId" type="hidden" value="<%=adminEditUserInfo.getUserId()%>" />
      <table class="table table-bordered">
        <tr>
          <td>登录邮箱：</td>
          <td><input type="text" name="userEmail" value="<%=adminEditUserInfo.getUserEmail()%>" required="required"/></td>
        </tr>
        <tr>
          <td>登录密码：</td>
          <td><input type="text" name="userPswd" value="<%=adminEditUserInfo.getUserPswd()%>" required="required"/></td>
        </tr>
        <tr>
          <td>登录昵称：</td>
          <td><input type="text" name="userName" value="<%=adminEditUserInfo.getUserName()%>" required="required"></td>
        </tr>
        <tr>
          <td>权限：</td>
          <td>
            <%if(adminEditUserInfo.getUserRule().isUser()){%>
            <label><input type="radio" name="rule" value="0" checked="checked"/>普通用户</label>
            <%}else{%>
            <label><input type="radio" name="rule" value="0"/>普通用户</label>
            <%
              }
              if(adminEditUserInfo.getUserRule().isInactive()){
            %>
            <label><input type="radio" name="rule" value="2" checked="checked"/>冻结状态</label>
            <%}else{%>
            <label><input type="radio" name="rule" value="2"/>冻结状态</label>
            <%}%>
          </td>
        </tr>
        <tr>
          <td><button class="btn btn-primary" type="submit" name="Submit" onclick="return dialog()" value="yes">确认修改</button></td>
          <td><button class="btn btn-primary" type="submit" name="Submit" value="no">返回</button></td>
        </tr>
      </table>

    </form>
    <%}%>
  </div>
  <div class="col-md-4"></div>
</div>
</body>
</html>
