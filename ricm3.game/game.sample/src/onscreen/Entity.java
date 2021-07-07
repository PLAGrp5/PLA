package src.onscreen;

import java.awt.image.BufferedImage;

import edu.ricm3.game.sample.*;


/*
Classe permettant de modélier toutes les identités du jeu
 */
public class Entity {
		
    public Point p;
    public char dir;
    char type;
    float m_scale;
    BufferedImage m_sprite;
  	Model m_model;

    public Entity(char type, int i, int j, char dir) {
        this.type = type;
        this.p = new Point(i, j);
        this.dir = dir;
    }

    public Entity(char type, int i, int j) {
        this.type = type;
        this.p = new Point(i, j);
    }
    
    public Entity(Model model, BufferedImage sprite, int x, int y,char dir, float scale) {
  		m_model = model;
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
        default:
            this.dir = 'D';
            break;
        }
    }
}