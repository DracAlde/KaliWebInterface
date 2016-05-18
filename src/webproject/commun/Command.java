package webproject.commun;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Command {
	
	private ToolName tool;
	private Map<String, Object> arguments = new HashMap<String, Object>();
	private boolean success;
	
	public Command(ToolName tool){
		this.tool = tool;
	}

	public ToolName getTool() {
		return tool;
	}

	public void setTool(ToolName tool) {
		this.tool = tool;
	}

	public Map<String, Object> getArguments() {
		return arguments;
	}

	public void setArguments(String argName, Object value) {
		arguments.put(argName, value);
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
	
	public String getURL(){
		String url = "";
		switch(tool){
		case Nikto:
			url += Constants.ADDR_NIKTO;
			break;
		case Zenmap:
			url += Constants.ADDR_ZENMAP;
			break;
		case TheHarvester:
			url += Constants.ADDR_THEHARVESTER;
			break;
		case Hydra:
			url += Constants.ADDR_HYDRA;
			break;
		case DnsRecon:
			url += Constants.ADDR_DNS_RECON;
			break;
		case TlsSled:
			url += Constants.ADDR_TLS_SLED;
			break;
		}
		
		url += "?";
		
		// We add the arguments in url's parameters
		for(Entry<String, Object> entry : arguments.entrySet()) {
		    url += entry.getKey()+"="+entry.getValue().toString()+"&";
		}
		
		// We cut the last "&"
		url = url.substring(0, url.length()-1);
		return url;
	}
}
