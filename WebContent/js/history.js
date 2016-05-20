/**
 * 
 */

//var search = window.location.search;
//search = search.replace('?', '');
//var couple = search.split("&");
//document.getElementById('hostname').value="test.com";
//separateKeyValue(couple);

//detectInputType(['hostname', 'port']);


function history()
{
	if (window.location.search)
		{
			var searchURL = window.location.search;
			var keyValue = parseURL(searchURL);
			//console.log(keyValue);
			detectInputType(keyValue);
		}
}


function separateKeyValue(couple)
{
	var key = [], value = [];
	for (var i = 0; i < couple.length; i++)
	{
		key[i] = couple[i].split('=')[0];
		value[i] = couple[i].split('=')[1];
	}
	return [key, value];
}


function parseURL(searchURL)
{
	searchURL = searchURL.replace('?', '');
	var couple = searchURL.split('&');
	var keyValue = separateKeyValue(couple);
	return keyValue;
}


function setTextField(id, value)
{
	document.getElementById(id).value = value;
}



function setCheckState(id, value)
{
	if (value == "on")
		{
		document.getElementById(id).checked = true;
		}
}


function setSelectState(id, value)
{
	var obj = document.getElementById(id);

	for (var i = 0; i < obj.options.length; i++)
	{    
		if (obj.options[i].value == value)
		{
			obj.options[i].selected = true; 
		}
	}

}


function detectInputType(inputArray)
{
	for (i = 0; i < inputArray[0].length; i++) 
	{
		//console.log("current input " + inputArray[0][i]);
		var currentElement = document.getElementById(inputArray[0][i]);
		if (currentElement.tagName == "INPUT") 
		{
			if (currentElement.type == "text") 
			{
				setTextField(inputArray[0][i], inputArray[1][i]);
				//console.log("text " + inputArray[0][i]);
			}
			if (currentElement.type == "number") 
			{
				setTextField(inputArray[0][i], inputArray[1][i]);
				//console.log("number " + inputArray[0][i]);
			}
			if (currentElement.type == "checkbox") 
			{
				setCheckState(inputArray[0][i], inputArray[1][i]);
				//console.log("checkbox " + inputArray[0][i]);
			}
			if (currentElement.type == "radio") 
			{
				setCheckState(inputArray[0][i], inputArray[1][i]);
				//console.log("checkbox " + inputArray[0][i]);
			}
		}   
		if (currentElement.tagName == "SELECT") 
		{
			setSelectState(inputArray[0][i], inputArray[1][i]);
			//console.log("select " + inputArray[0][i]);
		}
	}
}
			