package io.github.jayennn.BlockchainVoting.view.dashboardAdmin.view;
import javax.swing.*;
import javax.swing.table.*;

import io.github.jayennn.BlockchainVoting.view.dashboardAdmin.view.CandidatePanel.ButtonEditor;

import java.awt.*;
import java.awt.event.*;
public class ElectionPanel extends JPanel {
    private JTable electionTable;
    private JTextField searchField;

    public ElectionPanel() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        initTopPanel();
        initTablePanel();
    }

    private void initTopPanel() {
        JPanel topPanel = new JPanel(new BorderLayout(10, 10));
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        topPanel.setBackground(Color.WHITE);

        JPanel leftTop = new JPanel(new FlowLayout(FlowLayout.LEFT));
        leftTop.setOpaque(false);

        JButton filterButton = new JButton("Seacrh");
        searchField = new JTextField(20);
        leftTop.add(filterButton);
        leftTop.add(searchField);

        JPanel rightTop = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        rightTop.setOpaque(false);
        JButton addButton = new JButton("add");
        rightTop.add(addButton);

        topPanel.add(leftTop, BorderLayout.WEST);
        topPanel.add(rightTop, BorderLayout.EAST);

        add(topPanel, BorderLayout.NORTH);
    }

    private void initTablePanel() {
        String[] columns = {"", "Election Name", "Title", "Start date", "end Date", "isActive", "actions"};

        Object[][] data = {
            {false, "Elections 2025", "Prisiden", "2025-03-01", "2025-03-02", "Active", ""}
        };

        DefaultTableModel model = new DefaultTableModel(data, columns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 0 || column == 6;
            }

            @Override
            public Class<?> getColumnClass(int column) {
                if (column == 0) return Boolean.class;
                return Object.class;
            }

        };

        electionTable = new JTable(model);
        electionTable.setRowHeight(30);

        electionTable.getColumn("actions").setCellRenderer(new ButtonRenderer());
        electionTable.getColumn("actions").setCellEditor(new ButtonEditor(new JCheckBox()));

        JScrollPane scrollPane = new JScrollPane (electionTable);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        add(scrollPane, BorderLayout.CENTER);
    }

    class ButtonRenderer extends JPanel implements TableCellRenderer {
        public ButtonRenderer (){
            setLayout(new FlowLayout(FlowLayout.LEFT, 5, 0));
        }
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column){
            removeAll();
            add(createIconButton("✏️"));
            add(createIconButton("🗑️"));
            add(createIconButton("🔗"));
            return this;  
        }

        private JButton createIconButton(String text) {
            JButton btn = new JButton(text);
            btn.setMargin(new Insets(2, 4, 2, 4));
            btn.setFocusable(false);
            btn.setBackground(Color.WHITE);
            return btn;
        }
    }

    class ButtonEditor extends DefaultCellEditor {
        protected JPanel panel;
        protected JButton btnEdit, btnDelete, btnShare;

        public ButtonEditor(JCheckBox checkbox) {
            super(checkbox);
            panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5,0));
            btnEdit = createButton("✏️");
            btnDelete = createButton("🗑️");
            btnShare = createButton("🔗");

            panel.add(btnEdit);
            panel.add(btnDelete);
            panel.add(btnShare);

            btnEdit.addActionListener(e -> JOptionPane.showMessageDialog(null, "Edit clicked"));
            btnDelete.addActionListener(e -> JOptionPane.showMessageDialog(null, "Delete clicked"));
            btnShare.addActionListener(e -> JOptionPane.showMessageDialog(null, "Share clicked"));

        }

        private JButton createButton(String text) {
            JButton btn = new JButton (text);
            btn.setMargin(new Insets(2, 4, 2, 4));
            btn.setFocusable(false);
            btn.setBackground(Color.WHITE);
            return btn;
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            return panel;
        }

        @Override
        public Object getCellEditorValue() {
            return "";
        }
    
    }

}
