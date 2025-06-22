package io.github.jayennn.BlockchainVoting.view.common;

import javax.swing.*;

public abstract class BasePanel extends JPanel {
    public BasePanel(){
        initComponents();
    }

    protected abstract void initComponents();

}
