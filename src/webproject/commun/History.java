package webproject.commun;

import java.util.LinkedList;

public class History {
	
	private LinkedList<Command> history = new LinkedList<Command>();

	
	public History(){}

	public LinkedList<Command> getHistory() {
		return history;
	}
	
	public void addCommand(Command command){
		history.add(command);
	}
}
