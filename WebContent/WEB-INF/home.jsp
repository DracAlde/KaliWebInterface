<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>

<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<title>Bootstrap 101 Template</title>

<!-- Bootstrap -->
<link href="bootstrap/css/bootstrap.css" rel="stylesheet">
<link
	href="css/nav.css"
	rel="stylesheet">

</head>
<body>


	<div class="nav-side-menu">
		<div class="brand">Brand Logo</div>
		<i class="fa fa-bars fa-2x toggle-btn" data-toggle="collapse"
			data-target="#menu-content"></i>

		<div class="menu-list">

			<ul id="menu-content" class="menu-content collapse out">
				<li><a href="#"> <i class="fa fa-dashboard fa-lg"></i>
						Dashboard
				</a></li>

				<li data-toggle="collapse" data-target="#products"
					class="collapsed active"><a href="#"><i
						class="fa fa-gift fa-lg"></i> UI Elements <span class="arrow"></span></a>
				</li>
				<ul class="sub-menu collapse" id="products">
					<li class="active"><a href="#">CSS3 Animation</a></li>
					<li><a href="#">General</a></li>
					<li><a href="#">Buttons</a></li>
					<li><a href="#">Tabs & Accordions</a></li>
					<li><a href="#">Typography</a></li>
					<li><a href="#">FontAwesome</a></li>
					<li><a href="#">Slider</a></li>
					<li><a href="#">Panels</a></li>
					<li><a href="#">Widgets</a></li>
					<li><a href="#">Bootstrap Model</a></li>
				</ul>


				<li data-toggle="collapse" data-target="#service" class="collapsed">
					<a href="#"><i class="fa fa-globe fa-lg"></i> Services <span
						class="arrow"></span></a>
				</li>
				<ul class="sub-menu collapse" id="service">
					<li>New Service 1</li>
					<li>New Service 2</li>
					<li>New Service 3</li>
				</ul>


				<li data-toggle="collapse" data-target="#new" class="collapsed">
					<a href="#"><i class="fa fa-car fa-lg"></i> New <span
						class="arrow"></span></a>
				</li>
				<ul class="sub-menu collapse" id="new">
					<li>New New 1</li>
					<li>New New 2</li>
					<li>New New 3</li>
				</ul>


				<li><a href="#"> <i class="fa fa-user fa-lg"></i> Profile
				</a></li>

				<li><a href="#"> <i class="fa fa-users fa-lg"></i> Users
				</a></li>
			</ul>
		</div>
	</div>


	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="bootstrap/js/bootstrap.js"></script>


	<div class=col-lg-5>.</div>
	<h1 class=col-lg-1>Hello, world!</h1>
</body>
</html>