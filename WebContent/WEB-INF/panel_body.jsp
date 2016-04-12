<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<div class="panel-body">
	<ul class="nav nav-pills">
		<li id="history_btn" role="presentation" class="active"
			onclick="changeHead(this)"><a> History <span class="badge">
					<c:out value="${history['history'].size()}" /> <c:if
						test="${empty history}">
			  				0
			  			</c:if>
			</span>
		</a></li>

		<li id="desc_btn" role="presentation" onclick="changeHead(this)">
			<a> Description </a>
		</li>
	</ul>

	<br>

	<div id="history_panel">
		<div class="panel panel-default">
			<div class="list-group" style="height: 150px; overflow: auto;">
				<c:forEach items="${history['history']}" var="command">
					<c:if test="${command.isSuccess()}">
						<a class="list-group-item list-group-item-success">
					</c:if>
					<c:if test="${!command.isSuccess()}">
						<a class="list-group-item list-group-item-danger">
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

	<div id="desc" class="hidden">
		<div class="panel panel-default">
			<div class="panel-body" style="height: 150px; overflow: auto;">
				Description de tsl sled,<br> quand il est apparu,<br> qui
				l'a créé,<br> à quoi il sert,<br> dans quel contexte on
				l'utilise ... =)<br> On peut aussi mettre la description dans
				l'info-bulle du point d'interrogration près du titre !
			</div>
		</div>
	</div>
</div>