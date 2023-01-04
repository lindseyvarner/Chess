package com.lindseyvarner.engine.board;

import com.lindseyvarner.engine.Alliance;
import com.lindseyvarner.engine.pieces.*;
import com.lindseyvarner.engine.players.BlackPlayer;
import com.lindseyvarner.engine.players.Player;
import com.lindseyvarner.engine.players.WhitePlayer;

import java.util.*;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Board {
    private final List<Tile> gameBoard;
    private final Collection<Piece> whitePieces;
    private final Collection<Piece> blackPieces;
    private final WhitePlayer whitePlayer;
    private final BlackPlayer blackPlayer;
    private final Player currentPlayer;
    private final Pawn enPassantPawn;
    private final Move transitionMove;

    public Board(final Builder builder) {
        this.gameBoard = createGameBoard(builder);
        this.whitePieces = calculateActivePieces(this.gameBoard, Alliance.WHITE);
        this.blackPieces = calculateActivePieces(this.gameBoard, Alliance.BLACK);
        this.enPassantPawn = builder.enPassantPawn;

        final Collection<Move> whiteLegalMoves = calculateLegalMoves(this.whitePieces);
        final Collection<Move> blackLegalMoves = calculateLegalMoves(this.blackPieces);

        this.whitePlayer = new WhitePlayer(this, whiteLegalMoves, blackLegalMoves);
        this.blackPlayer = new BlackPlayer(this, whiteLegalMoves, blackLegalMoves);
        this.currentPlayer = builder.nextMoveMaker.choosePlayer(this.whitePlayer, this.blackPlayer);
        this.transitionMove = builder.transitionMove != null ? builder.transitionMove : Move.Factory.getNullMove();
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        for (int i = 0; i < Utilities.NUM_TILES; i++) {
            final String tileText = this.gameBoard.get(i).toString();
            builder.append(String.format("%3s", tileText));
            
            if ((i + 1) % Utilities.NUM_TILES_PER_ROW == 0) {
                builder.append("\n");
            }
        }
        return builder.toString();
    }

    public Player whitePlayer() {
        return this.whitePlayer;
    }
    public Player blackPlayer() {
        return this.blackPlayer;
    }
    public Player currentPlayer() {
        return this.currentPlayer;
    }
    public Pawn getEnPassantPawn() {
        return this.enPassantPawn;
    }
    public Collection<Piece> getWhitePieces() {
        return this.whitePieces;
    }
    public Collection<Piece> getBlackPieces() {
        return this.blackPieces;
    }
    public Collection<Piece> getAllPieces() {
        return Stream.concat(this.whitePieces.stream(),
                this.blackPieces.stream()).collect(Collectors.toList());
    }

    private Collection<Move> calculateLegalMoves(final Collection<Piece> pieces) {
        final List<Move> legalMoves = new ArrayList<>();
        for (final Piece piece : pieces) {
            legalMoves.addAll(piece.calculateLegalMoves(this));
        }
        return Collections.unmodifiableList(legalMoves);
    }

    private static Collection<Piece> calculateActivePieces(final List<Tile> gameBoard, final Alliance alliance) {
        final List<Piece> activePieces = new ArrayList<>();
        for (final Tile tile : gameBoard) {

            if (tile.isTileOccupied()) {
                final Piece piece = tile.getPiece();

                if (piece.getPieceAlliance() == alliance) {
                    activePieces.add(piece);
                }
            }
        }
        return Collections.unmodifiableList(activePieces);
    }

    public Tile getTile(final int tileCoordinate) {
        return gameBoard.get(tileCoordinate);
    }

    private static List<Tile> createGameBoard(final Builder builder) {
        final Tile[] tiles = new Tile[Utilities.NUM_TILES];
        for (int i = 0; i < Utilities.NUM_TILES; i++) {
            tiles[i] = Tile.createTile(i, builder.boardConfiguration.get(i));
        }
        return List.of(tiles);
    }

    public static Board createStandardBoard() {
        final Builder builder = new Builder();

        for (int i = 48; i < 56; i++) {
            builder.setPiece(new Pawn(i, Alliance.WHITE));
        }
        builder.setPiece(new King(60, Alliance.WHITE, true, true));
        builder.setPiece(new Queen(59, Alliance.WHITE));
        builder.setPiece(new Rook(56, Alliance.WHITE));
        builder.setPiece(new Rook(63, Alliance.WHITE));
        builder.setPiece(new Knight(57, Alliance.WHITE));
        builder.setPiece(new Knight(62, Alliance.WHITE));
        builder.setPiece(new Bishop(58, Alliance.WHITE));
        builder.setPiece(new Bishop(61, Alliance.WHITE));

        for (int i = 8; i < 16; i++) {
            builder.setPiece(new Pawn(i, Alliance.BLACK));
        }
        builder.setPiece(new King(4, Alliance.BLACK, true, true));
        builder.setPiece(new Queen(3, Alliance.BLACK));
        builder.setPiece(new Rook(0, Alliance.BLACK));
        builder.setPiece(new Rook(7, Alliance.BLACK));
        builder.setPiece(new Knight(1, Alliance.BLACK));
        builder.setPiece(new Knight(6, Alliance.BLACK));
        builder.setPiece(new Bishop(2, Alliance.BLACK));
        builder.setPiece(new Bishop(5, Alliance.BLACK));

        builder.nextMoveMaker = Alliance.WHITE;
        return builder.build();
    }

    public Collection<Move> getLegalMoves() {
        return Stream.concat(this.whitePlayer.getLegalMoves().stream(),
                             this.blackPlayer.getLegalMoves().stream()).collect(Collectors.toList());
    }

    public static class Builder {
        public Move transitionMove;
        Map<Integer, Piece> boardConfiguration;
        Alliance nextMoveMaker;
        Pawn enPassantPawn;

        public Builder() {
            this.boardConfiguration = new HashMap<>();
        }

        public Builder setPiece(final Piece piece) {
            this.boardConfiguration.put(piece.getPiecePosition(), piece);
            return this;
        }

        public Builder setMoveMaker(final Alliance nextMoveMaker) {
            this.nextMoveMaker = nextMoveMaker;
            return this;
        }

        public Builder setMoveTransition(final Move transitionMove) {
            this.transitionMove = transitionMove;
            return this;
        }

        public Builder setEnPassant(Pawn enPassantPawn) {
            this.enPassantPawn = enPassantPawn;
            return this;
        }

        public Board build() {
            return new Board(this);
        }
    }
}