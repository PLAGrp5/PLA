package onscreen;

import ui.*;

public class Hit extends Action {

	public Hit() {
	}

	public void execute(Model model, Entity e) {
		model.nent++;
		if (model.nent > model.ent.length) {
			Entity[] tmp = new Entity[2 * model.nent];
			System.arraycopy(model.ent, 0, tmp, 0, model.ent.length);
			model.ent = tmp;
		}
		model.ent[model.nent - 1] = new Bullet(e);
		model.ent[model.nent - 1].comport = model.automates[0];
		model.ent[model.nent - 1].comport.t[0].act = new Move(model.ent[model.nent - 1].dir, model.m);
	}

	/*
	 * public Hit(char dir, Map m) { this.dir = dir; this.m = m; }
	 * 
	 * Point nextstep(Entity e) { Point p = new Point(e.p.i, e.p.j); switch
	 * (this.dir) { case 'D': p.i++; break; case 'L': p.j--; break; case 'R': p.j++;
	 * break; default: p.i--; break; } return p; }
	 * 
	 * boolean caniHit(Map m, int i, int j) { return m.isfree(i, j) || m.isbonus(i,
	 * j); }
	 * 
	 * public void execute(Entity e) { if (this.dir != e.dir) e.turn(dir); else {
	 * Point p = nextstep(e); // calcul nouvel coordonnées if (caniHit(m, p.i, p.j))
	 * { m.free(e.p.i, e.p.j); e.p = p; m.insert(e); } else if (m.map[p.i][p.j].type
	 * == 'T') { e.opposite(); this.dir = e.dir; } } }
	 */
}
