<%@page import="webproject.commun.Language"%>
<%@page import="webproject.commun.Constants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="panel-body">
	<ul class="nav nav-pills">
		<li id="history_btn" role="presentation" class="active"
			onclick="changeHead(this)"><a> <c:out value="${language.getLanguageValue('default', '/default/historique')}" />
				<span class="badge"> <c:out
						value="${history['history'].size()}" /> <c:if
						test="${empty history}">
			  				0
			  			</c:if>
			</span>
		</a></li>

		<li id="desc_btn" role="presentation" onclick="changeHead(this)">
			<a> <c:out value="${language.getLanguageValue('default', '/default/description')}" />
		</a>
		</li>
	</ul>

	<br>

	<c:import url="history.jsp"></c:import>

	<c:import url="description.jsp"></c:import>

</div>

<script>
	var history_btn = document.getElementById('history_btn'),
		desc_btn = document.getElementById('desc_btn'),
		history_panel = document.getElementById('history_panel'),
		desc = document.getElementById('desc');
	
	function changeHead(e){
		if(desc_btn == e){
			desc_btn.className = 'active';
			history_btn.className = '';
			desc.className = '';
			history_panel.className = 'hidden';
		}else{
			history_btn.className = 'active';
			desc_btn.className = '';
			history_panel.className = '';
			desc.className = 'hidden';
		}
	}
</script>