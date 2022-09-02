package fen;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import classes.Achat;
import static main.App.*;
import utilitaires.AchatTableModel;
import utilitaires.Utilitaires;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import static main.App.getPharma;

import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextArea;
import java.awt.SystemColor;
import java.awt.Font;

/**
 * Fenetre pour l'historique des achats
 * 
 * @author SRet
 */
public class HistoriqueAchat extends JFrame {

	/**
	 * identifiant pour "serialize" un objet
	 */
	private static final long serialVersionUID = -4444040350772303512L;
	/**
	 * JPanel de la fenetre
	 */
	private JPanel contentPane;
	/**
	 * Model de la JTable
	 */
	private AchatTableModel model = new AchatTableModel();
	/**
	 * JTable contenant les achats realises dans la pharmacie
	 */
	private final JTable table = new JTable(model);

	/**
	 * Constructeur de la fenetre HistoriqueAchat
	 */
	public HistoriqueAchat() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(HistoriqueAchat.class
				.getResource("/main/resources/sparadrap.jpg")));
		setTitle("Historique des achats");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 526, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(43, 21, 417, 263);
		contentPane.add(scrollPane);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					try {
					Utilitaires.afficherAchat	
					(getPharma().getAchats().get(table.getSelectedRow()));
					} catch (NullPointerException NPE) {
					} catch (IndexOutOfBoundsException IOBE) {
					}
				}
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
		btnRetour.setBounds(27, 313, 75, 37);
		contentPane.add(btnRetour);

		JButton btnQuitter = new JButton("Quitter");
		btnQuitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				System.exit(0);
			}
		});
		btnQuitter.setBounds(409, 313, 75, 37);
		contentPane.add(btnQuitter);
		
		JTextArea txtrCliqueFois_1 = new JTextArea();
		txtrCliqueFois_1.setText("Clique 2 fois sur un achat\r\npour en afficher les détails");
		txtrCliqueFois_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtrCliqueFois_1.setEditable(false);
		txtrCliqueFois_1.setBackground(SystemColor.menu);
		txtrCliqueFois_1.setBounds(157, 307, 195, 43);
		contentPane.add(txtrCliqueFois_1);
		for (Achat achat : getPharma().getAchats()) {
			model.addAchat(achat);
		}
	}
}
