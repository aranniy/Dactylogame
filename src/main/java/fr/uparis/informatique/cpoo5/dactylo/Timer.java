package fr.uparis.informatique.cpoo5.dactylo;

public class Timer {

    private int seconds;

    public Timer(int seconds){
        this.seconds = seconds;
    }

    public int getTimeLeft(){ return seconds; }
    public boolean isOutOfTime(){ return seconds <= 0; }
    
}
