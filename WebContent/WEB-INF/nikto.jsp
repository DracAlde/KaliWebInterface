<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="nav.jsp"></c:import>
<link href="bootstrap/css/bootstrap-multiselect.css" media="screen"
	rel="stylesheet" type="text/css">

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
							<a class="navbar-brand">Nikto</a>
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
					<form method="POST" action="<c:url value="/nikto" ></c:url>"
						id="form">
						<div class="col-lg-12 col-md-12 col-sm-12">
							<label for="hostname" data-toggle="tooltip"
								class="col-lg-12 col-md-12 col-sm-12"
								title="<c:out value="${language.getLanguageValue('nikto', '/nikto/aide/cible')}" />"
								data-placement="right"> <c:out
									value="${language.getLanguageValue('nikto', '/nikto/options/cible')}" />
							</label>
							<div class="input-group col-lg-2 col-mg-4 col-sm-6">
								<input type="text" class="form-control" placeholder="Hostname"
									name="hostname" id="hostname" onkeyup="updateCommand()"
									onselect="updateCommand()" autofocus required> <span
									class="input-group-addon" id="basic-addon2"
									data-toggle="tooltip"
									title="<c:out value="${language.getLanguageValue('nikto', '/nikto/aide/cible')}" />"
									data-placement="bottom"> <i class="fa fa-question fa-lg"></i>
								</span>
							</div>
							<p class="error">${form.errors['hostname']}</p>

							<label for="port" data-toggle="tooltip"
								class="col-lg-12 col-md-12 col-sm-12"
								title="<c:out value="${language.getLanguageValue('nikto', '/nikto/aide/port')}" />"
								data-placement="right"> <c:out
									value="${language.getLanguageValue('nikto', '/nikto/options/port')}" />
							</label>
							<div class="input-group col-lg-2">
								<input type="number" class="form-control" placeholder="Port"
									name="port" id="port" value="80" onkeyup="updateCommand()"
									onchange="updateCommand()" required> <span
									class="input-group-addon" id="basic-addon2"
									data-toggle="tooltip"
									title="<c:out value="${language.getLanguageValue('nikto', '/nikto/aide/port')}" />"
									data-placement="bottom"> <i class="fa fa-question fa-lg"></i>
								</span>
							</div>
							<p class="error">${form.errors['port']}</p>


							<label for="help-vhost" data-toggle="tooltip"
								class="col-lg-12 col-md-12 col-sm-12"
								title="<c:out value="${language.getLanguageValue('nikto', '/nikto/aide/hote-virtuel')}" />"
								data-placement="right"> <c:out
									value="${language.getLanguageValue('nikto', '/nikto/options/hote-virtuel')}" />
							</label>
							<div class="input-group col-lg-2 col-mg-4 col-sm-6">
								<input type="text" class="form-control"
									placeholder="Virtual host" name="vhost" id="vhost"
									onkeyup="updateCommand()" onselect="updateCommand()" autofocus>
								<span class="input-group-addon" id="basic-addon2"
									data-toggle="tooltip"
									title="<c:out value="${language.getLanguageValue('nikto', '/nikto/aide/hote-virtuel')}" />"
									data-placement="bottom"> <i class="fa fa-question fa-lg"></i>
								</span>
							</div>

							<br> <label for="time-out" data-toggle="tooltip"
								class="col-lg-12 col-md-12 col-sm-12"
								title="<c:out value="${language.getLanguageValue('nikto', '/nikto/aide/timeout')}" />"
								data-placement="right"> <c:out
									value="${language.getLanguageValue('nikto', '/nikto/options/timeout')}" />
							</label>
							<div class="input-group col-lg-2">
								<input type="number" class="form-control" placeholder="Timeout"
									name="timeout" id="timeout" value="2" onkeyup="updateCommand()"
									onchange="updateCommand()" required> <span
									class="input-group-addon" id="basic-addon2"
									data-toggle="tooltip"
									title="<c:out value="${language.getLanguageValue('nikto', '/nikto/aide/timeout')}" />"
									data-placement="bottom"> <i class="fa fa-question fa-lg"></i>
								</span>
							</div>


							<br> <label for="evasion-label" data-toggle="tooltip"
								class="col-lg-12 col-md-12 col-sm-12"
								title="<c:out value="${language.getLanguageValue('nikto', '/nikto/aide/evasion')}" />"
								data-placement="right"> <c:out
									value="${language.getLanguageValue('nikto', '/nikto/options/evasion')}" />
							</label>

							<div class="input-group col-lg-4 col-mg-4 col-sm-6 btn-group">
								<span class="input-group-addon" id="basic-addon2"
									data-toggle="tooltip"
									title="<c:out value="${language.getLanguageValue('nikto', '/nikto/aide/evasion')}" />"
									data-placement="bottom"> <i class="fa fa-question fa-lg"></i>
								</span>

								<div class="btn-group">
									<button type="button" id="unselect_all" name="unselect_all"
										value="unselect_all" class="btn btn-danger"
										onclick="removeAllItems()">
										<span class="glyphicon glyphicon-remove"></span>
										${language.getLanguageValue('the-harvester', '/the-harvester/actions/tout-supprimer')}
									</button>
								</div>

								<select name="evasion-type" id="evasion-type"
									onchange="updateCommand()" autofocus multiple="multiple">
									<option value="1" id="random-uri"><c:out
											value="${language.getLanguageValue('nikto', '/nikto/options/evasion-grp/uri')}" /></option>
									<option value="2" id="directory-self-reference"><c:out
											value="${language.getLanguageValue('nikto', '/nikto/options/evasion-grp/rep-autoref')}" /></option>
									<option value="3" id="premature-url-ending"><c:out
											value="${language.getLanguageValue('nikto', '/nikto/options/evasion-grp/url-fin')}" /></option>
									<option value="4" id="long-random-string"><c:out
											value="${language.getLanguageValue('nikto', '/nikto/options/evasion-grp/long-random')}" /></option>
									<option value="5" id="fake-parameter"><c:out
											value="${language.getLanguageValue('nikto', '/nikto/options/evasion-grp/faux-parametres')}" /></option>
									<option value="6" id="tab-request-spacer"><c:out
											value="${language.getLanguageValue('nikto', '/nikto/options/evasion-grp/tab')}" /></option>
									<option value="7" id="change-case"><c:out
											value="${language.getLanguageValue('nikto', '/nikto/options/evasion-grp/change-case')}" /></option>
									<option value="8" id="directory-separator"><c:out
											value="${language.getLanguageValue('nikto', '/nikto/options/evasion-grp/windows-url')}" /></option>
								</select>
							</div>

							<br>

							<div class="col-lg-12 col-md-12 col-sm-12">

								<br> <label for="options" data-toggle="tooltip"
									class="col-lg-12 col-md-12 col-sm-12" data-placement="right">
									<c:out
										value="${language.getLanguageValue('nikto', '/nikto/options/options/nom')}" />
								</label>

								<div class="col-lg-2 col-md-2 col-sm-2">
									<label data-toggle="tooltip"
										title="<c:out value="${language.getLanguageValue('nikto', '/nikto/aide/options/find-only')}" />"
										data-placement="right" class="checkbox-inline"><input
										onclick="updateCommand()" type="checkbox" id="find-only"
										name="find-only" value="-findonly">${language.getLanguageValue('nikto', '/nikto/options/options/find-only')}</label>
								</div>

								<div class="col-lg-2 col-md-2 col-sm-2">
									<label data-toggle="tooltip"
										title="<c:out value="${language.getLanguageValue('nikto', '/nikto/aide/options/nolookup')}" />"
										data-placement="right" class="checkbox-inline"><input
										onclick="updateCommand()" type="checkbox" id="no-lookup"
										name="no-lookup" value="-nolookup">${language.getLanguageValue('nikto', '/nikto/options/options/nolookup')}</label>
								</div>

								<div class="col-lg-2 col-md-2 col-sm-2">
									<label data-toggle="tooltip"
										title="<c:out value="${language.getLanguageValue('nikto', '/nikto/aide/options/force-ssl')}" />"
										data-placement="right" class="checkbox-inline"><input
										onclick="updateCommand()" type="checkbox" id="force-ssl"
										name="force-ssl" value="-ssl">${language.getLanguageValue('nikto', '/nikto/options/options/force-ssl')}</label>
								</div>

								<div class="col-lg-2 col-md-2 col-sm-2">
									<label data-toggle="tooltip"
										title="<c:out value="${language.getLanguageValue('nikto', '/nikto/aide/options/single-request')}" />"
										data-placement="right" class="checkbox-inline"><input
										onclick="updateCommand()" type="checkbox" id="single-request"
										name="single-request" value="-Single">${language.getLanguageValue('nikto', '/nikto/options/options/single-request')}</label>
								</div>
							</div>

							<br>
							<br>
							<br>
							<br>
							<div class=command id=command data-toggle="tooltip"
								data-placement="bottom">> nikto</div>


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
		<!-- Include switcher script -->
		<script type="text/javascript" src="js/switcher.js"></script>
		<!-- Include history script -->
		<script type="text/javascript" src="js/history.js"></script>
		<!-- Include nikto script -->
		<script type="text/javascript" src="js/nikto.js"></script>

		<script>
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
											"<c:url value='/asyncrequest?tool=nikto' ></c:url>",
											true);
							xhr.send(null);
							if (document.getElementById('response').innerHTML == '') {
								request(readData);
							}
						}, 1000);
			}

			function readData(sData) {
				// On peut maintenant traiter les données sans encombrer l'objet XHR.
				if (sData != document.getElementById('response').innerHTML) {
					document.getElementById('response').innerHTML = sData;
					switcher(response_btn);
				}
			}

			request(readData);
		</script>
	</section>

	<c:import url="credits.jsp"></c:import>
</body>
</html>