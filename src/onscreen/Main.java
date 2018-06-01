package onscreen;

import java.util.Scanner;

/**
 * Main
 */
public class Main {

    public static void main(String[] args) {
        Map m = new Map(10);
        Tank t = new Tank(m.n / 2, 1, 'R');

        m.insert('T', t.p.i, t.p.j);

        m.print();

        Scanner sc = new Scanner(System.in);

        String dir;

        while (true) {
            dir = sc.nextLine();
            t.move(m, dir.charAt(0));
            m.print();
        }
    }
}
