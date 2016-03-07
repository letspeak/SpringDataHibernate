<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<h1><spring:message code="employee.list.page.title"/></h1>
<c:if test="${not empty employees}">
    <table>
        <thead>
        <tr>
            <td><spring:message code="employee.label.lastName"/></td>
            <td><spring:message code="employee.label.firstName"/></td>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${employees}" var="employee">
            <tr>
                <td><c:out value="${employee.lastName}"/></td>
                <td><c:out value="${employee.firstName}"/></td>
                <td><a href="/employee/edit/<c:out value="${employee.id}"/>"><spring:message code="employee.edit.link.label"/></a></td>
                <td><a href="/employee/delete/<c:out value="${employee.id}"/>"><spring:message code="employee.delete.link.label"/></a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>
<c:if test="${empty employees}">
    <p>
        <spring:message code="employee.list.page.label.no.employees.found"/>
    </p>
</c:if>