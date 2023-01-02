package com.test;

//import com.lindseyvarner.engine.players;
import com.lindseyvarner.engine.players.Transition;
import org.junit.Test;

import static com.lindseyvarner.engine.Alliance.BLACK;
import static com.lindseyvarner.engine.Alliance.WHITE;
import static org.junit.jupiter.api.Assertions.*;
import com.lindseyvarner.engine.Alliance;
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

public class PlayerTest {

    /* @Test
    public void testSimpleEvaluation() {
        final Board board = Board.createStandardBoard();
        final Transition t1 = board.currentPlayer()
                .makeMove(Move.Factory.createMove(board, Utilities.getCoordinateAtPosition("e2"),
                        Utilities.getCoordinateAtPosition("e4")));
        assertTrue(t1.getStatus().isDone());
        final Transition t2 = t1.getToBoard()
                .currentPlayer()
                .makeMove(Move.Factory.createMove(t1.getToBoard(), Utilities.getCoordinateAtPosition("e7"),
                        Utilities.getCoordinateAtPosition("e5")));
        assertTrue(t2.getStatus().isDone());
        assertEquals(StandardBoardEvaluator.get().evaluate(t2.getToBoard(), 0), 0);
    }

    @Test
    public void testBug() {
        final Board board = Board.createStandardBoard();
        final Transition t1 = board.currentPlayer()
                .makeMove(Move.Factory.createMove(board, Utilities.getCoordinateAtPosition("c2"),
                        Utilities.getCoordinateAtPosition("c3")));
        assertTrue(t1.getStatus().isDone());
        final Transition t2 = t1.getToBoard()
                .currentPlayer()
                .makeMove(Move.Factory.createMove(t1.getToBoard(), Utilities.getCoordinateAtPosition("b8"),
                        Utilities.getCoordinateAtPosition("a6")));
        assertTrue(t2.getStatus().isDone());
        final Transition t3 = t2.getToBoard()
                .currentPlayer()
                .makeMove(Move.Factory.createMove(t2.getToBoard(), Utilities.getCoordinateAtPosition("d1"),
                        Utilities.getCoordinateAtPosition("a4")));
        assertTrue(t3.getStatus().isDone());
        final Transition t4 = t3.getToBoard()
                .currentPlayer()
                .makeMove(Move.Factory.createMove(t3.getToBoard(), Utilities.getCoordinateAtPosition("d7"),
                        Utilities.getCoordinateAtPosition("d6")));
        assertFalse(t4.getStatus().isDone());
    }

    @Test
    public void testDiscoveredCheck() {
        final Builder builder = new Builder();
        
        builder.setPiece(new King(BLACK, 4, false));
        builder.setPiece(new Rook(BLACK, 24));
        
        builder.setPiece(new Bishop(WHITE, 44));
        builder.setPiece(new Rook(WHITE, 52));
        builder.setPiece(new King(WHITE, 58, false));
        
        builder.setMoveMaker(WHITE);
        final Board board = builder.build();
        final Transition t1 = board.currentPlayer()
                .makeMove(Move.Factory.createMove(board, Utilities.getCoordinateAtPosition("e3"),
                        Utilities.getCoordinateAtPosition("b6")));
        assertTrue(t1.getStatus().isDone());
        assertTrue(t1.getToBoard().currentPlayer().isInCheck());
        final Transition t2 = t1.getToBoard()
                .currentPlayer()
                .makeMove(Move.Factory.createMove(t1.getToBoard(), Utilities.getCoordinateAtPosition("a5"),
                        Utilities.getCoordinateAtPosition("b5")));
        assertFalse(t2.getStatus().isDone());
        final Transition t3 = t1.getToBoard()
                .currentPlayer()
                .makeMove(Move.Factory.createMove(t1.getToBoard(), Utilities.getCoordinateAtPosition("a5"),
                        Utilities.getCoordinateAtPosition("e5")));
        assertTrue(t3.getStatus().isDone());
    }

    @Test
    public void testUnmakeMove() {
        final Board board = Board.createStandardBoard();
        final Move m1 = Move.Factory.createMove(board, Utilities.getCoordinateAtPosition("e2"),
                Utilities.getCoordinateAtPosition("e4"));
        final Transition t1 = board.currentPlayer()
                .makeMove(m1);
        assertTrue(t1.getStatus().isDone());
        t1.getToBoard().currentPlayer().getOpponent().unMakeMove(m1);
    }

    @Test
    public void testIllegalMove() {
        final Board board = Board.createStandardBoard();
        final Move m1 = Move.Factory.createMove(board, Utilities.getCoordinateAtPosition("e2"),
                Utilities.getCoordinateAtPosition("e6"));
        final Transition t1 = board.currentPlayer()
                .makeMove(m1);
        assertFalse(t1.getStatus().isDone());
    } */

}
