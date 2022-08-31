package fen;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import classes.Client;
import classes.Mutuelle;

import static main.App.*;
import utilitaires.ClientTableModel;
import utilitaires.Utilitaires;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

/**
 * Fenetre pour la liste des clients d'une mutuelle
 * 
 * @author SRet
 */
public class DetailClientMutuelle extends JFrame {

	/**
	 * identifiant pour "serialize" un objet
	 */
	private static final long serialVersionUID = -829873087974612830L;
	/**
	 * JPanel de la fenetre
	 */
	private JPanel contentPane;
	/**
	 * Model de la JTable
	 */
	private ClientTableModel model = new ClientTableModel();
	/**
	 * Jtable contenant les clients de la mutelle
	 */
	private final JTable table = new JTable(model);

	/**
	 * Constructeur de la fenetre DetailClientMutuelle
	 * 
	 * @param mutuelle : mutuelle a afficher
	 */
	public DetailClientMutuelle(Mutuelle mutuelle) {
		setIconImage(
				Toolkit.getDefaultToolkit().getImage(DetailClientMutuelle.class
						.getResource("/main/resources/sparadrap.jpg")));
		setTitle("Détail des clients par mutuelle");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 951, 454);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		for (Client client : pharma.getClients())
			if (client.getMutuelle() == mutuelle) {
				model.addClient(client);
			}

		JScrollPane scrollPane = new JScrollPane();
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
		btnRetour.setBounds(371, 362, 193, 41);
		contentPane.add(btnRetour);

		JButton btnQuitter = new JButton("Quitter");
		btnQuitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				System.exit(0);
			}
		});
		btnQuitter.setBounds(706, 360, 178, 44);
		contentPane.add(btnQuitter);

		JButton btnDetails = new JButton("Détails du client sélectionné");
		btnDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Utilitaires.afficherClient
					(mutuelle.getClients().get(table.getSelectedRow()));
				} catch (NullPointerException npe) {
				} catch (IndexOutOfBoundsException iobe) {
				}
			}
		});
		btnDetails.setBounds(30, 360, 193, 44);
		contentPane.add(btnDetails);
	}

}
