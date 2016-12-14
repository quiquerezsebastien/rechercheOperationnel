/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hegarc.ig.ro.graphe.business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sebastie.quiquere
 */
public class Noeud implements Cloneable {

    private String nom;
    private HashMap<String, Arc> arcsSort;
    private boolean marquage;
    private int niveau;
    private float temps;
    public static int NOEUD_PERSONNE = 0;
    public static int NOEUD_PAGE = 1;
    private int type;
    private int dijkstraPoids;
    private Noeud dijkstraPred;
    private List<Noeud> vpcc;
    private int degreEntrant;
    private int degreSortant;

    public Noeud() {
    }

    public Noeud(String nom) {
        this.nom = nom;
        this.arcsSort = new HashMap<String, Arc>();
        this.marquage = false;
        this.vpcc = new ArrayList<>();
    }

    public Noeud(String nom, HashMap<String, Arc> arcs) {
        this.nom = nom;
        this.arcsSort = arcs;
        this.marquage = false;
    }

    @Override
    public String toString() {
        StringBuilder chaine = new StringBuilder();
//        chaine.append("{");
//        for (Map.Entry<String, Arc> entry : arcsSort.entrySet()) {
//            chaine.append(entry.getKey());
//            chaine.append(",");
//        }
//        chaine.append("} = {");
//        for (Map.Entry<String, Arc> entry : arcsSort.entrySet()) {
//            chaine.append(entry.getValue().toString());
//            chaine.append(",");
//        }
//        chaine.deleteCharAt(chaine.length() - 1);
//        chaine.append("}");
        chaine.append(nom);
        return chaine.toString();
    }
    

    /**
     * Procédure perméttant de d'ajouter un arc sortant au noeud
     *
     * @param nom Dénomination de l'arc sortant
     * @param poids Poids de l'arc
     * @param dest Noeud de destination de l'arc
     */
    public void ajouterArcSortant(String nom, Integer poids, Noeud dest) {
        arcsSort.put(nom, new Arc(nom, poids, dest));
    }

    public String getNom() {
        return nom;
    }

    public HashMap<String, Arc> getArcsSort() {
        return arcsSort;
    }

    public HashMap<String, Arc> getArcsSort(int typeArc) {
        return arcsSort;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setArcsSort(HashMap<String, Arc> arcsSort) {
        this.arcsSort = arcsSort;
    }

    public boolean isMarquage() {
        return marquage;
    }

    public void setMarquage(boolean marquage) {
        this.marquage = marquage;
    }

    public void marque() {
        this.marquage = true;
    }

    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }

    public int getNiveau() {
        return niveau;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getDijkstraPoids() {
        return dijkstraPoids;
    }

    public void setDijkstraPoids(int dijkstraPoids) {
        this.dijkstraPoids = dijkstraPoids;
    }

    public Noeud getDijkstraPred() {
        return dijkstraPred;
    }

    public void setDijkstraPred(Noeud dijkstraPred) {
        this.dijkstraPred = dijkstraPred;
    }

    public List<Noeud> getVpcc() {
        return vpcc;
    }

    public void setVpcc(List<Noeud> vpcc) {
        this.vpcc = vpcc;
    }

    public int getDegreEntrant() {
        return degreEntrant;
    }

    public void setDegreEntrant(int degreEntrant) {
        this.degreEntrant = degreEntrant;
    }

    public int getDegreSortant() {
        return degreSortant;
    }

    public void setDegreSortant(int degreSortant) {
        this.degreSortant = degreSortant;
    }
    
    @Override
    protected Object clone(){
        try {
            return super.clone();
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(Noeud.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
