<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<c:import url="head.jsp" />
    <body>
		<c:import url="header.jsp" />
		<c:import url="nav.jsp" />
        <form method="post" action="inscription.html">
        	<img src="img/kali.png" />
        	<br />
        	<span class="form_title">Inscription</span>
	
			<br /><br />
	
	        <input type="email" id="email" name="email" placeholder="email" size="20" maxlength="256" />
	        <span class="erreur">${form.erreurs['email']}</span>
	        <br />
	
	        <input type="password" id="password" name="password" placeholder="password" size="20" maxlength="128" />
	        <span class="erreur">${form.erreurs['password']}</span>
	        <br />
	
	        <input type="password" id="confirmation" name="confirmation" placeholder="confirmation" size="20" maxlength="128" />
	        <span class="erreur">${form.erreurs['confirmation']}</span>
	        <br />
	
	        <input type="text" id="username" name="username" placeholder="pseudo" size="20" maxlength="128" />
	        <span class="erreur">${form.erreurs['username']}</span>
	        <br /><br />
	
	        <input type="submit" value="Inscription" class="sansLabel" />
	        <br />
	        
	        <p class="${empty form.erreurs ? 'succes' : 'erreur'}">${form.resultat}</p>
        </form>
    </body>
</html>