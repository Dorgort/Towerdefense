import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)


public class FeuerGegner extends Gegner
{
    GifImage gifImage = new GifImage("fireGuyR.gif");
    GifImage gifImage2 = new GifImage("fireGuyL.gif");

    public FeuerGegner(){
        gegnerlautverhalten = new MachtLaut();
    }

    public void act() 
    {
        if (firstAct == true){
            setSchaden(-50);
        }
        MyWorld myWorld = (MyWorld)getWorld();
        if (myWorld.sound == true){
            tuLautMachen();
        }
        
        neuFinden(); 
        bewegen();
        setImage(gifImage.getCurrentImage());
        if (gespiegelt == true) setImage(gifImage2.getCurrentImage());

    }


}
