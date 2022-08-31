package classes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.regex.Pattern;

import exception.AppException;

/**
 * Objet mutuelle de la pharmacie
 * 
 * @author SRet
 */
public class Mutuelle implements Serializable {

	/**
	 * identifiant pour "serialize" un objet
	 */
	private static final long serialVersionUID = -4411302578738763356L;
	/**
	 * nom de la mutuelle
	 */
	private String nom;
	/**
	 * adresse de la mutuelle
	 */
	private Adresse adresse;
	/**
	 * departement de la mutuelle
	 */
	private String telephone;
	/**
	 * numero de telephone de la mutuelle
	 */
	private String eMail;
	/**
	 * email de la mutuelle
	 */
	private String departement;
	/**
	 * departement de la mutuelle
	 */
	private int remboursement;
	/**
	 * liste des clients
	 */
	private ArrayList<Client> clients = new ArrayList<>();

	/**
	 * getter pour le nom de la mutuelle
	 * 
	 * @return nom de la mutuelle
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * setter pour le nom de la mutuelle
	 * 
	 * @param nom : nom de la mutuelle
	 * @throws AppException : erreur de frappe
	 */
	public void setNom(String nom) throws AppException {
		if (nom == null)
			throw new AppException("Une mutuelle a forcement un nom");
		if (!Pattern.matches("[A-Za-zéèàçùêëî. ]{3,}", nom))
			throw new AppException("Un nom de mutuelle est ecrit en lettre"
					+ "et peut contenir des points.");
		this.nom = nom;
	}

	/**
	 * getter pour l'adresse de la mutuelle
	 * 
	 * @return {@link Adresse} de la mutuelle
	 */
	public Adresse getAdresse() {
		return adresse;
	}

	/**
	 * setter de la mutuelle
	 * 
	 * @param adresse : {@link Adresse} de la mutuelle
	 * @throws AppException : absence d'adresse
	 */
	public void setAdresse(Adresse adresse) throws AppException {
		if (adresse == null)
			throw new AppException("Une mutuelle a forcement une adresse");
		this.adresse = adresse;
	}

	/**
	 * getter du numero de telephone de la mutuelle
	 * 
	 * @return le numero de telephone de la mutuelle en {@link String}
	 */
	public String getTelephone() {
		return telephone;
	}

	/**
	 * setter pour le numero de telephone de la mutuelle
	 * 
	 * @param telephone : numero de telephone en {@link String}
	 * @throws AppException : format telephone non respecte
	 */
	public void setTelephone(String telephone) throws AppException {
		if (!Pattern.matches("[0-9]{4}", telephone) &&
		!Pattern.matches("[0][0-9][.][0-9]{2}[.][0-9]{2}[.][0-9]{2}[.][0-9]{2}", telephone))
			throw new AppException(
	"Un numero de telephone est compose de 4 chiffres minimum et que des chiffres"
	+ "ou s'ecrit au format xx.xx.xx.xx.xx (x étant des chiffres)");
		this.telephone = telephone;
	}

	/**
	 * getter pour l'email de la mutuelle
	 * 
	 * @return l'email de la mutuelle
	 */
	public String geteMail() {
		return eMail;
	}

	/**
	 * setter pour l'email de la mutuelle
	 * 
	 * @param eMail : email de la mutuelle en {@link String}
	 * @throws AppException : erreur lors de l'entree de l'email
	 */
	public void seteMail(String eMail) throws AppException {
		if (eMail == null)
			throw new AppException("Une mutuelle a forcement un e-mail.");
		if (!Pattern.matches("[a-zA-Z0-9_.-]{1,}@{1}[a-zA-Z0-9.-]{2,}[.]{1}[a-z]{2,3}", eMail))
			throw new AppException(
					"Un E-Mail s'ecrit au format abc@domaine.fr.");
		this.eMail = eMail;
	}

	/**
	 * getter pour le departement de la mutuelle
	 * 
	 * @return le departement de la mutuelle en {@link String}
	 */
	public String getDepartement() {
		return departement;
	}

	/**
	 * setter pour le departement de la mutuelle
	 * 
	 * @param departement : departement de la mutuelle en {@link String}
	 * @throws AppException : erreur dans la saisi du departement
	 */
	public void setDepartement(String departement) throws AppException {
		if (departement == null)
			throw new AppException(
					"Une mutuelle est forcement installe dans un departement");
		if (!Pattern.matches("[A-Z]{1}[A-Za-z -]{2,}", departement))
			throw new AppException(
					"Le departement contient seulement des lettres "
							+ "et des tirets");
		this.departement = departement;
	}

	/**
	 * getter du taux de remboursement de la mutuelle
	 * 
	 * @return le taux de remboursement de la mutuelle en %
	 */
	public int getRemboursement() {
		return remboursement;
	}

	/**
	 * setter pour le taux de remboursement de la mutuelle
	 * 
	 * @param remboursement : taux de remboursement de la mutuelle
	 * @throws AppException : taux de remboursement non compris entre 0 et 100
	 */
	public void setRemboursement(int remboursement) throws AppException {
		if (1 > remboursement || remboursement > 99)
			throw new AppException(
					"Le taux de remboursement est compris " + "entre 0 et 100");
		this.remboursement = remboursement;
	}

	/**
	 * getter pour la liste des clients de la mutuelle
	 * 
	 * @return la liste des clients de la mutuelle
	 */
	public ArrayList<Client> getClients() {
		return clients;
	}

	/**
	 * Constructeur de l'Objet de la mutuelle
	 * 
	 * @param nom           : nom de la mutuelle
	 * @param adresse       : adresse de la mutuelle
	 * @param telephone     : telephone de la mutuelle
	 * @param eMail         : email de la mutuelle
	 * @param departement   : departement de la mutuelle
	 * @param remboursement : remboursement de la mutuelle
	 * @param pharma        : base de donnees de la pharmacie
	 * @throws AppException : erreurs de l'utilisateur 
	 */
	public Mutuelle(String nom, Adresse adresse, String telephone, String eMail,
			String departement, int remboursement, Pharmacie pharma)
			throws AppException {

		this.setNom(nom);
		this.setAdresse(adresse);
		this.setTelephone(telephone);
		this.seteMail(eMail);
		this.setDepartement(departement);
		this.setRemboursement(remboursement);
		pharma.setMutuelle(this);
	}

	/**
	 * ajout d'un client a la mutuelle
	 * 
	 * @param client : {@link Client} de la mutuelle
	 * @throws AppException  : aucun client selectionne
	 */
	public void ajouterClients(Client client) throws AppException {
		if (client == null) throw new AppException
		("Aucun client n'est ajouté a la mutuelle");
		this.clients.add(client);
	}

	/**
	 * Outil pour recuperer le nom de la mutuelle
	 */
	@Override
	public String toString() {
		return nom;
	}

}
