import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Kante extends Actor
{
    int gewicht;
    Knoten knotenA = null;
    Knoten knotenB = null;

    public void act() 
    {

    }  

    public Kante(Knoten A, Knoten B, int gewicht){
        knotenA = A;
        knotenB = B;
        this.gewicht = gewicht;
        knotenA.kanteHinzufuegen(this);
        knotenB.kanteHinzufuegen(this);

    }

    public String getGewicht() {
        return " " + gewicht;
    }

    public Knoten getKnotenA() {
        return knotenA;
    }

    public Knoten getKnotenB() {
        return knotenB ;
    }

    public Knoten pls_send_help(Knoten uff){
        if(uff == knotenA){
            return knotenB;
        }else{
            return knotenA;
        }
    }
}
