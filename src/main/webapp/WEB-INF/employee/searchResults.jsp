<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title><spring:message code="spring.data.jpa.example.title"/></title>
    <link rel="stylesheet" href="/static/css/styles.css" type="text/css"/>
    <link rel="stylesheet" href="/static/css/pagination.css" type="text/css"/>
    <!-- Fetch jQuery from Google's CDN and fallback to local version -->
    <script src=”http://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js”></script>
    <script>window.jQuery || document.write('<script src="/static/js/jquery-1.7.2.min.js"><\/script>')</script>

    <script type="text/javascript" src="/static/js/jquery.pagination.js"></script>
    <script type="text/javascript" src="/static/js/handlebars-1.0.0.beta.6.js"></script>
    <script type="text/javascript" src="/static/js/json2.js"></script>

    <script type="text/javascript" src="/static/js/employee.search.js"></script>
</head>
<body>
<div id="searchTerm" class="hidden">${searchCriteria.searchTerm}</div>
<jsp:include page="navigation.jsp"/>
<jsp:include page="searchForm.jsp"/>
<h1><spring:message code="employee.search.result.page.title"/>: <c:out value="${searchCriteria.searchTerm}"/></h1>
<div class="pagination-holder"></div>
<div class="pagination-clear"></div>
<div id="employee-list-holder">

</div>
<div class="pagination-holder"></div>
<div class="pagination-clear"></div>

<script id="template-employee-list" type="text/x-handlebars-template">
    {{#if employees}}
    <table>
        <thead>
        <tr>
            <td><spring:message code="employee.label.lastName"/></td>
            <td><spring:message code="employee.label.firstName"/></td>
        </tr>
        </thead>
        <tbody>
            {{#each employees}}
            <tr>
                <td>{{lastName}}</td>
                <td>{{firstName}}</td>
                <td><a href="/employee/edit/{{id}}"><spring:message code="employee.edit.link.label"/></a></td>
                <td><a href="/employee/delete/{{id}}"><spring:message code="employee.delete.link.label"/></a></td>
            </tr>
            {{/each}}
        </tbody>
    </table>
    {{/if}}
</script>

</body>
</html>