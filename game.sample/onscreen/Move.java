package onscreen;

public class Move extends Action {

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
		return m.isfree(i, j) || m.isbonus(i, j) || m.ismine(i, j);
	}

	public void caseBonus(Entity e) {
		int bonus = (int)(Math.random() * ((1) + 1));
		switch (bonus) {
		case 0 :
			Vie v = new Vie();
			if(!(v.prendre(e)))
				System.out.println("Inventaire plein");
			break;
		case 1 :
			Mine mine = new Mine();
			if(!(mine.prendre(e)))
				System.out.println("Inventaire plein");
		}
	}
	
	public void caseMine(Entity e) {
		e.vie -= 3;
		System.out.println("AIE UNE MINE | VIE : " + e.vie);
	}

	public void execute(Entity e) {
		if (this.dir != e.dir)
			e.turn(dir);
		else {
			Point p = nextstep(e); // calcul nouvel coordonnées
			if (canimove(m, p.i, p.j)) {
				if (m.isbonus(p.i, p.j))
					caseBonus(e);
				else if(m.ismine(p.i,p.j))
					caseMine(e);
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
