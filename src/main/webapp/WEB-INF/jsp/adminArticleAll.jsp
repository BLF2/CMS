<%@ page import="net.blf2.model.entity.ArticleInfo" %>
<%@ page import="java.util.List" %>
<%@ page import="net.blf2.model.entity.UserInfo" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.Map" %>
<%--
  Created by IntelliJ IDEA.
  User: blf2
  Date: 16-4-16
  Time: 下午2:12
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
  <div class="col-md-9" name="rightdiv" id="ableToChange">
    <table class="table table-bordered">
      <tr>
        <td>
          文章题目
        </td>
        <td>
          作者
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
      </tr>

      <%
        HttpSession httpSession = request.getSession();

        List<ArticleInfo> alist = (List<ArticleInfo>) httpSession.getAttribute("articleInfoAllList");
        Map<Integer,String> map = (Map<Integer, String>) httpSession.getAttribute("UserIdToName");
        if(alist == null){%>
      无记录！
      <%}else{
        Iterator<ArticleInfo> iterator = alist.iterator();
        while(iterator.hasNext()){
          ArticleInfo articleInfo = iterator.next();%>
      <tr>
        <td>
          <a href="/Article/frontLookArticle.action?articleId=<%=articleInfo.getArticleId()%>"><%=articleInfo.getArticleTitle()%></a>
        </td>
        <td>
          <%=map.get(articleInfo.getWriterId())%>
        </td>
        <td>
          <%=articleInfo.getPublishDateTime()%>
        </td>
        <%if(articleInfo.getArticleStatus().isPublished()){%>
        <td>
          已发表
        </td>
        <%}else if(articleInfo.getArticleStatus().isDrafts()){%>
        <td>
          草稿
        </td>
        <%}else{%>
        <td>
          回收站
        </td>
        <%}%>
        <td>
          <a href="/Article/edit.action?articleId=<%=articleInfo.getArticleId()%>&userId=<%=articleInfo.getWriterId()%>"><button type="button" class="btn btn-primary">编辑</button></a>
        </td>
        <td>
          <a href="/Article/delete.action?articleId=<%=articleInfo.getArticleId()%>&userId=<%=articleInfo.getWriterId()%>"><button type="button" class="btn btn-primary" onclick="return checkDelete()">删除</button></a>
        </td>
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
