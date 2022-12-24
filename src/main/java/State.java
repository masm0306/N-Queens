import java.util.Random;

public class State {
        final public Ilayout layout;
        final public double h;
        final public int n;


        public State(Ilayout l, int n) {
            this.layout = l;
            this.h = l.getH();
            this.n = n;
        }

       
        public State(Ilayout l, double h, int n) {
            this.layout = l;
            this.h = h;
            this.n = n;
        }
        public String toString() { return layout.toString(); }


        //Return a new Ilayout that minimizes h by modifying a random variable of the problem
        public State minChild(){
            Random r = new Random();
            return layout.minChild(this.h,r.nextInt(0,n));
        }
}
