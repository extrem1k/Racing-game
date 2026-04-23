package po2.press;


import p02.game.*;
import javax.swing.*;
import java.awt.*;

public class SevenSegmentDigit extends JPanel implements PlusOneEvent, StartEvent, ResetEvent{
    public int value;
    public SevenSegmentDigit() {
        this.value = -1;
        setPreferredSize(new Dimension(60, 110));
        this.setBackground(Color.GREEN);
    }
    public void addPlusoneeventlister(PlusOneEvent lister)
    {
        listenerList.add(PlusOneEvent.class, lister);
    }
    public void addResetEventListener(ResetEvent lister)
    {
        listenerList.add(ResetEvent.class, lister);
    }
    public void addStartEventListener(StartEvent lister)
    {
        listenerList.add(StartEvent.class, lister);
    }


    @Override
    public void resetEvent() {
        value = -1;
        for(ResetEvent l: listenerList.getListeners(ResetEvent.class))
            l.resetEvent();
        repaint();

    }
    @Override
    public void plusOneEvent() {
        if (value == 9) {
            value=0;
            for(PlusOneEvent l: listenerList.getListeners(PlusOneEvent.class))
                l.plusOneEvent();

        } else
            this.value++;
        repaint();
    }
    @Override
    public void startEvent() {
        value = 0;
        for(StartEvent startEvent: listenerList.getListeners(StartEvent.class))
            startEvent.startEvent();
        repaint();

    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(value!=-1) {
            g.setColor(Color.BLACK);
            drawSegment(g, 10, 0, 40, 10, value != 1 && value != 4); // segment A
            drawSegment(g, 50, 10, 10, 40, value != 5 && value != 6); // segment B
            drawSegment(g, 50, 60, 10, 40, value != 2); // segment C
            drawSegment(g, 10, 100, 40, 10, value != 1 && value != 4 && value != 7); // segment D
            drawSegment(g, 0, 60, 10, 40, value != 1 && value != 3 && value != 4 && value != 5 && value != 7 && value != 9); // segment E
            drawSegment(g, 0, 10, 10, 40, value != 1 && value != 2 && value != 3 && value != 7); // segment F
            drawSegment(g, 10, 50, 40, 10, value != 0 && value != 1 && value != 7); // segment G
        }
    }

    private void drawSegment(Graphics g, int x, int y, int width, int height, boolean isOn) {
        if (isOn) {
            g.fillRect(x, y, width, height);
        }
    }
}