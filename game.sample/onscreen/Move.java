package onscreen;

public class Move extends Action {

	public Move() {
	}

	public Move(char dir, Map m) {
		this.dir = dir;
		this.m = m;
	}

	public Move(Map m) {
		this.m = m;
	}

	/*
	 * retourne les coordonné du point devant en fonction des coordonnées du point
	 * actuel et de la direction
	 */

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

	// retourne vrai si le deplacement est possible (la case devant est free ou un
	// bonus)
	boolean canimove(Map m, int i, int j) {
		return m.isfree(i, j) || m.isbonus(i, j) || m.ismine(i, j) || m.isbullet(i, j);
	}

	public void caseBonus(Entity e) {
		int bonus = (int) (Math.random() * ((1) + 1));
		switch (bonus) {
		case 0:
			Vie v = new Vie();
			if (!(v.prendre(e)))
				System.out.println("Inventaire plein");
			break;
		case 1:
			Mine mine = new Mine();
			if (!(mine.prendre(e)))
				System.out.println("Inventaire plein");
		}
	}

	public void caseMine(Entity e) {
		e.vie -= 3;
		System.out.println("AIE UNE MINE | VIE : " + e.vie);
	}

	public void execute(Entity e) {
		if (!e.aut) {
			/*
			 * Convention de notre jeu: lorsque le tank n'est pas dans la bonne direction on
			 * le tourne dans la bonne direction
			 */
			if (this.dir != e.dir)
				e.turn(dir);
			// Sinon on effectue l'action move
			else {
				Point p = nextstep(e); // calcul nouvel coordonnées
				if (canimove(m, p.i, p.j)) {
					if (m.isbonus(p.i, p.j))
						caseBonus(e);
					else if (m.ismine(p.i, p.j))
						caseMine(e);
					m.free(e.p.i, e.p.j);
					e.p = p;
					m.insert(e);
				} else if (m.map[p.i][p.j].type == 'T') {
					e.opposite();
					this.dir = e.dir;
				}
			}
		} else {
			this.dir = e.dir;
			Point p = nextstep(e); // calcul nouvel coordonnées
			if (canimove(m, p.i, p.j)) {
				if (m.isbonus(p.i, p.j))
					caseBonus(e);
				else if (m.ismine(p.i, p.j))
					caseMine(e);
				m.free(e.p.i, e.p.j);
				e.p = p;
				m.insert(e);
			}

		}
	}
}
