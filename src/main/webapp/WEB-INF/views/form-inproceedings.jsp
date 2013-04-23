<%@page contentType="text/html" pageEncoding="UTF-8"%>
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

        <form action="lisaa" method="POST" enctype="application/x-www-form-urlencoded">
            <fieldset>
                <legend>Lisää lähdeviite</legend>

                <label for="type">Tyyppi</label>
                <select name="type" id="type" size="3" onchange="vaihdaLomake();">
                    <option value="article" title="An article from a journal or magazine">Artikkeli</option>
                    <option value="book" title="A book with an explicit publisher">Kirja</option>
                    <option value="inproceedings" title="An article in a conference proceedings">Konferenssi</option>
                </select>

                <p class="virhe">${message}</p>

                <label for="id">ID</label>
                <input name="id" id="id" type="text" value="${id}"/>    

                <label for="author">Kirjoittaja (author)</label>
                <input name="author" id="author" type="text" value="${author}"/>

                <label for="booktitle">Nimike (booktitle)</label>
                <input name="booktitle" id="booktitle" type="text" value="${booktitle}"/>

                <label for="title">Otsikko (title)</label>
                <input name="title" id="title" type="text" value="${title}"/>

                <label for="year">Vuosi (year)</label>
                <input name="year" id="year" type="text" value="${year}"/>

                <input name="form-submit" id="form-submit" class="button" type="submit" value="Lähetä" />

                <p>Valinnaiset</p>

                <label for="address">Osoite (address)</label>
                <input name="address" id="address" type="text" value="${address}"/>

                <label for="editor">Editor (editor)</label>
                <input name="editor" id="editor" type="text" value="${editor}"/>

                <label for="key">Avain (key)</label>
                <input name="key" id="key" type="text" value="${key}"/>

                <label for="month">Kuukausi (month)</label>
                <input name="month" id="month" type="text" value="${month}"/>

                <label for="note">Kommentti (note)</label>
                <input name="note" id="note" type="text" value="${note}"/>

                <label for="number">Numero (number)</label>
                <input name="number" id="number" type="text" value="${number}"/>                

                <label for="organization">Organisaatio (organization)</label>
                <input name="organization" id="organization" type="text" value="${organization}"/>

                <label for="pages">Sivut (pages)</label>
                <input name="pages" id="pages" type="text" value="${pages}"/>

                <label for="publisher">Kustantaja (publisher)</label>
                <input name="publisher" id="publisher" type="text" value="${publisher}"/>

                <label for="series">Sarja (series)</label>
                <input name="series" id="series" type="text" value="${series}"/>

                <label for="volume">Nidos/Määrä</label>
                <input name="volume" id="volume" type="text" value="${volume}"/>
            </fieldset>
        </form>

    </body>
</html>