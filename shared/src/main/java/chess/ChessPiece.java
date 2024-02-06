package chess;

import java.util.Collection;
import java.util.HashSet;

/**
 * Represents a single chess piece
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessPiece {
    ChessGame.TeamColor team;
    PieceType piece;
    public ChessPiece(ChessGame.TeamColor pieceColor, ChessPiece.PieceType type) {
        team = pieceColor;
        piece = type;
    }

    @Override
    public String toString() {
        if (team == ChessGame.TeamColor.WHITE){
            switch (piece){
                case ROOK: return "R";
                case KNIGHT: return "N";
                case BISHOP: return "B";
                case QUEEN: return "Q";
                case KING: return "K";
                case PAWN: return "P";
            }
        } else {
            switch (piece){
                case ROOK: return "r";
                case KNIGHT: return "n";
                case BISHOP: return "b";
                case QUEEN: return "q";
                case KING: return "k";
                case PAWN: return "p";
            }
        }
        return "e";
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
        return this.team;
    }

    /**
     * @return which type of chess piece this piece is
     */
    public PieceType getPieceType() {
        return this.piece;
    }

    /**
     * Calculates all the positions a chess piece can move to
     * Does not take into account moves that are illegal due to leaving the king in
     * danger
     *
     * @return Collection of valid moves
     */
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        switch (piece){
            case BISHOP: return bishopMoves(board, myPosition);
            case ROOK:  return rookMoves(board, myPosition);
            case QUEEN: return queenMoves(board, myPosition);
            case KING: return kingMoves(board, myPosition);
            case KNIGHT: return knightMoves(board, myPosition);
            case PAWN: return pawnMoves(board, myPosition);
        }
        throw new RuntimeException("Not implemented");
    }

    private Collection<ChessMove> addMove(ChessPosition start, ChessPosition end, PieceType pro) {
        Collection<ChessMove> move = new HashSet<ChessMove>();
        move.add(new ChessMove(start, end, pro));
        return move;
    }

    private Collection<ChessMove> calculateMove(ChessBoard board, ChessPosition start, ChessPosition pos,
                                                ChessPosition end, PieceType pro){
        Collection<ChessMove> move = new HashSet<ChessMove>();
        if (board.getPiece(end) != null && board.getPiece(end).getTeamColor() == this.team) {
            return move;
        } else {
            return addMove(start, end, pro);
        }
    }


    private Collection<ChessMove> bishopMoves(ChessBoard board, ChessPosition start){
        Collection<ChessMove> moves = new HashSet<ChessMove>();
        ChessPosition pos = start;
        //up-left
        while (pos.getRow() < 8 && pos.getColumn() > 1){
            moves.addAll(calculateMove(board, start, pos, new ChessPosition(pos.getRow()+1, pos.getColumn()-1), null));
            pos = new ChessPosition(pos.getRow()+1, pos.getColumn()-1);
            if (board.getPiece(pos) != null){
                break;
            }
        }
        //up-right
        pos = start;
        while (pos.getRow() < 8 && pos.getColumn() < 8){
            moves.addAll(calculateMove(board, start, pos, new ChessPosition(pos.getRow()+1, pos.getColumn()+1), null));
            pos = new ChessPosition(pos.getRow()+1, pos.getColumn()+1);
            if (board.getPiece(pos) != null){
                break;
            }
        }
        //down-left
        pos = start;
        while (pos.getRow() > 1 && pos.getColumn() > 1){
            moves.addAll(calculateMove(board, start, pos, new ChessPosition(pos.getRow()-1, pos.getColumn()-1), null));
            pos = new ChessPosition(pos.getRow()-1, pos.getColumn()-1);
            if (board.getPiece(pos) != null){
                break;
            }
        }
        //down-right
        pos = start;
        while (pos.getRow() > 1 && pos.getColumn() < 8){
            moves.addAll(calculateMove(board, start, pos, new ChessPosition(pos.getRow()-1, pos.getColumn()+1), null));
            pos = new ChessPosition(pos.getRow()-1, pos.getColumn()+1);
            if (board.getPiece(pos) != null){
                break;
            }
        }
        return moves;
    }

    private Collection<ChessMove> rookMoves(ChessBoard board, ChessPosition start){
        Collection<ChessMove> moves = new HashSet<ChessMove>();
        ChessPosition pos = start;
        //up
        while (pos.getRow() < 8){
            moves.addAll(calculateMove(board, start, pos, new ChessPosition(pos.getRow()+1, pos.getColumn()), null));
            pos = new ChessPosition(pos.getRow()+1, pos.getColumn());
            if (board.getPiece(pos) != null){
                break;
            }
        }
        //right
        pos = start;
        while (pos.getColumn() < 8){
            moves.addAll(calculateMove(board, start, pos, new ChessPosition(pos.getRow(), pos.getColumn()+1), null));
            pos = new ChessPosition(pos.getRow(), pos.getColumn()+1);
            if (board.getPiece(pos) != null){
                break;
            }
        }
        //left
        pos = start;
        while (pos.getColumn() > 1){
            moves.addAll(calculateMove(board, start, pos, new ChessPosition(pos.getRow(), pos.getColumn()-1), null));
            pos = new ChessPosition(pos.getRow(), pos.getColumn()-1);
            if (board.getPiece(pos) != null){
                break;
            }
        }
        //down
        pos = start;
        while (pos.getRow() > 1){
            moves.addAll(calculateMove(board, start, pos, new ChessPosition(pos.getRow()-1, pos.getColumn()), null));
            pos = new ChessPosition(pos.getRow()-1, pos.getColumn());
            if (board.getPiece(pos) != null){
                break;
            }
        }
        return moves;
    }

    private Collection<ChessMove> queenMoves(ChessBoard board, ChessPosition start){
        Collection<ChessMove> moves = new HashSet<ChessMove>();
        ChessPosition pos = start;
        //up-left
        while (pos.getRow() < 8 && pos.getColumn() > 1){
            moves.addAll(calculateMove(board, start, pos, new ChessPosition(pos.getRow()+1, pos.getColumn()-1), null));
            pos = new ChessPosition(pos.getRow()+1, pos.getColumn()-1);
            if (board.getPiece(pos) != null){
                break;
            }
        }
        //up-right
        pos = start;
        while (pos.getRow() < 8 && pos.getColumn() < 8){
            moves.addAll(calculateMove(board, start, pos, new ChessPosition(pos.getRow()+1, pos.getColumn()+1), null));
            pos = new ChessPosition(pos.getRow()+1, pos.getColumn()+1);
            if (board.getPiece(pos) != null){
                break;
            }
        }
        //down-left
        pos = start;
        while (pos.getRow() > 1 && pos.getColumn() > 1){
            moves.addAll(calculateMove(board, start, pos, new ChessPosition(pos.getRow()-1, pos.getColumn()-1), null));
            pos = new ChessPosition(pos.getRow()-1, pos.getColumn()-1);
            if (board.getPiece(pos) != null){
                break;
            }
        }
        //down-right
        pos = start;
        while (pos.getRow() > 1 && pos.getColumn() < 8){
            moves.addAll(calculateMove(board, start, pos, new ChessPosition(pos.getRow()-1, pos.getColumn()+1), null));
            pos = new ChessPosition(pos.getRow()-1, pos.getColumn()+1);
            if (board.getPiece(pos) != null){
                break;
            }
        }
        pos = start;
        //up
        while (pos.getRow() < 8){
            moves.addAll(calculateMove(board, start, pos, new ChessPosition(pos.getRow()+1, pos.getColumn()), null));
            pos = new ChessPosition(pos.getRow()+1, pos.getColumn());
            if (board.getPiece(pos) != null){
                break;
            }
        }
        //right
        pos = start;
        while (pos.getColumn() < 8){
            moves.addAll(calculateMove(board, start, pos, new ChessPosition(pos.getRow(), pos.getColumn()+1), null));
            pos = new ChessPosition(pos.getRow(), pos.getColumn()+1);
            if (board.getPiece(pos) != null){
                break;
            }
        }
        //left
        pos = start;
        while (pos.getColumn() > 1){
            moves.addAll(calculateMove(board, start, pos, new ChessPosition(pos.getRow(), pos.getColumn()-1), null));
            pos = new ChessPosition(pos.getRow(), pos.getColumn()-1);
            if (board.getPiece(pos) != null){
                break;
            }
        }
        //down
        pos = start;
        while (pos.getRow() > 1){
            moves.addAll(calculateMove(board, start, pos, new ChessPosition(pos.getRow()-1, pos.getColumn()), null));
            pos = new ChessPosition(pos.getRow()-1, pos.getColumn());
            if (board.getPiece(pos) != null){
                break;
            }
        }
        return moves;
    }

    private Collection<ChessMove> kingMoves(ChessBoard board, ChessPosition pos){
        Collection<ChessMove> moves = new HashSet<ChessMove>();

        if (pos.getRow() < 8){
            if (pos.getColumn() > 1) {
                moves.addAll(calculateMove(board, pos, pos, new ChessPosition(pos.getRow() + 1, pos.getColumn() - 1), null));
            }
            if (pos.getColumn() < 8) {
                moves.addAll(calculateMove(board, pos, pos, new ChessPosition(pos.getRow() + 1, pos.getColumn() + 1), null));
            }
            moves.addAll(calculateMove(board, pos, pos, new ChessPosition(pos.getRow()+1, pos.getColumn()), null));
        }
        if (pos.getRow() > 1){
            if (pos.getColumn() > 1) {
                moves.addAll(calculateMove(board, pos, pos, new ChessPosition(pos.getRow()-1, pos.getColumn()-1), null));
            }
            if (pos.getColumn() < 8) {
                moves.addAll(calculateMove(board, pos, pos, new ChessPosition(pos.getRow()-1, pos.getColumn()+1), null));
            }
            moves.addAll(calculateMove(board, pos, pos, new ChessPosition(pos.getRow()-1, pos.getColumn()), null));
        }
        if (pos.getColumn() < 8){
            moves.addAll(calculateMove(board, pos, pos, new ChessPosition(pos.getRow(), pos.getColumn()+1), null));
        }
        if (pos.getColumn() > 1){
            moves.addAll(calculateMove(board, pos, pos, new ChessPosition(pos.getRow(), pos.getColumn()-1), null));
        }
        return moves;
    }

    private Collection<ChessMove> knightMoves(ChessBoard board, ChessPosition pos){
        Collection<ChessMove> moves = new HashSet<ChessMove>();
        if (pos.getRow()< 7) {
            if (pos.getColumn() < 8) {
                moves.addAll(calculateMove(board, pos, pos, new ChessPosition(pos.getRow()+2, pos.getColumn()+1), null));
            }
            if (pos.getColumn() > 1) {
                moves.addAll(calculateMove(board, pos, pos, new ChessPosition(pos.getRow()+2, pos.getColumn()-1), null));
            }
        }
        if (pos.getRow()> 2) {
            if (pos.getColumn() < 8) {
                moves.addAll(calculateMove(board, pos, pos, new ChessPosition(pos.getRow()-2, pos.getColumn()+1), null));
            }
            if (pos.getColumn() > 1) {
                moves.addAll(calculateMove(board, pos, pos, new ChessPosition(pos.getRow()-2, pos.getColumn()-1), null));
            }
        }
        if (pos.getColumn() < 7) {
            if (pos.getRow() < 8){
                moves.addAll(calculateMove(board, pos, pos, new ChessPosition(pos.getRow()+1, pos.getColumn()+2), null));
            }
            if (pos.getRow() > 1){
                moves.addAll(calculateMove(board, pos, pos, new ChessPosition(pos.getRow()-1, pos.getColumn()+2), null));

            }
        }
        if (pos.getColumn() > 2) {
            if (pos.getRow() < 8){
                moves.addAll(calculateMove(board, pos, pos, new ChessPosition(pos.getRow()+1, pos.getColumn()-2), null));
            }
            if (pos.getRow() > 1){
                moves.addAll(calculateMove(board, pos, pos, new ChessPosition(pos.getRow()-1, pos.getColumn()-2), null));

            }
        }
        return moves;
    }

    private Collection<ChessMove> calculatePawns(ChessBoard board, ChessPosition start, int grad, ChessPosition end){
        Collection<ChessMove> move = new HashSet<ChessMove>();
        if (board.getPiece(end) != null && board.getPiece(end).getTeamColor() == this.team) {
            return move;
        } else if (end.getRow() == grad) {
            move.addAll(addMove(start, end, PieceType.ROOK));
            move.addAll(addMove(start, end, PieceType.BISHOP));
            move.addAll(addMove(start, end, PieceType.QUEEN));
            move.addAll(addMove(start, end, PieceType.KNIGHT));
            return move;
        } else {
            return addMove(start, end, null);
        }
    }

    private Collection<ChessMove> pawnMoves(ChessBoard board, ChessPosition pos){
        Collection<ChessMove> moves = new HashSet<ChessMove>();
        switch (team){
            case WHITE:
                if (pos.getRow() < 8 && board.getPiece(new ChessPosition(pos.getRow()+1, pos.getColumn())) == null) {
                    // move 1
                    moves.addAll(calculatePawns(board, pos, 8, new ChessPosition(pos.getRow()+1, pos.getColumn())));
                    if (pos.getRow() == 2 && board.getPiece(new ChessPosition(pos.getRow()+1, pos.getColumn())) == null
                            && board.getPiece(new ChessPosition(pos.getRow()+2, pos.getColumn())) == null) {
                        // initial move 2
                        moves.addAll(calculatePawns(board, pos,8, new ChessPosition(pos.getRow()+2, pos.getColumn())));
                    }
                }
                if (pos.getColumn() > 1 && board.getPiece(new ChessPosition(pos.getRow()+ 1, pos.getColumn()-1)) != null){
                    moves.addAll(calculatePawns(board, pos, 8, new ChessPosition(pos.getRow()+1, pos.getColumn()-1)));
                }
                if (pos.getColumn() < 8 && board.getPiece(new ChessPosition(pos.getRow()+ 1, pos.getColumn()+1)) != null){
                    moves.addAll(calculatePawns(board, pos, 8, new ChessPosition(pos.getRow()+1, pos.getColumn()+1)));
                }
                break;
            case BLACK:
                if (pos.getRow() > 1 && board.getPiece(new ChessPosition(pos.getRow()-1, pos.getColumn())) == null) {
                    // move 1
                    moves.addAll(calculatePawns(board, pos, 1, new ChessPosition(pos.getRow()-1, pos.getColumn())));
                    if (pos.getRow() == 7 && board.getPiece(new ChessPosition(pos.getRow()-1, pos.getColumn())) == null
                            && board.getPiece(new ChessPosition(pos.getRow()-2, pos.getColumn())) == null) {
                        // initial move 2
                        moves.addAll(calculatePawns(board, pos,1, new ChessPosition(pos.getRow()-2, pos.getColumn())));
                    }
                }
                if (pos.getColumn() > 1 && board.getPiece(new ChessPosition(pos.getRow()- 1, pos.getColumn()-1)) != null){
                    moves.addAll(calculatePawns(board, pos, 1, new ChessPosition(pos.getRow()-1, pos.getColumn()-1)));
                }
                if (pos.getColumn() < 8 && board.getPiece(new ChessPosition(pos.getRow()- 1, pos.getColumn()+1)) != null){
                    moves.addAll(calculatePawns(board, pos, 1, new ChessPosition(pos.getRow()-1, pos.getColumn()+1)));
                }
                break;
        }
        return moves;
    }
}
