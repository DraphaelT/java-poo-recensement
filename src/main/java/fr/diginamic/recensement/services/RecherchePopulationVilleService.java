package fr.diginamic.recensement.services;

import java.util.List;
import java.util.Scanner;

import org.apache.commons.lang3.math.NumberUtils;

import exception.BorneServiceException;
import exception.RegionServiceException;
import exception.VilleServiceException;
import fr.diginamic.recensement.entites.Recensement;
import fr.diginamic.recensement.entites.Ville;

/**
 * Recherche et affichage de la population d'une ville
 * 
 * @author DIGINAMIC
 *
 */
public class RecherchePopulationVilleService extends MenuService {

	@Override
	public void traiter(Recensement rec, Scanner scanner) throws VilleServiceException {

		System.out.println("Quel est le nom de la ville recherchée ? ");
		String choix = scanner.nextLine();
		
		List<Ville> villes = rec.getVilles();
		boolean existe = false;
		for (Ville ville : villes) {
			if (ville.getNom().equalsIgnoreCase(choix)
					|| ville.getNom().toLowerCase().startsWith(choix.toLowerCase())) {
				existe = true;
				System.out.println(ville);
			}
		}
		if(existe == false) {
			throw new VilleServiceException("La Ville n'existe pas");
		}
	}

}
