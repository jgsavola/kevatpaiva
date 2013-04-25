<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="fi" id="form-inproceedings.jsp">
    <head>
        <meta charset="UTF-8" />
        <title>Lisää lähdeviitteitä - Kevätpäivä</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/main.css" />
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/images/favicon.ico" />
        <script src="${pageContext.request.contextPath}/resources/scripts/lomakkeenvaihtaja.js"></script>
    </head>
    <body>
        <ul class="navigaatio">
            <li><a href="${pageContext.request.contextPath}/listaa">Tarkastele viitteitä</a></li>
            <li><a href="${pageContext.request.contextPath}/haebibtex">Tarkastele viitteitä BibTex-muodossa</a></li>
        </ul>

        <form id="viiteTyyppiLomake" action="lomake" method="GET" enctype="application/x-www-form-urlencoded">
            <fieldset>
                <legend>Valitse viitetyyppi</legend>
                <select name="viiteTyyppi" id="viiteTyyppi" size="3" onchange="vaihdaLomake();">
                    <option value="article" title="An article from a journal or magazine">Artikkeli</option>
                    <option value="book" title="A book with an explicit publisher">Kirja</option>
                    <option selected="selected" class="selected" value="inproceedings" title="An article in a conference proceedings">Konferenssi</option>
                </select>
            </fieldset>
        </form>

        <form id="viiteLomake" class="inproceedings" action="${pageContext.request.contextPath}/lisaa" method="POST" enctype="application/x-www-form-urlencoded">
            <fieldset>
                <legend>
                    <c:choose>
                        <c:when test="${paivitysMoodi}">Päivitä konferenssijulkaisuviite</c:when>
                        <c:otherwise>Lisää konferenssijulkaisuviite</c:otherwise>
                    </c:choose>
                </legend>

                <c:if test="${!empty messages}">
                    <ul class="virheet">
                        <c:forEach var="item" items="${messages}">
                            <li>${item}</li>
                            </c:forEach>
                    </ul>
                </c:if>

                <input name="viiteTyyppi" value="inproceedings" type="hidden" />

                <label for="id">ID</label>
                <c:choose>
                    <c:when test="${paivitysMoodi}">
                        <input type="text" value="${id}" disabled="disabled" />
                        <input name="id" id="id" type="hidden" value="${id}" />
                    </c:when>
                    <c:otherwise><input name="id" id="id" type="text" value="${id}"/></c:otherwise>
                </c:choose>  

                <label for="author">Kirjoittaja (author)</label>
                <input name="author" id="author" type="text" value="${author}"/>

                <label for="booktitle">Nimike (booktitle)</label>
                <input name="booktitle" id="booktitle" type="text" value="${booktitle}"/>

                <label for="title">Otsikko (title)</label>
                <input name="title" id="title" type="text" value="${title}"/>

                <label for="year">Vuosi (year)</label>
                <input name="year" id="year" type="text" value="${year}"/>

                <c:choose>
                    <c:when test="${paivitysMoodi}">
                        <input name="paivitysMoodi" type="hidden" value="true" />
                        <input name="form-submit" id="form-submit" class="button" type="submit" value="Päivitä" />
                    </c:when>
                    <c:otherwise><input name="form-submit" id="form-submit" class="button" type="submit" value="Lähetä" /></c:otherwise>
                </c:choose>

                <p class="toggle" onclick="togglaaSetti('valinnaiset');">+ Lisää valinnaista tietoa</p>

                <fieldset id="valinnaiset">

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
            </fieldset>
        </form>

    </body>
</html>