package onscreen;

/*
 * la classe transition sert à décrire un automate (=ensemble de transition)
 */

public class Transition {

	public State src;
	public State dest;
	public Action act;
	public Condition cond;
	
	
	public Transition(State sr, State des, Action ac, Condition con) {
		this.src = sr;
		this.dest = des;
		this.act = ac;
		this.cond = con;
	}
	
	
}
