package io.github.jayennn.BlockchainVoting.app;


import io.github.jayennn.BlockchainVoting.utils.FlywayUtil;
import io.github.jayennn.BlockchainVoting.view.common.GuiManager;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        FlywayUtil.migrate();

        SwingUtilities.invokeLater(() -> {
            GuiManager guiManager = new GuiManager();
            guiManager.setVisible(true);
        });
    }
}
