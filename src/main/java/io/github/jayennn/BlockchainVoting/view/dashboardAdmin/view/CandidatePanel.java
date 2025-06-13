package io.github.jayennn.BlockchainVoting.view.dashboardAdmin.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

public class CandidatePanel extends JPanel {
    private JTable candidateTable;
    private JTextField searchField;

    public CandidatePanel() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        initTopPanel();
        initTablePanel();
    }

    private void initTopPanel(){
        JPanel topPanel = new JPanel(new BorderLayout(10, 10));
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        topPanel.setBackground(Color.WHITE);

        JPanel leftTop = new JPanel(new FlowLayout(FlowLayout.LEFT));
        leftTop.setOpaque(false);

        JButton searchButton = new JButton("Search");
        searchField = new JTextField(20);
        leftTop.add(searchButton);
        leftTop.add(searchField);

        JPanel rightTop = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        rightTop.setOpaque(false);
        JButton addButton = new JButton("Add");
        rightTop.add(addButton);

        topPanel.add(leftTop, BorderLayout.WEST);
        topPanel.add(rightTop, BorderLayout.EAST);

        add(topPanel, BorderLayout.NORTH);
    }

    private void initTablePanel() {
        String[] columnNames = {"Name", "Visi", "Misi", "Actions"};
        Object[][] data = new Object[][]{
            {"Nama kandidat", "Visi", "Misi", ""}
        };

        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 3;
            }
        };

        candidateTable = new JTable(model);
        candidateTable.setRowHeight(40);

        candidateTable.getColumn("Actions").setCellRenderer(new ButtonRenderer());
        candidateTable.getColumn("Actions").setCellEditor((TableCellEditor) new ButtonEditor(new JCheckBox()));

        JScrollPane scrollPane = new JScrollPane(candidateTable);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(scrollPane, BorderLayout.CENTER);
    }

    class ButtonRenderer extends JPanel implements TableCellRenderer {
        public ButtonRenderer() {
            setLayout(new FlowLayout(FlowLayout.LEFT, 5, 0));
            setBackground(Color.WHITE);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            removeAll();
            add(createIconButton("Edit"));
            add(createIconButton("Hapus"));
            return this;
        }

        private JButton createIconButton(String text) {
            JButton button = new JButton(text);
            button.setMargin(new Insets(2, 4, 2, 4));
            button.setFocusable(false);
            button.setBackground(Color.WHITE);
            return button;
        }
    }

    class ButtonEditor extends DefaultCellEditor {
        protected JPanel panel;
        protected JButton btnEdit, btnDelete;

        public ButtonEditor(JCheckBox checkBox) {
            super(checkBox);
            panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
            panel.setBackground(Color.WHITE);
        
            btnEdit = createButton("Edit");
            btnDelete = createButton("Delete");

            panel.add(btnEdit);
            panel.add(btnDelete);

            btnEdit.addActionListener(e -> JOptionPane.showMessageDialog(null, "Edit clicked"));
            btnDelete.addActionListener(e -> JOptionPane.showMessageDialog(null, "Delete clicked"));
        }

        private JButton createButton(String text) {
            JButton btn = new JButton(text);
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
