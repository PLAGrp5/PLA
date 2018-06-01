package onscreen;

import java.util.Scanner;

/**
 * Main
 */
public class Main {

    public static void main(String[] args) {
        Map m = new Map(10);
        Entity t = new Entity('T', m.n / 2, 1, 'R');

        m.insert(t);

        m.print();

        Scanner sc = new Scanner(System.in);

        String dir;

        for (int i = 0; i < 10; i++) {
            dir = sc.nextLine();
            t.move(m, dir.charAt(0));
            m.print();
        }
        sc.close();
    }
}
