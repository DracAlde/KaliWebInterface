/**
 * Switch from the Form to the request (or from the request to the form)
 */

var response_btn = document.getElementById('response_btn');
var form_btn = document.getElementById('form_btn');
var response_panel = document.getElementById('response_panel');
var form = document.getElementById('form');

function switcher(e){
	if(form_btn == e){
		form_btn.className = 'active';
		response_btn.className = '';
		form.className = '';
		response_panel.className = 'hidden';
	}else{
		response_btn.className = 'active';
		form_btn.className = '';
		response_panel.className = '';
		form.className = 'hidden';
	}
}