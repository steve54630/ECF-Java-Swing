package classes;

import java.io.Serializable;
import java.util.regex.Pattern;

import exception.AppException;

/**
 * constructeur des adresses dans l'application
 * 
 * @author SRet
 */
public class Adresse implements Serializable {

	/**
	 * identifiant pour "serialize" un objet
	 */
	private static final long serialVersionUID = -7649748190005458920L;
	/**
	 * numero de la rue
	 */
	private String numero;
	/**
	 * nom de la rue
	 */
	private String rue;
	/**
	 * code postal de la ville
	 */
	private String codePostal;
	/**
	 * ville ou est localise l'adresse
	 */
	private String ville;

	/**
	 * getter pour le numero de la rue
	 * 
	 * @return le numero de la rue
	 */
	public String getNumero() {
		return numero;
	}

	/**
	 * setter pour le numero de la rue
	 * 
	 * @param numero : numero de la rue
	 * @throws AppException : erreur dans la saisi
	 */
	public void setNumero(String numero) throws AppException {
		if (numero == null)
			throw new AppException("Un numero de rue est necessaire");
		if (!Pattern.matches("[0-9]{1,3}", numero))
			throw new AppException("Un numero de rue est necessaire "
					+ "et ne contient que des chiffres.");
		this.numero = numero;
	}

	/**
	 * getter pour le nom de la rue
	 * 
	 * @return le nom de la rue en {@link String}
	 */
	public String getRue() {
		return rue;
	}

	/**
	 * setter pour le numero de la rue
	 * 
	 * @param rue : nom de la rue
	 * @throws AppException : si l'utilisateur n'a pas entre de nom de rue
	 */
	public void setRue(String rue) throws AppException {
		if (rue == null)
			throw new AppException("Un nom de rue est nécessaire.");
		this.rue = rue;
	}

	/**
	 * getter pour le code postal
	 * 
	 * @return le code postal en {@link String}
	 */
	public String getCodePostal() {
		return codePostal;
	}

	/**
	 * setter pour le code postal
	 * 
	 * @param codePostal : code postal de l'adresse
	 * @throws AppException : si il n'y a pas 5 chiffres dans le code postal
	 */
	public void setCodePostal(String codePostal) throws AppException {
		if (codePostal == null)
			throw new AppException("Un code postal est nécessaire");
		if (!Pattern.matches("[0-9]{5}", codePostal))
			throw new AppException("Un code postal est composé de 5 chiffres");
		this.codePostal = codePostal;
	}

	/**
	 * getter pour la ville
	 * 
	 * @return le nom de la ville en {@link String}
	 */
	public String getVille() {
		return ville;
	}

	/**
	 * setter pour la ville
	 * 
	 * @param ville : nom de la ville
	 * @throws AppException : si la ville ne commence par une majuscule ou n'est
	 *                      pas present
	 */
	public void setVille(String ville) throws AppException {
		if (ville == null)
			throw new AppException("Un nom de ville est necessaire");
		if (!Pattern.matches("[A-Z]{1}[a-zA-Zàâéèêîôùûç-]{1,}", ville))
			throw new AppException(
					"Un nom de ville est nécessaire, commence par une majuscule,\n"
							+ "contient seulement des lettres (accentués ou non) et des tirets.");
		this.ville = ville;
	}

	/**
	 * constructeur pour la ville
	 * 
	 * @param numero     : numero de la rue
	 * @param rue        : nom de la rue
	 * @param codePostal : code postal
	 * @param ville      : nom de la ville
	 * @throws AppException : erreurs de l'utilisateur
	 */
	public Adresse(String numero, String rue, String codePostal, String ville)
			throws AppException {
		this.setNumero(numero);
		this.setRue(rue);
		this.setCodePostal(codePostal);
		this.setVille(ville);
	}

	/**
	 * Transforme les valeur de l'objet courant en {@link String}
	 */
	@Override
	public String toString() {
		return "" + this.getNumero() + ", " + this.getRue() + ", "
				+ this.getCodePostal() + " " + this.getVille();
	}

}
