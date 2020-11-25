<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <meta charset="UTF-8">
    <title>Search</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<div id="container">
    <jsp:include page="header.jsp">
        <jsp:param name="page" value="Search"/>
    </jsp:include>
    <main>
        <form method="post" action="Controller?action=Find" novalidate="novalidate">
            <p><label for="searchString" hidden="hidden">Search</label>
                <input type="text" id="searchString" name="searchString" required value="${searchStringPreviousValue}"></p>
            <p><input type="submit" id="search" value="Search"></p>
        </form>

        <c:choose>
            <c:when test="${!empty contacts and contacts.size() == 0}">
                <p>No results were found.</p>
            </c:when>
            <c:when test="${!empty contacts}">
                <table>
                    <tr>
                        <th>Name</th>
                        <th>Email</th>
                        <th>Gsm</th>
                    </tr>
                    <c:forEach items="${contacts}" var="contact">
                        <tr>
                            <td>${contact.firstName} ${contact.lastName}</td>
                            <td>${contact.email}</td>
                            <td>${contact.gsm}</td>
                        </tr>
                    </c:forEach>
                    <caption>Search Results</caption>
                </table>
            </c:when>
        </c:choose>

    </main>
    <footer>
        &copy; Webontwikkeling 3, UC Leuven-Limburg
    </footer>
</div>
</body>
</html>
