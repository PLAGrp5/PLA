package automate;

import onscreen.Bullet;
import onscreen.Entity;
import onscreen.Point;

public class Hit extends Action {

	public Hit() {
	}

	public boolean canihit(Entity e) {
		Point p = new Point(e.p);

		switch (e.dir) {
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
		return e.m_model.m.map[p.i][p.j].type == 'F';
	}

	public void execute(Entity e) {
		State s = new State("1");
		Transition[] transitionsb = new Transition[2];
		Action mAction = new Move(e.dir);
		Action eAction = new Explode();
		Condition cond = new CondFree(e.m_model.m);
		Condition cond1 = new CondDefault(e.m_model.m);
		transitionsb[0] = new Transition(s, s, mAction, cond);
		transitionsb[1] = new Transition(s, s, eAction, cond1);
		Automate a = new Automate(model, s, transitionsb);
		Bullet b = new Bullet(e.m_model, e.m_model.m_bullet, 1L, e, a, s);
		e.m_model.add(b);
	}
}