package com.lindseyvarner.AI;

import com.lindseyvarner.engine.board.Board;
import com.lindseyvarner.engine.board.Move;
import com.lindseyvarner.engine.players.Transition;

public class Minimax implements Strategy {
    private final Evaluator evaluator;
    private final int depth;

    public Minimax(final int depth) {
        this.evaluator = new BoardEvaluator();
        this.depth = depth;
    }
    @Override
    public String toString() {
        return "Minimax";
    }
    @Override
    public Move execute(Board board) {
        final long startTime = System.currentTimeMillis();
        Move bestMove = null;
        int highestValue = Integer.MIN_VALUE;
        int lowestValue = Integer.MAX_VALUE;
        int currentValue;

        System.out.println(board.currentPlayer() + "depth = " + depth);
        int numMoves = board.currentPlayer().getLegalMoves().size();

        for (final Move move : board.currentPlayer().getLegalMoves()) {
            final Transition transition = board.currentPlayer().makeMove(move);

            if (transition.getStatus().isDone()) {
                currentValue = board.currentPlayer().getAlliance().isWhite() ?
                               min(transition.getTransitionBoard(), depth - 1) :
                               max(transition.getTransitionBoard(), depth - 1);

                if (board.currentPlayer().getAlliance().isWhite() && currentValue >= highestValue) {
                    highestValue = currentValue;
                    bestMove = move;
                }
                else if (board.currentPlayer().getAlliance().isBlack() && currentValue <= lowestValue) {
                    lowestValue = currentValue;
                    bestMove = move;
                }
            }
        }
        final long executionTime = System.currentTimeMillis() - startTime;
        return bestMove;
    }
    public int min(final Board board, final int depth) {
        if (depth == 0) {
            return this.evaluator.evaluate(board, depth);
        }
        int lowestValue = Integer.MAX_VALUE;
        for (final Move move : board.currentPlayer().getLegalMoves()) {
            final Transition transition = board.currentPlayer().makeMove(move);

            if (transition.getStatus().isDone()) {
                final int currentValue = max(transition.getTransitionBoard(), depth - 1);
                if (currentValue <= lowestValue) {
                    lowestValue = currentValue;
                }
            }
        }
        return lowestValue;
    }
    private static boolean isGameOver(final Board board) {
        return board.currentPlayer().isCheckmated() ||
               board.currentPlayer().isStalemated();
    }
    public int max (final Board board, final int depth) {
        if (depth == 0 || isGameOver(board)) {
            return this.evaluator.evaluate(board, depth);
        }
        int highestValue = Integer.MIN_VALUE;
        for (final Move move : board.currentPlayer().getLegalMoves()) {
            final Transition transition = board.currentPlayer().makeMove(move);

            if (transition.getStatus().isDone()) {
                final int currentValue = min(transition.getTransitionBoard(), depth - 1);
                if (currentValue >= highestValue) {
                    highestValue = currentValue;
                }
            }
        }
        return highestValue;
    }
}
