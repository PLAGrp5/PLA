package automate;

import onscreen.Entity;
import onscreen.Map;
import onscreen.Sbire;

public class CondFree extends Condition {

	public CondFree(Map m) {
		this.m = m;
	}

	// retourne vrai si le deplacement est possible (la case devant est free ou un
	// bonus)
	boolean canimove(Map m, int i, int j) {
		return m.isfree(i, j) || m.isbonus(i, j) || m.ismine(i, j) || m.isbullet(i, j);
	}

	public boolean eval(Entity ent) {
		if (ent instanceof Sbire) {
			switch (ent.dir) {
				case 'N':
					return (canimove(m, ent.p.i - 1, ent.p.j));
				case 'S':
					return (canimove(m, ent.p.i + 1, ent.p.j));
				case 'W':
					return (canimove(m, ent.p.i, ent.p.j - 1));
				case 'E':
					return (canimove(m, ent.p.i, ent.p.j + 1));
				default:
					return false;
			}
		} else {
			switch (ent.dir) {
				case 'N':
					return (m.isfree(ent.p.i - 1, ent.p.j));
				case 'S':
					return (m.isfree(ent.p.i + 1, ent.p.j));
				case 'W':
					return (m.isfree(ent.p.i, ent.p.j - 1));
				case 'E':
					return (m.isfree(ent.p.i, ent.p.j + 1));
				default:
					return false;
			}
		}
	}
}
