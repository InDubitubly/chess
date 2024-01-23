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
    ChessPiece bishop = new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.BISHOP);
    ChessPosition spot = new ChessPosition(5, 4);
    test.addPiece(spot, bishop);
    System.out.println(test);
  }
}
