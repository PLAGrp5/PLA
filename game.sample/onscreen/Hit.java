package onscreen;

import ui.*;

public class Hit extends Action {

	public Hit() {
	}

	public void execute(Model model, Entity e) {
		State s = new State("1");
		Transition[] transitionsb = new Transition[2];
		Action mAction = new Move(e.dir, model.m);
		Action eAction = new Explode();
		Condition cond = new CondFree(model.m);
		Condition cond1 = new CondDefault(model.m);
		transitionsb[0] = new Transition(s, s, mAction, cond);
		transitionsb[1] = new Transition(s, s, eAction, cond1);
		Automate a = new Automate(model, s, transitionsb);
		model.add(new Bullet(e, a, s));
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
