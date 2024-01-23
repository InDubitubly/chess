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
    private PieceType type;
    private ChessGame.TeamColor color;

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
                    type = 'k';
                }  else {
                    type = 'K';
                }
                break;
            case QUEEN:
                if (this.color == ChessGame.TeamColor.BLACK){
                    type = 'q';
                }  else {
                    type = 'Q';
                }
                break;
            case BISHOP:
                if (this.color == ChessGame.TeamColor.BLACK){
                    type = 'b';
                }  else {
                    type = 'B';
                }
                break;
            case KNIGHT:
                if (this.color == ChessGame.TeamColor.BLACK){
                    type = 'n';
                }  else {
                    type = 'N';
                }
                break;
            case ROOK:
                if (this.color == ChessGame.TeamColor.BLACK){
                    type = 'r';
                }  else {
                    type = 'R';
                }
                break;
            case PAWN:
                if (this.color == ChessGame.TeamColor.BLACK){
                    type = 'p';
                }  else {
                    type = 'P';
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
        ChessPosition start = pos;
        ChessPosition new_pos = null;
        Collection<ChessMove> the_moves = new HashSet<>();
        // up-left test
        while (pos.getRow() < 8 && pos.getColumn() > 1){
            new_pos = new ChessPosition(pos.getRow()+1, pos.getColumn()-1);
            if (board.getPiece(new_pos) != null){
                if (board.getPiece(new_pos).getTeamColor() == this.getTeamColor()){
                    break;
                } else {
                    ChessMove capture = new ChessMove(start, new_pos, null);
                    the_moves.add(capture);
                    break;
                }
            } else {
                ChessMove open = new ChessMove(start, new_pos, null);
                the_moves.add(open);
                pos = new_pos;
            }
        }
        // up-right test
        pos = start;
        while (pos.getRow() < 8 && pos.getColumn() < 8){
            new_pos = new ChessPosition(pos.getRow()+1, pos.getColumn()+1);
            if (board.getPiece(new_pos) != null){
                if (board.getPiece(new_pos).getTeamColor() == this.getTeamColor()){
                    break;
                } else {
                    ChessMove capture = new ChessMove(start, new_pos, null);
                    the_moves.add(capture);
                    break;
                }
            } else {
                ChessMove open = new ChessMove(start, new_pos, null);
                the_moves.add(open);
                pos = new_pos;
            }
        }
        // down-left test
        pos = start;
        while (pos.getRow() > 1 && pos.getColumn() > 1){
            new_pos = new ChessPosition(pos.getRow()-1, pos.getColumn()-1);
            if (board.getPiece(new_pos) != null){
                if (board.getPiece(new_pos).getTeamColor() == this.getTeamColor()){
                    break;
                } else {
                    ChessMove capture = new ChessMove(start, new_pos, null);
                    the_moves.add(capture);
                    break;
                }
            } else {
                ChessMove open = new ChessMove(start, new_pos, null);
                the_moves.add(open);
                pos = new_pos;
            }
        }
        // down-right test
        pos = start;
        while (pos.getRow() > 1 && pos.getColumn() < 8){
            new_pos = new ChessPosition(pos.getRow()-1, pos.getColumn()+1);
            if (board.getPiece(new_pos) != null){
                if (board.getPiece(new_pos).getTeamColor() == this.getTeamColor()){
                    break;
                } else {
                    ChessMove capture = new ChessMove(start, new_pos, null);
                    the_moves.add(capture);
                    break;
                }
            } else {
                ChessMove open = new ChessMove(start, new_pos, null);
                the_moves.add(open);
                pos = new_pos;
            }
        }
        // return collection
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
        throw new RuntimeException("Not implemented");

    }
}
