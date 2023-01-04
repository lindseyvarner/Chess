package com.lindseyvarner.engine.players;

import com.lindseyvarner.engine.Alliance;
import com.lindseyvarner.engine.board.Board;
import com.lindseyvarner.engine.board.Move;
import com.lindseyvarner.engine.board.Tile;
import com.lindseyvarner.engine.pieces.Piece;
import com.lindseyvarner.engine.pieces.Rook;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class BlackPlayer extends Player {
    public BlackPlayer(final Board board,
                       final Collection<Move> whiteLegalMoves,
                       final Collection<Move> blackLegalMoves) {
        super(board, blackLegalMoves, whiteLegalMoves);
    }
    @Override
    public Collection<Piece> getActivePieces() {
        return this.board.getBlackPieces();
    }
    @Override
    public Alliance getAlliance() {
        return Alliance.BLACK;
    }
    @Override
    public Player getOpponent() {
        return this.board.whitePlayer();
    }
    @Override
    protected Collection<Move> calculateKingCastles(final Collection<Move> playerLegals,
                                                    final Collection<Move> opponentLegals) {
        final List<Move> kingCastles = new ArrayList<>();
        if (this.playerKing.isFirstMove() && !this.isInCheck()) {

            if (!this.board.getTile(5).isTileOccupied() &&
                !this.board.getTile(6).isTileOccupied()) {
                    final Tile rookTile = this.board.getTile(7);

                if (rookTile.isTileOccupied() && rookTile.getPiece().isFirstMove()) {

                    if (Player.calculateAttacks(5, opponentLegals).isEmpty() &&
                        Player.calculateAttacks(6, opponentLegals).isEmpty() &&
                        rookTile.getPiece().getPieceType().isRook()) {
                            kingCastles.add(new Move.KingsideCastleMove(this.board, this.playerKing, 6,
                                                                   (Rook)rookTile.getPiece(),
                                                                    rookTile.getTileCoordinate(), 5));
                    }
                }
            }
            if (!this.board.getTile(1).isTileOccupied() &&
                !this.board.getTile(2).isTileOccupied() &&
                !this.board.getTile(3).isTileOccupied()) {
                    final Tile rookTile = this.board.getTile(0);

                if (rookTile.isTileOccupied() && rookTile.getPiece().isFirstMove() &&
                    Player.calculateAttacks(2, opponentLegals).isEmpty() &&
                    Player.calculateAttacks(3, opponentLegals).isEmpty() &&
                    rookTile.getPiece().getPieceType().isRook()) {
                        kingCastles.add(new Move.QueensideCastleMove(this.board, this.playerKing, 2,
                                                                (Rook)rookTile.getPiece(),
                                                                 rookTile.getTileCoordinate(), 3));
                    }
                }
            }
            return Collections.unmodifiableList(kingCastles);
        }
    }

