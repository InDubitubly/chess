package chess;

import java.util.Arrays;

/**
 * A chessboard that can hold and rearrange chess pieces.
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessBoard {
    private ChessPiece[][] board = new ChessPiece[8][8]; //first horizontal, second vertical
    private ChessPiece.PieceType[] piece_order = {
            ChessPiece.PieceType.ROOK, ChessPiece.PieceType.KNIGHT,
            ChessPiece.PieceType.BISHOP, ChessPiece.PieceType.QUEEN,
            ChessPiece.PieceType.KING, ChessPiece.PieceType.BISHOP,
            ChessPiece.PieceType.KNIGHT, ChessPiece.PieceType.ROOK};
            // piece order from left to right, for both sides

    public ChessBoard() {

    }

    @Override
    public String toString() {
        String result = "\n";
        for(int i=8; i > 0; i--) {
            for(int j=1; j < 9; j++) {
                ChessPosition temp=new ChessPosition(i, j);
                if(this.getPiece(temp)!=null) {
                    result += "|" + this.getPiece(temp) + "|";
                } else {
                    result += "| |";
                }
            }
            result += "\n";
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if(this==o) return true;
        if(!(o instanceof ChessBoard that)) return false;
        if (this.hashCode() == o.hashCode()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        int result=this.toString().hashCode();
        result=31 * result;
        return result;
    }

    /**
     * Adds a chess piece to the chessboard
     *
     * @param position where to add the piece to
     * @param piece    the piece to add
     */
    public void addPiece(ChessPosition position, ChessPiece piece) {
        this.board[position.getRow()-1][position.getColumn()-1] = piece;
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
        for (int i = 1; i < 9; i++){
            ChessPosition white_pawns = new ChessPosition(2,i);
            ChessPosition black_pawns = new ChessPosition(7,i);
            ChessPosition white_pos = new ChessPosition(1,i);
            ChessPosition black_pos = new ChessPosition(8,i);
            ChessPiece white_pawn = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.PAWN);
            ChessPiece black_pawn = new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.PAWN);
            ChessPiece white_piece = new ChessPiece(ChessGame.TeamColor.WHITE, piece_order[i-1]);
            ChessPiece black_piece = new ChessPiece(ChessGame.TeamColor.BLACK, piece_order[i-1]);
            this.addPiece(white_pawns, white_pawn);
            this.addPiece(black_pawns, black_pawn);
            this.addPiece(white_pos, white_piece);
            this.addPiece(black_pos, black_piece);
        }
    }
}
