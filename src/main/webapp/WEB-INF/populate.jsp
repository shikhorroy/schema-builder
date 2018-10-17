<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <%--<c:url value="/css/main.css" var="jstlCss" />--%>
    <%--<link href="${jstlCss}" rel="stylesheet" />--%>
    <spring:url value="/css/main.css" var="springCss"/>
    <link href="${springCss}" rel="stylesheet"/>

    <spring:url value="/js/populate.js" var="springJs"/>
    <script src="${springJs}"></script>

    <meta charset="UTF-8">
    <title>Schema Builder</title>
</head>
<body>
<div id="main">
    <div id="sql">
        <div id="sql-label">SQL :</div>
        <div id="sql-textarea"><textarea rows="4" cols="50"></textarea></div>
    </div>
    <br>
    <br>
    <br>
    <br>
    <br>
    <div id="sql-lang">
        <div id="sql-lang-label">SQL Language:</div>
        <div id="sql-lang-options">
            <select>
                <option value="oracle">Oracle</option>
                <option value="mysql">MySQL</option>
                <option value="mssql">MSSQL</option>
                <option value="postgres">PostgreSQL</option>
            </select>
        </div>
    </div>
    <div id="schema-path">
        <div id="schema-path-label">
            Schema Path :
        </div>
        <div id="schema-path-input">
            <input type="text" name="schema-path"/>
        </div>
    </div>
    <div id="model">
        <div id="model-label">
            Model :
        </div>
        <div id="model-input">
            <input type="text" name="model"/>
        </div>
    </div>
    <div id="submit-button">
        <input type="button" name="populate-schema" value="Populate Schema" onclick="populateSchema()"/>
        <input type="button" name="qeury" value="Query" onclick="query()"/>
    </div>
</div>
</body>
</html>
