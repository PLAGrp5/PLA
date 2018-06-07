package onscreen;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

/**
 * Bullet
 */
public class Bullet extends Entity {
    BufferedImage[] m_sprites;

    public Bullet(Entity e) {
        super('B', e.p.i, e.p.j, e.dir);
        aut = true;
        e.m_map.insert(this);
    }

    public Bullet(Entity e, Automate a, State s) {
        super('B', e.p.i, e.p.j, e.dir);
        aut = true;
        courant = s;
        comport = a;
        e.m_map.insert(this);
    }

    public Bullet(BufferedImage sprite, float scale, Entity e, Automate a, State s) {
        super('B', e.p.i, e.p.j, e.dir);
        switch (dir) {
        case 'D':
            p.i++;
            break;
        case 'L':
            p.j--;
            break;
        case 'R':
            p.j++;
            break;
        default:
            p.i--;
            break;
        }

        if (!e.m_map.isfree(p.i, p.j))
            return;

        aut = true;
        courant = s;
        comport = a;
        m_scale = scale;
        m_sprite = sprite;
        splitBulletSprite();
        e.m_map.insert(this);
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
        case 'U':
            img = m_sprites[1];
            break;
        case 'D':
            img = m_sprites[3];
            break;
        case 'R':
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
