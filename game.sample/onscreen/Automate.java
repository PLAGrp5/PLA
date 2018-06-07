package onscreen;

public class Automate {
	// State courant;
	Transition[] t;

	public Automate(State e, Transition[] t) {
		// courant = e;
		this.t = new Transition[4];
		this.t = t;
	}

	public void step(Entity e) {
		e.lasti = e.p.i;
		e.lastj = e.p.j;
		int i = 0;
		while ((i < t.length) && ((t[i].src != e.courant) || (!t[i].cond.eval(e)))) {
			i++;
		}
		if (i < t.length) {
			t[i].act.execute(e);
			e.courant = t[i].dest;
		}
	}
}
