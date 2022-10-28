package fr.diginamic.recensement.services;

import java.util.List;
import java.util.Scanner;

import org.apache.commons.lang3.math.NumberUtils;

import exception.BorneServiceException;
import exception.DepartementServiceException;
import exception.RegionServiceException;
import fr.diginamic.recensement.entites.Recensement;
import fr.diginamic.recensement.entites.Ville;

/**
 * Recherche et affichage de la population d'une région
 * 
 * @author DIGINAMIC
 *
 */
public class RecherchePopulationRegionService extends MenuService {

	@Override
	public void traiter(Recensement rec, Scanner scanner)  throws RegionServiceException {

		System.out.println("Quel est le nom (ou le début de nom) de la région recherchée ? ");
		String choix = scanner.nextLine();
		
		List<Ville> villes = rec.getVilles();
		int somme = 0;
		String nom = null;
		boolean existe = false;
		for (Ville ville : villes) {
			if (ville.getNomRegion().toLowerCase().equals(choix.toLowerCase())
					|| ville.getCodeRegion().toLowerCase().equals(choix.toLowerCase())) {
				existe = true;
				somme += ville.getPopulation();
				nom = ville.getNomRegion();
			}
		}
		if(existe == false) {
			throw new RegionServiceException("La region n'existe pas");
		}
		if (somme > 0) {
			System.out.println("Population de la région " + nom + " : " + somme);
		} else {
			System.out.println("Région " + choix + " non trouvée.");
		}
	}

}
