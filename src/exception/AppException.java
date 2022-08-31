package exception;

/**
 * Classe d'exception pour les erreurs de l'utilisateur
 * 
 * @author SRet
 */
public class AppException extends Exception {

	/**
	 * identifiant pour "serialize" un objet
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Fenetre a afficher si l'exception renvoit un message
	 * 
	 * @param message : message à afficher
	 */
	public AppException(String message) {
		super(message);
	}

}
