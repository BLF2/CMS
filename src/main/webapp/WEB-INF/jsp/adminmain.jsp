<%@ page import="java.util.List" %>
<%@ page import="net.blf2.model.entity.UserInfo" %>
<%@ page import="java.util.Iterator" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: blf2
  Date: 16-4-7
  Time: 下午8:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
  <jsp:include page="top.html"></jsp:include>
  <title>Admin Page</title>
</head>
<body>

<jsp:include page="bodytop.html"></jsp:include>
<div class="row">
  <div class="col-md-2">
    <ul class="nav nav-sidebar">
      <li class="active">
        <a href="#">可用操作：</a>
      </li>
      <li>
        <a href="/User/logout.action">注销当前登录</a>
      </li>
      <li>
        <a href="/User/toRegister.action">增加用户信息</a>
      </li>
      <li>
        <a href="/User/lookUserInfoAll.action">查看用户信息</a>
      </li>
      <li>
        <a href="/Article/lookArticleInfoAll.action">查看文章信息</a>
      </li>
      <li>
        <a href="/Article/toAddArticleInfo.action">增加文章信息</a>
      </li>
    </ul>
  </div>
  <div class="col-md-8">
    <table class="table table-bordered">
      <tr>
        <td>用户ID</td>
        <td>用户邮箱</td>
        <td>用户昵称</td>
        <td>用户密码</td>
        <td>用户权限</td>
        <td>操作1</td>
        <td>操作2</td>
        <td>查看</td>
      </tr>
    <%
      List<UserInfo>userInfoAllList = (List<UserInfo>) request.getSession().getAttribute("userInfoAllList");
      if(userInfoAllList == null){%>
        <h3>无记录！！！</h3>
      <%}else {
        Iterator <UserInfo>iterator = userInfoAllList.iterator();
        while(iterator.hasNext()){
          UserInfo userInfo = iterator.next();
      %>
      <tr>
      <td><%=userInfo.getUserId()%></td>
      <td><%=userInfo.getUserEmail()%></td>
      <td><%=userInfo.getUserName()%></td>
      <td><%=userInfo.getUserPswd()%></td>
      <td><%=userInfo.getUserRule()%></td>
      <td><a href="/User/adminEditUserInfo.action?userId=<%=userInfo.getUserId()%>"><button class="btn btn-primary">编辑</button></a></td>
      <td><a href="/User/adminDeleteUserInfo.action?userId=<%=userInfo.getUserId()%>"><button class="btn btn-primary" onclick="return checkDelete()">删除</button></a></td>
        <td><a href="/Article/lookUsersArticleAll.action?userId=<%=userInfo.getUserId()%>"><button class="btn btn-primary">所有文章</button></a></td>
      </tr>
      <%}
      }
    %>
    </table>
  </div>
</div>

<script type="text/javascript">
  function checkDelete(){
    return confirm("确定删除？");
  }
</script>
</body>
</html>
