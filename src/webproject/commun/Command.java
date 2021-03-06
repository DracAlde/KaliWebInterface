package webproject.commun;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * The Command object is used to store a command line in the history
 * and allow to restore the page which create it
 * @author kilian
 *
 */

public class Command {
	
	private ToolName tool;
	private LinkedList<HashMap<String, Object>> arguments = new LinkedList<HashMap<String, Object>>();
	private boolean success;
	private String command;
	
	public Command(ToolName tool){
		this.tool = tool;
	}

	public ToolName getTool() {
		return tool;
	}

	public void setTool(ToolName tool) {
		this.tool = tool;
	}
	
	public void setStringCommand(String command)
	{
		this.command = command;
	}

//	public Map<String, Object> getArguments() {
//		return arguments;
//	}

	public void setArguments(String argName, Object value) {
		HashMap<String, Object> couple = new HashMap<String, Object>();
		couple.put(argName, value);
		arguments.add(couple);
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
//		for(Entry<String, Object> entry : arguments.entrySet()) {
//		    url += entry.getKey()+"="+entry.getValue().toString()+"&";
//		}
		
		for (int i = 0; i < arguments.size(); i++)
		{
			Map.Entry<String,Object> entry=arguments.get(i).entrySet().iterator().next();
			url += entry.getKey() + "=" + entry.getValue().toString() + "&";
		}
		
		// We cut the last "&"
		url = url.substring(0, url.length()-1);
		return url;
	}
	
	public String getStringCommmand()
	{
		return this.command;
	}
}
