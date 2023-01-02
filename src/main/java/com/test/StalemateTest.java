package com.test;

//import com.lindseyvarner.engine.players;
import com.lindseyvarner.engine.players.Transition;
import org.junit.Test;

import static com.lindseyvarner.engine.Alliance.BLACK;
import static com.lindseyvarner.engine.Alliance.WHITE;
import static org.junit.jupiter.api.Assertions.*;
import com.lindseyvarner.engine.board.*;
import com.lindseyvarner.engine.board.Board.Builder;
import com.lindseyvarner.engine.board.Move;
import com.lindseyvarner.engine.pieces.*;
//import com.lindseyvarner.engine.player.ai.BoardEvaluator;
//import com.lindseyvarner.engine.player.ai.StandardBoardEvaluator;
import org.junit.Test;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class StalemateTest {
    /* @Test
    public void testAnandKramnikStaleMate() {

        final Builder builder = new Builder();
        
        builder.setPiece(new Pawn(14, BLACK));
        builder.setPiece(new Pawn(21, BLACK));
        builder.setPiece(new King(36, BLACK, false));
        
        builder.setPiece(new Pawn(29, WHITE));
        builder.setPiece(new King(31, WHITE, false));
        builder.setPiece(new Pawn(39, WHITE));
        builder.setMoveMaker(BLACK);

        final Board board = builder.build();
        assertFalse(board.currentPlayer().isStalemated());
        final Transition t1 = board.currentPlayer()
                .makeMove(Move.Factory.createMove(board, Utilities.getCoordinateAtPosition("e4"),
                        Utilities.getCoordinateAtPosition("f5")));
        assertTrue(t1.getStatus().isDone());
        assertTrue(t1.getToBoard().currentPlayer().isStalemated());
        assertFalse(t1.getToBoard().currentPlayer().isInCheck());
        assertFalse(t1.getToBoard().currentPlayer().isInCheckMate());
    }

    @Test
    public void testAnonymousStaleMate() {
        final Builder builder = new Builder();
        
        builder.setPiece(new King(2, BLACK, false));
        
        builder.setPiece(new Pawn(10, WHITE));
        builder.setPiece(new King(26, WHITE, false));
        builder.setMoveMaker(WHITE);

        final Board board = builder.build();
        assertFalse(board.currentPlayer().isStalemated());
        final Transition t1 = board.currentPlayer()
                .makeMove(Move.Factory.createMove(board, Utilities.getCoordinateAtPosition("c5"),
                        Utilities.getCoordinateAtPosition("c6")));
        assertTrue(t1.getStatus().isDone());
        assertTrue(t1.getToBoard().currentPlayer().isStalemated());
        assertFalse(t1.getToBoard().currentPlayer().isInCheck());
        assertFalse(t1.getToBoard().currentPlayer().isInCheckMate());
    }

    @Test
    public void testAnonymousStaleMate2() {
        final Builder builder = new Builder();
        
        builder.setPiece(new King(0, BLACK, false));
        builder.setPiece(new Pawn(16, WHITE));
        builder.setPiece(new King(17, WHITE, false));
        builder.setPiece(new Bishop(19, WHITE));
        builder.setMoveMaker(WHITE);
        
        final Board board = builder.build();
        assertFalse(board.currentPlayer().isStalemated());
        final Transition t1 = board.currentPlayer()
                .makeMove(Move.Factory.createMove(board, Utilities.getCoordinateAtPosition("a6"),
                        Utilities.getCoordinateAtPosition("a7")));
        assertTrue(t1.getStatus().isDone());
        assertTrue(t1.getToBoard().currentPlayer().isStalemated());
        assertFalse(t1.getToBoard().currentPlayer().isInCheck());
        assertFalse(t1.getToBoard().currentPlayer().isInCheckMate());
    } */
}
