package src.onscreen;

public class Automate {
	State courant;
	Transition[] t;
	
	void step() {
		int i=0;
		while((t[i].src != this.courant)&&(!t.c.eval())&&(i<t.length-1)) {
			i++;
		}
		t[i].a.execute();
		
    }
}
