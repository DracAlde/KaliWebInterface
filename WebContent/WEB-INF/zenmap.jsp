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
							<a class="navbar-brand">Zenmap</a>
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

				<div id="form">
					<form method="POST" action="<c:url value="/zenmap" ></c:url>">
						<div>
							<label for="hostname" data-toggle="tooltip"
								title="<c:out value="${language.getLanguageValue('zenmap', '/zenmap/aide/cible')}" />"
								data-placement="right"> <c:out
									value="${language.getLanguageValue('zenmap', '/zenmap/options/cible')}" />
							</label>
							<div class="input-group col-lg-2 col-mg-4 col-sm-6">
								<input type="text" class="form-control" placeholder="Hostname"
									name="hostname" id="hostname" onkeyup="updateCommand()"
									onselect="updateCommand()" autofocus required> <span
									class="input-group-addon" id="basic-addon2"
									data-toggle="tooltip"
									title="<c:out value="${language.getLanguageValue('zenmap', '/zenmap/aide/cible')}" />"
									data-placement="bottom"> <i class="fa fa-question fa-lg"></i>
								</span>
							</div>
							<p class="error">${form.errors['hostname']}</p>
						</div>


						<div>
							<label for="scan_type" data-toggle="tooltip"
								title="<c:out value="${language.getLanguageValue('zenmap', '/zenmap/aide/scan-type')}" />"
								data-placement="right"> <c:out
									value="${language.getLanguageValue('zenmap', '/zenmap/options/scan-type/nom')}" />
							</label>
							<div class="input-group col-lg-2 col-mg-4 col-sm-6">
								<select class="form-control" name="scan_type" id="scan_type"
									onchange="updateCommand()" autofocus required>
									<optgroup label="Scan intense">
										<option value="intense" id="intense" title="Test"><c:out
												value="${language.getLanguageValue('zenmap', '/zenmap/options/scan-type/intense')}" /></option>
										<option value="intense_udp" id="intense_udp" title="Test"><c:out
												value="${language.getLanguageValue('zenmap', '/zenmap/options/scan-type/intense-udp')}" /></option>
										<option value="intense_tcp" id="intense_tcp" title="Test"><c:out
												value="${language.getLanguageValue('zenmap', '/zenmap/options/scan-type/intense-tcp')}" /></option>
										<option value="intense_no_ping" id="intense_no_ping"
											title="Test"><c:out
												value="${language.getLanguageValue('zenmap', '/zenmap/options/scan-type/intense-ping')}" /></option>
									</optgroup>
									<optgroup label="Scan rapide">
										<option value="rapide" id="rapide" title="Test"><c:out
												value="${language.getLanguageValue('zenmap', '/zenmap/options/scan-type/rapide')}" /></option>
										<option value="rapide_plus" id="rapide_plus" title="Test"><c:out
												value="${language.getLanguageValue('zenmap', '/zenmap/options/scan-type/rapide-plus')}" /></option>
									</optgroup>
									<optgroup label="Scan classique">
										<option value="ping" id="ping" title="Test"><c:out
												value="${language.getLanguageValue('zenmap', '/zenmap/options/scan-type/ping')}" /></option>
										<option value="traceroute" id="traceroute" title="Test"><c:out
												value="${language.getLanguageValue('zenmap', '/zenmap/options/scan-type/traceroute')}" /></option>
										<option value="ordinaire" id="ordinaire" title="Test"><c:out
												value="${language.getLanguageValue('zenmap', '/zenmap/options/scan-type/ordinaire')}" /></option>
										<option value="complet" id="complet" title="Test"><c:out
												value="${language.getLanguageValue('zenmap', '/zenmap/options/scan-type/complet')}" /></option>
									</optgroup>
								</select> <span class="input-group-addon" id="basic-addon2"
									data-toggle="tooltip"
									title="<c:out value="${language.getLanguageValue('zenmap', '/zenmap/aide/cible')}" />"
									data-placement="bottom"> <i class="fa fa-question fa-lg"></i>
								</span>
							</div>
						</div>

						<br>

						<div class=command id=command data-toggle="tooltip"
							data-placement="bottom">> nmap</div>

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

				var hostname = document.getElementById('hostname'), scan_type = document
						.getElementById('scan_type'), command = document
						.getElementById('command');

				function updateCommand() {

					var hostname_txt = '';
					var scan_txt = '';
					var command_string = '';
					var scan_index = scan_type.selectedIndex;

					if (hostname.value.length == 0) {
						hostname_txt = '&lt;hostname&gt;';
					} else {
						hostname_txt = hostname.value;
					}

					switch (scan_index) {
					case 0:
						scan_txt = '-T4 -A -v';
						break;
					case 1:
						scan_txt = '-sS -sU T4 -A -v';
						break;
					case 2:
						scan_txt = '-p 1-65535 -T4 -A -v';
						break;
					case 3:
						scan_txt = '-T4 -A -v -Pn';
						break;
					case 4:
						scan_txt = '-T4 -F';
						break;
					case 5:
						scan_txt = '-sV -T4 -O -F --version-light';
						break;
					case 6:
						scan_txt = '-sn';
						break;
					case 7:
						scan_txt = '-sn --traceroute';
						break;
					case 8:
						scan_txt = '-v';
						break;
					case 9:
						scan_txt = 'S80,443 -PA3389 -PU4-sS -sU -T4 -A -v -PE -PP -P0125 -PY -g 53 --script "default or (discovery and safe)"';
						break;
					default:
						scan_txt = '&lt;scan_type&gt;';
					}

					command_string = '> nmap ' + hostname_txt + ' ' + scan_txt;
					command.innerHTML = command_string;
				}

				updateCommand();

				function request(callback) {
					var xhr = getXMLHttpRequest();

					setTimeout(
							function() {
								xhr.onreadystatechange = function() {
									if (xhr.readyState == 4
											&& (xhr.status == 200 || xhr.status == 0)) {
										callback(xhr.responseText);
									}
								};

								xhr
										.open(
												"GET",
												"<c:url value='/asyncrequest?tool=zenmap' ></c:url>",
												true);
								xhr.send(null);
								if (document.getElementById('response').innerHTML == '') {
									request(readData);
								}
							}, 1000);
				}

				function readData(sData) {
					// On peut maintenant traiter les données sans encombrer l'objet XHR.
					if (sData != response.innerHTML) {
						response.innerHTML = sData;
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