import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class AugenGegner extends Gegner
{
    GifImage gifImage = new GifImage("augengegnerR.gif");
    GifImage gifImage2 = new GifImage("augengegnerL.gif");

    public AugenGegner(){
        gegnerlautverhalten = new MachtFlugLaut();
    }

    public void act() 
    {      
        MyWorld myWorld = (MyWorld)getWorld();
        if (myWorld.sound == true){
            tuLautMachen();
        }
        if(firstAct){
            setSchaden(-10);
        }

        setImage(gifImage.getCurrentImage());
        if (gespiegelt == true) setImage(gifImage2.getCurrentImage());
        fliegen();
    }    
}
