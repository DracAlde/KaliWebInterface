<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="nav.jsp"></c:import>
<body>
	<section>
		<div class="row">
			<h1 class=col-lg-5>TLS Sled page</h1>
		</div>
		<div class="row">
			<form class=col-lg-5 method="POST" action="<c:url value="/tls_sled.jsp" ></c:url>">
				<label for="hostname">Hostname ou IP</label>
				<br />
				<input type="text" name="hostname"id="hostname"/>
				<br />
				<label for="port">Port</label>
				<br />
				<input type="number" name="port" id="port" value="443" />
				<br />
				<input type="submit" value="Envoyer" />
			</form>
		</div>
		<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
		<script
			src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.js"></script>
		<!-- Include all compiled plugins (below), or include individual files as needed -->
		<script src="bootstrap/js/bootstrap.js"></script>
	</section>
</body>
</html>