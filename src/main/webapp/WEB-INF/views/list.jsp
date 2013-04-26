<%-- 
    Document   : list
    Created on : Apr 8, 2013, 2:56:33 PM
    Author     : Daniel Lillqvist <daniel.lillqvist@helsinki.fi>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="fi" id="list.jsp">
    <head>
        <meta charset="UTF-8" />
        <title>Viitteet - kevätpäivä</title>
        <link rel="stylesheet" href="resources/css/main.css" />
        <link rel="shortcut icon" href="resources/images/favicon.ico" />
    </head>
    <body>
        <h1>Viitteet</h1>
        <ul class="navigaatio">
            <li><a href="lomake">Lisää uusi viite</a></li>
            <li><a href="haebibtex">Tarkastele viitteitä BibTex-muodossa</a></li>
        </ul>
        
        
        <!-- <ul>
            <c:forEach var="item" items="${artikkelit}">
                <li>
                    <a href="haebibtex/${item.id}">${item.id} | ${item.title} | ${item.author} | ${item.year}</a>
                    <form name="poisto/${item.id}" action="poista/${item.id}" method="POST">
                        <input type="submit" value="Poista" style="width: auto"/>
                    </form>
                </li>
            </c:forEach>
        </ul> -->
        
        <table>
            <c:forEach var="item" items="${artikkelit}">
                <tr>
                    <td style="text-align:center">${item.viiteTyyppi}</td>
                    <td><a href="haebibtex/${item.id}">[${item.id}] ${item.title}</a></td>
                    <td>${item.author}</td>
                    <td>${item.year}</td>
                    <td>
                        <form name="muutos/${item.id}" action="paivita/${item.id}" method="POST">
                            <input type="submit" value="Muuta" style="width: auto"/>
                        </form>
                    </td>
                    <td>
                        <form name="poisto/${item.id}" action="poista/${item.id}" method="POST">
                            <input type="submit" value="Poista" style="width: auto"/>
                        </form>
                    </td>
            </tr>
            </c:forEach>
        </table>
        
    </body>
</html>
