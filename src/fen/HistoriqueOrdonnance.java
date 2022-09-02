package fen;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import classes.Medecin;
import classes.Ordonnance;

import static main.App.*;

import javax.swing.JTable;

import utilitaires.OrdonnanceTableModel;
import utilitaires.Utilitaires;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.Color;

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
	 */
	public HistoriqueOrdonnance() {
		setIconImage(
				Toolkit.getDefaultToolkit().getImage(HistoriqueOrdonnance.class
						.getResource("/main/resources/sparadrap.jpg")));
		setTitle("Ordonnances de la pharmacie");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 557, 388);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		for (Ordonnance ordonnance : getPharma().getOrdonnances()) {
			model.addOrdonnance(ordonnance);
		}

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(65, 57, 423, 216);
		contentPane.add(scrollPane);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2)
					try {
						Utilitaires.afficherOrdonnance(
								model.getOrdonnance(table.getSelectedRow()));
					} catch (NullPointerException NPE) {
					} catch (IndexOutOfBoundsException IOBE) {
					}
				;
			}
		});
		scrollPane.setViewportView(table);

		JButton btnRetour = new JButton("Retour");
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				MainMenu fen = new MainMenu();
				fen.setVisible(true);
			}
		});
		btnRetour.setBounds(10, 293, 111, 43);
		contentPane.add(btnRetour);

		JButton btnQuitter = new JButton("Quitter");
		btnQuitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnQuitter.setBounds(434, 291, 97, 47);
		contentPane.add(btnQuitter);

		JLabel lblNewLabel = new JLabel("Choix du medecin");
		lblNewLabel.setBounds(70, 21, 141, 14);
		contentPane.add(lblNewLabel);

		JComboBox<Medecin> comboBox = new JComboBox<>();
		comboBox.addItem(null);
		for (Medecin medecinList : getPharma().getMedecins())
			comboBox.addItem(medecinList);
		comboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (comboBox.getSelectedItem() == null) {
						for (int i = model.getRowCount() - 1; i >= 0; i--) {
							model.removeOrdonnance(i);
						}
						for (Ordonnance ordonnance : getPharma()
								.getOrdonnances())
							model.addOrdonnance(ordonnance);
					}
					if (comboBox.getSelectedItem() == getPharma().getMedecins()
							.get(comboBox.getSelectedIndex() - 1)) {
						for (int i = model.getRowCount() - 1; i >= 0; i--) {
							model.removeOrdonnance(i);
						}
						for (Ordonnance ordonnance : getPharma()
								.getOrdonnances())
							if (ordonnance.getMedecin() == comboBox
									.getSelectedItem())
								model.addOrdonnance(ordonnance);
					}
				} catch (IndexOutOfBoundsException ibe) {
				}
			}
		});
		comboBox.setBounds(221, 17, 267, 22);
		contentPane.add(comboBox);
		
		JTextArea txtrCliqueFois = new JTextArea();
		txtrCliqueFois.setEditable(false);
		txtrCliqueFois.setBackground(new Color(240, 240, 240));
		txtrCliqueFois.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtrCliqueFois.setText("Clique 2 fois sur une ordonnance\r\npour en afficher les détails");
		txtrCliqueFois.setBounds(184, 293, 195, 43);
		contentPane.add(txtrCliqueFois);

	}
}
