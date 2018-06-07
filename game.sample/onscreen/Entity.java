package onscreen;

import ui.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/*
 * Classe permettant de modéliser toutes les identités du jeu
*/
public class Entity {

	public Point p;
	public char dir;
	public char type;
	float m_scale;
	BufferedImage m_sprite;
	Map m_map;
	public long m_lastMove;
	
	public Automate comport_bonus;
	public boolean aut_bonus;
	public State courant_bonus;
	
	public State courant;
	
	int vie;
	int vie_max;
	public BonusEtMalusFixes[] inventaire = new BonusEtMalusFixes[3];
	int jauge_couleur;
	public int lastj, lasti;
	public Color m_tank;

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

	public void hit(Model model) {
		Hit h = new Hit();
		if (h.canihit(model, this))
			new Hit().execute(model, this);
	}

	public void explode(Model model) {
		new Explode().execute(model, this);
	}

	public void turn(char dir) {
		this.dir = dir;
	}

	public void paint(Graphics g, char dir) {

	}

	public void setvie(int vie) {
		this.vie_max = vie;
		this.vie = vie;
	}

	public void initinventaire() {
		for (int i = 0; i < 3; i++)
			this.inventaire[i] = null;
	}

	public void afficherInventaire() {
		for (int i = 0; i < 3; i++)
			if (this.inventaire[i] != null)
				System.out.println(this.inventaire[i].type);
			else
				System.out.println("null");
	}

	public boolean inventaireVide() {
		return this.inventaire[0] == null;
	}
}