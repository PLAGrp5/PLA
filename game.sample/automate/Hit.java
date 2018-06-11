package automate;

import onscreen.Bullet;
import onscreen.Entity;
import onscreen.Point;

public class Hit extends Action {

	public Hit() {
	}

	public void execute(Entity e) {

		// Blesse l'entité en face directement
		if (!e.canihit()) {
			Point p = new Point(e.p).nextPoint(e.dir);
			e.m_model.GetEntity(p).updatevie(e.m_model, -1);
			return;
		}
		State s = new State("1");
		Transition[] transitionsb = new Transition[2];
		Action mAction = new Move();
		Action eAction = new Explode();
		Condition cond = new CondFree();
		Condition cond1 = new CondDefault();
		transitionsb[0] = new Transition(s, s, mAction, cond);
		transitionsb[1] = new Transition(s, s, eAction, cond1);
		Automate a = new Automate(s, transitionsb);
		Bullet b = new Bullet(e.m_model, e.m_model.m_bullet, 1L, e, a, s);
		e.m_model.add(b);
	}
}