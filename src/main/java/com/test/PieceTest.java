package com.test;

//import com.lindseyvarner.engine.players;
import com.lindseyvarner.engine.players.Transition;
import org.junit.Assert;
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

public class PieceTest {

    @Test
    public void testMiddleQueenOnEmptyBoard() {
        /* final Board.Builder builder = new Board.Builder();

        builder.setPiece(new King(4, BLACK, false));

        builder.setPiece(new Queen(36, WHITE));
        builder.setPiece(new King(60, WHITE, false));

        builder.setMoveMaker(WHITE);
        final Board board = builder.build();
        final Collection<Move> whiteLegals = board.whitePlayer().getLegalMoves();
        final Collection<Move> blackLegals = board.blackPlayer().getLegalMoves();
        assertEquals(whiteLegals.size(), 31);
        assertEquals(blackLegals.size(), 5);
        assertTrue(whiteLegals.contains(Move.Factory
                .createMove(board, Utilities.getCoordinateAtPosition("e4"), Utilities.getCoordinateAtPosition("e8"))));
        assertTrue(whiteLegals.contains(Move.Factory
                .createMove(board, Utilities.getCoordinateAtPosition("e4"), Utilities.getCoordinateAtPosition("e7"))));
        assertTrue(whiteLegals.contains(Move.Factory
                .createMove(board, Utilities.getCoordinateAtPosition("e4"), Utilities.getCoordinateAtPosition("e6"))));
        assertTrue(whiteLegals.contains(Move.Factory
                .createMove(board, Utilities.getCoordinateAtPosition("e4"), Utilities.getCoordinateAtPosition("e5"))));
        assertTrue(whiteLegals.contains(Move.Factory
                .createMove(board, Utilities.getCoordinateAtPosition("e4"), Utilities.getCoordinateAtPosition("e3"))));
        assertTrue(whiteLegals.contains(Move.Factory
                .createMove(board, Utilities.getCoordinateAtPosition("e4"), Utilities.getCoordinateAtPosition("e2"))));
        assertTrue(whiteLegals.contains(Move.Factory
                .createMove(board, Utilities.getCoordinateAtPosition("e4"), Utilities.getCoordinateAtPosition("a4"))));
        assertTrue(whiteLegals.contains(Move.Factory
                .createMove(board, Utilities.getCoordinateAtPosition("e4"), Utilities.getCoordinateAtPosition("b4"))));
        assertTrue(whiteLegals.contains(Move.Factory
                .createMove(board, Utilities.getCoordinateAtPosition("e4"), Utilities.getCoordinateAtPosition("c4"))));
        assertTrue(whiteLegals.contains(Move.Factory
                .createMove(board, Utilities.getCoordinateAtPosition("e4"), Utilities.getCoordinateAtPosition("d4"))));
        assertTrue(whiteLegals.contains(Move.Factory
                .createMove(board, Utilities.getCoordinateAtPosition("e4"), Utilities.getCoordinateAtPosition("f4"))));
        assertTrue(whiteLegals.contains(Move.Factory
                .createMove(board, Utilities.getCoordinateAtPosition("e4"), Utilities.getCoordinateAtPosition("g4"))));
        assertTrue(whiteLegals.contains(Move.Factory
                .createMove(board, Utilities.getCoordinateAtPosition("e4"), Utilities.getCoordinateAtPosition("h4"))));
    }

    @Test
    public void testLegalMoveAllAvailable() {

        final Board.Builder boardBuilder = new Board.Builder();

        boardBuilder.setPiece(new King(4, BLACK, false));
        boardBuilder.setPiece(new Knight(28, BLACK));

        boardBuilder.setPiece(new Knight(36, WHITE));
        boardBuilder.setPiece(new King(60, WHITE, false));

        boardBuilder.setMoveMaker(WHITE);
        final Board board = boardBuilder.build();
        final Collection<Move> whiteLegals = board.whitePlayer().getLegalMoves();
        assertEquals(whiteLegals.size(), 13);
        final Move wm1 = Move.Factory
                .createMove(board, Utilities.getCoordinateAtPosition("e4"), Utilities.getCoordinateAtPosition("d6"));
        final Move wm2 = Move.Factory
                .createMove(board, Utilities.getCoordinateAtPosition("e4"), Utilities.getCoordinateAtPosition("f6"));
        final Move wm3 = Move.Factory
                .createMove(board, Utilities.getCoordinateAtPosition("e4"), Utilities.getCoordinateAtPosition("c5"));
        final Move wm4 = Move.Factory
                .createMove(board, Utilities.getCoordinateAtPosition("e4"), Utilities.getCoordinateAtPosition("g5"));
        final Move wm5 = Move.Factory
                .createMove(board, Utilities.getCoordinateAtPosition("e4"), Utilities.getCoordinateAtPosition("c3"));
        final Move wm6 = Move.Factory
                .createMove(board, Utilities.getCoordinateAtPosition("e4"), Utilities.getCoordinateAtPosition("g3"));
        final Move wm7 = Move.Factory
                .createMove(board, Utilities.getCoordinateAtPosition("e4"), Utilities.getCoordinateAtPosition("d2"));
        final Move wm8 = Move.Factory
                .createMove(board, Utilities.getCoordinateAtPosition("e4"), Utilities.getCoordinateAtPosition("f2"));

        assertTrue(whiteLegals.contains(wm1));
        assertTrue(whiteLegals.contains(wm2));
        assertTrue(whiteLegals.contains(wm3));
        assertTrue(whiteLegals.contains(wm4));
        assertTrue(whiteLegals.contains(wm5));
        assertTrue(whiteLegals.contains(wm6));
        assertTrue(whiteLegals.contains(wm7));
        assertTrue(whiteLegals.contains(wm8));

        final Board.Builder boardBuilder2 = new Board.Builder();

        boardBuilder2.setPiece(new King(BLACK, 4, false));
        boardBuilder2.setPiece(new Knight(BLACK, 28));

        boardBuilder2.setPiece(new Knight(WHITE, 36));
        boardBuilder2.setPiece(new King(WHITE, 60, false));

        boardBuilder2.setMoveMaker(BLACK);
        final Board board2 = boardBuilder2.build();
        final Collection<Move> blackLegals = board.blackPlayer().getLegalMoves();

        final Move bm1 = Move.Factory
                .createMove(board2, Utilities.getCoordinateAtPosition("e5"), Utilities.getCoordinateAtPosition("d7"));
        final Move bm2 = Move.Factory
                .createMove(board2, Utilities.getCoordinateAtPosition("e5"), Utilities.getCoordinateAtPosition("f7"));
        final Move bm3 = Move.Factory
                .createMove(board2, Utilities.getCoordinateAtPosition("e5"), Utilities.getCoordinateAtPosition("c6"));
        final Move bm4 = Move.Factory
                .createMove(board2, Utilities.getCoordinateAtPosition("e5"), Utilities.getCoordinateAtPosition("g6"));
        final Move bm5 = Move.Factory
                .createMove(board2, Utilities.getCoordinateAtPosition("e5"), Utilities.getCoordinateAtPosition("c4"));
        final Move bm6 = Move.Factory
                .createMove(board2, Utilities.getCoordinateAtPosition("e5"), Utilities.getCoordinateAtPosition("g4"));
        final Move bm7 = Move.Factory
                .createMove(board2, Utilities.getCoordinateAtPosition("e5"), Utilities.getCoordinateAtPosition("d3"));
        final Move bm8 = Move.Factory
                .createMove(board2, Utilities.getCoordinateAtPosition("e5"), Utilities.getCoordinateAtPosition("f3"));

        assertEquals(blackLegals.size(), 13);

        assertTrue(blackLegals.contains(bm1));
        assertTrue(blackLegals.contains(bm2));
        assertTrue(blackLegals.contains(bm3));
        assertTrue(blackLegals.contains(bm4));
        assertTrue(blackLegals.contains(bm5));
        assertTrue(blackLegals.contains(bm6));
        assertTrue(blackLegals.contains(bm7));
        assertTrue(blackLegals.contains(bm8));
    }

    @Test
    public void testKnightInCorners() {
        final Board.Builder boardBuilder = new Board.Builder();
        boardBuilder.setPiece(new King(4, BLACK, false));
        boardBuilder.setPiece(new Knight(0, BLACK));
        boardBuilder.setPiece(new Knight(56, WHITE));
        boardBuilder.setPiece(new King(60, WHITE, false));
        boardBuilder.setMoveMaker(WHITE);
        final Board board = boardBuilder.build();
        final Collection<Move> whiteLegals = board.whitePlayer().getLegalMoves();
        final Collection<Move> blackLegals = board.blackPlayer().getLegalMoves();
        assertEquals(whiteLegals.size(), 7);
        assertEquals(blackLegals.size(), 7);
        final Move wm1 = Move.Factory
                .createMove(board, Utilities.getCoordinateAtPosition("a1"), Utilities.getCoordinateAtPosition("b3"));
        final Move wm2 = Move.Factory
                .createMove(board, Utilities.getCoordinateAtPosition("a1"), Utilities.getCoordinateAtPosition("c2"));
        assertTrue(whiteLegals.contains(wm1));
        assertTrue(whiteLegals.contains(wm2));
        final Move bm1 = Move.Factory
                .createMove(board, Utilities.getCoordinateAtPosition("a8"), Utilities.getCoordinateAtPosition("b6"));
        final Move bm2 = Move.Factory
                .createMove(board, Utilities.getCoordinateAtPosition("a8"), Utilities.getCoordinateAtPosition("c7"));
        assertTrue(blackLegals.contains(bm1));
        assertTrue(blackLegals.contains(bm2));

    }

    @Test
    public void testMiddleBishopOnEmptyBoard() {
        final Board.Builder builder = new Board.Builder();

        builder.setPiece(new King(4, BLACK, false));

        builder.setPiece(new Bishop(35, WHITE));
        builder.setPiece(new King(60, WHITE, false));

        builder.setMoveMaker(WHITE);
      
        final Board board = builder.build();
        final Collection<Move> whiteLegals = board.whitePlayer().getLegalMoves();
        final Collection<Move> blackLegals = board.blackPlayer().getLegalMoves();
        assertEquals(whiteLegals.size(), 18);
        assertEquals(blackLegals.size(), 5);
        assertTrue(whiteLegals.contains(Move.Factory
                .createMove(board, Utilities.getCoordinateAtPosition("d4"), Utilities.getCoordinateAtPosition("a7"))));
        assertTrue(whiteLegals.contains(Move.Factory
                .createMove(board, Utilities.getCoordinateAtPosition("d4"), Utilities.getCoordinateAtPosition("b6"))));
        assertTrue(whiteLegals.contains(Move.Factory
                .createMove(board, Utilities.getCoordinateAtPosition("d4"), Utilities.getCoordinateAtPosition("c5"))));
        assertTrue(whiteLegals.contains(Move.Factory
                .createMove(board, Utilities.getCoordinateAtPosition("d4"), Utilities.getCoordinateAtPosition("e3"))));
        assertTrue(whiteLegals.contains(Move.Factory
                .createMove(board, Utilities.getCoordinateAtPosition("d4"), Utilities.getCoordinateAtPosition("f2"))));
        assertTrue(whiteLegals.contains(Move.Factory
                .createMove(board, Utilities.getCoordinateAtPosition("d4"), Utilities.getCoordinateAtPosition("g1"))));
    }

    @Test
    public void testTopLeftBishopOnEmptyBoard() {
        Board.Builder builder = new Board.Builder();
    
        builder.setPiece(new King(4, BLACK, false));
     
        builder.setPiece(new Bishop(0, WHITE));
        builder.setPiece(new King(60, WHITE, false));
   
        builder.setMoveMaker(WHITE);
     
        final Board board = builder.build();
        final Collection<Move> whiteLegals = board.whitePlayer().getLegalMoves();
        final Collection<Move> blackLegals = board.blackPlayer().getLegalMoves();
        assertEquals(board.getPiece(0), board.getPiece(0));
        assertNotNull(board.getPiece(0));
        assertEquals(whiteLegals.size(), 12);
        assertEquals(blackLegals.size(), 5);
        assertTrue(whiteLegals.contains(Move.Factory
                .createMove(board, Utilities.getCoordinateAtPosition("a8"), Utilities.getCoordinateAtPosition("b7"))));
        assertTrue(whiteLegals.contains(Move.Factory
                .createMove(board, Utilities.getCoordinateAtPosition("a8"), Utilities.getCoordinateAtPosition("c6"))));
        assertTrue(whiteLegals.contains(Move.Factory
                .createMove(board, Utilities.getCoordinateAtPosition("a8"), Utilities.getCoordinateAtPosition("d5"))));
        assertTrue(whiteLegals.contains(Move.Factory
                .createMove(board, Utilities.getCoordinateAtPosition("a8"), Utilities.getCoordinateAtPosition("e4"))));
        assertTrue(whiteLegals.contains(Move.Factory
                .createMove(board, Utilities.getCoordinateAtPosition("a8"), Utilities.getCoordinateAtPosition("f3"))));
        assertTrue(whiteLegals.contains(Move.Factory
                .createMove(board, Utilities.getCoordinateAtPosition("a8"), Utilities.getCoordinateAtPosition("g2"))));
        assertTrue(whiteLegals.contains(Move.Factory
                .createMove(board, Utilities.getCoordinateAtPosition("a8"), Utilities.getCoordinateAtPosition("h1"))));
    }

    @Test
    public void testTopRightBishopOnEmptyBoard() {
        Board.Builder builder = new Board.Builder();
        
        builder.setPiece(new King(4, BLACK, false));
        builder.setPiece(new Bishop(7, WHITE));
        builder.setPiece(new King(60, WHITE, false));
        builder.setMoveMaker(WHITE);
     
        final Board board = builder.build();
        final Collection<Move> whiteLegals = board.whitePlayer().getLegalMoves();
        final Collection<Move> blackLegals = board.blackPlayer().getLegalMoves();
        assertEquals(whiteLegals.size(), 12);
        assertEquals(blackLegals.size(), 5);
        assertTrue(whiteLegals.contains(Move.Factory
                .createMove(board, Utilities.getCoordinateAtPosition("h8"), Utilities.getCoordinateAtPosition("g7"))));
        assertTrue(whiteLegals.contains(Move.Factory
                .createMove(board, Utilities.getCoordinateAtPosition("h8"), Utilities.getCoordinateAtPosition("f6"))));
        assertTrue(whiteLegals.contains(Move.Factory
                .createMove(board, Utilities.getCoordinateAtPosition("h8"), Utilities.getCoordinateAtPosition("e5"))));
        assertTrue(whiteLegals.contains(Move.Factory
                .createMove(board, Utilities.getCoordinateAtPosition("h8"), Utilities.getCoordinateAtPosition("d4"))));
        assertTrue(whiteLegals.contains(Move.Factory
                .createMove(board, Utilities.getCoordinateAtPosition("h8"), Utilities.getCoordinateAtPosition("c3"))));
        assertTrue(whiteLegals.contains(Move.Factory
                .createMove(board, Utilities.getCoordinateAtPosition("h8"), Utilities.getCoordinateAtPosition("b2"))));
        assertTrue(whiteLegals.contains(Move.Factory
                .createMove(board, Utilities.getCoordinateAtPosition("h8"), Utilities.getCoordinateAtPosition("a1"))));
    }

    @Test
    public void testBottomLeftBishopOnEmptyBoard() {
        Board.Builder builder = new Board.Builder();
        
        builder.setPiece(new King(4, BLACK, false));
        builder.setPiece(new Bishop(56, WHITE));
        builder.setPiece(new King(60, WHITE, false));
       
        builder.setMoveMaker(WHITE);
        //build the board
        final Board board = builder.build();
        final Collection<Move> whiteLegals = board.whitePlayer().getLegalMoves();
        final Collection<Move> blackLegals = board.blackPlayer().getLegalMoves();
        assertEquals(whiteLegals.size(), 12);
        assertEquals(blackLegals.size(), 5);
        assertTrue(whiteLegals.contains(Move.Factory
                .createMove(board, Utilities.getCoordinateAtPosition("a1"), Utilities.getCoordinateAtPosition("b2"))));
        assertTrue(whiteLegals.contains(Move.Factory
                .createMove(board, Utilities.getCoordinateAtPosition("a1"), Utilities.getCoordinateAtPosition("c3"))));
        assertTrue(whiteLegals.contains(Move.Factory
                .createMove(board, Utilities.getCoordinateAtPosition("a1"), Utilities.getCoordinateAtPosition("d4"))));
        assertTrue(whiteLegals.contains(Move.Factory
                .createMove(board, Utilities.getCoordinateAtPosition("a1"), Utilities.getCoordinateAtPosition("e5"))));
        assertTrue(whiteLegals.contains(Move.Factory
                .createMove(board, Utilities.getCoordinateAtPosition("a1"), Utilities.getCoordinateAtPosition("f6"))));
        assertTrue(whiteLegals.contains(Move.Factory
                .createMove(board, Utilities.getCoordinateAtPosition("a1"), Utilities.getCoordinateAtPosition("g7"))));
        assertTrue(whiteLegals.contains(Move.Factory
                .createMove(board, Utilities.getCoordinateAtPosition("a1"), Utilities.getCoordinateAtPosition("h8"))));
    }

    @Test
    public void testBottomRightBishopOnEmptyBoard() {
        Board.Builder builder = new Board.Builder();
        
        builder.setPiece(new King(4, BLACK, false));
        builder.setPiece(new Bishop(63, WHITE));
        builder.setPiece(new King(60, WHITE, false));
        builder.setMoveMaker(WHITE);

        final Board board = builder.build();
        final Collection<Move> whiteLegals = board.whitePlayer().getLegalMoves();
        final Collection<Move> blackLegals = board.blackPlayer().getLegalMoves();
        assertEquals(whiteLegals.size(), 12);
        assertEquals(blackLegals.size(), 5);
        assertTrue(whiteLegals.contains(Move.Factory
                .createMove(board, Utilities.getCoordinateAtPosition("h1"), Utilities.getCoordinateAtPosition("g2"))));
        assertTrue(whiteLegals.contains(Move.Factory
                .createMove(board, Utilities.getCoordinateAtPosition("h1"), Utilities.getCoordinateAtPosition("f3"))));
        assertTrue(whiteLegals.contains(Move.Factory
                .createMove(board, Utilities.getCoordinateAtPosition("h1"), Utilities.getCoordinateAtPosition("e4"))));
        assertTrue(whiteLegals.contains(Move.Factory
                .createMove(board, Utilities.getCoordinateAtPosition("h1"), Utilities.getCoordinateAtPosition("d5"))));
        assertTrue(whiteLegals.contains(Move.Factory
                .createMove(board, Utilities.getCoordinateAtPosition("h1"), Utilities.getCoordinateAtPosition("c6"))));
        assertTrue(whiteLegals.contains(Move.Factory
                .createMove(board, Utilities.getCoordinateAtPosition("h1"), Utilities.getCoordinateAtPosition("b7"))));
        assertTrue(whiteLegals.contains(Move.Factory
                .createMove(board, Utilities.getCoordinateAtPosition("h1"), Utilities.getCoordinateAtPosition("a8"))));
    }

    @Test
    public void testMiddleRookOnEmptyBoard() {
        final Board.Builder builder = new Board.Builder();
        
        builder.setPiece(new King(4, BLACK, false));
        builder.setPiece(new Rook(36, WHITE));
        builder.setPiece(new King(60, WHITE, false));
        builder.setMoveMaker(WHITE);

        final Board board = builder.build();
        final Collection<Move> whiteLegals = board.whitePlayer().getLegalMoves();
        final Collection<Move> blackLegals = board.blackPlayer().getLegalMoves();
        assertEquals(whiteLegals.size(), 18);
        assertEquals(blackLegals.size(), 5);
        assertTrue(whiteLegals.contains(Move.Factory
                .createMove(board, Utilities.getCoordinateAtPosition("e4"), Utilities.getCoordinateAtPosition("e8"))));
        assertTrue(whiteLegals.contains(Move.Factory
                .createMove(board, Utilities.getCoordinateAtPosition("e4"), Utilities.getCoordinateAtPosition("e7"))));
        assertTrue(whiteLegals.contains(Move.Factory
                .createMove(board, Utilities.getCoordinateAtPosition("e4"), Utilities.getCoordinateAtPosition("e6"))));
        assertTrue(whiteLegals.contains(Move.Factory
                .createMove(board, Utilities.getCoordinateAtPosition("e4"), Utilities.getCoordinateAtPosition("e5"))));
        assertTrue(whiteLegals.contains(Move.Factory
                .createMove(board, Utilities.getCoordinateAtPosition("e4"), Utilities.getCoordinateAtPosition("e3"))));
        assertTrue(whiteLegals.contains(Move.Factory
                .createMove(board, Utilities.getCoordinateAtPosition("e4"), Utilities.getCoordinateAtPosition("e2"))));
        assertTrue(whiteLegals.contains(Move.Factory
                .createMove(board, Utilities.getCoordinateAtPosition("e4"), Utilities.getCoordinateAtPosition("a4"))));
        assertTrue(whiteLegals.contains(Move.Factory
                .createMove(board, Utilities.getCoordinateAtPosition("e4"), Utilities.getCoordinateAtPosition("b4"))));
        assertTrue(whiteLegals.contains(Move.Factory
                .createMove(board, Utilities.getCoordinateAtPosition("e4"), Utilities.getCoordinateAtPosition("c4"))));
        assertTrue(whiteLegals.contains(Move.Factory
                .createMove(board, Utilities.getCoordinateAtPosition("e4"), Utilities.getCoordinateAtPosition("d4"))));
        assertTrue(whiteLegals.contains(Move.Factory
                .createMove(board, Utilities.getCoordinateAtPosition("e4"), Utilities.getCoordinateAtPosition("f4"))));
        assertTrue(whiteLegals.contains(Move.Factory
                .createMove(board, Utilities.getCoordinateAtPosition("e4"), Utilities.getCoordinateAtPosition("g4"))));
        assertTrue(whiteLegals.contains(Move.Factory
                .createMove(board, Utilities.getCoordinateAtPosition("e4"), Utilities.getCoordinateAtPosition("h4"))));
    }

    @Test
    public void testPawnPromotion() {
        final Board.Builder builder = new Board.Builder();
        
        builder.setPiece(new Rook(3, BLACK));
        builder.setPiece(new King(22, BLACK, false));
        builder.setPiece(new Pawn(15, WHITE));
        builder.setPiece(new King(52, WHITE, false));
        builder.setMoveMaker(WHITE);

        final Board board = builder.build();
        final Move m1 = Move.Factory.createMove(board, Utilities.getCoordinateAtPosition(
                "h7"), Utilities.getCoordinateAtPosition("h8"));
        final Transition t1 = board.currentPlayer().makeMove(m1);
        Assert.assertTrue(t1.getStatus().isDone());
        final Move m2 = Move.Factory.createMove(t1.getToBoard(), Utilities.getCoordinateAtPosition("d8"), Utilities.getCoordinateAtPosition("h8"));
        final Transition t2 = t1.getToBoard().currentPlayer().makeMove(m2);
        Assert.assertTrue(t2.getStatus().isDone());
        final Move m3 = Move.Factory.createMove(t2.getToBoard(), Utilities.getCoordinateAtPosition("e2"), Utilities.getCoordinateAtPosition("d2"));
        final Transition t3 = board.currentPlayer().makeMove(m3);
        Assert.assertTrue(t3.getStatus().isDone());
    }

    @Test
    public void testSimpleWhiteEnPassant() {
        final Board.Builder builder = new Board.Builder();
        
        builder.setPiece(new King(4, BLACK, false));
        builder.setPiece(new Pawn(11, BLACK));
        builder.setPiece(new Pawn(52, WHITE));
        builder.setPiece(new King(60, WHITE, false));
        builder.setMoveMaker(WHITE);

        final Board board = builder.build();
        final Move m1 = Move.Factory.createMove(board, Utilities.getCoordinateAtPosition(
                "e2"), Utilities.getCoordinateAtPosition("e4"));
        final Transition t1 = board.currentPlayer().makeMove(m1);
        Assert.assertTrue(t1.getStatus().isDone());
        final Move m2 = Move.Factory.createMove(t1.getToBoard(), Utilities.getCoordinateAtPosition("e8"), Utilities.getCoordinateAtPosition("d8"));
        final Transition t2 = t1.getToBoard().currentPlayer().makeMove(m2);
        Assert.assertTrue(t2.getStatus().isDone());
        final Move m3 = Move.Factory.createMove(t2.getToBoard(), Utilities.getCoordinateAtPosition("e4"), Utilities.getCoordinateAtPosition("e5"));
        final Transition t3 = t2.getToBoard().currentPlayer().makeMove(m3);
        Assert.assertTrue(t3.getStatus().isDone());
        final Move m4 = Move.Factory.createMove(t3.getToBoard(), Utilities.getCoordinateAtPosition("d7"), Utilities.getCoordinateAtPosition("d5"));
        final Transition t4 = t3.getToBoard().currentPlayer().makeMove(m4);
        Assert.assertTrue(t4.getStatus().isDone());
        final Move m5 = Move.Factory.createMove(t4.getToBoard(), Utilities.getCoordinateAtPosition("e5"), Utilities.getCoordinateAtPosition("d6"));
        final Transition t5 = t4.getToBoard().currentPlayer().makeMove(m5);
        Assert.assertTrue(t5.getStatus().isDone());
    }

    @Test
    public void testSimpleBlackEnPassant() {
        final Board.Builder builder = new Board.Builder();
        
        builder.setPiece(new King(4, BLACK, false));
        builder.setPiece(new Pawn(11, BLACK));
        builder.setPiece(new Pawn(52, WHITE));
        builder.setPiece(new King(60, WHITE, false));
        builder.setMoveMaker(WHITE);

        final Board board = builder.build();
        final Move m1 = Move.Factory.createMove(board, Utilities.getCoordinateAtPosition(
                "e1"), Utilities.getCoordinateAtPosition("d1"));
        final Transition t1 = board.currentPlayer().makeMove(m1);
        assertTrue(t1.getStatus().isDone());
        final Move m2 = Move.Factory.createMove(t1.getToBoard(), Utilities.getCoordinateAtPosition("d7"), Utilities.getCoordinateAtPosition("d5"));
        final Transition t2 = t1.getToBoard().currentPlayer().makeMove(m2);
        assertTrue(t2.getStatus().isDone());
        final Move m3 = Move.Factory.createMove(t2.getToBoard(), Utilities.getCoordinateAtPosition("d1"), Utilities.getCoordinateAtPosition("c1"));
        final Transition t3 = t2.getToBoard().currentPlayer().makeMove(m3);
        assertTrue(t3.getStatus().isDone());
        final Move m4 = Move.Factory.createMove(t3.getToBoard(), Utilities.getCoordinateAtPosition("d5"), Utilities.getCoordinateAtPosition("d4"));
        final Transition t4 = t3.getToBoard().currentPlayer().makeMove(m4);
        assertTrue(t4.getStatus().isDone());
        final Move m5 = Move.Factory.createMove(t4.getToBoard(), Utilities.getCoordinateAtPosition("e2"), Utilities.getCoordinateAtPosition("e4"));
        final Transition t5 = t4.getToBoard().currentPlayer().makeMove(m5);
        assertTrue(t5.getStatus().isDone());
        final Move m6 = Move.Factory.createMove(t5.getToBoard(), Utilities.getCoordinateAtPosition("d4"), Utilities.getCoordinateAtPosition("e3"));
        final Transition t6 = t5.getToBoard().currentPlayer().makeMove(m6);
        Assert.assertTrue(t6.getStatus().isDone());
    }

    @Test
    public void testEnPassant2() {
        final Board board = Board.createStandardBoard();
        final Move m1 = Move.Factory.createMove(board, Utilities.getCoordinateAtPosition(
                "e2"), Utilities.getCoordinateAtPosition("e3"));
        final Transition t1 = board.currentPlayer().makeMove(m1);
        assertTrue(t1.getStatus().isDone());
        final Move m2 = Move.Factory.createMove(t1.getToBoard(), Utilities.getCoordinateAtPosition("h7"), Utilities.getCoordinateAtPosition("h5"));
        final Transition t2 = t1.getToBoard().currentPlayer().makeMove(m2);
        assertTrue(t2.getStatus().isDone());
        final Move m3 = Move.Factory.createMove(t2.getToBoard(), Utilities.getCoordinateAtPosition("e3"), Utilities.getCoordinateAtPosition("e4"));
        final Transition t3 = t2.getToBoard().currentPlayer().makeMove(m3);
        assertTrue(t3.getStatus().isDone());
        final Move m4 = Move.Factory.createMove(t3.getToBoard(), Utilities.getCoordinateAtPosition("h5"), Utilities.getCoordinateAtPosition("h4"));
        final Transition t4 = t3.getToBoard().currentPlayer().makeMove(m4);
        assertTrue(t4.getStatus().isDone());
        final Move m5 = Move.Factory.createMove(t4.getToBoard(), Utilities.getCoordinateAtPosition("g2"), Utilities.getCoordinateAtPosition("g4"));
        final Transition t5 = t4.getToBoard().currentPlayer().makeMove(m5);
        assertTrue(t5.getStatus().isDone());
    }

    @Test
    public void testKingEquality() {
        final Board board = Board.createStandardBoard();
        final Board board2 = Board.createStandardBoard();
        assertEquals(board.getPiece(60), board2.getPiece(60));
        assertFalse(board.getPiece(60).equals(null));
    }

    @Test
    public void testHashCode() {
        final Board board = Board.createStandardBoard();
        final Set<Piece> pieceSet = Sets.newHashSet(board.getAllPieces());
        final Set<Piece> whitePieceSet = Sets.newHashSet(board.getWhitePieces());
        final Set<Piece> blackPieceSet = Sets.newHashSet(board.getBlackPieces());
        assertTrue(pieceSet.size() == 32);
        assertTrue(whitePieceSet.size() == 16);
        assertTrue(blackPieceSet.size() == 16); */
    }

}
