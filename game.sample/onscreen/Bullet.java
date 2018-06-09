package onscreen;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import automate.*;
import ui.*;

/**
 * Bullet
 */
public class Bullet extends Entity {
    BufferedImage[] m_sprites;

    public Automate comport;

    public Bullet(Entity e) {
        super('B', e.p.i, e.p.j, e.dir);
        switch (this.dir) {
        case 'S':
            p.i++;
            break;
        case 'W':
            p.j--;
            break;
        case 'E':
            p.j++;
            break;
        default:
            p.i--;
            break;
        }
        e.m_model.m.insert(this);
    }

    public Bullet(Entity e, Automate a, State s) {
        super('B', e.p.i, e.p.j, e.dir);
        courant = s;
        comport = a;
        e.m_model.m.insert(this);
    }

    public Bullet(Model model, BufferedImage sprite, float scale, Entity e, Automate a, State s) {
        super('B', e.p.i, e.p.j, e.dir);
        this.m_model = model;
        switch (dir) {
        case 'S':
            p.i++;
            break;
        case 'W':
            p.j--;
            break;
        case 'E':
            p.j++;
            break;
        default:
            p.i--;
            break;
        }

        if (!e.m_model.m.isfree(p.i, p.j))
            return;

        courant = s;
        comport = a;
        m_scale = scale;
        m_sprite = sprite;
        splitBulletSprite();
        e.m_model.m.insert(this);
    }

    void splitBulletSprite() {
        m_sprites = new BufferedImage[4];
        for (int j = 0; j < 4; j++) {
            int x = j * 32;
            int y = 0;
            m_sprites[j] = m_sprite.getSubimage(x, y, 32, 32);
        }
    }

    // Affichage d'un tank
    public void paint(Graphics g, char dir) {
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
}
