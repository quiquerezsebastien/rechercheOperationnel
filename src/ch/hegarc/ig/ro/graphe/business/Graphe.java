/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hegarc.ig.ro.graphe.business;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author sebastie.quiquere
 */
public class Graphe {
    
    private String nom;
    private HashMap<String, Noeud> noeuds;

    public Graphe() {
        noeuds = new HashMap<String, Noeud>();
    }

    public Graphe(String nom) {
        this.nom = nom;
        noeuds = new HashMap<String, Noeud>();
    }
    
    public void ajouterNoeud (String pNomNoeud){
        noeuds.put(pNomNoeud, new Noeud(pNomNoeud));
    }
    
    public void ajouterArc (String pSrc, String pDest, Integer pPoids, String pNomArc){
        Noeud source = rechercheNoeud(pSrc);
        Noeud desti = rechercheNoeud(pDest);
        
        source.ajouterArcSortant(pNomArc, pPoids, desti);
        
    }
    
    public Noeud rechercheNoeud (String pNom){
        return noeuds.get(pNom);
    }

    @Override
    public String toString() {
        StringBuilder chaine = new StringBuilder("Liste d'incidence : ");
        chaine.append(nom);
        chaine.append("\n");
        
        for (Map.Entry<String, Noeud> entry : noeuds.entrySet()) {
            chaine.append("W+(");
            chaine.append(entry.getKey());
            chaine.append(") = ");
            chaine.append(entry.getValue());
            chaine.append("\n");
        }
        return chaine.toString();
    }
    
    public void supprimerNoeud(String victime){
        Noeud noeud = rechercheNoeud(victime);
        if (noeud != null) {
            supprimerNoeud(noeud);
        }
    }
    
    public void supprimerNoeud(Noeud victime){
        
        Noeud tmp_node;
        Arc tmp_a;
        
        for(Iterator it = this.noeuds.values().iterator(); it.hasNext();){
            
            tmp_node = (Noeud) it.next();
            
            for (Iterator it1 = tmp_node.getArcsSort().values().iterator();  it1.hasNext();) {
                tmp_a = (Arc) it1.next();
                if (tmp_a.getDest().getNom().equals(victime.getNom())) {
                    it1.remove();
                    
                }
            }
        }
        
        noeuds.remove(victime.getNom());
        //supprimer les arcs entrants des victimes
        
        
       // supprimer le noeud
        
    }

    public String getNom() {
        return nom;
    }

    public HashMap<String, Noeud> getNoeuds() {
        return noeuds;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setNoeuds(HashMap<String, Noeud> noeuds) {
        this.noeuds = noeuds;
    }
    
    /** méthode de la classe graphe pour lui attacher la Hashmap
    * Algorithme du parcours en PROFONDEUR
    *
    */
    public List<Noeud> parcoursProfondeur(Noeud depart){
	reInitNoeud();
        int niveauMax = 0;
	/* initialisation c'est en profondeur, PILE */
	
	LinkedList<Noeud> pile = new LinkedList<Noeud>();
	
	List<Noeud> parcours = new ArrayList<Noeud>();
	
	depart.marque();	// va passer l'attribut à "true"
        depart.setNiveau(0);    // seter le niveau de départ à 0
	pile.push(depart);	// ajouter du point de départ dans la pile
	
	/* Boucle sur la mémoire */
	while(!pile.isEmpty()){
		
		Noeud noeudCourant = pile.pop();	//on depile
                if(niveauMax < noeudCourant.getNiveau()){
                    niveauMax = noeudCourant.getNiveau();   //checker si le noeud suivant est à un niveau supérieur si oui on remplace le niveau max.
                }
		parcours.add(noeudCourant);
                HashMap<String, Arc> arcs = noeudCourant.getArcsSort();
		/* Boucle sur les arcs */
		for(HashMap.Entry<String, Arc> arc : noeudCourant.getArcsSort().entrySet()){
			
			Noeud dest = arc.getValue().getDest();	// on prend toutes les destinations de l'arc
			
			if(!dest.isMarquage()){         // si la destination n'est pas marquée
				dest.marque();		// on marque la destination
                                dest.setNiveau(noeudCourant.getNiveau()+1); //augmente le niveau du noeud de destination
				pile.push(dest);	// on ajoute la destination à la pile
			}
		}
	}
	return parcours;
    }
    
    /** méthode de la classe graphe pour lui attacher la Hashmap
    * Algorithme du parcours en LARGEUR
    *
    */
    public List<Noeud> parcoursLargeur(Noeud depart, int niveauMax, int typeArc, int typeNoeud){
	reInitNoeud();
	/* initialisation c'est en profondeur, PILE */
	
	List<Noeud> file = new LinkedList<Noeud>();
	
	List<Noeud> parcours = new ArrayList<Noeud>();
	
	depart.marque();	// va passer l'attribut à "true"
        depart.setNiveau(0);
	file.add(depart);	// ajouter du point de départ dans la file --> add last
	
	/* Boucle sur la mémoire */
	while(!file.isEmpty()){
		
		Noeud noeudCourant = file.get(0);	//on defile
                parcours.add(noeudCourant);
                
                if(niveauMax < noeudCourant.getNiveau()){
                file.remove(0);
		/* Boucle sur les arcs */
		for(HashMap.Entry<String, Arc> arc : noeudCourant.getArcsSort(typeArc).entrySet()){
			
                    Noeud dest = arc.getValue().getDest();	// on prend toutes les destinations de l'arc --> remove first
			
                    if(!dest.isMarquage() && dest.getType() == typeNoeud){		// si la destination n'est pas marquée
                        dest.marque();		// on marque la destination
                        dest.setNiveau(noeudCourant.getNiveau()+1);
                        file.add(dest);		// on ajoute la destination à la file --> add last
                    }
		}
                }
	}
	return parcours;
    }
    
    public void reInitNoeud(){
        
        for(Map.Entry<String, Noeud> noeud : noeuds.entrySet()){
            noeud.getValue().setMarquage(false);
        }
        
    }
    
    public void afficheListe(List<Noeud> noeuds){
        StringBuilder chaine = new StringBuilder();
        noeuds = (ArrayList<Noeud>) noeuds;
        for(Noeud noeud : noeuds){
            chaine.append(noeud.getNom());
            chaine.append(" ");
        }
        
        System.out.println(chaine.toString());
            
    }
    
    public void dijkstra(Noeud noeudDepart){
        
	/*for(Noeud n : noeuds.values()){
            n.setDijkstraPoids(Integer.MAX_VALUE);
            n.setDijkstraPred(null);
	}*/
        
        this.reInitNoeud();
	
	List<Noeud> vpcc = new ArrayList<>();
	List<Noeud> memoire = new ArrayList<>();
	
	Noeud depart = noeudDepart;
	depart.setDijkstraPoids(0);
	memoire.add(depart);
	
	while(!memoire.isEmpty()){
            Noeud courant = getDijkstraPoidsMin(memoire);
            depart.getVpcc().add((Noeud)courant.clone());
            memoire.remove(courant);
            
            for(Arc arc : courant.getArcsSort().values()){
                Noeud destCourant  = arc.getDest();
                
                
                if(destCourant.getDijkstraPoids() == Integer.MAX_VALUE){
                    memoire.add(destCourant);
                }

                int poidsCourant = courant.getDijkstraPoids() + arc.getPoids();
                
                if(poidsCourant < destCourant.getDijkstraPoids()){
                    destCourant.setDijkstraPoids(poidsCourant);
                    destCourant.setDijkstraPred(courant);
                }
            }
	}
    }
    
    public List<Noeud> plusCourtChemin(String src, String dest, Metrique metrique){ // metrique qui permet de calculer au niveau du temps et du parcours
        
        Noeud nsrc = rechercheNoeud(src);
        Noeud ndest = rechercheNoeud(dest);
        
        if (nsrc.getVpcc(/*metrique*/) == null) {
            dijkstra(nsrc/*,metrique*/);         // null vpcc pas encore calculé
                                    // vide le cpvv ne contient pas de chemin
        }
        
        ndest = nsrc.getVpcc(/*metrique*/).get();
        
        
        if (ndest == null) {
            System.out.println("Pas de chemin");
            return null;
        }
        else{
            // parcourir le vpcc depuis la destination jusqu'à la source
            LinkedList chemin = new LinkedList();
            while (ndest != null) {
                chemin.addFirst(ndest);
                ndest = ndest.getDijkstraPred();    // on remonte toute la chaine du vpcc pour trouver les noeud qu'il ce strouve entre la source et la destination.
            }
            return chemin;
        }
    }
    
    public Noeud getDijkstraPoidsMin(List<Noeud> memoire){
        
        Noeud noeudCourant = memoire.get(0);
        
        DijkstraNodeComparator comparator = new DijkstraNodeComparator();
        
        for (int i = 1 ; i < memoire.size() ; i++) {
            if (comparator.compareTo(noeudCourant, memoire.get(i)) == 1) {
                noeudCourant = memoire.get(i);
            }
        }
        return noeudCourant;
        /*Collections.sort(memoire,new DijkstraNodeComparator());*/
    }
    
    public void calculDegres(){
        this.reInitNoeud();
        
        for (Map.Entry<String, Noeud> noeud : this.noeuds.entrySet()) {
            noeud.getValue().setDegreSortant(noeud.getValue().getArcsSort().size());
            for (Arc arc : noeud.getArcsSort()) {
                Noeud dest = arc.getDest();
                dest.setDegreEntrant(dest.getDegreEntrant()+1);
                arc.setSource(noeud.getValue());
                dest.ajouterArcEntrant(arc);
            }
        }
    }
   
    public static Graphe copy(Graphe g) throws IOException, ClassNotFoundException{
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        ObjectOutputStream cout = new ObjectOutputStream(bout);
        cout.writeObject(g);
        byte[] bytes = bout.toByteArray();
        
        ByteArrayInputStream bin = new ByteArrayInputStream(bytes);
        ObjectInputStream cin = new ObjectInputStream(bin);
        Graphe clone = (Graphe) cin.readObject();
        clone.setNom(g.getNom() + " copy");
        return clone;
    }
    
}
