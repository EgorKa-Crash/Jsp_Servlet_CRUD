<%--
  Created by IntelliJ IDEA.
  User: Егор
  Date: 13.04.2022
  Time: 14:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>

<head>
    <title>User Management Application</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>

<body>

<header>
    <nav class="navbar navbar-expand-md navbar-dark" style="background-color: #aaa">
        <div>
            <h3 class="p-2"> Servlet APP </h3>
        </div>

        <ul class="navbar-nav">
            <li><a href="<%=request.getContextPath()%>/user" class="btn btn-outline-dark mr-2">Users</a></li>
            <li><a href="<%=request.getContextPath()%>/group" class="btn btn-outline-dark mr-2">Groups</a></li>
            <li><a href="<%=request.getContextPath()%>/post" class="btn btn-outline-dark mr-2">Post</a></li>
            <li><a href="<%=request.getContextPath()%>/groupOfUsers" class="btn btn-outline-dark mr-2">Group of
                users</a>
            </li>
            <li><a href="<%=request.getContextPath()%>/subscriptions" class="btn btn-outline-dark mr-2">Subscription</a>
            </li>
        </ul>
    </nav>
</header>
<br>
<div class="container col-md-5">
    <div class="card">
        <div class="card-body">
            <form action="insertSubscriptions" method="post">

                <caption>
                    <h2>
                        Add new subscription
                    </h2>
                </caption>

                <fieldset class="form-post">
                    <tr>
                        <td>user</td>
                        <td>
                    <tr>
                        <select class="form-control" name="userId">
                            <c:forEach var="user" items="${users}">
                                <option>ID: ${user.userId} Login: ${user.nickName}</option>
                            </c:forEach>
                        </select>
                </fieldset>

                <fieldset class="form-post">
                    <tr>
                        <td>subscriber</td>
                        <td>
                    <tr>
                        <select class="form-control" name="subscriberId">
                            <c:forEach var="subscriber" items="${subscribers}">
                                <option>ID: ${subscriber.userId} Login: ${subscriber.nickName}</option>
                            </c:forEach>
                        </select>
                </fieldset>

                <button type="submit" class="btn btn-success mt-3">Save</button>
            </form>
        </div>
    </div>
</div>
</body>

</html>