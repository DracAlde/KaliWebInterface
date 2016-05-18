<%@page import="webproject.commun.Language"%>
<%@page import="webproject.commun.Constants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id="history_panel">
	<div class="panel panel-default">
		<div class="list-group" style="height: 150px; overflow: auto;">
			<c:forEach items="${history['history']}" var="command">
				<c:if test="${command.isSuccess()}">
					<a class="list-group-item list-group-item-success" href="<c:url value="${command.getURL()}" />">
				</c:if>
				<c:if test="${!command.isSuccess()}" >
					<a class="list-group-item list-group-item-danger" href="<c:url value="${command.getURL()}" />">
				</c:if>
				<strong><c:out value="${command['tool']}" /></strong>
							hostname = <strong><c:out
						value="${command.getArguments()['hostname']}" /></strong>
							port = <strong><c:out
						value="${command.getArguments()['port']}" /></strong>
				</a>
			</c:forEach>
		</div>
	</div>
</div>