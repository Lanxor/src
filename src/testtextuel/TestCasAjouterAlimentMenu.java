package testtextuel;

import control.ControlAjouterAlimentMenu;
import control.ControlCreerProfil;
import control.ControlSIdentifier;
import control.ControlVerifierIdentification;
import model.ProfilUtilisateur;
import vue.BoundaryAjouterAlimentMenu;

public class TestCasAjouterAlimentMenu {

	public static void main(String[] args) {

		// Mise en place de l'environnement
		ControlCreerProfil controlCreerProfil = new ControlCreerProfil();
		controlCreerProfil.creerProfil(ProfilUtilisateur.GERANT, "Martin",
				"Victor", "gmv");
		ControlSIdentifier controlSIdentifier = new ControlSIdentifier();

		int numGerant = controlSIdentifier.sIdentifier(
				ProfilUtilisateur.GERANT, "Victor.Martin", "gmv");

		ControlVerifierIdentification controlVerifierIdentification = new ControlVerifierIdentification();

		// Initialisation controleur du cas
		ControlAjouterAlimentMenu controlAjouterAlimentMenu = new ControlAjouterAlimentMenu(controlVerifierIdentification);
		// Initialisation vue du cas
		BoundaryAjouterAlimentMenu boundaryAjouterAlimentMenu = new BoundaryAjouterAlimentMenu(controlAjouterAlimentMenu);

		// Lancement du cas
		boundaryAjouterAlimentMenu.ajouterAlimentMenu(numGerant);

		// Verification de la bonne realisation du cas
		System.out.println("VERIFICATION");
		System.out.println(controlAjouterAlimentMenu.visualiserMenu());

		// Resultat du test
		// Entrer le num�ro du type d'aliment que vous souhaitez ajouter
		// 1. ajouter un hamburger
		// 2. ajouter un accompagnement
		// 3. ajouter une boisson
		// 2
		// Veuillez entrer le nom de l'aliment :
		// frites
		// VERIFICATION
		// Menu [listeHamburger=[], listeBoisson=[], listeAccompagnement=[nom=frites]]

	}
}
