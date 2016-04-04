<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>




<c:import url="nav.jsp"></c:import>
<body>
	<section>
		<h1 class="col-lg-3">Hello, world!</h1>
	</section>

	<p class="col-lg-5">
	This text is just a trying one permitting to test Help Tip CSS feature !
	<div class="help-tip">
		<p>This is the inline help tip! It can contain all kinds of HTML.
			Style it as you please.</p>
	</div>
	</p>

	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="bootstrap/js/bootstrap.js"></script>
</body>
</html>