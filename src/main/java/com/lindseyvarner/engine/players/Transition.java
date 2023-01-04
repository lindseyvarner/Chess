package com.lindseyvarner.engine.players;

import com.lindseyvarner.engine.board.Board;
import com.lindseyvarner.engine.board.Move;

public final class Transition {
    private final Board transitionBoard;
    private final Move transitionMove;
    private final Status status;

    public Transition(final Board transitionBoard, final Move transitionMove, final Status status) {
        this.transitionBoard = transitionBoard;
        this.transitionMove = transitionMove;
        this.status = status;
    }

    public Status getStatus() {
        return this.status;
    }

    public Board getTransitionBoard() {
        return this.transitionBoard;
    }
}
