<%--
  Created by IntelliJ IDEA.
  User: blf2
  Date: 16-4-5
  Time: 下午5:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
  <jsp:include page="top.html"/>
  <link href="../../css/signin.css" rel="stylesheet" />
    <title>Login</title>
  <script type="text/javascript">
  <%
    String status = (String) request.getSession().getAttribute("loginStatus");
    if(status != null){
   request.getSession().removeAttribute("loginStatus");
      if("NoEmail".equals(status)){%>
        alert("登录邮箱不存在！");
      <%}else if("NoMactch".equals(status)){%>
        alert("登录邮箱和密码不匹配！");
     <% }
    }
  %>
  </script>
</head>
<body>
<div class="container">

  <form class="form-signin" action="/User/login.action" method="post">
    <h4 class="form-signin-heading">请登录：</h4>
    <label for="userEmail">邮箱：</label>
    <input type="email" id="userEmail" name="userEmail" class="form-control" placeholder="Email address" required autofocus>
    <label for="userPswd">密码：</label>
    <input type="password" id="userPswd" name="userPswd" class="form-control" placeholder="Password" required>
    <div class="checkbox">
      <label>
        <input type="checkbox" name="remember-me" value="remember-me"> 记住我
      </label>
    </div>
    <button class="btn btn-lg btn-primary btn-block" type="submit">登录</button>
  </form>
</div>
</body>
</html>
