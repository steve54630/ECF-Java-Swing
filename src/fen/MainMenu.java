package fen;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import classes.Client;
import classes.Medecin;
import classes.Mutuelle;
import exception.AppException;
import main.App;
import utilitaires.Utilitaires;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

/**
 * Fenetre pour le menu principal
 * 
 * @author SRet
 */
public class MainMenu extends JFrame {

	/**
	 * identifiant pour "serialize" un objet
	 */
	private static final long serialVersionUID = 2309255008830717969L;
	/**
	 * JPanel de la fenetre
	 */
	private JPanel contentPane;

	/**
	 * Constructeur de la phrase MainMenu.
	 */
	public MainMenu() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				MainMenu.class.getResource("/main/resources/sparadrap.jpg")));
		setTitle("Logiciel Gestion Sparadrap");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 304, 413);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Accueil");
		lblNewLabel.setIcon(new ImageIcon(
				MainMenu.class.getResource("/main/resources/Icon.jpg")));
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 29));
		lblNewLabel.setBounds(63, 11, 141, 96);
		contentPane.add(lblNewLabel);

		JButton btnAchat = new JButton("Achat");
		btnAchat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					btnAchatClick();
				} catch (AppException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnAchat.setBounds(95, 133, 89, 23);
		contentPane.add(btnAchat);

		JButton btnHistoriqueOrdonnace = new JButton(
				"Historique des ordonnances");
		btnHistoriqueOrdonnace.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnHistoriqueOrdonnanceClick();
			}
		});
		btnHistoriqueOrdonnace.setBounds(36, 211, 212, 23);
		contentPane.add(btnHistoriqueOrdonnace);

		JButton btnNewButton_2 = new JButton("Quitter");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				System.exit(0);
			}
		});
		btnNewButton_2.setBounds(95, 318, 89, 23);
		contentPane.add(btnNewButton_2);

		JButton btnDetailClient = new JButton("Détail des clients");
		btnDetailClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnDetailClientClick();
			}
		});
		btnDetailClient.setBounds(71, 245, 133, 23);
		contentPane.add(btnDetailClient);

		JButton btnHistoriqueAchat = new JButton("Historique des achats");
		btnHistoriqueAchat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				HistoriqueAchat fen = new HistoriqueAchat();
				fen.setVisible(true);
			}
		});
		btnHistoriqueAchat.setBounds(52, 168, 183, 23);
		contentPane.add(btnHistoriqueAchat);

		JButton btnNewButton = new JButton("Ajouter Client");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				AjouterClient fen = new AjouterClient();
				fen.setVisible(true);
			}
		});
		btnNewButton.setBounds(71, 279, 133, 23);
		contentPane.add(btnNewButton);
	}

	/**
	 * Effet du bouton Historique ordonnances
	 */
	private void btnHistoriqueOrdonnanceClick() {
		Medecin medecinChoix = Utilitaires.choixMedecin();
		if (medecinChoix != null) {
			dispose();
			HistoriqueOrdonnance fen = new HistoriqueOrdonnance(medecinChoix);
			fen.setVisible(true);
		}
	}

	/**
	 * Effet du bouton Detail des clients : 1) ouvre une fenetre qui permet
	 * d'afficher les clients par mutuelle ou par nom 2) Si afficher par
	 * mutuelle, choix de la mutuelle
	 */
	private void btnDetailClientClick() {
		try {
			JList<String> list = new JList<>(new String[] {
					"Liste Client pharmacie", "Client par mutuelle" });
			JOptionPane.showMessageDialog(this, list, "Choisi une option",
					JOptionPane.PLAIN_MESSAGE);
			if (list.getSelectedValue() != null) {
				if (list.isSelectedIndex(0)) {
					dispose();
					DetailClientNom fen = new DetailClientNom();
					fen.setVisible(true);
				} else {
					ArrayList<Mutuelle> mutuelles = new ArrayList<>();
					for (Mutuelle mutuelle : App.pharma.getMutuelles())
						mutuelles.add(mutuelle);
					JList<Object> listeMutuelle = new JList<>(
							mutuelles.toArray());
					JOptionPane.showMessageDialog(this, listeMutuelle,
							"Choisi une option", JOptionPane.PLAIN_MESSAGE);
					if (listeMutuelle.getSelectedValue() != null) {
						dispose();
						DetailClientMutuelle fen = new DetailClientMutuelle(
								(Mutuelle) listeMutuelle.getSelectedValue());
						fen.setVisible(true);
					}
				}
			}
		} catch (NullPointerException e) {
		}

	}

	/**
	 * Effet du bouton achat : 1) choix du type d'achat 2) choix du client
	 * 
	 * @throws AppException : erreur dans les saisis de nom
	 */
	private void btnAchatClick() throws AppException {

		JList<String> list = new JList<>(new String[] {
				"Achat sans ordonnances", "Achat avec ordonnance" });
		JOptionPane.showMessageDialog(this, list, "Choix type achat",
					JOptionPane.PLAIN_MESSAGE);
		Client clientChoix = null;
		if (list.isSelectedIndex(0)) {
			clientChoix = Utilitaires.choixClient(this);
			if (clientChoix != null) {
				dispose();
				AchatDirect fen = new AchatDirect(clientChoix);
				fen.setVisible(true);
			}
		} else if (list.isSelectedIndex(1)) {
			clientChoix = Utilitaires.choixClient(this);
			if (clientChoix != null) {
				dispose();
				AchatOrdonnance fen = new AchatOrdonnance(clientChoix);
				fen.setVisible(true);
			}
		}
	}
}
