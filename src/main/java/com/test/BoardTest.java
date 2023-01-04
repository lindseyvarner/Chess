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

class BoardTest {

        @Test
        public void initialBoard() {

            final Board board = Board.createStandardBoard();
            assertEquals(board.currentPlayer().getLegalMoves().size(), 20);
            assertEquals(board.currentPlayer().getOpponent().getLegalMoves().size(), 20);
            assertFalse(board.currentPlayer().isInCheck());
            assertFalse(board.currentPlayer().isCheckmated());
            assertFalse(board.currentPlayer().isCastled());
            //assertTrue(board.currentPlayer().isKingSideCastleCapable());
            //assertTrue(board.currentPlayer().isQueenSideCastleCapable());
            assertEquals(board.currentPlayer(), board.whitePlayer());
            assertEquals(board.currentPlayer().getOpponent(), board.blackPlayer());
            assertFalse(board.currentPlayer().getOpponent().isInCheck());
            assertFalse(board.currentPlayer().getOpponent().isCheckmated());
            assertFalse(board.currentPlayer().getOpponent().isCastled());
            //assertTrue(board.currentPlayer().getOpponent().isKingSideCastleCapable());
            //assertTrue(board.currentPlayer().getOpponent().isQueenSideCastleCapable());
            assertTrue(board.whitePlayer().toString().equals("White"));
            assertTrue(board.blackPlayer().toString().equals("Black"));

            final Collection<Piece> allPieces = board.getAllPieces();
            final Collection<Move> allMoves = Stream.concat(board.whitePlayer().getLegalMoves().stream(),
                    board.blackPlayer().getLegalMoves().stream()).collect(Collectors.toList());
            for(final Move move : allMoves) {
                assertFalse(move.isAttack());
                assertFalse(move.isCastle());
                //assertEquals(Utilities.exchangeScore(move), 1);
            }

            //assertFalse(Utilities.isEndgame(board));
            //assertFalse(Utilities.isThreatenedBoard(board));
            //assertEquals(StandardBoardEvaluator.get().evaluate(board, 0), 0);
            //assertEquals(board.getPiece(35), null);
        }

        @Test
        public void testPlainKingMove() {

            final Builder builder = new Builder();
            builder.setPiece(new King(4, BLACK, false, false));
            builder.setPiece(new Pawn(12, BLACK));
            builder.setPiece(new Pawn(52, WHITE, false));
            builder.setPiece(new King(60, WHITE, false, false));
            builder.setMoveMaker(WHITE);
            final Board board = builder.build();
            System.out.println(board);

            assertEquals(board.whitePlayer().getLegalMoves().size(), 6);
            assertEquals(board.blackPlayer().getLegalMoves().size(), 6);
            assertFalse(board.currentPlayer().isInCheck());
            assertFalse(board.currentPlayer().isCheckmated());
            assertFalse(board.currentPlayer().getOpponent().isInCheck());
            assertFalse(board.currentPlayer().getOpponent().isCheckmated());
            assertEquals(board.currentPlayer(), board.whitePlayer());
            assertEquals(board.currentPlayer().getOpponent(), board.blackPlayer());
            //BoardEvaluator evaluator = StandardBoardEvaluator.get();
            //System.out.println(evaluator.evaluate(board, 0));
            //assertEquals(StandardBoardEvaluator.get().evaluate(board, 0), 0);

            //final Move move = Utilities.createMove(board, Utilities.getCoordinateAtPosition("e1"),
                    //Utilities.getCoordinateAtPosition("f1"));

            //final Transition transition = board.currentPlayer().makeMove(move);

            /*assertEquals(Transition.getTransitionMove(), move);
            assertEquals(Transition.getFromBoard(), board);
            assertEquals(Transition.getToBoard().currentPlayer(), Transition.getToBoard().blackPlayer());

            assertTrue(Transition.Utilities().isDone());
            assertEquals(Transition.getToBoard().whitePlayer().getPlayerKing().getPiecePosition(), 61);
            System.out.println(Transition.getToBoard());*/

        }

        @Test
        public void testBoardConsistency() {
            final Board board = Board.createStandardBoard();
            assertEquals(board.currentPlayer(), board.whitePlayer());

            /* final Transition t1 = board.currentPlayer()
                                  .makeMove(Utilities.createMove(board, Utilities.getCoordinateAtPosition("e2"),
                                  Utilities.getCoordinateAtPosition("e4")));

            final Transition t2 = t1.getToBoard().currentPlayer()
                                    .makeMove(Utilities.createMove(t1.getToBoard(),
                                    Utilities.getCoordinateAtPosition("e7"),
                                    Utilities.getCoordinateAtPosition("e5")));

            final Transition t3 = t2.getToBoard().currentPlayer()
                                  .makeMove(Utilities.createMove(t2.getToBoard(),
                                  Utilities.getCoordinateAtPosition("g1"),
                                  Utilities.getCoordinateAtPosition("f3")));

            final Transition t4 = t3.getToBoard().currentPlayer()
                                  .makeMove(Utilities.createMove(t3.getToBoard(),
                                  Utilities.getCoordinateAtPosition("d7"),
                                  Utilities.getCoordinateAtPosition("d5")));

            final Transition t5 = t4.getToBoard().currentPlayer()
                                  .makeMove(Utilities.createMove(t4.getToBoard(),
                                  Utilities.getCoordinateAtPosition("e4"),
                                  Utilities.getCoordinateAtPosition("d5")));

            final Transition t6 = t5.getToBoard().currentPlayer()
                                  .makeMove(Utilities.createMove(t5.getToBoard(),
                                  Utilities.getCoordinateAtPosition("d8"),
                                  Utilities.getCoordinateAtPosition("d5")));

            final Transition t7 = t6.getToBoard().currentPlayer()
                                  .makeMove(Utilities.createMove(t6.getToBoard(),
                                  Utilities.getCoordinateAtPosition("f3"),
                                  Utilities.getCoordinateAtPosition("g5")));

            final Transition t8 = t7.getToBoard().currentPlayer()
                                  .makeMove(Utilities.createMove(t7.getToBoard(),
                                  Utilities.getCoordinateAtPosition("f7"),
                                  Utilities.getCoordinateAtPosition("f6")));

            final Transition t9 = t8.getToBoard().currentPlayer()
                                  .makeMove(Utilities.createMove(t8.getToBoard(),
                                  Utilities.getCoordinateAtPosition("d1"),
                                  Utilities.getCoordinateAtPosition("h5")));

            final Transition t10 = t9.getToBoard().currentPlayer()
                                   .makeMove(Utilities.createMove(t9.getToBoard(),
                                   Utilities.getCoordinateAtPosition("g7"),
                                   Utilities.getCoordinateAtPosition("g6")));

            final Transition t11 = t10.getToBoard().currentPlayer()
                                   .makeMove(Utilities.createMove(t10.getToBoard(),
                                   Utilities.getCoordinateAtPosition("h5"),
                                   Utilities.getCoordinateAtPosition("h4")));

            final Transition t12 = t11.getToBoard().currentPlayer()
                                   .makeMove(Utilities.createMove(t11.getToBoard(),
                                   Utilities.getCoordinateAtPosition("f6"),
                                   Utilities.getCoordinateAtPosition("g5")));

            final Transition t13 = t12.getToBoard().currentPlayer()
                                   .makeMove(Utilities.createMove(t12.getToBoard(),
                                   Utilities.getCoordinateAtPosition("h4"),
                                   Utilities.getCoordinateAtPosition("g5")));

            final Transition t14 = t13.getToBoard().currentPlayer()
                                   .makeMove(Utilities.createMove(t13.getToBoard(),
                                   Utilities.getCoordinateAtPosition("d5"),
                                   Utilities.getCoordinateAtPosition("e4")));

            assertTrue(t14.getToBoard().whitePlayer().getActivePieces().size() ==
                       calculatedActivesFor(t14.getToBoard(), WHITE));
            assertTrue(t14.getToBoard().blackPlayer().getActivePieces().size() ==
                       calculatedActivesFor(t14.getToBoard(), BLACK)); */
        }

        @Test(expected=RuntimeException.class)
        public void testInvalidBoard() {

            final Builder builder = new Builder();

            builder.setPiece(new Rook(0, BLACK));
            builder.setPiece(new Knight(1, BLACK));
            builder.setPiece(new Bishop(2, BLACK));
            builder.setPiece(new Queen(3, BLACK));
            builder.setPiece(new Bishop(5, BLACK));
            builder.setPiece(new Knight(6, BLACK));
            builder.setPiece(new Rook(7, BLACK));
            builder.setPiece(new Pawn(8, BLACK));
            builder.setPiece(new Pawn(9, BLACK));
            builder.setPiece(new Pawn(10, BLACK));
            builder.setPiece(new Pawn(11, BLACK));
            builder.setPiece(new Pawn(12, BLACK));
            builder.setPiece(new Pawn(13, BLACK));
            builder.setPiece(new Pawn(14, BLACK));
            builder.setPiece(new Pawn(15, BLACK));

            builder.setPiece(new Pawn(48, WHITE));
            builder.setPiece(new Pawn(49, WHITE));
            builder.setPiece(new Pawn(50, WHITE));
            builder.setPiece(new Pawn(51, WHITE));
            builder.setPiece(new Pawn(52, WHITE));
            builder.setPiece(new Pawn(53, WHITE));
            builder.setPiece(new Pawn(54, WHITE));
            builder.setPiece(new Pawn(55, WHITE));
            builder.setPiece(new Rook(56, WHITE));
            builder.setPiece(new Knight(57, WHITE));
            builder.setPiece(new Bishop(58, WHITE));
            builder.setPiece(new Queen(59, WHITE));
            builder.setPiece(new Bishop(61, WHITE));
            builder.setPiece(new Knight(62, WHITE));
            builder.setPiece(new Rook(63, WHITE));

            builder.setMoveMaker(WHITE);
            builder.build();
        }

        @Test
        public void testAlgebraicNotation() {
            assertEquals(Utilities.getPositionAtCoordinate(0), "a8");
            assertEquals(Utilities.getPositionAtCoordinate(1), "b8");
            assertEquals(Utilities.getPositionAtCoordinate(2), "c8");
            assertEquals(Utilities.getPositionAtCoordinate(3), "d8");
            assertEquals(Utilities.getPositionAtCoordinate(4), "e8");
            assertEquals(Utilities.getPositionAtCoordinate(5), "f8");
            assertEquals(Utilities.getPositionAtCoordinate(6), "g8");
            assertEquals(Utilities.getPositionAtCoordinate(7), "h8");
        }

        @Test
        public void memory() {
            final Runtime runtime = Runtime.getRuntime();
            long start, end;
            runtime.gc();
            start = runtime.freeMemory();
            Board board = Board.createStandardBoard();
            end =  runtime.freeMemory();
            System.out.println("Memory used:  " + (start-end) + " bytes.");

        }
        private static int calculatedActivesFor(final Board board,
                                                final Alliance alliance) {
            int count = 0;
            for (final Piece p : board.getAllPieces()) {
                if (p.getPieceAlliance().equals(alliance)) {
                    count++;
                }
            }
            return count;
        }
    }
