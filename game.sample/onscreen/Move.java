package onscreen;

public class Move extends Action {

	public Move(char dir, Map m, Entity e) {
		this.dir = dir;
		this.m = m;
		this.e = e;
	}

	Point nextstep() {
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

	public void execute() {
		if (this.dir != e.dir)
			e.turn(dir);
		else {
			Point p = nextstep(); // calcul nouvel coordonnées
			if (canimove(m, p.i, p.j)) {
				m.free(e.p.i, e.p.j);
				e.p = p;
				m.insert(e);
			} else if (m.map[p.i][p.j].type == 'T') {
				e.opposite();
				this.dir = e.dir;
			} else if (m.map[p.i][p.j].type == 'W') {
				switch (this.dir) {
					case 'D':
            this.dir = 'L';
            break;
        case 'L':
            this.dir = 'U';
            break;
        case 'R':
            this.dir = 'D';
            break;
        case 'U':
          this.dir = 'R';
          break;
        default:
            this.dir = 'D';
            break;
				}
			}
		}
	}
}
