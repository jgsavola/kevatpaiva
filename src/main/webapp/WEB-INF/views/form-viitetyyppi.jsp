<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="fi">
    <head>
        <meta charset="UTF-8" />
        <title>Lisää lähdeviitteitä - Kevätpäivä</title>
        <link rel="stylesheet" href="resources/css/main.css" />
        <link rel="shortcut icon" href="resources/images/favicon.ico" />
        <script src="resources/scripts/lomakkeenvaihtaja.js"></script>
    </head>
    <body>
        <ul class="navigaatio">
            <li><a href="listaa">Tarkastele viitteitä</a></li>
            <li><a href="haebibtex">Tarkastele viitteitä BibTex-muodossa</a></li>
        </ul>

        <form id="viiteTyyppiLomake" action="lomake" method="GET" enctype="application/x-www-form-urlencoded">
            <fieldset>
                <legend>Valitse viitetyyppi</legend>
                <label for="viiteTyyppi">Tyyppi</label>
                <select name="viiteTyyppi" id="viiteTyyppi" size="3" onchange="vaihdaLomake();">
                    <option value="article" title="An article from a journal or magazine">Artikkeli</option>
                    <option value="book" title="A book with an explicit publisher">Kirja</option>
                    <option value="inproceedings" title="An article in a conference proceedings">Konferenssi</option>
                </select>
            </fieldset>
        </form>
    </body>
</html>