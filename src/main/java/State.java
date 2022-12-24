import java.util.Random;

public class State {
        final public Ilayout layout;
        final public double h;
        final public int n;

        /**
         * Constructs a state with an Ilayout with a number of variables of the problem n
         * @param l is the Ilayout
         * @param n is the number of variables of the problem
         */
        public State(Ilayout l, int n) {
            this.layout = l;
            this.h = l.getH();
            this.n = n;
        }

        /**
         * Constructs a state with an Ilayout with a number of variables of the problem n and a value h representing the
         * violated restrictions
         * @param l is the Ilayout
         * @param h is the value of the violated restrictions
         * @param n is the number of variables of the problem
         */
        public State(Ilayout l, double h, int n) {
            this.layout = l;
            this.h = h;
            this.n = n;
        }
        public String toString() { return layout.toString(); }


        /**
         * Return a new Ilayout that minimizes h by modifying a random variable of the problem
         * @return a new Ilayout that minimizes h
         */
        public State minChild(){
            Random r = new Random();
            return layout.minChild(this.h,r.nextInt(0,n));
        }
}
