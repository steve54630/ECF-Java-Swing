package utilitaires;

import classes.Client;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/** Modele de JTable pour ajouter des achats de la pharmacie a la table
 * @author SRet
 *
 */
public class ClientTableModel extends AbstractTableModel {

	/**
	 * Index pour "serialiser" un objet
	 */
	private static final long serialVersionUID = 914614833245792898L;

	/**
	 * Nom des colonnes de la table
	 */
	private String[] columnNames = { "Nom", "Prenom", "Telephone",
			"N°Securité Sociale", "Medecin traitant", "Mutuelle" };

	/**
	 * Liste des clients contenus dans la table
	 */
	private List<Client> clients;
	
	public List<Client> getClients() {
		return clients;
	}

	/**
	 * Constructeur du modele de la table
	 */
	public ClientTableModel() {
		super();
		clients = new ArrayList<Client>();
	}

	/**
	 *  methode pour recuperer le nombre de lignes de la table
	 */
	@Override
	public int getRowCount() {
		return clients.size();
	}

	/**
	 * methode pour recuperer le nombre de colonnes de la table
	 */
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
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

		Client client = clients.get(rowIndex);

		switch (columnIndex) {
		case 0:
			return client.getNom();
		case 1:
			return client.getPrenom();
		case 2:
			return client.getNumeroTelephone();
		case 3:
			return client.getSecuriteSociale();
		case 4:
			return client.getMedecin().getNom() + " "
					+ client.getMedecin().getPrenom();
		case 5:
			return client.getMutuelle();
		default:
			return "";
		}
	}

	/** methode pour ajouter des clients a la table
	 * @param client : client a ajouter
	 */
	public void addClient(Client client) {
		insertClient(getRowCount(), client);
	}

	/** Methode pour inserer un client a la derniere ligne du tableau
	 * @param rowCount : derniere position du tableau
	 * @param client : cleint a ajouter
	 */
	private void insertClient(int rowCount, Client client) {
		// TODO Auto-generated method stub
		clients.add(rowCount, client);
		fireTableRowsInserted(rowCount, rowCount);
	}

	public Client getClient(int choix)
	{
		return clients.get(choix);
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

	public void removeClient(int row) {
		// TODO Auto-generated method stub
		clients.remove(row);
		fireTableRowsDeleted(row, row);
	}

}
