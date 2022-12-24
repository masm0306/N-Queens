
public class MinConfict {
    private State actual;


    public MinConfict(Ilayout l, int n){
        actual = new State(l,n);
    }

    //Function that implements the min-conflict algorithm
    final public State solve() {
        while(true){
            if(actual.h == 0)
                return actual;
            actual = actual.minChild();
        }
    }
}
