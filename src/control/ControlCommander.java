package control;

import java.util.ArrayList;
import java.util.List;

import model.Accompagnement;
import model.BDClient;
import model.BDCommande;
import model.Boisson;
import model.Client;
import model.Hamburger;
import model.Menu;
import model.ProfilUtilisateur;

/**
 * 
 * @author LAPEYRADE Sylvain
 * @author OLIVIER Thomas
 *
 */
public class ControlCommander {
	
    /**
     * @see control.ControlVerifierIdentification
     */
    private ControlVerifierIdentification controlVerifierIdentification;
    /**
     * @see model.Menu
     */
    private Menu menu = Menu.getInstance();
    /**
     * @see model.BDClient
     */
    private BDClient bdClient = BDClient.getInstance();
    /**
     * @see model.BDCommande
     */
    private BDCommande bdCommande = BDCommande.getInstance();
    
    /**
     * Constructeur ControlCommander.
     *
     * @param controlVerifierIdentification Controleur vérification identification.
     */
    public ControlCommander(ControlVerifierIdentification controlVerifierIdentification) {
        this.controlVerifierIdentification = controlVerifierIdentification;
    }
    
    /**
     * Vérifie l'identification d'un profil.
     *
     * @param numProfil Numéro de profil à vérifier.
     * @return Vrai si le profil est connecté, faux sinon.
     */
    public boolean verifierIdentification(int numProfil) {
    	boolean identificationOk = controlVerifierIdentification
    			.verifierIdentification(ProfilUtilisateur.CLIENT, numProfil);
    	return identificationOk;
    }

    /**
     * Getter listeHamburger.
     *
     * @return Liste des hamburgers avec leur numero et leur nom.
     */
    public List<String> getListHamburger() {
    	List<String> listeHamburgers = new ArrayList<String>();
    	List<Hamburger> listeHamburger = menu.getListHamburger();
    	int numero = 1;
    	for ( Hamburger hamburger : listeHamburger ) {
    		listeHamburgers.add(numero + ". " + hamburger.getNom());
    		numero++;
    	}
    	return listeHamburgers;
    }

    /**
     * Getter listeAccompagnement.
     *
     * @return Liste des accompagnements avec leur numero et leur nom.
     */
    public List<String> getListAccompagnement() {
    	List<String> listeAccompagnements = new ArrayList<String>();
    	List<Accompagnement> listeAccompagnement = menu.getListAccompagnement();
    	int numero = 1;
    	for ( Accompagnement accompagnement : listeAccompagnement ) {
    		listeAccompagnements.add(numero + ". " + accompagnement.getNom());
    		numero++;
    	}
    	return listeAccompagnements;
    }

    /**
     * Getter listeBoisson.
     *
     * @return Liste des boissons avec leur numero et leur nom.
     */
    public List<String> getListBoisson() {
    	List<String> listeBoissons = new ArrayList<String>();
    	List<Boisson> listeBoisson = menu.getListBoisson();
    	int numero = 1;
    	for ( Boisson boisson : listeBoisson ) {
    		listeBoissons.add(numero + ". " + boisson.getNom());
    		numero++;
    	}
    	return listeBoissons;
    }
    
    /**
     * Vérifie si un client possède une carte bancaire.
     *
     * @param numClient Numéro de client à vérifier.
     * @return Vrai s'il possède une carte bancaire, fau sinon.
     */
    public boolean isCarteRenseignee(int numClient) {
    	Client client = bdClient.selectionnerClient(numClient);
    	boolean carteRenseignee = client.isCoordonneesBancaireExist();
    	return carteRenseignee;
    }
    
    /**
     * Enregistre une commande dans la base de données.
     *
     * @param numClient Numéro du client de la commande.
     * @param numHamburger Numéro du hamburger de la commande.
     * @param numAccompagnement Numéro de l'accompagnement de la commande.
     * @param numBoisson Numéro de la boisson de la commande.
     * @return Le numéro de la commande crée.
     */
    public int enregistrerCommande(int numClient, int numHamburger, int numAccompagnement, int numBoisson) {
    	Hamburger hamburger = menu.getHamburger(numHamburger);
    	Accompagnement accompagnement = menu.getAccompagnement(numAccompagnement);
    	Boisson boisson = menu.getBoisson(numBoisson);
    	int numeroCommande = bdCommande.enregistrerCommande(numClient, hamburger, accompagnement, boisson);
    	return numeroCommande;
    }
    
    /**
     * Renvoie la chaine de caractère des commandes de la base de données.
     *
     * @return La chaine de caractère des commandes de la base de données.
     */
    public String visualiserBDCommandes() {
		return (bdCommande.toString());
    }
}
