package automate;

import onscreen.*;

public class Closest extends Condition {
	public Closest(String ent, String dir) {
		this.ent = ent.charAt(0);
		this.dir = dir.charAt(0);
	}

	public boolean eval(Entity e) {
		char d = 'F';
		char en;
		switch (this.ent) {
			case 'T':
				en = 'T';
				break;
			case 'E':
				en = 'T';
				break;
			case 'P':
				en = 'I';
				break;
			case 'J':
				en = 'G';
				break;
			default:
				en = 'F';
				break;
		}

		for (int i = 0; i < 30; i++) {
			if (e.m_model.m_Map.map[i][e.p.j].type == en) {
				if (i < e.p.i)
					d = 'N';
				else if (i > e.p.i)
					d = 'S';
			} else if (e.m_model.m_Map.map[e.p.i][i].type == en) {
				if (i < e.p.j)
					d = 'O';
				else if (i > e.p.j)
					d = 'E';
			}
		}
		
		if (this.dir == 'N' || this.dir == 'S' || this.dir == 'O' || this.dir == 'E') {
			return (d == this.dir);
		} else {
			return (d == e.dir);
		}
	}
}
