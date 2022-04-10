<%--
  Created by IntelliJ IDEA.
  User: Егор
  Date: 09.04.2022
  Time: 13:23
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
            <li><a href="<%=request.getContextPath()%>/groupofusers" class="btn btn-outline-dark mr-2">Group of
                users</a></li>
            <li><a href="<%=request.getContextPath()%>/subscriptin" class="btn btn-outline-dark mr-2">Subscription</a>
            </li>
        </ul>
    </nav>
</header>
<br>
<div class="container col-md-5">
    <div class="card">
        <div class="card-body">
            <c:if test="${group != null}">
            <form action="updateGroup" method="post">
                </c:if>
                <c:if test="${group == null}">
                <form action="insertGroup" method="post">
                    </c:if>

                    <caption>
                        <h2>
                            <c:if test="${group != null}">
                                Edit group
                            </c:if>
                            <c:if test="${group == null}">
                                Add new group
                            </c:if>
                        </h2>
                    </caption>

                    <c:if test="${group != null}">
                        <input type="hidden" name="groupId" value="<c:out value='${group.groupId}' />"/>
                    </c:if>

                    <fieldset class="form-group">
                        <label>name</label> <input type="text" value="<c:out value='${group.groupName}' />"
                                                   class="form-control" name="groupName" required="required">
                    </fieldset>

                    <fieldset class="form-group">
                        <label>comment</label> <input type="text" value="<c:out value='${group.groupComment}' />"
                                                      class="form-control" name="groupComment">
                    </fieldset>

                    <fieldset class="form-group" >
                        <input type="hidden" value="<c:out value='${group.creatingDate}' />"
                                                      class="form-control" name="creatingDate">
                    </fieldset>

                    <button type="submit" class="btn btn-success">Save</button>
                </form>
        </div>
    </div>
</div>
</body>

</html>
