package onscreen;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import ui.*;
import automate.*;

/*
 * Classe permettant de modéliser toutes les identités du jeu
*/
public class Entity {

	public Model m_model;
	public Point p;
	public char dir;
	public char type;
	float m_scale;
	BufferedImage m_sprite;
	public BufferedImage[] m_sprites;
	public long m_lastMove;

	public Automate comport;
	public Automate comport_bonus;
	public boolean aut_bonus;
	public State courant_bonus;
	public State courant;
	public int nstep;
	public int maxnstep;

	public int vie;
	int vie_max;

	public boolean alive = true;
	
	public BonusEtMalus[] inventaire = new BonusEtMalus[3];
	public int jauge_couleur;
	public int lastj, lasti;
	public Color m_tank;

	public String printvie = "Vie_0";
	public String printmine = "mine_0";
	public String printsbire = "fondpanel";
	public int nbre_mine = 0;
	public int nbre_vie = 0;

	public Sbire[] sbires_allies = new Sbire[2];

	public Entity(char type) {
		this.type = type;
		p.i = 1;
		p.j = 1;
	}

	public Entity(char type, int i, int j, char dir) {
		this.type = type;
		this.p = new Point(i, j);
		this.dir = dir;
	}

	public Entity(char type, int i, int j) {
		this.type = type;
		this.p = new Point(i, j);
	}

	public Entity(Model model, BufferedImage sprite, int x, int y, char dir, float scale) {
		m_model = model;
		m_sprite = sprite;
		p.i = y;
		p.j = x;
		this.dir = dir;
		m_scale = scale;
	}

	public void opposite() {
		switch (this.dir) {
			case 'S':
				this.dir = 'N';
				break;
			case 'W':
				this.dir = 'E';
				break;
			case 'E':
				this.dir = 'W';
				break;
			default:
				this.dir = 'S';
				break;
		}
	}

	public boolean canimove() {
		Point p = new Point(this.p).nextPoint(dir);
		return m_model.m_Map.isfree(p.i, p.j) || m_model.m_Map.isbonus(p.i, p.j) || m_model.m_Map.ismine(p.i, p.j)
				|| m_model.m_Map.isbullet(p.i, p.j);
	}

	public boolean canihit() {
		Point p1 = new Point(this.p).nextPoint(dir);
		return m_model.GetEntity(p1).type == 'F';
	}

	public void pop() {
		new Pop().execute(this);
	}

	public void wizz() {
		new Wizz().execute(this);
	}

	public void hit() {
		long now = System.currentTimeMillis();
		long elapsed = now - m_lastMove;
		if (elapsed > 500L) {
			m_lastMove = now;
			if (canihit())
				new Hit().execute(this);
			else {
				Point pe = new Point(p);
				Point p = pe.nextPoint(dir);
				m_model.m_Map.map[p.i][p.j].updatevie(m_model, -1);
			}
		}
	}

	public void explode() {
		new Explode().execute(this);
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

	public void updatevie(Model model, int vie) {
		this.vie += vie;
		if (this.vie < 1) {
			this.vie = 0;
			if (type != 'W' && type != 'P')
				model.del(this);
		}
	}
}