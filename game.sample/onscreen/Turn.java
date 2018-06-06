package onscreen;

public class Turn extends Action{

	public Turn() {
		
	}
	
	public void execute(Entity e) {
		switch (e.dir) {
			case 'U' : 
				this.dir = 'R';
				break;
			case 'R' : 
				this.dir = 'D';
				break;
			case 'D' : 
				this.dir = 'L';
				break;
			case 'L' : 
				this.dir = 'U';
				break;
			default : 
				return;
		}
	}
}
