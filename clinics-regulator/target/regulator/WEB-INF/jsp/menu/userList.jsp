<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:forEach items="${personList}" var="user" step="1">
	<tr>
         <td>${user.userName}</td>
         <td>${user.userNames}</td>
         <td>${user.mobile}</td>
         <td>${user.userType}</td>
         <td>${user.orgName}</td>
         <td><fmt:formatDate value="${user.data}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
         <td>
	         <c:if test="${user.id != 1}">
	         		<a href="javascript:void(0);" uid="${user.id}" class="deleteUser">删除</a>
	        		<span>||</span>
	         		<a href="javascript:void(0);" uid="${user.id}" name="${user.userName}" class="updateUser">修改</a>
	         </c:if>
         </td>
     </tr>
</c:forEach>