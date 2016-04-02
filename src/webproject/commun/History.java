package webproject.commun;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class History {
	
	private static LinkedList<History> history = new LinkedList<History>();
	
	private ToolName tool;
	private Map<String, Object> arguments;
	
	public History(ToolName tool){
		this.tool = tool;
		arguments = new HashMap<>();
		history.add(this);
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

	public static LinkedList<History> getHistory() {
		return history;
	}
}
