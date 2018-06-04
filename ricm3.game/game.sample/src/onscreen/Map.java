package src.onscreen;

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

    public boolean isfree(int i, int j) {
        return this.map[i][j].type == 'F';
    }

    public boolean isbonus(int i, int j) {
        return this.map[i][j].type == 'B';
    }

    public void free(int i, int j) {
        this.insert(new Entity('F', i, j));
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
