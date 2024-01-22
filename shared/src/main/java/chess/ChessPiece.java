package chess;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;

/**
 * Represents a single chess piece
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessPiece {
    PieceType type;
    ChessGame.TeamColor color;

    @Override
    public boolean equals(Object o) {
        if(this==o) return true;
        if(!(o instanceof ChessPiece that)) return false;
        return type==that.type && color==that.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, color);
    }

    @Override
    public String toString() {
        char type = 'e';
        switch (this.type) {
            case KING:
                if (this.color == ChessGame.TeamColor.BLACK){
                    type = 'K';
                }  else {
                    type = 'k';
                }
                break;
            case QUEEN:
                if (this.color == ChessGame.TeamColor.BLACK){
                    type = 'Q';
                }  else {
                    type = 'q';
                }
                break;
            case BISHOP:
                if (this.color == ChessGame.TeamColor.BLACK){
                    type = 'B';
                }  else {
                    type = 'b';
                }
                break;
            case KNIGHT:
                if (this.color == ChessGame.TeamColor.BLACK){
                    type = 'N';
                }  else {
                    type = 'n';
                }
                break;
            case ROOK:
                if (this.color == ChessGame.TeamColor.BLACK){
                    type = 'R';
                }  else {
                    type = 'r';
                }
                break;
            case PAWN:
                if (this.color == ChessGame.TeamColor.BLACK){
                    type = 'P';
                }  else {
                    type = 'p';
                }
                break;
            }
        return type + "";
    }

    public ChessPiece(ChessGame.TeamColor pieceColor, ChessPiece.PieceType type) {
        this.type = type;
        this.color = pieceColor;
    }

    /**
     * The various different chess piece options
     */
    public enum PieceType {
        KING,
        QUEEN,
        BISHOP,
        KNIGHT,
        ROOK,
        PAWN
    }

    /**
     * @return Which team this chess piece belongs to
     */
    public ChessGame.TeamColor getTeamColor() {
        return this.color;
    }

    /**
     * @return which type of chess piece this piece is
     */
    public PieceType getPieceType() {
        return this.type;
    }

    /**
     *
     */
    public Collection<ChessMove> findBishopMoves(ChessBoard board, ChessPosition pos) {
        Collection<ChessMove> the_moves = new HashSet<>();

//        the_moves.add();
        return the_moves;
    }




    /**
     * Calculates all the positions a chess piece can move to
     * Does not take into account moves that are illegal due to leaving the king in
     * danger
     *
     * @return Collection of valid moves
     */
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        if (this.type == PieceType.BISHOP) {
            return findBishopMoves(board, myPosition);
        }
        return null;
    }
}
