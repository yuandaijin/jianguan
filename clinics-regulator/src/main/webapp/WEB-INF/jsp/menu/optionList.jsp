<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%String path = request.getContextPath(); %>
<c:if test="${roleList != null}">
<ul class="nav" baseUrl="<%=path%>">
	<c:forEach items="${roleList}" var="role" step="1">
     <option value="${role.id}">${role.rolName}</option>
    </c:forEach>
</ul>
</c:if>