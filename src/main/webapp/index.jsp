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
        <jsp:param name="page" value="Home"/>
    </jsp:include>
    <main>
        <p>Sed ut perspiciatis unde omnis iste natus error sit
            voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque
            ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae
            dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit
            aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos
            qui ratione voluptatem sequi nesciunt.</p>

        <c:if test="${error != null}">
            <div class="alert-danger">
                <p>${error}</p>
            </div>
        </c:if>
        <c:choose>
            <c:when test="${empty sessionScope}">
                <h2>Please log in.</h2>
                <form method="post" action="Controller?action=LogIn" novalidate="novalidate">
                    <p>
                        <label for="userid">Your userid</label>
                        <input type="text" id="userid" name="userid" required>
                    </p>
                    <p>
                        <label for="password">Your password</label>
                        <input type="password" id="password" name="password" required>
                    </p>
                    <p><input type="submit" id="logIn" value="Log in"></p>
                </form>
            </c:when>
            <c:otherwise>
                <h2>Welcome ${sessionScope.person.firstName}!</h2>
                <form method="post" action="delete.jsp" novalidate="novalidate">
                    <p><input type="submit" id="remove" value="Remove account"></p>
                </form>
                <form method="post" action="Controller?action=LogOut" novalidate="novalidate">
                    <p><input type="submit" id="logOut" value="Log out"></p>
                </form>
            </c:otherwise>
        </c:choose>

    </main>
    <footer>&copy; Webontwikkeling 3, UC Leuven-Limburg</footer>
</div>
</body>
</html>
