<%-- 
    Document   : start
    Created on : Apr 13, 2013, 12:56:40 PM
    Author     : heidi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <title>Kevätpäivä</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/main.css" />
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/images/favicon.ico" />
    </head>
    <body>
        
        <h1>Kevätpäivä</h1>
        <h2>BibTex-viitteiden hallintatyökalu</h2>
        
        <ul id="navigaatio">
            <li><a href="${pageContext.request.contextPath}/lomake">Lisää uusi viite</a></li>
            <li><a href="${pageContext.request.contextPath}/listaa">Tarkastele viitteitä</a></li>
            <li><a href="${pageContext.request.contextPath}/haebibtex">Tarkastele viitteitä BibTex-muodossa</a></li>
        </ul>
        
    </body>
</html>
