<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="net.blf2.model.entity.ArticleInfo" %>
<%@ page import="java.util.List" %>
<%@ page import="org.springframework.web.portlet.ModelAndView" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="net.blf2.model.entity.UserInfo" %>
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
    <title>PrimaryMainPage</title>
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
        <a href="/User/toLookPersonalInfo.action">查看个人信息</a>
      </li>
      <li>
        <a href="/User/toLookPersonalInfo.action">修改个人信息</a>
      </li>
      <li>
        <a href="/Article/toAddArticleInfo.action">添加个人文章</a>
      </li>
      <li>
        <a href="#">查看个人文章</a>
      </li>
    </ul>
  </div>
  <div class="col-md-9" name="rightdiv" id="ableToChange">
    <table class="table table-bordered">
      <tr>
        <td>
          文章题目
        </td>
        <td>
          发表时间
        </td>
        <td>
          文章状态
        </td>
        <td>
          操作1
        </td>
        <td>
          操作2
        </td>
        <%
          HttpSession httpSession = request.getSession();

          List<ArticleInfo>alist = (List<ArticleInfo>) httpSession.getAttribute("ListOfArticleByWriterId");
          if(alist == null){%>
          无记录！
          <%}else{
          Iterator<ArticleInfo>iterator = alist.iterator();
          while(iterator.hasNext()){
            ArticleInfo articleInfo = iterator.next();%>
        <tr>
          <a href="/Article/look.action?articleId=<%=articleInfo.getArticleId()%>"><td><%=articleInfo.getArticleTitle()%></td></a>
          <td><%=articleInfo.getPublishDateTime()%></td>
        <%if(articleInfo.getArticleStatus().isPublished()){%>
      <td>已发表</td>
      <%}else if(articleInfo.getArticleStatus().isDrafts()){%>
      <td>草稿</td>
      <%}else{%>
      <td>回收站</td>
      <%}%>
      <a href="/Article/edit.action?articleId=<%=articleInfo.getArticleId()%>"><td><button type="button" class="btn btn-primary">编辑</button></td></a>
        <a href="/Article/delete.action?articleId=<%=articleInfo.getArticleId()%>"><td><button type="button" class="btn btn-primary">删除</button></td></a>
        </tr>
          <%}
          }
        %>
      </tr>
    </table>
  </div>
</div>
</body>
</html>
