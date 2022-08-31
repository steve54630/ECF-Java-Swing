package classes;

import java.util.ArrayList;
import java.util.regex.Pattern;

import exception.AppException;

/**
 * Objet Medecin de la pharmacie
 * 
 * @author SRet
 */
public class Medecin extends Personne {

	/**
	 * identifiant pour "serialize" un objet
	 */
	private static final long serialVersionUID = 1290049247119287201L;
	/**
	 * numero d'agreement du medecin
	 */
	private String numeroAggrement;
	/**
	 * liste des clients du medecin
	 */
	private ArrayList<Client> patients = new ArrayList<>();
	/**
	 * liste des ordonnances que le medecin a ecrit
	 */
	private ArrayList<Ordonnance> ordonnancesPrescrites = new ArrayList<>();

	/**
	 * getter pour le numero d'aggrement
	 * 
	 * @return le numero d'aggrement en {@link String}
	 */
	public String getNumeroAggrement() {
		return numeroAggrement;
	}

	/**
	 * setter pour le numero d'aggrement
	 * 
	 * @param numeroAggrement : numero d'agreement en {@link String}
	 * @throws AppException : un numero d'aggrement contient 16 chiffres
	 */
	public void setNumeroAggrement(String numeroAggrement) throws AppException {
		if (numeroAggrement == null)
			throw new AppException(
					"Un medecin a forcément un numero d'aggrément.");
		if (!Pattern.matches("[0-9]{16}", numeroAggrement))
			throw new AppException(
					"Un numero d'agréément contient 16 chiffres.");
		this.numeroAggrement = numeroAggrement;
	}

	/**
	 * getter pour la liste des patients du medecin
	 * 
	 * @return la liste des {@link Client} du medecin
	 */
	public ArrayList<Client> getPatients() {
		return patients;
	}

	/**
	 * getter pour la liste des ordonnances prescrites du medecin
	 * 
	 * @return la liste des {@link Ordonnance} du medecin
	 */
	public ArrayList<Ordonnance> getOrdonnancesPrescrites() {
		return ordonnancesPrescrites;
	}

	/**
	 * constructeur du medecin
	 * 
	 * @param nom             : nom du medecin
	 * @param prenom          : prenom du medecin
	 * @param adresse         : adresse du medecin
	 * @param numeroTelephone : numero de telephone du medecin
	 * @param email           :e-mail du medecin
	 * @param numeroAggrement : numero d'aggrement du medecin
	 * @param pharma          : phrmacie ou est enregistre le medecin
	 * @throws AppException : erreurs de l'utilisateur
	 */
	public Medecin(String nom, String prenom, Adresse adresse,
			String numeroTelephone, String email, String numeroAggrement,
			Pharmacie pharma) throws AppException {
		super(nom, prenom, adresse, numeroTelephone, email);
		// constructeur de la classe medecin
		this.setNumeroAggrement(numeroAggrement);
		pharma.setMedecins(this);
	}

	/**
	 * ajouter un patient a la liste
	 * 
	 * @param patient : {@link Client} a ajouter
	 * @throws AppException : erreur ajout
	 */
	public void ajouterPatient(Client patient) throws AppException {
		if (patient == null)
			throw new AppException(
					"On ne peut pas ajouter de patient ayant une valeur nulle");
		this.patients.add(patient);
	}

	/**
	 * ajouter uen ordonnance au medecin
	 * 
	 * @param ordonnance : {@link Ordonnance} du medecin
	 * @throws AppException : erreur ajout
	 */
	public void ajouterOrdonnance(Ordonnance ordonnance) throws AppException {
		if (ordonnance == null)
			throw new AppException(
					"On ne peut pas ajouter de ordonnance ayant une valeur nulle");
		this.ordonnancesPrescrites.add(ordonnance);
	}

	/**
	 * Recuperer les donnees du medecin sous forme de String
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Docteur " + this.getNom() + " " + this.getPrenom();
	}

}
