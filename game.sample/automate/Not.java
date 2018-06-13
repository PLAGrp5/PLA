package automate;

import onscreen.*;

public class Not extends Condition {
	Condition cond;
	public Not(Condition cond) {
		this.cond = cond;
	}
	
	public boolean eval(Entity e) {
		return !cond.eval(e);
	}
}
