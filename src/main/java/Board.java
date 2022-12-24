
import java.util.*;

import static java.lang.Math.abs;


public class Board implements Ilayout{
    public int[] board;
    public int[] rows;
    public int[] upDiagonals;
    public int[] downDiagonals;

    public Board(int[] b){
        int n = b.length;
        board = new int[n];
        rows = new int[n];
        upDiagonals = new int[2*n-1];
        downDiagonals = new int[2*n-1];
        for (int i = 0; i < board.length; i++){
            int row = b[i];
            board[i] = b[i];
            updateRowAndDiagonals(b[i],i,1);
        }
    }

    
    public Board(int n) {
        board = new int[n];
        rows = new int[n];
        upDiagonals = new int[2*n-1];
        downDiagonals = new int[2*n-1];
        Random r = new Random();
        int j = r.nextInt(0,n);
        for (int i = 0; i < board.length; i++){
            if(j < board.length) {
                board[i] = j;
                updateRowAndDiagonals(j,i,1);
                j += 2;
            }
            else{
                j = 0;
                board[i] = j;
                updateRowAndDiagonals(j,i,1);
            }
        }
    }



    //Update conficts on diagonals and rows
    private void updateRowAndDiagonals(int row, int col, int n){
        upDiagonals[row + col] += n;
        downDiagonals[col - row + board.length - 1] += n;
        rows[row] += n;
    }



    // Get conflicts of row with index i
    private double getRowH(int i) {
        double result = rows[i];
        return result > 1 ? (result * (result-1))/2 : 0;
    }

    // Get conflicts of upDiagonal with index i
    private double getUpDiagonalH(int i) {
        double result = upDiagonals[i];
        return result > 1 ? (result * (result-1))/2 : 0;
    }

    // Get conflicts of downDiagonal with index i
    private double getDownDiagonalH(int i) {
        double result = downDiagonals[i];
        return result > 1 ? (result * (result-1))/2 : 0;
    }


    //Get diagonal of a board position based on coordinates
    private int getUpDiagonal(int row, int col){
        return row + col;
    }

    //Get the index of the down diagonal of some board position
    private int getDownDiagonal(int row, int col){
        return col - row + board.length - 1;
    }


    
    //Get conflicts that involve the queen based on its coordinates
    private double queenH(int row, int col){
        int dDiagonal = getDownDiagonal(row,col);
        int uDiagonal = getUpDiagonal(row,col);
        return getUpDiagonalH(uDiagonal) + getDownDiagonalH(dDiagonal) + getRowH(row);
    }


    // string representation of a board
    public String toString(){
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if(i == board[j])
                    result.append(" Q ");
                else
                    result.append(" - ");
                if(j == board.length - 1)
                    result.append("\n");
            }
        }
        return result.toString();
    }

    
    //Total board conflicts
    public double getH(){
        int l = board.length;
        double result = 0;
        for (int i = 0; i < 2*l-1 ; i++) {
            if(i < l)
                result += getRowH(i);
            result += getUpDiagonalH(i);
            result += getDownDiagonalH(i);
        }
        return result;
    }

    
    //Choose next child with min conflicts, moving a random queen to a row that minimizes the total number of conflicts of the board
    public State minChild(double totalH, int r) {
        int l = board.length;
        int col = r;
        int row = board[col];
        double originalQueenH = queenH(row,col);
        double h = totalH;
        int newRow = row;
        for (int i = 0; i < l; i++) {
            if(i != row){
                board[col] = i;
                updateRowAndDiagonals(newRow,col,-1);
                updateRowAndDiagonals(i,col,1);
                double nextQueenH = queenH(i,col);
                if(nextQueenH <= originalQueenH) {
                    h = getH();
                    originalQueenH = nextQueenH;
                    newRow = i;
                }
                else{
                    board[col] = newRow;
                    updateRowAndDiagonals(newRow,col,1);
                    updateRowAndDiagonals(i,col,-1);
                }
            }
        }
        return new State(this,h,l);
    }
}
