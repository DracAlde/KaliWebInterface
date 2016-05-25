<%@page import="webproject.commun.Language"%>
<%@page import="webproject.commun.Constants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="nav.jsp"></c:import>
<body>

	<section>
		<div class="panel panel-default col-md-12 col-lg-12"
			style="padding: 0px;">

			<c:import url="top_page.jsp"></c:import>

			<div class="panel-footer">

				<!-- Test nav bar -->

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
							<a class="navbar-brand">TLS Sled</a>
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
						</ul>

					</div>
				</nav>

				<!-- End test nav bar -->

				<div id="form">
					<form method="POST" action="<c:url value="/tlssled" ></c:url>"
						id="form">
						<div>
							<label for="hostname" data-toggle="tooltip"
								title="<c:out value="${language.getLanguageValue('tls-sled', '/tls-sled/aide/cible')}" />"
								data-placement="right"> <c:out
									value="${language.getLanguageValue('tls-sled', '/tls-sled/options/cible')}" />
							</label>
							<div class="input-group col-lg-2 col-mg-4 col-sm-6">
								<input type="text" class="form-control" placeholder="Hostname"
									name="hostname" id="hostname" onkeyup="updateCommand()"
									onselect="updateCommand()" autofocus required> <span
									class="input-group-addon" id="basic-addon2"
									data-toggle="tooltip"
									title="<c:out value="${language.getLanguageValue('tls-sled', '/tls-sled/aide/cible')}" />"
									data-placement="bottom"> <i class="fa fa-question fa-lg"></i>
								</span>
							</div>
							<p class="error">${form.errors['hostname']}</p>

							<label for="port" data-toggle="tooltip"
								title="<c:out value="${language.getLanguageValue('tls-sled', '/tls-sled/aide/port')}" />"
								data-placement="right"> <c:out
									value="${language.getLanguageValue('tls-sled', '/tls-sled/options/port')}" />
							</label>
							<div class="input-group col-lg-2">
								<input type="number" class="form-control" placeholder="Port"
									name="port" id="port" value="443" onkeyup="updateCommand()"
									onchange="updateCommand()" required> <span
									class="input-group-addon" id="basic-addon2"
									data-toggle="tooltip"
									title="<c:out value="${language.getLanguageValue('tls-sled', '/tls-sled/aide/port')}" />"
									data-placement="bottom"> <i class="fa fa-question fa-lg"></i>
								</span>
							</div>
							<p class="error">${form.errors['port']}</p>
						</div>

						<br>

						<div class=command id=command data-toggle="tooltip"
							title="C'est la ligne qui sera envoyée dans le terminal de notre système Kali Linux"
							data-placement="bottom">> tlssled</div>

						<div style="text-align: right">
							<br>
							<button type="submit" class="btn btn-default"
								aria-label="Right Align">
								<c:out
									value="${language.getLanguageValue('tls-sled', '/tls-sled/actions/bouton-envoyer')}" />
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
		<script
			src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.js"></script>
		<!-- Include all compiled plugins (below), or include individual files as needed -->
		<script src="bootstrap/js/bootstrap.js"></script>
		<!-- Include history script -->
		<script src="js/history.js"></script>
		<!-- Include tools for asynchrone requests -->
		<script type="text/javascript" src="js/async.js"></script>
		<!-- Include switcher -->
		<script type="text/javascript" src="js/switcher.js"></script>

		<script>
			history();

			var hostname = document.getElementById('hostname');
			var port = document.getElementById('port');
			var command = document.getElementById('command');
			var form = document.getElementById('form');
			var response_btn = document.getElementById('response_btn');

			function updateCommand() {

				var hostname_txt = '';
				var port_txt = '';
				var command_string = '';

				if (hostname.value.length == 0) {
					hostname_txt = '&lt;hostname&gt;';
				} else {
					hostname_txt = hostname.value;
				}

				if (port.value.length == 0) {
					port_txt = '&lt;port&gt;';
				} else {
					port_txt = port.value;
				}

				command_string = '> tlssled ' + hostname_txt + ' ' + port_txt;
				command.innerHTML = command_string;
			}

			updateCommand();

			function request(callback) {
				var xhr = getXMLHttpRequest();

				setTimeout(function(){
					xhr.onreadystatechange = function() {
						if (xhr.readyState == 4
								&& (xhr.status == 200 || xhr.status == 0)) {
							callback(xhr.responseText);
						}
					};

					xhr.open("GET", "<c:url value='/asyncrequest?tool=tls-sled' ></c:url>", true);
					xhr.send(null);
					if(document.getElementById('response').innerHTML == ''){
						request(readData);
					}
					}, 1000);
			}

			function readData(sData) {
				// On peut maintenant traiter les données sans encombrer l'objet XHR.
				if(sData != document.getElementById('response').innerHTML){
					document.getElementById('response').innerHTML = sData;
					switcher(response_btn);
				}
			}
			
			request(readData);
			
			$(document).ready(function() {
				$('[data-toggle="tooltip"]').tooltip();
			});
		</script>
	</section>
	
	<c:import url="credits.jsp"></c:import>
</body>
</html>