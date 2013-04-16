<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="fi">
    <head>
        <meta charset="UTF-8" />
        <title>Lisää lähdeviitteitä</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/main.css" />
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/images/favicon.ico" />
        <script>
            function changeForm(){
                var selector = document.getElementById("viiteTyyppi");
                var selected = selector.options[selector.selectedIndex].value;
                console.log("chosen: " + selected);
                
                var selectorForm = document.getElementById("viiteTyypiLomake");
                selectorForm.action = "${pageContext.request.contextPath}/lomake/" + selected;
                selectorForm.submit();
                
            }
        </script>
    </head>
    <body>
        <ul id="navigation">
            <li><a href="listaa">Tarkastele viitteitä</a></li>
            <li><a href="haebibtex">Tarkastele viitteitä BibTex-muodossa</a></li>
        </ul>
        <sf:form modelAttribute="viiteTyypit" id="viiteTyypiLomake" action="${pageContext.request.contextPath}/lomake/${viiteTyyppi.nimi}" method="GET" enctype="application/x-www-form-urlencoded">
            <fieldset>
                <legend>Lisää lähdeviite</legend>
                <label for="viiteTyyppi">Tyyppi</label>
                <select name="viiteTyyppi" id="viiteTyyppi" size="3" onchange="changeForm();">
                    <c:forEach items="${viiteTyypit}" var="vt">
                        <c:choose>
                            <c:when test="${viiteTyyppi == vt.nimi}">
                                <option selected="selected" value="${vt.nimi}">${vt.selitys}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${vt.nimi}">${vt.selitys}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach> 
                </select>
            </fieldset>
        </sf:form>
        
        <c:forEach items="${viiteTyyppi.kenttaTyypit}" var="vts">
            <p>${vts.nimi}</p>
        </c:forEach>
        
    </body>
</html>
