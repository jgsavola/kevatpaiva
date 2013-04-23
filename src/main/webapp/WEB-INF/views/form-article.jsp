<%-- 
    Document   : helloworld
    Created on : 25-Mar-2013, 11:05:19
    Author     : danielli
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="fi">
<head>
    <meta charset="UTF-8" />
    <title>Lisää lähdeviitteitä - Kevätpäivä</title>
    <link rel="stylesheet" href="resources/css/main.css" />
    <link rel="shortcut icon" href="resources/images/favicon.ico" />
</head>
<body>
    <ul style="list-style: none">
            <li><a href="listaa">Tarkastele viitteitä</a></li>
            <li><a href="haebibtex">Tarkastele viitteitä BibTex-muodossa</a></li>
        </ul>
    <pre><c:forEach var="item" items="${messages}">${item}
</c:forEach></pre>
    <form action="lisaa" method="POST" enctype="application/x-www-form-urlencoded">
<fieldset>
<legend>Lisää lähdeviite</legend>

    

    <label for="type">Tyyppi</label>
	<select name="type" id="type" size="3">
            <option value="article" title="An article from a journal or magazine">Artikkeli</option>
            <option value="book" disabled="disabled" title="A book with an explicit publisher">Kirja</option>
            <option value="inproceedings" disabled="disabled" title="An article in a conference proceedings">Konferenssi</option>
	</select>

    <label for="id">ID</label>
    <input name="id" id="id" type="text" value="${id}"/>    
    
    <label for="author">Kirjoittaja</label>
    <p class="help" title="Recognized structures:
    [First von Last] or 
    [von Last, First] or 
    [von Last, Jr ,First]">(help)</p>
    <input name="author" id="author" type="text" value="${author}"/>
    
    <label for="title">Otsikko</label>
    <input name="title" id="title" type="text" value="${title}"/>
    
    <label for="journal">Journaali</label>
    <input name="journal" id="journal" type="text" value="${journal}"/>
    
    <label for="year">Vuosi</label>
	<input name="year" id="year" type="text" value="${year}"/>
    
    <label for="publisher">Kustantaja</label>
	<input name="publisher" id="publisher" type="text" value="${publisher}"/>
        
    <label for="address">Osoite</label>
	<input name="address" id="address" type="text" value="${address}"/>

	<p>Valinnaiset</p>

    <label for="volume">Nidos/Määrä</label>
	<input name="volume" id="volume" type="text" value="${volume}"/>
    
    <label for="number">Numero</label>
	<input name="number" id="number" type="text" value="${number}"/>
    
    <label for="pages">Sivut</label>
	<input name="pages" id="pages" type="text" value="${pages}"/>
    
    <label for="month">Kuukausi</label>
	<input name="month" id="month" type="text" value="${month}"/>
    
    <label for="note">Kommentti</label>
	<input name="note" id="note" type="text" value="${note}"/>
    
    <label for="key">Key</label>
	<input name="key" id="key" type="text" value="${key}"/>

	<input name="form-submit" id="form-submit" class="button" type="submit" value="Lähetä" />
</fieldset>
</form>

</body>
</html>