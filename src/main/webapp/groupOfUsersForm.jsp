<%--
  Created by IntelliJ IDEA.
  User: Егор
  Date: 10.04.2022
  Time: 14:38
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
            <li><a href="<%=request.getContextPath()%>/subscriptin" class="btn btn-outline-dark mr-2">Subscription</a>
            </li>
        </ul>
    </nav>
</header>
<br>
<div class="container col-md-5">
    <div class="card">
        <div class="card-body">
                        <form action="insertGroupOfUsers" method="post">

                    <caption>
                        <h2>
                            Add new group subscription
                        </h2>
                    </caption>

                    <fieldset class="form-post">
                        <tr>
                            <td>userId</td>
                            <td>
                        <tr>
                            <select class="form-control" name="userId">
                                <c:forEach var="user" items="${users}">
                                    <option>ID: ${user.userId} Login: ${user.login}</option>
                                </c:forEach>
                            </select>
                    </fieldset>

                    <fieldset class="form-post">
                        <tr>
                            <td>groupId</td>
                            <td>
                        <tr>
                            <select class="form-control" name="groupId">
                                <c:forEach var="group" items="${groups}">
                                    <option>ID: ${group.groupId} Name: ${group.groupName}</option>
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
