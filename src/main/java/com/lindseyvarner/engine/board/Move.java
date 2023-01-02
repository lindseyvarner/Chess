package com.lindseyvarner.engine.board;

import com.lindseyvarner.engine.pieces.Pawn;
import com.lindseyvarner.engine.pieces.Piece;
import com.lindseyvarner.engine.board.Board.Builder;
import com.lindseyvarner.engine.pieces.Rook;

public abstract class Move {
    protected final Board board;
    protected final Piece movedPiece;
    protected final int destinationCoordinate;
    protected final boolean isFirstMove;

    public static final Move NULL_MOVE = new NullMove();

    private Move(final Board board,
                 final Piece movedPiece,
                 final int destinationCoordinate) {
        this.board = board;
        this.movedPiece = movedPiece;
        this.destinationCoordinate = destinationCoordinate;
        this.isFirstMove = movedPiece.isFirstMove();
    }

    private Move(final Board board, final int destinationCoordinate) {
        this.board = board;
        this.destinationCoordinate = destinationCoordinate;
        this.movedPiece = null;
        this.isFirstMove = false;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + this.destinationCoordinate;
        result = prime * result + this.movedPiece.hashCode();
        result = prime * result + this.movedPiece.getPiecePosition();
        return result;
    }

    @Override
    public boolean equals(final Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Move)) {
            return false;
        }
        final Move otherMove = (Move)other;
        return getCurrentCoordinate() == otherMove.getCurrentCoordinate() &&
               getDestinationCoordinate() == otherMove.getDestinationCoordinate() &&
               getMovedPiece().equals(otherMove.getMovedPiece());
    }

    public Board getBoard() {
        return this.board;
    }
    public int getCurrentCoordinate() {
        return this.getMovedPiece().getPiecePosition();
    }
    public int getDestinationCoordinate() {
        return this.destinationCoordinate;
    }
    public Piece getMovedPiece() {
        return this.movedPiece;
    }
    public boolean isAttack() {
        return false;
    }
    public boolean isCastle() {
        return false;
    }
    public Piece getAttackedPiece() {
        return null;
    }

    public Board execute() {
        final Builder builder = new Builder();
        for (final Piece piece : this.board.currentPlayer().getActivePieces()) {

            if (!this.movedPiece.equals(piece)) {
                builder.setPiece(piece);
            }
        }
        for (final Piece piece : this.board.currentPlayer().getOpponent().getActivePieces()) {
            builder.setPiece(piece);
        }
        builder.setPiece(this.movedPiece.movePiece(this));
        builder.setMoveMaker(this.board.currentPlayer().getOpponent().getAlliance());
        return builder.build();
    }

    public static class MajorAttackMove extends AttackMove {
        public MajorAttackMove(final Board board,
                               final Piece movedPiece,
                               final int destinationCoordinate,
                               final Piece attackedPiece) {
            super(board, movedPiece, destinationCoordinate, attackedPiece);
        }
        @Override
        public boolean equals(final Object other) {
            return this == other || other instanceof MajorAttackMove && super.equals(other);
        }
        @Override
        public String toString() {
            return movedPiece.getPieceType() +
                   Utilities.getPositionAtCoordinate(this.destinationCoordinate);
        }
    }

    public static final class MajorMove extends Move {
        public MajorMove(final Board board,
                         final Piece movedPiece,
                         final int destinationCoordinate) {
            super(board, movedPiece, destinationCoordinate);
        }
        @Override
        public boolean equals(final Object other) {
            return this == other || other instanceof MajorMove && super.equals(other);
        }
        @Override
        public String toString() {
            return movedPiece.getPieceType().toString() +
                   Utilities.getPositionAtCoordinate(this.destinationCoordinate);
        }
    }

    public static class AttackMove extends Move {
        final Piece attackedPiece;
        public AttackMove(final Board board,
                          final Piece movedPiece,
                          final int destinationCoordinate,
                          final Piece attackedPiece) {
            super(board, movedPiece, destinationCoordinate);
            this.attackedPiece = attackedPiece;
        }

        @Override
        public boolean equals(final Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof AttackMove)) {
                return false;
            }
            final AttackMove otherAttack = (AttackMove)other;
            return super.equals(otherAttack) &&
                   getAttackedPiece().equals(otherAttack.getAttackedPiece());
        }

        @Override
        public boolean isAttack() {
            return true;
        }
        @Override
        public Piece getAttackedPiece() {
            return this.attackedPiece;
        }
        @Override
        public int hashCode() {
            return this.attackedPiece.hashCode() + super.hashCode();
        }
    }
    public static final class PawnMove extends Move {
        public PawnMove(final Board board,
                        final Piece movedPiece,
                        final int destinationCoordinate) {
            super(board, movedPiece, destinationCoordinate);
        }
        @Override
        public boolean equals(final Object other) {
            return this == other || other instanceof PawnMove && super.equals(other);
        }
        @Override
        public String toString() {
            return Utilities.getPositionAtCoordinate(this.destinationCoordinate);
        }
    }

    public static class PawnAttackMove extends AttackMove {
        public PawnAttackMove(final Board board,
                              final Piece movedPiece,
                              final int destinationCoordinate,
                              final Piece attackedPiece) {
            super(board, movedPiece, destinationCoordinate, attackedPiece);
        }
        @Override
        public boolean equals(final Object other) {
            return this == other || other instanceof PawnAttackMove && super.equals(other);
        }
        @Override
        public String toString() {
            return Utilities.getPositionAtCoordinate(this.movedPiece.getPiecePosition())
                   .substring(0, 1) + "x" + Utilities.getPositionAtCoordinate(this.destinationCoordinate);
        }
    }

    public static final class EnPassant extends PawnAttackMove {
        public EnPassant(final Board board,
                         final Piece movedPiece,
                         final int destinationCoordinate,
                         final Piece attackedPiece) {
            super(board, movedPiece, destinationCoordinate, attackedPiece);
        }
        @Override
        public boolean equals(final Object other) {
            return this == other || other instanceof EnPassant && super.equals(other);
        }
        @Override
        public Board execute() {
            final Builder builder = new Builder();
            for (final Piece piece : this.board.currentPlayer().getActivePieces()) {

                if (!this.movedPiece.equals(piece)) {
                    builder.setPiece(piece);
                }
            }
            for (final Piece piece : this.board.currentPlayer().getOpponent().getActivePieces()) {

                if (!piece.equals(this.getAttackedPiece())) {
                    builder.setPiece(piece);
                }
            }
            builder.setPiece(this.movedPiece.movePiece(this));
            builder.setMoveMaker(this.board.currentPlayer().getOpponent().getAlliance());
            return builder.build();
        }
    }

    public static class Promotion extends Move {
        final Move decoratedMove;
        final Pawn promotedPawn;
        public Promotion(final Move decoratedMove) {
            super(decoratedMove.getBoard(),
                  decoratedMove.getMovedPiece(),
                  decoratedMove.getDestinationCoordinate());
            this.decoratedMove = decoratedMove;
            this.promotedPawn = (Pawn)decoratedMove.getMovedPiece();
        }
        @Override
        public int hashCode() {
            return decoratedMove.hashCode() +  (31 * promotedPawn.hashCode());
        }
        @Override
        public boolean equals(final Object other) {
            return this == other || other instanceof Promotion && super.equals(other);
        }
        @Override
        public Board execute() {
            final Board pawnMoved = this.decoratedMove.execute();
            final Builder builder = new Builder();
            for (final Piece piece : pawnMoved.currentPlayer().getActivePieces()) {

                if (!this.promotedPawn.equals(piece)) {
                    builder.setPiece(piece);
                }
            }
            for (final Piece piece : pawnMoved.currentPlayer().getOpponent().getActivePieces()) {
                builder.setPiece(piece);
            }
            builder.setPiece(this.promotedPawn.getPromotionPiece().movePiece(this));
            builder.setMoveMaker(pawnMoved.currentPlayer().getAlliance());
            return builder.build();
        }
        @Override
        public boolean isAttack() {
            return this.decoratedMove.isAttack();
        }
        @Override
        public Piece getAttackedPiece() {
            return this.decoratedMove.getAttackedPiece();
        }
        @Override
        public String toString() {
            return "";
        }
    }

    public static final class PawnJump extends Move {
        public PawnJump(final Board board,
                        final Piece movedPiece,
                        final int destinationCoordinate) {
            super(board, movedPiece, destinationCoordinate);
        }
        @Override
        public Board execute() {
            final Board.Builder builder = new Board.Builder();
            for (final Piece piece : this.board.currentPlayer().getActivePieces()) {

                if (!this.movedPiece.equals(piece)) {
                    builder.setPiece(piece);
                }
            }
            for (final Piece piece : this.board.currentPlayer().getOpponent().getActivePieces()) {
                builder.setPiece(piece);
            }
            final Pawn movedPawn = (Pawn)this.movedPiece.movePiece(this);
            builder.setPiece(movedPawn);
            builder.setEnPassant(movedPawn);
            builder.setMoveMaker(this.board.currentPlayer().getOpponent().getAlliance());
            return builder.build();
        }
        @Override
        public String toString() {
            return Utilities.getPositionAtCoordinate(this.destinationCoordinate);
        }
    }

    public static abstract class Castle extends Move {
        protected final Rook castleRook;
        protected final int castleRookStart;
        protected final int castleRookEnd;

        public Castle(final Board board,
                      final Piece movedPiece,
                      final int destinationCoordinate,
                      final Rook castleRook,
                      final int castleRookStart,
                      final int castleRookEnd) {
            super(board, movedPiece, destinationCoordinate);
            this.castleRook = castleRook;
            this.castleRookStart = castleRookStart;
            this.castleRookEnd = castleRookEnd;
        }
        public Rook getCastleRook() {
            return this.castleRook;
        }
        @Override
        public boolean isCastle() {
            return true;
        }

        @Override
        public Board execute() {
            final Builder builder = new Builder();
            for (final Piece piece : this.board.currentPlayer().getActivePieces()) {

                if (!this.movedPiece.equals(piece) && !this.castleRook.equals(piece)) {
                    builder.setPiece(piece);
                }
            }
            for (final Piece piece : this.board.currentPlayer().getOpponent().getActivePieces()) {
                builder.setPiece(piece);
            }
            builder.setPiece(this.movedPiece.movePiece(this));
            builder.setPiece(new Rook(this.castleRookEnd, this.castleRook.getPieceAlliance()));
            builder.setMoveMaker(this.board.currentPlayer().getOpponent().getAlliance());
            return builder.build();
        }
        @Override
        public int hashCode() {
            final int prime = 31;
            int result = super.hashCode();
            result = prime * result + this.castleRook.hashCode();
            result = prime * result + this.castleRookEnd;
            return result;
        }
        @Override
        public boolean equals(final Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Castle)) {
                return false;
            }
            final Castle otherCastle = (Castle)other;
            return super.equals(otherCastle) &&
                   this.castleRook.equals(otherCastle.getCastleRook());
        }
    }

    public static final class KingsideCastle extends Castle {
        public KingsideCastle(final Board board,
                              final Piece movedPiece,
                              final int destinationCoordinate,
                              final Rook castleRook,
                              final int castleRookStart,
                              final int castleRookEnd) {
            super(board, movedPiece, destinationCoordinate,
                  castleRook, castleRookStart, castleRookEnd);
        }
        @Override
        public boolean equals(final Object other) {
            return this == other || other instanceof KingsideCastle && super.equals(other);
        }
        @Override
        public String toString() {
            return "O-O";
        }
    }

    public static final class QueensideCastle extends Castle {
        public QueensideCastle(final Board board,
                               final Piece movedPiece,
                               final int destinationCoordinate,
                               final Rook castleRook,
                               final int castleRookStart,
                               final int castleRookEnd) {
            super(board, movedPiece, destinationCoordinate,
                  castleRook, castleRookStart, castleRookEnd);
        }
        @Override
        public boolean equals(final Object other) {
            return this == other || other instanceof QueensideCastle && super.equals(other);
        }
        @Override
        public String toString() {
            return "O-O-O";
        }
    }

    public static final class NullMove extends Move {
        public NullMove() {
            super(null, 65);
        }
        @Override
        public Board execute() {
            throw new RuntimeException("Unable to execute");
        }
        @Override
        public int getCurrentCoordinate() {
            return -1;
        }
    }

    public static class Factory {
        private Factory() {
           throw new RuntimeException("Unable to instantiate");
        }
        public static Move createMove(final Board board,
                                      final int currentCoordinate,
                                      final int destinationCoordinate) {
            for (final Move move : board.getLegalMoves()) {

                if (move.getCurrentCoordinate() == currentCoordinate &&
                    move.getDestinationCoordinate() == destinationCoordinate) {
                        return move;
                }
            }
            return NULL_MOVE;
        }
    }
}