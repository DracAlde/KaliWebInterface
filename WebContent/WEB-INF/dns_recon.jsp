<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="nav.jsp"></c:import>
<link href="bootstrap/css/bootstrap-multiselect.css" media="screen"
	rel="stylesheet" type="text/css">
<link href="css/dns_recon.css" media="screen" rel="stylesheet"
	type="text/css">

<body>

	<section>
		<div class="panel panel-default col-md-12 col-lg-12"
			style="padding: 0px;">

			<c:import url="top_page.jsp"></c:import>

			<div class="panel-footer">
				<nav class="navbar navbar-default">
					<div class="container-fluid">
						<!-- Brand and toggle get grouped for better mobile display -->
						<div class="navbar-header">
							<button type="button" class="navbar-toggle collapsed"
								data-toggle="collapse"
								data-target="#bs-example-navbar-collapse-1"
								aria-expanded="false">
								<span class="sr-only">Toggle navigation</span> <span
									class="icon-bar"></span> <span class="icon-bar"></span> <span
									class="icon-bar"></span>
							</button>
							<a class="navbar-brand">DNSRecon</a>
						</div>

						<ul class="nav navbar-nav">


							<li id="form_btn" role="presentation" class="active"
								onclick="switcher(this)"><a> <c:out
										value="${language.getLanguageValue('default', '/default/form')}" />

							</a></li>

							<li id="response_btn" role="presentation"
								onclick="switcher(this)"><a> <c:out
										value="${language.getLanguageValue('default', '/default/response')}" />
							</a></li>
							<li><a data-toggle="tooltip"
								title="<c:out value="${language.getLanguageValue('dns-recon', '/dns-recon/desc-tool')}" />"
								data-placement="right">?</a></li>
						</ul>

					</div>
				</nav>
				<div id="form">
					<form method="POST" action="<c:url value="/theharvester" ></c:url>">
						<div class="col-lg-12 col-mg-12 col-sm-12">
							<label for="domain" data-toggle="tooltip"
								title="<c:out value="${language.getLanguageValue('dns-recon', '/dns-recon/aide/domaine')}" />"
								data-placement="right"> <c:out
									value="${language.getLanguageValue('dns-recon', '/dns-recon/options/domaine')}" />
							</label>
							<div class="input-group col-lg-2 col-mg-4 col-sm-6">
								<input type="text" class="form-control" placeholder="google.com"
									name="domain" id="domain" onkeyup="updateCommand()"
									onselect="updateCommand()" autofocus required> <span
									class="input-group-addon" id="basic-addon2"
									data-toggle="tooltip"
									title="<c:out value="${language.getLanguageValue('dns-recon', '/dns-recon/aide/domaine')}" />"
									data-placement="bottom"> <i class="fa fa-question fa-lg"></i>
								</span>
							</div>
							<p class="error">${form.errors['domain']}</p>
						</div>

						<div class="col-lg-12 col-mg-12 col-sm-12">
							<label for="optional" data-toggle="tooltip"
								title="<c:out value="${language.getLanguageValue('dns-recon', '/dns-recon/aide/optional-options')}" />"
								data-placement="right"> <c:out
									value="${language.getLanguageValue('dns-recon', '/dns-recon/options/optional-options')}" />
							</label>

							<div class="switch">
								<input type="checkbox" name="switch" class="switch-checkbox"
									id="myswitch" onclick="updateCommand()" checked="checked">
								<label class="switch-label" for="myswitch"> <span
									class="switch-inner"></span> <span class="switch-switch"></span>
								</label>
							</div>
						</div>



						<div class="col-lg-12 col-mg-12 col-sm-12" id="enumeration">
							<div class="col-lg-12 col-mg-12 col-sm-12">
								<label for="enum" data-toggle="tooltip"
									title="<c:out value="${language.getLanguageValue('dns-recon', '/dns-recon/aide/enum-type')}" />"
									data-placement="right"> <c:out
										value="${language.getLanguageValue('dns-recon', '/dns-recon/options/enum-type')}" />
								</label>
							</div>

							<div class="input-group col-lg-4 col-mg-4 col-sm-6 btn-group">
								<span class="input-group-addon" id="basic-addon2"
									data-toggle="tooltip"
									title="<c:out value="${language.getLanguageValue('dns-recon', '/dns-recon/aide/enum-type')}" />"
									data-placement="bottom"> <i class="fa fa-question fa-lg"></i>
								</span> <select name="enum-type" id="enum-type"
									onchange="updateCommand()">
									<option value="rvl" id="reverse-lookup"><c:out
											value="${language.getLanguageValue('dns-recon', '/dns-recon/options/enum-type-options/reverse-lookup')}" /></option>
									<option value="brt" id="brute-force"><c:out
											value="${language.getLanguageValue('dns-recon', '/dns-recon/options/enum-type-options/brute-force')}" /></option>
									<option value="srv" id="srv-record"><c:out
											value="${language.getLanguageValue('dns-recon', '/dns-recon/options/enum-type-options/srv-records')}" /></option>
									<option value="axfr" id="axfr"><c:out
											value="${language.getLanguageValue('dns-recon', '/dns-recon/options/enum-type-options/axfr')}" /></option>
									<option value="goo" id="google"><c:out
											value="${language.getLanguageValue('dns-recon', '/dns-recon/options/enum-type-options/google')}" /></option>
									<option value="snoop" id="cache-snooping"><c:out
											value="${language.getLanguageValue('dns-recon', '/dns-recon/options/enum-type-options/cache-snoop')}" /></option>
									<option value="tld" id="tld"><c:out
											value="${language.getLanguageValue('dns-recon', '/dns-recon/options/enum-type-options/tld')}" /></option>
									<option value="zonewalk" id="zone-walk"><c:out
											value="${language.getLanguageValue('dns-recon', '/dns-recon/options/enum-type-options/zonewalk')}" /></option>
								</select>
							</div>
						</div>

						<br><br><br><br><br><br><br><br><br><br>

						<div>
							<div class=command id=command data-toggle="tooltip"
								data-placement="bottom">dnsrecon</div>
						</div>


						<div style="text-align: right">
							<br>
							<button type="submit" class="btn btn-default"
								aria-label="Right Align">
								<c:out
									value="${language.getLanguageValue('default', '/default/actions/bouton-envoyer')}" />
							</button>
						</div>

						<div>
							<p class="${empty form.errors ? 'succes' : 'error'}">${form.result}</p>
						</div>
					</form>
				</div>
				<div id="response_panel" class="hidden">
					<div class=command id=response></div>
				</div>
			</div>
		</div>
		<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
		<script type="text/javascript" src="js/jquery.js"></script>
		<!-- Include all compiled plugins (below), or include individual files as needed -->
		<script type="text/javascript" src="bootstrap/js/bootstrap.js"></script>
		<script type="text/javascript" src="bootstrap/js/angular.min.js"></script>
		<script type="text/javascript"
			src="bootstrap/js/angularjs-multiple-select.js"></script>
		<script type="text/javascript"
			src="bootstrap/js/bootstrap-multiselect.js"></script>
		<!-- Include history script -->
		<script type="text/javascript" src="js/history.js"></script>
		<!-- Include tools for asynchrone requests -->
		<script type="text/javascript" src="js/async.js"></script>
		<!-- Include switcher -->
		<script type="text/javascript" src="js/switcher.js"></script>
		<!-- Include DNSrecon script file -->
		<script type="text/javascript" src="js/dns_recon.js"></script>
	</section>

	<c:import url="credits.jsp"></c:import>
</body>
</html>