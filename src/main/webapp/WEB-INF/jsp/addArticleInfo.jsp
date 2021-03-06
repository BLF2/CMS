<%@ page import="net.blf2.model.entity.UserInfo" %>
<%--
  Created by IntelliJ IDEA.
  User: blf2
  Date: 16-4-12
  Time: 下午8:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
  <jsp:include page="top.html"></jsp:include>
  <title>AddArticle</title>
  <script type="text/javascript" src="../../ckeditor/ckeditor.js"></script>
</head>
<body>

<jsp:include page="bodytop.html"></jsp:include>
<%
  UserInfo userInfo = (UserInfo) request.getSession().getAttribute("loginInfo");
  if(userInfo == null){%>
    <a href="/User/toLogin.action"><h3>请登录</h3></a>
  <%}else{
%>

<div class="row">
  <div class="col-md-2">
    <ul class="nav nav-sidebar">
      <li class="active">
        <a href="#">可用操作：</a>
      </li>
      <li>
        <a href="/User/toUserMainPage.action">放弃编辑并返回</a>
      </li>
    </ul>
  </div>
  <div class="col-md-8">
    <form action="/Article/addArticleInfo.action" method="post">
      <input type="hidden" name="userId" value="<%=userInfo.getUserId()%>"/>
      <div class="form-group">
      <label for="articleTitle">文章题目：</label>
      <input id="articleTitle" name="articleTitle" type="text" class="form-control" placeholder="ArticelTitle" />
      </div>
      <div class="form-group">
        <label for="articleWriter">作者：</label>
        <input id="articleWriter" type="text" class="form-control" value="<%=userInfo.getUserName()%>" readonly="readonly"/>
      </div>
    <textarea name="articleInfoEditor" class="form-control" rows="20"></textarea>
    <script type="text/javascript">CKEDITOR.replace('articleInfoEditor')</script>
      <div class="col-md-3">
        <button class="btn btn-primary" type="submit" name="Submit" value="publish">发表</button>
      </div>
      <div class="col-md-5">
        <button class="btn btn-primary" type="submit" name="Submit" value="drafts">保存为草稿</button>
      </div>
    </form>
  </div>
</div>

<%}%>
</body>
</html>
