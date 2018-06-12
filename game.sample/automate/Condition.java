package automate;

import onscreen.Entity;
import onscreen.Point;

public abstract class Condition {

	char dir;
	char ent;
	
	public Point nextstep(Entity e) {
		return e.p.nextPoint(e.dir);
	}
  
	public abstract boolean eval(Entity ent);
	
}