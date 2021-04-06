import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class BossGegner extends Gegner
{
    GifImage gifImage = new GifImage("angel-idle.gif");
    private static BossGegner singleton;

    private BossGegner(){
        gegnerlautverhalten = new MachtFlugLaut();
    }

    public static BossGegner getInstanz() {
        if(singleton==null) {
            singleton=new BossGegner();
            //tot = false;
            //firstAct = true; how dis work??
        }
        return singleton;
    }

    public void act() 
    {

        if (firstAct == true){
            setSchaden(-500);
        }
        MyWorld myWorld = (MyWorld)getWorld();
        if (myWorld.sound == true){
            tuLautMachen();
        }
        neuFinden(); 
        bewegen();
        setImage(gifImage.getCurrentImage());
    }    
}
