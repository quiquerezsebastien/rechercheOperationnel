/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hegarc.ig.ro.graphe.business;

/**
 *
 * @author sebastie.quiquere
 */
public class DijkstraNodeComparator implements Comparable{

    public int compareTo(Noeud one, Noeud two) {
//        Noeud one = (Noeud) arg0;
//        Noeud two = (Noeud) arg1;
        
        if(one.getDijkstraPoids() < two.getDijkstraPoids()){
            return -1;
        }else if (one.getDijkstraPoids() > two.getDijkstraPoids()){
            return 1;
        }else{
            return 0;
        }
    }

    @Override
    public int compareTo(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
