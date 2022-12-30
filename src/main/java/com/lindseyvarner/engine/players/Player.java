package com.lindseyvarner.engine.players;

import com.lindseyvarner.engine.Alliance;
import com.lindseyvarner.engine.board.Board;
import com.lindseyvarner.engine.board.Move;
import com.lindseyvarner.engine.pieces.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public abstract class Player {
    protected final Board board;
    protected final King playerKing;
    protected final Collection<Move> legalMoves;
    private final boolean isInCheck;

    Player(final Board board, final Collection<Move> playerLegals, final Collection<Move> opponentLegals) {
        this.board = board;
        this.playerKing = establishKing();
        this.legalMoves = Collections.unmodifiableCollection(playerLegals);
        this.isInCheck = !Player.calculateAttacks(this.playerKing.getPiecePosition(), opponentLegals).isEmpty();
    }

    public King getPlayerKing() {
        return this.playerKing;
    }

    public Collection<Move> getLegalMoves() {
        return this.legalMoves;
    }

    protected static Collection<Move> calculateAttacks(int piecePosition, Collection<Move> moves) {
        final List<Move> attackMoves = new ArrayList<>();
        for (final Move move : moves) {
            if (piecePosition == move.getDestinationCoordinate()) {
                attackMoves.add(move);
            }
        }
        return Collections.unmodifiableList(attackMoves);
    }

    private King establishKing() {
        for (final Piece piece : getActivePieces()) {
            if (piece.getPieceType().isKing()) {
                return (King) piece;
            }
        }
        throw new RuntimeException("Invalid board");
    }

    public boolean isMoveLegal(final Move move) {
        return this.legalMoves.contains(move);
    }

    public boolean isInCheck() {
        return this.isInCheck;
    }

    public boolean isCheckmated() {
        return this.isInCheck && !hasEscapeMove();
    }

    public boolean isStalemated() {
        return !this.isInCheck && !hasEscapeMove();
    }

    protected boolean hasEscapeMove() {
        for (final Move move : this.legalMoves) {
            final Transition transition = makeMove(move);

            if (transition.getStatus().isDone()) {
                return true;
            }
        }
        return false;
    }

    public boolean isCastled() {
        return false;
    }

    public Transition makeMove(final Move move) {
        if (!isMoveLegal(move)) {
            return new Transition(this.board, move, Status.ILLEGAL_MOVE);
        }
        final Board transitionBoard = move.execute();

        final Collection<Move> kingAttacks =
              Player.calculateAttacks(transitionBoard.currentPlayer().getOpponent().getPlayerKing().getPiecePosition(),
                                      transitionBoard.currentPlayer().getLegalMoves());

        if (!kingAttacks.isEmpty()) {
            return new Transition(this.board, move, Status.IN_CHECK);
        }
        return new Transition(transitionBoard, move, Status.DONE);
    }

    public abstract Collection<Piece> getActivePieces();
    public abstract Alliance getAlliance();
    public abstract Player getOpponent();
    protected abstract Collection<Move> calculateKingCastles(Collection<Move> playerLegals,
                                                             Collection<Move> opponentLegals);
}

