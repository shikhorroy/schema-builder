<%@ page import="java.util.ResourceBundle" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%
    //~ load the application properties:
    ResourceBundle resourceBundle = ResourceBundle.getBundle("application");
    String selectedQueryLanguage = null;
    try {
        selectedQueryLanguage = resourceBundle.getString("schema.selected-query-language");
    } catch (Exception e) {
        selectedQueryLanguage = "oracle";
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <%-- START :: imporing JS--%>
    <script src="https://code.jquery.com/jquery-2.2.4.js" type="text/javascript"></script>
    <spring:url value="/js/populate.js" var="springJs"/>
    <script src="${springJs}"></script>
    <%-- END :: imporing JS--%>

    <%-- START :: imporing CSS--%>
    <%--<c:url value="/css/main.css" var="jstlCss" />--%>
    <%--<link href="${jstlCss}" rel="stylesheet" />--%>
    <spring:url value="/css/main.css" var="springCss"/>
    <link href="${springCss}" rel="stylesheet"/>
    <%-- END :: imporing CSS--%>

    <meta charset="UTF-8">
    <title>Schema Builder</title>
</head>
<body>
<div id="main" class="center">
    <h2>Schema Builder</h2>
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
        <div id="sql-lang-label">Query Language:</div>
        <div id="sql-lang-options">
            <select>
                <option value="oracle" <%= (selectedQueryLanguage.equals("oracle") ? "selected='selected'" : "") %>>
                    Oracle
                </option>
                <option value="mysql" <%= (selectedQueryLanguage.equals("mysql") ? "selected='selected'" : "") %>>
                    MySQL
                </option>
                <option value="mssql" <%= (selectedQueryLanguage.equals("mssql") ? "selected='selected'" : "") %>>
                    MSSQL
                </option>
                <option value="postgres" <%= (selectedQueryLanguage.equals("postgres") ? "selected='selected'" : "") %>>
                    PostgreSQL
                </option>
            </select>
        </div>
    </div>
    <div id="schema-path">
        <div id="schema-path-label">
            Schema Path :
        </div>
        <div id="schema-path-input">
            <input type="text" name="schema-path" placeholder="e.g. path/Schema.yaml"/>
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
        <input type="button" name="populate-schema" value="Populate Schema"/>
        <input type="button" name="qeury" value="Query"/>
    </div>

    <div id="output"></div>
</div>
</body>
</html>
