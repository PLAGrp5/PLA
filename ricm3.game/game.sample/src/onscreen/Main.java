package src.onscreen;

import java.util.Scanner;

/*
Main pour les tests
 */
public class Main {

    public static void main(String[] args) {
        Map m = new Map(10);
        Tank j1 = new Tank(m.n / 2 - 1, 1, 'R');
        Tank j2 = new Tank(m.n / 2, 1, 'R');

        m.insert(j1);
        m.insert(j2);

        m.print();

        Scanner sc = new Scanner(System.in);

        String dir;

        for (int i = 0; i < 100; i++) {
            dir = sc.nextLine();
            j1.move(m, dir.charAt(0));
            m.print();
        }
        sc.close();
    }
}