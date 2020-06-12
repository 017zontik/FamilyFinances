<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 017zo
  Date: 03.06.2020
  Time: 12:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Jekyll v4.0.1">
    <title>Registration</title>

    <link href="styles/css/bootstrap.css" type="text/css" rel="stylesheet">

    <style>
        .bd-placeholder-img {
            font-size: 1.125rem;
            text-anchor: middle;
            -webkit-user-select: none;
            -moz-user-select: none;
            -ms-user-select: none;
            user-select: none;
        }

        @media (min-width: 768px) {
            .bd-placeholder-img-lg {
                font-size: 3.5rem;
            }
        }
    </style>
    <link href="styles/css/floating-labels.css" type="text/css" rel="stylesheet">
</head>

<body>
<form:form method="post" action="registration" modelAttribute="user" class="form-signin">
    <div class="text-center mb-4">
        <img class="mb-4" src="styles/brand/bootstrap-solid.svg" alt="" width="72" height="72">
        <h1 class="h3 mb-3 font-weight-normal">Register</h1>
    </div>
    <div class="form-label-group">
        <form:input path="username" id="username" class="form-control" placeholder="User name" required="required"
                    autofocus=""/>
        <label for="username">User name</label>
    </div>
    <div  class="form-label-group">
        <form:input path="login" id="login" class="form-control ${error!=null ? 'is-invalid':''}" placeholder="Login name" required="required"
                    autofocus="" />
        <label for="login">Login name</label>
    </div>
    <c:if test="${error != null}">
        <div class="alert alert-danger" role="alert">
                ${error}
        </div>
    </c:if>
    <div class="form-label-group">
        <form:input path="password" type="password" id="password" class="form-control" placeholder="Password" required="required"
                    autofocus=""/>
        <label for="password" >Password</label>
    </div>
    <button class="btn btn-lg btn-primary btn-block" type="submit">Register</button>
    <p class="mt-5 mb-3 text-muted"></p>
</form:form>
</body>
</html>
