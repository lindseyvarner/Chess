package com.lindseyvarner.engine.players;

import com.lindseyvarner.engine.board.Board;
import com.lindseyvarner.engine.board.Move;

public class Transition {
    private final Board transitionBoard;
    private final Move move;
    private final Status status;

    public Transition(final Board transitionBoard, final Move move, final Status status) {
        this.transitionBoard = transitionBoard;
        this.move = move;
        this.status = status;
    }

    public Status getStatus() {
        return this.status;
    }
}
