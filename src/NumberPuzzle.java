import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class NumberPuzzle extends JFrame {

    public static int seconds = 600;
    public static Timer timer;
    static JButton[][] jButtons = new JButton[4][4];

    public static void main(String[] args) throws IOException {

        JFrame jFrame = new JFrame("Femton Pussel");
        jFrame.setPreferredSize(new Dimension(650, 670));
        jFrame.setBackground(Color.cyan);
        jFrame.setVisible(true);

        JPanel jPanelMain = new JPanel();
        jPanelMain.setPreferredSize(new Dimension(650, 670));
        jPanelMain.setBackground(Color.gray);
        jPanelMain.setLayout(new FlowLayout());

        JPanel jPanel = new JPanel();
        jPanel.setPreferredSize(new Dimension(550, 550));
        jPanel.setBackground(Color.BLUE);
        jPanel.setLayout(new GridLayout(4, 4));

        JPanel jPanelOmSpelet = new JPanel();
        jPanelOmSpelet.setPreferredSize(new Dimension(550, 550));
        jPanelOmSpelet.setVisible(false);
        jPanelOmSpelet.setLayout(new BorderLayout());
        jPanelOmSpelet.setBackground(Color.gray);

        JPanel jPanel1 = new JPanel();
        jPanel1.setPreferredSize(new Dimension(550, 50));
        jPanel1.setLayout(new GridLayout(1, 0));
        jPanel1.setBackground(Color.BLUE);

        JButton nyttSpelButton = new JButton();
        nyttSpelButton.setText("Nytt Spel");
        nyttSpelButton.setBackground(Color.YELLOW);
        nyttSpelButton.setForeground(Color.black);
        nyttSpelButton.setSize(140, 140);
        nyttSpelButton.setHorizontalAlignment(0);

        JButton finishButton = new JButton();
        finishButton.setText("Klart");
        finishButton.setBackground(Color.YELLOW);
        finishButton.setForeground(Color.black);
        finishButton.setSize(140, 140);
        finishButton.setHorizontalAlignment(0);
        finishButton.setEnabled(false);
        finishButton.setBackground(Color.lightGray);

        JButton closeWindowButton = new JButton();
        closeWindowButton.setText("Stäng Fönster");
        closeWindowButton.setBackground(Color.YELLOW);
        closeWindowButton.setForeground(Color.black);
        closeWindowButton.setSize(140, 140);

        JButton aboutGameButton = new JButton();
        aboutGameButton.setText("Om spelet");
        aboutGameButton.setBackground(Color.YELLOW);
        aboutGameButton.setForeground(Color.black);
        aboutGameButton.setSize(140, 140);

        JTextPane jTextPane = new JTextPane();
        jTextPane.setBackground(Color.RED);
        jTextPane.setForeground(Color.white);

        JLabel countDownLabel = new JLabel("Timer: 10:00");
        countDownLabel.setBackground(Color.yellow);
        countDownLabel.setVisible(true);
        countDownLabel.setForeground(Color.white);
        countDownLabel.setSize(160, 150);
        countDownLabel.setFont(new Font(Font.SERIF, Font.BOLD, 20));

        JEditorPane headerLabel = new JEditorPane();
        headerLabel.setBackground(Color.yellow);
        headerLabel.setVisible(true);
        headerLabel.setText("Kort beskrivning om femton pussel spel:");
        headerLabel.setForeground(Color.black);
        headerLabel.setFont(new Font(Font.SERIF, Font.ITALIC, 22));
        headerLabel.setBorder(BorderFactory.createDashedBorder(Color.GRAY, 25, 10));

       /* BufferedImage bufferedImage = ImageIO.read(NumberPuzzle.class.getResource("icon.png"));
        ImageIcon imageIcon = new ImageIcon();
        imageIcon.setImage(bufferedImage);*/

      /*  JLabel iconLabel = new JLabel(imageIcon);
        iconLabel.setSize(200, 300);*/

        JButton buttonBack = new JButton();
        buttonBack.setText("Stäng");


        int count = 1;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < jButtons[i].length; j++) {

                jButtons[i][j] = new JButton();
                jButtons[i][j].setBackground(Color.cyan);
                jButtons[i][j].setForeground(Color.black);
                jButtons[i][j].setText(String.valueOf((count)));
                jButtons[i][j].setVisible(true);

                if (i == 3 && j == 3) {
                    jButtons[i][j].setText(String.valueOf((" ")));
                    jButtons[i][j].setBackground(Color.blue);
                    jButtons[i][j].setVisible(false);
                }

                jPanel.add(jButtons[i][j]);
                jPanel.setVisible(true);
                count++;

            }
        }

        nyttSpelButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                shuffle(jButtons);
                shuffledButtons(jButtons, jPanel);

                CountDown countDown = new CountDown(countDownLabel, jPanel, nyttSpelButton);
                timer = new Timer(1000, countDown);
                timer.start();
                nyttSpelButton.setBackground(Color.LIGHT_GRAY);
                nyttSpelButton.setEnabled(false);
                finishButton.setBackground(Color.yellow);
                finishButton.setEnabled(true);
                SwingUtilities.updateComponentTreeUI(jPanel);

            }
        });


        jPanel1.add(nyttSpelButton, BorderLayout.LINE_START);

        closeWindowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(1);
            }
        });

        jPanel1.add(closeWindowButton, BorderLayout.AFTER_LAST_LINE);
        buttonBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jPanel.setVisible(true);
                jPanelOmSpelet.setVisible(false);
            }
        });
        aboutGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                jTextPane.setText("\n" + "Målet med det här spelet är att adjustera antal nummeret i korrekt ordning." +
                        " Det vill säga ett till femton. Nummer ett kommer att visas övan och till vänster." +
                        "Och nummer 15 borde ligga undan och till höger.Den sista button skulle vara töm. " + "\n\n" +
                        "Klicka på nytt spel knappen för att börja spela. Du har 10 minuter att spela och tiden börjar " +
                        "att räkna efter knappen 'nytt spel' trycks. Efter du har ordnat nummeret, tryck på knappen " +
                        "'klart' för bekräftelse." + "\n\n" +
                        "Lycka till !! ");

                SimpleAttributeSet sa = new SimpleAttributeSet();
                StyleConstants.setAlignment(sa, StyleConstants.ALIGN_JUSTIFIED);

                jTextPane.getStyledDocument().setParagraphAttributes(0, 1000, sa, false);
                jTextPane.setFont(new Font("Ms Sans Serif", 0, 16));
                jTextPane.setSize(new Dimension(500, 300));
                jPanel.setVisible(false);

                jPanelOmSpelet.add(headerLabel, BorderLayout.NORTH);
                // jPanelOmSpelet.add(iconLabel, BorderLayout.EAST);
                jPanelOmSpelet.add(buttonBack, BorderLayout.SOUTH);
                jPanelOmSpelet.add(jTextPane, BorderLayout.CENTER);
                jPanelOmSpelet.setVisible(true);

            }
        });

        finishButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (timer != null && checkBoard(jButtons)) {
                    JOptionPane.showMessageDialog(jPanel, "Grattis! du har vunnit,Tryck på nytt spel för att förtsätt");
                    resetGame(nyttSpelButton, finishButton, countDownLabel);

                } else {
                    int reply = JOptionPane.showConfirmDialog(jPanel, "Nummeret ställs inte ordentligt, är du säker på att avsluta pågående spel ?", "Meddelande", JOptionPane.YES_NO_OPTION);
                    if (reply == JOptionPane.YES_OPTION) {

                        if (NumberPuzzle.timer.isRunning() && NumberPuzzle.seconds > 0) {
                            resetGame(nyttSpelButton, finishButton, countDownLabel);
                        }
                    }
                }
            }

        });
        jPanel1.add(aboutGameButton, BorderLayout.AFTER_LAST_LINE);
        jPanel1.add(finishButton, BorderLayout.AFTER_LAST_LINE);
        jPanel1.add(countDownLabel, BorderLayout.LINE_END);

        jPanelMain.add(jPanel);
        jPanelMain.add(jPanelOmSpelet);
        jPanelMain.add(jPanel1);
        jFrame.add(jPanelMain);
        jFrame.pack();
        jFrame.setLocationRelativeTo(null);

    }

    public static void resetGame(JButton nyttSpelButton, JButton finishButton, JLabel countDownLabel) {
        nyttSpelButton.setEnabled(true);
        nyttSpelButton.setBackground(Color.yellow);
        finishButton.setEnabled(false);
        finishButton.setBackground(Color.GRAY);

        NumberPuzzle.seconds = 600;
        NumberPuzzle.timer.stop();

        int minutes = (NumberPuzzle.seconds % 3600) / 60;
        int seconds = NumberPuzzle.seconds % 60;

        String format = String.format("%02d:%02d", minutes, seconds);
        countDownLabel.setText("Timer : " + format);
    }

    public static boolean checkBoard(JButton[][] jButtons) {
        int buttonText = 1;
        int countTrack = 0;

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (jButtons[i][j].getText().equals(Integer.toString(buttonText))) {
                    buttonText++;
                    if (buttonText == 15)
                        return true;
                }
            }
        }
        return false;
    }

    public static void shuffledButtons(JButton[][] jButtons, JPanel jPanel) {

        for (int i = 0; i < jButtons.length; i++) {
            for (int j = 0; j < jButtons[i].length; j++) {

                jButtons[i][j].addMouseListener(new MouseAdapter() {

                    @Override
                    public void mouseClicked(MouseEvent e) {
                        Object source = e.getSource();
                        if (source instanceof JButton)
                            moves(jPanel, jButtons, (JButton) source);
                    }
                });
                jPanel.add(jButtons[i][j]);
            }
        }
        jPanel.updateUI();
    }

    public static void shuffle(JButton[][] jButtons) {
        //Collections.shuffle(Arrays.asList(jButtons));
        Random random = new Random();
        for (int i = 0; i < jButtons.length; i++) {
            for (int j = 0; j < jButtons[i].length; j++) {
                int r = random.nextInt(i + 1);
                int c = random.nextInt(j + 1);

                JButton temp = jButtons[i][j];
                jButtons[i][j] = jButtons[r][c];
                jButtons[r][c] = temp;

            }
        }
    }

    public static void moves(JPanel jPanel, JButton[][] jButtons, JButton jButton) {

        for (int i = 0; i < jButtons.length; i++) {
            for (int j = 0; j < jButtons[i].length; j++) {

                if (jButtons[i][j].getBackground() == Color.BLUE) {

                    System.out.println("Blue button... " + jButtons[i][j].getX() + "  " + jButtons[i][j].getY() + "\n");
                    System.out.println("Button clicked... " + jButton.getX() + "  " + jButton.getY());
                    if (((jButtons[i][j].getX() == jButton.getX()) && ((jButton.getY() - jButtons[i][j].getY() <= 147) && (jButton.getY() - jButtons[i][j].getY()) >= -147))
                            || ((jButtons[i][j].getY() == jButton.getY()) && ((jButton.getX() - jButtons[i][j].getX()) <= 147) && (jButton.getX() - jButtons[i][j].getX()) >= -147)) {
                        jButtons[i][j].setVisible(true);
                        jButtons[i][j].setText(jButton.getText());
                        jButtons[i][j].setBackground(Color.CYAN);

                        jButton.setBackground(Color.blue);
                        jButton.setText(" ");
                        jButton.setVisible(false);
                    }

                    //SwingUtilities.updateComponentTreeUI(jPanel);
                }
            }
        }
    }
}

class CountDown implements ActionListener {
    private JLabel countDownLabel;
    private JPanel jPanel;
    private JButton jButton;

    public CountDown(JLabel countDownLabel, JPanel jPanel, JButton jButton) {
        this.countDownLabel = countDownLabel;
        this.jPanel = jPanel;
        this.jButton = jButton;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (NumberPuzzle.seconds == 000)
            showMessage();
        else if (NumberPuzzle.seconds > 0) {
            NumberPuzzle.seconds--;

            int minutes = (NumberPuzzle.seconds % 3600) / 60;
            int seconds = NumberPuzzle.seconds % 60;

            String format = String.format("%02d:%02d", minutes, seconds);
            this.countDownLabel.setText("Timer: " + format);
        }
        SwingUtilities.updateComponentTreeUI(jPanel);
    }

    public void resetTimer() {
        NumberPuzzle.seconds = 600;
        NumberPuzzle.timer.stop();

        int minutes = (NumberPuzzle.seconds % 3600) / 60;
        int seconds = NumberPuzzle.seconds % 60;

        String format = String.format("%02d:%02d", minutes, seconds);
        this.countDownLabel.setText("Timer : " + format);
    }

    public void showMessage() {
        JOptionPane.showMessageDialog(jPanel, "Tiden är slut, börja nytt spel");
        jButton.setBackground(Color.YELLOW);
        jButton.setEnabled(true);
        resetTimer();
    }
}