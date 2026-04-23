
import p02.game.Board;
import p02.game.GameThread;
import po2.press.GamePanel;
import po2.press.GameTable;
import po2.press.SevenSegmentDigit;

import javax.swing.*;

import java.awt.*;


public class S30723PO2 extends JFrame{

    public S30723PO2() {

        setTitle("Ruski Autoslalom");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 800);


        // Tworzenie głównego kontenera warstwowego
        JLayeredPane jlp = new JLayeredPane();


        JPanel score = new JPanel(new FlowLayout(FlowLayout.LEFT));
        SevenSegmentDigit hundreds = new SevenSegmentDigit();
        SevenSegmentDigit tens = new SevenSegmentDigit();
        SevenSegmentDigit units = new SevenSegmentDigit();
        score.add(hundreds);
        score.add(tens);
        score.add(units);
        score.setOpaque(false); //przezroczystosc panelu

        Board board = new Board(hundreds, tens, units);
        GameTable gameTable = new GameTable(board);
        GamePanel gamePanel = new GamePanel();

       // board.setBounds(0, 0, 1000, 800);
        score.setBounds(0, 0, 400, 200);
        gameTable.setBounds(0, 0, 1000, 800);
        gamePanel.setBounds(0, 0, 1000, 800);


        // Dodanie komponentow do warst

        jlp.add(gamePanel,JLayeredPane.DEFAULT_LAYER);
        jlp.add(gameTable, JLayeredPane.PALETTE_LAYER);
        jlp.add(score, JLayeredPane.MODAL_LAYER);
         jlp.add(board, JLayeredPane.POPUP_LAYER);


        // Dodanie warstwowego panelu do głównego kontenera ramki w środku

        add(jlp, BorderLayout.CENTER);
        GameThread.getInstance().addToListTickets(gameTable);
        GameThread.getInstance().addToListTickets(board);

        setVisible(true);

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(S30723PO2::new);
    }
}








