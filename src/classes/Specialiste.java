package classes;

import exception.AppException;

/**
 * Objet specialiste de la pharmacie
 * 
 * @author SRet
 */
public class Specialiste extends Personne {

	/**
	 * identifiant pour "serialize" un objet
	 */
	private static final long serialVersionUID = 120533401974972919L;
	/**
	 * specialite du specialiste
	 */
	private Specialite specialite;

	/**
	 * getter pour la specialite du specialiste
	 * 
	 * @return {@link Specialite} du Specialiste
	 */
	public Specialite getSpecialite() {
		return specialite;
	}

	/**
	 * setter pour la specialite du specialiste
	 * 
	 * @param specialite : {@link Specialite} du Specialiste
	 */
	private void setSpecialite(Specialite specialite) {
		if (specialite == null) throw new NullPointerException("Un specialiste"
				+ "a une specialite.");
		this.specialite = specialite;
	}

	/**
	 * Constructeur du specialiste
	 * 
	 * @param nom             : nom du specialiste
	 * @param prenom          : prenom du specialiste
	 * @param adresse         : adresse du specialiste
	 * @param numeroTelephone : numero de telephone du specialiste
	 * @param email           : E-mail du specialiste
	 * @param specialite      : specialite du specialiste
	 * @param pharma          : pharmacie ou est enregistre le specialiste
	 * @throws AppException : erreurs de l'utilisateur
	 */
	public Specialiste(String nom, String prenom, Adresse adresse,
			String numeroTelephone, String email, Specialite specialite,
			Pharmacie pharma) throws AppException, AppException {
		super(nom, prenom, adresse, numeroTelephone, email);
		// TODO Auto-generated constructor stub
		this.setSpecialite(specialite);
		pharma.setSpecialiste(this);
	}

	/**
	 * Permet de recuperer le nom, prenom et {@link Specialite} du specialiste
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Docteur " + this.getNom() + " " + this.getPrenom() + ", "
				+ this.getSpecialite();
	}

}
