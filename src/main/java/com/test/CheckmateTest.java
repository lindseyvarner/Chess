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

public class CheckmateTest {
    public class TestCheckmate {

        @Test
        public void testFoolsMate() {

            /* final Board board = Board.createStandardBoard();
            final Transition t1 = board.currentPlayer()
                    .makeMove(Move.Factory.createMove(board, Utilities.getCoordinateAtPosition("f2"),
                            Utilities.getCoordinateAtPosition("f3")));

            assertTrue(t1.getStatus().isDone());

            final Transition t2 = t1.getToBoard()
                    .currentPlayer()
                    .makeMove(Move.Factory.createMove(t1.getToBoard(), Utilities.getCoordinateAtPosition("e7"),
                            Utilities.getCoordinateAtPosition("e5")));

            assertTrue(t2.getStatus().isDone());

            final Transition t3 = t2.getToBoard()
                    .currentPlayer()
                    .makeMove(Move.Factory.createMove(t2.getToBoard(), Utilities.getCoordinateAtPosition("g2"),
                            Utilities.getCoordinateAtPosition("g4")));

            assertTrue(t3.getStatus().isDone());

            final Transition t4 = t3.getToBoard()
                    .currentPlayer()
                    .makeMove(Move.Factory.createMove(t3.getToBoard(), Utilities.getCoordinateAtPosition("d8"),
                            Utilities.getCoordinateAtPosition("h4")));

            assertTrue(t4.getStatus().isDone());

            assertTrue(t4.getToBoard().currentPlayer().isInCheckMate());

        }

        @Test
        public void testScholarsMate() {

            final Board board = Board.createStandardBoard();
            final Transition t1 = board.currentPlayer()
                    .makeMove(Move.Factory.createMove(board, Utilities.getCoordinateAtPosition("e2"),
                            Utilities.getCoordinateAtPosition("e4")));

            assertTrue(t1.getStatus().isDone());

            final Transition t2 = t1.getToBoard()
                    .currentPlayer()
                    .makeMove(Move.Factory.createMove(t1.getToBoard(), Utilities.getCoordinateAtPosition("a7"),
                            Utilities.getCoordinateAtPosition("a6")));

            assertTrue(t2.getStatus().isDone());

            final Transition t3 = t2.getToBoard()
                    .currentPlayer()
                    .makeMove(Move.Factory.createMove(t2.getToBoard(), Utilities.getCoordinateAtPosition("d1"),
                            Utilities.getCoordinateAtPosition("f3")));

            assertTrue(t3.getStatus().isDone());

            final Transition t4 = t3.getToBoard()
                    .currentPlayer()
                    .makeMove(Move.Factory.createMove(t3.getToBoard(), Utilities.getCoordinateAtPosition("a6"),
                            Utilities.getCoordinateAtPosition("a5")));

            assertTrue(t4.getStatus().isDone());

            final Transition t5 = t4.getToBoard()
                    .currentPlayer()
                    .makeMove(Move.Factory.createMove(t4.getToBoard(), Utilities.getCoordinateAtPosition("f1"),
                            Utilities.getCoordinateAtPosition("c4")));

            assertTrue(t5.getStatus().isDone());

            final Transition t6 = t5.getToBoard()
                    .currentPlayer()
                    .makeMove(Move.Factory.createMove(t5.getToBoard(), Utilities.getCoordinateAtPosition("a5"),
                            Utilities.getCoordinateAtPosition("a4")));

            assertTrue(t6.getStatus().isDone());

            final Transition t7 = t6.getToBoard()
                    .currentPlayer()
                    .makeMove(Move.Factory.createMove(t6.getToBoard(), Utilities.getCoordinateAtPosition("f3"),
                            Utilities.getCoordinateAtPosition("f7")));

            assertTrue(t7.getStatus().isDone());
            assertTrue(t7.getToBoard().currentPlayer().isInCheckMate());

        }

        @Test
        public void testLegalsMate() {

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
                    .makeMove(Move.Factory.createMove(t2.getToBoard(), Utilities.getCoordinateAtPosition("f1"),
                            Utilities.getCoordinateAtPosition("c4")));

            assertTrue(t3.getStatus().isDone());

            final Transition t4 = t3.getToBoard()
                    .currentPlayer()
                    .makeMove(Move.Factory.createMove(t3.getToBoard(), Utilities.getCoordinateAtPosition("d7"),
                            Utilities.getCoordinateAtPosition("d6")));

            assertTrue(t4.getStatus().isDone());

            final Transition t5 = t4.getToBoard()
                    .currentPlayer()
                    .makeMove(Move.Factory.createMove(t4.getToBoard(), Utilities.getCoordinateAtPosition("g1"),
                            Utilities.getCoordinateAtPosition("f3")));

            assertTrue(t5.getStatus().isDone());

            final Transition t6 = t5.getToBoard()
                    .currentPlayer()
                    .makeMove(Move.Factory.createMove(t5.getToBoard(), Utilities.getCoordinateAtPosition("c8"),
                            Utilities.getCoordinateAtPosition("g4")));

            assertTrue(t6.getStatus().isDone());

            final Transition t7 = t6.getToBoard()
                    .currentPlayer()
                    .makeMove(Move.Factory.createMove(t6.getToBoard(), Utilities.getCoordinateAtPosition("b1"),
                            Utilities.getCoordinateAtPosition("c3")));

            assertTrue(t7.getStatus().isDone());

            final Transition t8 = t7.getToBoard()
                    .currentPlayer()
                    .makeMove(Move.Factory.createMove(t7.getToBoard(), Utilities.getCoordinateAtPosition("g7"),
                            Utilities.getCoordinateAtPosition("g6")));

            assertTrue(t8.getStatus().isDone());

            final Transition t9 = t8.getToBoard()
                    .currentPlayer()
                    .makeMove(Move.Factory.createMove(t8.getToBoard(), Utilities.getCoordinateAtPosition("f3"),
                            Utilities.getCoordinateAtPosition("e5")));

            assertTrue(t9.getStatus().isDone());

            final Transition t10 = t9.getToBoard()
                    .currentPlayer()
                    .makeMove(Move.Factory.createMove(t9.getToBoard(), Utilities.getCoordinateAtPosition("g4"),
                            Utilities.getCoordinateAtPosition("d1")));

            assertTrue(t10.getStatus().isDone());

            final Transition t11 = t10.getToBoard()
                    .currentPlayer()
                    .makeMove(Move.Factory.createMove(t10.getToBoard(), Utilities.getCoordinateAtPosition("c4"),
                            Utilities.getCoordinateAtPosition("f7")));

            assertTrue(t11.getStatus().isDone());

            final Transition t12 = t11.getToBoard()
                    .currentPlayer()
                    .makeMove(Move.Factory.createMove(t11.getToBoard(), Utilities.getCoordinateAtPosition("e8"),
                            Utilities.getCoordinateAtPosition("e7")));

            assertTrue(t12.getStatus().isDone());

            final Transition t13 = t12.getToBoard()
                    .currentPlayer()
                    .makeMove(Move.Factory.createMove(t12.getToBoard(), Utilities.getCoordinateAtPosition("c3"),
                            Utilities.getCoordinateAtPosition("d5")));

            assertTrue(t13.getStatus().isDone());
            assertTrue(t13.getToBoard().currentPlayer().isInCheckMate());

        }

        @Test
        public void testSevenMoveMate() {

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
                            Utilities.getCoordinateAtPosition("d4")));

            assertTrue(t3.getStatus().isDone());

            final Transition t4 = t3.getToBoard()
                    .currentPlayer()
                    .makeMove(Move.Factory.createMove(t3.getToBoard(), Utilities.getCoordinateAtPosition("d7"),
                            Utilities.getCoordinateAtPosition("d6")));

            assertTrue(t4.getStatus().isDone());

            final Transition t5 = t4.getToBoard()
                    .currentPlayer()
                    .makeMove(Move.Factory.createMove(t4.getToBoard(), Utilities.getCoordinateAtPosition("d4"),
                            Utilities.getCoordinateAtPosition("e5")));

            assertTrue(t5.getStatus().isDone());

            final Transition t6 = t5.getToBoard()
                    .currentPlayer()
                    .makeMove(Move.Factory.createMove(t5.getToBoard(), Utilities.getCoordinateAtPosition("d8"),
                            Utilities.getCoordinateAtPosition("e7")));

            assertTrue(t6.getStatus().isDone());

            final Transition t7 = t6.getToBoard()
                    .currentPlayer()
                    .makeMove(Move.Factory.createMove(t6.getToBoard(), Utilities.getCoordinateAtPosition("e5"),
                            Utilities.getCoordinateAtPosition("d6")));

            assertTrue(t7.getStatus().isDone());

            final Transition t8 = t7.getToBoard()
                    .currentPlayer()
                    .makeMove(Move.Factory.createMove(t7.getToBoard(), Utilities.getCoordinateAtPosition("e7"),
                            Utilities.getCoordinateAtPosition("e4")));

            assertTrue(t8.getStatus().isDone());

            final Transition t9 = t8.getToBoard()
                    .currentPlayer()
                    .makeMove(Move.Factory.createMove(t8.getToBoard(), Utilities.getCoordinateAtPosition("f1"),
                            Utilities.getCoordinateAtPosition("e2")));

            assertTrue(t9.getStatus().isDone());

            final Transition t10 = t9.getToBoard()
                    .currentPlayer()
                    .makeMove(Move.Factory.createMove(t9.getToBoard(), Utilities.getCoordinateAtPosition("e4"),
                            Utilities.getCoordinateAtPosition("g2")));

            assertTrue(t10.getStatus().isDone());

            final Transition t11 = t10.getToBoard()
                    .currentPlayer()
                    .makeMove(Move.Factory.createMove(t10.getToBoard(), Utilities.getCoordinateAtPosition("d6"),
                            Utilities.getCoordinateAtPosition("c7")));

            assertTrue(t11.getStatus().isDone());

            final Transition t12 = t11.getToBoard()
                    .currentPlayer()
                    .makeMove(Move.Factory.createMove(t11.getToBoard(), Utilities.getCoordinateAtPosition("g2"),
                            Utilities.getCoordinateAtPosition("h1")));

            assertTrue(t12.getStatus().isDone());

            final Transition t13 = t12.getToBoard()
                    .currentPlayer()
                    .makeMove(Move.Factory.createMove(t12.getToBoard(), Utilities.getCoordinateAtPosition("d1"),
                            Utilities.getCoordinateAtPosition("d8")));

            assertTrue(t13.getStatus().isDone());
            assertTrue(t13.getToBoard().currentPlayer().isInCheckMate());

        }

        @Test
        public void testGrecoGame() {

            final Board board = Board.createStandardBoard();
            final Transition t1 = board.currentPlayer()
                    .makeMove(Move.Factory.createMove(board, Utilities.getCoordinateAtPosition("d2"),
                            Utilities.getCoordinateAtPosition("d4")));

            assertTrue(t1.getStatus().isDone());

            final Transition t2 = t1.getToBoard()
                    .currentPlayer()
                    .makeMove(Move.Factory.createMove(t1.getToBoard(), Utilities.getCoordinateAtPosition("g8"),
                            Utilities.getCoordinateAtPosition("f6")));

            assertTrue(t2.getStatus().isDone());

            final Transition t3 = t2.getToBoard()
                    .currentPlayer()
                    .makeMove(Move.Factory.createMove(t2.getToBoard(), Utilities.getCoordinateAtPosition("b1"),
                            Utilities.getCoordinateAtPosition("d2")));

            assertTrue(t3.getStatus().isDone());

            final Transition t4 = t3.getToBoard()
                    .currentPlayer()
                    .makeMove(Move.Factory.createMove(t3.getToBoard(), Utilities.getCoordinateAtPosition("e7"),
                            Utilities.getCoordinateAtPosition("e5")));

            assertTrue(t4.getStatus().isDone());

            final Transition t5 = t4.getToBoard()
                    .currentPlayer()
                    .makeMove(Move.Factory.createMove(t4.getToBoard(), Utilities.getCoordinateAtPosition("d4"),
                            Utilities.getCoordinateAtPosition("e5")));

            assertTrue(t5.getStatus().isDone());

            final Transition t6 = t5.getToBoard()
                    .currentPlayer()
                    .makeMove(Move.Factory.createMove(t5.getToBoard(), Utilities.getCoordinateAtPosition("f6"),
                            Utilities.getCoordinateAtPosition("g4")));


            assertTrue(t6.getStatus().isDone());

            final Transition t7 = t6.getToBoard()
                    .currentPlayer()
                    .makeMove(Move.Factory.createMove(t6.getToBoard(), Utilities.getCoordinateAtPosition("h2"),
                            Utilities.getCoordinateAtPosition("h3")));

            assertTrue(t7.getStatus().isDone());

            final Transition t8 = t7.getToBoard()
                    .currentPlayer()
                    .makeMove(Move.Factory.createMove(t7.getToBoard(), Utilities.getCoordinateAtPosition("g4"),
                            Utilities.getCoordinateAtPosition("e3")));

            assertTrue(t8.getStatus().isDone());

            final Transition t9 = t8.getToBoard()
                    .currentPlayer()
                    .makeMove(Move.Factory.createMove(t8.getToBoard(), Utilities.getCoordinateAtPosition("f2"),
                            Utilities.getCoordinateAtPosition("e3")));

            assertTrue(t9.getStatus().isDone());

            final Transition t10 = t9.getToBoard()
                    .currentPlayer()
                    .makeMove(Move.Factory.createMove(t9.getToBoard(), Utilities.getCoordinateAtPosition("d8"),
                            Utilities.getCoordinateAtPosition("h4")));

            assertTrue(t10.getStatus().isDone());

            final Transition t11 = t10.getToBoard()
                    .currentPlayer()
                    .makeMove(Move.Factory.createMove(t10.getToBoard(), Utilities.getCoordinateAtPosition("g2"),
                            Utilities.getCoordinateAtPosition("g3")));

            assertTrue(t11.getStatus().isDone());

            final Transition t12 = t11.getToBoard()
                    .currentPlayer()
                    .makeMove(Move.Factory.createMove(t11.getToBoard(), Utilities.getCoordinateAtPosition("h4"),
                            Utilities.getCoordinateAtPosition("g3")));

            assertTrue(t12.getStatus().isDone());
            assertTrue(t12.getToBoard().currentPlayer().isInCheckMate());

        }

        @Test
        public void testOlympicGame() {

            final Board board = Board.createStandardBoard();
            final Transition t1 = board.currentPlayer()
                    .makeMove(Move.Factory.createMove(board, Utilities.getCoordinateAtPosition("e2"),
                            Utilities.getCoordinateAtPosition("e4")));

            assertTrue(t1.getStatus().isDone());

            final Transition t2 = t1.getToBoard()
                    .currentPlayer()
                    .makeMove(Move.Factory.createMove(t1.getToBoard(), Utilities.getCoordinateAtPosition("c7"),
                            Utilities.getCoordinateAtPosition("c6")));

            assertTrue(t2.getStatus().isDone());

            final Transition t3 = t2.getToBoard()
                    .currentPlayer()
                    .makeMove(Move.Factory.createMove(t2.getToBoard(), Utilities.getCoordinateAtPosition("g1"),
                            Utilities.getCoordinateAtPosition("f3")));

            assertTrue(t3.getStatus().isDone());

            final Transition t4 = t3.getToBoard()
                    .currentPlayer()
                    .makeMove(Move.Factory.createMove(t3.getToBoard(), Utilities.getCoordinateAtPosition("d7"),
                            Utilities.getCoordinateAtPosition("d5")));

            assertTrue(t4.getStatus().isDone());

            final Transition t5 = t4.getToBoard()
                    .currentPlayer()
                    .makeMove(Move.Factory.createMove(t4.getToBoard(), Utilities.getCoordinateAtPosition("b1"),
                            Utilities.getCoordinateAtPosition("c3")));

            assertTrue(t5.getStatus().isDone());

            final Transition t6 = t5.getToBoard()
                    .currentPlayer()
                    .makeMove(Move.Factory.createMove(t5.getToBoard(), Utilities.getCoordinateAtPosition("d5"),
                            Utilities.getCoordinateAtPosition("e4")));

            assertTrue(t6.getStatus().isDone());

            final Transition t7 = t6.getToBoard()
                    .currentPlayer()
                    .makeMove(Move.Factory.createMove(t6.getToBoard(), Utilities.getCoordinateAtPosition("c3"),
                            Utilities.getCoordinateAtPosition("e4")));

            assertTrue(t7.getStatus().isDone());

            final Transition t8 = t7.getToBoard()
                    .currentPlayer()
                    .makeMove(Move.Factory.createMove(t7.getToBoard(), Utilities.getCoordinateAtPosition("b8"),
                            Utilities.getCoordinateAtPosition("d7")));

            assertTrue(t8.getStatus().isDone());

            final Transition t9 = t8.getToBoard()
                    .currentPlayer()
                    .makeMove(Move.Factory.createMove(t8.getToBoard(), Utilities.getCoordinateAtPosition("d1"),
                            Utilities.getCoordinateAtPosition("e2")));

            assertTrue(t9.getStatus().isDone());

            final Transition t10 = t9.getToBoard()
                    .currentPlayer()
                    .makeMove(Move.Factory.createMove(t9.getToBoard(), Utilities.getCoordinateAtPosition("g8"),
                            Utilities.getCoordinateAtPosition("f6")));

            assertTrue(t10.getStatus().isDone());

            final Transition t11 = t10.getToBoard()
                    .currentPlayer()
                    .makeMove(Move.Factory.createMove(t10.getToBoard(), Utilities.getCoordinateAtPosition("e4"),
                            Utilities.getCoordinateAtPosition("d6")));

            assertTrue(t11.getStatus().isDone());
            assertTrue(t11.getToBoard().currentPlayer().isInCheckMate());

        }

        @Test
        public void testAnotherGame() {

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
                    .makeMove(Move.Factory.createMove(t2.getToBoard(), Utilities.getCoordinateAtPosition("g1"),
                            Utilities.getCoordinateAtPosition("f3")));

            assertTrue(t3.getStatus().isDone());

            final Transition t4 = t3.getToBoard()
                    .currentPlayer()
                    .makeMove(Move.Factory.createMove(t3.getToBoard(), Utilities.getCoordinateAtPosition("b8"),
                            Utilities.getCoordinateAtPosition("c6")));

            assertTrue(t4.getStatus().isDone());

            final Transition t5 = t4.getToBoard()
                    .currentPlayer()
                    .makeMove(Move.Factory.createMove(t4.getToBoard(), Utilities.getCoordinateAtPosition("f1"),
                            Utilities.getCoordinateAtPosition("c4")));

            assertTrue(t5.getStatus().isDone());

            final Transition t6 = t5.getToBoard()
                    .currentPlayer()
                    .makeMove(Move.Factory.createMove(t5.getToBoard(), Utilities.getCoordinateAtPosition("c6"),
                            Utilities.getCoordinateAtPosition("d4")));

            assertTrue(t6.getStatus().isDone());

            final Transition t7 = t6.getToBoard()
                    .currentPlayer()
                    .makeMove(Move.Factory.createMove(t6.getToBoard(), Utilities.getCoordinateAtPosition("f3"),
                            Utilities.getCoordinateAtPosition("e5")));

            assertTrue(t7.getStatus().isDone());

            final Transition t8 = t7.getToBoard()
                    .currentPlayer()
                    .makeMove(Move.Factory.createMove(t7.getToBoard(), Utilities.getCoordinateAtPosition("d8"),
                            Utilities.getCoordinateAtPosition("g5")));

            assertTrue(t8.getStatus().isDone());

            final Transition t9 = t8.getToBoard()
                    .currentPlayer()
                    .makeMove(Move.Factory.createMove(t8.getToBoard(), Utilities.getCoordinateAtPosition("e5"),
                            Utilities.getCoordinateAtPosition("f7")));

            assertTrue(t9.getStatus().isDone());

            final Transition t10 = t9.getToBoard()
                    .currentPlayer()
                    .makeMove(Move.Factory.createMove(t9.getToBoard(), Utilities.getCoordinateAtPosition("g5"),
                            Utilities.getCoordinateAtPosition("g2")));

            assertTrue(t10.getStatus().isDone());

            final Transition t11 = t10.getToBoard()
                    .currentPlayer()
                    .makeMove(Move.Factory.createMove(t10.getToBoard(), Utilities.getCoordinateAtPosition("h1"),
                            Utilities.getCoordinateAtPosition("f1")));

            assertTrue(t11.getStatus().isDone());

            final Transition t12 = t11.getToBoard()
                    .currentPlayer()
                    .makeMove(Move.Factory.createMove(t11.getToBoard(), Utilities.getCoordinateAtPosition("g2"),
                            Utilities.getCoordinateAtPosition("e4")));

            assertTrue(t12.getStatus().isDone());

            final Transition t13 = t12.getToBoard()
                    .currentPlayer()
                    .makeMove(Move.Factory.createMove(t12.getToBoard(), Utilities.getCoordinateAtPosition("c4"),
                            Utilities.getCoordinateAtPosition("e2")));

            assertTrue(t13.getStatus().isDone());

            final Transition t14 = t13.getToBoard()
                    .currentPlayer()
                    .makeMove(Move.Factory.createMove(t13.getToBoard(), Utilities.getCoordinateAtPosition("d4"),
                            Utilities.getCoordinateAtPosition("f3")));

            assertTrue(t14.getStatus().isDone());
            assertTrue(t14.getToBoard().currentPlayer().isInCheckMate());

        }

        @Test
        public void testSmotheredMate() {

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
                    .makeMove(Move.Factory.createMove(t2.getToBoard(), Utilities.getCoordinateAtPosition("g1"),
                            Utilities.getCoordinateAtPosition("e2")));

            assertTrue(t3.getStatus().isDone());

            final Transition t4 = t3.getToBoard()
                    .currentPlayer()
                    .makeMove(Move.Factory.createMove(t3.getToBoard(), Utilities.getCoordinateAtPosition("b8"),
                            Utilities.getCoordinateAtPosition("c6")));

            assertTrue(t4.getStatus().isDone());

            final Transition t5 = t4.getToBoard()
                    .currentPlayer()
                    .makeMove(Move.Factory.createMove(t4.getToBoard(), Utilities.getCoordinateAtPosition("b1"),
                            Utilities.getCoordinateAtPosition("c3")));

            assertTrue(t4.getStatus().isDone());

            final Transition t6 = t5.getToBoard()
                    .currentPlayer()
                    .makeMove(Move.Factory.createMove(t5.getToBoard(), Utilities.getCoordinateAtPosition("c6"),
                            Utilities.getCoordinateAtPosition("d4")));

            assertTrue(t6.getStatus().isDone());

            final Transition t7 = t6.getToBoard()
                    .currentPlayer()
                    .makeMove(Move.Factory.createMove(t6.getToBoard(), Utilities.getCoordinateAtPosition("g2"),
                            Utilities.getCoordinateAtPosition("g3")));

            assertTrue(t7.getStatus().isDone());

            final Transition t8 = t7.getToBoard()
                    .currentPlayer()
                    .makeMove(Move.Factory.createMove(t7.getToBoard(), Utilities.getCoordinateAtPosition("d4"),
                            Utilities.getCoordinateAtPosition("f3")));

            assertTrue(t8.getStatus().isDone());
            assertTrue(t8.getToBoard().currentPlayer().isInCheckMate());

        }

        @Test
        public void testHippopotamusMate() {

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
                    .makeMove(Move.Factory.createMove(t2.getToBoard(), Utilities.getCoordinateAtPosition("g1"),
                            Utilities.getCoordinateAtPosition("e2")));

            assertTrue(t3.getStatus().isDone());

            final Transition t4 = t3.getToBoard()
                    .currentPlayer()
                    .makeMove(Move.Factory.createMove(t3.getToBoard(), Utilities.getCoordinateAtPosition("d8"),
                            Utilities.getCoordinateAtPosition("h4")));

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
                    .makeMove(Move.Factory.createMove(t6.getToBoard(), Utilities.getCoordinateAtPosition("g2"),
                            Utilities.getCoordinateAtPosition("g3")));

            assertTrue(t7.getStatus().isDone());

            final Transition t8 = t7.getToBoard()
                    .currentPlayer()
                    .makeMove(Move.Factory.createMove(t7.getToBoard(), Utilities.getCoordinateAtPosition("h4"),
                            Utilities.getCoordinateAtPosition("g5")));


            assertTrue(t8.getStatus().isDone());

            final Transition t9 = t8.getToBoard()
                    .currentPlayer()
                    .makeMove(Move.Factory.createMove(t8.getToBoard(), Utilities.getCoordinateAtPosition("d2"),
                            Utilities.getCoordinateAtPosition("d4")));

            assertTrue(t9.getStatus().isDone());

            final Transition t10 = t9.getToBoard()
                    .currentPlayer()
                    .makeMove(Move.Factory.createMove(t9.getToBoard(), Utilities.getCoordinateAtPosition("c6"),
                            Utilities.getCoordinateAtPosition("d4")));

            assertTrue(t10.getStatus().isDone());

            final Transition t11 = t10.getToBoard()
                    .currentPlayer()
                    .makeMove(Move.Factory.createMove(t10.getToBoard(), Utilities.getCoordinateAtPosition("c1"),
                            Utilities.getCoordinateAtPosition("g5")));

            assertTrue(t11.getStatus().isDone());

            final Transition t12 = t11.getToBoard()
                    .currentPlayer()
                    .makeMove(Move.Factory.createMove(t11.getToBoard(), Utilities.getCoordinateAtPosition("d4"),
                            Utilities.getCoordinateAtPosition("f3")));

            assertTrue(t12.getStatus().isDone());
            assertTrue(t12.getToBoard().currentPlayer().isInCheckMate());

        }

        @Test
        public void testBlackburneShillingMate() {

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
                    .makeMove(Move.Factory.createMove(t2.getToBoard(), Utilities.getCoordinateAtPosition("g1"),
                            Utilities.getCoordinateAtPosition("f3")));

            assertTrue(t3.getStatus().isDone());

            final Transition t4 = t3.getToBoard()
                    .currentPlayer()
                    .makeMove(Move.Factory.createMove(t3.getToBoard(), Utilities.getCoordinateAtPosition("b8"),
                            Utilities.getCoordinateAtPosition("c6")));

            assertTrue(t4.getStatus().isDone());

            final Transition t5 = t4.getToBoard()
                    .currentPlayer()
                    .makeMove(Move.Factory.createMove(t4.getToBoard(), Utilities.getCoordinateAtPosition("f1"),
                            Utilities.getCoordinateAtPosition("c4")));

            assertTrue(t5.getStatus().isDone());

            final Transition t6 = t5.getToBoard()
                    .currentPlayer()
                    .makeMove(Move.Factory.createMove(t5.getToBoard(), Utilities.getCoordinateAtPosition("c6"),
                            Utilities.getCoordinateAtPosition("d4")));

            assertTrue(t6.getStatus().isDone());

            final Transition t7 = t6.getToBoard()
                    .currentPlayer()
                    .makeMove(Move.Factory.createMove(t6.getToBoard(), Utilities.getCoordinateAtPosition("f3"),
                            Utilities.getCoordinateAtPosition("e5")));

            assertTrue(t7.getStatus().isDone());

            final Transition t8 = t7.getToBoard()
                    .currentPlayer()
                    .makeMove(Move.Factory.createMove(t7.getToBoard(), Utilities.getCoordinateAtPosition("d8"),
                            Utilities.getCoordinateAtPosition("g5")));

            assertTrue(t8.getStatus().isDone());

            final Transition t9 = t8.getToBoard()
                    .currentPlayer()
                    .makeMove(Move.Factory.createMove(t8.getToBoard(), Utilities.getCoordinateAtPosition("e5"),
                            Utilities.getCoordinateAtPosition("f7")));

            assertTrue(t9.getStatus().isDone());

            final Transition t10 = t9.getToBoard()
                    .currentPlayer()
                    .makeMove(Move.Factory.createMove(t9.getToBoard(), Utilities.getCoordinateAtPosition("g5"),
                            Utilities.getCoordinateAtPosition("g2")));

            assertTrue(t10.getStatus().isDone());

            final Transition t11 = t10.getToBoard()
                    .currentPlayer()
                    .makeMove(Move.Factory.createMove(t10.getToBoard(), Utilities.getCoordinateAtPosition("h1"),
                            Utilities.getCoordinateAtPosition("f1")));

            assertTrue(t11.getStatus().isDone());

            final Transition t12 = t11.getToBoard()
                    .currentPlayer()
                    .makeMove(Move.Factory.createMove(t11.getToBoard(), Utilities.getCoordinateAtPosition("g2"),
                            Utilities.getCoordinateAtPosition("e4")));

            assertTrue(t12.getStatus().isDone());

            final Transition t13 = t12.getToBoard()
                    .currentPlayer()
                    .makeMove(Move.Factory.createMove(t12.getToBoard(), Utilities.getCoordinateAtPosition("c4"),
                            Utilities.getCoordinateAtPosition("e2")));

            assertTrue(t13.getStatus().isDone());

            final Transition t14 = t13.getToBoard()
                    .currentPlayer()
                    .makeMove(Move.Factory.createMove(t13.getToBoard(), Utilities.getCoordinateAtPosition("d4"),
                            Utilities.getCoordinateAtPosition("f3")));

            assertTrue(t14.getStatus().isDone());
            assertTrue(t14.getToBoard().currentPlayer().isInCheckMate());

        }

        @Test
        public void testAnastasiaMate() {
            final Builder builder = new Builder();

            builder.setPiece(new Rook(0, BLACK));
            builder.setPiece(new Rook(5, BLACK));
            builder.setPiece(new Pawn(8, BLACK));
            builder.setPiece(new Pawn(9, BLACK));
            builder.setPiece(new Pawn(10, BLACK));
            builder.setPiece(new Pawn(13, BLACK));
            builder.setPiece(new Pawn(14, BLACK));
            builder.setPiece(new King(15, BLACK, false));

            builder.setPiece(new Knight(12, WHITE));
            builder.setPiece(new Rook(27, WHITE));
            builder.setPiece(new Pawn(41, WHITE));
            builder.setPiece(new Pawn(48, WHITE));
            builder.setPiece(new Pawn(53, WHITE));
            builder.setPiece(new Pawn(54, WHITE));
            builder.setPiece(new Pawn(55, WHITE));
            builder.setPiece(new King(62, WHITE, false));

            builder.setMoveMaker(WHITE);

            final Board board = builder.build();
            final Transition t1 = board.currentPlayer()
                    .makeMove(Move.Factory.createMove(board, Utilities.getCoordinateAtPosition("d5"),
                            Utilities.getCoordinateAtPosition("h5")));

            assertTrue(t1.getStatus().isDone());
            assertTrue(t1.getToBoard().currentPlayer().isInCheckMate());
        }

        @Test
        public void testTwoBishopMate() {

            final Builder builder = new Builder();

            builder.setPiece(new King(7, BLACK, false));
            builder.setPiece(new Pawn(8, BLACK));
            builder.setPiece(new Pawn(10, BLACK));
            builder.setPiece(new Pawn(15, BLACK));
            builder.setPiece(new Pawn(17, BLACK));
            
            builder.setPiece(new Bishop(40, WHITE));
            builder.setPiece(new Bishop(48, WHITE));
            builder.setPiece(new King(53, WHITE, false));
           
            builder.setMoveMaker(WHITE);

            final Board board = builder.build();
            final Transition t1 = board.currentPlayer()
                    .makeMove(Move.Factory.createMove(board, Utilities.getCoordinateAtPosition("a3"),
                            Utilities.getCoordinateAtPosition("b2")));

            assertTrue(t1.getStatus().isDone());
            assertTrue(t1.getToBoard().currentPlayer().isInCheckMate());
        }

        @Test
        public void testQueenRookMate() {
            final Builder builder = new Builder();

            builder.setPiece(new King(5, BLACK, false));

            builder.setPiece(new Rook(9, WHITE));
            builder.setPiece(new Queen(16, WHITE));
            builder.setPiece(new King(59, WHITE, false));

            builder.setMoveMaker(WHITE);

            final Board board = builder.build();
            final Transition t1 = board.currentPlayer()
                    .makeMove(Move.Factory.createMove(board, Utilities.getCoordinateAtPosition("a6"),
                            Utilities.getCoordinateAtPosition("a8")));

            assertTrue(t1.getStatus().isDone());
            assertTrue(t1.getToBoard().currentPlayer().isInCheckMate());

        }

        @Test
        public void testQueenKnightMate() {
            final Builder builder = new Builder();

            builder.setPiece(new King(4, BLACK, false));
            builder.setPiece(new Queen(15, WHITE));
            builder.setPiece(new Knight(29, WHITE));
            builder.setPiece(new Pawn(55, WHITE));
            builder.setPiece(new King(60, WHITE, false));

            builder.setMoveMaker(WHITE);

            final Board board = builder.build();
            final Transition t1 = board.currentPlayer()
                    .makeMove(Move.Factory.createMove(board, Utilities.getCoordinateAtPosition("h7"),
                            Utilities.getCoordinateAtPosition("e7")));

            assertTrue(t1.getStatus().isDone());
            assertTrue(t1.getToBoard().currentPlayer().isInCheckMate());

        }

        @Test
        public void testBackRankMate() {
            final Builder builder = new Builder();

            builder.setPiece(new King(4, BLACK, false));
            builder.setPiece(new Rook(18, BLACK));

            builder.setPiece(new Pawn(53, WHITE));
            builder.setPiece(new Pawn(54, WHITE));
            builder.setPiece(new Pawn(55, WHITE));
            builder.setPiece(new King(62, WHITE, false));

            builder.setMoveMaker(BLACK);
            final Board board = builder.build();

            final Transition t1 = board.currentPlayer()
                    .makeMove(Move.Factory.createMove(board, Utilities.getCoordinateAtPosition("c6"),
                            Utilities.getCoordinateAtPosition("c1")));

            assertTrue(t1.getStatus().isDone());
            assertTrue(t1.getToBoard().currentPlayer().isInCheckMate());

        }

        @Test
        public void testMateInTwoTest1() {
            final Board board = FenUtilities.createGameFromFEN("6k1/1b4pp/1B1Q4/4p1P1/p3q3/2P3r1/P1P2PP1/R5K1 w - - 1 0");
            final MoveStrategy alphaBeta = new StockAlphaBeta(6);
            final Move bestMove = alphaBeta.execute(board);
            assertEquals(bestMove, Move.Move.Factory
                    .createMove(board, Utilities.getCoordinateAtPosition("d6"), Utilities.getCoordinateAtPosition("e6")));
        }

        @Test
        public void testMateInTwoTest2() {
            final Board board = FenUtilities.createGameFromFEN("3r3r/1Q5p/p3q2k/3NBp1B/3p3n/5P2/PP4PP/4R2K w - - 1 0");
            final MoveStrategy alphaBeta = new StockAlphaBeta(6);
            final Move bestMove = alphaBeta.execute(board);
            assertEquals(
                    bestMove,
                    Move.Move.Factory.createMove(board, Utilities.getCoordinateAtPosition("b7"),
                            Utilities.getCoordinateAtPosition("g7")));
        }

        @Test
        public void testMateInTwoTest3() {
            final Board board = FenUtilities.createGameFromFEN("rn3rk1/1R3ppp/2p5/8/PQ2P3/1P5P/2P1qPP1/3R2K1 w - - 1 0");
            final MoveStrategy alphaBeta = new StockAlphaBeta(1);
            final Move bestMove = alphaBeta.execute(board);
            assertEquals(bestMove, Move.Move.Factory
                    .createMove(board, Utilities.getCoordinateAtPosition("b4"), Utilities.getCoordinateAtPosition("f8")));
        }

        @Test
        public void testMateInFourTest1() {
            final Board board = FenUtilities.createGameFromFEN("7k/4r2B/1pb5/2P5/4p2Q/2q5/2P2R2/1K6 w - - 1 0");
            final MoveStrategy alphaBeta = new StockAlphaBeta(6);
            final Move bestMove = alphaBeta.execute(board);
            assertEquals(bestMove, Move.Move.Factory
                    .createMove(board, Utilities.getCoordinateAtPosition("f2"), Utilities.getCoordinateAtPosition("f8")));
        }

        @Test
        public void testMagnusBlackToMoveAndWinTest1() {
            final Board board = FenUtilities.createGameFromFEN("2rr2k1/pb3pp1/4q2p/2pn4/2Q1P3/P4P2/1P3BPP/2KR2NR b - - 0 1");
            final MoveStrategy alphaBeta = new StockAlphaBeta(6);
            final Move bestMove = alphaBeta.execute(board);
            assertEquals(bestMove, Move.Move.Factory
                    .createMove(board, Utilities.getCoordinateAtPosition("d5"), Utilities.getCoordinateAtPosition("e3"))); */
        }

    }
}
