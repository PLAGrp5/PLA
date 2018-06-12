package automate;

import onscreen.Entity;
import onscreen.Point;

public abstract class Condition {

	char dir;
	char ent;
	
	public Point nextstep(Entity e) {
		Point p = new Point(e.p.i, e.p.j);
		if(this.dir == 'F') {
			return p.nextPoint(e.dir);
		}else if(this.dir == 'B') {
			switch (dir) {
				case 'N' :
					return e.p.nextPoint('S');
				case 'E' : 
					return e.p.nextPoint('O');
				case 'S' : 
					return e.p.nextPoint('N');
				default : 
					return e.p.nextPoint('E');
			}
		}else if(this.dir == 'R') {
			switch (e.dir) {
				case 'N' :
					return e.p.nextPoint('E');
				case 'E' : 
					return e.p.nextPoint('S');
				case 'S' : 
					return e.p.nextPoint('O');
				default : 
					return e.p.nextPoint('N');
			}
		}else {
			switch (e.dir) {
				case 'N' :
					return e.p.nextPoint('N');
				case 'E' : 
					return e.p.nextPoint('S');
				case 'S' : 
					return e.p.nextPoint('E');
				default : 
					return e.p.nextPoint('O');
			}
		}
	}
  
	public abstract boolean eval(Entity ent);
	
}