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
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.time.format.DateTimeParseException;
import java.awt.Toolkit;

/**
 * Fenetre pour ajouter un client
 * 
 * @author SRet
 */
public class ModifierClient extends JFrame {

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
	private JLabel textFieldNom;
	/**
	 * zone de saisi d'un prenom
	 */
	private JLabel textFieldPrenom;
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
	private JLabel textFieldSecuriteSociale;
	/**
	 * zone de saisi de l'email
	 */
	private JTextField textFieldEmail;
	/**
	 * liste des specialistes du client de l'ordonnance
	 */
	private ArrayList<Specialiste> specialistesClient = new ArrayList<>();

	/**
	 * Constructeur de la fenetre AjouterClient
	 * 
	 * @param i
	 */
	public ModifierClient(int i) {
		Client client = pharma.getClients().get(i);
		setIconImage(Toolkit.getDefaultToolkit().getImage(ModifierClient.class
				.getResource("/main/resources/sparadrap.jpg")));
		setTitle("Modifier client");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 301, 763);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textFieldNom = new JLabel();
		textFieldNom.setText(client.getNom());
		textFieldNom.setBounds(115, 23, 117, 20);
		contentPane.add(textFieldNom);

		textFieldPrenom = new JLabel();
		textFieldPrenom.setText(client.getPrenom());
		textFieldPrenom.setBounds(115, 54, 117, 20);
		contentPane.add(textFieldPrenom);

		JLabel lbNom = new JLabel("Nom :");
		lbNom.setBounds(40, 25, 39, 17);
		contentPane.add(lbNom);

		JLabel lblNewLabel = new JLabel("Prenom : ");
		lblNewLabel.setBounds(41, 56, 61, 17);
		contentPane.add(lblNewLabel);

		JLabel lblAdresse = new JLabel("Adresse :");
		lblAdresse.setBounds(100, 85, 61, 28);
		contentPane.add(lblAdresse);

		JLabel lblNumRue = new JLabel("N°Rue");
		lblNumRue.setBounds(10, 124, 46, 14);
		contentPane.add(lblNumRue);

		JLabel lblNomRue = new JLabel("Nom de la rue");
		lblNomRue.setBounds(55, 124, 71, 14);
		contentPane.add(lblNomRue);

		JLabel lblCodePostal = new JLabel("Code Postal");
		lblCodePostal.setBounds(146, 124, 58, 14);
		contentPane.add(lblCodePostal);

		JLabel lblVille = new JLabel("Ville");
		lblVille.setBounds(222, 124, 29, 14);
		contentPane.add(lblVille);

		textFieldNumRue = new JTextField();
		textFieldNumRue.setText(client.getAdresse().getNumero());
		textFieldNumRue.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!Character.isDigit(c)) {
					e.consume();
				}
			}
		});
		textFieldNumRue.setBounds(6, 149, 39, 20);
		contentPane.add(textFieldNumRue);
		textFieldNumRue.setColumns(10);

		textFieldNomRue = new JTextField();
		textFieldNomRue.setText(client.getAdresse().getRue());
		textFieldNomRue.setBounds(53, 149, 86, 20);
		contentPane.add(textFieldNomRue);
		textFieldNomRue.setColumns(10);

		textFieldCodePostal = new JTextField();
		textFieldCodePostal.setText(client.getAdresse().getCodePostal());
		textFieldCodePostal.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!Character.isDigit(c)) {
					e.consume();
				}
			}
		});

		textFieldCodePostal.setBounds(146, 149, 58, 20);
		contentPane.add(textFieldCodePostal);
		textFieldCodePostal.setColumns(10);

		textFieldVille = new JTextField();
		textFieldVille.setText(client.getAdresse().getVille());
		textFieldVille.setBounds(214, 149, 61, 20);
		contentPane.add(textFieldVille);
		textFieldVille.setColumns(10);

		JLabel lblNumTelephone = new JLabel("N° de téléphone");
		lblNumTelephone.setBounds(6, 196, 86, 20);
		contentPane.add(lblNumTelephone);

		textFieldNumTelephone = new JTextField();
		textFieldNumTelephone.setText(client.getNumeroTelephone());
		textFieldNumTelephone.setBounds(115, 196, 160, 20);
		contentPane.add(textFieldNumTelephone);
		textFieldNumTelephone.setColumns(10);

		textFieldSecuriteSociale = new JLabel();
		textFieldSecuriteSociale.setText(client.getSecuriteSociale());
		textFieldSecuriteSociale.setBounds(115, 235, 160, 20);
		contentPane.add(textFieldSecuriteSociale);

		JLabel lblNewLabel_7 = new JLabel("Numéro de \r\n");
		lblNewLabel_7.setBounds(10, 227, 61, 20);
		contentPane.add(lblNewLabel_7);

		JLabel lblEmail = new JLabel("E-mail");
		lblEmail.setBounds(29, 286, 46, 20);
		contentPane.add(lblEmail);

		textFieldEmail = new JTextField();
		textFieldEmail.setText(client.getEmail());
		textFieldEmail.setBounds(75, 286, 200, 20);
		contentPane.add(textFieldEmail);
		textFieldEmail.setColumns(10);

		JLabel lblSecuriteSociale = new JLabel("sécurité sociale");
		lblSecuriteSociale.setBounds(10, 241, 97, 14);
		contentPane.add(lblSecuriteSociale);

		JLabel lblDateNaissance = new JLabel(
				"Date de naissance : " + client.dateToString());
		lblDateNaissance.setBounds(10, 338, 265, 14);
		contentPane.add(lblDateNaissance);

		JComboBox<Medecin> comboBoxMedecin = new JComboBox<>();
		for (Medecin medecin : pharma.getMedecins()) {
			comboBoxMedecin.addItem(medecin);
		}
		comboBoxMedecin.setBounds(75, 363, 200, 22);
		contentPane.add(comboBoxMedecin);

		JLabel lblMedecin = new JLabel("Medecin");
		lblMedecin.setBounds(10, 363, 69, 20);
		contentPane.add(lblMedecin);

		JLabel lblMutuelle = new JLabel("Mutuelle");
		lblMutuelle.setBounds(10, 400, 65, 14);
		contentPane.add(lblMutuelle);

		JComboBox<Mutuelle> comboBoxMutuelle = new JComboBox<>();
		for (Mutuelle mutuelle : pharma.getMutuelles()) {
			comboBoxMutuelle.addItem(mutuelle);
		}
		comboBoxMutuelle.setBounds(75, 396, 200, 22);
		contentPane.add(comboBoxMutuelle);

		JButton btnValider = new JButton("Valider");
		btnValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Adresse newAdresse = new Adresse(textFieldNumRue.getText(),
							textFieldNomRue.getText(),
							textFieldCodePostal.getText(),
							textFieldVille.getText());
					client.setEmail(textFieldEmail.getText());
					client.setAdresse(newAdresse);
					client.setNumeroTelephone(textFieldNumTelephone.getText());
					client.setMedecin(
							(Medecin) comboBoxMedecin.getSelectedItem());
					save(pharma, "donnees");
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
				}
			}
		});

		btnValider.setBounds(10, 677, 89, 23);
		contentPane.add(btnValider);

		JButton btnRetour = new JButton("Retour");
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				EditionClients fen = new EditionClients();
				fen.setVisible(true);
			}
		});
		btnRetour.setBounds(171, 677, 89, 23);
		contentPane.add(btnRetour);

		JLabel lblNewLabel_1 = new JLabel("Specialiste");
		lblNewLabel_1.setBounds(122, 429, 129, 14);
		contentPane.add(lblNewLabel_1);

		JComboBox<Specialiste> comboBoxSpecialiste = new JComboBox<>();
		for (Specialiste specialiste : pharma.getSpecialiste()) {
			comboBoxSpecialiste.addItem(specialiste);
		}
		comboBoxSpecialiste.setBounds(10, 454, 265, 22);
		contentPane.add(comboBoxSpecialiste);

		JButton btnAjouterSpecialiste = new JButton("Ajouter specialiste");
		btnAjouterSpecialiste.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!specialistesClient
						.contains(comboBoxSpecialiste.getSelectedItem()))
					client.getSpecialiste().add((Specialiste) comboBoxSpecialiste
							.getSelectedItem());
			}
		});
		btnAjouterSpecialiste.setBounds(40, 487, 211, 23);
		contentPane.add(btnAjouterSpecialiste);
	}
}