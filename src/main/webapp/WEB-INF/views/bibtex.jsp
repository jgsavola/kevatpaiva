<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="fi">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <title>BibTeX - Kevätpäivä</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/main.css" />
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/images/favicon.ico" />
    </head>
    <body>
        
        <ul id="navigaatio">
            <li><a href="${pageContext.request.contextPath}">S</a></li>
            <li><a href="${pageContext.request.contextPath}/lomake">Lisää uusi viite</a></li>
            <li><a href="${pageContext.request.contextPath}/listaa">Tarkastele viitteitä</a></li>
            
        </ul>
        
        <h1>BibTeX</h1>
        <p>Viitteet BibTeX-muodossa:</p>
        <pre><c:forEach var="item" items="${bibit}">${item}</c:forEach></pre>
        
    </body>
</html>
