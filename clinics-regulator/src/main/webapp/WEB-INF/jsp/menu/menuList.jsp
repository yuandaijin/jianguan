<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%String path = request.getContextPath(); %>
<c:if test="${mlist != null}">
<ul class="nav" baseUrl="<%=path%>">
	<c:forEach items="${mlist}" var="menu_s" step="1" varStatus="num_s">
      <li>
      
      <a class="${(menu_s.menuUrl == null || empty menu_s.menuUrl) ? 'toOperate' : 'J_menuItem'}" href="${(menu_s.menuUrl == null || empty menu_s.menuUrl) ? 'javascript:void(0);' : menu_s.menuUrl }" huatuo_url="${menu_s.menuUrl}" parentId="${menu_s.parentId}" mId="${menu_s.mId}" level = ${level + 1}>${menu_s.menuName}
      <c:if test="${(menu_s.menuUrl == null || empty menu_s.menuUrl)}">
      	<span class="fa arrow"></span>
      </c:if>
      </a>
     </li> 
    </c:forEach>
</ul>
</c:if>