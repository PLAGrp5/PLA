package automate;

import onscreen.*;

public class Cell extends Condition {

	public Cell(String d, String e) {
		this.dir = d.charAt(0);
		this.ent = e.charAt(0);
	}

	public boolean eval(Entity ent) {
		this.m = ent.m_model;
		Point p;
		if(dir == 'N' || dir == 'S' || dir == 'E' || dir == 'W') {
			p = ent.p.nextPoint(dir);
		}else {
			p = nextstep(ent);
		}
		
		switch (this.ent) {
			case 'T' :
				this.ent = 'T';
				break;
			case 'E' :
				this.ent = 'T';
				break;
			case 'P' :
				this.ent = 'I';
				break;
			default :
				this.ent = 'F';
				break;
		}
		return this.m.m.map[p.i][p.j].type == this.ent;
	}
}
