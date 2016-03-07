<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
    <title><spring:message code="spring.data.jpa.example.title"/></title>
    <link rel="stylesheet" href="/static/css/styles.css" type="text/css"/>
</head>
<body>
    <jsp:include page="navigation.jsp"/>
    <h1><spring:message code="employee.create.page.title"/></h1>
    <div>
        <form:form action="/employee/create" commandName="employee" method="POST">
            <div>
                <form:label path="firstName"><spring:message code="employee.label.firstName"/>:</form:label>
                <form:input path="firstName" size="20"/>
                <form:errors path="firstName" cssClass="error" element="div"/>
            </div>
            <div>
                <form:label path="lastName"><spring:message code="employee.label.lastName"/>:</form:label>
                <form:input path="lastName" size="20"/>
                <form:errors path="lastName" cssClass="error" element="div"/>
            </div>
            <div>
                <input type="submit" value="<spring:message code="employee.create.page.submit.label"/>"/>
            </div>
        </form:form>
    </div>
</body>
</html>