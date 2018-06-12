package automate;

import onscreen.*;

public class Closest extends Condition {
	public Closest(String ent, String dir) {
		this.ent = ent.charAt(0);
		this.dir = dir.charAt(0);
	}

	public boolean eval(Entity e) {
		char d = 'F';
		char en ;
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

		/*
		 * int i; for (i = 0; i < 30 && e.m_model.m_Map.map[i][e.p.j].type != this.ent;
		 * i++) ; if (i < 30) { if (i < e.p.j) d = 'N'; else if (i > e.p.j) d = 'S'; }
		 * else { int j; for (j = 0; j < 30 && e.m_model.m_Map.map[e.p.i][j].type !=
		 * this.ent; j++) ; if (j < 30) { if (j < e.p.i) d = 'O'; else if (j > e.p.i) d
		 * = 'E'; } }
		 */

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
		if(d == 'O')
			System.out.print("ok");
		return (d == this.dir);
	}
}
