package com.lindseyvarner.GUI;
import com.lindseyvarner.engine.Alliance;
import com.lindseyvarner.engine.players.Player;
import com.lindseyvarner.GUI.Table.PlayerType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Setup extends JDialog {
        private PlayerType whitePlayerType;
        private PlayerType blackPlayerType;

        private static final String HUMAN_TEXT = "Human";
        private static final String COMPUTER_TEXT = "Computer";

        Setup(final JFrame frame,
              final boolean modal) {
            super(frame, modal);
            final JPanel myPanel = new JPanel(new GridLayout(0, 1));
            final JRadioButton whiteHumanButton = new JRadioButton(HUMAN_TEXT);
            final JRadioButton whiteComputerButton = new JRadioButton(COMPUTER_TEXT);
            final JRadioButton blackHumanButton = new JRadioButton(HUMAN_TEXT);
            final JRadioButton blackComputerButton = new JRadioButton(COMPUTER_TEXT);
            whiteHumanButton.setActionCommand(HUMAN_TEXT);
            final ButtonGroup whiteGroup = new ButtonGroup();
            whiteGroup.add(whiteHumanButton);
            whiteGroup.add(whiteComputerButton);
            whiteHumanButton.setSelected(true);

            final ButtonGroup blackGroup = new ButtonGroup();
            blackGroup.add(blackHumanButton);
            blackGroup.add(blackComputerButton);
            blackHumanButton.setSelected(true);

            getContentPane().add(myPanel);
            myPanel.add(new JLabel("White"));
            myPanel.add(whiteHumanButton);
            myPanel.add(whiteComputerButton);
            myPanel.add(new JLabel("Black"));
            myPanel.add(blackHumanButton);
            myPanel.add(blackComputerButton);

            final JButton cancelButton = new JButton("Cancel");
            final JButton okButton = new JButton("OK");

            okButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    whitePlayerType = whiteComputerButton.isSelected() ? PlayerType.COMPUTER : PlayerType.HUMAN;
                    blackPlayerType = blackComputerButton.isSelected() ? PlayerType.COMPUTER : PlayerType.HUMAN;
                    Setup.this.setVisible(false);
                }
            });

            cancelButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    System.out.println("Cancel");
                    Setup.this.setVisible(false);
                }
            });

            myPanel.add(cancelButton);
            myPanel.add(okButton);

            setLocationRelativeTo(frame);
            pack();
            setVisible(false);
        }

        void promptUser() {
            setVisible(true);
            repaint();
        }

        boolean isAIPlayer(final Player player) {
            if(player.getAlliance() == Alliance.WHITE) {
                return getWhitePlayerType() == PlayerType.COMPUTER;
            }
            return getBlackPlayerType() == PlayerType.COMPUTER;
        }

        PlayerType getWhitePlayerType() {
            return this.whitePlayerType;
        }

        PlayerType getBlackPlayerType() {
            return this.blackPlayerType;
        }
    }
