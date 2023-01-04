package com.lindseyvarner.AI;

import com.lindseyvarner.engine.board.Board;
import com.lindseyvarner.engine.board.Move;

public interface Strategy {
    Move execute(Board board);


}
