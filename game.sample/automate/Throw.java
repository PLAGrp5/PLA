package automate;

import onscreen.*;

public class Throw extends Action{
	
	public Throw() {
		
	}
	
	public void execute (Entity e) {
		if(e.inventaire.length <=0 )
			e.inventaire[0].jeter(e);
			
	}
	
}