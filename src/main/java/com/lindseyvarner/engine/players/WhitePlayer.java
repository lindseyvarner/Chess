package com.lindseyvarner.engine.players;

import com.lindseyvarner.engine.Alliance;
import com.lindseyvarner.engine.board.Board;
import com.lindseyvarner.engine.board.Move;
import com.lindseyvarner.engine.board.Tile;
import com.lindseyvarner.engine.pieces.Piece;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class WhitePlayer extends Player {
    public WhitePlayer(final Board board,
                       final Collection<Move> whiteLegalMoves,
                       final Collection<Move> blackLegalMoves) {
        super(board, whiteLegalMoves, blackLegalMoves);
    }

    @Override
    public Collection<Piece> getActivePieces() {
        return this.board.getWhitePieces();
    }

    @Override
    public Alliance getAlliance() {
        return Alliance.WHITE;
    }

    @Override
    public Player getOpponent() {
        return this.board.blackPlayer();
    }

    @Override
    protected Collection<Move> calculateKingCastles(Collection<Move> playerLegals,
                                                    Collection<Move> opponentLegals) {
        final List<Move> kingCastles = new ArrayList<>();
        if (this.playerKing.isFirstMove() && !this.isInCheck()) {

            if (!this.board.getTile(61).isTileOccupied() &&
                !this.board.getTile(62).isTileOccupied()) {
                    final Tile rookTile = this.board.getTile(63);

                    if (rookTile.isTileOccupied() && rookTile.getPiece().isFirstMove()) {
                        kingCastles.add(null);
                    }
            }
            if (!this.board.getTile(59).isTileOccupied() &&
                !this.board.getTile(58).isTileOccupied() &&
                !this.board.getTile(57).isTileOccupied()) {
                final Tile rookTile = this.board.getTile(56);

                if (rookTile.isTileOccupied() && rookTile.getPiece().isFirstMove()) {

                    if (Player.calculateAttacks(61, opponentLegals).isEmpty() &&
                        Player.calculateAttacks(62, opponentLegals).isEmpty() &&
                        rookTile.getPiece().getPieceType().isRook()) {
                            kingCastles.add(null);
                    }
                }
            }
        }
        return Collections.unmodifiableList(kingCastles);
    }
}
