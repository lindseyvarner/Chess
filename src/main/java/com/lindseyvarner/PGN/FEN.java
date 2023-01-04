package com.lindseyvarner.PGN;

import com.lindseyvarner.engine.board.Board;
import com.lindseyvarner.engine.board.Utilities;
import com.lindseyvarner.engine.pieces.Pawn;

public class FEN {
    private FEN() {
        throw new RuntimeException("Unable to instantiate");
    }

    public static Board createGameFromFEN(final String fen) {
        return null;
    }

    public static String createFENFromGame(final Board board) {
        return boardText(board) + " " +
               currentPlayerText(board) + " " +
               castleText(board) + " " +
               enPassantText(board) + " " + "0 1";
    }

    private static String boardText(final Board board) {
        final StringBuilder builder = new StringBuilder();

        for (int i = 0; i <Utilities.NUM_TILES; i++) {
            final String tileText = board.getTile(i).toString();
            builder.append(tileText);
        }
        builder.insert(8, "/");
        builder.insert(17, "/");
        builder.insert(26, "/");
        builder.insert(35, "/");
        builder.insert(44, "/");
        builder.insert(53, "/");
        builder.insert(62, "/");

        return builder.toString().replaceAll("--------", "8")
                                 .replaceAll("-------", "7")
                                 .replaceAll("------", "6")
                                 .replaceAll("-----", "5")
                                 .replaceAll("----", "4")
                                 .replaceAll("---", "3")
                                 .replaceAll("--", "2")
                                 .replaceAll("-", "1");
    }

    private static String enPassantText(final Board board) {
        final Pawn enPassant = board.getEnPassantPawn();

        if (enPassant != null) {
            return Utilities.getPositionAtCoordinate(enPassant.getPiecePosition() +
                   8 * enPassant.getPieceAlliance().getOppositeDirection());
        }
        return "-";
    }

    private static String castleText(final Board board) {
        final StringBuilder builder = new StringBuilder();

        if (board.whitePlayer().isKingsideCastleCapable()) {
            builder.append("K");
        }
        if (board.whitePlayer().isQueensideCastleCapable()) {
            builder.append("Q");
        }
        if (board.blackPlayer().isKingsideCastleCapable()) {
            builder.append("k");
        }
        if (board.blackPlayer().isQueensideCastleCapable()) {
            builder.append("q");
        }

        final String result = builder.toString();
        return result.isEmpty() ? "-" : result;
    }

    private static String currentPlayerText(final Board board) {
        return board.currentPlayer().toString().substring(0, 1).toLowerCase();
    }


}
