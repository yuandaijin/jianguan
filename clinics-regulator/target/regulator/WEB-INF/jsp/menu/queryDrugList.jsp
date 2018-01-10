<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:forEach items="${drugList}" var="drug" step="1">
	<tr>
         <td>${drug.tradeName}</td>
         <td>${drug.factoryName}</td>
         <td>${drug.supplyName}</td>
         <td>
         	<a href="javascript:void(0);" drugid="${drug.id}" class="deleteDrug">删除</a>
        	<span>||</span>
         	<a href="javascript:void(0);" drugid="${drug.id}" class="${delFlag == 0 ? 'updateDrug' : 'updatedelFlag'}">${delFlag == 0 ? '修改' : '还原'}</a>
         </td>
     </tr>
</c:forEach>
	<input type="hidden" id="pageTotal" value="${total}"/>
	<input type="hidden" id="pageCurrent" value="${current}"/>