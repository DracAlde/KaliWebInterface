
$(document).ready(function() {
	$('[data-toggle="tooltip"]').tooltip();
});


/*
 * Create the multiselct view
 */
$(document).ready(function() {
	$('#enum-type').multiselect({
		includeSelectAllOption: true,
		enableFiltering: true,
		enableCaseInsensitiveFiltering: true
	});
}); 

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
		}
	}

	return selected;
}


var myswitch = document.getElementById('myswitch'),
enum_type = document.getElementById('enum-type'),
enumeration = document.getElementById('enumeration');

function updateCommand()
{
	var command_string = "> dnsrecon -d",
	domain_txt = getInputFieldValue('domain'),
	selected_value_txt = getSelectedValues('enum-type');


	if(domain_txt != null)
	{
		command_string += ' ' + domain_txt;
	}
	else
	{
		command_string += ' &lt;domain&gt;';
	}
	if(myswitch.checked)
	{
		enumeration.style.visibility = 'initial';
		if(selected_value_txt != "" && (selected_value_txt == 'brt' || selected_value_txt == 'snoop'))
		{
			command_string += ' -D /usr/share/dnsrecon/namelist.txt' + ' -t ' + selected_value_txt;
		}
		else if(selected_value_txt != "")
		{
			command_string += ' -t ' + selected_value_txt;
		}
	}
	else
	{
		enumeration.style.visibility = 'hidden';
		command_string += ' -a';
	}

	command.innerHTML = command_string;

}

updateCommand();
