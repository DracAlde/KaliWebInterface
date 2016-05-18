<%@page import="webproject.commun.Language"%>
<%@page import="webproject.commun.Constants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id="history_panel">
	<div class="panel panel-default">
		<div id="hist" class="list-group" style="height: 150px; overflow: auto;">
			<c:forEach items="${history['history']}" var="command">
				<c:if test="${command.isSuccess()}">
					<a class="list-group-item list-group-item-success"
						href="<c:url value="${command.getURL()}" />"> 
						<%-- <strong><c:out
								value="${command['tool']}" /></strong> hostname = <strong><c:out
								value="${command.getArguments()['hostname']}" /></strong> port = <strong><c:out
								value="${command.getArguments()['port']}" /></strong>
								commande =  --%>
								<strong><c:out
								value="${command.getStringCommmand()}" /></strong>
					</a>
				</c:if>
				<c:if test="${!command.isSuccess()}" >
					<a class="list-group-item list-group-item-danger"
						href="<c:url value="${command.getURL()}" />"> 
						<%-- <strong><c:out
								value="${command['tool']}" /></strong> hostname = <strong><c:out
								value="${command.getArguments()['hostname']}" /></strong> port = <strong><c:out
								value="${command.getArguments()['port']}" /></strong>
								commande =  --%>
								<strong><c:out
								value="${command.getStringCommmand()}" /></strong>
					</a>
				</c:if>
			</c:forEach>
		</div>
	</div>
</div>

<script type='text/javascript'>
function scrollDown()
{
    document.getElementById('hist').scrollTop = '15000000';
}
scrollDown();
</script>