package onscreen;

import java.io.File;
import java.util.Scanner;

/*
Class Map nous permet de représenter a carte de jeu
 */

public class Map {

    int n;
    Entity map[][];

    public Map(int n) {
        this.n = n;
        this.map = new Entity[n][n];
        int i, j;
        for (i = 1; i < n; i++)
            for (j = 1; j < n; j++)
                this.insert(new Entity('F', i, j));

        for (i = 0, j = 0; j < n; j++)
            this.insert(new Entity('W', i, j));

        for (i = n - 1, j = 0; j < n; j++)
            this.insert(new Entity('W', i, j));

        for (i = 0, j = 0; i < n; i++)
            this.insert(new Entity('W', i, j));

        for (i = 0, j = n - 1; i < n; i++)
            this.insert(new Entity('W', i, j));

    }

    public Map(String filepath) {
        File f = null;
        Scanner scan = null;
        try {
            f = new File(filepath);
            scan = new Scanner(f);
        } catch (Exception e) {
            System.exit(0);
        }

        this.n = scan.nextInt();
        this.map = new Entity[n][n];
        String s;
        for (int i = 0; i < this.n; i++) {
            s = scan.next();
            for (int j = 0; j < this.n; j++)
                insert(new Entity(s.charAt(j), i, j));
        }

    }

    public Map(int n, int percentage) {
        this.n = n;
        this.map = new Entity[n][n];
        int i, j, rand;
        for (i = 1; i < n; i++)
            for (j = 1; j < n; j++) {
                rand = (int) (Math.random() * 100);
                if (rand < percentage)
                    this.insert(new Entity('W', i, j));
                else
                    this.insert(new Entity('F', i, j));
            }

        for (i = 0, j = 0; j < n; j++)
            this.insert(new Entity('W', i, j));

        for (i = n - 1, j = 0; j < n; j++)
            this.insert(new Entity('W', i, j));

        for (i = 0, j = 0; i < n; i++)
            this.insert(new Entity('W', i, j));

        for (i = 0, j = n - 1; i < n; i++)
            this.insert(new Entity('W', i, j));
    }

    public boolean isfree(int i, int j) {
        return this.map[i][j].type == 'F';
    }

    public boolean isbonus(int i, int j) {
        return this.map[i][j].type == 'B';
    }

    public void free(int i, int j) {
        this.insert(new Entity('F', i, j));
    }
    
    public void color(int i, int j) {
    	
    }

    public void insert(Entity e) {
        map[e.p.i][e.p.j] = e;
    }

    public void print() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j].type == 'T') {
                    switch (map[i][j].dir) {
                    case 'D':
                        System.out.printf("v ");
                        break;
                    case 'L':
                        System.out.printf("< ");
                        break;
                    case 'R':
                        System.out.printf("> ");
                        break;
                    default:
                        System.out.printf("A ");
                        break;
                    }
                } else
                    System.out.printf("%c ", map[i][j].type);
            }
            System.out.print("\n");
        }
    }
}
