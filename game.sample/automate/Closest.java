package automate;

import onscreen.*;

public class Closest extends Condition{
	public Closest(String ent, String dir) {
		this.ent = ent.charAt(0);
		this.dir = dir.charAt(0);
	}
	
	public boolean eval(Entity e) {
		char d = 'F';
		
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
			case 'J' :
				this.ent = 'G';
				break;
			default :
				this.ent = 'F';
				break;
		}
		
		for(int i = 0; i < 30; i++) {
			if(e.m_model.m_Map.map[e.p.i][i].type == this.ent) {
				if(i < e.p.j)
					d ='O';
				else if(i > e.p.j)
					d = 'E';
			}
			if(e.m_model.m_Map.map[i][e.p.j].type == this.ent) {
				if(i < e.p.i)
					d ='N';
				else if(i > e.p.i)
					d = 'S';
			}
		}
		return (d == this.dir);
	}
}
