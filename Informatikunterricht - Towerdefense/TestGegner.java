import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)


public class TestGegner extends Gegner
{
    
    

    public void act() 
    {
        if (firstAct == true){
        setSchaden(-1000);
        }
        neuFinden();
        this.turnTowards(nextNode.getX(), nextNode.getY());
        move(2);
        
        

    }  




}
