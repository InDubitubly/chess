package chess;

import com.sun.source.tree.WhileLoopTree;

import java.security.InvalidParameterException;
import java.util.Collection;
import java.util.HashSet;

/**
 * For a class that can manage a chess game, making moves on a board
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessGame {
    ChessBoard board = new ChessBoard();
    ChessGame.TeamColor curr_turn = TeamColor.WHITE;
    public ChessGame() {

    }

    /**
     * @return Which team's turn it is
     */
    public TeamColor getTeamTurn() {
        return this.curr_turn;
    }

    /**
     * Set's which teams turn it is
     *
     * @param team the team whose turn it is
     */
    public void setTeamTurn(TeamColor team) {
        this.curr_turn = team;
    }

    /**
     * Enum identifying the 2 possible teams in a chess game
     */
    public enum TeamColor {
        WHITE,
        BLACK
    }

    /**
     * Gets a valid moves for a piece at the given location
     *
     * @param startPosition the piece to get valid moves for
     * @return Set of valid moves for requested piece, or null if no piece at
     * startPosition
     */
    public Collection<ChessMove> validMoves(ChessPosition startPosition) {
        ChessPiece moving_piece = board.getPiece(startPosition);
        if (moving_piece == null) {
            return null;
        }
        Collection<ChessMove> the_moves = moving_piece.pieceMoves(board, startPosition);
        if (the_moves.isEmpty()) {
            return null;
        }
        return the_moves;
    }

    /**
     * Makes a move in a chess game
     *
     * @param move chess move to preform
     * @throws InvalidMoveException if move is invalid
     */
    public void makeMove(ChessMove move) throws InvalidMoveException {
        ChessPiece moving_piece=board.getPiece(move.getStartPosition());
        if(moving_piece==null || moving_piece.getTeamColor() != getTeamTurn()) {
            throw new chess.InvalidMoveException();
        }
        Collection<ChessMove> the_moves=validMoves(move.getStartPosition());
        if(the_moves==null) {
            throw new chess.InvalidMoveException();
        }
        if(the_moves.contains(move)) {
            board.addPiece(move.getStartPosition(), null);
            ChessPiece captured = board.getPiece(move.getEndPosition());
            board.addPiece(move.getEndPosition(), moving_piece);
            if (isInCheck(getTeamTurn())){
                board.addPiece(move.getStartPosition(), moving_piece);
                board.addPiece(move.getEndPosition(), captured);
                throw new chess.InvalidMoveException();
            }
        } else {
            throw new chess.InvalidMoveException();
        }
        setTeamTurn(getTeamTurn() == TeamColor.WHITE ? TeamColor.BLACK : TeamColor.WHITE);
    }

    /**
     * Determines if the given team is in check
     *
     * @param teamColor which team to check for check
     * @return True if the specified team is in check
     */
    public boolean isInCheck(TeamColor teamColor) {
        Collection<ChessMove> the_moves = new HashSet<>();
        ChessPosition curr_pos = null;
        ChessPiece curr_piece = null;
        ChessPosition king_pos = null;
        for (int i = 1; i < 9; i++){
            for (int j = 1; j < 9; j++){
                curr_pos = new ChessPosition(i,j);
                curr_piece = board.getPiece(curr_pos);
                if (curr_piece != null && curr_piece.getTeamColor() != teamColor) {
                    the_moves.addAll(curr_piece.pieceMoves(board, curr_pos));
                }
                if (curr_piece != null && curr_piece.getTeamColor() == teamColor
                        && curr_piece.getPieceType() == ChessPiece.PieceType.KING){
                    king_pos = curr_pos;
                }
            }
        }
        if (king_pos == null){
            return false;
        }
        for (ChessMove move : the_moves){
            if (move.getEndPosition().equals(king_pos)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Determines if the given team is in checkmate
     *
     * @param teamColor which team to check for checkmate
     * @return True if the specified team is in checkmate
     */
    public boolean isInCheckmate(TeamColor teamColor) {
        throw new RuntimeException("Not implemented");
    }

    /**
     * Determines if the given team is in stalemate, which here is defined as having
     * no valid moves
     *
     * @param teamColor which team to check for stalemate
     * @return True if the specified team is in stalemate, otherwise false
     */
    public boolean isInStalemate(TeamColor teamColor) {
        throw new RuntimeException("Not implemented");
    }

    /**
     * Sets this game's chessboard with a given board
     *
     * @param board the new board to use
     */
    public void setBoard(ChessBoard board) {
        this.board = board;
    }

    /**
     * Gets the current chessboard
     *
     * @return the chessboard
     */
    public ChessBoard getBoard() {
        return this.board;
    }
}
