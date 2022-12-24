import java.util.Scanner;

import static java.lang.System.currentTimeMillis;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int runs = 5;
        long startTime;
        long stopTime;
        State state = null;
        startTime = currentTimeMillis();
        for (int i = 0; i < runs; i++) {
            MinConfict s = new MinConfict(new Board(n),n);
            s.solve();
        }
        stopTime = currentTimeMillis();
        System.out.println((stopTime - startTime)/runs);
        if (state==null)
            System.out.println("no solution found");
        else {
            //System.out.println(state);
            System.out.println("Sol");
        }
        sc.close();
    }
}
