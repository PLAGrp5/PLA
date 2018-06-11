package automate;

import onscreen.*;

public class CondFree extends Condition {

	public CondFree() {
	}

	public CondFree(char dir) {
		this.dir = dir;
	}

	// retourne vrai si le deplacement est possible (la case devant est free ou un
	// bonus)

	public boolean eval(Entity ent) {
		if (ent instanceof Sbire)
			return ent.canimove();
		else {
			switch (ent.dir) {
			case 'N':
				return (ent.m_model.m_Map.isfree(ent.p.i - 1, ent.p.j));
			case 'S':
				return (ent.m_model.m_Map.isfree(ent.p.i + 1, ent.p.j));
			case 'W':
				return (ent.m_model.m_Map.isfree(ent.p.i, ent.p.j - 1));
			case 'E':
				return (ent.m_model.m_Map.isfree(ent.p.i, ent.p.j + 1));
			default:
				return false;
			}
		}
	}
}
