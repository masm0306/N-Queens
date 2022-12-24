
public class MinConfict {
    private State actual;


    public MinConfict(Ilayout l, int n){
        actual = new State(l,n);
    }

    /**
     * Function that implements the min-conflict algorithm
     * @return The final representing the state of the board
     */
    final public State solve() {
        while(true){
            if(actual.h == 0)
                return actual;
            actual = actual.minChild();
        }
    }
}
