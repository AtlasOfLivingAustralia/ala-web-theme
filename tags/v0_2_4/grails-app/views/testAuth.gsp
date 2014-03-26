<%--
  Created by IntelliJ IDEA.
  User: dos009@csiro.au
  Date: 27/08/13
  Time: 3:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%
    def authService = applicationContext.authService
%>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Test page for auth</title>
    <style type="text/css" media="screen">
    </style>
</head>
<body class="nav-datasets">
<h1>Test Page for AUTH</h1>
<div>
    <hf:loginLogout cssClass="btn"/> ${request.userPrincipal}
</div>
<div>
    <h4>AUTH users</h4>
    <table class="table table-bordered table-striped table-condensed">
        <thead>
            <tr>
                <th>User Id</th>
                <th>User Name</th>
                <th>Display Name</th>
            </tr>
        </thead>
        <tbody>
            <g:each var="user" in="${authService.allUserNameList}">
                <tr>
                    <td>${user.userId}</td>
                    <td>${user.userName}</td>
                    <td>${user.displayName}</td>
                </tr>
            </g:each>
        </tbody>
    </table>
</div>
</body>
</html>