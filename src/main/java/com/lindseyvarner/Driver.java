package com.lindseyvarner;


import com.lindseyvarner.engine.board.Board;

public class Driver {
    public static void main(String[] args) {
        Board board = Board.createStandardBoard();
        System.out.println(board);
    }
}
