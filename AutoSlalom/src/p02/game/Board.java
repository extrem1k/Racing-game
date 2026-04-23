package p02.game;

import po2.press.*;


import javax.swing.*;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.util.Arrays;

public class Board extends JPanel implements KeyListener, StartEvent, PlusOneEvent, ResetEvent, TickEvent {
    private int plansza[] = new int[7];
    int panel[] = new int[7];
    int score=0;
    int coile=0;
    final int odstepodpaneli =3;
    public Board(SevenSegmentDigit hundreds, SevenSegmentDigit tens, SevenSegmentDigit units){
        this.addKeyListener(this);
        this.setFocusable(true);
        plansza[0] = 2;

        units.addPlusoneeventlister(tens);
        units.addStartEventListener(tens);
        units.addResetEventListener(tens);

        tens.addPlusoneeventlister(hundreds);
        tens.addResetEventListener(hundreds);
        tens.addStartEventListener(hundreds);

        hundreds.addPlusoneeventlister(this);
        hundreds.addStartEventListener(this);


        listenerList.add(PlusOneEvent.class,  units);

        listenerList.add(ResetEvent.class, units);
        listenerList.add(ResetEvent.class, GameThread.getInstance());

        listenerList.add(StartEvent.class, units);
        listenerList.add(StartEvent.class, GameThread.getInstance());

    }



    @Override
    public void tickEvent() {
        if ((plansza[0] & plansza[1]) != 0)
            resetEvent();
        else {
            if(plansza[1]!=0)
            {
                for(PlusOneEvent plusone: listenerList.getListeners(PlusOneEvent.class))
                    plusone.plusOneEvent();
                score++;
            }
            for (int i = 1; i < plansza.length - 1; i++) {
                plansza[i] = plansza[i + 1];
                panel[i] = panel[i + 1];
            }
            // co jaki odstep rysowac panele
            if(coile%odstepodpaneli==0)
                panel[6]=1;
            else panel[6]=0;

            //logika generowania przeszkód lol
            if(score == 0 ){
                if(coile % 4 == 0)
                    plansza[6] = (int) (Math.random() * 6) + 1;
                else plansza[6] = 0;
            }  else
            if (score < 10){
                if(coile%3==1)
                    plansza[6] = (int) (Math.random() * 6) + 1;
                else plansza[6] = 0;
            } else
            if (score < 100)
            {
                if(coile%2==0)
                    plansza[6] = (int) (Math.random() * 6) + 1;
                else plansza[6] = 0;
            }
            else
            if (score < 999 ) {
                int x = (int) (Math.random() * 6) + 1;
                while ((plansza[6] & x) != 0)
                    x = (int) (Math.random() * 6) + 1;
                plansza[6] = x;
            }
            coile++;
        }
    }
    @Override
    public void plusOneEvent() {
        resetEvent();   //odbiornik na sygnal od setek
    }
    @Override
    public void resetEvent() {
        for(ResetEvent listener : listenerList.getListeners(ResetEvent.class))
            listener.resetEvent();
        score = 0;
        coile = 0;
        Arrays.fill(panel, 0);
        Arrays.fill(plansza, 0);
        plansza[0] = 2;


    }
    @Override
    public void startEvent() {
        if(GameThread.getInstance().isHolded()) {
            for (StartEvent listener : listenerList.getListeners(StartEvent.class))
                listener.startEvent();
        }
    }
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_A && plansza[0] <= 2&&!GameThread.getInstance().isHolded())
            plansza[0] <<= 1;
        else if (e.getKeyCode() == KeyEvent.VK_D && plansza[0] >= 2&&!GameThread.getInstance().isHolded())
            plansza[0] >>= 1;

        else if (e.getKeyCode() == KeyEvent.VK_S&& GameThread.getInstance().isHolded())
            startEvent();


    }
    @Override
    public void keyReleased(KeyEvent e) {}
    public int[] getPlansza() {
        return plansza;
    }
}
