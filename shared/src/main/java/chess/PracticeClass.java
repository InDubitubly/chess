package chess;

public class PracticeClass {
  public static void main(String[] args){
    ChessBoard test = new ChessBoard();
    test.resetBoard();
    for (int i = 1; i < 9; i++){
      for (int j = 1; j < 9; j++){
        ChessPosition temp = new ChessPosition(i,j);
        if (test.getPiece(temp) != null) {
          System.out.print("|" + test.getPiece(temp) + "|");
        } else {
          System.out.print("| |");
        }
      }
      System.out.println();
    }
  }
}
