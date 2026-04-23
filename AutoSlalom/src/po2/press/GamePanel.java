package po2.press;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GamePanel extends JPanel {

    private BufferedImage sceneryImage;
    private boolean sceneryDrawn = false;

    public GamePanel() {
        setPreferredSize(new Dimension(1000, 800)); // Ensure the panel has a preferred size
        sceneryImage = new BufferedImage(1000, 800, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = sceneryImage.createGraphics();
        drawSceneria(g2d);
        g2d.dispose();
    }

    public void drawSceneria(Graphics g) {
        int lewaliniax[] = new int[]{0, 570, 590, 0};
        int lewaliniay[] = new int[]{500, 0, 0, 520};
        g.setColor(Color.RED);
        g.fillPolygon(lewaliniax, lewaliniay, 4);

        int prawaliniax[] = new int[]{-10, 640, 660, -10};
        int prawaliniay[] = new int[]{650, 0, 0, 670};
        g.fillPolygon(prawaliniax, prawaliniay, 4);
        int prawaliniaxx[] = new int[]{790, 940, 960, 810};
        int prawaliniayx[] = new int[]{800, 0, 0, 800};
        g.fillPolygon(prawaliniaxx, prawaliniayx, 4);

        g.setColor(Color.GREEN);
        int trawalewax[] = new int[]{0, 565, 0};
        int trawaleway[] = new int[]{495, 0, 0};
        g.fillPolygon(trawalewax,trawaleway,3);
        int trawaprawax[] = new int[]{815,965 ,1000, 1000};
        int trawapraway[] = new int[]{800, 0,0, 800};
        g.fillPolygon(trawaprawax,trawapraway,4);
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        drawSceneria(g);
        if (!sceneryDrawn)
        {
            drawSceneria(g);
            sceneryDrawn = true; // Ustawienie flagi po narysowaniu scenerii
        }
    }
}
