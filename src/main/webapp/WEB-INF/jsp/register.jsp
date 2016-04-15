<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: blf2
  Date: 16-4-5
  Time: 下午5:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <jsp:include page="top.html"/>
  <link href="../../css/signin.css" rel="stylesheet" />
  <title>Register</title>
  <script type="text/javascript">
    function checkPassword(){
      var pswd1 = document.getElementById("userPswd");
      var pswd2 = document.getElementById("rePswd");
      if(pswd1.value === pswd2.value){
        return true;
      }
      var pt = document.getElementById("resultOfCheckPswd");
      pt.innerHTML="<p id=\"resultOfCheckPswd\">两次输入的密码不一样！</p>";
      return false;
    }
    function removeDialog(){
      var pt = document.getElementById("resultOfCheckPswd");
      pt.innerHTML="<p id=\"resultOfCheckPswd\"></p>";
    }
  </script>
</head>
<body>
<div class="container">
  <form class="form-signin" action="/User/register.action" method="post">
    <h4 class="form-signin-heading">请填写：</h4>
    <label for="userEmail">邮箱：</label>
    <input type="email" id="userEmail" name="userEmail" class="form-control" placeholder="Email address" maxlength="100" required autofocus>
    <label for="userPswd" >密码：</label>
    <input type="password" id="userPswd" name="userPswd" class="form-control" placeholder="Password" maxlength="50" required>
    <label for="rePswd" >确认密码：</label>
    <input type="password" id="rePswd" class="form-control" name="rePswd" placeholder="Password" maxlength="50" onfocus="removeDialog()" onblur="checkPassword()" required />
    <p id="resultOfCheckPswd"></p>
    <label for="userName" >用户名：</label>
    <input type="text" id="userName" class="form-control" name="userName" placeholder="userName" maxlength="50" required />
    <br />
    <button class="btn btn-lg btn-primary btn-block form-signin" type="submit">添加</button>
  </form>
</div>
</body>
</html>
