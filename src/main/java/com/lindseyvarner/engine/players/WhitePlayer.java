package com.lindseyvarner.engine.players;

import com.lindseyvarner.engine.Alliance;
import com.lindseyvarner.engine.board.Board;
import com.lindseyvarner.engine.board.Move;
import com.lindseyvarner.engine.pieces.Piece;

import java.util.Collection;

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
}
