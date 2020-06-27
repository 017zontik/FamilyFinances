<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Jekyll v4.0.1">
    <title>Groshiky</title>

    <link rel="canonical" href="https://getbootstrap.com/docs/4.5/examples/sign-in/">

    <!-- Bootstrap core CSS -->
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
<body >

<form:form method="post" action="index" modelAttribute="user" class="form-signin">
    <div class="text-center mb-4">
        <img class="mb-4" src="styles/brand/bootstrap-solid.svg" alt="" width="72" height="72">
        <h1 class="h3 mb-3 font-weight-normal">Please sing in</h1>
    </div>

    <c:if test="${registered}">
        <div class="alert alert-success" role="alert">
            You have successfully registered. Please log in.
        </div>
    </c:if>

    <div class="form-label-group">
        <form:input path="login" id="login" class="form-control" placeholder="Login" required="required"
                    autofocus=""/>
        <label for="login">Login</label>
    </div>
       <div class="form-label-group">
        <form:input path="password" type="password" id="password" class="form-control" placeholder="Password" required="required"
                    autofocus=""/>
        <label for="password" >Password</label>
    </div>

    <c:if test="${error}">
        <div class="alert alert-danger" role="alert" >
            Wrong login or password
        </div>
    </c:if>

    <div class="checkbox mb-3">
        <label>
            <a href="registration">registration</a>
        </label>
    </div>
    <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
    <p class="mt-5 mb-3 text-muted"></p>
</form:form>
</body>
</html>
