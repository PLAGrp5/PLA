package onscreen;

public class CondFree extends Condition{
	
	public CondFree() {
	}

	public boolean eval(Entity ent) {
		switch (ent.dir) {
		case 'U': 
			return (m.isfree(ent.p.i-1, ent.p.j));
		case 'D':
			return (m.isfree(ent.p.i+1, ent.p.j));
		case 'L':
			return (m.isfree(ent.p.i, ent.p.j-1));
		case 'R':
			return (m.isfree(ent.p.i, ent.p.j+1));
		default:
			return false;
		}
	}	
}
