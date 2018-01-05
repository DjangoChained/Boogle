/*
 * Copyright (C) 2017 lucidiot
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package boogle.ui;

import boogle.jeu.Engine;
import java.awt.Window;
import java.util.stream.Stream;
import javax.swing.JOptionPane;

/**
 * Formulaire de menu principal.
 * @author rouchete
 */
public class MenuForm extends javax.swing.JDialog {

    private final Engine engine;
    private Runnable readyToPlay;
    
    /**
     * Creates new form MenuForm
     * @param engine Game engine to use
     */
    public MenuForm(Engine engine) {
        super((Window)null);
        setModal(true);
        this.engine = engine;
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        titleLabel = new javax.swing.JLabel();
        playerNameLabel1 = new javax.swing.JLabel();
        playerNameLabel2 = new javax.swing.JLabel();
        playerNameLabel3 = new javax.swing.JLabel();
        playerNameLabel4 = new javax.swing.JLabel();
        playerNameLabel5 = new javax.swing.JLabel();
        playerNameField1 = new javax.swing.JTextField();
        playerNameField2 = new javax.swing.JTextField();
        playerNameField3 = new javax.swing.JTextField();
        playerNameField4 = new javax.swing.JTextField();
        playerNameField5 = new javax.swing.JTextField();
        exitButton = new javax.swing.JButton();
        playButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        titleLabel.setFont(new java.awt.Font("DejaVu Sans", 0, 24)); // NOI18N
        titleLabel.setText("Boogle");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.weighty = 0.3;
        getContentPane().add(titleLabel, gridBagConstraints);

        playerNameLabel1.setText("Nom du joueur 1");
        playerNameLabel1.setToolTipText("");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.weighty = 0.1;
        getContentPane().add(playerNameLabel1, gridBagConstraints);

        playerNameLabel2.setText("Nom du joueur 2");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.weighty = 0.1;
        getContentPane().add(playerNameLabel2, gridBagConstraints);

        playerNameLabel3.setText("Nom du joueur 3");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.weighty = 0.1;
        getContentPane().add(playerNameLabel3, gridBagConstraints);

        playerNameLabel4.setText("Nom du joueur 4");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.weighty = 0.1;
        getContentPane().add(playerNameLabel4, gridBagConstraints);

        playerNameLabel5.setText("Nom du joueur 5");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.weighty = 0.1;
        getContentPane().add(playerNameLabel5, gridBagConstraints);

        playerNameField1.setText("Humain");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        getContentPane().add(playerNameField1, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        getContentPane().add(playerNameField2, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        getContentPane().add(playerNameField3, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        getContentPane().add(playerNameField4, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        getContentPane().add(playerNameField5, gridBagConstraints);

        exitButton.setText("Quitter");
        exitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 0.3;
        getContentPane().add(exitButton, gridBagConstraints);

        playButton.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        playButton.setForeground(new java.awt.Color(255, 51, 51));
        playButton.setText("JOUER");
        playButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.3;
        getContentPane().add(playButton, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void onReadyToPlay(Runnable r) {
        this.readyToPlay = r;
    }
    
    private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitButtonActionPerformed
        System.exit(0);
    }//GEN-LAST:event_exitButtonActionPerformed

    private void playButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playButtonActionPerformed
        engine.clearPlayers();
        Stream.of(playerNameField1, playerNameField2, playerNameField3, playerNameField4, playerNameField5)
                .filter(field -> !field.getText().trim().isEmpty())
                .forEach(field -> engine.createPlayer(field.getText().trim()));
        if(engine.getPlayers().size() < 1) {
            JOptionPane.showMessageDialog(this, "Au moins un nom de joueur doit être saisi.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        } else {
            this.setVisible(false);
            readyToPlay.run();
        }
    }//GEN-LAST:event_playButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton exitButton;
    private javax.swing.JButton playButton;
    private javax.swing.JTextField playerNameField1;
    private javax.swing.JTextField playerNameField2;
    private javax.swing.JTextField playerNameField3;
    private javax.swing.JTextField playerNameField4;
    private javax.swing.JTextField playerNameField5;
    private javax.swing.JLabel playerNameLabel1;
    private javax.swing.JLabel playerNameLabel2;
    private javax.swing.JLabel playerNameLabel3;
    private javax.swing.JLabel playerNameLabel4;
    private javax.swing.JLabel playerNameLabel5;
    private javax.swing.JLabel titleLabel;
    // End of variables declaration//GEN-END:variables
}
