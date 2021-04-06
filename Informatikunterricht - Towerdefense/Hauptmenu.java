import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Hauptmenu here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Hauptmenu extends World
{

    /**
     * Constructor for objects of class Hauptmenu.
     * 
     */
    public Hauptmenu()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(400, 400, 1); 
        /*
        Knoten knoten = new Knoten("Turret1");
        addObject(knoten,353, 300);
        knoten.setImage("mauer.png");
        Turm turm = new Turm();
        addObject(turm,353, 300 );
        turm.setImage("turm3level4.png");

         */
        Knopf logo = new Knopf(0);
        addObject(logo, 200, 50);
        logo.setImage("towerdefense.png");
        Knopf spielen = new Knopf(3);
        addObject(spielen, 140, 190);
        spielen.setImage("spielen.PNG");
        //Knopf steuerung = new Knopf(5);
        //addObject(steuerung, 177, 250);  
        Knopf beenden = new Knopf(4);
        addObject(beenden, 140, 290); 
        beenden.setImage("beenden.PNG");
        prepare();
    }

    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        Knoten knoten = new Knoten("Turret1");
        addObject(knoten,353, 300);
        knoten.setImage("mauer.png");
        Turm turm = new Turm();
        addObject(turm,353, 300 );
        turm.setImage("turm3level4.png");

        Knoten knoten2 = new Knoten("Turret2");
        addObject(knoten2,269, 140);
        knoten2.setImage("mauer.png");
        Turm turm2 = new Turm();
        addObject(turm2,269, 140 );
        turm2.setImage("turm1level3.png");

        Knoten knoten3 = new Knoten("Turret3");
        addObject(knoten3,65, 360);
        knoten3.setImage("mauer.png");
        Turm turm3 = new Turm();
        addObject(turm3,65, 360 );
        turm3.setImage("turm2level1.png");
    }
}
