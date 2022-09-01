package fen;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;

import classes.Achat;
import classes.Client;
import classes.Medicament;
import exception.AppException;

import static main.App.*;
import utilitaires.MedicamentTableModel;
import utilitaires.Utilitaires;

import javax.swing.border.BevelBorder;
import java.awt.Color;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Fenetre pour un achat sans ordonnance
 * 
 * @author SRet
 */
public class AchatDirect extends JFrame {

	/**
	 * identifiant pour "serialize" un objet
	 */
	private static final long serialVersionUID = 568535321018598637L;
	/**
	 * JPanel de la fenetre
	 */
	private JPanel contentPane;
	/**
	 * modele de la JTable de la fenetre
	 */
	private MedicamentTableModel model = new MedicamentTableModel();
	/**
	 * JTable qui contient les medicaments achetes
	 */
	private final JTable listeProduitsAchetes = new JTable(model);
	/**
	 * achat cree par les choix de l'utilisateur dans la fenetre
	 */
	private Achat nouvelAchat;

	/**
	 * Constructeur de la fenetre AchatDirect
	 * 
	 * @param client : client qui achete
	 * @throws AppException : erreur dans la saisi du client
	 */
	public AchatDirect(Client client) throws AppException {
		setIconImage(Toolkit.getDefaultToolkit().getImage(AchatDirect.class
				.getResource("/main/resources/sparadrap.jpg")));
		setResizable(false);
		nouvelAchat = new Achat(client);
		setTitle(" Achat sans ordonnance pour " + client.getNom() + " "
				+ client.getPrenom());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 623, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Double-cliquez sur l'élément voulu");
		lblNewLabel.setBounds(208, 353, 205, 14);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("pour le supprimer");
		lblNewLabel_1.setBounds(208, 363, 221, 14);
		contentPane.add(lblNewLabel_1);

		JLabel lblPrixTotal = new JLabel(
				"Prix total = " + nouvelAchat.getPrixTotal());
		lblPrixTotal.setBounds(221, 323, 158, 29);
		contentPane.add(lblPrixTotal);

		JScrollPane titreColonnes = new JScrollPane();
		titreColonnes.setBounds(46, 11, 534, 300);
		contentPane.add(titreColonnes);
		listeProduitsAchetes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					int choix = JOptionPane.showConfirmDialog(null,
							"Voulez-vous vraiment"
									+ " supprimer le medicament selectionné?",
							"Effacer un medicament",
							JOptionPane.OK_CANCEL_OPTION);
					if (choix == 0) {
						try {
							nouvelAchat.setPrixTotal(nouvelAchat.getPrixTotal()
									- nouvelAchat.getMedicaments()
											.get(listeProduitsAchetes
													.getSelectedRow())
											.getPrix());
							nouvelAchat.getMedicaments().remove(
									listeProduitsAchetes.getSelectedRow());
							model.removeMedicament(
									listeProduitsAchetes.getSelectedRow());
							lblPrixTotal.setText("Prix total = "
									+ nouvelAchat.getPrixTotal());
						} catch (NullPointerException | AppException NPE) {
					}
					;
				}
				}
			}	
		});
		titreColonnes.setViewportView(listeProduitsAchetes);
		listeProduitsAchetes.setBorder(
				new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		listeProduitsAchetes.setRowSelectionAllowed(true);
		listeProduitsAchetes.setBackground(Color.WHITE);

		JButton btnAjouterMedicament = new JButton("Ajouter medicament");
		btnAjouterMedicament.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					buttonAjouterMedicamentClick(lblPrixTotal);
				} catch (AppException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnAjouterMedicament.setBounds(10, 353, 188, 31);
		contentPane.add(btnAjouterMedicament);

		JButton btnValider = new JButton("Valider");
		btnValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnValiderClick();
			}
		});
		btnValider.setBounds(60, 404, 128, 47);
		contentPane.add(btnValider);

		JButton btnRetour = new JButton("Retour");
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nouvelAchat = null;
				dispose();
				MainMenu fen = new MainMenu();
				fen.setVisible(true);
			}
		});
		btnRetour.setBounds(257, 404, 134, 47);
		contentPane.add(btnRetour);

		JButton btnQuitter = new JButton("Quitter");
		btnQuitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnQuitter.setBounds(461, 404, 119, 47);
		contentPane.add(btnQuitter);

		JButton btnNewButton = new JButton("Afficher achat");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Utilitaires.afficherAchat(nouvelAchat);
			}
		});
		btnNewButton.setBounds(431, 356, 149, 29);
		contentPane.add(btnNewButton);
	}

	// mon code
	/**
	 * effet du bouton valider
	 */
	private void btnValiderClick() {
		if (nouvelAchat.getMedicaments().size() != 0) {
			getPharma().setAchats(nouvelAchat);
			save(getPharma(), "donnees");
			dispose();
			MainMenu fen = new MainMenu();
			fen.setVisible(true);
		} else
			JOptionPane.showMessageDialog(null,
					"La liste de medicament est vide.", "Erreur",
					JOptionPane.ERROR_MESSAGE);
	}

	/**
	 * effet du bouton ajouter medicament
	 * 
	 * @param label : label a editer
	 * @throws AppException : Erreur dans le medicament
	 */
	private void buttonAjouterMedicamentClick(JLabel label)
			throws AppException {
		Medicament medicamentChoix = null;
		try {
			medicamentChoix = Utilitaires.choixMedicaments();
		} catch (NullPointerException e) {
		}
		if (medicamentChoix != null) {
			nouvelAchat.setMedicaments(medicamentChoix);
			model.addMedicament(medicamentChoix);
			nouvelAchat.setPrixTotal(
					nouvelAchat.getPrixTotal() + medicamentChoix.getPrix());
			label.setText("Prix total = " + nouvelAchat.getPrixTotal());
		}
	}
}
