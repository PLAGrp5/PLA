package automate;

import onscreen.*;

/*
 * la classe transition sert à décrire un automate (=ensemble de transition)
 */

public class Transition {

	public State dest;
	public Action act;
	public Condition cond;
	
	public Transition() {
	}
	
	public Transition(State des, Action ac, Condition con) {
		this.dest = des;
		this.act = ac;
		this.cond = con;
	}
	
	
	public boolean eval(Entity e) {
		return (this.cond.eval(e) && !(this.cond instanceof True));
	}
	
}
