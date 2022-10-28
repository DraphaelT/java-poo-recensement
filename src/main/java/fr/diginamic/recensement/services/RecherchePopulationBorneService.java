package fr.diginamic.recensement.services;

import java.util.List;
import java.util.Scanner;

import org.apache.commons.lang3.math.NumberUtils;

import exception.BorneServiceException;
import fr.diginamic.recensement.entites.Recensement;
import fr.diginamic.recensement.entites.Ville;

/**
 * Recherche et affichage de toutes les villes d'un département dont la
 * population est comprise entre une valeur min et une valeur max renseignées
 * par l'utilisateur.
 * 
 * @author DIGINAMIC
 *
 */
public class RecherchePopulationBorneService extends MenuService {

	@Override
	public void traiter(Recensement rec, Scanner scanner) throws BorneServiceException {
		
		System.out.println("Quel est le code du département recherché ? ");
		String choix = scanner.nextLine();
		if(!NumberUtils.isDigits(choix)) {
			throw new BorneServiceException("Ce n'est pas un nombre");
		}
		System.out.println("Choississez une population minimum (en milliers d'habitants): ");
		String saisieMin = scanner.nextLine();
		if(!NumberUtils.isDigits(saisieMin)) {
			throw new BorneServiceException("Ce n'est pas un nombre");
		}
		System.out.println("Choississez une population maximum (en milliers d'habitants): ");
		String saisieMax = scanner.nextLine();
		if(!NumberUtils.isDigits(saisieMax)) {
			throw new BorneServiceException("Ce n'est pas un nombre");
		}
		int min = Integer.parseInt(saisieMin) * 1000;
		
		if (min < 0){
			throw new BorneServiceException("Le Chiffre Minimun ne peut pas être inférieur a 0");
		}

		int max = Integer.parseInt(saisieMax) * 1000;
		
		if (max < 0 || max < min){
			throw new BorneServiceException("Le Chiffre Maximun ne peut pas être inférieur a 0, ni être inférieure au minimun");
		}
		
		List<Ville> villes = rec.getVilles();
		boolean existe = false;
		for (Ville ville : villes) {		
			if (ville.getCodeDepartement().equalsIgnoreCase(choix)) {
				existe = true;
				if (ville.getPopulation() >= min && ville.getPopulation() <= max) {
					System.out.println(ville);
				}	
			}
		}	
		if(existe == false) {
			throw new BorneServiceException("La region n'existe pas");
		}
	}
}
