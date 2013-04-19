<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="fi">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <title>${otsikko} - Kevätpäivä</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/main.css" />
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/images/favicon.ico" />
    </head>
    <body>
        
        <ul id="navigaatio">
            <li><a href="${pageContext.request.contextPath}">S</a></li>
            <li><a href="${pageContext.request.contextPath}/lomake">Lisää uusi viite</a></li>
            <li><a href="${pageContext.request.contextPath}/listaa">Tarkastele viitteitä</a></li>
            <li><a href="${pageContext.request.contextPath}/haebibtex">Tarkastele viitteitä BibTex-muodossa</a></li>
        </ul>
        
        <h1>${otsikko}</h1>
        <p>${viesti}</p>
        
    </body>
</html>