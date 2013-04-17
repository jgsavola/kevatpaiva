<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="fi">
    <head>
        <meta charset="UTF-8" />
        <title>${title} - kevätpäivä</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/main.css" />
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/images/favicon.ico" />
    </head>
    <body>
        <h1>Viitteet</h1>
        <ul id="navigation">
            <li><a href="${pageContext.request.contextPath}/haebibtex">Tarkastele viitteitä BibTex-muodossa</a></li>
            <li><a href="${pageContext.request.contextPath}/lomake">Lisää uusi viite</a></li>
        </ul>
        
        <ul>
        <c:forEach var="viite" items="${viiteLista}">
            <li><a href="haebibtex/${viite.id}">${viite.id} | ${viite.kentat}</a></li>
        </c:forEach>
        </ul>
    </body>
</html>