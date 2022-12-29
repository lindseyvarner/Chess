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

public class Pawn extends Piece {
    private static final int[] CANDIDATE_MOVE_VECTOR_COORDINATES = {7, 8, 9, 16};
    public Pawn(final int piecePosition, final Alliance pieceAlliance) {
        super(piecePosition, pieceAlliance);
    }

    @Override
    public Collection<Move> calculateLegalMoves(final Board board) {
        final List<Move> legalMoves = new ArrayList<>();
        for (final int candidateCoordinatesOffset : CANDIDATE_MOVE_VECTOR_COORDINATES) {
            final int candidateDestinationCoordinate =
                    this.piecePosition + (this.pieceAlliance.getDirection() * candidateCoordinatesOffset);

            if (!Utilities.isValidTileCoordinate(candidateDestinationCoordinate)) {
                continue;
                }
            if (candidateCoordinatesOffset == 8 && board.getTile(candidateDestinationCoordinate).isTileOccupied()) {
                legalMoves.add(new Move.MajorMove(board, this, candidateDestinationCoordinate));
            }
            else if (candidateCoordinatesOffset == 16 && this.isFirstMove() &&
                    (Utilities.SECOND_ROW[this.piecePosition] && this.getPieceAlliance().isBlack()) ||
                    (Utilities.SEVENTH_ROW[this.piecePosition] && getPieceAlliance().isWhite())) {
               final int behindCandidateDestinationCoordinate =
                       this.piecePosition + (this.pieceAlliance.getDirection() * 8);

               if (!board.getTile(behindCandidateDestinationCoordinate).isTileOccupied() &&
                    !board.getTile(candidateDestinationCoordinate).isTileOccupied()) {
                   legalMoves.add(new Move.MajorMove(board, this, candidateDestinationCoordinate));
               }
            }
            else if (candidateCoordinatesOffset == 7 &&
                    !((Utilities.EIGHTH_COLUMN[this.piecePosition] && this.pieceAlliance.isWhite() ||
                    (Utilities.FIRST_COLUMN[this.piecePosition] && this.pieceAlliance.isBlack())))) {

                if (board.getTile(candidateDestinationCoordinate).isTileOccupied()) {
                    final Piece pieceOnCandidate = board.getTile(candidateDestinationCoordinate).getPiece();

                    if (this.pieceAlliance != pieceOnCandidate.getPieceAlliance()) {
                        legalMoves.add(new Move.MajorMove(board, this, candidateDestinationCoordinate));
                    }
                }
                else if (candidateCoordinatesOffset == 9 &&
                        !((Utilities.FIRST_COLUMN[this.piecePosition] && this.pieceAlliance.isWhite() ||
                          (Utilities.EIGHTH_COLUMN[this.piecePosition] && this.pieceAlliance.isBlack())))) {

                    if (board.getTile(candidateDestinationCoordinate).isTileOccupied()) {
                        final Piece pieceOnCandidate = board.getTile(candidateDestinationCoordinate).getPiece();

                        if (this.pieceAlliance != pieceOnCandidate.getPieceAlliance()) {
                            legalMoves.add(new Move.MajorMove(board, this, candidateDestinationCoordinate));
                        }
                    }
                }
            }

        }
        return Collections.unmodifiableList(legalMoves);
    }
    private static boolean isFirstColumnExclusion(final int currentPosition, final int candidateOffset) {
        return Utilities.FIRST_COLUMN[currentPosition] &&
                (candidateOffset == -1 ||candidateOffset == -7 || candidateOffset == -9);
    }

    private static boolean isEighthColumnExclusion(final int currentPosition, final int candidateOffset) {
        return Utilities.EIGHTH_COLUMN[currentPosition] &&
                (candidateOffset == 1 || candidateOffset == -7 || candidateOffset == 9);
    }
}
