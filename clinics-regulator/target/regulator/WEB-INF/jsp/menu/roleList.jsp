<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%String path = request.getContextPath(); %>
<c:if test="${roleList != null}">
	<c:forEach items="${roleList}" var="menu" step="1">
	<li class="list-group-item active list-${level} node-treeview2 node-selected" data-nodeid="${level}" parentid = "${menu.parentId}" mid = "${menu.mId}" level="${level + 1}">
        <span class="icon">
            <i class="click-collapse glyphicon glyphicon-plus"></i>
        </span>
        <input type="checkbox" class = "selectmenu" name="munu" value="${menu.mId}" parentid = "${menu.parentId}" level="${level + 1}">${menu.menuName}
	</li>
    </c:forEach>
</c:if>
