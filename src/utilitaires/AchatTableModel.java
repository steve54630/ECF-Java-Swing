package utilitaires;

import classes.Achat;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/** Modele de JTable pour ajouter des achats de la pharmacie a la table
 * @author SRet
 *
 */
public class AchatTableModel extends AbstractTableModel {

	/**
	 * Index pour "serialiser" un objet
	 */
	private static final long serialVersionUID = -3684372875315636244L;

	/**
	 * Nom des colonnes de la table
	 */
	private String[] columnNames = { "Client", "Date", "Type d'achat", "Prix total" };

	/**
	 * Liste des achats contenus dans la table
	 */
	private List<Achat> achats;

	/**
	 * Constructeur du modele de la table
	 */
	public AchatTableModel() {
		super();
		// creation du tableau des lignes de la liste
		achats = new ArrayList<Achat>();
	}

	/**
	 * methode pour recuperer le nombre de lignes de la table
	 */
	@Override
	public int getRowCount() {
		// calcule le nombre de lignes du tableau
		return achats.size();
	}

	/**
	 * methode pour recuperer le nombre de colonnes de la table
	 */
	@Override
	public int getColumnCount() {
		// calcule le nombre de colonnes en fonction du tableau des noms des
		// colonnes
		return columnNames.length;
	}

	/**
	 * methode pour recuperer le nom des colonnes
	 */
	@Override
	public String getColumnName(int column) {
		// recupere le nom des colonnes
		return columnNames[column];
	}

	/**
	 * methode pour remplir les cellules de la JTable en fonction des lignes
	 * et des colonnes
	 */
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// explique pour chque ligne comment la remplir en fonction des colonnes

		Achat achat = achats.get(rowIndex);
		String nomClass = achat.getClass().getName();

		switch (columnIndex) {
		case 0:
			return achat.getAcheteur().getNom() + " "
					+ achat.getAcheteur().getPrenom();
		case 1:
			return achat.dateToString();
		case 2:
			return nomClass.replaceAll("classes.", "");
		case 3 :
			return achat.getPrixTotal();
		default:
			return "";
		}
	}

	/** methode pour ajouter des achats a la table
	 * @param achat : achat a ajouter
	 */
	public void addAchat(Achat achat) {
		insertClient(getRowCount(), achat);
	}

	/** Methode pour inserer un achat a la derniere ligne du tableau
	 * @param rowCount : ligne du tableau a ajouter
	 * @param achat : achat a ajouter
	 */
	private void insertClient(int rowCount, Achat achat) {
		// ajoute un achat realise au tableau des achats
		achats.add(rowCount, achat);
		fireTableRowsInserted(rowCount, rowCount);
	}

	/**
	 * methode pour definir le type de contenu des cellules
	 */
	@Override
	public Class<?> getColumnClass(int column) {
		// definit le type de contenu des cases
		return String.class;
	}

	/**
	 * methode pour dire si les cellules sont modifiables ou non
	 */
	@Override
	public boolean isCellEditable(int row, int column) {
		// autorise ou non la modification des cellules
		return false;
	}

}
