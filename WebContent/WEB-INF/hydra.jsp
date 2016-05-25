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
							<a class="navbar-brand">Hydra</a>
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
				<form method="POST" action="<c:url value="/hydra" ></c:url>">

					<!-- Target -->

					<div>
						<label for="target" data-toggle="tooltip"
							title="<c:out value="${language.getLanguageValue('hydra', '/hydra/aide/cible')}" />"
							data-placement="right"> <c:out
								value="${language.getLanguageValue('hydra', '/hydra/options/cible')}" />
						</label>
						<div class="input-group col-lg-2 col-mg-4 col-sm-6">
							<input type="text" class="form-control" placeholder="Target"
								name="target" id="target" onkeyup="updateCommand()"
								onselect="updateCommand()" autofocus required> <span
								class="input-group-addon" id="basic-addon2"
								data-toggle="tooltip"
								title="<c:out value="${language.getLanguageValue('hydra', '/hydra/aide/cible')}" />"
								data-placement="bottom"> <i class="fa fa-question fa-lg"></i>
							</span>
						</div>
						<p class="error">${form.errors['hostname']}</p>
					</div>
					
					<!-- Protocol -->
					
					<div>
						<label for="protocol" data-toggle="tooltip"
							title="help" />Protocol
						</label>
						<div class="input-group col-lg-2 col-mg-4 col-sm-6"">
							<select class="form-control" name="protocol" id="protocol"
								onchange="updateCommand()" autofocus>
								<option value="ftp" id="ftp" title="dictionary">FTP</option>
								<option value="http" id="http" title="dictionary">HTTP</option>
								<option value="ssh" id="http" title="dictionary">SSH</option>
							</select> <span class="input-group-addon" id="basic-addon2"
								data-toggle="tooltip"
								title="help"
								data-placement="bottom"> <i class="fa fa-question fa-lg"></i>
							</span>
						</div>
					</div>

				<!-- Account Group-->

					<fieldset class="col-sm-6 col-md-6 col-lg-6">
						<legend>
							<c:out
								value="${language.getLanguageValue('hydra', '/hydra/options/account-group/nom')}" />
						</legend>

						<!-- Radio -->

						<div class="col-sm-4 col-md-4 col-lg-4">
							<input type="radio" name="account" value="known" id="account_known" checked="checked" onclick="printAccountForm()"/> <label
								for="known"><c:out
									value="${language.getLanguageValue('hydra', '/hydra/options/account-group/compte-connu')}" /></label>
							<br> <input type="radio" name="account" value="dictionary"
								id="account_radio_dictionary" onclick="printAccountForm()"/> <label for="dictionary"><c:out
									value="${language.getLanguageValue('hydra', '/hydra/options/account-group/dictionnaire')}" /></label>
						</div>

						<!-- Nom du compte -->

						<div class="col-sm-6 col-md-6 col-lg-6" id="account_name">
							<label for="account_name_field" data-toggle="tooltip"
								title="<c:out value="${language.getLanguageValue('hydra', '/hydra/aide/cible')}" />"
								data-placement="right"> <c:out
									value="${language.getLanguageValue('hydra', '/hydra/options/account-group/nom-compte')}" />
							</label>
							<div class="input-group">
								<input type="text" class="form-control"
									placeholder="Nom du compte" name="account_name_field" id="account_name_field"
									onkeyup="updateCommand()" onselect="updateCommand()" autofocus> <span class="input-group-addon"
									id="basic-addon2" data-toggle="tooltip"
									title="<c:out value="${language.getLanguageValue('hydra', '/hydra/aide/cible')}" />"
									data-placement="bottom"> <i class="fa fa-question fa-lg"></i>
								</span>
							</div>
						</div>

						<!-- Dictionnaire -->

						<div id="account_dictionary" class='hidden'>
							<label for="account_dico_type" data-toggle="tooltip"
								title="<c:out value="${language.getLanguageValue('hydra', '/hydra/aide/dico')}" />"
								data-placement="right"> <c:out
									value="${language.getLanguageValue('hydra', '/hydra/options/dictionnaire/nom')}" />
							</label>
							<div class="input-group col-lg-6 col-mg-6 col-sm-6">
								<select class="form-control" name="account_dico_type" id="account_dico_type"
									onchange="updateCommand()" autofocus required>
									<option value="big-dico" id="big-dico" title="dictionary"><c:out
											value="${language.getLanguageValue('hydra', '/hydra/options/dictionnaire/grand-dico')}" /></option>
									<option value="med-dico" id="med-dico" title="dictionary"><c:out
											value="${language.getLanguageValue('hydra', '/hydra/options/dictionnaire/dico')}" /></option>
									<option value="small-dico" id="small-dico" title="dictionary"><c:out
											value="${language.getLanguageValue('hydra', '/hydra/options/dictionnaire/petit-dico')}" /></option>
								</select> <span class="input-group-addon" id="basic-addon2"
									data-toggle="tooltip"
									title="<c:out value="${language.getLanguageValue('hydra', '/hydra/aide/cible')}" />"
									data-placement="bottom"> <i class="fa fa-question fa-lg"></i>
								</span>
							</div>
						</div>

					</fieldset>

					<!-- Password Group -->

					<fieldset class="col-sm-6 col-md-6 col-lg-6">
						<legend>
							<c:out
								value="${language.getLanguageValue('hydra', '/hydra/options/password-group/nom')}" />
						</legend>

						<!-- Radio -->

						<div class="col-sm-4 col-md-4 col-lg-4">
							<input type="radio" name="password" value="known" id="password_known" checked="checked" onclick="printPasswordForm()"/>
							<label for="known"><c:out
									value="${language.getLanguageValue('hydra', '/hydra/options/password-group/mdp-connu')}" /></label>
							<br> <input type="radio" name="password" value="dictionary"
								id="password_radio_dictionary" onclick="printPasswordForm()"/> <label for="dictionary"><c:out
									value="${language.getLanguageValue('hydra', '/hydra/options/password-group/dictionnaire')}" /></label>
						</div>

						<!-- Password -->

						<div id="password">
							<label for="target" data-toggle="tooltip"
								title="<c:out value="${language.getLanguageValue('hydra', '/hydra/aide/cible')}" />"
								data-placement="right"> <c:out
									value="${language.getLanguageValue('hydra', '/hydra/options/password-group/mdp')}" />
							</label>
							<div class="input-group">
								<input type="text" class="form-control"
									placeholder="Mot de passe" name="password_field" id="password_field"
									onkeyup="updateCommand()" onselect="updateCommand()" autofocus> <span class="input-group-addon"
									id="basic-addon2" data-toggle="tooltip"
									title="<c:out value="${language.getLanguageValue('hydra', '/hydra/aide/cible')}" />"
									data-placement="bottom"> <i class="fa fa-question fa-lg"></i>
								</span>
							</div>
						</div>

						<!-- Dictionnaire -->

						<div id="password_dictionary" class='hidden'>
							<label for="password_dico_type" data-toggle="tooltip"
								title="<c:out value="${language.getLanguageValue('hydra', '/hydra/aide/dico')}" />"
								data-placement="right"> <c:out
									value="${language.getLanguageValue('hydra', '/hydra/options/dictionnaire/nom')}" />
							</label>
							<div class="input-group col-lg-6 col-mg-6 col-sm-6">
								<select class="form-control" name="password_dico_type" id="password_dico_type"
									onchange="updateCommand()" autofocus required>
									<option value="big-dico" id="big-dico" title="dictionary"><c:out
											value="${language.getLanguageValue('hydra', '/hydra/options/dictionnaire/grand-dico')}" /></option>
									<option value="med-dico" id="med-dico" title="dictionary"><c:out
											value="${language.getLanguageValue('hydra', '/hydra/options/dictionnaire/dico')}" /></option>
									<option value="small-dico" id="small-dico" title="dictionary"><c:out
											value="${language.getLanguageValue('hydra', '/hydra/options/dictionnaire/petit-dico')}" /></option>
								</select> <span class="input-group-addon" id="basic-addon2"
									data-toggle="tooltip"
									title="<c:out value="${language.getLanguageValue('hydra', '/hydra/aide/cible')}" />"
									data-placement="bottom"> <i class="fa fa-question fa-lg"></i>
								</span>
							</div>
						</div>
					</fieldset>

					<br><br><br><br><br><br><br>
					
					<!-- Command -->

					<div class=command id=command data-toggle="tooltip"
						title="C'est la ligne qui sera envoyée dans le terminal de notre système Kali Linux"
						data-placement="bottom">
						
						> hydra
						
					</div>

					<div style="text-align: right">
						<br>
						<button type="submit" class="btn btn-default"
							aria-label="Right Align">
							<c:out
								value="${language.getLanguageValue('hydra', '/hydra/actions/bouton-envoyer')}" />
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
			var target = document.getElementById('target'), 
				account_radio_name = document.getElementById('account_known'),
				account_radio_dico = document.getElementById('account_radio_dictionary'),
				account_name = document.getElementById('account_name'),
				account_name_field = document.getElementById('account_name_field'),
				account_dictionary = document.getElementById('account_dictionary'),
				password_radio_name = document.getElementById('password_known'),
				password_radio_dico = document.getElementById('password_radio_dictionary'),
				password = document.getElementById('password'),
				password_field = document.getElementById('password_field'),
				password_dictionary = document.getElementById('password_dictionary'),
				command = document.getElementById('command'),
				protocol = document.getElementById('protocol');

			function updateCommand() {
				var target_txt = '';
				var protocol_txt = '';
				var command_string = '';
				var account_opt = '';
				var password_opt = '';

				if (target.value.length == 0) {
					target_txt = '&lt;target&gt;';
				} else {
					target_txt = target.value;
				}
				
				if(protocol.options[protocol.selectedIndex].value == 'ftp'){
					protocol_txt = 'ftp://';
				}else if(protocol.options[protocol.selectedIndex].value == 'http'){
					protocol_txt = 'http://';
				}else if(protocol.options[protocol.selectedIndex].value == 'ssh'){
					protocol_txt = 'ssh://';
				}

				if(account_radio_name.checked){
					if (account_name_field.value.length == 0) {
						account_opt = '-l &lt;account name&gt;';
					} else {
						account_opt = '-l ' + account_name_field.value;
					}
				}else if(account_radio_dico.checked){
					account_opt = '-L [dictionary path]';
				}

				if(password_radio_name.checked){
					if (password_field.value.length == 0) {
						password_opt = '-p &lt;password&gt;';
					} else {
						password_opt = '-p ' + password_field.value;
					}
				}else if(password_radio_dico.checked){
					password_opt = '-P [dictionary path]';
				}
				
				command_string = '> hydra ' + account_opt + ' ' + password_opt + ' ' + protocol_txt + target_txt;
				command.innerHTML = command_string;
			}
			
			function printAccountForm(){
				if(account_radio_name.checked){
					account_name.className = '';
					account_dictionary.className = 'hidden';
				}else if(account_radio_dico.checked){
					account_name.className = 'hidden';
					account_dictionary.className = '';
				}
				updateCommand();
			}
			
			function printPasswordForm(){
				if(password_radio_name.checked){
					password.className = '';
					password_dictionary.className = 'hidden';
				}else if(password_radio_dico.checked){
					password.className = 'hidden';
					password_dictionary.className = '';
				}
				updateCommand();
			}
			
			printAccountForm();
			printPasswordForm();
			
			function request(callback) {
				var xhr = getXMLHttpRequest();

				setTimeout(function(){
					xhr.onreadystatechange = function() {
						if (xhr.readyState == 4
								&& (xhr.status == 200 || xhr.status == 0)) {
							callback(xhr.responseText);
						}
					};

					xhr.open("GET", "<c:url value='/asyncrequest?tool=hydra' ></c:url>", true);
					xhr.send(null);
					if(document.getElementById('response').innerHTML == ''){
						request(readData);
					}
					}, 1000);
			}

			function readData(sData) {
				// On peut maintenant traiter les données sans encombrer l'objet XHR.
				if(sData != response.innerHTML){
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