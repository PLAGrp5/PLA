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
	Color color;

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
}