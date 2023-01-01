package com.lindseyvarner.engine.pieces;

import com.lindseyvarner.engine.Alliance;
import com.lindseyvarner.engine.board.Board;
import com.lindseyvarner.engine.board.Move;

import java.util.Collection;

public abstract class Piece {
    protected final PieceType pieceType;
    protected final int piecePosition;
    protected final Alliance pieceAlliance;
    protected final boolean isFirstMove;
    private final int cachedHashCode;

    Piece(final PieceType pieceType, final int piecePosition,
          final Alliance pieceAlliance, final boolean isFirstMove) {
        this.pieceType = pieceType;
        this.piecePosition = piecePosition;
        this.pieceAlliance = pieceAlliance;
        this.isFirstMove = isFirstMove;
        this.cachedHashCode = calculateHashCode();
    }

    private int calculateHashCode() {
        int result = pieceType.hashCode();
        result = 31 * result + pieceAlliance.hashCode();
        result = 31 * result + piecePosition;
        result = 31 * result + (isFirstMove ? 1 : 0);
        return result;
    }

    public PieceType getPieceType() {
        return this.pieceType;
    }
    public int getPieceValue() {
        return this.pieceType.getPieceValue();
    }

    @Override
    public boolean equals(final Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Piece)) {
            return false;
        }
        final Piece otherPiece = (Piece)other;
        return piecePosition == otherPiece.getPiecePosition() &&
               pieceType == otherPiece.getPieceType() &&
               pieceAlliance == otherPiece.getPieceAlliance() &&
               isFirstMove == otherPiece.isFirstMove();
    }

    @Override
    public int hashCode() {
        return this.cachedHashCode;
    }
    public int getPiecePosition() {
        return this.piecePosition;
    }
    public Alliance getPieceAlliance() {
        return this.pieceAlliance;
    }
    public boolean isFirstMove() {
        return isFirstMove;
    }

    public abstract Collection<Move> calculateLegalMoves(final Board board);
    public abstract Piece movePiece(Move move);

    public enum PieceType {
        KING("K", 10000) {
            @Override
            public boolean isKing() {
                return true;
            }
            @Override
            public boolean isRook() {
                return false;
            }
        },
        QUEEN("Q", 1000) {
            @Override
            public boolean isKing() {
                return false;
            }
            @Override
            public boolean isRook() {
                return false;
            }
        },
        ROOK("R", 525) {
            @Override
            public boolean isKing() {
                return false;
            }
            @Override
            public boolean isRook() {
                return true;
            }
        },
        KNIGHT("N", 350) {
            @Override
            public boolean isKing() {
                return false;
            }
            @Override
            public boolean isRook() {
                return false;
            }
        },
        BISHOP("B", 350) {
            @Override
            public boolean isKing() {
                return false;
            }
            @Override
            public boolean isRook() {
                return false;
            }
        },
        PAWN("P", 100) {
            @Override
            public boolean isKing() {
                return false;
            }
            @Override
            public boolean isRook() {
                return false;
            }
        };

        private final String pieceName;
        private int pieceValue;
        PieceType(final String pieceName, final int pieceValue) {
            this.pieceName = pieceName;
            this.pieceValue = pieceValue;
        }
        @Override
        public String toString() {
            return this.pieceName;
        }
        public int getPieceValue() {
            return this.pieceValue;
        }
        public abstract boolean isKing();
        public abstract boolean isRook();
    }
}

