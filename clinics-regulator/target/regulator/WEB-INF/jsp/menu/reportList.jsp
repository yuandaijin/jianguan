<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:forEach items="${list}" var="report" step="1">
	<tr id="${report.id}">
         <td class="type">${report.type}</td>
         <td>
         	 <c:if test="${report.print == 0}">
         	 	<input class="checkOne" type="checkbox" value="${report.print}" checked="checked" disabled="true"/>套打
         	 </c:if>
         	 <c:if test="${report.print == 1}">
         	 	<input class="checkOne" type="checkbox" value="${report.print}" disabled="true"/>套打
         	 </c:if>
	         <c:if test="${report.notPrint == 0}">
         	 	<input class="checkTwo" type="checkbox" value="${report.notPrint}"  checked="checked" disabled="true"/>非套打
         	 </c:if>
         	 <c:if test="${report.notPrint == 1}">
         	 	<input class="checkTwo" type="checkbox" value="${report.notPrint}"  disabled="true"/>非套打
         	 </c:if>
         </td>
         <td><input type="text" class="form-control" id="input1" name="printName"  value="${report.printName}" disabled='disabled'></td>
         <td><input type="text" class="form-control" id="input2" name="notPrintName"  value="${report.notPrintName}" disabled='disabled'></td>
         <td><input type="text" class="form-control" id="input3" name="memo"  value="${report.memo}" disabled='disabled'></td>
         <td>
         	<select class="form-control" id="flg" disabled='disabled'>
         		 <c:if test="${report.defaultType == 0}">
         		 	<option value="0" selected="selected">套打</option>
         		 	<option value="1">非套打</option>
         	 	 </c:if>
         	 	 <c:if test="${report.defaultType == 1}">
         		 	<option value="0" >套打</option>
         		 	<option value="1" selected="selected">非套打</option>
         	 	 </c:if>
         	</select>
         </td>
     </tr>
</c:forEach>