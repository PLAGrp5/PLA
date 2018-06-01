package edu.ricm3.game.sample;

public class Transition {

	public State ent;
	public State sort;
	public Action act;
	public Condition cond;
	
	Transition() {
	}
	
	Transition(State en, State so, Action ac, Condition con) {
		this.ent = en;
		this.sort = so;
		this.act = ac;
		this.cond = con;
	}
	
	
}
