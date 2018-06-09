package automate;

import onscreen.Entity;

public class Turn extends Action {

	public Turn() {

	}

	public void execute(Entity e) {
		switch (e.dir) {
		case 'N':
			e.dir = 'E';
			break;
		case 'E':
			e.dir = 'S';
			break;
		case 'S':
			e.dir = 'W';
			break;
		default:
			e.dir = 'N';
			break;
		}
		// e.comport_bonus.t[0].act.dir = e.dir;
	}
}
