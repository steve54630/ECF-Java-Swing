package fen;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableRowSorter;

import classes.Client;
import classes.Mutuelle;
import main.App;

import static main.App.*;
import utilitaires.ClientTableModel;

import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JComboBox;


public class EditionClients extends JFrame {

	/**
	 * identifiant pour "serialize" un objet
	 */
	private static final long serialVersionUID = -829873087974612830L;
	/**
	 * JPanel de la fenetre
	 */
	private JPanel contentPane;
	/**
	 * Model de la Jtable
	 */
	private ClientTableModel model = new ClientTableModel();
	/**
	 * JTable ou sont afficher les clients de la pharmacie
	 */
	private final JTable table = new JTable(model);

	/**
	 * Constructeur de la fenetre DetailClientNom
	 */
	public EditionClients() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(EditionClients.class
				.getResource("/main/resources/sparadrap.jpg")));
		setTitle("Edition des clients");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 957, 489);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		RowSorter<ClientTableModel> sort = new TableRowSorter<>(model);
		table.setRowSorter(sort);

		for (Client client : getPharma().getClients())
			model.addClient(client);

		JScrollPane scrollPane = new JScrollPane();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == 1 && e.getClickCount() == 2) {
					dispose();
					FenClient fen = new FenClient(
							model.getClient(table.getSelectedRow()), false);
					fen.setVisible(true);
				}
				if (e.getButton() == 3) {
					int r = table.rowAtPoint(e.getPoint());
					if (r >= 0 && r < table.getRowCount()) {
						table.setRowSelectionInterval(r, r);
					}
					editerClient();
				}
			}
		});
		scrollPane.setBounds(10, 24, 920, 317);
		contentPane.add(scrollPane);
		scrollPane.setViewportView(table);

		JButton btnRetour = new JButton("Retour");
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				MainMenu fen = new MainMenu();
				fen.setVisible(true);
			}
		});
		btnRetour.setBounds(461, 423, 193, 18);
		contentPane.add(btnRetour);

		JButton btnQuitter = new JButton("Quitter");
		btnQuitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				System.exit(0);
			}
		});
		btnQuitter.setBounds(722, 423, 193, 18);
		contentPane.add(btnQuitter);

		JButton btnAjouterClient = new JButton("Ajouter client");
		btnAjouterClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Client nouveauClient = new Client();
				dispose();
				FenClient fen = new FenClient(nouveauClient, true);
				fen.setVisible(true);
			}
		});
		btnAjouterClient.setBounds(10, 352, 364, 18);
		contentPane.add(btnAjouterClient);

		JLabel lblNewLabel = new JLabel(
				"Double-cliquez pour afficher le client séléctionné");
		lblNewLabel.setBounds(10, 412, 315, 14);
		contentPane.add(lblNewLabel);

		JButton btnNewButton = new JButton("Supprimer le client selectionné");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnSupprimerClick(table.getSelectedRow());
			}
		});
		btnNewButton.setBounds(10, 381, 364, 23);
		contentPane.add(btnNewButton);

		JLabel lblNewLabel_1 = new JLabel(
				"Clic droit pour editer le client selectionné");
		lblNewLabel_1.setBounds(10, 425, 279, 14);
		contentPane.add(lblNewLabel_1);

		JComboBox<String> comboBox = new JComboBox<>();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBox.getSelectedItem() == "Nom") {
					String choix = JOptionPane.showInputDialog(null,
							"Entre les premières lettres du nom", "Tri par nom",
							JOptionPane.QUESTION_MESSAGE);
					if (choix != null) {
						for (int i = model.getRowCount() - 1; i > -1; i--) {
							model.removeClient(i);
						}
						if (!choix.isBlank()) {
							char[] arr = choix.toCharArray();
							arr[0] = Character.toUpperCase(arr[0]);
							choix = new String(arr);
						}
						for (Client client : getPharma().getClients())
							if (client.getNom().startsWith(choix)) {
								model.addClient(client);
							}
					}
				}
				if (comboBox.getSelectedItem() == "Mutuelle") {
					ArrayList<Mutuelle> mutuelles = new ArrayList<>();
					for (Mutuelle mutuelle : App.getPharma().getMutuelles())
						mutuelles.add(mutuelle);
					JList<Object> listeMutuelle = new JList<>(
							mutuelles.toArray());
					JOptionPane.showMessageDialog(null, listeMutuelle,
							"Choisi une option", JOptionPane.PLAIN_MESSAGE);
					if (listeMutuelle.getSelectedValue() != null) {
						for (int i = model.getRowCount() - 1; i > -1; i--) {
							model.removeClient(i);
						}
						for (Client client : getPharma().getClients())
							if (client.getMutuelle() == listeMutuelle
									.getSelectedValue()) {
								model.addClient(client);
							}
					}
				}
			}
		});
		comboBox.addItem("");
		comboBox.addItem("Nom");
		comboBox.addItem("Mutuelle");
		comboBox.setBounds(461, 368, 173, 22);
		contentPane.add(comboBox);

		JLabel lblNewLabel_2 = new JLabel("Filtre de la table");
		lblNewLabel_2.setBounds(461, 352, 173, 14);
		contentPane.add(lblNewLabel_2);

		JButton btnAfficherTousClients = new JButton(
				"Afficher tous les clients");
		btnAfficherTousClients.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					for (int i = model.getRowCount() - 1; i > -1; i--) {
						model.removeClient(i);}
						for (Client client : getPharma().getClients())
						{model.addClient(client);}
					}
		});
		btnAfficherTousClients.setBounds(677, 368, 238, 23);
		contentPane.add(btnAfficherTousClients);
	}

	/**
	 * Effet du bouton Supprimer
	 * 
	 * @param i : ligne a supprimer
	 */
	protected void btnSupprimerClick(int i) {
		// TODO Auto-generated method stub
		int choix = JOptionPane.showConfirmDialog(null,
				"Voulez-vous vraiment" + " supprimer le client selectionné?",
				"Effacer un medicament", JOptionPane.OK_CANCEL_OPTION);
		if (choix == 0) {
			try {
				// supprimer le client de la pharmacie
				getPharma().getClients().remove(i);
				// supprimer le client de la liste
				model.removeClient(i);
				save(getPharma(), "donnees");
			} catch (NullPointerException NPE) {
			}
		}
	}

	/**
	 * Effet du bouton detail du client
	 */
	private void editerClient() {
		// TODO Auto-generated method stub
		try {
			dispose();
			FenClient fen = new FenClient(
					model.getClient(table.getSelectedRow()), true);
			fen.setVisible(true);
		} catch (NullPointerException e) {
		} catch (IndexOutOfBoundsException e) {
		}
	}
}
