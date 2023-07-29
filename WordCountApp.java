import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class WordCountApp extends JFrame {

    private JTextArea textArea;
    private JLabel wordCountLabel;
    private JLabel paragraphCountLabel;
    private JLabel pageCountLabel;
    private JLabel lineCountLabel;
    private JLabel characterCountLabel;

    public WordCountApp() {
        setTitle("Word Count App");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(textArea);

        JButton countButton = new JButton("Count");
        countButton.addActionListener(new CountButtonListener());

        wordCountLabel = new JLabel("Word count: 0");
        paragraphCountLabel = new JLabel("Paragraph count: 0");
        pageCountLabel = new JLabel("Page count: 0");
        lineCountLabel = new JLabel("Line count: 0");
        characterCountLabel = new JLabel("Character count: 0");

        JPanel infoPanel = new JPanel(new GridLayout(5, 1));
        infoPanel.add(wordCountLabel);
        infoPanel.add(paragraphCountLabel);
        infoPanel.add(pageCountLabel);
        infoPanel.add(lineCountLabel);
        infoPanel.add(characterCountLabel);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        getContentPane().add(countButton, BorderLayout.SOUTH);
        getContentPane().add(infoPanel, BorderLayout.EAST);
    }

    private class CountButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String text = textArea.getText();

            // Word count
            String[] words = text.trim().split("\\s+");
            int wordCount = words.length;
            wordCountLabel.setText("Word count: " + wordCount);

            // Paragraph count
            String[] paragraphs = text.split("\n\n+");
            int paragraphCount = paragraphs.length;
            paragraphCountLabel.setText("Paragraph count: " + paragraphCount);

            // Page count
            int lineCount = textArea.getLineCount();
            int pageHeight = textArea.getHeight();
            int linesPerPage = pageHeight / textArea.getHeight();
            int pageCount = (int) Math.ceil((double) lineCount / linesPerPage);
            pageCountLabel.setText("Page count: " + pageCount);

            // Line count
            lineCountLabel.setText("Line count: " + lineCount);

            // Character count
            int characterCount = text.replaceAll("\\s", "").length();
            characterCountLabel.setText("Character count: " + characterCount);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            WordCountApp app = new WordCountApp();
            app.setVisible(true);
        });
    }
}
