package fen;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import classes.Client;
import classes.Medicament;
import classes.Ordonnance;
import classes.Specialiste;
import exception.AppException;

import static main.App.*;
import utilitaires.MedicamentTableModel;
import utilitaires.Utilitaires;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

/**
 * fenetre pour l'achat avec ordonnance
 * 
 * @author SRet
 */
public class AchatOrdonnance extends JFrame {

	/**
	 * identifiant pour "serialize" un objet
	 */
	private static final long serialVersionUID = -8419476072053374950L;
	/**
	 * Jpanel de la fenetre
	 */
	private JPanel contentPane;
	/**
	 * modele de la JtTable
	 */
	private MedicamentTableModel model = new MedicamentTableModel();
	/**
	 * JTable ou s'affiche les medicaments achetes
	 */
	private final JTable listeProduitsAchetes = new JTable(model);
	/**
	 * ordonnance cree par la fenetre
	 */
	private Ordonnance nouvelleOrdonnance;
	/**
	 * Titre de la JTable
	 */
	private JScrollPane scrollPane;

	/**
	 * Constructeur de la fenetre AchatOrdonnance
	 * 
	 * @param client : client qui achete le produit
	 * @throws AppException : erreur dans le client selectionne
	 */
	public AchatOrdonnance(Client client) throws AppException {
		setIconImage(Toolkit.getDefaultToolkit().getImage(AchatOrdonnance.class
				.getResource("/main/resources/sparadrap.jpg")));
		setResizable(false);
		nouvelleOrdonnance = new Ordonnance(client, client.getMedecin());
		setTitle("Ordonnance pour " + client.getNom() + " "
				+ client.getPrenom());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 554, 485);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTuto1 = new JLabel("Double-cliquez sur l'élément voulu");
		lblTuto1.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblTuto1.setBounds(179, 345, 168, 14);
		contentPane.add(lblTuto1);

		JLabel lblTuto2 = new JLabel("pour le supprimer");
		lblTuto2.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblTuto2.setBounds(179, 354, 154, 14);
		contentPane.add(lblTuto2);

		JLabel lblPrixTotal = new JLabel(
				"Prix total = " + nouvelleOrdonnance.getPrixTotal());
		lblPrixTotal.setBounds(210, 309, 158, 29);
		contentPane.add(lblPrixTotal);

		JLabel lblNewLabel = new JLabel(
				"Médecin : " + client.getMedecin().toString());
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(72, 0, 367, 46);
		contentPane.add(lblNewLabel);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(54, 70, 445, 243);
		contentPane.add(scrollPane);
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
							nouvelleOrdonnance.setPrixTotal(nouvelleOrdonnance
									.getPrixTotal()
									- nouvelleOrdonnance.getMedicaments()
											.get(listeProduitsAchetes
													.getSelectedRow())
											.getPrix());
							nouvelleOrdonnance.getMedicaments().remove(
									listeProduitsAchetes.getSelectedRow());
							model.removeMedicament(
									listeProduitsAchetes.getSelectedRow());
							lblPrixTotal.setText("Prix total = "
									+ nouvelleOrdonnance.getPrixTotal());
						} catch (NullPointerException NPE) {
						} catch (IndexOutOfBoundsException IOBE) {
						} catch (AppException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					;
				}
			}
		});
		scrollPane.setViewportView(listeProduitsAchetes);
		final JComboBox<Specialiste> specialisteList = new JComboBox<Specialiste>();
		specialisteList.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				nouvelleOrdonnance.setSpecialiste(
						(Specialiste) specialisteList.getSelectedItem());
			}
		});
		specialisteList.addItem(null);
		for (Specialiste specialiste : client.getSpecialiste()) {
			specialisteList.addItem(specialiste);
		}
		specialisteList.setBounds(159, 37, 340, 22);
		contentPane.add(specialisteList);

		JButton btnAjouterMedicament = new JButton("Ajouter medicament");
		btnAjouterMedicament.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					btnAjouterMedicamentClick(lblPrixTotal);
				} catch (AppException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnAjouterMedicament.setBounds(6, 345, 169, 23);
		contentPane.add(btnAjouterMedicament);

		JLabel lblSpecialiste = new JLabel("Spécialiste :");
		lblSpecialiste.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSpecialiste.setBounds(72, 34, 86, 25);
		contentPane.add(lblSpecialiste);

		JButton btnAfficher = new JButton("Afficher ordonnance");
		btnAfficher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Utilitaires.afficherOrdonnance(nouvelleOrdonnance);
			}
		});
		btnAfficher.setBounds(343, 341, 175, 23);
		contentPane.add(btnAfficher);

		JButton btnValider = new JButton("Valider");
		btnValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnValiderClick();
			}
		});
		btnValider.setBounds(28, 387, 142, 42);
		contentPane.add(btnValider);

		JButton btnRetour = new JButton("Retour");
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nouvelleOrdonnance = null;
				dispose();
				MainMenu fen = new MainMenu();
				fen.setVisible(true);
			}
		});
		btnRetour.setBounds(216, 387, 131, 42);
		contentPane.add(btnRetour);

		JButton btnQuitter = new JButton("Quitter");
		btnQuitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				System.exit(0);
			}
		});
		btnQuitter.setBounds(399, 387, 116, 42);
		contentPane.add(btnQuitter);

	}

	// mon code
	/**
	 * Effet du bouton valider choix specialiste
	 * 
	 * @param choix : choix de l'utilisateur
	 */
	private void btnValiderClick() {
		if (nouvelleOrdonnance.getMedicaments().size() != 0) {
			for (Medicament medicament : getPharma().getMedicaments())
				for (Medicament medicament2 : nouvelleOrdonnance.getMedicaments())
					if (medicament.getNom() == medicament2.getNom())
						try {
							medicament.setStock(medicament.getStock() - 1);
						} catch (AppException e) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(null,
									"Plus aucun medicament n'est disponible en stock",
									"Erreur", JOptionPane.ERROR_MESSAGE);
						};
			getPharma().setAchats(nouvelleOrdonnance);
			getPharma().setOrdonnances(nouvelleOrdonnance);
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
	 * Effet du bouton ajouter un medicament
	 * 
	 * @param label : label a editer
	 * @throws AppException : verifie que le prix du medicament est positif
	 */
	protected void btnAjouterMedicamentClick(JLabel label) throws AppException {
		Medicament medicamentChoix = null;
		try {
			medicamentChoix = Utilitaires.choixMedicaments();
		} catch (NullPointerException e) {
		}
		if (medicamentChoix != null) {
			nouvelleOrdonnance.setMedicaments(medicamentChoix);
			ArrayList<Medicament> medicamentListe = nouvelleOrdonnance.getMedicaments();
			medicamentChoix = medicamentListe.get(medicamentListe.size()-1);
			model.addMedicament(medicamentChoix);
			nouvelleOrdonnance.setPrixTotal(nouvelleOrdonnance.getPrixTotal()
					+ medicamentChoix.getPrix());
			label.setText("Prix total = " + nouvelleOrdonnance.getPrixTotal());
		}
	}
}