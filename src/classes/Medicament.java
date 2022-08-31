package classes;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.GregorianCalendar;
import java.util.regex.Pattern;

import exception.AppException;

/**
 * Objet medicament de la pharmacie
 * 
 * @author SRet
 */
public class Medicament implements Serializable {

	/**
	 * identifiant pour "serialize" un objet
	 */
	private static final long serialVersionUID = 4271170431441892140L;
	/**
	 * nom du medicament
	 */
	private String nom;
	/**
	 * categorie du medicament
	 */
	private String categorie;
	/**
	 * prix du medicament (avec la virgule possible)
	 */
	private double prix;
	/**
	 * date de mise en circulation du medicament
	 */
	private LocalDate dateCirculation;
	/**
	 * nombre de medicament en stock dans la pharmacie ou il est ajoute
	 */
	private int stock;

	/**
	 * setter pour le nom du medicament
	 * 
	 * @param nom : nom du medicament
	 * @throws AppException : erreur dans la saisi du nom
	 */
	public void setNom(String nom) throws AppException {
		if (nom == null)
			throw new AppException("Un medicament a besoin d'un nom");
		if (!Pattern.matches("[A-Za-z]{3,}", nom))
			throw new AppException("Un nom de medicament est ecrit en lettres");
		this.nom = nom;
	}

	/**
	 * getter pour le nom du medicament
	 * 
	 * @return le nom du medicament en {@link String}
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * setter pour la categorie du medicament
	 * 
	 * @param categorie : categorie du medicament en {@link String}
	 * @throws AppException : erreur dans la saisie de la categorie
	 */
	public void setCategorie(String categorie) throws AppException {
		if (categorie == null)
			throw new AppException("Un medicament a besoin d'une categorie");
		if (!Pattern.matches("[A-Za-zéèçâäëêù]{3,}", categorie))
			throw new AppException(
					"La categorie d'un medicament est " + "ecrit en lettres");
		this.categorie = categorie;
	}

	/**
	 * getter pour la categorie du medicament
	 * 
	 * @return la categorie du medicament en {@link String}
	 */
	public String getCategorie() {
		return categorie;
	}

	/**
	 * setter pour le prix du medicament
	 * 
	 * @param prix : prix du medicament
	 * @throws AppException : refus d'un prix inferieur ou egal a 0
	 */
	public void setPrix(double prix) throws AppException {
		if (prix < 0)
			throw new AppException(
					"Le prix d'un medicament est forcement superieur à 0");
		this.prix = prix;
	}

	/**
	 * getter pour le prix du medicament
	 * 
	 * @return le prix du medicament
	 */
	public double getPrix() {
		return prix;
	}

	/**
	 * setter pour la date de mise en circulation
	 * 
	 * @param year  : annee de mise en circulation
	 * @param month : mois de mise en circulation
	 * @param day   : jour de mise circulation
	 * @throws AppException : erreur dans la saisi de la date
	 */
	public void setDateCirculation(String year, String month, String day)
			throws AppException, DateTimeParseException {
		String date = year + "-" + month + "-" + day;
		this.dateCirculation = LocalDate.parse(date);
	}

	/**
	 * getter pour la date de mise en circulation
	 * 
	 * @return date de mise en circulation au format {@link GregorianCalendar}
	 */
	public LocalDate getDateCirculation() {
		return dateCirculation;
	}

	/** Methode pour recuperer une {@link LocalDate} au format "dd/MM/yyyy"
	 * @return la date au format voulu
	 */
	public String dateToString() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return this.getDateCirculation().format(formatter);
	}

	/**
	 * getter pour le stock de medicaments de la pharmacie
	 * 
	 * @return le stock de medicaments dans la pharmacie
	 */
	public int getStock() {
		return stock;
	}

	/**
	 * setter pour le stock de medicaments de la pharmacie
	 * 
	 * @param stock : stock de medicaments de la pharmacie
	 * @throws AppException : un stock ne peut pas etre negatif
	 */
	public void setStock(int stock) throws AppException {
		if (stock <= 0)
			throw new AppException("Un stock est soit positif soit null.");
		this.stock = stock;
	}

	/**
	 * Constructeur pour le medicament
	 * 
	 * @param nom       : nom du medicament
	 * @param categorie : categorie du medicament
	 * @param prix      : prix du medicament
	 * @param stock     : stock du medicament
	 * @param year      : annee de fabrication
	 * @param month     : mois de fabrication
	 * @param day       : jour de fabrication
	 * @param pharma    : pharmacie ou est stocke le medicament
	 * @throws AppException : erreurs de l'utilisateur
	 */
	public Medicament(String nom, String categorie, int prix, int stock,
			String year, String month, String day, Pharmacie pharma)
			throws AppException {
		this.setNom(nom);
		this.setCategorie(categorie);
		this.setPrix(prix);
		this.setDateCirculation(year, month, day);
		this.setStock(stock);
		pharma.setMedicaments(this);
	}

	/**
	 * Recuperer les donnees du medicament qui nous interessent
	 */
	@Override
	public String toString() {
		return this.getNom() + ", " + this.getCategorie() + ", Prix= "
				+ this.getPrix() + "€";
	}

}
