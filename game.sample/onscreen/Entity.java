package onscreen;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/*
Classe permettant de modélier toutes les identités du jeu
 */
public class Entity {

	public Point p;
	public char dir;
	public char type;
	float m_scale;
	BufferedImage m_sprite;
	Map m_map;
	public long m_lastMove;
	public Automate comport;
	public boolean aut;
	public State courant;
	int lastj, lasti;
	Color m_tank;
	int vie;
	int vie_max;
	BonusEtMalusFixes[] inventaire = new BonusEtMalusFixes[3];

	public Entity(char type, int i, int j, char dir) {
		this.type = type;
		this.p = new Point(i, j);
		this.dir = dir;
	}

	public Entity(char type, int i, int j) {
		this.type = type;
		this.p = new Point(i, j);
	}

	public Entity(Map m, BufferedImage sprite, int x, int y, char dir, float scale) {
		m_map = m;
		m_sprite = sprite;
		p.i = y;
		p.j = x;
		this.dir = dir;
		m_scale = scale;
	}

	public void turn(char dir) {
		this.dir = dir;
	}

	public void opposite() {
		switch (this.dir) {
		case 'D':
			this.dir = 'U';
			break;
		case 'L':
			this.dir = 'R';
			break;
		case 'R':
			this.dir = 'L';
			break;
		case 'U':
			this.dir = 'D';
			break;
		default:
			this.dir = 'D';
			break;
		}
	}

	public void paint(Graphics g, char dir) {

	}

	public void setvie(int vie) {
		this.vie_max = vie;
		this.vie = vie;
	}
	
	public void initinventaire() {
		this.inventaire[0] = null;
		this.inventaire[1] = null;
		this.inventaire[2] = null;
	}

	//renvoie false si inventaire plein
	public boolean setinventaire(BonusEtMalusFixes b) {
		if (this.inventaire[0] == null)
			this.inventaire[0] = b;
		else if (this.inventaire[1] == null)
			this.inventaire[1] = b;
		else if (this.inventaire[2] == null)
			this.inventaire[2] = b;
		else
			return false;
		return true;
	}
}