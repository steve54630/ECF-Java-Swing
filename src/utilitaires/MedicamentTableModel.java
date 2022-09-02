package utilitaires;

import classes.Medicament;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/** Modele de JTable pour ajouter des medicaments de la pharmacie a la table
 * @author SRet
 *
 */
public class MedicamentTableModel extends AbstractTableModel {

	/**
	 * Index pour "serialiser" un objet
	 */
	private static final long serialVersionUID = -3219729329895697478L;

	/**
	 * Nom des colonnes de la table
	 */
	private String[] columnNames = { "Nom", "Categorie", "Prix (en euros)",
			"Stock" };

	/**
	 * Liste des mdeciaments contenus dans la table
	 */
	private List<Medicament> medicaments;

	/**
	 * Constructeur du modele de la table
	 */
	public MedicamentTableModel() {
		super();
		medicaments = new ArrayList<Medicament>();
	}

	/**
	 * methode pour recuperer le nombre de lignes de la table
	 */
	@Override
	public int getRowCount() {
		return medicaments.size();
	}

	/**
	 * methode pour recuperer le nombre de colonnes de la table
	 */
	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	/**
	 * methode pour recuperer le nom des colonnes
	 */
	@Override
	public String getColumnName(int column) {
		return columnNames[column];
	}

	/**
	 * methode pour remplir les cellules de la JTable en fonction des lignes
	 * et des colonnes
	 */
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {

		Medicament medicament = medicaments.get(rowIndex);

		switch (columnIndex) {
		case 0:
			return medicament.getNom();
		case 1:
			return medicament.getCategorie();
		case 2:
			return medicament.getPrix();
		case 3:
			return medicament.getStock();
		default:
			return "";
		}
	}

	/** methode pour ajouter des medicaments a la table
	 * @param medicament : medicament a ajouter
	 */
	public void addMedicament(Medicament medicament) {
		insertMedicament(getRowCount(), medicament);
	}

	/** Methode pour inserer un medicament a la derniere ligne du tableau
	 * @param rowCount : ligne max du tableau
	 * @param medicament : medicament a ajouter
	 */
	private void insertMedicament(int rowCount, Medicament medicament) {
		// TODO Auto-generated method stub
		medicaments.add(rowCount, medicament);
		fireTableRowsInserted(rowCount, rowCount);
	}

	/** methode pour supprimer une ligne du tableau
	 * @param row : ligne a supprimer
	 */
	public void removeMedicament(int row) {
		medicaments.remove(row);
		fireTableRowsDeleted(row, row);
	}

	/**
	 *  methode pour definir le type de contenu des cellules
	 */
	@Override
	public Class<?> getColumnClass(int column) {
		return String.class;
	}

	/**
	 * methode pour dire si les cellules sont modifiables ou non
	 */
	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}

	public Medicament getMedicament(int selectedRow) {
		// TODO Auto-generated method stub
		return medicaments.get(selectedRow);
	}

}
