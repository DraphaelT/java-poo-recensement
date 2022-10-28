package fr.diginamic.recensement.services;

import java.util.List;
import java.util.Scanner;

import org.apache.commons.lang3.math.NumberUtils;

import exception.BorneServiceException;
import exception.DepartementServiceException;
import fr.diginamic.recensement.entites.Recensement;
import fr.diginamic.recensement.entites.Ville;

/** Recherche et affichage de la population d'un département
 * @author DIGINAMIC
 *
 */
public class RecherchePopulationDepartementService extends MenuService {

	@Override
	public void traiter(Recensement rec, Scanner scanner) throws DepartementServiceException {
		
		System.out.println("Quel est le code du département recherché ? ");
		String choix = scanner.nextLine();
		
		if (!NumberUtils.isDigits(choix)) {
			throw new DepartementServiceException("Le département doit être un entier.");
		}
		
		List<Ville> villes = rec.getVilles();
		int somme = 0;
		boolean existe = false;
		for (Ville ville: villes){
			if (ville.getCodeDepartement().equalsIgnoreCase(choix)){
				existe = true;
				somme+=ville.getPopulation();
			}
		}
		if(existe == false) {
			throw new DepartementServiceException("Le Departement n'existe pas");
		}
		if (somme>0){
			System.out.println("Population du département "+choix+" : "+ somme);
		}
		else {
			System.out.println("Département "+choix+ " non trouvé.");
		}
	}

}
