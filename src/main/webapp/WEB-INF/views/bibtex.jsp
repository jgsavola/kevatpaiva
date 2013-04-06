<%-- 
    Document   : bibtex
    Created on : Apr 6, 2013, 10:48:43 AM
    Author     : heidi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Kevätpäivä</title>
    </head>
    <body>
        <h1>BibTeX</h1>
        <p>Artikkelit BibTeX-muodossa:</p>
        <ol>
            <c:forEach var="item" items="${bibit}">
                <li>${item}</li>
            </c:forEach>
        </ol>
    </body>
</html>
