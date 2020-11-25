<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <meta charset="UTF-8">
    <title>Contact Overview</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<div id="container">
    <jsp:include page="header.jsp">
        <jsp:param name="page" value="Contact Overview"/>
    </jsp:include>
    <main>
        <c:choose>
            <c:when test="${empty contacts}">
                <p>The database is empty</p>
            </c:when>
            <c:otherwise>
                <table>
                    <tr>
                        <th>Date</th>
                        <th>Hour</th>
                        <th>Name</th>
                    </tr>
                    <c:forEach items="${contacts}" var="contact">
                        <tr>
                            <td>${contact.date}</td>
                            <td>${contact.hour}</td>
                            <td>${contact.firstName} ${contact.lastName}</td>
                        </tr>
                    </c:forEach>
                    <caption>Contact Overview</caption>
                </table>
            </c:otherwise>
        </c:choose>

        <c:if test="${!empty errors}">
            <div class="alert-danger">
                <ul>
                    <c:forEach items="${errors}" var="message">
                        <li>${message}</li>
                    </c:forEach>
                </ul>
            </div>
        </c:if>
        <form method="post" action="Controller?action=AddContact" novalidate="novalidate">
            <!-- novalidate in order to be able to run tests correctly -->
            <p><label for="firstName">First Name</label>
                <input class="${firstNameClass}" type="text" id="firstName" name="firstName" required
                       <c:if test="${!empty errors}">value="${firstNamePreviousValue}"</c:if>></p>
            <p><label for="lastName">Last Name</label>
                <input class="${lastNameClass}" type="text" id="lastName" name="lastName" required
                       <c:if test="${!empty errors}">value="${lastNamePreviousValue}"</c:if>></p>
            <p><label for="date">Date</label>
                <input class="${dateClass}" type="date" id="date" name="date" required
                       <c:if test="${!empty errors}">value="${datePreviousValue}"</c:if>></p>
            <p><label for="hour">Hour</label>
                <input class="${hourClass}" type="time" id="hour" name="hour" required
                       <c:if test="${!empty errors}">value="${hourPreviousValue}"</c:if>></p>
            <p><label for="gsm">GSM</label>
                <input class="${gsmClass}" type="tel" id="gsm" name="gsm" required
                       <c:if test="${!empty errors}">value="${gsmPreviousValue}"</c:if>></p>
            <p><label for="email">E-mail</label>
                <input class="${emailClass}" type="email" id="email" name="email" required
                       <c:if test="${!empty errors}">value="${emailPreviousValue}"</c:if>></p>
            <p><input type="submit" id="addContact" value="Add Contact"></p>
        </form>

    </main>
    <footer>
        &copy; Webontwikkeling 3, UC Leuven-Limburg
    </footer>
</div>
</body>
</html>
