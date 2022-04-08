<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>

<head>
    <title>User Management Application</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>

<body>

<header>
    <nav class="navbar navbar-expand-md navbar-dark" style="background-color: #aaa">
        <div>
            <h3 class=" p-2"> Servlet APP </h3>
        </div>

        <ul class="navbar-nav">
            <li><a href="<%=request.getContextPath()%>/user" class="btn btn-outline-dark">Users</a></li>
        </ul>
    </nav>
</header>
<br>

<div class="row">
    <!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

    <div class="container">
        <h3 class="text-center">List of Users</h3>
        <hr>
        <div class="container text-left">

            <a href="<%=request.getContextPath()%>/new" class="btn btn-success">Add
                New User</a>
        </div>
        <br>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>ID</th>
                <th>Login</th>
                <th>Password</th>
                <th>Email</th>
                <th>Nickname</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="user" items="${listUser}">

                <tr>
                    <td>
                        <c:out value="${user.userId}" />
                    </td>
                    <td>
                        <c:out value="${user.login}" />
                    </td>
                    <td>
                        <c:out value="${user.password}" />
                    </td>
                    <td>
                        <c:out value="${user.email}" />
                    </td>
                    <td>
                        <c:out value="${user.nickName}" />
                    </td>
                    <td>
                        <a href="edit?userId=<c:out value='${user.userId}'  />" class="btn btn-success" >Edit</a>
<%--                        &nbsp;&nbsp;&nbsp;&nbsp;--%>
                        <a href="delete?userId=<c:out value='${user.userId}' />  " class="btn btn-outline-danger" >Delete</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>

</html>