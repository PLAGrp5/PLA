package automate;

import java.awt.Color;
import onscreen.*;

public class Move extends Action {

	public Move() {
	}

	public Move(char dir) {
		this.dir = dir;
	}

	/*
	 * public Move(Model model, char dir) { this.dir = dir; this.model = model; }
	 * 
	 * public Move(Model model) { this.model = model; }
	 */

	/*
	 * retourne les coordonné du point devant en fonction des coordonnées du point
	 * actuel et de la direction
	 */

	Point nextstep(Entity e) {
		return e.p.nextPoint(e.dir);
	}

	// retourne vrai si le deplacement est possible (la case devant est free ou un
	// bonus)

	public boolean CanIMove(Entity e, int i, int j) {
		boolean b = e.m_model.m_Map.isfree(i, j) || e.m_model.m_Map.isbonus(i, j) || e.m_model.m_Map.ismine(i, j)
				|| e.m_model.m_Map.isbullet(i, j) || e.m_model.m_Map.isportail(i, j);
		if (e instanceof Sbire && e.m_model.m_Map.color[i][j] != 'F' && e.m_model.m_Map.color[i][j] != 'W')
			b = b && (e.m_model.m_Map.color[i][j] == 'B' && e.m_tank == Color.cyan)
					|| (e.m_model.m_Map.color[i][j] == 'R' && e.m_tank == Color.orange);
		return b;
	}

	public void CaseBonus(Entity e) {

		int bonus = (int) (Math.random() * 3);
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
			break;
		case 2:
			e.aut_bonus = true;
			e.comport_bonus = e.m_model.automates[1];
			e.courant_bonus = e.courant;
			e.courant = e.m_model.automates[1].b[0].src;
			e.m_lastMove = 0L;
			break;
		}

	}

	public void CaseMine(Entity e) {
		e.updatevie(e.m_model, -3);
		System.out.println("AIE UNE MINE | VIE : " + e.vie);
	}

	public Point Teleportation(Entity e, Point p) {

		int i = (int) (Math.random() * (e.m_model.m_Map.NombrePortails));
		// Trouver portail different de la source
		while ((e.m_model.m_Map.GateList.get(i).p.i == p.i) && (e.m_model.m_Map.GateList.get(i).p.j == p.j)) {
			i = (int) (Math.random() * (e.m_model.m_Map.NombrePortails));
		}
		Point tmp = new Point(e.m_model.m_Map.GateList.get(i).p);
		if ((tmp.i - 1 >= 0) && !(e.m_model.m_Map.isportail(tmp.i - 1, tmp.j)) && (CanIMove(e, tmp.i - 1, tmp.j))) { // Vers
																														// le
																														// haut
			tmp.i--;
			e.dir = 'N';
		} else if ((tmp.i + 1 < e.m_model.m_Map.n) && !(e.m_model.m_Map.isportail(tmp.i + 1, tmp.j))
				&& (CanIMove(e, tmp.i + 1, tmp.j))) { // Vers le bas
			tmp.i++;
			e.dir = 'S';
		} else if ((tmp.j + 1 < e.m_model.m_Map.n) && !(e.m_model.m_Map.isportail(tmp.i, tmp.j + 1))
				&& (CanIMove(e, tmp.i, tmp.j + 1))) { // Vers la droite
			tmp.j++;
			e.dir = 'E';
		} else if ((tmp.i - 1 >= 0) && !(e.m_model.m_Map.isportail(tmp.i, tmp.j - 1))
				&& (CanIMove(e, tmp.i, tmp.j - 1))) { // Vers la gauche
			tmp.j--;
			e.dir = 'O';
		} else
			tmp = e.p;
		return tmp;
	}

	public void execute(Entity e) {

		if (e instanceof Tank && e.jauge_couleur > 0) {
			if (e.m_model.m_Map.color[e.p.i][e.p.j] == 'F' || e.m_model.m_Map.color[e.p.i][e.p.j] == 'B'
					|| e.m_model.m_Map.color[e.p.i][e.p.j] == 'R') {
				if ((e.m_tank == Color.cyan) && (e.m_model.m_Map.color[e.p.i][e.p.j] != 'B')) {
					e.m_model.m_Map.color[e.p.i][e.p.j] = 'B';
					e.jauge_couleur--;
				} else if ((e.m_tank == Color.orange) && (e.m_model.m_Map.color[e.p.i][e.p.j] != 'R')) {
					e.m_model.m_Map.color[e.p.i][e.p.j] = 'R';
					e.jauge_couleur--;
				}
			}
		}

		if (e instanceof Tank && !e.aut_bonus) {
			/*
			 * Convention de notre jeu: lorsque le tank n'est pas dans la bonne direction on
			 * le tourne dans la bonne direction
			 */
			if (this.dir != e.dir)
				e.turn(dir);
			// Sinon on effectue l'action move
			else {
				Point p = nextstep(e); // calcul nouvel coordonnées
				if (CanIMove(e, p.i, p.j)) {
					if (e.m_model.m_Map.isportail(p.i, p.j))
						p = Teleportation(e, p);
					if (e.m_model.m_Map.isbonus(p.i, p.j))
						CaseBonus(e);
					else if (e.m_model.m_Map.ismine(p.i, p.j))
						CaseMine(e);
					e.m_model.m_Map.free(e.p.i, e.p.j);

					if ((e.jauge_couleur > 0)) {
						if (e.m_tank == Color.cyan) {
							e.m_model.m_Map.color[e.p.i][e.p.j] = 'B';
						} else if (e.m_tank == Color.orange) {
							e.m_model.m_Map.color[e.p.i][e.p.j] = 'R';
						}
					}
					e.p = p;
					e.m_model.m_Map.insert(e);
				} else if (e.m_model.m_Map.map[p.i][p.j].type == 'T') {
					e.updatevie(e.m_model, -1);
					e.m_model.m_Map.map[p.i][p.j].updatevie(e.m_model, -1);
					e.opposite();
					this.dir = e.dir;
				}
			}
		} else {
			this.dir = e.dir;
			Point p = nextstep(e); // calcul nouvel coordonnées
			if (CanIMove(e, p.i, p.j)) {
				if (e.m_model.m_Map.isportail(p.i, p.j))
					p = Teleportation(e, p);
				if (e.m_model.m_Map.isbonus(p.i, p.j))
					CaseBonus(e);
				else if (e.m_model.m_Map.ismine(p.i, p.j))
					CaseMine(e);
				e.m_model.m_Map.free(e.p.i, e.p.j);
				e.p = p;
				e.m_model.m_Map.insert(e);
			}

		}
	}
}
