package onscreen;

public class Pop extends Action {

	public Pop(Map m) {
		this.m = m;
	}
	
	public void execute(Entity e) {
		
		if (e.jauge_couleur >= 4) {
			//modifier Map de maniere à
			//colorier e.p.(i-1,j)
			//colorier e.p.(i+1,j)
			//colorier e.p.(i,j-1)
			//colorier e.p.(i,j+1)
			e.jauge_couleur -= 4;
		}
		
		
	}
}
