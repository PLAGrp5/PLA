package onscreen;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import automate.*;
import ui.*;

public class Sbire extends Entity {

	public Sbire(int i, int j, char dir) {
		super('T', i, j, dir);
	}

	public Sbire(Model model, BufferedImage sprite, BufferedImage sprite_hit, int i, int j, char dir, float scale, int dose_couleur,
			Color color) {
		super('T', i, j, dir);
		m_model = model;
		alive = true;
		m_sprite = sprite;
		m_sprite_Hit = sprite_hit;
		m_scale = scale;
		lastj = p.j;
		lasti = p.i;
		model.m_Map.insert(this);
		m_tank = color;
		jauge_couleur = dose_couleur;
		maxnstep = 15;
		splitSbiresSprite();
		splitSbiresSprite_Hit();
		setvie(3);
		initinventaire();
	}

	public Sbire(Model model, BufferedImage sprite, BufferedImage sprite_hit, int i, int j, char dir, float scale, int dose_couleur, Color color,
			Automate a, State s) {
		super('T', i, j, dir);
		m_model = model;
		m_sprite = sprite;
		m_sprite_Hit = sprite_hit;
		m_scale = scale;
		alive = true;
		lastj = p.j;
		lasti = p.i;
		model.m_Map.insert(this);
		m_tank = color;
		jauge_couleur = dose_couleur;
		maxnstep = 15;
		splitSbiresSprite();
		splitSbiresSprite_Hit();
		this.setvie(3);
		alive = true;
		this.initinventaire();
	}

	// Récuperation des différente images du sbire dans un tableau à partir des
	// sprites
	void splitSbiresSprite() {
		m_sprites = new BufferedImage[4];
		for (int j = 0; j < 4; j++) {
			int x = j * 32;
			int y = 0;
			m_sprites[j] = m_sprite.getSubimage(x, y, 32, 32);
		}
	}
	
	void splitSbiresSprite_Hit() {
		m_sprites_Hit = new BufferedImage[4];
		for (int j = 0; j < 4; j++) {
			int x = j * 32;
			int y = 0;
			m_sprites_Hit[j] = m_sprite_Hit.getSubimage(x, y, 32, 32);
		}
	}

	// Affichage d'un sbire
	public void paint(Graphics g, char dir) {
		if (alive && !isHit) {

			Image img;
			switch (dir) {
			case 'N':
				img = m_sprites[1];
				break;
			case 'S':
				img = m_sprites[3];
				break;
			case 'E':
				img = m_sprites[2];
				break;
			default:
				img = m_sprites[0];
			}
			int w = (int) (m_scale * 32);
			int h = (int) (m_scale * 32);

			g.drawImage(img, p.j * 32, p.i * 32, w, h, null);
		}
		else if (alive && isHit) {
			Image img;
			switch (dir) {
			case 'N':
				img = m_sprites_Hit[1];
				break;
			case 'S':
				img = m_sprites_Hit[3];
				break;
			case 'E':
				img = m_sprites_Hit[2];
				break;
			default:
				img = m_sprites_Hit[0];
			}
			int w = (int) (m_scale * 32);
			int h = (int) (m_scale * 32);

			g.drawImage(img, p.j * 32, p.i * 32, w, h, null);
			isHit = false;
		}
		else if (!alive){
			g.drawImage(m_model.m_mort, p.j * 32, p.i * 32, 32, 32, null);
		}
	}

}
