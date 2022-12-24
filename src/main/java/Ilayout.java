import java.util.List;
import java.util.PriorityQueue;

public interface Ilayout {

    /**
     * @return the number of total conflicts of the board
     */
    double getH();

    /**
     * Moves a random queen to a row that minimizes the total number of conflicts of the board
     * @param h is the number of conflicts of the board
     * @param n is a random number representing a column
     * @return A state the represents the board
     */
    State minChild(double h, int n);

}
