<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <title>Home</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<div id="container">
    <jsp:include page="header.jsp">
        <jsp:param name="page" value="Delete confirmation"/>
    </jsp:include>

    <main>
        Are you sure you want to delete your account?
        <div id="deleteConfirmationForms">
        <form method="post" action="Controller?action=DeletePerson">
            <input type="submit" id="yes" value="yes">
        </form>
        <form method="post" action="Controller">
            <input type="submit" id="no" value="no">
        </form>
        </div>
    </main>

    <footer>&copy; Webontwikkeling 3, UC Leuven-Limburg</footer>
</div>
</body>
</html>
