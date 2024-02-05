package chess;

import java.util.Arrays;

/**
 * A chessboard that can hold and rearrange chess pieces.
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessBoard {
    private ChessPiece board[][] = new ChessPiece[8][8];

    public ChessBoard() {
        
    }

    @Override
    public String toString() {
        String ret = "\n";
        for (int y = 7; y >= 0; y--){
            for (int x = 0; x < 8; x++){
                if (board[y][x] != null){
                    ret += "| " + board[y][x].toString() + " |";
                } else {
                    ret += "|   |";
                }

            }
            ret += "\n";
        }
        return ret;
    }

    @Override
    public boolean equals(Object o) {
        if(this==o) return true;
        if(o==null || getClass()!=o.getClass()) return false;
        ChessBoard that=(ChessBoard) o;
        return this.hashCode() == that.hashCode();
    }

    @Override
    public int hashCode() {
        return this.toString().hashCode();
    }

    /**
     * Adds a chess piece to the chessboard
     *
     * @param position where to add the piece to
     * @param piece    the piece to add
     */
    public void addPiece(ChessPosition position, ChessPiece piece) {
        board[position.getRow()-1][position.getColumn()-1] = piece;
    }

    /**
     * Gets a chess piece on the chessboard
     *
     * @param position The position to get the piece from
     * @return Either the piece at the position, or null if no piece is at that
     * position
     */
    public ChessPiece getPiece(ChessPosition position) {
        return board[position.getRow()-1][position.getColumn()-1];
    }

    /**
     * Sets the board to the default starting board
     * (How the game of chess normally starts)
     */
    public void resetBoard() {
        ChessPiece.PieceType order[] = {ChessPiece.PieceType.ROOK, ChessPiece.PieceType.KNIGHT,
        ChessPiece.PieceType.BISHOP, ChessPiece.PieceType.QUEEN, ChessPiece.PieceType.KING,
        ChessPiece.PieceType.BISHOP, ChessPiece.PieceType.KNIGHT, ChessPiece.PieceType.ROOK};
        for (int i = 0; i < 8; i++) {
            board[0][i] = new ChessPiece(ChessGame.TeamColor.WHITE, order[i]);
            board[1][i] = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.PAWN);
            board[2][i] = null;
            board[3][i] = null;
            board[4][i] = null;
            board[5][i] = null;
            board[6][i] = new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.PAWN);
            board[7][i] = new ChessPiece(ChessGame.TeamColor.BLACK, order[i]);
        }
    }
}
