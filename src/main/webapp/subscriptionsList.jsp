<%@ page import="com.example.ServletApp.exception.ErrorObj" %><%--
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

<%
    ErrorObj errorObj = (ErrorObj) request.getAttribute("error");
    if(errorObj.isIsAble()){
        out.println("<script type=\"text/javascript\">\n" +
                "        alert(\""+errorObj.getMessange() +"\");\n" +
                "    </script>");
    }
%>

<header>
    <nav class="navbar navbar-expand-md navbar-dark" style="background-color: #aaa">
        <div>
            <h3 class=" p-2"> Servlet APP </h3>
        </div>

        <ul class="navbar-nav">
            <li><a href="<%=request.getContextPath()%>/user" class="btn btn-outline-dark mr-2">Users</a></li>
            <li><a href="<%=request.getContextPath()%>/group" class="btn btn-outline-dark mr-2">Groups</a></li>
            <li><a href="<%=request.getContextPath()%>/post" class="btn btn-outline-dark mr-2">Post</a></li>
            <li><a href="<%=request.getContextPath()%>/groupOfUsers" class="btn btn-outline-dark mr-2">Group of
                users</a></li>
            <li><a href="<%=request.getContextPath()%>/subscriptions" class="btn btn-outline-dark mr-2">Subscription</a>
            </li>
        </ul>
    </nav>
</header>
<br>

<div class="row">
    <!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

    <div class="container">
        <h3 class="text-center">List of subscriptions</h3>
        <hr>
        <div class="container text-left">

            <a href="<%=request.getContextPath()%>/newSubscriptions" class="btn btn-success">Add
                New group</a>
        </div>
        <br>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>User</th>
                <th>Subscriber</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="subscriptions" items="${listSubscriptions}">

                <tr>
                    <td>
                        <c:out value="${subscriptions.user.nickName}"/>
                    </td>
                    <td>
                        <c:out value="${subscriptions.subscriber.nickName}"/>
                    </td>
                    <td>
                            <%--                        <a href="editGroup?groupId=<c:out value='${group.groupId}'  />" class="btn btn-success" >Edit</a>--%>
                            <%--                        &nbsp;&nbsp;&nbsp;&nbsp;--%>
                        <a href="deleteSubscriptions?userId=<c:out value='${subscriptions.user.userId}'/>&subscriberId=<c:out value='${subscriptions.subscriber.userId}'/>"
                           class="btn btn-outline-danger">Delete</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>

</html>
