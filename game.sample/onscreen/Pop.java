package onscreen;

import java.awt.Color;

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
			if (m.color[e.p.i-1][e.p.j] == 'W' || m.color[e.p.i-1][e.p.j] == 'B' || m.color[e.p.i-1][e.p.j] == 'R') {
				if (e.m_tank == Color.cyan) {
					m.color[e.p.i-1][e.p.j] = 'B';
				}
				if (e.m_tank == Color.orange) {
					m.color[e.p.i-1][e.p.j] = 'R';
				}
			}
			if (m.color[e.p.i+1][e.p.j] == 'W' || m.color[e.p.i+1][e.p.j] == 'B' || m.color[e.p.i+1][e.p.j] == 'R') {
				if (e.m_tank == Color.cyan) {
					m.color[e.p.i+1][e.p.j] = 'B';
				}
				if (e.m_tank == Color.orange) {
					m.color[e.p.i+1][e.p.j] = 'R';
				}
			}
			if (m.color[e.p.i][e.p.j-1] == 'W' || m.color[e.p.i][e.p.j-1] == 'B' || m.color[e.p.i][e.p.j-1] == 'R') {
				if (e.m_tank == Color.cyan) {
					m.color[e.p.i][e.p.j-1] = 'B';
				}
				if (e.m_tank == Color.orange) {
					m.color[e.p.i][e.p.j-1] = 'R';
				}
			}
			if (m.color[e.p.i][e.p.j+1] == 'W' || m.color[e.p.i][e.p.j+1] == 'B' || m.color[e.p.i][e.p.j+1] == 'R') {
				if (e.m_tank == Color.cyan) {
					m.color[e.p.i][e.p.j+1] = 'B';
				}
				if (e.m_tank == Color.orange) {
					m.color[e.p.i][e.p.j+1] = 'R';
				}
			}
			e.jauge_couleur -= 4;
		}
		
		
	}
}
