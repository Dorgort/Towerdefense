import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public  class MachtLaut extends Actor implements GegnerLautVerhalten {
    private int machmallangsamer = 0;
    public void lautMachen() {
        if (machmallangsamer == 55){
            Greenfoot.playSound("laufen.wav");
            machmallangsamer = 0;
        }
        machmallangsamer++;
    }
}

