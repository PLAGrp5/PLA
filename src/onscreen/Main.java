package onscreen;

/**
 * Main
 */
public class Main {

    public static void main(String[] args) {
        Map m = new Map(3);
        Tank t = new Tank(2, 1, 'D');

        m.insert('t', t.p.i, t.p.j);

        m.print();

    }
}