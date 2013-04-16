<%-- 
    Document   : list
    Created on : Apr 8, 2013, 2:56:33 PM
    Author     : Daniel Lillqvist <daniel.lillqvist@helsinki.fi>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="fi">
    <head>
        <meta charset="UTF-8" />
        <title>${title} - kevätpäivä</title>
        <link rel="stylesheet" href="resources/css/main.css" />
        <link rel="shortcut icon" href="resources/images/favicon.ico" />
    </head>
    <body>
        <h1>Artikkeliviitteet</h1>
        <ul style="list-style: none">
            <li><a href="haebibtex">Tarkastele viitteitä BibTex-muodossa</a></li>
            <li><a href="lomake">Lisää uusi viite</a></li>
        </ul>
        <!-- <ul>
            <c:forEach var="item" items="${artikkelit}">
                <li>${item.id} | ${item.title} | ${item.author} | ${item.year}</li>
            </c:forEach>
        </ul> -->
        
        <ul>
            <c:forEach var="item" items="${artikkelit}">
                <li><a href="haebibtex/${item.id}">${item.id} | ${item.title} | ${item.author} | ${item.year}</a></li>
            </c:forEach>
        </ul>
        
    </body>
</html>