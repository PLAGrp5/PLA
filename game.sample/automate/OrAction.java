package automate;

import onscreen.Entity;

public class OrAction extends Action{
	Action act1;
	Action act2;
	
	public OrAction(Action a1, Action a2) {
		act1 = a1;
		act2 = a2;
	}
	
	public void execute (Entity e) {
		int rand = (int) (Math.random() * 1);
		if(rand == 1)
			act1.execute(e);
		else
			act2.execute(e);
	}
}
