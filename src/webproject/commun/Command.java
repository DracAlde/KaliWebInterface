package webproject.commun;

import java.util.HashMap;
import java.util.Map;

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
}
