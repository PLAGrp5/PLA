package onscreen;

import ui.*;

public class Automate {
	// State courant;
	public Transition[] t;
	public Model model;

	public Automate(State e, Transition[] t) {
		// courant = e;
		this.t = t;
	}

	public Automate(Model model, State e, Transition[] t) {
		// courant = e;
		this.model = model;
		this.t = t;
	}

	public void step(Entity e) {
		e.lasti = e.p.i;
		e.lastj = e.p.j;
		int i = 0;
		while ((i < t.length) && !((t[i].src == e.courant) && (t[i].cond.eval(e))))
			i++;

		if (i < t.length) {
			if (t[i].act instanceof Explode)
				t[i].act.execute(e.comport.model, e);
			else {
				t[i].act.execute(e);
				e.courant = t[i].dest;
			}
		}
	}
}
