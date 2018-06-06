package onscreen;

public class Automate {
	// State courant;
	public Transition[] t;

	public Automate(State e, Transition[] t) {
		// courant = e;
		this.t = t;
	}

	public void step(Entity e) {
		e.lasti = e.p.i;
		e.lastj = e.p.j;
		int i = 0;
		while ((i < t.length - 1) && (t[i].src != e.courant) && (!t[i].cond.eval(e))) {
			i++;
		}
		t[i].act.execute(e);
		e.courant = t[i].dest;
	}
}
