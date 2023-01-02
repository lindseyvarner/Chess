package com.test;

import com.lindseyvarner.engine.players.Transition;
import org.junit.Test;

import static com.lindseyvarner.engine.BLACK;
import static com.lindseyvarner.engine.WHITE;
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

public class CastleTest {
    @Test
    public void testWhiteKingSideCastle() {
        final Board board = Board.createStandardBoard();
        final Transition t1 = board.currentPlayer()
                .makeMove(Move.Factory.createMove(board, Utilities.getCoordinateAtPosition("e2"),
                        Utilities.getCoordinateAtPosition("e4")));
        assertTrue(t1.getStatus().isDone());
        /* final Transition t2 = t1.getToBoard()
                .currentPlayer()
                .makeMove(Move.Factory.createMove(t1.getToBoard(), Utilities.getCoordinateAtPosition("e7"),
                        Utilities.getCoordinateAtPosition("e5")));
        assertTrue(t2.getStatus().isDone());
        final Transition t3 = t2.getToBoard()
                .currentPlayer()
                .makeMove(Move.Factory.createMove(t2.getToBoard(), Utilities.getCoordinateAtPosition("g1"),
                        Utilities.getCoordinateAtPosition("f3")));
        assertTrue(t3.getStatus().isDone());
        final Transition t4 = t3.getToBoard()
                .currentPlayer()
                .makeMove(Move.Factory.createMove(t3.getToBoard(), Utilities.getCoordinateAtPosition("d7"),
                        Utilities.getCoordinateAtPosition("d6")));
        assertTrue(t4.getStatus().isDone());
        final Transition t5 = t4.getToBoard()
                .currentPlayer()
                .makeMove(Move.Factory.createMove(t4.getToBoard(), Utilities.getCoordinateAtPosition("f1"),
                        Utilities.getCoordinateAtPosition("e2")));
        assertTrue(t5.getStatus().isDone());
        final Transition t6 = t5.getToBoard()
                .currentPlayer()
                .makeMove(Move.Factory.createMove(t5.getToBoard(), Utilities.getCoordinateAtPosition("d6"),
                        Utilities.getCoordinateAtPosition("d5")));
        assertTrue(t6.getStatus().isDone());
        final Move wm1 = Move.Factory
                .createMove(t6.getToBoard(), Utilities.getCoordinateAtPosition(
                        "e1"), Utilities.getCoordinateAtPosition("g1"));
        assertTrue(t6.getToBoard().currentPlayer().getLegalMoves().contains(wm1));
        final Transition t7 = t6.getToBoard().currentPlayer().makeMove(wm1);
        assertTrue(t7.getStatus().isDone());
        assertTrue(t7.getToBoard().whitePlayer().isCastled());
        assertFalse(t7.getToBoard().whitePlayer().isKingSideCastleCapable());
        assertFalse(t7.getToBoard().whitePlayer().isQueenSideCastleCapable());
    }

    @Test
    public void testWhiteQueenSideCastle() {
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
        final Transition t3 = t2.getToBoard()
                .currentPlayer()
                .makeMove(Move.Factory.createMove(t2.getToBoard(), Utilities.getCoordinateAtPosition("d2"),
                        Utilities.getCoordinateAtPosition("d3")));
        assertTrue(t3.getStatus().isDone());
        final Transition t4 = t3.getToBoard()
                .currentPlayer()
                .makeMove(Move.Factory.createMove(t3.getToBoard(), Utilities.getCoordinateAtPosition("d7"),
                        Utilities.getCoordinateAtPosition("d6")));
        assertTrue(t4.getStatus().isDone());
        final Transition t5 = t4.getToBoard()
                .currentPlayer()
                .makeMove(Move.Factory.createMove(t4.getToBoard(), Utilities.getCoordinateAtPosition("c1"),
                        Utilities.getCoordinateAtPosition("d2")));
        assertTrue(t5.getStatus().isDone());
        final Transition t6 = t5.getToBoard()
                .currentPlayer()
                .makeMove(Move.Factory.createMove(t5.getToBoard(), Utilities.getCoordinateAtPosition("d6"),
                        Utilities.getCoordinateAtPosition("d5")));
        assertTrue(t6.getStatus().isDone());
        final Transition t7 = t6.getToBoard()
                .currentPlayer()
                .makeMove(Move.Factory.createMove(t6.getToBoard(), Utilities.getCoordinateAtPosition("d1"),
                        Utilities.getCoordinateAtPosition("e2")));
        assertTrue(t7.getStatus().isDone());
        final Transition t8 = t7.getToBoard()
                .currentPlayer()
                .makeMove(Move.Factory.createMove(t7.getToBoard(), Utilities.getCoordinateAtPosition("h7"),
                        Utilities.getCoordinateAtPosition("h6")));
        assertTrue(t8.getStatus().isDone());
        final Transition t9 = t8.getToBoard()
                .currentPlayer()
                .makeMove(Move.Factory.createMove(t8.getToBoard(), Utilities.getCoordinateAtPosition("b1"),
                        Utilities.getCoordinateAtPosition("c3")));
        assertTrue(t9.getStatus().isDone());
        final Transition t10 = t9.getToBoard()
                .currentPlayer()
                .makeMove(Move.Factory.createMove(t9.getToBoard(), Utilities.getCoordinateAtPosition("h6"),
                        Utilities.getCoordinateAtPosition("h5")));
        assertTrue(t10.getStatus().isDone());
        final Move wm1 = Move.Factory
                .createMove(t10.getToBoard(), Utilities.getCoordinateAtPosition(
                        "e1"), Utilities.getCoordinateAtPosition("c1"));
        assertTrue(t10.getToBoard().currentPlayer().getLegalMoves().contains(wm1));
        final Transition t11 = t10.getToBoard().currentPlayer().makeMove(wm1);
        assertTrue(t11.getStatus().isDone());
        assertTrue(t11.getToBoard().whitePlayer().isCastled());
        assertFalse(t11.getToBoard().whitePlayer().isKingSideCastleCapable());
        assertFalse(t11.getToBoard().whitePlayer().isQueenSideCastleCapable());
    }

    @Test
    public void testBlackKingSideCastle() {
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
        final Transition t3 = t2.getToBoard()
                .currentPlayer()
                .makeMove(Move.Factory.createMove(t2.getToBoard(), Utilities.getCoordinateAtPosition("d2"),
                        Utilities.getCoordinateAtPosition("d3")));
        assertTrue(t3.getStatus().isDone());
        final Transition t4 = t3.getToBoard()
                .currentPlayer()
                .makeMove(Move.Factory.createMove(t3.getToBoard(), Utilities.getCoordinateAtPosition("g8"),
                        Utilities.getCoordinateAtPosition("f6")));
        assertTrue(t4.getStatus().isDone());
        final Transition t5 = t4.getToBoard()
                .currentPlayer()
                .makeMove(Move.Factory.createMove(t4.getToBoard(), Utilities.getCoordinateAtPosition("d3"),
                        Utilities.getCoordinateAtPosition("d4")));
        assertTrue(t5.getStatus().isDone());
        final Transition t6 = t5.getToBoard()
                .currentPlayer()
                .makeMove(Move.Factory.createMove(t5.getToBoard(), Utilities.getCoordinateAtPosition("f8"),
                        Utilities.getCoordinateAtPosition("e7")));
        assertTrue(t6.getStatus().isDone());
        final Transition t7 = t6.getToBoard()
                .currentPlayer()
                .makeMove(Move.Factory.createMove(t6.getToBoard(), Utilities.getCoordinateAtPosition("d4"),
                        Utilities.getCoordinateAtPosition("d5")));
        assertTrue(t7.getStatus().isDone());
        final Move wm1 = Move.Factory
                .createMove(t7.getToBoard(), Utilities.getCoordinateAtPosition(
                        "e8"), Utilities.getCoordinateAtPosition("g8"));
        assertTrue(t7.getToBoard().currentPlayer().getLegalMoves().contains(wm1));
        final Transition t8 = t7.getToBoard().currentPlayer().makeMove(wm1);
        assertTrue(t8.getStatus().isDone());
        assertTrue(t8.getToBoard().blackPlayer().isCastled());
        assertFalse(t8.getToBoard().blackPlayer().isKingSideCastleCapable());
        assertFalse(t8.getToBoard().blackPlayer().isQueenSideCastleCapable());
    }

    @Test
    public void testBlackQueenSideCastle() {
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
        final Transition t3 = t2.getToBoard()
                .currentPlayer()
                .makeMove(Move.Factory.createMove(t2.getToBoard(), Utilities.getCoordinateAtPosition("d2"),
                        Utilities.getCoordinateAtPosition("d3")));
        assertTrue(t3.getStatus().isDone());
        final Transition t4 = t3.getToBoard()
                .currentPlayer()
                .makeMove(Move.Factory.createMove(t3.getToBoard(), Utilities.getCoordinateAtPosition("d8"),
                        Utilities.getCoordinateAtPosition("e7")));
        assertTrue(t4.getStatus().isDone());
        final Transition t5 = t4.getToBoard()
                .currentPlayer()
                .makeMove(Move.Factory.createMove(t4.getToBoard(), Utilities.getCoordinateAtPosition("b1"),
                        Utilities.getCoordinateAtPosition("c3")));
        assertTrue(t5.getStatus().isDone());
        final Transition t6 = t5.getToBoard()
                .currentPlayer()
                .makeMove(Move.Factory.createMove(t5.getToBoard(), Utilities.getCoordinateAtPosition("b8"),
                        Utilities.getCoordinateAtPosition("c6")));
        assertTrue(t6.getStatus().isDone());
        final Transition t7 = t6.getToBoard()
                .currentPlayer()
                .makeMove(Move.Factory.createMove(t6.getToBoard(), Utilities.getCoordinateAtPosition("c1"),
                        Utilities.getCoordinateAtPosition("d2")));
        assertTrue(t7.getStatus().isDone());
        final Transition t8 = t7.getToBoard()
                .currentPlayer()
                .makeMove(Move.Factory.createMove(t7.getToBoard(), Utilities.getCoordinateAtPosition("d7"),
                        Utilities.getCoordinateAtPosition("d6")));
        assertTrue(t8.getStatus().isDone());
        final Transition t9 = t8.getToBoard()
                .currentPlayer()
                .makeMove(Move.Factory.createMove(t8.getToBoard(), Utilities.getCoordinateAtPosition("f1"),
                        Utilities.getCoordinateAtPosition("e2")));
        assertTrue(t9.getStatus().isDone());
        final Transition t10 = t9.getToBoard()
                .currentPlayer()
                .makeMove(Move.Factory.createMove(t9.getToBoard(), Utilities.getCoordinateAtPosition("c8"),
                        Utilities.getCoordinateAtPosition("d7")));
        assertTrue(t10.getStatus().isDone());
        final Transition t11 = t10.getToBoard()
                .currentPlayer()
                .makeMove(
                        Move.Factory.createMove(t10.getToBoard(), Utilities.getCoordinateAtPosition("g1"),
                                Utilities.getCoordinateAtPosition("f3")));
        assertTrue(t11.getStatus().isDone());
        final Move wm1 = Move.Factory
                .createMove(t11.getToBoard(), Utilities.getCoordinateAtPosition(
                        "e8"), Utilities.getCoordinateAtPosition("c8"));
        assertTrue(t11.getToBoard().currentPlayer().getLegalMoves().contains(wm1));
        final Transition t12 = t11.getToBoard().currentPlayer().makeMove(wm1);
        assertTrue(t12.getStatus().isDone());
        assertTrue(t12.getToBoard().blackPlayer().isCastled());
        assertFalse(t12.getToBoard().blackPlayer().isKingSideCastleCapable());
        assertFalse(t12.getToBoard().blackPlayer().isQueenSideCastleCapable());
    }

    @Test
    public void testCastleBugOne() {
        final Board board = Board.createStandardBoard();
        final Transition t1 = board.currentPlayer()
                .makeMove(Move.Factory.createMove(board, Utilities.getCoordinateAtPosition("e2"),
                        Utilities.getCoordinateAtPosition("e4")));
        assertTrue(t1.getStatus().isDone());
        final Transition t2 = t1.getToBoard()
                .currentPlayer()
                .makeMove(Utilities.createMove(t1.getToBoard(), Utilities.getCoordinateAtPosition("d7"),
                        Utilities.getCoordinateAtPosition("d5")));
        assertTrue(t2.Utilities().isDone());
        final Transition t3 = t2.getToBoard()
                .currentPlayer()
                .makeMove(Utilities.createMove(t2.getToBoard(), Utilities.getCoordinateAtPosition("e4"),
                        Utilities.getCoordinateAtPosition("e5")));
        assertTrue(t3.Utilities().isDone());
        final Transition t4 = t3.getToBoard()
                .currentPlayer()
                .makeMove(Utilities.createMove(t3.getToBoard(), Utilities.getCoordinateAtPosition("c8"),
                        Utilities.getCoordinateAtPosition("f5")));
        assertTrue(t4.Utilities().isDone());
        final Transition t5 = t4.getToBoard()
                .currentPlayer()
                .makeMove(Utilities.createMove(t4.getToBoard(), Utilities.getCoordinateAtPosition("f1"),
                        Utilities.getCoordinateAtPosition("d3")));
        assertTrue(t5.Utilities().isDone());
        final Transition t6 = t5.getToBoard()
                .currentPlayer()
                .makeMove(Utilities.createMove(t5.getToBoard(), Utilities.getCoordinateAtPosition("f5"),
                        Utilities.getCoordinateAtPosition("d3")));
        assertTrue(t6.Utilities().isDone());
        final Transition t7 = t6.getToBoard()
                .currentPlayer()
                .makeMove(Utilities.createMove(t6.getToBoard(), Utilities.getCoordinateAtPosition("c2"),
                        Utilities.getCoordinateAtPosition("d3")));
        assertTrue(t7.Utilities().isDone());
        final Transition t8 = t7.getToBoard()
                .currentPlayer()
                .makeMove(Utilities.createMove(t7.getToBoard(), Utilities.getCoordinateAtPosition("e7"),
                        Utilities.getCoordinateAtPosition("e6")));
        assertTrue(t8.Utilities().isDone());
        final Transition t9 = t8.getToBoard()
                .currentPlayer()
                .makeMove(Utilities.createMove(t8.getToBoard(), Utilities.getCoordinateAtPosition("d1"),
                        Utilities.getCoordinateAtPosition("a4")));
        assertTrue(t9.Utilities().isDone());
        final Transition t10 = t9.getToBoard()
                .currentPlayer()
                .makeMove(Utilities.createMove(t9.getToBoard(), Utilities.getCoordinateAtPosition("d8"),
                        Utilities.getCoordinateAtPosition("d7")));
        assertTrue(t10.Utilities().isDone());
        final Transition t11 = t10.getToBoard()
                .currentPlayer()
                .makeMove(
                        Utilities.createMove(t10.getToBoard(), Utilities.getCoordinateAtPosition("b1"),
                                Utilities.getCoordinateAtPosition("c3")));
        assertTrue(t11.Utilities().isDone());

        final MoveStrategy moveStrategy = new StockAlphaBeta(6);

        moveStrategy.execute(t11.getToBoard());
    }

    @Test
    public void testNoCastlingOutOfCheck() {
        final Board board = FenUtilities.createGameFromFEN("r3k2r/1pN1nppp/p3p3/3p4/8/8/PPPK1PPP/R6R b kq - 1 18");
        final Move illegalCastleMove = Utilities
                .createMove(board, Utilities.getCoordinateAtPosition("e8"), Utilities.getCoordinateAtPosition("c8"));
        final Transition t1 = board.currentPlayer()
                .makeMove(illegalCastleMove);
        assertFalse(t1.Utilities().isDone()); */
    }
}
