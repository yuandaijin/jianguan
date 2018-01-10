<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%String path = request.getContextPath(); %>
<c:if test="${companys != null}">
<select style="height: 30px;" name="companyId">
	<c:forEach items="${companys}" var="company" step="1">
     <option value="${company.companyId}">${company.companyName}</option>
    </c:forEach>
    </select>
</c:if>