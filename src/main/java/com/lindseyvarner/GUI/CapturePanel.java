package com.lindseyvarner.GUI;

import com.lindseyvarner.engine.board.Move;
import com.lindseyvarner.engine.pieces.Piece;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;

public class CapturePanel extends JPanel {
    private static final EtchedBorder PANEL_BORDER = new EtchedBorder(EtchedBorder.RAISED);
    private static final Color PANEL_COLOR = Color.decode("#285440");
    private static final Dimension CAPTURE_PANEL_DIMENSION = new Dimension(110, 300);
    private final JPanel northPanel;
    private final JPanel southPanel;

    public CapturePanel() {
        super(new BorderLayout());
        setBackground(PANEL_COLOR);
        setBorder(PANEL_BORDER);
        this.northPanel = new JPanel(new GridLayout(8, 2));
        this.southPanel = new JPanel(new GridLayout(8, 2));
        this.northPanel.setBackground(PANEL_COLOR);
        this.southPanel.setBackground(PANEL_COLOR);
        add(this.northPanel, BorderLayout.NORTH);
        add(this.southPanel, BorderLayout.SOUTH);
        setPreferredSize(CAPTURE_PANEL_DIMENSION);
    }

    public void redo(final Table.MoveLog moveLog) {
        southPanel.removeAll();
        northPanel.removeAll();

        final List<Piece> whiteCaptures = new ArrayList<>();
        final List<Piece> blackCaptures = new ArrayList<>();

        for (final Move move : moveLog.getMoves()) {

            if (move.isAttack()) {
                final Piece capturedPiece = move.getAttackedPiece();

                if (capturedPiece.getPieceAlliance().isWhite()) {
                    blackCaptures.add(capturedPiece);
                }
                else if (capturedPiece.getPieceAlliance().isBlack()) {
                    whiteCaptures.add(capturedPiece);
                }
                else {
                    throw new RuntimeException("Error");
                }
            }
        }

        Collections.sort(whiteCaptures, new Comparator<Piece>() {
            @Override
            public int compare(Piece p1, Piece p2) {
                return Integer.compare(p1.getPieceValue(), p2.getPieceValue());
            }
        });

        Collections.sort(blackCaptures, new Comparator<Piece>() {
            @Override
            public int compare(Piece p1, Piece p2) {
                return Integer.compare(p1.getPieceValue(), p2.getPieceValue());
            }
        });

        for (final Piece capturedPiece : whiteCaptures) {
            try {
                final BufferedImage image = ImageIO.read(new File("icons/"
                                            + capturedPiece.getPieceAlliance().toString().substring(0, 1) +
                                            "" + capturedPiece.toString() + ".png"));
                final ImageIcon icon = new ImageIcon(image);
                final JLabel label = new JLabel(new ImageIcon(icon.getImage().getScaledInstance
                                               (icon.getIconWidth() - 40,
                                                icon.getIconHeight() - 35, Image.SCALE_SMOOTH)));
                this.southPanel.add(label);
            }
            catch (final IOException e) {
                e.printStackTrace();
            }
        }

        for (final Piece capturedPiece : blackCaptures) {
            try {
                final BufferedImage image = ImageIO.read(new File("icons/"
                                            + capturedPiece.getPieceAlliance().toString().substring(0, 1) +
                                            "" + capturedPiece.toString() + ".png"));
                final ImageIcon icon = new ImageIcon(image);
                final JLabel label = new JLabel(new ImageIcon(icon.getImage().getScaledInstance
                                               (icon.getIconWidth() - 40,
                                                icon.getIconHeight() - 35, Image.SCALE_SMOOTH)));
                this.northPanel.add(label);
            }
            catch (final IOException e) {
                e.printStackTrace();
            }
        }
        validate();
    }
}
