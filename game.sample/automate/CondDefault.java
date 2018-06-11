package automate;

import onscreen.Entity;
import onscreen.Map;
import ui.Model;

public class CondDefault extends Condition {

	public CondDefault(Model m) {
		this.m = m;
	}
	
	public CondDefault() {
		
	}

	public boolean eval(Entity ent) {
		this.m = ent.m_model;
		return true;
	}
}
