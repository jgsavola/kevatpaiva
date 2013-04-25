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
                <select name="viiteTyyppi" id="viiteTyyppi" size="3" onchange="vaihdaLomake();">
                    <option value="article" title="An article from a journal or magazine">Artikkeli</option>
                    <option selected="selected" class="selected" value="book" title="A book with an explicit publisher">Kirja</option>
                    <option value="inproceedings" title="An article in a conference proceedings">Konferenssi</option>
                </select>
            </fieldset>
        </form>
                
        <form id="viiteLomake" class="book" action="lisaa" method="POST" enctype="application/x-www-form-urlencoded">
            <fieldset>
                <legend>Lisää kirjaviite</legend>
                
                <c:if test="${!empty messages}">
                <ul class="virheet">
                <c:forEach var="item" items="${messages}">
                    <li>${item}</li>
                </c:forEach>
                </ul>
                </c:if>

                <input name="viiteTyyppi" value="book" type="hidden" />

                <label for="id">ID</label>
                <input name="id" id="id" type="text" value="${id}"/>    

                <label for="author">Kirjoittaja (author)</label>
                <input name="author" id="author" type="text" value="${author}"/>

                <label for="editor">Editor (editor)</label>
                <input name="editor" id="editor" type="text" value="${editor}"/>
                
                <label for="publisher">Kustantaja (publisher)</label>
                <input name="publisher" id="publisher" type="text" value="${publisher}"/>
                
                <label for="title">Otsikko (title)</label>
                <input name="title" id="title" type="text" value="${title}"/>

                <label for="year">Vuosi (year)</label>
                <input name="year" id="year" type="text" value="${year}"/>

                <input name="form-submit" id="form-submit" class="button" type="submit" value="Lähetä" />
                
                <p>Valinnaiset</p>

                <label for="address">Osoite (address)</label>
                <input name="address" id="address" type="text" value="${address}"/>
                
                <label for="edition">Edition (edition)</label>
                <input name="edition" id="edition" type="text" value="${edition}"/>  
                
                <label for="key">Avain (key)</label>
                <input name="key" id="key" type="text" value="${key}"/>
                
                <label for="month">Kuukausi (month)</label>
                <input name="month" id="month" type="text" value="${month}"/>

                <label for="note">Kommentti (note)</label>
                <input name="note" id="note" type="text" value="${note}"/>

                <label for="number">Numero (number)</label>
                <input name="number" id="number" type="text" value="${number}"/>

                <label for="series">Sarja (series)</label>
                <input name="series" id="series" type="text" value="${series}"/>
                
                <label for="volume">Nidos/Määrä (volume)</label>
                <input name="volume" id="volume" type="text" value="${volume}"/>

            </fieldset>
        </form>

    </body>
</html>