package p02.game;

import java.util.ArrayList;

public class GameThread extends Thread implements TickEvent, StartEvent, ResetEvent {
    private  volatile boolean holded;
    private int interval;
    private static GameThread instance = null;
    private ArrayList<TickEvent> tickets = new ArrayList<>();

    private GameThread() {
        holded = true;
        this.interval = 900;  // Początkowy interwał 1 sekunda
        this.start();
    }

    public static GameThread getInstance() {
        if (instance == null) {
            synchronized (GameThread.class) {
                if (instance == null) {
                    instance = new GameThread();
                }
            }
        }
        return instance;
    }

    public void addToListTickets(TickEvent ticket) {
        tickets.add(ticket);
    }

    @Override
    public synchronized void resetEvent() {
        holded = true;

    }

    @Override
    public synchronized void startEvent() {
        holded= false;
        interval = 900;
        this.notify();

    }

    @Override
    public void run() {
        try {
            while (true) {
                synchronized (this) {
                    if (holded) {
                        this.wait();
                    }

                    tickEvent();
                }
                Thread.sleep(interval);
                interval = Math.max(300, interval - 10); // Zmniejszanie interwału
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void tickEvent() {
        for (TickEvent ticket : tickets) {
            ticket.tickEvent();
        }
    }
    public synchronized boolean isHolded() {
        return holded;
    }
}
