import greenfoot.*; 

public class MachtFlugLaut extends Actor implements GegnerLautVerhalten {
    private int machmallangsamer = 0;
    public void lautMachen() {
        if (machmallangsamer == 60){
            Greenfoot.playSound("fliegen.wav");
            machmallangsamer = 0;
        }
        machmallangsamer++;
    }
}
