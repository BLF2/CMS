<%@ page import="net.blf2.model.entity.UserInfo" %>
<%@ page import="net.blf2.model.entity.ArticleInfo" %>
<%--
  Created by IntelliJ IDEA.
  User: blf2
  Date: 16-4-13
  Time: 上午11:01
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
  ArticleInfo articleInfo = (ArticleInfo)request.getSession().getAttribute("editCurrentArticle");
  if(articleInfo == null){%>
    <h3>不可编辑！！！</h3>
  <%}else{
%>

<div class="row">
  <div class="col-md-2">
    <ul class="nav nav-sidebar">
      <li class="active">
        <a href="#">可用操作：</a>
      </li>
      <li>
        <a href="/WEB-INF/jsp/IndexPage.jsp">放弃编辑并返回</a>
      </li>
    </ul>
  </div>
  <div class="col-md-8">
    <form action="/Article/updateArticleInfo.action" method="post">
      <input type="hidden" name="userId" value="<%=userInfo.getUserId()%>"/>
      <input type="hidden" name="articleId" value="<%=articleInfo.getArticleId()%>" />
      <div class="form-group">
        <label for="articleTitle">文章题目：</label>
        <input id="articleTitle" name="articleTitle" type="text" class="form-control" placeholder="ArticelTitle" value="<%=articleInfo.getArticleTitle()%>" />
      </div>
      <div class="form-group">
        <label for="articleWriter">作者：</label>
        <input id="articleWriter" type="text" class="form-control" value="<%=userInfo.getUserName()%>" readonly="readonly"/>
      </div>
      <textarea name="articleInfoEditor" rows="15"><%=articleInfo.getArticleText()%></textarea>
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

<%}
}%>
</body>
</html>

