<%@page import="webproject.commun.Language"%>
<%@page import="webproject.commun.Constants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="nav.jsp"></c:import>
<body>

	<% 
		Language language = new Language(); //class must be instancied each time we want to use the language parser
		language.setCurrentTool(Constants.TOOL_ZENMAP);
		Constants langSelected = new Constants();
	%>
	
	<section>
		<div class="panel panel-default col-md-12 col-lg-12" style="padding:0px;">
		  
		  <c:import url="top_page.jsp"></c:import>
		  
		  <div class="panel-footer">
			<div class="row">
			
				<h3 class=col-lg-12>
					<span	data-toggle="tooltip" 
							title="<%= language.getLanguageValue("/site/tools/$tool/desc-tool") %>"
							data-placement="right">
							
						Zenmap
						
					</span>
					
					<div class="help-picture"
						data-toggle="tooltip" 
						title="<%= language.getLanguageValue("/site/tools/$tool/desc-tool") %>"
						data-placement="left">
					</div>
				</h3>
					
				</div>
			<form method="POST" action="<c:url value="/zenmap.html" ></c:url>">
					<div>
						<label for="hostname" data-toggle="tooltip"
							title="<%=language.getLanguageValue("/site/tools/$tool/aide/cible")%>"
							data-placement="right"> <%=language.getLanguageValue("/site/tools/$tool/options/cible")%>
						</label>
						<div class="input-group col-lg-2 col-mg-4 col-sm-6">
							<input type="text" class="form-control" placeholder="Hostname"
								name="hostname" id="hostname" onkeyup="updateCommand()"
								onselect="updateCommand()" autofocus required> 
							<span
								class="input-group-addon" id="basic-addon2"
								data-toggle="tooltip"
								title="<%=language.getLanguageValue("/site/tools/$tool/aide/cible")%>"
								data-placement="bottom"> <i class="fa fa-question fa-lg"></i>
							</span>
						</div>
						<p class="error">${form.errors['hostname']}</p>
					</div>

					<div>
						<label for="scan_type" data-toggle="tooltip"
							title="<%=language.getLanguageValue("/site/tools/$tool/aide/scan-type")%>"
							data-placement="right"> <%=language.getLanguageValue("/site/tools/$tool/options/scan-type/nom")%>
						</label>
						<div class="input-group col-lg-2 col-mg-4 col-sm-6">
							<select class="form-control" name="scan_type" id="scan_type">
								<optgroup label="Scan intense">
									<option value="intense" id="intense" title="Test"><%=language.getLanguageValue("/site/tools/$tool/options/scan-type/intense")%></option>
									<option value="intense_udp" id="intense_udp" title="Test"><%=language.getLanguageValue("/site/tools/$tool/options/scan-type/intense-udp")%></option>
									<option value="intense_tcp" id="intense_tcp" title="Test"><%=language.getLanguageValue("/site/tools/$tool/options/scan-type/intense-tcp")%></option>
									<option value="intense_ping" id="intense_ping" title="Test"><%=language.getLanguageValue("/site/tools/$tool/options/scan-type/intense-ping")%></option>
								</optgroup>
								<optgroup label="Scan rapide">
									<option value="rapide" id="rapide" title="Test"><%=language.getLanguageValue("/site/tools/$tool/options/scan-type/rapide")%></option>
									<option value="rapide_plus" id="rapide_plus" title="Test"><%=language.getLanguageValue("/site/tools/$tool/options/scan-type/rapide-plus")%></option>
								</optgroup>
								<optgroup label="Scan classique">
									<option value="ping" id="ping" title="Test"><%=language.getLanguageValue("/site/tools/$tool/options/scan-type/ping")%></option>
									<option value="traceroute" id="traceroute" title="Test"><%=language.getLanguageValue("/site/tools/$tool/options/scan-type/traceroute")%></option>
									<option value="ordinaire" id="ordinaire" title="Test"><%=language.getLanguageValue("/site/tools/$tool/options/scan-type/ordinaire")%></option>
									<option value="complet" id="complet" title="Test"><%=language.getLanguageValue("/site/tools/$tool/options/scan-type/complet")%></option>
								</optgroup>
							</select>
							<span
								class="input-group-addon" id="basic-addon2"
								data-toggle="tooltip"
								title="<%=language.getLanguageValue("/site/tools/$tool/aide/cible")%>"
								data-placement="bottom"> <i class="fa fa-question fa-lg"></i>
							</span>
						</div>
					</div>

					<br>

					<div 	class=command id=command 
						data-toggle="tooltip" 
						title="C'est la ligne qui sera envoyée dans le terminal de notre système Kali Linux"
						data-placement="bottom">
					> zenmap
				</div>
				
				<div style="text-align:right">
					<br>
					<button type="submit" class="btn btn-default" aria-label="Right Align">
						<%= language.getLanguageValue("/site/tools/$tool/actions/bouton-envoyer") %>
					</button>
				</div>
				
				<div>
					<p class="${empty form.errors ? 'succes' : 'error'}">${form.result}</p>
				</div>
			</form>
		  </div>
		</div>
		
		<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.js"></script>
		<!-- Include all compiled plugins (below), or include individual files as needed -->
		<script src="bootstrap/js/bootstrap.js"></script>
		
		<script>
			var hostname = document.getElementById('hostname'),
				command = document.getElementById('command');
			
			function updateCommand(){
				
				var hostname_txt = '';
				var command_string = '';
					
				if(hostname.value.length == 0){
					hostname_txt = '&lt;hostname&gt;';
				}else{
					hostname_txt = hostname.value;
				}
				
				command_string ='> zenmap ' + hostname_txt;
				command.innerHTML = command_string ;
			}
			
			updateCommand();
			
			$(document).ready(function(){
			    $('[data-toggle="tooltip"]').tooltip();
			});
		</script>
	</section>
</body>
</html>