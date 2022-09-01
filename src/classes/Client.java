package classes;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import java.util.regex.Pattern;

import exception.AppException;

/**
 * Objet client de la pharmacie
 * 
 * @author SRet
 *
 */

public class Client extends Personne {

	/**
	 * identifiant pour "serialize" un objet
	 */
	private static final long serialVersionUID = -4591804252029740329L;
	/**
	 * date de naissance du client
	 */
	private LocalDate dateNaissance;
	/**
	 * mutuelle du client
	 */
	private Mutuelle mutuelle;
	/**
	 * medecin du client
	 */
	private Medecin medecin;
	/**
	 * liste des specialistes lies au client
	 */
	private ArrayList<Specialiste> specialiste = new ArrayList<>();;
	/**
	 * numero de securite sociale du client
	 */
	private String securiteSociale;
	private Pharmacie pharma;

	/**
	 * getter pour le numero de securite sociale
	 * 
	 * @return le numero de securite sociale
	 */
	public String getSecuriteSociale() {
		return securiteSociale;
	}

	/**
	 * setter pour le numero de securite sociale
	 * 
	 * @param securiteSociale : numero de securite sociale
	 * @throws AppException : contient obligatoirement 15 chiffres
	 */
	public void setSecuriteSociale(String securiteSociale) throws AppException {
		if (securiteSociale == null)
			throw new AppException("Le numero de sécurité est nécessaire");
		if (Pattern.matches("[0-9]{14}", securiteSociale))
			throw new AppException(
					"Le numero de sécurité est composé de 15 chiffres uniquement.");
		this.securiteSociale = securiteSociale;
	}

	/**
	 * getter pour la date de naissance
	 * 
	 * @return la date de naissance au format {@link LocalDate}
	 */
	public LocalDate getDateNaissance() {
		return dateNaissance;
	}
	
	
	/** Methode pour recuperer une {@link LocalDate} au format "dd/MM/yyyy"
	 * @return la date au format voulu
	 */
	public String dateToString () {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return this.getDateNaissance().format(formatter);
	}

	/**
	 * setter pour la date de naissance
	 * 
	 * @param year  : annee de naissance
	 * @param month : mois de naissance
	 * @param day   : jour de naissance
	 */
	public void setDateDeNaissance(String year, String month, String day)
	{
		String date = year+"-"+month+"-"+day;
		this.dateNaissance = LocalDate.parse(date);
	}

	/**
	 * getter pour la mutuelle
	 * 
	 * @return le parametre {@link Mutuelle} de la classe
	 */
	public Mutuelle getMutuelle() {
		return mutuelle;
	}

	/**
	 * setter pour la mutuelle
	 * 
	 * @param mutuelle : {@link Mutuelle} de la classe
	 * @throws AppException : aucune mutuelle est ajoute
	 */
	public void setMutuelle(Mutuelle mutuelle) throws AppException {
		if (mutuelle == null)
			throw new AppException("Un client a obligatoirement une mutuelle");
		this.mutuelle = mutuelle;
	}

	/**
	 * getter pour le medecin du client
	 * 
	 * @return le {@link Medecin} du client
	 */
	public Medecin getMedecin() {
		return medecin;
	}

	/**
	 * setter pour le medecin du client
	 * 
	 * @param medecin : choix du medecin du client au format {@link Medecin}
	 * @throws AppException : erreur d'ajout
	 */
	public void setMedecin(Medecin medecin) throws AppException {
		if (medecin == null)
			throw new AppException("Un medecin est necessaire");
		this.medecin = medecin;
	}

	/**
	 * getter pour la liste des specialistes lies au client
	 * 
	 * @return l'ArrayList des {@link Specialiste} du client
	 */
	public ArrayList<Specialiste> getSpecialiste() {
		return specialiste;
	}

	/**
	 * setter pour ajouter un specialiste a la liste du client
	 * 
	 * @param specialiste : {@link Specialiste} à ajouter au client
	 * @throws AppException : le specialiste existe deja dans les donnees du
	 *                      client
	 */
	public void setSpecialiste(Specialiste specialiste) throws AppException {
		if (specialiste == null) throw new AppException
		("Aucun specialiste ne peut etre ajoute au client");
		if (this.getSpecialiste().contains(specialiste))
			throw new AppException("Ce specialiste est deja ajoute au client");
		this.specialiste.add(specialiste);
	}

	/**
	 * Constructeur du client
	 * 
	 * @param nom             : nom du client
	 * @param prenom          : prenom du client
	 * @param adresse         : adresse du client
	 * @param numeroTelephone : numero de telephone du client
	 * @param securiteSociale : numero de securite sociale du client
	 * @param email           : email du client
	 * @param year            : annee de naissance du client
	 * @param month           : mois de naisssance du client
	 * @param day             : jour de naissance du client
	 * @param medecin         : Medecin du client
	 * @param mutuelle        : mutuelle du client
	 * @param pharma          : pharmacie ou est enregistre le client
	 * @throws AppException : erreurs de l'utilisateur 
	 */
	public Client(String nom, String prenom, Adresse adresse,
			String numeroTelephone, String securiteSociale, String email,
			String year, String month, String day, Medecin medecin, Mutuelle mutuelle,
			Pharmacie pharma) throws AppException{
		super(nom, prenom, adresse, numeroTelephone, email);
		// Constructeur de la classe client
		this.setDateDeNaissance(year, month, day);
		this.setSecuriteSociale(securiteSociale);
		this.setMutuelle(mutuelle);
		this.getMutuelle().ajouterClients(this);
		this.setMedecin(medecin);
		this.getMedecin().ajouterPatient(this);
		this.setPharma(pharma);
		this.getPharma().setClients(this);
	}

	public Client ()
	{
		
	}
	
	/**
	 * Afficher la liste des specialistes
	 * 
	 * @return la liste des {@link Specialiste} du client
	 */
	public String afficherListeSpecialiste() {

		String temp = "";
		for (int i = 0; i < this.specialiste.size(); i++) {
			temp = temp + this.specialiste.get(i).toString() + "\n";
		}
		return temp;
	}

	/**
	 * Renvoie les donnees du client sous forme de {@link String}
	 */
	@Override
	public String toString() {
		
		return "Nom = " + this.getNom() + ", Prenom = " + this.getPrenom()
				+ "\n" + "Adresse = " + this.getAdresse()
				+ "\nNumero de telephone = " + this.getNumeroTelephone() + "\n"
				+ "E-Mail =" + this.getEmail() + "\n" + "N°Sécurité Scoiale = "
				+ this.getSecuriteSociale() + "\n" + "Date de naissance = "
				+ this.dateToString()+ "\n"
				+ "Mutuelle = " + this.mutuelle.getNom() + "\n"
				+ "Medecin traitant = " + this.medecin.getNom() + " "
				+ this.getMedecin().getPrenom() + "\n" + "Specialiste = "
				+ this.afficherListeSpecialiste();
	}

	public Pharmacie getPharma() {
		return pharma;
	}

	public void setPharma(Pharmacie pharma) {
		this.pharma = pharma;
	}

}
