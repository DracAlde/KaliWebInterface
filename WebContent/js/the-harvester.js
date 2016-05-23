
history();

/*
 * That function permits to unselect all items selected in the 'info-source'
 * multiselect box
 */
function removeAllItems(){
	$('option', $('#info-source')).each(function(element) {
		$(this).removeAttr('selected').prop('selected', false);
	});
	$("#info-source").multiselect('refresh');
	updateCommand();
}

/*
 * Print the input associated to the below checkboxes
 * 
 */
$('#startN-check').click(function() {
	$("#start-result").toggle(this.checked);
});


$('#use-dns').click(function() {
	$("#dns-name").toggle(this.checked);
});


$('#fix-limit').click(function() {
	$("#limit-number").toggle(this.checked);
});

function toogleCheckboxes()
{
	if (document.getElementById('startN-check').checked)
	{
		$("#start-result").toggle(this.checked);
	}
	if (document.getElementById('use-dns').checked)
	{
		$("#dns-name").toggle(this.checked);
	}
	if (document.getElementById('fix-limit').checked)
	{
		$("#limit-number").toggle(this.checked);
	}
}


var domain = document.getElementById('domain'),
scan_type = document.getElementById('info-source'),
verify_hostname = document.getElementById('verify-hostname'),
reverse_dns = document.getElementById('reverse-dns'),
tld_discovery = document.getElementById('tld-discovery'),
shodan = document.getElementById('shodan'),
start_number = document.getElementById('start-number'),
dns_name = document.getElementById('dns'),
max_number = document.getElementById('max-number'),
command = document.getElementById('command');


function getLimit()
{
	var max_number_txt = '';

	if (document.getElementById("fix-limit").checked)
	{
		if (max_number.value.length == 0)
		{
			max_number_txt = '-l ' + '&lt;limit&gt;';
		}
		else
		{
			max_number_txt = '-l ' + max_number.value;
		}
	}

	return max_number_txt;
}



function getDNSName()
{
	var dns_name_txt = '';

	if (document.getElementById("use-dns").checked)
	{
		if (dns_name.value.length == 0)
		{
			dns_name_txt = '-e ' + '&lt;dns&gt;';
		}
		else
		{
			dns_name_txt = '-e ' + dns_name.value;
		}
	}

	return dns_name_txt;
}


function getStartNumber()
{
	var start_number_txt = '';

	if (document.getElementById("startN-check").checked)
	{
		if (start_number.value.length == 0)
		{
			start_number_txt = '-s ' + '&lt;start&gt;';
		}
		else
		{
			start_number_txt = '-s ' + start_number.value;
		}
	}

	return start_number_txt;
}


function getDomain()
{
	var domain_txt = '';

	if(domain.value.length == 0){
		domain_txt = '&lt;domain&gt;';
	}else{
		domain_txt = domain.value;
	}

	return domain_txt;
}


function getInformationSource()
{
	var info_source_txt = '-b &lt;source&gt;';

	for(i=0;i<scan_type.length;i++)
	{
		if(scan_type[i].selected == true)
		{
			if (info_source_txt == '-b &lt;source&gt;')
			{
				info_source_txt = '-b ';
			}

			if(scan_type[i].value != 'multiselect-all')
			{
				info_source_txt += scan_type[i].value;
			}
			info_source_txt += " ";
		}
	}

	return info_source_txt;
}

function updateCommand(){

	var domain_txt = '';
	var scan_type_txt = '';
	var verify_hostname_txt = '';
	var reverse_dns_txt = '';
	var tld_discovery_txt = '';
	var shodan_txt = '';
	var dns_name_txt = '';
	var start_number_txt = '';
	var max_number_txt = '';

	var command_string = '';

	console.log(getStartNumber());

	domain_txt = getDomain();
	info_source_txt =  getInformationSource();
	dns_name_txt = getDNSName();
	start_number_txt = getStartNumber();
	max_number_txt = getLimit();

	if (verify_hostname.checked)
	{
		verify_hostname_txt = verify_hostname.value;
	}

	if (reverse_dns.checked)
	{
		reverse_dns_txt = reverse_dns.value;
	}

	if (tld_discovery.checked)
	{
		tld_discovery_txt = tld_discovery.value;
	}

	if (shodan.checked)
	{
		shodan_txt = shodan.value;
	}

	command_string ='> theharvester ' + domain_txt + ' ' + info_source_txt + ' ' + verify_hostname_txt + ' ' + reverse_dns_txt + ' ' + tld_discovery_txt + ' ' + shodan_txt + ' ' + dns_name_txt + ' ' + start_number_txt + ' ' + max_number_txt;
	command.innerHTML = command_string ;
}

updateCommand();

toogleCheckboxes();

$(document).ready(function() {
	$('[data-toggle="tooltip"]').tooltip();
});


/*
 * Create the multiselct view
 *
 */
$(document).ready(function() {
	$('#info-source').multiselect({
		includeSelectAllOption: true,
		enableFiltering: true,
		enableCaseInsensitiveFiltering: true
	});
});

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