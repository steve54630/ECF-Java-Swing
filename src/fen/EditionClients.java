package fen;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import classes.Client;

import static main.App.*;
import utilitaires.ClientTableModel;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
		setBounds(100, 100, 985, 454);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		for (Client client : pharma.getClients())
			model.addClient(client);

		JScrollPane scrollPane = new JScrollPane();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					int choix = JOptionPane.showConfirmDialog(null,
							"Voulez-vous vraiment"
									+ " supprimer le client selectionné?",
							"Effacer un medicament",
							JOptionPane.OK_CANCEL_OPTION);
					if (choix == 0) {
						try {
							pharma.getClients().remove(
									table.getSelectedRow());
							model.removeClient(
									table.getSelectedRow());
							save(pharma, "donnees");
						} catch (NullPointerException NPE) {
					}
					}
				}
			}
		});
		scrollPane.setBounds(39, 24, 920, 317);
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
		btnRetour.setBounds(578, 362, 193, 41);
		contentPane.add(btnRetour);

		JButton btnQuitter = new JButton("Quitter");
		btnQuitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				System.exit(0);
			}
		});
		btnQuitter.setBounds(781, 360, 178, 44);
		contentPane.add(btnQuitter);

		JButton btnDetails = new JButton("Modifier le client sélectionné");
		btnDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnDetailsClick();
			}
		});
		btnDetails.setBounds(10, 360, 223, 44);
		contentPane.add(btnDetails);
		
		JButton btnAjouterClient = new JButton("Ajouter client");
		btnAjouterClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				AjouterClient fen = new AjouterClient();
				fen.setVisible(true);
			}
		});
		btnAjouterClient.setBounds(243, 361, 185, 43);
		contentPane.add(btnAjouterClient);
		
		JLabel lblNewLabel = new JLabel("Double-cliquez pour");
		lblNewLabel.setBounds(438, 362, 130, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("supprimer");
		lblNewLabel_1.setBounds(448, 375, 75, 14);
		contentPane.add(lblNewLabel_1);
	}

	/**
	 * Effet du bouton detail du client
	 */
	private void btnDetailsClick() {
		// TODO Auto-generated method stub
		try {
			dispose();
			ModifierClient fen = new ModifierClient(table.getSelectedRow());
			fen.setVisible(true);
		} catch (NullPointerException e) {
		} catch (IndexOutOfBoundsException e) {
		}
	}
}
