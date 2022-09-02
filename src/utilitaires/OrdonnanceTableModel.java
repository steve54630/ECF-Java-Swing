package utilitaires;

import classes.Ordonnance;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/** Modele de JTable pour ajouter des ordonnances de la pharmacie a la table
 * @author SRet
 *
 */
public class OrdonnanceTableModel extends AbstractTableModel {

	/**
	 * Index pour "serialiser" un objet
	 */
	private static final long serialVersionUID = 8520608430072805932L;

	/**
	 * Nom des colonnes de la table
	 */
	private String[] columnNames = { "Patient", "Date", "Prix total"};

	/**
	 * Liste des ordonnances contenus dans la table
	 */
	private List<Ordonnance> ordonnances;

	/**
	 * Constructeur du modele de la table
	 */
	public OrdonnanceTableModel() {
		super();
		ordonnances = new ArrayList<Ordonnance>();
	}

	/**
	 * methode pour recuperer le nombre de lignes de la table
	 */
	@Override
	public int getRowCount() {
		return ordonnances.size();
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

		Ordonnance ordonnance = ordonnances.get(rowIndex);

		switch (columnIndex) {
		case 0:
			return ordonnance.getAcheteur().getNom() + " "
					+ ordonnance.getAcheteur().getPrenom();
		case 1:
			return ordonnance.dateToString();
		case 2:
			return ordonnance.getPrixTotal();
		default:
			return "";
		}
	}

	/**
	 * methode pour ajouter des ordonnances a la table
	 * @param ordonnance : ordonnance a ajouter
	 */
	public void addOrdonnance(Ordonnance ordonnance) {
		insertOrdonnance(getRowCount(), ordonnance);
	}

	/** Methode pour inserer un ordonnance a la derniere ligne du tableau
	 * @param rowCount : ligne du tableau a ajouter
	 * @param ordonnance : ordonnance a ajouter
	 */
	private void insertOrdonnance(int rowCount, Ordonnance ordonnance) {
		// TODO Auto-generated method stub
		ordonnances.add(rowCount, ordonnance);
		fireTableRowsInserted(rowCount, rowCount);
	}

	/**
	 * methode pour definir le type de contenu des cellules
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

	public Ordonnance getOrdonnance(int choix)
	{
		return ordonnances.get(choix);
	}
	
	public void removeOrdonnance(int row) {
		// TODO Auto-generated method stub
		ordonnances.remove(row);
		fireTableRowsDeleted(row, row);
	}
	
}
