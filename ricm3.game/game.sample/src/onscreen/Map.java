package onscreen;

/**
 * map
 */

public class Map {

    int n;
    char map[][];

    Map(int n) {
        this.n = n;
        this.map = new char[n][n];
        int i, j;

        for (i = 1; i < n; i++) {
            for (j = 1; j < n; j++) {
                map[i][j] = 'f';
            }
        }

        for (i = 0, j = 0; j < n; j++)
            map[i][j] = 'w';

        for (i = n - 1, j = 0; j < n; j++)
            map[i][j] = 'w';

        for (i = 0, j = 0; i < n; i++)
            map[i][j] = 'w';

        for (i = 0, j = n - 1; i < n; i++)
            map[i][j] = 'w';

    }

    void print() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.printf("%c ", map[i][j]);
            }
            System.out.print("\n");
        }
    }

    void insert(char type, int i, int j) {
        map[i][j] = type;
    }
}
