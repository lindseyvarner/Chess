package com.lindseyvarner.GUI;

import com.lindseyvarner.AI.Minimax;
import com.lindseyvarner.AI.Strategy;
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
import java.util.*;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static javax.swing.SwingUtilities.isLeftMouseButton;
import static javax.swing.SwingUtilities.isRightMouseButton;

public class Table extends Observable {
    private final JFrame gameFrame;
    private final MovePanel movePanel;
    private final CapturePanel capturePanel;
    private final OuterBoardPanel boardPanel;
    private final MoveLog moveLog;
    private final Setup setup;
    private Board chessBoard;
    private Tile sourceTile;
    private Tile destinationTile;
    private Piece playerMovedPiece;
    private BoardDirection boardDirection;
    private Move computerMove;
    private boolean showLegalMoves;

    private final static Dimension OUTER_FRAME = new Dimension(960, 740);
    private final static Dimension BOARD_PANEL = new Dimension(400, 350);
    private final static Dimension TILE_PANEL = new Dimension(10, 10);
    private static String defaultIconPath = "icons/";

    private final Color lightTile = Color.decode("#DCD7C5");
    private final Color darkTile = Color.decode("#807261");
    private final Color borderColor = Color.decode("#423B32");
    private final Color textColor = Color.WHITE;

    private static final Table INSTANCE = new Table();
    private Table() {
        this.gameFrame = new JFrame("Chess");
        this.gameFrame.setLayout(new BorderLayout());
        final JMenuBar tableMenuBar = createMenuBar();
        this.gameFrame.setJMenuBar(tableMenuBar);
        this.gameFrame.setSize(OUTER_FRAME);
        this.chessBoard = Board.createStandardBoard();
        this.movePanel = new MovePanel();
        this.capturePanel = new CapturePanel();
        this.boardPanel = new OuterBoardPanel();
        this.moveLog = new MoveLog();
        this.addObserver(new Table.AIObserver());
        this.setup = new Setup(this.gameFrame, true);
        this.boardDirection = BoardDirection.NORMAL;
        this.showLegalMoves = true;
        this.gameFrame.add(this.capturePanel, BorderLayout.WEST);
        this.gameFrame.add(this.boardPanel, BorderLayout.CENTER);
        this.gameFrame.add(this.movePanel, BorderLayout.EAST);
        this.gameFrame.setVisible(true);
    }


    public static Table get() {
        return INSTANCE;
    }
    public void show() {
        Table.get().getMoveLog().clear();
        Table.get().getMovePanel().redo(chessBoard, Table.get().getMoveLog());
        Table.get().getCapturePanel().redo(Table.get().getMoveLog());
        Table.get().getBoardPanel().drawBoard(Table.get().getBoard());
    }
    private Setup getSetup() {
        return this.setup;
    }
    private Board getBoard() {
        return this.chessBoard;
    }

    private JMenuBar createMenuBar() {
        final JMenuBar tableMenuBar = new JMenuBar();
        tableMenuBar.add(createFileMenu());
        tableMenuBar.add(createPreferencesMenu());
        tableMenuBar.add(createOptionsMenu());
        return tableMenuBar;
    }

    private JMenu createFileMenu() {
        final JMenu fileMenu = new JMenu("File");
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

    private JMenu createOptionsMenu() {
        final JMenu optionsMenu = new JMenu("Options");
        final JMenuItem gameSetup = new JMenuItem("Game setup");
        gameSetup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Table.get().getSetup().promptUser();
                Table.get().setUpdate(Table.get().getSetup());
            }
        });
        optionsMenu.add(gameSetup);
        return optionsMenu;
    }

    private void setUpdate(final Setup setup) {
        setChanged();
        notifyObservers(setup);
    }

    private static class AIObserver implements Observer {
        @Override
        public void update(final Observable o, final Object arg) {
            if (Table.get().getSetup().isAIPlayer(Table.get().getBoard().currentPlayer()) &&
                !Table.get().getBoard().currentPlayer().isCheckmated() &&
                !Table.get().getBoard().currentPlayer().isStalemated()) {
                    final AIBrain brain = new AIBrain();
                    brain.execute();
            }
            if (Table.get().getBoard().currentPlayer().isCheckmated()) {
                    System.out.println(Table.get().getBoard().currentPlayer() + " is checkmated");
            }
            if (Table.get().getBoard().currentPlayer().isStalemated()) {
                    System.out.println("Draw by stalemate");
            }
        }
    }

    public void updateBoard(final Board board) {
        this.chessBoard = board;
    }
    public void updateComputerMove(final Move move) {
        this.computerMove = move;
    }
    private MoveLog getMoveLog() {
        return this.moveLog;
    }
    private MovePanel getMovePanel() {
        return this.movePanel;
    }
    private CapturePanel getCapturePanel() {
        return this.capturePanel;
    }
    private OuterBoardPanel getBoardPanel() {
        return this.boardPanel;
    }

    private void moveUpdate(final PlayerType playerType) {
        setChanged();
        notifyObservers(playerType);
    }

    private static class AIBrain extends SwingWorker<Move, String> {
        private AIBrain() {
        }
        @Override
        protected Move doInBackground() throws Exception {
            final Strategy minimax = new Minimax(4);
            final Move bestMove = minimax.execute(Table.get().getBoard());
            return bestMove;
        }
        @Override
        public void done() {
            try {
                final Move bestMove = get();
                Table.get().updateComputerMove(bestMove);
                Table.get().updateBoard(Table.get().getBoard().currentPlayer()
                           .makeMove(bestMove).getTransitionBoard());

                Table.get().getMoveLog().addMove(bestMove);
                Table.get().getMovePanel().redo(Table.get().getBoard(),
                           Table.get().getMoveLog());

                Table.get().getCapturePanel().redo(Table.get().getMoveLog());
                Table.get().getBoardPanel().drawBoard(Table.get().getBoard());
                Table.get().moveUpdate(PlayerType.COMPUTER);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        }
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

    private class OuterBoardPanel extends JPanel {
        private static final int GAP = 5;
        private BoardPanel innerBoard;

        OuterBoardPanel() {
            innerBoard = new BoardPanel();

            setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 1;
            gbc.gridwidth = 1;
            gbc.gridheight = 1;
            gbc.weightx = 0.0;
            gbc.weighty = 0.0;
            gbc.fill = GridBagConstraints.BOTH;
            gbc.anchor = GridBagConstraints.WEST;
            gbc.insets = new Insets(0, 2 * GAP, 0, 2 * GAP);
            add(createRankPanel(), gbc);

            gbc.gridx = 2;
            gbc.anchor = GridBagConstraints.EAST;
            add(createRankPanel(), gbc);

            gbc.gridx = 1;
            gbc.gridy = 0;
            gbc.anchor = GridBagConstraints.SOUTH;
            gbc.insets = new Insets(GAP, 0, GAP, 0);
            add(createFilePanel(), gbc);

            gbc.gridy = 2;
            gbc.anchor = GridBagConstraints.NORTH;
            add(createFilePanel(), gbc);

            gbc.gridx = 1;
            gbc.gridy = 1;
            gbc.weightx = 1;
            gbc.weighty = 1;
            gbc.anchor = GridBagConstraints.CENTER;
            gbc.insets = new Insets(0, 0, 0, 0);

            add(innerBoard, gbc);
            setBackground(borderColor);
        }

        private JPanel createFilePanel() {
            JPanel filePanel = new JPanel(new GridLayout(1, 0));

            for (char c : Arrays.asList('A','B','C','D','E', 'F','G','H')) {
                var label = new JLabel(String.valueOf(c), SwingConstants.CENTER);
                label.setForeground(textColor);
                filePanel.add(label);
            }
            filePanel.setBackground(borderColor);
            return filePanel;
        }

        private JPanel createRankPanel() {
            JPanel rankPanel = new JPanel(new GridLayout(0, 1));

            for (int i = 0; i < 8; i++) {
                int row = 8 - i;
                var label = new JLabel(String.valueOf(row));
                label.setForeground(textColor);
                rankPanel.add(label);
            }
            rankPanel.setBackground(borderColor);
            return rankPanel;
        }

        public void drawBoard(final Board board) {
            innerBoard.drawBoard(board);
        }

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
            setBackground(borderColor);
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

    enum PlayerType {
        HUMAN,
        COMPUTER
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
                    }
                    else if (isLeftMouseButton(e)) {

                        if (sourceTile == null) {
                                sourceTile = chessBoard.getTile(tileID);
                                playerMovedPiece = sourceTile.getPiece();

                            if (playerMovedPiece == null) {
                                    sourceTile = null;
                            }
                        }
                        else {
                            destinationTile = chessBoard.getTile(tileID);
                            final Move move = Move.Factory.createMove(chessBoard, sourceTile.getTileCoordinate(),
                                              destinationTile.getTileCoordinate());
                            final Transition transition = chessBoard.currentPlayer().makeMove(move);

                            if (transition.getStatus().isDone()) {
                                    chessBoard = transition.getTransitionBoard();
                                    moveLog.addMove(move);
                            }
                            sourceTile = null;
                            destinationTile = null;
                            playerMovedPiece = null;
                        }
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                movePanel.redo(chessBoard, moveLog);
                                capturePanel.redo(moveLog);

                                if (setup.isAIPlayer(chessBoard.currentPlayer())) {
                                        Table.get().moveUpdate(PlayerType.HUMAN);
                                }
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
                    final BufferedImage image = ImageIO.read(new File(defaultIconPath +
                                                board.getTile(this.tileID)
                                                .getPiece().getPieceAlliance().toString()
                                                .substring(0, 1) + board.getTile(this.tileID)
                                                .getPiece().toString() + ".png"));
                    add(new JLabel(new ImageIcon(image)));
                }
                catch (IOException e) {
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


