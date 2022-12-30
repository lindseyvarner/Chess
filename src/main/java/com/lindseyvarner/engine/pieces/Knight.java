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

public class Knight extends Piece {
    private static final int[] CANDIDATE_MOVE_COORDINATES = {-17, -15, -10, -6, 6, 10, 15, 17};
    public Knight(final int piecePosition, final Alliance pieceAlliance) {
        super(PieceType.KNIGHT, piecePosition, pieceAlliance);
    }

    @Override
    public Collection<Move> calculateLegalMoves(final Board board) {
        final List<Move> legalMoves = new ArrayList<>();
        for (final int currentCandidateOffset: CANDIDATE_MOVE_COORDINATES) {
                final int candidateDestinationCoordinate;
                candidateDestinationCoordinate = this.piecePosition + currentCandidateOffset;

            if (Utilities.isValidTileCoordinate(candidateDestinationCoordinate)) {

                if (isAFileExclusion(this.piecePosition, currentCandidateOffset) ||
                    isBFileExclusion(this.piecePosition, currentCandidateOffset) ||
                    isGFileExclusion(this.piecePosition, currentCandidateOffset) ||
                    isHFileExclusion(this.piecePosition, currentCandidateOffset)) {
                        continue;
                }

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
    public Knight movePiece(final Move move) {
        return new Knight(move.getDestinationCoordinate(), move.getMovedPiece().getPieceAlliance());
    }
    @Override
    public String toString() {
        return Piece.PieceType.KNIGHT.toString();
    }

    private static boolean isAFileExclusion(final int currentPosition, final int candidateOffset) {
        return Utilities.A_FILE[currentPosition] && (candidateOffset == -17 || candidateOffset == -10 ||
               candidateOffset == 6 || candidateOffset == 15);
    }

    private static boolean isBFileExclusion(final int currentPosition, final int candidateOffset) {
        return Utilities.B_FILE[currentPosition] && (candidateOffset == -10 || candidateOffset == 6);
    }

    private static boolean isGFileExclusion(final int currentPosition, final int candidateOffset) {
        return Utilities.G_FILE[currentPosition] && (candidateOffset == 10 || candidateOffset == -6);
    }

    private static boolean isHFileExclusion(final int currentPosition, final int candidateOffset) {
        return Utilities.H_FILE[currentPosition] && (candidateOffset == -15 || candidateOffset == -6 ||
               candidateOffset == 10 || candidateOffset == 17);
    }
}

