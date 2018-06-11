package automate;

import onscreen.Entity;

public class Turn extends Action {

	public Turn(String dir) {
		this.dir = dir.charAt(0);
	}

	public void TurnR(Entity e) {
		switch (e.dir) {
		case 'N':
			e.dir = 'E';
			break;
		case 'S':
			e.dir = 'W';
			break;
		case 'E':
			e.dir = 'S';
			break;
		default:
			e.dir = 'N';
			break;
		}
	}

	public void TurnL(Entity e) {
		switch (e.dir) {
		case 'N':
			e.dir = 'W';
			break;
		case 'S':
			e.dir = 'E';
			break;
		case 'E':
			e.dir = 'N';
			break;
		default:
			e.dir = 'S';
			break;
		}
	}

	public void TurnB(Entity e) {
		switch (e.dir) {
		case 'N':
			e.dir = 'S';
			break;
		case 'S':
			e.dir = 'N';
			break;
		case 'E':
			e.dir = 'W';
			break;
		default:
			e.dir = 'E';
			break;
		}
	}

	public void execute(Entity e) {
		switch (dir) {
		case 'R':
			TurnR(e);
			break;
		case 'L':
			TurnL(e);
			break;
		case 'B':
			TurnB(e);
			break;
		default:
			e.dir = dir;
		}
	}
}
