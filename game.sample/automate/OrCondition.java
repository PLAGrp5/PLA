package automate;

import onscreen.*;

public class OrCondition extends Condition {
	Condition left;
	Condition right;
	
	public OrCondition(Condition left, Condition right) {
		this.left = left;
		this.right = right;
	}
	
	public boolean eval(Entity e) {
		return left.eval(e) || right.eval(e);
	}
}
