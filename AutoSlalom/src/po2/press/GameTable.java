package po2.press;
import p02.game.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GameTable extends JTable implements TickEvent {
    private BufferedImage image;

    GameTableModel gameTableModel;
    public GameTable(Board board) {

        File imageFile = new File("src/car.png");
        try {
            image = ImageIO.read(imageFile);
        } catch (IOException e) {
            System.err.println("Error loading image");
            e.printStackTrace();
        }
        gameTableModel = new GameTableModel(board);

        this.setOpaque(false);
        this.setBackground(new Color(0, 0, 0, 0));
        this.setShowGrid(false);
        this.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            {
                setOpaque(false);
            }
        });

    }

    @Override
    public void tickEvent() {
        this.repaint();

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawGame(g);
        drawCar(g);

    }
    public void drawCar(Graphics g) {
        if ((gameTableModel.getValueAt(0,0) & 4) != 0)
            carleft(g);

        if ((gameTableModel.getValueAt(0,0) & 2) != 0)
            carmid(g);

        if ((gameTableModel.getValueAt(0,0) & 1) != 0)
            carright(g);

    }
    public void drawGame(Graphics g) {

        for (int i = 1; i < gameTableModel.getColumnCount(); i++) {
            if ((gameTableModel.getValueAt(0,i) & 4) != 0)
                drawleft(g, i);

            if ((gameTableModel.getValueAt(0,i) & 2) != 0)
                drawmid(g, i);
            if ((gameTableModel.getValueAt(0,i) & 1) != 0)
                drawright(g, i);
            if(gameTableModel.getValueAt(1,i)==1)
                panele(g, i);
        }

    }
    public void drawmid(Graphics g, int i) {
        g.setColor(Color.black);
        int y1 = 0, y2 = 0,h=0;
        switch (i) {
            case 1 -> { y1 = 500; y2 = 390;h=23; }
            case 2 -> { y1 = 330; y2 = 255; h=16;}
            case 3 -> { y1 = 230; y2 = 180; h=13;}
            case 4 -> { y1 = 158; y2 = 120; h=10;}
            case 5 -> { y1 = 90; y2 = 65; h=8;}
            case 6 -> { y1 = 30; y2 = 15; h=5;}
        }

        int[] tabx = {(int) ((-3f / 4) * y1) + 780, (int) ((-3f / 4) * (y1 )) + 780, (int) ((-3f / 8) * (y1 ) + 800), (int) ((-3f / 8) * y1) + 800};
        int[] taby = {y1, y1 - h, y1 - h, y1};
        g.fillPolygon(tabx, taby, 4);
        tabx = new int[]{(int) ((-3f / 4) * y2) + 780, (int) ((-3f / 4) * (y2 )) + 780, (int) ((-3f / 8) * (y2) + 800), (int) ((-3f / 8) * y2) + 800};
        taby = new int[]{y2, y2 + h, y2 + h, y2};
        g.fillPolygon(tabx, taby, 4);
    }
    public void drawleft(Graphics g, int i) {
        g.setColor(Color.black);
        int y1 = 0, y2 = 0, h=0;
        switch (i) {
            case 1 -> { y1 = 500; y2 = 390;h=23; }
            case 2 -> { y1 = 330; y2 = 255; h=16;}
            case 3 -> { y1 = 230; y2 = 180; h=13;}
            case 4 -> { y1 = 158; y2 = 120; h=10;}
            case 5 -> { y1 = 90; y2 = 65; h=8;}
            case 6 -> { y1 = 30; y2 = 15; h=5;}
        }
        int[] tabx = {720 - y1, 720 - y1, (int) ((-3f / 4) * y1) + 740, (int) ((-3f / 4) * y1) + 740};
        int[] taby = {y1, y1 - h, y1 - h, y1};
        g.fillPolygon(tabx, taby, 4);
        tabx = new int[]{720 - y2, 720 - y2, (int) ((-3f / 4) * y2) + 740, (int) ((-3f / 4) * y2) + 740};
        taby = new int[]{y2, y2 + h, y2 + h, y2};
        g.fillPolygon(tabx, taby, 4);

    }
    public void drawright(Graphics g, int i) {
        g.setColor(Color.black);
        int y1 = 0, y2 = 0,h=0;
        switch (i) {
            case 1 -> { y1 = 500; y2 = 390;h=23; }
            case 2 -> { y1 = 330; y2 = 255; h=16;}
            case 3 -> { y1 = 230; y2 = 180; h=13;}
            case 4 -> { y1 = 158; y2 = 120; h=10;}
            case 5 -> { y1 = 90; y2 = 65; h=8;}
            case 6 -> { y1 = 30; y2 = 15; h=5;}
        }
        int[] tabx = {(int) ((-3f / 8) * y1) + 840, (int) ((-3f / 8) * (y1 - 4)) + 840, (int) ((-3f / 16) * y1) + 880, (int) ((-3f / 16) * y1) + 880};
        int[] taby = {y1, y1 - h, y1 - h, y1};
        g.fillPolygon(tabx, taby, 4);
        tabx = new int[]{(int) ((-3f / 8) * y2) + 840, (int) ((-3f / 8) * y2) + 840, (int) ((-3f / 16) * y2) + 880, (int) ((-3f / 16) * y2) + 880};
        taby = new int[]{y2, y2 + h, y2 + h, y2};
        g.fillPolygon(tabx, taby, 4);

    }
    public void panele(Graphics g, int i) {
        g.setColor(Color.black);
        int y1 = 0, y2 = 0;
        switch (i % 6) {
            case 1 -> { y1 = 500; y2 = 350; }
            case 2 -> { y1 = 350; y2 = 240; }
            case 3 -> { y1 = 240; y2 = 160; }
            case 4 -> { y1 = 160; y2 = 100; }
            case 5 -> { y1 = 100; y2 = 45; }
            case 0 -> { y1 = 45; y2 = 0; }
        }
        int tabx[] = new int[]{670-y1,670-y2,690-y2,690-y1};
        int taby[] = new int[]{y1,y2,y2,y1};
        g.fillPolygon(tabx,taby,4);
        tabx = new int[]{(int)((-3f/16)*y1)+910,(int)((-3f/16)*y2)+910,(int)((-3f/16)*y2)+930,(int)((-3f/16)*y1)+930 };
        g.fillPolygon(tabx,taby,4);

    }
    public void carleft(Graphics g) {

        g.drawImage(image, 120, 500, this);
    }
    public void carmid(Graphics g) {

        g.drawImage(image, 350, 500, this);
    }
    public void carright(Graphics g) {

        g.drawImage(image, 600, 500, this);
    }
}

