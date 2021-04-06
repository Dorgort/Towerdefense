import java.util.ArrayList;
import java.util.LinkedList;
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class MyWorld extends World
{
    World gotoWorld;
    //LinkedList<Kante> kantenListe;
    LinkedList<Knoten> knotenListe;
    public boolean sound = true;
    public int currentBackground = 0;
    public Counter punkteCounter, lebenCounter, goldCounter;
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(500, 550, 1); 

        currentBackground = Greenfoot.getRandomNumber(7);
        changeBackground();

        knotenListe= new LinkedList<Knoten>();
        //kantenListe= new LinkedList<Kante>();

        //bg.drawLine(20, 70,200,200);

        goldCounter = new Counter("Gold: ");
        addObject(goldCounter, 250, 535);
        goldCounter.add(50);

        lebenCounter = new Counter("Leben: ");
        lebenCounter.setLeben();
        lebenCounter.add(10);
        addObject(lebenCounter, 48, 535);

        punkteCounter = new Counter("Punkte: ");
        addObject(punkteCounter, 48, 505);

        Knopf muteKnopf = new Knopf(1);
        addObject(muteKnopf, 480,530);
        Knopf menuKnopf = new Knopf(2);
        addObject(menuKnopf, 440,530);
        Knopf bossKnopf = new Knopf(6);
        addObject(bossKnopf, 400,530);
        prepare();
        pfadUpdate();
        SaveFile save = new SaveFile(true);
        addObject(save, 160,530);
        save.setImage("save.png");
        SaveFile load = new SaveFile(false);
        addObject(load, 120,530);
        load.setImage("load.png");
        
    }

    public void changeBackground() {
        setBackground(new GreenfootImage("background"+currentBackground+".png"));
        getBackground().setColor(Color.BLACK);
    }

    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */

    private void prepare()
    {
        int zaehler = 0;
        int breite = 10;
        int hoehe = 10;

        for (int i = 0; i < hoehe; i++) {
            for (int j = 0; j < breite; j++) {

                Knoten knoten = new Knoten("Knoten"+zaehler);
                addObject(knoten,20+(j*50),20+(i*50));
                zaehler++;
                knotenListe.add(knoten);

            }
        }

        //Knoten verbinden mit rechts davon
        for (int i = 0; i < hoehe; i++) {
            for (int j = 0; j < breite; j++) {
                Knoten knoten = knotenListe.get(j+breite*i);
                //System.out.println(knoten.name);
                //System.out.println(j+breite*i +"   "+ (breite*hoehe-1));
                if (j+breite*i < (breite*hoehe-1)){
                    //System.out.println("  "+(breite - 1) +"  "+ j);
                    if ((breite - 1) != j){
                        //System.out.println(((j+breite*i)));
                        kanteHinzufuegen( knoten,knotenListe.get((j+breite*i)+1),1 );
                    }
                }
            }
        }
        //Knoten verbinden mit darunter
        for (int i = 0; i < hoehe; i++) {
            for (int j = 0; j < breite; j++) {
                Knoten knoten = knotenListe.get(j+breite*i);
                //System.out.println((j+breite*i +"   "+ (breite*hoehe-hoehe)));
                if (j+breite*i < (breite*hoehe-breite)){
                    //if ((hoehe - 1) != i){
                    //System.out.println(((j+breite*i)));
                    kanteHinzufuegen( knoten,knotenListe.get((j+breite*i)+breite),1 );

                    // }
                }
            }
        }
        
    }

    public Knoten getStartKnoten(){
        Knoten startKnoten = null;
        for (int i = 0; i < knotenListe.size(); i++) {
            if(knotenListe.get(i).name.equals("Knoten0")){
                startKnoten = knotenListe.get(i);
            }
        }
        return startKnoten;
    }

    public Knoten getZielKnoten(){
        Knoten zielKnoten = null;
        for (int i = 0; i < knotenListe.size(); i++) {
            if(knotenListe.get(i).name.equals("Knoten99")){
                zielKnoten = knotenListe.get(i);
            }
        }
        return zielKnoten;
    }

    public Knoten Ihatehtis(Knoten node) {
        Knoten testStart = node;
        Knoten testZiel = null;
        for (int i = 0; i < knotenListe.size(); i++) {
            /*if(knotenListe.get(i).name.equals("Knoten45")){
            testStart = knotenListe.get(i);
            }*/
            if(knotenListe.get(i).name.equals("Knoten99")){
                testZiel = knotenListe.get(i);
            }
        }

        if(testStart == testZiel){
            return testZiel;
        }

        Knoten goal = findPath(testStart, testZiel);        
        while(!goal.startpunkt){
            Knoten newGoal = goal.vorgaenger;
            goal = newGoal;
            //System.out.println("     path: " + goal.name + " " + goal.startpunkt);
            if(goal.vorgaenger != null){    
                if(goal.vorgaenger.startpunkt){
                    break;
                }
            }
            else{
                goal = testZiel;
                break;
            }
        }
        //System.out.println("next step: " + goal.name);
        return goal;
    }

    //Kanten zeichnen
    public void kanteHinzufuegen( Knoten A , Knoten B , int gewicht ){
        Kante kt  = new Kante( A,B,gewicht );
        //kantenListe.add(kt);

        getBackground().drawLine(A.getX(),A.getY(), B.getX(),B.getY());
    }

    public  void findNeighbours(Knoten node, LinkedList<Knoten> neighbourList){

        //Kant.kantenListe
        for (int i = 0; i < node.kantenListe.size(); i++) {

            //node is KnotenA
            if(node.kantenListe.get(i).knotenA == node && !node.kantenListe.get(i).knotenB.besucht && !node.kantenListe.get(i).knotenB.geblockt){
                neighbourList.add(node.kantenListe.get(i).knotenB);
                node.kantenListe.get(i).knotenB.vorgaenger = node;
            }
            //node is KnotenB
            if(node.kantenListe.get(i).knotenB == node && !node.kantenListe.get(i).knotenA.besucht && !node.kantenListe.get(i).knotenA.geblockt){
                neighbourList.add(node.kantenListe.get(i).knotenA);
                node.kantenListe.get(i).knotenA.vorgaenger = node;
            }
        }   
    }

    public void printList(LinkedList<Knoten> k){

        for (int i = 0; i < k.size(); i++) {
            System.out.print( k.get(i).name + ", ");
        }
        System.out.print("\n");
    }

    public Knoten findPath(Knoten currentNode, Knoten targetNode){

        LinkedList<Knoten> bfs = new LinkedList<Knoten>();//breadth first search
        LinkedList<Knoten> neighbourList= new LinkedList<Knoten>();

        for (int i = 0; i < knotenListe.size(); i++) {
            knotenListe.get(i).setBesucht(false);
            knotenListe.get(i).vorgaenger = null;
            knotenListe.get(i).startpunkt = false;
        }

        currentNode.startpunkt = true;
        currentNode.besucht = true;

        bfs.add(currentNode);
        neighbourList.add(currentNode);
        while(bfs.size() > 0){
            //System.out.println("1. ll " + bfs);

            for (int i = 0; i < bfs.size(); i++) {
                findNeighbours(bfs.get(i), neighbourList);
            }
            //printList(bfs);

            for (int i = 0; i < bfs.size(); i++) {
                Knoten node = bfs.get(i);
                //System.out.println("   " + node.name + "  " + targetNode.name);
                if(node == targetNode)
                {
                    bfs = new LinkedList<Knoten>();
                    neighbourList = new LinkedList<Knoten>();

                    return node;
                }

            }
            //System.out.println("2. nl " + neighbourList);
            bfs = new LinkedList<Knoten>();
            for (int i = 0; i < neighbourList.size(); i++) {
                Knoten n = neighbourList.poll();
                n.setBesucht(true);
                bfs.add(n);

            }
            //Greenfoot.delay(200);
        }
        Knoten nextNode = null;
        return targetNode;
    }

    public void pfadUpdate() {
        clearPfad();
        Knoten ziel_node = null;

        for (int i = 0; i < knotenListe.size(); i++) {
            if(knotenListe.get(i).name.equals("Knoten99")){
                ziel_node = knotenListe.get(i);
            }
        }

        ziel_node.pfad = 0;

        LinkedList<Knoten> update_knotenListe = new LinkedList<Knoten>();
        update_knotenListe.add(ziel_node);

        pfadUpdateHelper(update_knotenListe);

    }
    //yep
    public void pfadUpdateHelper(LinkedList<Knoten> kl){
        LinkedList<Knoten> update_knotenListe = new LinkedList<Knoten>();
        for(int i = 0; i< kl.size(); i++){
            Knoten k = kl.get(i);
            if(k.geblockt == false){
                //nachbarn von lil suchen
                for(int j = 0; j< k.kantenListe.size(); j++){
                    Knoten k_neighbour = k.kantenListe.get(j).pls_send_help(k);
                    //System.out.println(j + "  ---  " + k_neighbour.name);

                    if(k_neighbour.geblockt == false && k_neighbour.pfad == -1){
                        k_neighbour.pfad = k.pfad+1;
                        update_knotenListe.add(k_neighbour);
                    }
                }
            }
        }
        if(update_knotenListe.size() != 0){
            //for(int i = 0; i< update_knotenListe.size(); i++){
            //System.out.println(update_knotenListe.get(i).name);
            //}
            pfadUpdateHelper(update_knotenListe);
        }
    }

    public void clearPfad(){
        for(int i = 0; i< knotenListe.size(); i++){
            Knoten k = knotenListe.get(i);
            k.pfad = -1;
        }
    }

}
