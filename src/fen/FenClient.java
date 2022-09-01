package fen;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import static main.App.*;

import classes.Adresse;
import classes.Client;
import classes.Medecin;
import classes.Mutuelle;
import classes.Specialiste;
import exception.AppException;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.time.format.DateTimeParseException;
import java.awt.Toolkit;
import java.awt.Color;

/**
 * Fenetre pour ajouter un client
 * 
 * @author SRet
 */
public class FenClient extends JFrame {

	/**
	 * identifiant pour "serialize" un objet
	 */
	private static final long serialVersionUID = -2497113582364940757L;
	/**
	 * JPanel de la fenetre
	 */
	private JPanel contentPane;
	/**
	 * zone de saisi d'un nom
	 */
	private JTextField textFieldNom;
	/**
	 * zone de saisi d'un prenom
	 */
	private JTextField textFieldPrenom;
	/**
	 * zone de saisi d'un numero de rue
	 */
	private JTextField textFieldNumRue;
	/**
	 * zone de saisi d'un nom de rue
	 */
	private JTextField textFieldNomRue;
	/**
	 * zone de saisi du code postal
	 */
	private JTextField textFieldCodePostal;
	/**
	 * zone de saisi d'un nom de ville
	 */
	private JTextField textFieldVille;
	/**
	 * zone de saisi d'un numero de telephone
	 */
	private JTextField textFieldNumTelephone;
	/**
	 * zone de saisi du numero de securite sociale
	 */
	private JTextField textFieldSecuriteSociale;
	/**
	 * zone de saisi de l'email
	 */
	private JTextField textFieldEmail;
	/**
	 * zone de saisi du jour de naissance
	 */
	private JTextField textJour;
	/**
	 * zone de saisi du mois de naissance
	 */
	private JTextField textMois;
	/**
	 * zone de saisi de l'annee de naissance
	 */
	private JTextField textAnnee;

	/**
	 * Constructeur de la fenetre AjouterClient
	 */
	public FenClient(Client client, boolean editer) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				FenClient.class.getResource("/main/resources/sparadrap.jpg")));
		if (client.getNom() == "") {
			setTitle("Nouveau client");
		}
		if (editer && client.getNom() != "")
			setTitle("Edition du client");
		if (!editer && client.getNom() != "")
			setTitle("Detail du client");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 562, 746);
		if (!editer) setBounds(100, 100, 562, 655);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textFieldNom = new JTextField();
		textFieldNom.setText(client.getNom());
		if (client.getNom() != "") {
			textFieldNom.setEditable(false);
		}
		textFieldNom.setBounds(218, 23, 117, 20);
		contentPane.add(textFieldNom);
		textFieldNom.setColumns(10);

		textFieldPrenom = new JTextField();
		textFieldPrenom.setText(client.getPrenom());
		if (client.getNom() != "") {
			textFieldPrenom.setEditable(false);
		}
		textFieldPrenom.setBounds(218, 54, 117, 20);
		contentPane.add(textFieldPrenom);
		textFieldPrenom.setColumns(10);

		JLabel lbNom = new JLabel("Nom :");
		lbNom.setBounds(151, 25, 39, 17);
		contentPane.add(lbNom);

		JLabel lblNewLabel = new JLabel("Prenom : ");
		lblNewLabel.setBounds(147, 56, 61, 17);
		contentPane.add(lblNewLabel);

		JLabel lblAdresse = new JLabel("Adresse :");
		lblAdresse.setBounds(228, 85, 61, 28);
		contentPane.add(lblAdresse);

		JLabel lblNumRue = new JLabel("N°Rue");
		lblNumRue.setBounds(46, 124, 46, 14);
		contentPane.add(lblNumRue);

		JLabel lblNomRue = new JLabel("Nom de la rue");
		lblNomRue.setBounds(146, 124, 71, 14);
		contentPane.add(lblNomRue);

		JLabel lblCodePostal = new JLabel("Code Postal");
		lblCodePostal.setBounds(277, 124, 58, 14);
		contentPane.add(lblCodePostal);

		JLabel lblVille = new JLabel("Ville");
		lblVille.setBounds(427, 124, 29, 14);
		contentPane.add(lblVille);

		textFieldNumRue = new JTextField();
		if (client.getNom() != "")
			textFieldNumRue.setText(client.getAdresse().getNumero());
		textFieldNumRue.setEditable(editer);
		textFieldNumRue.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!Character.isDigit(c)) {
					e.consume();
				}
			}
		});
		textFieldNumRue.setBounds(40, 149, 39, 20);
		contentPane.add(textFieldNumRue);
		textFieldNumRue.setColumns(10);

		textFieldNomRue = new JTextField();
		if (client.getNom() != "")
			textFieldNomRue.setText(client.getAdresse().getRue());
		textFieldNomRue.setEditable(editer);
		textFieldNomRue.setBounds(115, 149, 152, 20);
		contentPane.add(textFieldNomRue);
		textFieldNomRue.setColumns(10);

		textFieldCodePostal = new JTextField();
		if (client.getNom() != "")
			textFieldCodePostal.setText(client.getAdresse().getCodePostal());
		textFieldCodePostal.setEditable(editer);
		textFieldCodePostal.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!Character.isDigit(c)) {
					e.consume();
				}
			}
		});

		textFieldCodePostal.setBounds(277, 149, 58, 20);
		contentPane.add(textFieldCodePostal);
		textFieldCodePostal.setColumns(10);

		textFieldVille = new JTextField();
		if (client.getNom() != "")
			textFieldVille.setText(client.getAdresse().getVille());
		textFieldVille.setEditable(editer);
		textFieldVille.setBounds(356, 149, 164, 20);
		contentPane.add(textFieldVille);
		textFieldVille.setColumns(10);

		JLabel lblNumTelephone = new JLabel("N° de téléphone");
		lblNumTelephone.setBounds(93, 196, 86, 20);
		contentPane.add(lblNumTelephone);

		textFieldNumTelephone = new JTextField();
		if (client.getNom() != "")
			textFieldNumTelephone.setText(client.getNumeroTelephone());
		textFieldNumTelephone.setEditable(editer);
		textFieldNumTelephone.setBounds(189, 196, 160, 20);
		contentPane.add(textFieldNumTelephone);
		textFieldNumTelephone.setColumns(10);

		textFieldSecuriteSociale = new JTextField();
		if (client.getNom() != "") {
			textFieldSecuriteSociale.setText(client.getSecuriteSociale());
		}
		if (client.getNom() != "") {
			textFieldSecuriteSociale.setEditable(false);
		}
		textFieldSecuriteSociale.setBounds(189, 227, 160, 20);
		contentPane.add(textFieldSecuriteSociale);
		textFieldSecuriteSociale.setColumns(10);

		JLabel lblNewLabel_7 = new JLabel("Numéro de \r\n");
		lblNewLabel_7.setBounds(93, 227, 61, 20);
		contentPane.add(lblNewLabel_7);

		JLabel lblEmail = new JLabel("E-mail");
		lblEmail.setBounds(115, 286, 46, 20);
		contentPane.add(lblEmail);

		textFieldEmail = new JTextField();
		if (client.getNom() != "") {
			textFieldEmail.setText(client.getEmail());
		}
		textFieldEmail.setEditable(editer);
		textFieldEmail.setBounds(189, 286, 200, 20);
		contentPane.add(textFieldEmail);
		textFieldEmail.setColumns(10);

		JLabel lblSecuriteSociale = new JLabel("sécurité sociale");
		lblSecuriteSociale.setBounds(93, 240, 97, 14);
		contentPane.add(lblSecuriteSociale);

		JLabel lblDateNaissance = new JLabel("Date de naissance :");
		lblDateNaissance.setBounds(189, 327, 139, 14);
		contentPane.add(lblDateNaissance);

		textJour = new JTextField();
		if (client.getNom() != "") {
			textJour.setText("" + client.getDateNaissance().getDayOfMonth());
		}
		if (client.getNom() != "") {
			textJour.setEditable(false);
		}
		textJour.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!Character.isDigit(c)) {
					e.consume();
				}
			}
		});
		textJour.setBounds(75, 379, 51, 20);
		contentPane.add(textJour);
		textJour.setColumns(10);

		textMois = new JTextField();
		textMois = new JTextField();
		if (client.getNom() != "") {
			textMois.setText("" + client.getDateNaissance().getMonth());
		}
		if (client.getNom() != "") {
			textMois.setEditable(false);
		}
		textMois.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!Character.isDigit(c)) {
					e.consume();
				}
			}
		});
		textMois.setBounds(189, 379, 61, 20);
		contentPane.add(textMois);
		textMois.setColumns(10);

		textAnnee = new JTextField();
		textAnnee = new JTextField();
		if (client.getNom() != "") {
			textAnnee.setText("" + client.getDateNaissance().getYear());
		}
		if (client.getNom() != "") {
			textAnnee.setEditable(false);
		}
		textAnnee.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!Character.isDigit(c)) {
					e.consume();
				}
			}
		});
		textAnnee.setBounds(315, 379, 86, 20);
		contentPane.add(textAnnee);
		textAnnee.setColumns(10);

		JLabel lblJour = new JLabel("Jour");
		lblJour.setBounds(93, 352, 29, 14);
		contentPane.add(lblJour);

		JLabel lblMois = new JLabel("Mois");
		lblMois.setBounds(203, 352, 29, 14);
		contentPane.add(lblMois);

		JLabel lblAnnee = new JLabel("Année");
		lblAnnee.setBounds(336, 352, 39, 14);
		contentPane.add(lblAnnee);

		JComboBox<Medecin> comboBoxMedecin = new JComboBox<>();
		comboBoxMedecin.setForeground(Color.BLACK);
		comboBoxMedecin.setEnabled(editer);
		for (Medecin medecin : getPharma().getMedecins()) {
			comboBoxMedecin.addItem(medecin);
		}
		comboBoxMedecin.setBounds(171, 424, 200, 22);
		if (!editer)
			comboBoxMedecin.setVisible(false);
		contentPane.add(comboBoxMedecin);

		JLabel lblMedecin = new JLabel("Medecin");
		lblMedecin.setBounds(92, 425, 69, 20);
		contentPane.add(lblMedecin);

		JLabel lblMutuelle = new JLabel("Mutuelle");
		lblMutuelle.setBounds(93, 466, 65, 14);
		contentPane.add(lblMutuelle);

		JComboBox<Mutuelle> comboBoxMutuelle = new JComboBox<>();
		comboBoxMutuelle.setForeground(Color.BLACK);
		comboBoxMutuelle.setEnabled(editer);
		for (Mutuelle mutuelle : getPharma().getMutuelles()) {
			comboBoxMutuelle.addItem(mutuelle);
		}
		comboBoxMutuelle.setBounds(171, 462, 200, 22);
		contentPane.add(comboBoxMutuelle);
		comboBoxMutuelle.setVisible(editer);

		JLabel lblNewLabel_1 = new JLabel("Specialiste");
		lblNewLabel_1.setBounds(189, 525, 129, 14);
		contentPane.add(lblNewLabel_1);

		JComboBox<Specialiste> comboBoxSpecialiste = new JComboBox<>();
		if (editer)
		{for (Specialiste specialiste : getPharma().getSpecialiste())
			comboBoxSpecialiste.addItem(specialiste);}
		else 
		{for (Specialiste specialiste : client.getSpecialiste())
			comboBoxSpecialiste.addItem(specialiste);}
		comboBoxSpecialiste.setBounds(103, 550, 266, 22);
		contentPane.add(comboBoxSpecialiste);

		JButton btnAjouterSpecialiste = new JButton("Ajouter specialiste");
		btnAjouterSpecialiste.setVisible(editer);
		btnAjouterSpecialiste.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!client.getSpecialiste()
						.contains(comboBoxSpecialiste.getSelectedItem()))
					try {
						client.setSpecialiste((Specialiste) comboBoxSpecialiste
								.getSelectedItem());
					} catch (AppException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}
		});
		btnAjouterSpecialiste.setBounds(138, 592, 211, 23);
		contentPane.add(btnAjouterSpecialiste);

		JLabel lblMedecinClient = new JLabel("" + client.getMedecin());
		lblMedecinClient.setBounds(186, 428, 200, 14);
		contentPane.add(lblMedecinClient);
		lblMedecinClient.setVisible(false);
		if (editer == false)
			lblMedecinClient.setVisible(true);

		JLabel lblMutuelleClient = new JLabel("" + client.getMutuelle());
		lblMutuelleClient.setBounds(186, 466, 203, 14);
		contentPane.add(lblMutuelleClient);
		lblMutuelleClient.setVisible(false);
		if (editer == false)
			lblMutuelleClient.setVisible(true);

		JButton btnValider = new JButton("Valider");
		btnValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Medecin medecinChoix = (Medecin) comboBoxMedecin
							.getSelectedItem();
					Mutuelle mutuelleChoix = (Mutuelle) comboBoxMutuelle
							.getSelectedItem();
					Adresse adresseClient = new Adresse(
							textFieldNumRue.getText(),
							textFieldNomRue.getText(),
							textFieldCodePostal.getText(),
							textFieldVille.getText());
					client.setNom(textFieldNom.getText());
					client.setPrenom(textFieldPrenom.getText());
					client.setAdresse(adresseClient);
					client.setNumeroTelephone(textFieldNumTelephone.getText());
					client.setSecuriteSociale(
							textFieldSecuriteSociale.getText());
					client.setEmail(textFieldEmail.getText());
					client.setDateDeNaissance(textAnnee.getText(),
							textMois.getText(), textJour.getText());
					client.setMedecin(medecinChoix);
					client.setMutuelle(mutuelleChoix);
					save(getPharma(), "donnees");
					dispose();
					EditionClients fen = new EditionClients();
					fen.setVisible(true);
				} catch (AppException ae) {
					JOptionPane.showMessageDialog(null, ae.getMessage(),
							"Erreur", JOptionPane.ERROR_MESSAGE);
				} catch (DateTimeParseException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null,
							"Jour = xx, Mois = xx, Annee = xx", "Erreur",
							JOptionPane.ERROR_MESSAGE);
				} catch (NullPointerException e1) {
					JOptionPane.showMessageDialog(null,
							"Aucun client n'est ajouté", "Erreur",
							JOptionPane.ERROR_MESSAGE);
				}
				JLabel lblMutuelleClient = new JLabel(
						"" + client.getMutuelle());
				lblMutuelleClient.setBounds(186, 466, 203, 14);
				contentPane.add(lblMutuelleClient);
				if (editer)
					lblMutuelleClient.setVisible(false);
			}
		});
		btnValider.setBounds(40, 655, 89, 23);
		contentPane.add(btnValider);
		if (!editer)
			btnValider.setVisible(false);

		JButton btnRetour = new JButton("Retour");
		btnRetour.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				dispose();
				EditionClients fen = new EditionClients();
				fen.setVisible(true);
			}
		});
		btnRetour.setBounds(416, 655, 89, 23);
		if (!editer) btnRetour.setBounds(427, 550, 89, 23);
		contentPane.add(btnRetour);

	}
}