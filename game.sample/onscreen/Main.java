package onscreen;

/*
Main pour les premiers tests, notamment les prototypes
*/
public class Main {

    public static void main(String[] args) {
        //Map m = new Map("game.sample/onscreen/map.txt");
        Map m = new Map(10,25);
        m.print();
        /* Tank j1 = new Tank(m.n / 2 - 1, 1, 'E');
        Tank j2 = new Tank(m.n / 2, 1, 'E');

        m.insert(j1);
        m.insert(j2);

        

        Scanner sc = new Scanner(System.in);

        String dir;

        for (int i = 0; i < 100; i++) {
            dir = sc.nextLine();
            j1.move(m, dir.charAt(0));
            m.print();
        }
        sc.close(); */
    }
}
