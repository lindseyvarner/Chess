package com.lindseyvarner.engine.players;

public enum Status {
    DONE {
        @Override
        public boolean isDone() {
            return true;
        }
    },
    ILLEGAL_MOVE {
        @Override
        public boolean isDone() {
            return false;
        }
    },
    IN_CHECK {
        @Override
        public boolean isDone() {
            return false;
        }
    };
    public abstract boolean isDone();
}
