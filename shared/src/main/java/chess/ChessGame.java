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


private Collection<ChessMove> checkChecking(Collection<ChessMove> the_moves, ChessPosition startPosition) {
        Collection<ChessMove> invalid = new HashSet<>();
        ChessPiece moving_piece = board.getPiece(startPosition);
        for (ChessMove m : the_moves){
            ChessPiece captured = board.getPiece(m.getEndPosition());
            board.addPiece(m.getStartPosition(), null);
            board.addPiece(m.getEndPosition(), moving_piece);
            if (isInCheck(moving_piece.getTeamColor())){
                board.addPiece(m.getStartPosition(), moving_piece);
                board.addPiece(m.getEndPosition(), captured);
                invalid.add(m);
            }
            board.addPiece(m.getStartPosition(), moving_piece);
            board.addPiece(m.getEndPosition(), captured);
        }
        the_moves.removeAll(invalid);
        return the_moves;
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
            return the_moves;
        }
        return checkChecking(the_moves, startPosition);
    }

    /**
     * Makes a move in a chess game
     *
     * @param move chess move to preform
     * @throws InvalidMoveException if move is invalid
     */
    public void makeMove(ChessMove move) throws InvalidMoveException {
        ChessPiece moving_piece=board.getPiece(move.getStartPosition());
        if (move.getPromotionPiece() != null) {
            moving_piece = new ChessPiece(moving_piece.getTeamColor(), move.getPromotionPiece());
        }
        if(moving_piece==null || moving_piece.getTeamColor() != getTeamTurn()) {
            throw new chess.InvalidMoveException();
        }
        Collection<ChessMove> the_moves=validMoves(move.getStartPosition());
        if(the_moves==null) {
            throw new chess.InvalidMoveException();
        }
        the_moves = checkChecking(the_moves, move.getStartPosition());
        if(the_moves.contains(move)) {
            board.addPiece(move.getStartPosition(), null);
            ChessPiece captured = board.getPiece(move.getEndPosition());
            board.addPiece(move.getEndPosition(), moving_piece);
        } else {
            throw new chess.InvalidMoveException();
        }
        setTeamTurn(getTeamTurn() == TeamColor.WHITE ? TeamColor.BLACK : TeamColor.WHITE);
    }

    public ChessPosition getKingPos(TeamColor teamColor) {
        ChessPosition curr_pos = null;
        ChessPiece curr_piece = null;
        for (int i = 1; i < 9; i++){
            for (int j = 1; j < 9; j++){
                curr_pos = new ChessPosition(i,j);
                curr_piece = board.getPiece(curr_pos);
                if (curr_piece != null && curr_piece.getTeamColor() == teamColor
                        && curr_piece.getPieceType() == ChessPiece.PieceType.KING){
                    return curr_pos;
                }
            }
        }
        return null;
    }

    public Collection<ChessMove> getEnemies(TeamColor teamColor) {
        Collection<ChessMove> the_moves = new HashSet<>();
        ChessPosition curr_pos = null;
        ChessPiece curr_piece = null;
        for (int i = 1; i < 9; i++){
            for (int j = 1; j < 9; j++){
                curr_pos = new ChessPosition(i,j);
                curr_piece = board.getPiece(curr_pos);
                if (curr_piece != null && curr_piece.getTeamColor() != teamColor) {
                    the_moves.addAll(curr_piece.pieceMoves(board, curr_pos));
                }
            }
        }
        return the_moves;
    }

    /**
     * Determines if the given team is in check
     *
     * @param teamColor which team to check for check
     * @return True if the specified team is in check
     */
    public boolean isInCheck(TeamColor teamColor) {
        Collection<ChessMove> the_moves = getEnemies(teamColor);
        ChessPosition king_pos = getKingPos(teamColor);
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
        Collection<ChessMove> enemies = getEnemies(teamColor);
        Collection<ChessMove> valid = validMoves(getKingPos(teamColor));
        valid.removeAll(enemies);
        if (isInCheck(teamColor) && valid.isEmpty()) {
            return true;
        }
        return false;
    }

    /**
     * Determines if the given team is in stalemate, which here is defined as having
     * no valid moves
     *
     * @param teamColor which team to check for stalemate
     * @return True if the specified team is in stalemate, otherwise false
     */
    public boolean isInStalemate(TeamColor teamColor) {
        ChessPosition curr_pos = null;
        ChessPiece curr_piece = null;
        Collection<ChessMove> the_moves = new HashSet<>();
        for (int i = 1; i < 9; i++){
            for (int j = 1; j < 9; j++){
                curr_pos = new ChessPosition(i,j);
                curr_piece = board.getPiece(curr_pos);
                if (curr_piece != null && curr_piece.getTeamColor() == teamColor){
                    the_moves.addAll(validMoves(curr_pos));
                }
            }
        }
        if (the_moves.isEmpty()){
            return true;
        }
        return false;
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
