package com.lindseyvarner.engine.board;

import java.util.Map;

public class Utilities {
    public static final boolean[] A_FILE = initFile(0);
    public static final boolean[] B_FILE = initFile(1);
    public static final boolean[] C_FILE = initFile(2);
    public static final boolean[] D_FILE = initFile(3);
    public static final boolean[] E_FILE = initFile(4);
    public static final boolean[] F_FILE = initFile(5);
    public static final boolean[] G_FILE = initFile(6);
    public static final boolean[] H_FILE = initFile(7);

    public static final boolean[] EIGHTH_RANK = initRank(0);
    public static final boolean[] SEVENTH_RANK = initRank(8);
    public static final boolean[] SIXTH_RANK = initRank(16);
    public static final boolean[] FIFTH_RANK = initRank(24);
    public static final boolean[] FOURTH_RANK = initRank(32);
    public static final boolean[] THIRD_RANK = initRank(40);
    public static final boolean[] SECOND_RANK = initRank(48);
    public static final boolean[] FIRST_RANK = initRank(56);

    public static final String[] ALGEBRAIC_NOTATION = initializeAlgebraicNotation();
    public static final Map<String, Integer> POSITION_COORDINATE = initializePositionCoordinate();

    public static final int NUM_TILES = 64;
    public static final int NUM_TILES_PER_ROW = 8;

    private Utilities() {
        throw new RuntimeException("Unable to instantiate");
    }

    private static boolean[] initFile(int fileIndex) {
        final boolean[] file = new boolean[NUM_TILES];
        do {
            file[fileIndex] = true;
            fileIndex += NUM_TILES_PER_ROW;
        } while (fileIndex < NUM_TILES);
        return file;
    }

    private static boolean[] initRank(int rankIndex) {
        final boolean[] rank = new boolean[NUM_TILES];
        do {
            rank[rankIndex] = true;
            rankIndex++;
        } while (rankIndex % NUM_TILES_PER_ROW != 0);
        return rank;
    }

    public static boolean isValidTileCoordinate(final int coordinate) {
        return coordinate >= 0 && coordinate < NUM_TILES;
    }

    public static int getCoordinateAtPosition(final String position) {
        return POSITION_COORDINATE.get(position);
    }

    public static int getPositionAtCoordinate(final String coordinate) {
        return ALGEBRAIC_NOTATION[coordinate];
    }

}
