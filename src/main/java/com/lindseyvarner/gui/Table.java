package com.lindseyvarner.gui;

import com.lindseyvarner.engine.board.Board;
import com.lindseyvarner.engine.board.Move;
import com.lindseyvarner.engine.board.Tile;
import com.lindseyvarner.engine.board.Utilities;
import com.lindseyvarner.engine.pieces.Piece;
import com.lindseyvarner.engine.players.Transition;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static javax.swing.SwingUtilities.isLeftMouseButton;
import static javax.swing.SwingUtilities.isRightMouseButton;

public class Table {
    private final JFrame gameFrame;
    private final BoardPanel boardPanel;
    private Board chessBoard;
    private Tile sourceTile;
    private Tile destinationTile;
    private Piece playerMovedPiece;
    private BoardDirection boardDirection;
    private boolean showLegalMoves;

    private final static Dimension OUTER_FRAME = new Dimension(700, 700);
    private final static Dimension BOARD_PANEL = new Dimension(400, 350);
    private final static Dimension TILE_PANEL = new Dimension(10, 10);
    private static String defaultIconPath = "icons/";

    private final Color lightTile = Color.decode("#DCD7C5");
    private final Color darkTile = Color.decode("#807261");

    public Table() {
        this.gameFrame = new JFrame("Chess");
        this.gameFrame.setLayout(new BorderLayout());
        final JMenuBar tableMenuBar = createMenuBar();
        this.gameFrame.setJMenuBar(tableMenuBar);
        this.gameFrame.setSize(OUTER_FRAME);
        this.chessBoard = Board.createStandardBoard();
        this.boardPanel = new BoardPanel();
        this.boardDirection = BoardDirection.NORMAL;
        this.showLegalMoves = true;
        this.gameFrame.add(this.boardPanel, BorderLayout.CENTER);
        this.gameFrame.setVisible(true);
    }

    private JMenuBar createMenuBar() {
        final JMenuBar tableMenuBar = new JMenuBar();
        tableMenuBar.add(createFileMenu());
        tableMenuBar.add(createPreferencesMenu());
        return tableMenuBar;
    }

    private JMenu createFileMenu() {
        final JMenu fileMenu = new JMenu("File");
        final JMenuItem openPGN = new JMenuItem("Load PGN file");
        openPGN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Open PGN file");
            }
        });
        fileMenu.add(openPGN);
        final JMenuItem exit = new JMenuItem("Quit");
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        fileMenu.add(exit);
        return fileMenu;
    }

    private JMenu createPreferencesMenu() {
        final JMenu preferencesMenu = new JMenu("Preferences");
        final JMenuItem flipBoard = new JMenuItem("Flip board");
        flipBoard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boardDirection = boardDirection.opposite();
                boardPanel.drawBoard(chessBoard);
            }
        });
        preferencesMenu.add(flipBoard);
        preferencesMenu.addSeparator();
        final JCheckBoxMenuItem legalMoveVisibility = new JCheckBoxMenuItem("Show legal moves", true);
        legalMoveVisibility.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showLegalMoves = legalMoveVisibility.isSelected();
            }
        });
        preferencesMenu.add(legalMoveVisibility);
        return preferencesMenu;
    }

    public enum BoardDirection {
        NORMAL {
            @Override
            List<TilePanel> traverse(final List<TilePanel> boardTiles) {
                return boardTiles;
            }
            @Override
            BoardDirection opposite() {
                return FLIPPED;
            }
        },
        FLIPPED {
            @Override
            List<TilePanel> traverse(final List<TilePanel> boardTiles) {
            List<TilePanel> flipped = new ArrayList<>(boardTiles);
            Collections.reverse(flipped);
            return flipped;
            }
            @Override
            BoardDirection opposite() {
                return NORMAL;
            }
        };
        abstract List<TilePanel> traverse(final List<TilePanel> boardTiles);
        abstract BoardDirection opposite();
    }

    private class BoardPanel extends JPanel {
        final List<TilePanel> boardTiles;

        BoardPanel() {
            super(new GridLayout(8, 8));
            this.boardTiles = new ArrayList<>();

            for (int i = 0; i < Utilities.NUM_TILES; i++) {
                final TilePanel tilePanel = new TilePanel(this, i);
                this.boardTiles.add(tilePanel);
                add(tilePanel);
            }
            setPreferredSize(BOARD_PANEL);
            validate();
        }
        public void drawBoard(final Board board) {
            removeAll();
            for (final TilePanel tilePanel : boardDirection.traverse(boardTiles)) {
                tilePanel.drawTile(board);
                add(tilePanel);
            }
            validate();
            repaint();
        }
    }

    public static class MoveLog {
        private final List<Move> moves;
        MoveLog() {
            this.moves = new ArrayList<>();
        }
        public List<Move> getMoves() {
            return this.moves;
        }
        public void addMove(final Move move) {
            this.moves.add(move);
        }
        public int size() {
            return this.moves.size();
        }
        public void clear() {
            this.moves.clear();
        }
        public Move removeMove(int index) {
            return this.moves.remove(index);
        }
        public boolean removeMove(final Move move) {
            return this.moves.remove(move);
        }
    }

    private class TilePanel extends JPanel {
        private final int tileID;

        TilePanel(final BoardPanel boardPanel, final int tileID) {
            super(new GridBagLayout());
            this.tileID = tileID;
            setPreferredSize(TILE_PANEL);
            setTileColor();
            setTileIcon(chessBoard);

            addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(final MouseEvent e) {
                    if (isRightMouseButton(e)) {

                        sourceTile = null;
                        destinationTile = null;
                        playerMovedPiece = null;
                    } else if (isLeftMouseButton(e)) {

                        if (sourceTile == null) {
                            sourceTile = chessBoard.getTile(tileID);
                            playerMovedPiece = sourceTile.getPiece();

                            if (playerMovedPiece == null) {
                                sourceTile = null;
                            }
                        } else {
                            destinationTile = chessBoard.getTile(tileID);
                            final Move move = Move.Factory.createMove(chessBoard, sourceTile.getTileCoordinate(),
                                                                      destinationTile.getTileCoordinate());
                            final Transition transition = chessBoard.currentPlayer().makeMove(move);
                            if (transition.getStatus().isDone()) {
                                chessBoard = transition.getTransitionBoard();
                            }
                            sourceTile = null;
                            destinationTile = null;
                            playerMovedPiece = null;
                        }
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                boardPanel.drawBoard(chessBoard);
                            }
                        });
                    }
                }

                @Override
                public void mousePressed(final MouseEvent e) {

                }
                @Override
                public void mouseReleased(final MouseEvent e) {
                }
                @Override
                public void mouseEntered(final MouseEvent e) {

                }
                @Override
                public void mouseExited(final MouseEvent e) {

                }
            });
            validate();
        }

        public void drawTile(final Board board) {
            setTileColor();
            setTileIcon(board);
            showLegalMoves(board);
            validate();
            repaint();
        }

        private void setTileIcon(final Board board) {
            this.removeAll();
            if (board.getTile(this.tileID).isTileOccupied()) {
                try {
                    final BufferedImage image =
                            ImageIO.read(new File(defaultIconPath + board.getTile(this.tileID)
                            .getPiece().getPieceAlliance().toString()
                            .substring(0, 1) + board.getTile(this.tileID).getPiece()
                            .toString() + ".png"));
                    add(new JLabel(new ImageIcon(image)));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        private void showLegalMoves(final Board board) {
            if (showLegalMoves) {

                for (final Move move : pieceLegalMoves(board)) {

                    if (move.getDestinationCoordinate() == this.tileID) {
                        try {
                            add(new JLabel(new ImageIcon(ImageIO.read(new File("icons/legalMove.png")))));
                        }
                        catch(Exception e) {
                            e.printStackTrace();;
                        }
                    }
                }
            }
        }

        private Collection<Move> pieceLegalMoves(final Board board) {
            if (playerMovedPiece != null && playerMovedPiece.getPieceAlliance() ==
                board.currentPlayer().getAlliance()) {
                    return playerMovedPiece.calculateLegalMoves(board);
            }
            return Collections.emptyList();
        }

        private void setTileColor() {
            if (Utilities.EIGHTH_RANK[this.tileID] || Utilities.SIXTH_RANK[this.tileID] ||
                Utilities.FOURTH_RANK[this.tileID] || Utilities.SECOND_RANK[this.tileID]) {
                    setBackground(this.tileID % 2 == 0 ? lightTile : darkTile);
            } else if (Utilities.SEVENTH_RANK[this.tileID] || Utilities.FIFTH_RANK[this.tileID] ||
                       Utilities.THIRD_RANK[this.tileID] || Utilities.FIRST_RANK[this.tileID]) {
                            setBackground(this.tileID % 2 != 0 ? lightTile : darkTile);
            }
        }
    }
}

