<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:forEach items="${factoryList}" var="factory" step="1">
	<tr>
         <td>${factory.name}</td>
         <td>
         	<a href="javascript:void(0);" factoryid="${factory.id}" class="deleteFactory">删除</a>
        	<span>||</span>
         	<a href="javascript:void(0);" factoryid="${factory.id}" class="updateFactory">修改</a>
         </td>
     </tr>
</c:forEach>
<input type="hidden" id="pageTotal" value="${total}"/>
<input type="hidden" id="pageCurrent" value="${current}"/>