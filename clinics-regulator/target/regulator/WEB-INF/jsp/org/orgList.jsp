<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%String path = request.getContextPath(); %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:forEach items="${orgList}" var="org" step="1">
	<tr>
         <td>${org.orgName}</td>
         <td>${org.address}</td>
         <td>${org.userName}</td>
         <td>${org.mobile}</td>
         <td>${org.taName}</td>
         <td>${org.addressName}</td>
         <td>
			<a href="javascript:void(0);" id="${org.id}" class="disable">删除</a>||
			<a href="javascript:void(0);" id="${org.id}" class="updateOneOrg">修改</a>
         </td>

     </tr>
</c:forEach>
<input type="hidden" id="pageTotal" value="${total}"/>
<input type="hidden" id="pageCurrent" value="${current}"/>