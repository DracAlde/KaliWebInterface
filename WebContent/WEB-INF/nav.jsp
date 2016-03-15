<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="webproject.commun.Level"%>
<nav>
	<a href="http://localhost:8080/WebProject/">
		<div class="menu_button" id="button1">
			<span class="button_title">ACCUEIL</span>
		</div>
	</a> 
	
	<a href="http://localhost:8080/WebProject/">
		<div class="menu_button" id="button2">
			<span class="button_title">BUTTON 2</span>
		</div>
	</a> 
	
	<a href="http://localhost:8080/WebProject/">
		<div class="menu_button">
			<span class="button_title">BUTTON 3</span>
		</div>
	</a>

	<c:if test="${sessionScope.userSession.getLevel() == Level.ADMIN}">
		<a href="http://localhost:8080/WebProject/admin.html">
			<div class="menu_button">
				<span class="button_title">ADMIN</span>
			</div>
		</a>
	</c:if>

	<c:if test="${empty sessionScope.userSession}">
		<a href="http://localhost:8080/WebProject/inscription.html">
			<div class="menu_button_right">
				<span class="button_title">INSCRIPTION</span>
			</div>
		</a>
		
		<a href="http://localhost:8080/WebProject/connection.html">
			<div class="menu_button_right">
				<span class="button_title">CONNEXION</span>
			</div>
		</a>
	</c:if>

	<c:if test="${sessionScope.userSession != null}">
		<a href="http://localhost:8080/WebProject/disconnection.html">
			<div class="menu_button_right">
				<span class="button_title">DECONNEXION</span>
			</div>
		</a>
	</c:if>
</nav>