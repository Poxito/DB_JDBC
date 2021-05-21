package presentation;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainGUI extends JFrame {

	private JPanel contentPane;
	private JTextField txtQueryTextWill;
	private ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainGUI frame = new MainGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 524, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblWichStudentsWork = new JLabel("Wich student's work do you want to test?");
		lblWichStudentsWork.setBounds(12, 12, 325, 26);
		contentPane.add(lblWichStudentsWork);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(323, 13, 189, 24);
		contentPane.add(comboBox);
		
		comboBox.addItem("Aritz Plazaola");
		comboBox.addItem("Zdravko Todorov");
		
		txtQueryTextWill = new JTextField();
		txtQueryTextWill.setText("Query text will appear here");
		txtQueryTextWill.setBounds(30, 123, 456, 90);
		contentPane.add(txtQueryTextWill);
		txtQueryTextWill.setColumns(10);
		txtQueryTextWill.setVisible(false);
		
		JButton btnNextQuery = new JButton("Next Query");
		btnNextQuery.setBounds(350, 225, 136, 25);
		contentPane.add(btnNextQuery);
		
		JButton btnPreviousQuery = new JButton("Previous Query");
		btnPreviousQuery.setBounds(30, 225, 141, 25);
		contentPane.add(btnPreviousQuery);
		
		JButton btnExecute = new JButton("Execute");
		btnExecute.setBounds(203, 225, 117, 25);
		contentPane.add(btnExecute);
		
		btnExecute.setVisible(false);
		btnNextQuery.setVisible(false);
		btnPreviousQuery.setVisible(false);
		
		JLabel lblQueryOrTransaction = new JLabel("Query or transaction?");
		lblQueryOrTransaction.setBounds(12, 60, 203, 31);
		contentPane.add(lblQueryOrTransaction);
		
		JRadioButton rdbtnQuery = new JRadioButton("Query");
		rdbtnQuery.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				txtQueryTextWill.setVisible(true);
				btnExecute.setVisible(true);
				btnNextQuery.setVisible(true);
				btnPreviousQuery.setVisible(true);
			}
		});
		rdbtnQuery.setBounds(203, 64, 93, 23);
		buttonGroup.add(rdbtnQuery);
		
		JRadioButton rdbtnTransaction = new JRadioButton("Transaction");
		rdbtnTransaction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				txtQueryTextWill.setVisible(false);
				btnExecute.setVisible(false);
				btnNextQuery.setVisible(false);
				btnPreviousQuery.setVisible(false);
			}
		});
		rdbtnTransaction.setBounds(301, 64, 149, 23);
		buttonGroup.add(rdbtnTransaction);
		
		contentPane.add(rdbtnTransaction);
		contentPane.add(rdbtnQuery);
		
	}
}
