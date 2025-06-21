package io.github.jayennn.BlockchainVoting.view.dashboardUser.profile;

import javax.swing.*;
import java.awt.*;

public class CardPanel extends JPanel implements CardView{

    private final JPanel content;

    public CardPanel(String title, String subtitle) {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220), 1),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)));

        // Header
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(Color.WHITE);

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(new Color(51, 51, 51));

        JLabel subtitleLabel = new JLabel(subtitle);
        subtitleLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        subtitleLabel.setForeground(new Color(102, 102, 102));

        header.add(titleLabel, BorderLayout.NORTH);
        header.add(subtitleLabel, BorderLayout.CENTER);

        // Content
        content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setBackground(Color.WHITE);
        content.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        content.setAlignmentY(Component.TOP_ALIGNMENT);

        add(header, BorderLayout.NORTH);
        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.setBackground(Color.WHITE);
        wrapper.add(content, BorderLayout.NORTH);
        add(wrapper, BorderLayout.CENTER);
    }

    @Override
    public void addField(String label, String value) {
        JPanel row = new JPanel();
        row.setLayout(new BorderLayout());
        row.setBackground(Color.WHITE);
        row.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));

        JLabel nameLabel = new JLabel(label + ": ");
        nameLabel.setFont(new Font("Arial", Font.BOLD, 13));
        nameLabel.setForeground(new Color(80, 80, 80));

        JLabel valueLabel = new JLabel(value);
        valueLabel.setFont(new Font("Arial", Font.PLAIN, 13));
        valueLabel.setForeground(new Color(60, 60, 60));

        row.add(nameLabel, BorderLayout.WEST);
        row.add(valueLabel, BorderLayout.CENTER);

        content.add(row);
        content.revalidate();
        content.repaint();
    }

    @Override
    public void addVoteBox(String electionName, String candidateName, String dateTime) {
        JPanel box = new JPanel();
        box.setLayout(new BoxLayout(box, BoxLayout.Y_AXIS));
        box.setBackground(new Color(245, 245, 245));
        box.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY),
                BorderFactory.createEmptyBorder(10, 15, 10, 15)
        ));
        box.setAlignmentX(Component.LEFT_ALIGNMENT); // align to left within content
        box.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));

        JLabel electionLabel = new JLabel("Election: " + electionName);
        JLabel candidateLabel = new JLabel("Candidate: " + candidateName);
        JLabel timeLabel = new JLabel("Date: " + dateTime);

        Font font = new Font("Arial", Font.PLAIN, 13);
        Color textColor = new Color(60, 60, 60);

        for (JLabel label : new JLabel[]{electionLabel, candidateLabel, timeLabel}) {
            label.setFont(font);
            label.setForeground(textColor);
            box.add(label);
        }

        content.add(Box.createVerticalStrut(10)); // spacing
        content.add(box);
        content.revalidate();
        content.repaint();
    }

    @Override
    public void clearContent() {
        content.removeAll();
        content.revalidate();
        content.repaint();
    }

}
