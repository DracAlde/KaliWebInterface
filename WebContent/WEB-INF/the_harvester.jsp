<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="nav.jsp"></c:import>
<link href="bootstrap/css/bootstrap-multiselect.css" media="screen" rel="stylesheet" type="text/css">
<body>

	<section>
		<div class="panel panel-default col-md-12 col-lg-12" style="padding:0px;">
		  
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
							<a class="navbar-brand">The Harvester</a>
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
								title="<c:out value="${language.getLanguageValue('tls-sled', '/tls-sled/desc-tool')}" />"
								data-placement="right">?</a></li>
						</ul>

					</div>
				</nav>
				<div id="form">
			<form method="POST" action="<c:url value="/theharvester" ></c:url>">
					<div>
						<label for="domain" data-toggle="tooltip"
							title="<c:out value="${language.getLanguageValue('the-harvester', '/the-harvester/aide/domaine')}" />"
							data-placement="right"> <c:out value="${language.getLanguageValue('the-harvester', '/the-harvester/options/domaine')}" />
						</label>
						<div class="input-group col-lg-2 col-mg-4 col-sm-6">
							<input type="text" class="form-control" placeholder="google.com"
								name="domain" id="domain" onkeyup="updateCommand()"
								onselect="updateCommand()" autofocus required> 
							<span
								class="input-group-addon" id="basic-addon2"
								data-toggle="tooltip"
								title="<c:out value="${language.getLanguageValue('the-harvester', '/the-harvester/aide/domaine')}" />"
								data-placement="bottom"> <i class="fa fa-question fa-lg"></i>
							</span>
						</div>
						<p class="error">${form.errors['domain']}</p>
					</div>


					<div>
						<label for="info-source" data-toggle="tooltip"
							title="<c:out value="${language.getLanguageValue('the-harvester', '/the-harvester/aide/info-source')}" />"
							data-placement="right"> <c:out value="${language.getLanguageValue('the-harvester', '/the-harvester/options/info-source/nom')}" />
						</label>
						
						<div class="input-group col-lg-4 col-mg-4 col-sm-6 btn-group">
							<span
								class="input-group-addon" id="basic-addon2"
								data-toggle="tooltip"
								title="<c:out value="${language.getLanguageValue('the-harvester', '/the-harvester/aide/info-source')}" />"
								data-placement="bottom"> <i class="fa fa-question fa-lg"></i>
							</span>
							
							<div class="btn-group">
								<button type="button" id="unselect_all" name="unselect_all" value="unselect_all" class="btn btn-danger" onclick="removeAllItems()"><span class="glyphicon glyphicon-remove"></span> ${language.getLanguageValue('the-harvester', '/the-harvester/actions/tout-supprimer')}</button>
							</div>
							
							<select name="info-source" class="glyphicon" id="info-source" onchange="updateCommand()" autofocus multiple="multiple">
								<optgroup label="${language.getLanguageValue('the-harvester', '/the-harvester/options/info-source/group-search-engine')}">
									<option value="google" id="google"><c:out value="${language.getLanguageValue('the-harvester', '/the-harvester/options/info-source/google')}" /></option>
									<option value="google-cse" id="google-cse"><c:out value="${language.getLanguageValue('the-harvester', '/the-harvester/options/info-source/google-cse')}" /></option>
									<option value="bing" id="bing"><c:out value="${language.getLanguageValue('the-harvester', '/the-harvester/options/info-source/bing')}" /></option>
									<option value="bing-api" id="bing-api"><c:out value="${language.getLanguageValue('the-harvester', '/the-harvester/options/info-source/bing-api')}" /></option>
								</optgroup>
								<optgroup label="${language.getLanguageValue('the-harvester', '/the-harvester/options/info-source/group-social-network')}">
									<option value="linkedin" id="linkedin"><c:out value="${language.getLanguageValue('the-harvester', '/the-harvester/options/info-source/linkedin')}" /></option>
									<option value="google-profiles" id="google-profiles"><c:out value="${language.getLanguageValue('the-harvester', '/the-harvester/options/info-source/google-profiles')}" /></option>
									<option value="jigsaw" id="jigsaw"><c:out value="${language.getLanguageValue('the-harvester', '/the-harvester/options/info-source/jigsaw')}" /></option>
									<option value="twitter" id="twitter"><c:out value="${language.getLanguageValue('the-harvester', '/the-harvester/options/info-source/twitter')}" /></option>
									<option value="google-plus" id="google-plus"><c:out value="${language.getLanguageValue('the-harvester', '/the-harvester/options/info-source/google-plus')}" /></option>
								</optgroup>
								<optgroup label="${language.getLanguageValue('the-harvester', '/the-harvester/options/info-source/group-other')}">
									<option value="pgp" id="pgp"><c:out value="${language.getLanguageValue('the-harvester', '/the-harvester/options/info-source/pgp')}" /></option>
								</optgroup>
							</select>
						</div>

						<div>

							<br> <label for="options" data-toggle="tooltip"
								title="<c:out value="${language.getLanguageValue('the-harvester', '/the-harvester/aide/options')}" />"
								data-placement="right"> <c:out
									value="${language.getLanguageValue('the-harvester', '/the-harvester/options/options/nom')}" />
							</label>

							<div class="input-group col-lg-10 col-md-10 col-sm-10 btn-group">

								<div class="col-lg-2 col-md-2 col-sm-2">
								<label data-toggle="tooltip"
									title="<c:out value="${language.getLanguageValue('the-harvester', '/the-harvester/aide/verify')}" />"
									data-placement="right" class="checkbox-inline"><input onclick="updateCommand()"
									type="checkbox" id="verify-hostname" name="verify-hostname" value="-v">${language.getLanguageValue('the-harvester', '/the-harvester/options/options/verify')}</label>
									</div>
									
								<div class="col-lg-2 col-md-2 col-sm-2">
								<label data-toggle="tooltip"
									title="<c:out value="${language.getLanguageValue('the-harvester', '/the-harvester/aide/reverse-dns')}" />"
									data-placement="right" class="checkbox-inline"><input onclick="updateCommand()"
									type="checkbox" id="reverse-dns" name="reverse-dns" value="-n">${language.getLanguageValue('the-harvester', '/the-harvester/options/options/reverse-dns')}</label>
									</div>
									
								<div class="col-lg-2 col-md-2 col-sm-2">
								<label data-toggle="tooltip"
									title="<c:out value="${language.getLanguageValue('the-harvester', '/the-harvester/aide/tld')}" />"
									data-placement="right" class="checkbox-inline"><input onclick="updateCommand()"
									type="checkbox" id="tld-discovery" name="tld-discovery" value="-t">${language.getLanguageValue('the-harvester', '/the-harvester/options/options/tld')}</label>
									</div>
									
								<div class="col-lg-2 col-md-2 col-sm-2">
								<label data-toggle="tooltip"
									title="<c:out value="${language.getLanguageValue('the-harvester', '/the-harvester/aide/shodan')}" />"
									data-placement="right" class="checkbox-inline"><input onclick="updateCommand()"
									type="checkbox" id="shodan" name="shodan" value="-h">${language.getLanguageValue('the-harvester', '/the-harvester/options/options/shodan')}</label>
									</div>

														
								<div class="col-lg-10 col-md-10 col-sm-10">
								
								<div class="checkbox input-group">
									<label>
									<input type="checkbox" id="startN-check" name="startN-check" onclick="updateCommand()">${language.getLanguageValue('the-harvester', '/the-harvester/options/options/start')}</label>
									<div class="input-group" id="start-result" style="display: none;">
										<input type="number" class="form-control" min="0" max="10000" 
												placeholder="0" name="start-number" id="start-number"
												onkeyup="updateCommand()" onselect="updateCommand()"
												> 
												<span class="input-group-addon"
												id="basic-addon2" data-toggle="tooltip"
												title="<c:out value="${language.getLanguageValue('the-harvester', '/the-harvester/aide/start')}" />"
												data-placement="bottom"> <i
												class="fa fa-question fa-lg"></i>
											</span>
									</div>
								</div>
									
								<div class="checkbox input-group">
									<label><input type="checkbox" id="use-dns" name="use-dns" onclick="updateCommand()">${language.getLanguageValue('the-harvester', '/the-harvester/options/options/use-dns')}</label>
									<div class="input-group" id="dns-name" style="display: none;">
										<input type="text" class="form-control" placeholder="my-dns.com" 
												name="dns" id="dns"
												onkeyup="updateCommand()" onselect="updateCommand()"
												> 
												<span class="input-group-addon"
												id="basic-addon2" data-toggle="tooltip"
												title="<c:out value="${language.getLanguageValue('the-harvester', '/the-harvester/aide/use-dns')}" />"
												data-placement="bottom"> <i
												class="fa fa-question fa-lg"></i>
											</span>
									</div>
								</div>
									
								<div class="checkbox input-group">
									<label><input type="checkbox" id="fix-limit" name="fix-limit" onclick="updateCommand()">${language.getLanguageValue('the-harvester', '/the-harvester/options/options/limit')}</label>
									<div class="input-group" id="limit-number" style="display: none;">
										<input type="number" class="form-control" min="0" max="10000" 
												placeholder="100" name="max-number" id="max-number"
												onkeyup="updateCommand()" onselect="updateCommand()"
												> 
												<span class="input-group-addon"
												id="basic-addon2" data-toggle="tooltip"
												title="<c:out value="${language.getLanguageValue('the-harvester', '/the-harvester/aide/limit')}" />"
												data-placement="bottom"> <i
												class="fa fa-question fa-lg"></i>
											</span>
									</div>
								</div>
								</div>
							</div>

						</div>
					</div>

					<br>

					<div 	class=command id=command 
						data-toggle="tooltip" 
						title="C'est la ligne qui sera envoyée dans le terminal de notre système Kali Linux"
						data-placement="bottom">
					> theharvester
				</div>
				
				<div style="text-align:right">
					<br>
					<button type="submit" class="btn btn-default" aria-label="Right Align">
						<c:out value="${language.getLanguageValue('the-harvester', '/the-harvester/actions/bouton-envoyer')}" />
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
		<script type="text/javascript" src="js/jquery.js"></script>
		<!-- Include all compiled plugins (below), or include individual files as needed -->
		<script type="text/javascript" src="bootstrap/js/bootstrap.js"></script>
		<script type="text/javascript" src="bootstrap/js/angular.min.js"></script>
		<script type="text/javascript" src="bootstrap/js/angularjs-multiple-select.js"></script>
		<script type="text/javascript" src="bootstrap/js/bootstrap-multiselect.js"></script>
		<!-- Include history script -->
		<script src="js/history.js"></script>
		<!-- Include tools for asynchrone requests -->
		<script type="text/javascript" src="js/async.js"></script>
		<!-- Include switcher -->
		<script type="text/javascript" src="js/switcher.js"></script>
		<!-- Include the-harvester script -->
		<script type="text/javascript" src="js/the-harvester.js"></script>
		</div>
		
		<script type="text/javascript">

		function request(callback) {
			var xhr = getXMLHttpRequest();

			setTimeout(function(){
				xhr.onreadystatechange = function() {
					if (xhr.readyState == 4
							&& (xhr.status == 200 || xhr.status == 0)) {
						callback(xhr.responseText);
					}
				};

				xhr.open("GET", "<c:url value='/asyncrequest?tool=the-harvester' ></c:url>", true);
				xhr.send(null);
				request(readData);
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
		</script>
	</section>
</body>
</html>