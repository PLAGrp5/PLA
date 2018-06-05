package onscreen;

public class Automate {
	//State courant;
	Transition[] t;
	
	public Automate(State e, Transition t) {
		//courant = e;
		this.t = new Transition[4];
		this.t[0] = t;
	}
	
	public void step(Entity e) {
		int i=0;
		while((i<t.length-1)&&(t[i].src != e.courant)&&(!t[i].cond.eval())) {
			i++;
		}
		t[i].act.execute(e);
		e.courant = t[i].dest;
    }
}
