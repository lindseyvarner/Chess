package com.lindseyvarner.engine.pieces;

import com.lindseyvarner.engine.Alliance;
import com.lindseyvarner.engine.board.Board;
import com.lindseyvarner.engine.board.Move;
import com.lindseyvarner.engine.board.Utilities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Pawn extends Piece {
    private static final int[] CANDIDATE_MOVE_VECTOR_COORDINATES = {7, 8, 9, 16};
    public Pawn(final int piecePosition, final Alliance pieceAlliance) {
        super(PieceType.PAWN, piecePosition, pieceAlliance, true);
    }

    public Pawn(final int piecePosition, final Alliance pieceAlliance, final boolean isFirstMove) {
        super(PieceType.PAWN, piecePosition, pieceAlliance, isFirstMove);
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
            if (candidateCoordinatesOffset == 8 && !board.getTile(candidateDestinationCoordinate).isTileOccupied()) {

                if (this.pieceAlliance.isPromotionSquare(candidateDestinationCoordinate)) {
                    legalMoves.add(new Move.Promotion(new Move.PawnMove
                                  (board, this, candidateDestinationCoordinate)));
                }
                else {
                    legalMoves.add(new Move.PawnMove
                            (board, this, candidateDestinationCoordinate));
                }
            }
            else if (candidateCoordinatesOffset == 16 && this.isFirstMove() &&
                    ((Utilities.SEVENTH_RANK[this.piecePosition] && this.getPieceAlliance().isBlack()) ||
                    (Utilities.SECOND_RANK[this.piecePosition] && getPieceAlliance().isWhite()))) {
                        final int behindCandidateDestinationCoordinate =
                        this.piecePosition + (this.pieceAlliance.getDirection() * 8);

               if (!board.getTile(behindCandidateDestinationCoordinate).isTileOccupied() &&
                   !board.getTile(candidateDestinationCoordinate).isTileOccupied()) {
                        legalMoves.add(new Move.PawnJump
                                      (board, this, candidateDestinationCoordinate));
               }
            }
            else if (candidateCoordinatesOffset == 7 &&
                    !((Utilities.H_FILE[this.piecePosition] && this.pieceAlliance.isWhite() ||
                    (Utilities.A_FILE[this.piecePosition] && this.pieceAlliance.isBlack())))) {

                if (board.getTile(candidateDestinationCoordinate).isTileOccupied()) {
                    final Piece pieceOnCandidate = board.getTile(candidateDestinationCoordinate).getPiece();

                    if (this.pieceAlliance != pieceOnCandidate.getPieceAlliance()) {

                        if (this.pieceAlliance.isPromotionSquare(candidateDestinationCoordinate)) {
                            legalMoves.add(new Move.Promotion(new Move.PawnAttackMove
                                          (board, this, candidateDestinationCoordinate, pieceOnCandidate)));
                        }
                        else {
                            legalMoves.add(new Move.PawnAttackMove
                                    (board, this,
                                     candidateDestinationCoordinate, pieceOnCandidate));
                            }
                        }
                    }
                    else if (board.getEnPassantPawn() != null) {

                    if (board.getEnPassantPawn().getPiecePosition() ==
                            (this.piecePosition + (this.pieceAlliance.getOppositeDirection()))) {
                        final Piece pieceOnCandidate = board.getEnPassantPawn();

                        if (this.pieceAlliance != pieceOnCandidate.getPieceAlliance()) {
                            legalMoves.add(new Move.EnPassant
                                    (board, this,
                                     candidateDestinationCoordinate, pieceOnCandidate));
                        }
                    }
                }
            }
            else if (candidateCoordinatesOffset == 9 &&
                     !((Utilities.A_FILE[this.piecePosition] && this.pieceAlliance.isWhite() ||
                     (Utilities.H_FILE[this.piecePosition] && this.pieceAlliance.isBlack())))) {

                        if (board.getTile(candidateDestinationCoordinate).isTileOccupied()) {
                                final Piece pieceOnCandidate = board.getTile(candidateDestinationCoordinate).getPiece();

                            if (this.pieceAlliance != pieceOnCandidate.getPieceAlliance()) {

                                if (this.pieceAlliance.isPromotionSquare(candidateDestinationCoordinate)) {
                                    legalMoves.add(new Move.Promotion(new Move.PawnAttackMove
                                                  (board, this,
                                                   candidateDestinationCoordinate, pieceOnCandidate)));
                                }
                                else {
                                    legalMoves.add(new Move.PawnAttackMove
                                                  (board, this,
                                                   candidateDestinationCoordinate, pieceOnCandidate));
                                }
                            }
                        }
                        else if (board.getEnPassantPawn() != null) {

                            if (board.getEnPassantPawn().getPiecePosition() ==
                               (this.piecePosition - (this.pieceAlliance.getOppositeDirection()))) {
                                    final Piece pieceOnCandidate = board.getEnPassantPawn();

                                if (this.pieceAlliance != pieceOnCandidate.getPieceAlliance()) {
                                        legalMoves.add(new Move.EnPassant
                                                      (board, this,
                                                       candidateDestinationCoordinate, pieceOnCandidate));
                                }
                            }
                        }
                    }
            }
        return Collections.unmodifiableList(legalMoves);
    }

    @Override
    public Pawn movePiece(final Move move) {
        return new Pawn(move.getDestinationCoordinate(), move.getMovedPiece().getPieceAlliance());
    }
    @Override
    public String toString() {
        return Piece.PieceType.PAWN.toString();
    }
    public Piece getPromotionPiece() {
        return new Queen(this.piecePosition, this.pieceAlliance, false);
    }

    private static boolean isAFileExclusion(final int currentPosition, final int candidateOffset) {
        return Utilities.A_FILE[currentPosition] &&
               (candidateOffset == -1 ||candidateOffset == -7 || candidateOffset == -9);
    }

    private static boolean isHFileExclusion(final int currentPosition, final int candidateOffset) {
        return Utilities.H_FILE[currentPosition] &&
               (candidateOffset == 1 || candidateOffset == -7 || candidateOffset == 9);
    }
}
