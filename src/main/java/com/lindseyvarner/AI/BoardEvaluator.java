package com.lindseyvarner.AI;

import com.lindseyvarner.engine.board.Board;
import com.lindseyvarner.engine.pieces.Piece;
import com.lindseyvarner.engine.players.Player;

public final class BoardEvaluator implements Evaluator {
    private static final int CASTLE_BONUS = 60;
    private static final int CHECK_BONUS = 50;
    private static final int CHECKMATE_BONUS = 10000;
    private static final int DEPTH_BONUS = 100;

    @Override
    public int evaluate(final Board board, final int depth) {
        return score(board, board.whitePlayer(), depth) -
               score(board, board.blackPlayer(), depth);
    }
    private int score(final Board board, final Player player, final int depth) {
        return pieceValue(player) + mobility(player) +
               check(player) + checkmate(player, depth) +
               castled(player);
    }
    private static int mobility(final Player player) {
        return player.getLegalMoves().size();
    }
    private static int castled(Player player) {
        return player.isCastled() ? CASTLE_BONUS : 0;
    }
    private static int checkmate(Player player, int depth) {
        return player.getOpponent().isCheckmated() ?
               CHECKMATE_BONUS * depthBonus(depth) : 0;
    }
    private static int depthBonus(int depth) {
        return depth == 0 ? 1 : DEPTH_BONUS * depth;
    }
    private static int check(final Player player) {
        return player.getOpponent().isInCheck() ? CHECK_BONUS : 0;
    }
    private static int pieceValue(final Player player) {
        int value = 0;
        for (final Piece piece : player.getActivePieces()) {
            value += piece.getPieceValue();
        }
        return value;
    }
}
