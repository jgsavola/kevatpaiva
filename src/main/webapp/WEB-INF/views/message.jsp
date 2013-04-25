<%-- 
    Document   : message
    Created on : Apr 4, 2013, 12:39:06 AM
    Author     : Daniel Lillqvist <daniel.lillqvist@helsinki.fi>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="fi">
<head>
    <meta charset="UTF-8" />
    <title>${title} - kevätpäivä</title>
    <link rel="stylesheet" href="resources/css/main.css" />
    <link rel="shortcut icon" href="resources/images/favicon.ico" />
</head>
<body>
    <h1>${title}</h1>
    <p>${message}</p>
    <ul class="navigaatio">
        <li><a href="lomake">Lisää uusi viite</a></li>
        <li><a href="listaa">Tarkastele viitteitä</a></li>
        <li><a href="haebibtex">Tarkastele viitteitä BibTex-muodossa</a></li> 
    </ul>
</body>
</html>
