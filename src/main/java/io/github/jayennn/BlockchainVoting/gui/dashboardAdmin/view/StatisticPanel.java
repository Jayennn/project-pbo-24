package io.github.jayennn.BlockchainVoting.gui.dashboardAdmin.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.util.Arrays;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

public class StatisticPanel extends JPanel {
    public StatisticPanel() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        JLabel title = new JLabel("Pemilihan", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

        JPanel centerPanel = new JPanel(new GridLayout(1, 2, 20, 0));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        centerPanel.setBackground(Color.WHITE);

        centerPanel.add(new DonutChartPanel());
        centerPanel.add(createTablePanel());

        add(title, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
    }
    //CONTOH DOANG
    private JPanel createTablePanel() {
        String[] columnNames = {"Data", "Jumlah", "Persentase"};
        Object[][] data = {
            {"jayen", 50, "50%"},
            {"anwar", 50, "50%"},
            
        };

        JTable table = new JTable(data, columnNames);
        table.setRowHeight(30);
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        table.setShowGrid(false);
        table.setIntercellSpacing(new Dimension(0, 0));
        table.setBackground(Color.WHITE);
        table.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(300, 200));
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.setBorder(BorderFactory.createEmptyBorder());
        return panel;
    }
    //NGATUR NYA DI SINI
    private static class DonutChartPanel extends JPanel {
        private final int[] values = {50, 50};
        private final Color[] colors = {
            new Color(143, 148, 251),
            new Color(100, 210, 255),
            new Color(225, 222, 89),
            new Color(255, 128, 128)
        };
        private final String[] labels = {"jayen", "anwar"};

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            setOpaque(false);

            int total = Arrays.stream(values).sum();
            int startAngle = 0;
            int size = Math.min(getWidth(), getHeight()) - 40;
            int x = (getWidth() - size) / 2;
            int y = (getHeight() - size) / 2;

            int centerX = getWidth() / 2;
            int centerY = getHeight() / 2;
            int radius = size / 2;

            for (int i = 0; i < values.length; i++) {
                int arcAngle = (int) Math.round(360.0 * values[i] / total);
                g2d.setColor(colors[i % colors.length]);
                g2d.fillArc(x, y, size, size, startAngle, arcAngle);

                double theta = Math.toRadians(startAngle + arcAngle / 2.0);
                int labelX = (int) (centerX + (radius + 20) * Math.cos(theta));
                int labelY = (int) (centerY + (radius + 20) * Math.sin(theta));

                if (i < labels.length) {
                    g2d.setColor(Color.DARK_GRAY);
                    String label = labels[i];
                    g2d.drawString(label, labelX - g2d.getFontMetrics().stringWidth(label) / 2, labelY);
                }
                

                startAngle += arcAngle;
            }

            int holeSize = size / 3;
            g2d.setColor(Color.WHITE);
            g2d.fillOval(x + (size - holeSize) / 2, y + (size - holeSize) / 2, holeSize, holeSize);
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(300, 300);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Statistic Panel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 500);
        frame.setLocationRelativeTo(null);
        frame.setContentPane(new StatisticPanel());
        frame.setVisible(true);
    }
}
