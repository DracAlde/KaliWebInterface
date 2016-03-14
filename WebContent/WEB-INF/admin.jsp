<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<c:import url="head.jsp" />
<body>
	<c:import url="header.jsp" />
	<c:import url="nav.jsp" />

	<div class="page_pannel">
		<div class="right_panel">
			<span>${nbSuccess}</span>
			<form method="POST" action="http://localhost:8080/WebProject/admin.html">
				<table>
					<tr>
						<th>Check</th>
						<th>Username</th>
						<th>Email</th>
						<th>Creation date</th>
						<th>Level</th>
					</tr>
					<c:forEach items="${list}" var="user">
						<tr>
							<td><input type="checkbox" name="${user.getUsername()}"/></td>
							<td><c:out value="${user.getUsername()}" /></td>
							<td><c:out value="${user.getEmail()}" /></td>
							<td><c:out value="${user.getCreationDate()}" /></td>
							<td><c:out value="${user.getLevel()}" /></td>
						</tr>
					</c:forEach>
				</table>
				<div style="text-align: right;">
					<input type="submit" value="Supprimer" />
				</div>
			</form>
		</div>
	</div>
</body>
</html>