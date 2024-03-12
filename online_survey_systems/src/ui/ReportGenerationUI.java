package ui;

import javax.swing.*;
import java.awt.*;

public class ReportGenerationUI extends JFrame{
	
	
	public ReportGenerationUI() {
        setTitle("Report Generation");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("Generate Survey Report");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(titleLabel);

        JButton generateReportButton = new JButton("Generate Report");
        generateReportButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        generateReportButton.addActionListener(e -> generateReport());
        panel.add(generateReportButton);

        add(panel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void generateReport() {
        // Logic to generate the report
        JOptionPane.showMessageDialog(this, "Report generated successfully!");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ReportGenerationUI::new);
    }
}