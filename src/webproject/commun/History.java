package webproject.commun;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * An object hystory is stored in each session to save command history
 * @author kilian
 *
 */

public class History {
	
	private LinkedList<Command> history = new LinkedList<Command>();
	public HashMap<String, String> responses = new HashMap<String, String>();

	
	public History(){}

	public LinkedList<Command> getHistory() {
		return history;
	}
	
	public void addCommand(Command command){
		history.add(command);
	}
	
	public void addResponse(String tool, String response){
		responses.put(tool, response);
	}
	
	public String getResponse(String tool){
		return responses.get(tool);
	}
}
