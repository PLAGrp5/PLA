package onscreen;

public class Move extends Action {

	public Move() {
	}

	public Move(char dir, Map m) {
		this.dir = dir;
		this.m = m;
	}

	Point nextstep(Entity e) {
		Point p = new Point(e.p.i, e.p.j);
		switch (this.dir) {
		case 'D':
			p.i++;
			break;
		case 'L':
			p.j--;
			break;
		case 'R':
			p.j++;
			break;
		default:
			p.i--;
			break;
		}
		return p;
	}

	boolean canimove(Map m, int i, int j) {
		return m.isfree(i, j) || m.isbonus(i, j);
	}

	public void execute(Entity e) {
		if (this.dir != e.dir)
			e.turn(dir);
		else {
			Point p = nextstep(e); // calcul nouvel coordonnées
			if (canimove(m, p.i, p.j)) {
				m.free(e.p.i, e.p.j);
				e.p = p;
				m.insert(e);
			} else if (m.map[p.i][p.j].type == 'T') {
				e.opposite();
				this.dir = e.dir;
			}

		}
	}
}
