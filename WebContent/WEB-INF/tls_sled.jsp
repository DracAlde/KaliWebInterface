<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="nav.jsp"></c:import>
<body>
	<section>
		<div class="panel panel-default col-md-12 col-lg-12" style="padding:0px;">
		  <div class="panel-body">
			<ul class="nav nav-pills">
			  <li id="history_btn" role="presentation" class="active" onclick="changeHead(this)">
			  	<a>
			  		History 
			  		<span class="badge">
			  			<c:out value="${history['history'].size()}" />
			  			<c:if test="${empty history}">
			  				0
			  			</c:if>
			  		</span>
			  	</a>
			  </li>
			  
			  <li id="desc_btn" role="presentation" onclick="changeHead(this)">
			  	<a>
			  		Description
			  	</a>
			  </li>
			</ul>
			
			<br>
			
			<div id="history_panel">
				<div class="list-group" style="max-height: 100px; overflow: auto;">
					<c:forEach items="${history['history']}" var="command">
						<c:if test="${command.isSuccess()}">
						    <a class="list-group-item list-group-item-success">
						</c:if>
						<c:if test="${!command.isSuccess()}">
							<a class="list-group-item list-group-item-danger">
						</c:if>
							<c:out value="${command['tool']}" />
							hostname = <c:out value="${command.getArguments()['hostname']}" />
							port = <c:out value="${command.getArguments()['port']}" />
						</a>
					</c:forEach>
				</div>
			</div>
			
			<div id="desc" class="hidden">
				<div class="panel panel-default">
				  <div class="panel-body" style="max-height: 100px; overflow: auto;">
						Description de tsl sled,<br>
						quand il est apparu,<br>
						qui l'a créé,<br>
						à quoi il sert,<br>
						dans quel contexte on l'utilise ... =)<br>
						On peut aussi mettre la description dans l'info-bulle près du titre !
				  </div>
				</div>
			</div>
		  </div>
		  
		  <div class="panel-footer">
			<div class="row">
			
				<h3 class=col-lg-12>TLS Sled 
					<div class="help-picture"
						data-toggle="tooltip" 
						title="Description de tsl sled,
						quand il est apparu,
						qui l'a créé,
						à quoi il sert,
						dans quel contexte on l'utilise ... =)
						On peut aussi mettre la description dans l'onglet Description en haut de la page !"
						data-placement="left">
					</div>
				</h3>
					
				</div>
			<form method="POST" action="<c:url value="/tlssled.html" ></c:url>">
				<div>
					<label 	for="hostname" 
							data-toggle="tooltip" 
							title="Text that will be displayed when someone hover the label"
							data-placement="right">
							Hostname ou IP
					</label>
					<div class="input-group">
					  <input type="text" class="form-control" placeholder="Hostname" aria-describedby="basic-addon1" name="hostname" id="hostname" onkeyup="updateCommand()">
					</div>
					<p class="error">${form.errors['hostname']}</p>
					
					<label 	for="port"
							data-toggle="tooltip" 
							title="Text that will be displayed when someone hover the label"
							data-placement="right">
							Port
					</label>
					<div class="input-group">
						<input type="number"  class="form-control" placeholder="Port" name="port" id="port" value="443" onkeyup="updateCommand()" onchange="updateCommand()">
					</div>
					<p class="error">${form.errors['port']}</p>
				</div>
				
				<div class=command id=command >
					> tlssled
				</div>
				
				<div style="text-align:right">
					<br>
					<button type="submit" class="btn btn-default" aria-label="Right Align">
						Envoyer
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
				port = document.getElementById('port'),
				command = document.getElementById('command');
				history_btn = document.getElementById('history_btn');
				desc_btn = document.getElementById('desc_btn');
				history_panel = document.getElementById('history_panel');
				desc = document.getElementById('desc');
			
			function updateCommand(){
				var command_string ='> tlssled ' + hostname.value + ' ' + port.value;
				command.innerHTML = command_string ;
			}
			
			function changeHead(e){
				if(desc_btn == e){
					desc_btn.className = 'active';
					history_btn.className = '';
					desc.className = '';
					history_panel.className = 'hidden';
				}else{
					history_btn.className = 'active';
					desc_btn.className = '';
					history_panel.className = '';
					desc.className = 'hidden';
				}
			}
			
			updateCommand();
			
			$(document).ready(function(){
			    $('[data-toggle="tooltip"]').tooltip();
			});
		</script>
	</section>
</body>
</html>