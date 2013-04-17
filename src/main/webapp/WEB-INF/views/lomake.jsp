<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="fi">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <title>Lisää lähdeviite - Kevätpäivä</title>
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
        
        <ul id="navigaatio">
            <li><a href="${pageContext.request.contextPath}">S</a></li>
            <li><a href="${pageContext.request.contextPath}/listaa">Tarkastele viitteitä</a></li>
            <li><a href="${pageContext.request.contextPath}/haebibtex">Tarkastele viitteitä BibTex-muodossa</a></li>
        </ul>
        
        <h1>Lisää lähdeviite</h1>
        
        <sf:form modelAttribute="viiteTyypit" id="viiteTyypiLomake" action="${pageContext.request.contextPath}/lomake/${viiteTyyppi.nimi}" method="GET" enctype="application/x-www-form-urlencoded">
            <fieldset>
                <legend>Valitse viitteen tyyppi</legend>
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
        
        <c:if test="${viiteTyyppi != null}">
            <sf:form modelAttribute="viiteTyyppi" action="${pageContext.request.contextPath}/lisaa" method="POST" enctype="application/x-www-form-urlencoded">
                <fieldset>
                    <legend>Lisää ${viiteTyyppi.selitys}</legend>
                    
                    <input type="hidden" name="viiteTyyppi" value="${viiteTyyppi.nimi}" />
                    
                    <label for="id" class="pakollinen">Id</label>
                    <input type="text" name="id" />
                
                <c:forEach items="${viiteTyyppi.kenttaTyypit}" var="vts">
                    <label for="${vts.nimi}"<c:if test="${vts.pakollinen}"> class="pakollinen"</c:if>>${vts.selite}</label>
                    <input type="text" name="${vts.nimi}" />
                </c:forEach>

                    <input name="lahetaLomake" id="lahetaLomake"" class="button" type="submit" value="Lähetä" />
                </fieldset>
            </sf:form>
        </c:if>
        
    </body>
</html>
