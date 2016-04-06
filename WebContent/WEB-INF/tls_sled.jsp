<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="nav.jsp"></c:import>
<body onload="updateCommand()">
	<section>
		<div class="panel panel-default col-md-12 col-lg-12" style="padding:0px;">
		  <div class="panel-body">
			<ul class="nav nav-pills">
			  <li id="history_btn" role="presentation" class="active" onclick="changeHead()"><a href="#">History <span class="badge">4</span></a></li>
			  <li id="desc_btn" role="presentation" onclick="changeHead()"><a href="#">Description</a></li>
			</ul>
			<div>
				L'historique de malade dans lequel je vais faire des truc de ouf ! =)
			</div>
		  </div>
		  <div class="panel-footer">
			<div class="row">
				<h1 class=col-lg-5>TLS Sled page</h1>
			</div>
			<form method="POST" action="<c:url value="/tlssled.html" ></c:url>">
				<div>
					<label for="hostname">Hostname ou IP</label>
					<div class="input-group">
					  <input type="text" class="form-control" placeholder="Hostname" aria-describedby="basic-addon1" name="hostname" id="hostname" onkeyup="updateCommand()">
					</div>
					<p class="error">${form.errors['hostname']}</p>
					<label for="port">Port</label>
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
			
			function updateCommand(){
				var command_string ='> tlssled ' + hostname.value + ' ' + port.value;
				command.innerHTML = command_string ;
			}
			
			function changeHead(){
				
			}
		</script>
	</section>
</body>
</html>