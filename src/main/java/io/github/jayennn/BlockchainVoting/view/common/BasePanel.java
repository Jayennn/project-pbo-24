package io.github.jayennn.BlockchainVoting.view.common;

import javax.swing.*;

public abstract class BasePanel extends JPanel {
    public BasePanel(){
        initComponent();
    }

    protected abstract void initComponent();
}
