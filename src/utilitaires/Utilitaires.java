package utilitaires;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;

import classes.Achat;
import classes.Client;
import classes.Medecin;
import classes.Medicament;
import classes.Ordonnance;

import static main.App.*;

/** Utilitaires de l'application
 * @author SRet
 *
 */
public class Utilitaires {

	/** Methode pour choisir un client
	 * @param frame : JFrame ou se situe les JOptionPane
	 * @return le client choisi par l'utilisateur
	 * @throws NullPointerException : ce qui se passe si l'utilisateur ne choisit
	 * rien ou appuie sur la croix
	 */
	public static Client choixClient(JFrame frame) throws NullPointerException {

		Client clientChoix = null;

			DefaultListModel<String> list = new DefaultListModel<>();

			for (Client client : getPharma().getClients())
				list.addElement(client.getNom() + " " + client.getPrenom());

			JList<String> listeClient = new JList<String>(list);
			JOptionPane.showMessageDialog(frame, listeClient, "Clients",
					JOptionPane.PLAIN_MESSAGE);
			for (int i = 0; i < getPharma().getClients().size(); i++) {
				if (listeClient.isSelectedIndex(i))
					clientChoix = getPharma().getClients().get(i);
			}
		return clientChoix;
	}

	/** Methode pour choisir un medecin
	 * @return le medecin choisi par l'utilisateur
	 * @throws NullPointerException : ce qui se passe si l'utilisateur ne choisit
	 * rien ou appuie sur la croix
	 */
	public static Medecin choixMedecin() throws NullPointerException {

		Medecin medecinChoix = null;

			DefaultListModel<String> list = new DefaultListModel<>();

			for (Medecin client : getPharma().getMedecins())
				list.addElement(client.getNom() + " " + client.getPrenom());

			JList<String> listeClient = new JList<String>(list);
			JOptionPane.showMessageDialog(null, listeClient, "Clients",
					JOptionPane.PLAIN_MESSAGE);
			for (int i = 0; i < getPharma().getMedecins().size(); i++) {
				if (listeClient.isSelectedIndex(i))
					medecinChoix = getPharma().getMedecins().get(i);
			}
		return medecinChoix;
	}

	/** Methode pour choisir un medicament
	 * @return le medicament choisi par l'utilisateur
	 * @throws NullPointerException : ce qui se passe si l'utilisateur ne choisit
	 * rien ou appuie sur la croix
	 */
	public static Medicament choixMedicaments() throws NullPointerException {
		Medicament choix = null;

			DefaultListModel<String> list = new DefaultListModel<>();

			for (Medicament medicament : getPharma().getMedicaments())
				list.addElement(medicament.getNom());

			JList<String> listeClient = new JList<String>(list);
			JOptionPane.showMessageDialog(null, listeClient, "Clients",
					JOptionPane.PLAIN_MESSAGE);
			for (int i = 0; i < getPharma().getMedicaments().size(); i++) {
				if (listeClient.isSelectedIndex(i))
					choix = getPharma().getMedicaments().get(i);
			}
		return choix;
	}

	/**
	 * Fonction afficher Achat.toString
	 * @param achat : achat a afficher
	 */
	public static void afficherAchat(Achat achat) {
		JOptionPane.showMessageDialog(null, achat.toString(),
				"Achat de " + achat.getAcheteur().getNom() + " "
						+ achat.getAcheteur().getPrenom() + ", le "
						+ achat.dateToString(),
				JOptionPane.INFORMATION_MESSAGE);
	}
	
	/**
	 * afficher l'ordonnance dans un {@link JOptionPane}
	 * @param ordonnance : ordonnance a afficher
	 */
	public static void afficherOrdonnance(Ordonnance ordonnance) {
		String temp = "";
		if (ordonnance.getSpecialiste() == null)
			temp = "Ordonnance réalisé par " + ordonnance.getMedecin().getNom() + " "
					+ordonnance.getMedecin().getPrenom();
		else
			temp = "Ordonnance réalisé par " + ordonnance.getMedecin().getNom() + " "
					+ordonnance.getMedecin().getPrenom()
					+ ", appuyé par le specialiste "
					+ ordonnance.getSpecialiste().getNom() + " "
					+ ordonnance.getSpecialiste().getPrenom() + ", "
					+ ordonnance.getSpecialiste().getSpecialite();
		JOptionPane.showMessageDialog(null, ordonnance.toString(), temp,
				JOptionPane.INFORMATION_MESSAGE);
	}
	
}
