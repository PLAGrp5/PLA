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
		Bullet b = new Bullet(model.m_bullet, 1L, e, a, s);
		/* if (model.m.map[b.p.i][b.p.j].type == 'F') */
		model.add(b);
	}
}