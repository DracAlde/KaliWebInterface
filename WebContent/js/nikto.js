/**
 * 
 */

history();

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


/*
 * That function permits to get back input field values
 * The parameter given is the ID of the input and the value
 * returned is the value of the input field
 */
function getInputFieldValue(fieldID)
{
	var field = document.getElementById(fieldID);

	if (field.value.length != 0)
	{
		return field.value;
	}
	else
	{
		return null;
	}
}


/*
 * That function permit to get back the value of the 
 * select multiple field
 * The ID of the select must be given as parameter
 * The returned value is the String constructed with the 
 * value field of the options fields
 */
function getSelectedValues(selectBoxName)
{
	var select = document.getElementById(selectBoxName), selected = '';

	for(i=0;i<select.length;i++)
	{
		if(select[i].selected == true)
		{						
			if(select[i].value != 'multiselect-all')
			{
				selected += select[i].value;
			}
			selected += " ";
		}
	}

	return selected;
}


/*
 * Global variable for the script
 */
var find_only = document.getElementById('find-only'),
no_lookup = document.getElementById('no-lookup'),
force_ssl = document.getElementById('force-ssl'),
single_request = document.getElementById('single-request'),
command = document.getElementById('command');

function updateCommand(){

	var command_string = '> nikto';

	if (getInputFieldValue('hostname') != null)
	{
		command_string += " -host " + getInputFieldValue('hostname');
	}
	else
	{
		command_string += " -host " + '&lt;hostname&gt;';
	}

	if (getInputFieldValue('port') != null)
	{
		command_string += " -port " + getInputFieldValue('port');
	}
	else 
	{
		command_string += " -port " + '&lt;port&gt;';
	}

	if (getInputFieldValue('vhost') != null)
	{
		command_string += " -vhost " + getInputFieldValue('vhost');
	}

	if (getInputFieldValue('timeout') != null)
	{
		command_string += " -Pause " + getInputFieldValue('timeout');
	}

	if (find_only.checked)
	{
		command_string += " " + find_only.value;
	}

	if (no_lookup.checked)
	{
		command_string += " " + no_lookup.value;
	}

	if (force_ssl.checked)
	{
		command_string += " " + force_ssl.value;
	}

	if (single_request.checked)
	{
		command_string += " " + single_request.value;
	}

	if (getSelectedValues('evasion-type') != '')
	{
		command_string += " -evasion " + getSelectedValues('evasion-type');
	}

	command.innerHTML = command_string ;
}

updateCommand();

$(document).ready(function() {
	$('[data-toggle="tooltip"]').tooltip();
});


/*
 * Create the multiselct view
 *
 */
$(document).ready(function() {
	$('#evasion-type').multiselect({
		includeSelectAllOption: true,
		enableFiltering: true,
		enableCaseInsensitiveFiltering: true
	});
}); 
