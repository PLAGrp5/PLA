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
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; i++) {
                this.map[i][j] = 'f';
            }
        }
    }

    void print() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; i++) {
                System.out.printf("%f | ", map[i][j]);
            }
        }
    }

    void insert(char type, int i, int j) {
        System.out.printf("i = %d / j = %d", i, j);
        map[i][j] = type;
    }
}
