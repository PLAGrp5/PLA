package automate;

import onscreen.*;

public class And extends Condition {
	Condition left;
	Condition right;
	
	public And(Condition left, Condition right) {
		this.left = left;
		this.right = right;
	}
	
	public boolean eval(Entity e) {
		return left.eval(e) && right.eval(e);
	}
}
