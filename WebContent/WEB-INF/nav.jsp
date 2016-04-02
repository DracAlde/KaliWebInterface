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
<title>Shiva 0.1</title>

<!-- Bootstrap -->
<link href="bootstrap/css/bootstrap.css" rel="stylesheet">
<link href="css/nav.css" rel="stylesheet"> 
<link href="font-awesome/css/font-awesome.css" rel="stylesheet"> 

</head>

<!-- http://bootsnipp.com/snippets/featured/responsive-navigation-menu -->

<div class="nav-side-menu">
	<div class="brand">
		<img src="img/kali_logo.png"> 
	SHIVA</div>
	<i class="fa fa-bars fa-2x toggle-btn" data-toggle="collapse"
		data-target="#menu-content"></i>

	<div class="menu-list">

		<ul id="menu-content" class="menu-content collapse out">
			<li><a href="<c:url value="/"></c:url>"> <i class="fa fa-dashboard fa-lg"></i>
					<strong>Accueil</strong>
			</a></li>
			
			<li role="separator" class="separator"></li>

			<li><a href="<c:url value="/nikto.html"></c:url>"> Nikto
			</a></li>

			<li><a href="<c:url value="/zenmap.html"></c:url>"> <i class="fa fa-eye fa-lg"></i> Zenmap
			</a></li>
			
			<li><a href="<c:url value="/theharvester.html"></c:url>"> The Harvester
			</a></li>
			
			<li><a href="<c:url value="/tlssled.html"></c:url>"> <i class="fa fa-lock fa-lg"></i> TLS Sled
			</a></li>
			
			<li><a href="<c:url value="/hydra.html"></c:url>"> Hydra
			</a></li>
			
			<li><a href="<c:url value="/dnsrecon.html"></c:url>"> DNS Recon
			</a></li>
		</ul>
	</div>
</div>