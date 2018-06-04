package edu.ricm3.game.sample;

public class Condition {
	String cond;
	
	Condition(String c) {
		this.cond = c;
	}
	
	boolean eval() {
		return Boolean.parseBoolean(cond);
	}
}