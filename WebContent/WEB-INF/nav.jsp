<%@page import="webproject.commun.Language"%>
<%@page import="webproject.commun.Constants"%>
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
<!-- Adding CSS import to perform help tip -->
<link href="css/help_tip.css" rel="stylesheet">
<link href="font-awesome/css/font-awesome.css" rel="stylesheet">
<link rel="shortcut icon" type="image/png" href="img/favicon4.png"/>

</head>

<!-- http://bootsnipp.com/snippets/featured/responsive-navigation-menu -->

<!-- <div class="info-board">

		<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
			<img class="status-connection" src="img/earth-BR.png" width="12px"
				height="12px"> 
			<img class="status-connection hidden"
				src="img/earth-BB.png" width="12px" height="12px"> 
			<img
				class="status-connection hidden" src="img/earth-BG.png" width="12px"
				height="12px">
		</div>
		
		<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
			<li data-toggle="collapse" data-target="#service" class="collapsed language">
				FR <span class="arrow"></span>
			</li>
			<ul class="sub-menu collapse" id="service">
				<li>FR</li>
				<li>EN</li>
			</ul>
		</div>
		
		<div class="col-lg-4 col-md-4 col-sm-4 col-xs-4">
			<span class="settings glyphicon glyphicon-cog"></span>
		</div>
		
</div> -->

<div class="nav-side-menu">
	<a id="title_link" href="<c:url value="/"></c:url>">
		<div class="brand">
			<img src="img/kali_logo.png" width="60px" height="60px"> SHIVA
		</div>
	</a> <i class="fa fa-bars fa-2x toggle-btn" data-toggle="collapse"
		data-target="#menu-content"></i>

	<div class="menu-list">

		<ul id="menu-content" class="menu-content collapse out">

			<a href="<c:url value="/"></c:url>">
				<li><i class="fa fa-dashboard fa-lg"></i><strong><c:out
							value="${language.getLanguageValue('default', '/default/nav/accueil')}" /></li>
			</a>

			<li role="separator" class="separator"></li>

			<a href="<c:url value="/nikto"></c:url>">
				<li><img src="img/nikto.png"> <c:out
						value="${language.getLanguageValue('default', '/default/nav/nikto')}" /></li>
			</a>

			<a href="<c:url value="/zenmap"></c:url>">
				<li><i class="fa fa-eye fa-lg"></i> <c:out
						value="${language.getLanguageValue('default', '/default/nav/zenmap')}" /></li>
			</a>

			<a href="<c:url value="/theharvester"></c:url>">
				<li><img src="img/harvester.png"> <c:out
						value="${language.getLanguageValue('default', '/default/nav/the-harvester')}" />
			</li>
			</a>

			<a href="<c:url value="/tlssled"></c:url>">
				<li><i class="fa fa-lock fa-lg"></i> <c:out
						value="${language.getLanguageValue('default', '/default/nav/tls-sled')}" /></li>
			</a>

			<a href="<c:url value="/hydra"></c:url>">
				<li><img src="img/hydra.png"> <c:out
						value="${language.getLanguageValue('default', '/default/nav/hydra')}" /></li>
			</a>

			<a href="<c:url value="/dnsrecon"></c:url>">
				<li><img src="img/dnsrecon.png"> <c:out
						value="${language.getLanguageValue('default', '/default/nav/dns-recon')}" /></li>
			</a>

			<a href="<c:url value="/dnsrecon"></c:url>">
				<li><i class="fa fa-terminal fa-lg"></i> <c:out
						value="${language.getLanguageValue('default', '/default/nav/terminal')}" /></li>
			</a>
					<div class="onoffswitch" style="margin-left: 5px;margin-top: 5px;">
					<c:if test="${language.getLanguage().equals('en')}">
							<input type="checkbox" name="onoffswitch" onchange="updateLang()"
								class="onoffswitch-checkbox" id="myonoffswitch">
					</c:if>
					<c:if test="${language.getLanguage().equals('fr')}">
							<input type="checkbox" name="onoffswitch" onchange="updateLang()"
								class="onoffswitch-checkbox" id="myonoffswitch" checked>
					</c:if>
							<label class="onoffswitch-label" for="myonoffswitch"> <span
								class="onoffswitch-inner"></span> <span
								class="onoffswitch-switch"></span>
							</label>
					</div>
<!-- 			</a>
 -->		</ul>
	</div>
</div>

<script type="text/javascript">

function updateLang(){
		document.location.href = "changelanguage";
}
</script>

<!-- 

<style>
.horizontal-nav {
	background-color: #23282e;
	color: #c3c3c3;
}

.language-box {
	float: right;
}

.dropdown-menu {
  min-width: 0px;
}
</style>

<script type="text/javascript">

 
</script>

<section>
	<div class="horizontal-nav col-md-12 col-lg-12">
		<div class="language-box">
			<button class="btn btn-default btn-xs dropdown-toggle" type="button"
				data-toggle="dropdown">
				<c:out value="${language.language}" /><span class="caret"></span>
			</button>
			<ul class="dropdown-menu dropdown-menu-right">
				<li><a href="#">FR</a></li>
				<li><a href="#">EN</a></li>
			</ul>
		</div>
	</div>
</section>

 -->