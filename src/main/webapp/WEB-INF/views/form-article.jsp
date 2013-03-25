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
    <!--<link rel="stylesheet" href="main.css" />-->
    <style type="text/css">
        form {
                width: 30%; 
        }
        label {
                display:block;
        }
        select, input {
                width:90%;
                margin-bottom:0.6em;
                padding:3px;
        }
        input.button {
                margin:0;
        }
        p.help {
                display:inline;
        }        
        
    </style>
    
</head>
<body>

<form action="" method="POST">
<fieldset>
<legend>Lisää lähdeviite</legend>
	
    <label for="entry-type">Tyyppi</label>
	<select name="entry-type" size="3">
    	<option value="article" title="An article from a journal or magazine">Artikkeli</option>
        <option value="book" disabled="disabled" title="A book with an explicit publisher">Kirja</option>
        <option value="inproceedings" disabled="disabled" title="An article in a conference proceedings">Konferenssi</option>
	</select>

    <label for="entry-id">ID</label>
    <input name="entry-id" id="entry-id" type="text" />    
    
    <label for="entry-author">Kirjoittaja <p class="help" title="Recognized structures:
    [First von Last] or 
    [von Last, First] or 
    [von Last, Jr ,First]">(help)</p></label>
    <input name="entry-author" id="entry-author" type="text" />


    
    <label for="entry-title">Otsikko</label>
    <input name="entry-title" id="entry-title" type="text" />
    
    <label for="entry-journal">Journaali</label>
    <input name="entry-journal" id="entry-journal" type="text" />
    
    <label for="entry-year">Vuosi</label>
	<input name="entry-year" id="entry-year" type="text" />

	<p>Valinnaiset</p>

    <label for="entry-volume">Nidos/Määrä</label>
	<input name="entry-volume" id="entry-volume" type="text" />
    
    <label for="entry-number">Numero</label>
	<input name="entry-number" id="entry-number" type="text" />
    
    <label for="entry-pages">Sivut</label>
	<input name="entry-pages" id="entry-pages" type="text" />
    
    <label for="entry-month">Kuukausi</label>
	<input name="entry-month" id="entry-month" type="text" />
    
    <label for="entry-note">Kommentti</label>
	<input name="entry-note" id="entry-note" type="text" />
    
    <label for="entry-key">Key</label>
	<input name="entry-key" id="entry-key" type="text" />

	<input name="form-submit" id="form-submit" class="button" type="submit" value="Lähetä" />
</fieldset>
</form>

</body>
</html>