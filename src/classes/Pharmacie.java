package classes;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * pharmacie ou sont enregistrees les donnees
 * 
 * @author SRet
 */
public class Pharmacie implements Serializable {

	/**
	 * identifiant pour "serialize" un objet
	 */
	private static final long serialVersionUID = -6983086474382710418L;
	/**
	 * liste des clients de la pharmacie
	 */
	private ArrayList<Client> clients = new ArrayList<>();
	/**
	 * liste des medecins de la pharmacie
	 */
	private ArrayList<Medecin> medecins = new ArrayList<>();
	/**
	 * liste des achats de la pharmacie
	 */
	private ArrayList<Achat> achats = new ArrayList<>();
	/**
	 * liste des ordonnances de la pharmacie
	 */
	private ArrayList<Ordonnance> ordonnances = new ArrayList<>();
	/**
	 * liste des medicaments de la pharmacie
	 */
	private ArrayList<Medicament> medicaments = new ArrayList<>();
	/**
	 * liste des mutuelles de la pharmacie
	 */
	private ArrayList<Mutuelle> mutuelles = new ArrayList<>();
	/**
	 * liste des specialistes de la pharmacie
	 */
	private ArrayList<Specialiste> specialiste = new ArrayList<>();

	/**
	 * setter pour ajouter les clients a la base de la pharmacie
	 * 
	 * @param client : {@link Client} a ajouter dans la pharmacie
	 */
	public void setClients(Client client) {
		this.clients.add(client);
	}

	/**
	 * setter pour ajouter les medecins a la base de la pharmacie
	 * 
	 * @param medecin : {@link Medecin} a ajouter dans la pharmacie
	 */
	public void setMedecins(Medecin medecin) {
		this.medecins.add(medecin);
	}

	/**
	 * setter pour ajouter les achats a la base de la pharmacie
	 * 
	 * @param achat : {@link Achat} a ajouter dans la pharmacie
	 */
	public void setAchats(Achat achat) {
		this.achats.add(achat);
	}

	/**
	 * setter pour ajouter les ordonnances a la base de la pharmacie
	 * 
	 * @param ordonnance : {@link Ordonnance} a ajouter dans la pharmacie
	 */
	public void setOrdonnances(Ordonnance ordonnance) {
		this.ordonnances.add(ordonnance);
	}

	/**
	 * setter pour ajouter les medicaments a la base de la pharmacie
	 * 
	 * @param medicament : {@link Medicament} a ajouter dans la pharmacie
	 */
	public void setMedicaments(Medicament medicament) {
		this.medicaments.add(medicament);
	}

	/**
	 * setter pour ajouter les mutuelles a la base de la pharmacie
	 * 
	 * @param mutuelle : {@link Mutuelle} a ajouter dans la pharmacie
	 */
	public void setMutuelle(Mutuelle mutuelle) {
		this.mutuelles.add(mutuelle);
	}

	/**
	 * setter pour ajouter les specialistes a la base de la pharmacie
	 * 
	 * @param specialiste : {@link Specialiste}
	 */
	public void setSpecialiste(Specialiste specialiste) {
		this.specialiste.add(specialiste);
	}

	/**
	 * getter pour la liste des clients
	 * 
	 * @return liste des {@link Client}
	 */
	public ArrayList<Client> getClients() {
		return clients;
	}

	/**
	 * getter pour la liste des medecins
	 * 
	 * @return liste des {@link Medecin}
	 */
	public ArrayList<Medecin> getMedecins() {
		return medecins;
	}

	/**
	 * getter pour la liste des achats
	 * 
	 * @return liste des {@link Achat}
	 */
	public ArrayList<Achat> getAchats() {
		return achats;
	}

	/**
	 * getter pour la liste des ordonnances
	 * 
	 * @return liste des {@link Ordonnance}
	 */
	public ArrayList<Ordonnance> getOrdonnances() {
		return ordonnances;
	}

	/**
	 * getter pour la liste des medicaments
	 * 
	 * @return liste des {@link Medicament}
	 */
	public ArrayList<Medicament> getMedicaments() {
		return medicaments;
	}

	/**
	 * getter pour la liste des mutuelles
	 * 
	 * @return liste des {@link Mutuelle}
	 */
	public ArrayList<Mutuelle> getMutuelles() {
		return mutuelles;
	}

	/**
	 * getter pour la liste des specialistes
	 * 
	 * @return liste des {@link Specialiste}
	 */
	public ArrayList<Specialiste> getSpecialiste() {
		return specialiste;
	}

	/**
	 * constructeur pour la classe Pharmacie
	 */
	public Pharmacie() {

	}

}
