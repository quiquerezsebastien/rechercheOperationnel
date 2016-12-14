/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hegarc.ig.ro.graphe.Application;

import ch.hegarc.ig.ro.graphe.business.*;

/**
 *
 * @author sebastie.quiquere
 */
public class Application {

    /**
     * @param args the command line arguments
     */
    public static void run(){
        
        Graphe graphe = new Graphe("Test");
        
        /*Noeud x1 = new Noeud("x1");
        Noeud x2 = new Noeud("x2");
        Noeud x3 = new Noeud("x3");
        
        Arc u1 = new Arc("U1", 9);
        Arc u2 = new Arc("U2", 11);
        Arc u3 = new Arc("U3", 2);
        Arc u4 = new Arc("U4", 1);
        Arc u5 = new Arc("U5", 22);
        Arc u6 = new Arc("U6", 8);*/
        
        /*graphe.ajouterNoeud("x1");
        graphe.ajouterNoeud("x2");
        graphe.ajouterNoeud("x3");
        
        graphe.ajouterArc("x1","x2",9,"U1");
        graphe.ajouterArc("x1","x2",8,"U6");
        graphe.ajouterArc("x2","x1",11,"U2");
        graphe.ajouterArc("x2","x3",1,"U4");
        graphe.ajouterArc("x1","x3",2,"U3");
        graphe.ajouterArc("x3","x3",22,"U5");*/
        
        graphe.ajouterNoeud("x1");
        graphe.ajouterNoeud("x2");
        graphe.ajouterNoeud("x3");
        graphe.ajouterNoeud("x4");
        graphe.ajouterNoeud("x5");
        
        graphe.ajouterArc("x1","x5",1,"U1");
        graphe.ajouterArc("x5","x1",1,"U2");
        graphe.ajouterArc("x1","x4",8,"U3");
        graphe.ajouterArc("x2","x1",11,"U4");
        graphe.ajouterArc("x2","x5",3,"U5");
        graphe.ajouterArc("x5","x2",5,"U6");
        graphe.ajouterArc("x2","x3",5,"U7");
        graphe.ajouterArc("x3","x2",2,"U8");
        graphe.ajouterArc("x5","x3",1,"U9");
        graphe.ajouterArc("x3","x5",1,"U10");
        graphe.ajouterArc("x3","x4",1,"U11");
        graphe.ajouterArc("x5","x4",1,"U12");
        
        
        //graphe.supprimerNoeud(x3);
        
        //System.out.println(graphe.toString());
        graphe.dijkstra(graphe.rechercheNoeud("x1"));
        System.out.println(graphe.rechercheNoeud("x1").getVpcc());
        //System.out.println(graphe.plusCourtChemin("x1", "x4", new Metrique()));
        
    }
    
    public static void main(String[] args) {
        new Application().run();
    }
    
}
