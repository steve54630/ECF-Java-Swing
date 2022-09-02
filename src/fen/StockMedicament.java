package fen;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableRowSorter;

import classes.Medicament;
import exception.AppException;
import utilitaires.MedicamentTableModel;

import static main.App.*;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Toolkit;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextArea;
import javax.swing.RowSorter;

import java.awt.Font;
import java.awt.Color;


/** Fenetre pour consulter le stock des medicaments et le faire varier
 * @author SRet
 *
 */
public class StockMedicament extends JFrame {

	private static final long serialVersionUID = -1573127257212012566L;
	private JPanel contentPane;
	private MedicamentTableModel model = new MedicamentTableModel();
	private JTable table;

	/**
	 * Create the frame.
	 */
	public StockMedicament() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(StockMedicament.class
				.getResource("/main/resources/sparadrap.jpg")));
		setTitle("Gestion du stock");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 669, 508);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 240, 240));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(77, 23, 524, 373);
		contentPane.add(scrollPane);

		
		table = new JTable(model);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton()== 1 && e.getClickCount() == 2) {
					boolean erreur = true;
					int choixValide = 0;
					do
					{try {
						String choix = (String) JOptionPane.showInputDialog(
								null, "Stock", "Nombre à ajouter",
								JOptionPane.QUESTION_MESSAGE);
						if (choix == null) choixValide = 0;
						else choixValide = Integer.parseInt(choix);
						erreur = false;
					} catch (NumberFormatException e1) 
					{JOptionPane.showMessageDialog(null,
							"Seules les nombres positifs sont autorisées",
							"Erreur", JOptionPane.ERROR_MESSAGE);}}
					while (erreur);
					try {
						model.getMedicament(table.getSelectedRow()).setStock(
								model.getMedicament(table.getSelectedRow())
										.getStock() + choixValide);
						save(getPharma(), "donnees");
						revalidate();
						repaint();
					} catch (AppException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		scrollPane.setViewportView(table);
		JButton btnNewButton = new JButton("Quitter");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnNewButton.setBounds(500, 420, 127, 23);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Retour");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new MainMenu().setVisible(true);
			}
		});
		btnNewButton_1.setBounds(36, 420, 135, 23);
		contentPane.add(btnNewButton_1);
		
		JTextArea txtrCliqueFois = new JTextArea();
		txtrCliqueFois.setBackground(new Color(240, 240, 240));
		txtrCliqueFois.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtrCliqueFois.setText("Clique 2 fois sur un medicament \r\npour ajuster son stock (+ ou - devant\r\nle chiffre pour augmenter ou diminuer)");
		txtrCliqueFois.setBounds(237, 407, 207, 51);
		contentPane.add(txtrCliqueFois);
		for (Medicament medicament : getPharma().getMedicaments())
			model.addMedicament(medicament);

	}
}
