package classes;

import java.io.Serializable;

import exception.AppException;

/**
 * Objet ordonnance de la pharmacie
 * 
 * @author SRet
 */
public class Ordonnance extends Achat implements Serializable {

	/**
	 * identifiant pour "serialize" un objet
	 */
	private static final long serialVersionUID = 2828777103581029224L;
	/**
	 * medecin qui a redige l'ordonnance
	 */
	Medecin medecin;
	/**
	 * specialiste qui a co-redige l'ordonnance
	 */
	Specialiste specialiste;

	/**
	 * getter pour le medecin traitant du client
	 * 
	 * @return le {@link Medecin} traitant du client
	 */
	public Medecin getMedecin() {
		return medecin;
	}

	/**
	 * setter pour le medecin traitant du client
	 * 
	 * @param medecin : {@link Medecin} traitant du client
	 * @throws AppException : aucun medecin selectionne
	 */
	public void setMedecin(Medecin medecin) throws AppException {
		if (medecin == null)
			throw new AppException(
					"Une ordonnance est forcement lie a un medecin");
		this.medecin = medecin;
	}

	/**
	 * getter pour le specialiste qui a ecrit l'ordonnance si il y a
	 * 
	 * @return le {@link Specialiste} qui a ecrit l'ordonnance
	 */
	public Specialiste getSpecialiste() {
		return specialiste;
	}

	/**
	 * setter pour le pecialiste qui a ecrit l'ordonnance
	 * 
	 * @param specialiste : {@link Specialiste} qui a ecrit l'ordonnance
	 */
	public void setSpecialiste(Specialiste specialiste) {
		this.specialiste = specialiste;
	}

	/**
	 * Constructeur de la classe ordonnance
	 * 
	 * @param acheteur : {@link Client} qui a achete l'ordonnance
	 * @param medecin  : medecin traitant du client
	 * @throws AppException : erreurs de l'utilisateur
	 */
	public Ordonnance(Client acheteur, Medecin medecin) throws AppException {
		super(acheteur);
		this.setMedecin(medecin);
		medecin.ajouterOrdonnance(this);
	}

	/**
	 * reduction du prix en fonction du taux de remboursement de la mutuelle du
	 * client
	 * 
	 * @param prix : prix du medicament
	 * @return prix du medicament reduit
	 */
	private double reductionMutuelle(double prix) {
		double prixReduction = ((100
				- this.getAcheteur().getMutuelle().getRemboursement()) * prix)
				/ 100;
		return prixReduction;
	}

	/**
	 * setter pour les medicaments a ajouter
	 * 
	 * @param medicament : medicament a ajouter
	 * @throws AppException : erreur dans le medicament a ajouter
	 */
	@Override
	public void setMedicaments(Medicament medicament) throws AppException {
		if (medicament == null)
			throw new AppException("Il faut ajouter " + "un medicament");
		Medicament medicamentAchete = new Medicament(medicament.getNom(),
				medicament.getCategorie(),
				this.reductionMutuelle(medicament.getPrix()),
				medicament.getStock(),
				medicament.getDateCirculation(),
				medicament.getPharma());
		this.getMedicaments().add(medicamentAchete);
	}

	/**
	 * Transformer l'ordonnance en {@link String}
	 */
	@Override
	public String toString() {
		String temp = "";
		if (this.specialiste == null)
			temp = "Medecin = " + this.getMedecin() + "\n" + "\n"
					+ super.toString();
		else
			temp = "Medecin = " + this.getMedecin() + "\n" + "Specialiste = "
					+ this.getSpecialiste() + "\n" + super.toString();
		return temp;
	}

}
