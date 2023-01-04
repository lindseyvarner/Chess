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
    private boolean isCastled;

    public King(final int piecePosition, final Alliance pieceAlliance) {
        super(PieceType.KING, piecePosition, pieceAlliance, true);
    }

    public King(final int piecePosition, final Alliance pieceAlliance, final boolean isFirstMove) {
        super(PieceType.KING, piecePosition, pieceAlliance, isFirstMove);
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
                } else {
                    final Piece pieceAtDestination = candidateDestinationTile.getPiece();
                    final Alliance pieceAlliance = pieceAtDestination.getPieceAlliance();

                    if (this.pieceAlliance != pieceAlliance) {
                        legalMoves.add(new Move.MajorAttackMove(board, this,
                                candidateDestinationCoordinate, pieceAtDestination));
                    }
                }
                /* if (this.isCastled == false) {

                    if (this.kingsideCastleCapable) {
                        Collection<Piece> opponentPieces;
                        int castleRookStart, castleRookDestination, kingDestination, kingDangerPosition;

                        if (this.getPieceAlliance().toString().equals("BLACK")) {
                            castleRookStart = 7;
                            castleRookDestination = 5;
                            kingDestination = 6;
                            kingDangerPosition = 55;
                            opponentPieces = board.getWhitePieces();
                        } else {
                            castleRookStart = 63;
                            castleRookDestination = 61;
                            kingDestination = 62;
                            kingDangerPosition = 14;
                            opponentPieces = board.getBlackPieces();
                        }
                        boolean a = true, b = true;
                        if (board.getTile(kingDestination).isTileOccupied() ||
                            board.getTile(castleRookDestination).isTileOccupied()) {
                            a = false;
                        }
                        outer:
                        for (Piece opponentPiece : opponentPieces) {

                            if (!opponentPiece.getPieceType().isKing()) {
                                Collection<Move> opponentMoves = opponentPiece.calculateLegalMoves(board);

                                for (Move m : opponentMoves) {
                                    int target = m.getDestinationCoordinate();

                                    if (target == castleRookDestination || target == kingDestination) {
                                        b = false;
                                        break outer;
                                    }
                                }
                            } else {
                                if (opponentPiece.getPiecePosition() == kingDangerPosition) {
                                    b = false;
                                    break;
                                }
                            }
                        }
                        if (a & b) {
                            legalMoves.add(new Move.KingsideCastle(board, this, kingDestination,
                                    new Rook(castleRookStart, this.getPieceAlliance(), false),
                                    castleRookStart, castleRookDestination));
                        }
                    }
                        if (this.queensideCastleCapable) {
                                Collection<Piece> opponentPieces;
                                int castleRookStart, castleRookDestination, kingDestination,
                                    extraTileCoordinate, kingDangerPosition;

                            if (this.getPieceAlliance().toString().equals("BLACK")) {
                                castleRookStart = 0;
                                castleRookDestination = 3;
                                kingDestination = 2;
                                extraTileCoordinate = 1;
                                kingDangerPosition = 10;
                                opponentPieces = board.getWhitePieces();
                            } else {
                                castleRookStart = 56;
                                castleRookDestination = 59;
                                kingDestination = 58;
                                extraTileCoordinate = 57;
                                kingDangerPosition = 50;
                                opponentPieces = board.getBlackPieces();
                        }
                        boolean a = true, b = true;

                        if (board.getTile(kingDestination).isTileOccupied() ||
                            board.getTile(castleRookDestination).isTileOccupied() ||
                            board.getTile(extraTileCoordinate).isTileOccupied()) {
                                a = false;
                        }
                        outer:
                        for (Piece opponentPiece : opponentPieces) {

                            if (!opponentPiece.getPieceType().isKing()) {
                                    Collection<Move> opponentMoves = opponentPiece.calculateLegalMoves(board);

                                for (Move m : opponentMoves) {
                                    int target = m.getDestinationCoordinate();

                                    if (target == castleRookDestination ||
                                        target == kingDestination ||
                                        target == extraTileCoordinate) {
                                            b = false;
                                            break outer;
                                    }
                                }
                            }
                            else {
                                if (opponentPiece.getPiecePosition() == kingDangerPosition ||
                                    opponentPiece.getPiecePosition() == kingDangerPosition - 1) {
                                        b = false;
                                        break;
                                }
                            }
                        }
                        if (a & b)
                            legalMoves.add(new Move.QueensideCastle(board, this, kingDestination,
                                           new Rook(castleRookStart, this.getPieceAlliance(), false),
                                           castleRookStart, castleRookDestination));
                        }
                    } */
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
