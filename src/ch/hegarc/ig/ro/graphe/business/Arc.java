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
public class Arc {
    
    private String nom;
    private Integer poids;
    private float temps;
    private Noeud dest;
    public static int ARC_PERSONNE = 0;
    public static int ARC_PAGE = 1;
    private String type;

    public Arc() {
    }
    
    public Arc(String nom, int poids) {
        this.nom = nom;
        this.poids = poids;
    }
    
    /**
     * Constructeur perméttant de créer un arc
     * @param nom Dénomination de l'arc
     * @param poids Poids de l'arc
     * @param dest Noeud de destination de l'arc
     */
    public Arc(String nom, Integer poids, Noeud dest) {
        this.nom = nom;
        this.poids = poids;
        this.dest = dest;
    }

    @Override
    public String toString() {
        StringBuilder chaine = new StringBuilder("(");  
        chaine.append(this.poids.intValue());
        chaine.append(",");
        chaine.append(this.dest.getNom());
        chaine.append(")");
        
        return chaine.toString();
    }

    public String getNom() {
        return nom;
    }

    public int getPoids() {
        return poids;
    }

    public Noeud getDest() {
        return dest;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPoids(int poids) {
        this.poids = poids;
    }

    public void setDest(Noeud dest) {
        this.dest = dest;
    }
    
}