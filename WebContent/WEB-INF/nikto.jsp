<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="nav.jsp"></c:import>
<body>
	<section>
		<div class="row">
			<div class="col-md-6"
				style="border: 1px solid black;">
				<h3>Informations</h3>
				<p>Nikto est le meilleur logiciel du monde parce que ceci ou
					cela</p>
			</div>
			<c:import url="history.jsp"></c:import>
		</div>
		<h1 class=col-lg-3>Nikto page</h1>

		<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
		<script
			src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.js"></script>
		<!-- Include all compiled plugins (below), or include individual files as needed -->
		<script src="bootstrap/js/bootstrap.js"></script>
	</section>
</body>
</html>