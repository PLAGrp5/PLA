package automate;

import onscreen.Entity;

public class Turn extends Action{

	public Turn() {
		
	}
	
	public void execute(Entity e) {
		switch (e.dir) {
			case 'U' : 
				e.dir = 'R';
				break;
			case 'R' : 
				e.dir = 'D';
				break;
			case 'D' : 
				e.dir = 'L';
				break;
			case 'L' : 
				e.dir = 'U';
				break;
			default : 
				return;
		}
		//e.comport_bonus.t[0].act.dir = e.dir;
	}
}
