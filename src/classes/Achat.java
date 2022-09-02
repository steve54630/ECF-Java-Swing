package classes;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import exception.AppException;

/**
 * Objet achat de la pharmacie
 * 
 * @author SRet
 */
public class Achat implements Serializable {

	/**
	 * identifiant pour "serialize" un objet
	 */
	private static final long serialVersionUID = -6008856577443310289L;
	/**
	 * Client de l'achat
	 */
	private Client acheteur;
	/**
	 * date de l'achat
	 */
	private LocalDate date;

	/**
	 * ArrayList de {@link Medicament}
	 */
	private ArrayList<Medicament> medicaments = new ArrayList<>();

	/**
	 * prix total de l'achat
	 */
	private double prixTotal;

	public LocalDate getDate() {
		return date;
	}

	/**
	 * Methode pour recuperer une {@link LocalDate} au format "dd/MM/yyyy"
	 * 
	 * @return la date au format voulu
	 */
	public String dateToString() {
		// TODO Auto-generated method stub
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return this.getDate().format(formatter);
	}

	/**
	 * getter pour celui qui realise l'achat
	 * 
	 * @return le client qui realise l'achat
	 */
	public Client getAcheteur() {
		return acheteur;
	}

	/**
	 * setter pour celui qui realise l'achat
	 * 
	 * @param acheteur = celui qui realise l'achat
	 * @throws AppException : acheteur nul 
	 */
	public void setAcheteur(Client acheteur) throws AppException {
		if (acheteur == null)
			throw new AppException("Un achat a forcément" + " un acheteur");
		this.acheteur = acheteur;
	}

	/**
	 * getter pour la liste des medicaments de l'achat
	 * 
	 * @return la liste des medicaments de l'achat
	 */
	public ArrayList<Medicament> getMedicaments() {
		return medicaments;
	}

	/**
	 * getter pour le prix total de l'achat
	 * 
	 * @return le prix total de l'achat
	 */
	public double getPrixTotal() {
		return prixTotal;
	}

	/**
	 * setter pour le prix total de l'achat
	 * 
	 * @param prixTotal : prix total a obtenir
	 * @throws AppException : prix total negatif
	 */
	public void setPrixTotal(double prixTotal) throws AppException {
		if (prixTotal < 0)
			throw new AppException("Un prix est forcément positif");
		this.prixTotal = prixTotal;
	}

	/**
	 * Constructeur de l'achat
	 * 
	 * @param acheteur : celui qui realise l'achat
	 * @throws AppException : erreurs de l'utilisateur 
	 */
	public Achat(Client acheteur) throws AppException {
		this.setAcheteur(acheteur);
		this.date = LocalDate.now();
	}

	/**
	 * setter pour ajouter un medicaments a l'achat
	 * 
	 * @param medicament : medicament choisi par l'acheteur
	 * @throws AppException : medicament nul
	 */
	public void setMedicaments(Medicament medicament) throws AppException {
		if (medicament == null)
			throw new AppException("Il faut ajouter " + "un medicament");
		Medicament medicamentAchete = new Medicament(medicament.getNom(),
				medicament.getCategorie(),
				medicament.getPrix(),
				medicament.getStock(),
				medicament.getDateCirculation(),
				medicament.getPharma());
		this.medicaments.add(medicamentAchete);
	}

	/**
	 * getter pour la liste des medicaments
	 * 
	 * @return une {@link String} contenant la liste des medicaments
	 */
	private String getListeMedicaments() {

		String temp = "\n";
		for (Medicament medicament : this.medicaments) {
			temp = temp + medicament + "\n";
		}
		return temp;
	}

	/**
	 * Transformer l'objet obtenu en {@link String} lisible par l'utilisateur
	 */
	@Override
	public String toString() {
		LocalDate today = LocalDate.now();
		return "Acheteur = " + this.acheteur.getNom() + " "
				+ this.acheteur.getPrenom() + "\n" + "Agé de "
				+ (today.getYear() - this.acheteur.getDateNaissance().getYear())
				+ " ans\n" + this.getListeMedicaments() + "\nDate = "
				+ this.dateToString() + "\nPrix total = " + this.getPrixTotal()
				+" €" ;
	}

}
