package com.lindseyvarner.gui;

import com.lindseyvarner.engine.board.Board;
import com.lindseyvarner.engine.board.Utilities;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Table {
    private final JFrame gameFrame;
    private final BoardPanel boardPanel;
    private final Board chessBoard;
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
        this.gameFrame.add(this.boardPanel, BorderLayout.CENTER);
        this.gameFrame.setVisible(true);
    }

    private JMenuBar createMenuBar() {
        final JMenuBar tableMenuBar = new JMenuBar();
        tableMenuBar.add(createFileMenu());
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
        final JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        fileMenu.add(exit);
        return fileMenu;
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
    }

    private class TilePanel extends JPanel {
        private final int tileID;

        TilePanel(final BoardPanel boardPanel, final int tileID) {
            super(new GridBagLayout());
            this.tileID = tileID;
            setPreferredSize(TILE_PANEL);
            setTileColor();
            setTileIcon(chessBoard);
            validate();
        }
        private void setTileIcon(final Board board) {
            this.removeAll();
            if (board.getTile(this.tileID).isTileOccupied()) {
                try {
                    final BufferedImage image = ImageIO.read(new File(defaultIconPath + board.getTile(this.tileID)
                                                            .getPiece().getPieceAlliance().toString()
                                                            .substring(0, 1) + board.getTile(this.tileID).getPiece()
                                                            .toString() + ".png"));
                    add(new JLabel(new ImageIcon(image)));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
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

