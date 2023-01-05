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

public class Bishop extends Piece {
    private static final int[] CANDIDATE_MOVE_VECTOR_COORDINATES = {-9, -7, 7, 9};
    public Bishop(final int piecePosition, final Alliance pieceAlliance) {
        super(PieceType.BISHOP, piecePosition, pieceAlliance, true);
    }

    public Bishop(final int piecePosition, final Alliance pieceAlliance, final boolean isFirstMove) {
        super(PieceType.BISHOP, piecePosition, pieceAlliance, isFirstMove);
    }

    @Override
    public Collection<Move> calculateLegalMoves(final Board board) {
        final List<Move> legalMoves = new ArrayList<>();
        for (final int candidateCoordinatesOffset : CANDIDATE_MOVE_VECTOR_COORDINATES) {
            int candidateDestinationCoordinate = this.piecePosition;

            while (Utilities.isValidTileCoordinate(candidateDestinationCoordinate)) {

                if (isAFileExclusion(candidateDestinationCoordinate, candidateCoordinatesOffset) ||
                    isHFileExclusion(candidateDestinationCoordinate, candidateCoordinatesOffset)) {
                        break;
                }
                candidateDestinationCoordinate += candidateCoordinatesOffset;
                if (Utilities.isValidTileCoordinate(candidateDestinationCoordinate)) {
                    final Tile candidateDestinationTile = board.getTile(candidateDestinationCoordinate);

                    if (!candidateDestinationTile.isTileOccupied()) {
                            legalMoves.add(new Move.MajorMove(board, this, candidateDestinationCoordinate));
                    }
                    else {
                        final Piece pieceAtDestination = candidateDestinationTile.getPiece();
                        final Alliance pieceAlliance = pieceAtDestination.getPieceAlliance();

                        if (this.pieceAlliance != pieceAlliance) {
                                legalMoves.add(new Move.MajorAttackMove(board, this,
                                candidateDestinationCoordinate, pieceAtDestination));
                        }
                        break;
                    }
                }
            }
        }
        return Collections.unmodifiableList(legalMoves);
    }

    @Override
    public Bishop movePiece(final Move move) {
        return new Bishop(move.getDestinationCoordinate(), move.getMovedPiece().getPieceAlliance());
    }
    @Override
    public String toString() {
        return Piece.PieceType.BISHOP.toString();
    }

    private static boolean isAFileExclusion(final int currentPosition, final int candidateOffset) {
        return Utilities.A_FILE[currentPosition] && (candidateOffset == -9 || candidateOffset == 7);
    }

    private static boolean isHFileExclusion(final int currentPosition, final int candidateOffset) {
        return Utilities.H_FILE[currentPosition] && (candidateOffset == -7 || candidateOffset == 9);
    }
}
