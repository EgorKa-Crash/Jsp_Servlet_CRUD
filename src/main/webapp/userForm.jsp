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
            <h3 class=" p-2"> Servlet APP </h3>
        </div>

        <ul class="navbar-nav">
            <li><a href="<%=request.getContextPath()%>/user" class="btn btn-outline-dark mr-2">Users</a></li>
            <li><a href="<%=request.getContextPath()%>/group" class="btn btn-outline-dark mr-2">Groups</a></li>
            <li><a href="<%=request.getContextPath()%>/post" class="btn btn-outline-dark mr-2">Post</a></li>
            <li><a href="<%=request.getContextPath()%>/groupOfUsers" class="btn btn-outline-dark mr-2">Group of users</a></li>
            <li><a href="<%=request.getContextPath()%>/subscriptions" class="btn btn-outline-dark mr-2">Subscription</a></li>
        </ul>
    </nav>
</header>
<br>
<div class="container col-md-5">
    <div class="card">
        <div class="card-body">
            <c:if test="${user != null}">
            <form action="update" method="post">
                </c:if>
                <c:if test="${user == null}">
                <form action="insert" method="post">
                    </c:if>

                    <caption>
                        <h2>
                            <c:if test="${user != null}">
                                Edit User
                            </c:if>
                            <c:if test="${user == null}">
                                Add New User
                            </c:if>
                        </h2>
                    </caption>

                    <c:if test="${user != null}">
                        <input type="hidden" name="userId" value="<c:out value='${user.userId}' />"/>
                    </c:if>

                    <fieldset class="form-group">
                        <label>login</label> <input type="text" value="<c:out value='${user.login}' />"
                                                        class="form-control" name="login" required="required">
                    </fieldset>

                    <fieldset class="form-group">
                        <label>password</label> <input type="text" value="<c:out value='${user.password}' />"
                                                         class="form-control" name="password" required="required">
                    </fieldset>

                    <fieldset class="form-group">
                        <label>email</label> <input type="text" value="<c:out value='${user.email}' />"
                                                           class="form-control" name="email" required="required">
                    </fieldset>

                    <fieldset class="form-group">
                        <label>nickname</label> <input type="text" value="<c:out value='${user.nickName}' />"
                                                           class="form-control" name="nickName"  required="required">
                    </fieldset>
                    <button type="submit" class="btn btn-success">Save</button>
                </form>
        </div>
    </div>
</div>
</body>

</html>