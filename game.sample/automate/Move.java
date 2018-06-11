package automate;

import java.awt.Color;
import ui.*;
import onscreen.*;

public class Move extends Action {

	public Move() {
		this.dir = 'F';
	}

	public Move(char dir) {
		this.dir = dir;
	}

	public Move(Model model, char dir) {
		this.dir = dir;
		this.model = model;
	}

	public Move(Model model) {
		this.model = model;
	}

	/*
	 * retourne les coordonné du point devant en fonction des coordonnées du point
	 * actuel et de la direction
	 */

	Point nextstep(Entity e) {
		Point p = new Point(e.p.i, e.p.j);
		switch (this.dir) {
		case 'S':
			p.i++;
			break;
		case 'W':
			p.j--;
			break;
		case 'E':
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
	public boolean CanIMove(Map m, int i, int j) {
		return m.isfree(i, j) || m.isbonus(i, j) || m.ismine(i, j) || m.isbullet(i, j) || m.isportail(i, j);
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
			State s = new State("1");
			Transition[] transitionsb = new Transition[2];
			Action mAction = new Move(e.dir);
			Action eAction = new Turn();
			Condition cond = new CondFree(e.m_model.m);
			Condition cond1 = new CondDefault(e.m_model.m);
			transitionsb[0] = new Transition(s, s, mAction, cond);
			transitionsb[1] = new Transition(s, s, eAction, cond1);
			Automate a = new Automate(model, s, transitionsb);
			e.aut_bonus = true;
			e.comport = a;
			e.courant = s;
			e.m_lastMove = 0L;
			break;
		}
	}

	public void CaseMine(Entity e) {
		e.updatevie(e.m_model, -3);
		System.out.println("AIE UNE MINE | VIE : " + e.vie);
	}

	public Point Teleportation(Map m, Entity e, Point p) {
		int i = (int) (Math.random() * (m.portail.NombrePortail()));
		while ((m.portail.Get(i).i == p.i) && (m.portail.Get(i).j == p.j)) { // Trouver portail different de la source
			i = (int) (Math.random() * (m.portail.NombrePortail()));
		}
		Point tmp = new Point(m.portail.Get(i)) ;
		if (CanIMove(model.m, tmp.i - 1, tmp.j)) { // Vers le haut
			tmp.i--;
			e.dir = 'N';
		} else if (CanIMove(m, tmp.i + 1, tmp.j)) { // Vers le bas
			tmp.i++;
			e.dir = 'S';
		} else if (CanIMove(m, tmp.i, tmp.j + 1)) { // Vers la droite
			tmp.j++;
			e.dir = 'E';
		} else if (CanIMove(m, tmp.i, tmp.j - 1)) { // Vers la gauche
			tmp.j--;
			e.dir = 'O';
		} else
			tmp = e.p;
		return tmp;
	}

	public void execute(Entity e) {
		this.model = e.m_model;

		if (e instanceof Tank && e.jauge_couleur > 0) {
			if (e.m_model.m.color[e.p.i][e.p.j] == 'F' || e.m_model.m.color[e.p.i][e.p.j] == 'B'
					|| e.m_model.m.color[e.p.i][e.p.j] == 'R') {
				if ((e.m_tank == Color.cyan) && (e.m_model.m.color[e.p.i][e.p.j] != 'B')) {
					e.m_model.m.color[e.p.i][e.p.j] = 'B';
					e.jauge_couleur--;
				} else if ((e.m_tank == Color.orange) && (e.m_model.m.color[e.p.i][e.p.j] != 'R')) {
					e.m_model.m.color[e.p.i][e.p.j] = 'R';
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
				if (CanIMove(model.m, p.i, p.j)) {
					if (model.m.isportail(p.i, p.j))
						p = Teleportation(model.m, e, p);
					if (model.m.isbonus(p.i, p.j))
						CaseBonus(e);
					else if (model.m.ismine(p.i, p.j))
						CaseMine(e);
					model.m.free(e.p.i, e.p.j);
					if ((e.jauge_couleur > 0)) {
						if (e.m_tank == Color.cyan) {
							model.m.color[e.p.i][e.p.j] = 'B';
						} else if (e.m_tank == Color.orange) {
							model.m.color[e.p.i][e.p.j] = 'R';
						}
					}
					e.p = p;
					model.m.insert(e);
				} else if (model.m.map[p.i][p.j].type == 'T') {
					e.updatevie(e.m_model, -1);
					model.m.map[p.i][p.j].updatevie(e.m_model, -1);
					e.opposite();
					this.dir = e.dir;
				}
			}
		} else {
			this.dir = e.dir;
			Point p = nextstep(e); // calcul nouvel coordonnées
			if (CanIMove(model.m, p.i, p.j)) {
				if (model.m.isportail(p.i, p.j))
					p = Teleportation(model.m, e, p);
				if (model.m.isbonus(p.i, p.j))
					CaseBonus(e);
				else if (model.m.ismine(p.i, p.j))
					CaseMine(e);
				model.m.free(e.p.i, e.p.j);
				e.p = p;
				model.m.insert(e);
			}

		}
	}
}
