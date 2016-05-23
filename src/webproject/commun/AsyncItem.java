package webproject.commun;

public class AsyncItem {
	
	private String sessionID;
	private String tool;
	private String response;
	
	public AsyncItem(){}
	
	public AsyncItem(String sessionID, String tool, String response){
		this.sessionID = sessionID;
		this.tool = tool;
		this.response = response;
	}

	public String getSessionID() {
		return sessionID;
	}

	public void setSessionID(String sessionID) {
		this.sessionID = sessionID;
	}

	public String getTool() {
		return tool;
	}

	public void setTool(String tool) {
		this.tool = tool;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}
	
}