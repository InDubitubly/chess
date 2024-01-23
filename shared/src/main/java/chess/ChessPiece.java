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
        Collection<ChessMove> the_moves = new HashSet<ChessMove>();
        ChessPosition up_left = new ChessPosition(pos.getRow()+1, pos.getColumn()-1);
        ChessPosition up_right = new ChessPosition(pos.getRow()+1, pos.getColumn()+1);
        ChessPosition down_left = new ChessPosition(pos.getRow()-1, pos.getColumn()-1);
        ChessPosition down_right = new ChessPosition(pos.getRow()-1, pos.getColumn()+1);
        Collection<ChessMove> up_left_move = bishopHelper(board, up_left, pos);
        Collection<ChessMove> up_right_move = bishopHelper(board, up_right, pos);
        Collection<ChessMove> down_left_move = bishopHelper(board, down_left, pos);
        Collection<ChessMove> down_right_move = bishopHelper(board, down_right, pos);
        if (up_left_move != null) {
            the_moves.addAll(up_left_move);
        }
        if (up_right_move != null){
            the_moves.addAll(up_right_move);
        }
        if (down_left_move != null){
            the_moves.addAll(down_left_move);
        }
        if (down_right_move != null) {
            the_moves.addAll(down_right_move);
        }
        return the_moves;
    }

    /**
     * if wall, stop
     * if piece opposite, include and stop
     * if piece same, don't include and stop
     * if null, add and continue
     * iterate to next space following pattern
     */
    public Collection<ChessMove> bishopHelper(ChessBoard board, ChessPosition pos, ChessPosition start){
        Collection<ChessMove> adder = new HashSet<ChessMove>();
        if (pos.getRow() > 8 || pos.getRow() < 1) {
            return null;
        } else if (pos.getColumn() > 8 || pos.getColumn() < 1) {
            return null;
        } else if (board.getPiece(pos) != null) {
            return null;
        } else {
            ChessMove move = new ChessMove(start, pos, this.getPieceType());
            adder.add(move);
            ChessPosition up_left = new ChessPosition(pos.getRow()+1, pos.getColumn()-1);
            ChessPosition up_right = new ChessPosition(pos.getRow()+1, pos.getColumn()+1);
            ChessPosition down_left = new ChessPosition(pos.getRow()-1, pos.getColumn()-1);
            ChessPosition down_right = new ChessPosition(pos.getRow()-1, pos.getColumn()+1);
            Collection<ChessMove> up_left_move = bishopHelper(board, up_left, start);
            Collection<ChessMove> up_right_move = bishopHelper(board, up_right, start);
            Collection<ChessMove> down_left_move = bishopHelper(board, down_left, start);
            Collection<ChessMove> down_right_move = bishopHelper(board, down_right, start);
            if (up_left_move != null) {
                adder.addAll(up_left_move);
            }
            if (up_right_move != null){
                adder.addAll(up_right_move);
            }
            if (down_left_move != null){
                adder.addAll(down_left_move);
            }
            if (down_right_move != null) {
                adder.addAll(down_right_move);
            }
            return adder;
        }
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
