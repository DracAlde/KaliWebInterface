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
								title="<c:out value="${language.getLanguageValue('tls-sled', '/tls-sled/desc-tool')}" />"
								data-placement="right">?</a></li>
						</ul>

					</div>
				</nav>
			<div id="form">
				<form method="POST" action="<c:url value="/theharvester" ></c:url>">
						<div class="col-lg-12 col-mg-12 col-sm-12">
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
						
						<div class="col-lg-12 col-mg-12 col-sm-12">
						<label for="domain" data-toggle="tooltip"
								title="<c:out value="${language.getLanguageValue('the-harvester', '/the-harvester/aide/domaine')}" />"
								data-placement="right"> <c:out value="${language.getLanguageValue('the-harvester', '/the-harvester/options/domaine')}" />
							</label>
						</div>
						
						<div class="col-lg-12 col-mg-12 col-sm-12">
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
								<p class="error">${form.errors['dictionary']}</p>
							</div>
						</div>
						
						<div class="input-group col-lg-4 col-mg-4 col-sm-6 btn-group">
							<span
								class="input-group-addon" id="basic-addon2"
								data-toggle="tooltip"
								title="<c:out value="${language.getLanguageValue('nikto', '/nikto/aide/evasion')}" />"
								data-placement="bottom"> <i class="fa fa-question fa-lg"></i>
							</span>
							
							<div class="btn-group">
								<button type="button" id="unselect_all" name="unselect_all" value="unselect_all" class="btn btn-danger" onclick="removeAllItems()"><span class="glyphicon glyphicon-remove"></span> ${language.getLanguageValue('the-harvester', '/the-harvester/actions/tout-supprimer')}</button>
							</div>
							
							<select name="evasion-type" id="evasion-type" onchange="updateCommand()" autofocus multiple="multiple">
									<option value="1" id="random-uri"><c:out value="${language.getLanguageValue('nikto', '/nikto/options/evasion-grp/uri')}" /></option>
									<option value="2" id="directory-self-reference"><c:out value="${language.getLanguageValue('nikto', '/nikto/options/evasion-grp/rep-autoref')}" /></option>
									<option value="3" id="premature-url-ending"><c:out value="${language.getLanguageValue('nikto', '/nikto/options/evasion-grp/url-fin')}" /></option>
									<option value="4" id="long-random-string"><c:out value="${language.getLanguageValue('nikto', '/nikto/options/evasion-grp/long-random')}" /></option>
									<option value="5" id="fake-parameter"><c:out value="${language.getLanguageValue('nikto', '/nikto/options/evasion-grp/faux-parametres')}" /></option>
									<option value="6" id="tab-request-spacer"><c:out value="${language.getLanguageValue('nikto', '/nikto/options/evasion-grp/tab')}" /></option>
									<option value="7" id="change-case"><c:out value="${language.getLanguageValue('nikto', '/nikto/options/evasion-grp/change-case')}" /></option>
									<option value="8" id="directory-separator"><c:out value="${language.getLanguageValue('nikto', '/nikto/options/evasion-grp/windows-url')}" /></option>
							</select>
						</div>

					<br>
				<div class="col-lg-12 col-mg-12 col-sm-12">
					<div 	class=command id=command 
						data-toggle="tooltip" 
						title="C'est la ligne qui sera envoyée dans le terminal de notre système Kali Linux"
						data-placement="bottom">
					> dnsrecon
					</div>
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
		<script type="text/javascript" src="js/history.js"></script>
		<!-- Include tools for asynchrone requests -->
		<script type="text/javascript" src="js/async.js"></script>
		<!-- Include switcher -->
		<script type="text/javascript" src="js/switcher.js"></script>
		
		<script type="text/javascript">
		
		/*
		 * That function permits to unselect all items selected in the 'info-source'
		 * multiselect box
		 */
		function removeAllItems(){
			$('option', $('#evasion-type')).each(function(element) {
				$(this).removeAttr('selected').prop('selected', false);
			});
			$("#evasion-type").multiselect('refresh');
			updateCommand();
		}
		
		
		$(document).ready(function() {
			$('[data-toggle="tooltip"]').tooltip();
		});


		/*
		 * Create the multiselct view
		 */
		$(document).ready(function() {
			$('#evasion-type').multiselect({
				includeSelectAllOption: true,
				enableFiltering: true,
				enableCaseInsensitiveFiltering: true
			});
		}); 

		
		
		</script>
	</section>
	
	<c:import url="credits.jsp"></c:import>
</body>
</html>