<%@ page import="net.blf2.model.entity.ArticleInfo" %>
<%@ page import="net.blf2.model.entity.UserInfo" %>
<%--
  Created by IntelliJ IDEA.
  User: blf2
  Date: 16-4-19
  Time: 上午11:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta name="description" content="">
  <meta name="author" content="">
  <link rel="icon" href="http://7xrn7f.com1.z0.glb.clouddn.com/16-4-5/17279148.jpg">

  <title>My Blog</title>

  <link href="../../css/bootstrap.min.css" rel="stylesheet">

  <link href="../../css/offcanvas.css" rel="stylesheet">

  <script src="../../js/ie-emulation-modes-warning.js"></script>
</head>
<%
  ArticleInfo articleInfo = (ArticleInfo) request.getSession().getAttribute("frontArticleInfo");
  UserInfo userInfo = (UserInfo)request.getSession().getAttribute("writerInfo");
%>

<body>
<nav class="navbar navbar-fixed-top navbar-inverse">
  <div class="container">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="#">V·Cater丨死亡之扣</a>
    </div>
    <div id="navbar" class="collapse navbar-collapse">
      <ul class="nav navbar-nav">
        <li class="active"><a href="#">博客</a></li>
        <li><a href="#about">关于</a></li>
        <li><a href="/User/toLogin.action">会员入口</a></li>
      </ul>
    </div><!-- /.nav-collapse -->
  </div><!-- /.container -->
</nav><!-- /.navbar -->

<div class="container">

  <div class="row row-offcanvas row-offcanvas-right">

    <div class="col-xs-12 col-sm-9">
      <p class="pull-right visible-xs">
        <button type="button" class="btn btn-primary btn-xs" data-toggle="offcanvas">Toggle nav</button>
      </p>
      <div class="jumbotron">
        <h1>欢迎光临本站</h1>
        <p>本站是BLF2和他的朋友的网站。在这里，有技术博客，有生活琐事。如果您也有自己的博客，请在留言板里给我留言，我们可以互相添加友链。</p>
      </div>
      <div class="row">
        <div class="col-md-4">
         <h2><%=articleInfo.getArticleTitle()%></h2><br />
          <span><h4><%=userInfo.getUserName()%></h4><h4><%=articleInfo.getPublishDateTime()%></h4></span>
          <h3><%=articleInfo.getArticleText()%></h3>
        </div>
      </div>
    </div><!--/.col-xs-12.col-sm-9-->
    <div class="col-xs-6 col-sm-3 sidebar-offcanvas" id="sidebar">
      <div class="list-group">
        <a href="#" class="list-group-item active">文章</a>
        <a href="#" class="list-group-item">标签</a>
        <a href="#" class="list-group-item">朋友</a>
        <a href="#" class="list-group-item">语言</a>
        <a href="#" class="list-group-item">站长</a>
        <a href="#" class="list-group-item">留言板</a>
      </div>
    </div><!--/.sidebar-offcanvas-->
  </div><!--/row-->

  <hr>

  <footer>
    <p>&copy; @By BLF2</p>
  </footer>

</div><!--/.container-->

<script src="../../js/jquery.min.js"></script>
<script src="../../js/bootstrap.min.js"></script>
<script src="../../js/ie10-viewport-bug-workaround.js"></script>
<script src="../../js/offcanvas.js"></script>
</body>
</html>
