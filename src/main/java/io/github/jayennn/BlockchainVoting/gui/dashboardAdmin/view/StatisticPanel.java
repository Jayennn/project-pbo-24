package io.github.jayennn.BlockchainVoting.gui.dashboardAdmin.view;

import javax.swing.*;

import java.awt.*;
import java.util.Set;

public class StatisticPanel {
    public static void main(String[] args) {
        setLayout(new GridBagLayout());
        setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.fill = GridBagConstraints.BOTH;
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.5;
        gbc.weighty = 0.5;
        add(donutChart, gbc);

        
    }
}
