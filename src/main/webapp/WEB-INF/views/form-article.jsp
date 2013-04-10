<%-- 
    Document   : helloworld
    Created on : 25-Mar-2013, 11:05:19
    Author     : danielli
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="fi">
<head>
    <meta charset="UTF-8" />
    <title>Lisää lähdeviitteitä - Kevätpäivä</title>
    <link rel="stylesheet" href="resources/css/main.css" />
    <link rel="shortcut icon" href="resources/images/favicon.ico" />
</head>
<body>

    <form action="" method="POST" enctype="application/x-www-form-urlencoded">
<fieldset>
<legend>Lisää lähdeviite</legend>

    <div>${message}</div>

    <label for="type">Tyyppi</label>
	<select name="type" size="3">
            <option value="article" title="An article from a journal or magazine">Artikkeli</option>
            <option value="book" disabled="disabled" title="A book with an explicit publisher">Kirja</option>
            <option value="inproceedings" disabled="disabled" title="An article in a conference proceedings">Konferenssi</option>
	</select>

    <label for="id">ID</label>
    <input name="id" id="id" type="text" />    
    
    <label for="author">Kirjoittaja</label>
    <p class="help" title="Recognized structures:
    [First von Last] or 
    [von Last, First] or 
    [von Last, Jr ,First]">(help)</p>
    <input name="author" id="author" type="text" />
    
    <label for="title">Otsikko</label>
    <input name="title" id="title" type="text" />
    
    <label for="journal">Journaali</label>
    <input name="journal" id="journal" type="text" />
    
    <label for="year">Vuosi</label>
	<input name="year" id="year" type="text" />
    
    <label for="publisher">Kustantaja</label>
	<input name="publisher" id="publisher" type="text" />
        
    <label for="address">Osoite</label>
	<input name="address" id="address" type="text" />

	<p>Valinnaiset</p>

    <label for="volume">Nidos/Määrä</label>
	<input name="volume" id="volume" type="text" />
    
    <label for="number">Numero</label>
	<input name="number" id="number" type="text" />
    
    <label for="pages">Sivut</label>
	<input name="pages" id="pages" type="text" />
    
    <label for="month">Kuukausi</label>
	<input name="month" id="month" type="text" />
    
    <label for="note">Kommentti</label>
	<input name="note" id="note" type="text" />
    
    <label for="key">Key</label>
	<input name="key" id="key" type="text" />

	<input name="form-submit" id="form-submit" class="button" type="submit" value="Lähetä" />
</fieldset>
</form>
    <p>
        Katso lisättyjä artikkeleita <a href="haebibtex">BibTeX</a> muodossa
    </p>
</body>
</html>