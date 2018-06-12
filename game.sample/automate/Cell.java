package automate;

import onscreen.*;

public class Cell extends Condition {

	public Cell(String d, String e) {
		this.dir = d.charAt(0);
		this.ent = e.charAt(0);
	}

	public boolean eval(Entity ent) {
		Point p;
		if (dir == 'N' || dir == 'S' || dir == 'E' || dir == 'W') {
			p = ent.p.nextPoint(dir);
		} else {
			p = nextstep(ent);
		}

		switch (this.ent) {
		case 'T':
		case 'E':
			return ent.m_model.GetEntity(p).type == 'T';

		case 'P':
			return ent.m_model.GetEntity(p).type == 'I';

		case 'J':
			return ent.m_model.GetEntity(p).type == 'G';

		default:
			return ent.m_model.GetEntity(p).type == 'F';

		}
		/*
		 * if (ent instanceof Bullet && ent.p.i == 4 && ent.p.j == 13)
		 * System.out.print("ok\n"); return ent.m_model.m_Map.map[p.i][p.j].type ==
		 * this.ent;
		 */
	}
}
