package onscreen;

public class CondDefault extends Condition {

	public CondDefault(Map m) {
		this.m = m;
	}

	public boolean eval(Entity ent) {
		return true;
	}
}
