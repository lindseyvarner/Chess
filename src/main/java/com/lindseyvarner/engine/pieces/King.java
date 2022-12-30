package com.lindseyvarner.engine.pieces;

import com.lindseyvarner.engine.Alliance;
import com.lindseyvarner.engine.board.Board;
import com.lindseyvarner.engine.board.Move;
import com.lindseyvarner.engine.board.Tile;
import com.lindseyvarner.engine.board.Utilities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class King extends Piece {
    private static final int[] CANDIDATE_MOVE_VECTOR_COORDINATES = {-9, -8, -7, -1, 1, 7, 8, 9};
    public King(final int piecePosition, final Alliance pieceAlliance) {
        super(PieceType.KING, piecePosition, pieceAlliance);
    }

    @Override
    public Collection<Move> calculateLegalMoves(Board board) {
        final List<Move> legalMoves = new ArrayList<>();
        for (final int candidateCoordinatesOffset : CANDIDATE_MOVE_VECTOR_COORDINATES) {
                final int candidateDestinationCoordinate = this.piecePosition + candidateCoordinatesOffset;

            if (isAFileExclusion(this.piecePosition, candidateCoordinatesOffset) ||
                isHFileExclusion(this.piecePosition, candidateCoordinatesOffset)) {
                    continue;
            }
            if (Utilities.isValidTileCoordinate(candidateDestinationCoordinate)) {
                    final Tile candidateDestinationTile = board.getTile(candidateDestinationCoordinate);

                    if (!candidateDestinationTile.isTileOccupied()) {
                            legalMoves.add(new Move.MajorMove(board, this, candidateDestinationCoordinate));
                    }
                else {
                    final Piece pieceAtDestination = candidateDestinationTile.getPiece();
                    final Alliance pieceAlliance = pieceAtDestination.getPieceAlliance();

                        if (this.pieceAlliance != pieceAlliance) {
                                legalMoves.add(new Move.AttackMove(board, this,
                                candidateDestinationCoordinate, pieceAtDestination));
                        }
                    }
                }
            }
            return Collections.unmodifiableList(legalMoves);
        }

    @Override
    public King movePiece(final Move move) {
        return new King(move.getDestinationCoordinate(), move.getMovedPiece().getPieceAlliance());
    }
    @Override
    public String toString() {
        return Piece.PieceType.KING.toString();
    }

    private static boolean isAFileExclusion(final int currentPosition, final int candidateOffset) {
        return Utilities.A_FILE[currentPosition] &&
               (candidateOffset == -9 || candidateOffset == -1 || candidateOffset == 7);
    }

    private static boolean isHFileExclusion(final int currentPosition, final int candidateOffset) {
        return Utilities.H_FILE[currentPosition] &&
               (candidateOffset == -7 || candidateOffset == -1 || candidateOffset == 9);
    }
}
