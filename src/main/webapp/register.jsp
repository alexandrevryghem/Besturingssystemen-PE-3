<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <meta charset="UTF-8">
    <title>Sign Up</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<div id="container">
    <jsp:include page="header.jsp">
        <jsp:param name="page" value="Sign up"/>
    </jsp:include>
    <main>
        <c:if test="${!empty errors}">
            <div class="alert-danger">
                <ul>
                    <c:forEach items="${errors}" var="message">
                        <li>${message}</li>
                    </c:forEach>
                </ul>
            </div>
        </c:if>
        <form method="post" action="Controller?action=SignUp" novalidate="novalidate">
            <!-- novalidate in order to be able to run tests correctly -->
            <p><label for="userid">Userid</label>
                <input class="${useridClass}" type="text" id="userid" name="userid" required
                       value="${useridPreviousValue}"></p>
            <p><label for="firstName">First Name</label>
                <input class="${firstNameClass}" type="text" id="firstName" name="firstName" required
                       value="${firstNamePreviousValue}"></p>
            <p><label for="lastName">Last Name</label>
                <input class="${lastNameClass}" type="text" id="lastName" name="lastName" required
                       value="${lastNamePreviousValue}"></p>
            <p><label for="email">E-mail</label>
                <input class="${emailClass}" type="email" id="email" name="email" required
                       value="${emailPreviousValue}"></p>
            <p><label for="password">Password</label>
                <input class="${passwordClass}" type="password" id="password" name="password" required
                       value="${passwordPreviousValue}"></p>
            <p><input type="submit" id="signUp" value="Sign Up"></p>
        </form>
    </main>
    <footer>
        &copy; Webontwikkeling 3, UC Leuven-Limburg
    </footer>
</div>
</body>
</html>
