package fen;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import classes.Medecin;
import classes.Ordonnance;

import javax.swing.JTable;

import utilitaires.OrdonnanceTableModel;
import utilitaires.Utilitaires;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

/**
 * Fenetre de l'historique des ordonnances
 * 
 * @author SRet
 */
public class HistoriqueOrdonnance extends JFrame {

	/**
	 * identifiant pour "serialize" un objet
	 */
	private static final long serialVersionUID = -2442611901817489811L;
	/**
	 * JPanel de la fenetre
	 */
	private JPanel contentPane;
	/**
	 * Model de la JTable
	 */
	private OrdonnanceTableModel model = new OrdonnanceTableModel();
	/**
	 * Table qui contient les differentes ordonnances rediges par un medecin
	 */
	private final JTable table = new JTable(model);

	/**
	 * Constructeur de la fenetre HistoriqueOrdonnance
	 * 
	 * @param medecin : medecin qui a redige les ordonnances
	 */
	public HistoriqueOrdonnance(Medecin medecin) {
		setIconImage(
				Toolkit.getDefaultToolkit().getImage(HistoriqueOrdonnance.class
						.getResource("/main/resources/sparadrap.jpg")));
		setTitle("Ordonnances écrites par " + medecin.getNom() + " "
				+ medecin.getPrenom());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 557, 333);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(57, 11, 423, 216);
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
		btnRetour.setBounds(38, 238, 111, 43);
		contentPane.add(btnRetour);

		JButton btnQuitter = new JButton("Quitter");
		btnQuitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnQuitter.setBounds(411, 236, 97, 47);
		contentPane.add(btnQuitter);

		JButton btnDetails = new JButton("Détails");
		btnDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Utilitaires.afficherOrdonnance
					(medecin.getOrdonnancesPrescrites()
							.get(table.getSelectedRow()));
				} catch (NullPointerException NPE) {
				} catch (IndexOutOfBoundsException IOBE) {
				}
			}
		});
		btnDetails.setBounds(237, 236, 89, 47);
		contentPane.add(btnDetails);
		for (Ordonnance ordonnance : medecin.getOrdonnancesPrescrites()) {
			model.addOrdonnance(ordonnance);
		}

	}

}
