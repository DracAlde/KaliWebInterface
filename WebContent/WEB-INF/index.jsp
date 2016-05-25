<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="nav.jsp"></c:import>
<link rel="stylesheet" type="text/css" href="css/home.css">
<body>
	<section>
		<div class="title col-lg-12 col-sm-12 col-xs-12">
			<h1 class="h1">Shiva, an other way to hack the world</h1>
			<h3 class="h3">&ldquo;Practice more to go further into the system&rdquo;</h3>
		</div>
	</section>


	<section class="choice">
		<div class="tile col-lg-3 col-md-3 col-sm-6 col-xs-6">
			<a href="dnsrecon">
			<div class="hovereffect">
				<img class="img-responsive" src="img/base_dnsrecon.png">
				<div class="overlay">
					<h2>${language.getLanguageValue('default', '/default/home/tuiles/dns-recon/nom')}</h2>
					<h3>${language.getLanguageValue('default', '/default/home/tuiles/dns-recon/desc-courte')}</h3>
				</div>
			</div>
			</a>
		</div>
		
		<div class="tile col-lg-3 col-md-3 col-sm-6 col-xs-6">
			<a href="hydra">
			<div class="hovereffect">
				<img class="img-responsive" src="img/base_hydra.png">
				<div class="overlay">
					<h2>${language.getLanguageValue('default', '/default/home/tuiles/hydra/nom')}</h2>
					<h3>${language.getLanguageValue('default', '/default/home/tuiles/hydra/desc-courte')}</h3>
				</div>
			</div>
			</a>
		</div>
		
		<div class="tile col-lg-3 col-md-3 col-sm-6 col-xs-6">
			<a href="nikto">
			<div class="hovereffect">
				<img class="img-responsive" src="img/base_nikto.png">
				<div class="overlay">
					<h2>${language.getLanguageValue('default', '/default/home/tuiles/nikto/nom')}</h2>
					<h3>${language.getLanguageValue('default', '/default/home/tuiles/nikto/desc-courte')}</h3>
				</div>
			</div>
			</a>
		</div>
		
		<div class="tile col-lg-3 col-md-3 col-sm-6 col-xs-6">
			<a href="#">
			<div class="hovereffect">
				<img class="img-responsive" src="img/base_terminal.png">
				<div class="overlay">
					<h2>${language.getLanguageValue('default', '/default/home/tuiles/terminal/nom')}</h2>
					<h3>${language.getLanguageValue('default', '/default/home/tuiles/terminal/desc-courte')}</h3>
				</div>
			</div>
			</a>
		</div>
		
		<div class="tile col-lg-3 col-md-3 col-sm-6 col-xs-6">
			<a href="theharvester">
			<div class="hovereffect">
				<img class="img-responsive" src="img/base_theharvester.png">
				<div class="overlay">
					<h2>${language.getLanguageValue('default', '/default/home/tuiles/the-harvester/nom')}</h2>
					<h3>${language.getLanguageValue('default', '/default/home/tuiles/the-harvester/desc-courte')}</h3>
				</div>
			</div>
			</a>
		</div>
		
		<div class="tile col-lg-3 col-md-3 col-sm-6 col-xs-6">
			<a href="tlssled">
			<div class="hovereffect">
				<img class="img-responsive" src="img/base_tlssled.png">
				<div class="overlay">
					<h2>${language.getLanguageValue('default', '/default/home/tuiles/tls-sled/nom')}</h2>
					<h3>${language.getLanguageValue('default', '/default/home/tuiles/tls-sled/desc-courte')}</h3>
				</div>
			</div>
			</a>
		</div>
		
		<div class="tile col-lg-3 col-md-3 col-sm-6 col-xs-6">
			<a href="zenmap">
			<div class="hovereffect">
				<img class="img-responsive" src="img/base_zenmap.png">
				<div class="overlay">
					<h2>${language.getLanguageValue('default', '/default/home/tuiles/zenmap/nom')}</h2>
					<h3>${language.getLanguageValue('default', '/default/home/tuiles/zenmap/desc-courte')}</h3>
				</div>
			</div>
			</a>
		</div>
		
		<div class="tile col-lg-3 col-md-3 col-sm-6 col-xs-6">
			<a href="#">
			<div class="hovereffect">
				<img class="img-responsive" src="img/base_settings.png">
				<div class="overlay">
					<h2>${language.getLanguageValue('default', '/default/home/tuiles/parametres/nom')}</h2>
					<h3>${language.getLanguageValue('default', '/default/home/tuiles/parametres/desc-courte')}</h3>
				</div>
			</div>
			</a>
		</div>
	</section>
	
	<c:import url="credits.jsp"></c:import>

	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="bootstrap/js/bootstrap.js"></script>
</body>

</html>