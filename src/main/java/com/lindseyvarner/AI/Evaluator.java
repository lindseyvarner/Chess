package com.lindseyvarner.AI;

import com.lindseyvarner.engine.board.Board;

public interface Evaluator {
    int evaluate(Board board, int depth);
}
