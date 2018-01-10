<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:forEach items="${roleList}" var="role" step="1">
	<tr>
         <td>${role.rolName}</td>
         <td>${role.describe}</td>
         <td>
         		<span><a href="javascript:void(0);" roleid="${role.id}" class="deleteRole">删除</a></span>
         		<span>||</span>
         		<span><a href="javascript:void(0);" roleid="${role.id}" class="updateRole">修改</a></span>
         </td>
     </tr>
</c:forEach>