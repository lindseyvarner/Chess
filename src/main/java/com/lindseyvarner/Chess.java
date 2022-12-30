package com.lindseyvarner;

import com.lindseyvarner.engine.board.Board;
import com.lindseyvarner.gui.Table;

public class Chess {
    public static void main(String[] args) {
        Board board = Board.createStandardBoard();
        System.out.println(board);
        Table table = new Table();
    }
}
