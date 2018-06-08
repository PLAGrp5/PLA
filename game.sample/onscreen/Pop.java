package onscreen;

import java.awt.Color;

public class Pop extends Action {

	public Pop(Map m) {
		this.m = m;
	}
	
	public void execute(Entity e) {
		//On réalise l'action Pop seulement si jauge de couleur suffisante
		if (e.jauge_couleur >= 4) {
			
			//On colorie la croix associé à l'action Pop
			
			/*Pour chaque case à colorier:
			 * on regarde si la case est coloriable
			 * si elle l'est, on regarde si la nouvelle couleur est différente de l'actuelle
			 * On ne colorie que les cases dont la couleur changera
			 * cela consommera une recharge de peinture tout le temps 4 doses de peintures
			*/
			
			//La croix du bas
			if (m.color[e.p.i-1][e.p.j] == 'F' || m.color[e.p.i-1][e.p.j] == 'B' || m.color[e.p.i-1][e.p.j] == 'R') {
				if ((e.m_tank == Color.cyan)&&(m.color[e.p.i-1][e.p.j] != 'B')){
					m.color[e.p.i-1][e.p.j] = 'B';
				}
				else if ((e.m_tank == Color.orange)&&(m.color[e.p.i-1][e.p.j] != 'R')) {
					m.color[e.p.i-1][e.p.j] = 'R';
				}
			}
			
			//La croix du haut
			if (m.color[e.p.i+1][e.p.j] == 'F' || m.color[e.p.i+1][e.p.j] == 'B' || m.color[e.p.i+1][e.p.j] == 'R') {
				if ((e.m_tank == Color.cyan)&&(m.color[e.p.i+1][e.p.j] != 'B')){
					m.color[e.p.i+1][e.p.j] = 'B';
				}
				else if ((e.m_tank == Color.orange)&&(m.color[e.p.i+1][e.p.j] != 'R')) {
					m.color[e.p.i+1][e.p.j] = 'R';
				}
			}
			
			//La croix de gauche
			if (m.color[e.p.i][e.p.j-1] == 'F' || m.color[e.p.i][e.p.j-1] == 'B' || m.color[e.p.i][e.p.j-1] == 'R') {
				if ((e.m_tank == Color.cyan)&&(m.color[e.p.i][e.p.j-1] != 'B')){
					m.color[e.p.i][e.p.j-1] = 'B';
				}
				else if ((e.m_tank == Color.orange)&&(m.color[e.p.i][e.p.j-1] != 'R')) {
					m.color[e.p.i][e.p.j-1] = 'R';
				}
			}
			
			//La croix de droite
			if (m.color[e.p.i][e.p.j+1] == 'F' || m.color[e.p.i][e.p.j+1] == 'B' || m.color[e.p.i][e.p.j+1] == 'R') {
				if ((e.m_tank == Color.cyan)&&(m.color[e.p.i][e.p.j+1] != 'B')){
					m.color[e.p.i][e.p.j+1] = 'B';
				}
				else if ((e.m_tank == Color.orange)&&(m.color[e.p.i][e.p.j+1] != 'R')) {
					m.color[e.p.i][e.p.j+1] = 'R';
				}
			}
			e.jauge_couleur-=4;

		}
		
		
	}
}
