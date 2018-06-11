package automate;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import onscreen.Entity;
import onscreen.Map;
import onscreen.Point;
import ui.Model;

import java.lang.Object;

public abstract class Condition {

	char dir;
	
	public Point nextstep(Entity e) {
		return e.p.nextPoint(e.dir);
	}
  
	public abstract boolean eval(Entity ent);
	
}