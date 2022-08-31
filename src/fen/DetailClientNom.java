package fen;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import classes.Client;
import static main.App.*;
import utilitaires.ClientTableModel;
import utilitaires.Utilitaires;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class DetailClientNom extends JFrame {

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
	public DetailClientNom() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(DetailClientNom.class
				.getResource("/main/resources/sparadrap.jpg")));
		setTitle("Détail des clients");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 985, 454);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		for (Client client : pharma.getClients())
			model.addClient(client);

		JScrollPane scrollPane = new JScrollPane();
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
		btnRetour.setBounds(403, 362, 193, 41);
		contentPane.add(btnRetour);

		JButton btnQuitter = new JButton("Quitter");
		btnQuitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				System.exit(0);
			}
		});
		btnQuitter.setBounds(761, 360, 178, 44);
		contentPane.add(btnQuitter);

		JButton btnDetails = new JButton("Détails du client sélectionné");
		btnDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnDetailsClick();
			}
		});
		btnDetails.setBounds(49, 360, 193, 44);
		contentPane.add(btnDetails);
	}

	/**
	 * 
	 */
	private void btnDetailsClick() {
		// TODO Auto-generated method stub
		try {
			Utilitaires.afficherClient
			(pharma.getClients().get(table.getSelectedRow()));
		} catch (NullPointerException e) {
		} catch (IndexOutOfBoundsException e) {
		}
	}
}
