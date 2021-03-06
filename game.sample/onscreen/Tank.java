package onscreen;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import automate.*;
import ui.*;

/* Classe permettant de modéliser les tanks dans le jeu
 */
public class Tank extends Entity {

	public Tank(int i, int j, char dir) {
		super('T', i, j, dir);
	}

	public Tank(Model model, BufferedImage sprite, BufferedImage sprite_hit, int i, int j, char dir, float scale, int dose_couleur, Color color) {
		super('T', i, j, dir);
		aut_bonus = false;
		alive = true;
		m_model = model;
		m_sprite = sprite;
		m_sprite_Hit = sprite_hit;
		m_scale = scale;
		lastj = p.j;
		lasti = p.i;
		m_model.m_Map.insert(this);
		m_tank = color;
		jauge_couleur = dose_couleur;
		maxnstep = 10;
		splitTankSprite();
		splitTankSprite_Hit();
		setvie(25);
		initinventaire();
	}

	/*
	 * Réalise l'action du tank Step est appelé depuis le controller en fonction des
	 * touches enfoncé
	 */
	public void move(char dir) {

		if (aut_bonus)
			return;

		long now = System.currentTimeMillis();
		long elapsed = now - m_lastMove;
		if (elapsed > 100L) {
			lastj = p.j;
			lasti = p.i;
			m_lastMove = now;

			/*
			 * Si notre jauge de couleur n'est pas vide et que l'action est un mouvement On
			 * colorie la case précedente seulement si elle change de couleur
			 */

			new Move(dir).execute(this);

		}
	}

	// Récuperation des différente images du tank dans un tableau à partir des
	// sprites
	void splitTankSprite() {
		m_sprites = new BufferedImage[4];
		for (int j = 0; j < 4; j++) {
			int x = j * 32;
			int y = 0;
			m_sprites[j] = m_sprite.getSubimage(x, y, 32, 32);
		}
	}
	
	void splitTankSprite_Hit() {
		m_sprites_Hit = new BufferedImage[4];
		for (int j = 0; j < 4; j++) {
			int x = j * 32;
			int y = 0;
			m_sprites_Hit[j] = m_sprite_Hit.getSubimage(x, y, 32, 32);
		}
	}

	// Affichage d'un tank
	public void paint(Graphics g, char dir) {
		if (!isHit) {
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
		else {
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

	}
}
