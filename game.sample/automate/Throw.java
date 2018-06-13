package automate;

import onscreen.*;

public class Throw extends Action{
	public void execute (Entity e) {
		e.inventaire[0].jeter(e);
	}
}