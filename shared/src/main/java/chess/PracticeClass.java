package chess;

public class PracticeClass {
  public static void main(String[] args){
    ChessBoard test = new ChessBoard();
    test.resetBoard();
    ChessBoard compare = new ChessBoard();
    compare.resetBoard();
    System.out.println(test.hashCode());
    System.out.println(compare.hashCode());
    System.out.println(test.equals(compare));
  }
}
