package src.onscreen;

import java.util.Scanner;

/*
Main pour les tests
 */
public class Main {

    public static void main(String[] args) {
        Map m = new Map(10);
        Tank t = new Tank(m.n / 2, 1, 'R');

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
