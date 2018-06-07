package onscreen;

import ui.*;

public class Hit extends Action {

	public Hit() {
	}

	public boolean canihit(Model model, Entity e) {
		Point p = new Point(e.p);

		switch (e.dir) {
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
		return model.m.map[p.i][p.j].type == 'F';
	}

	public void execute(Model model, Entity e) {
		State s = new State("1");
		Transition[] transitionsb = new Transition[2];
		Action mAction = new Move();
		Action eAction = new Explode();
		Condition cond = new CondFree(model.m);
		Condition cond1 = new CondDefault(model.m);
		transitionsb[0] = new Transition(s, s, mAction, cond);
		transitionsb[1] = new Transition(s, s, eAction, cond1);
		Automate a = new Automate(model, s, transitionsb);
		Bullet b = new Bullet(model.m, model.m_bullet, 1L, e, a, s);
		model.add(b);
	}
}