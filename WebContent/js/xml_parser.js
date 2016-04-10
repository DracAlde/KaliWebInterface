/**
 * 
 */


function loadXMLDoc(path, element) 
{
	var xmlhttp = new XMLHttpRequest();
	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			myFunction(xmlhttp);
		}
	};
	xmlhttp.open("GET", path, true);
	xmlhttp.send();
	
	var xmlDoc = xml.responseXML;
	var x = xmlDoc.getElementsByTagName(element).childNode[0].nodeValue;
	
	return x;
}

