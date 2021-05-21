package presentation;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dataAccess.Query;
import domain.Queries;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JScrollBar;
import javax.swing.JTable;
import javax.swing.JList;
import javax.swing.JTextArea;

public class MainGUI extends JFrame {

	private JPanel contentPane;
	private ButtonGroup buttonGroup = new ButtonGroup();
	JTextArea textArea = new JTextArea();
	private JTable table;
	private Queries queries = new Queries();
	private int actualIndex = 0;
	private Query query = new Query();
	private JList<String> list;
	
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
		setBounds(100, 100, 524, 383);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblWichStudentsWork = new JLabel("Wich student's work do you want to test?");
		lblWichStudentsWork.setBounds(12, 12, 325, 26);
		contentPane.add(lblWichStudentsWork);

		JComboBox comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (comboBox.getSelectedItem().equals("Aritz Plazaola"))
					textArea.setText(queries.getAritzQueryText().get(actualIndex));
				if (comboBox.getSelectedItem().equals("Zdravko Todorov"))
					textArea.setText(queries.getZdravkoQueryText().get(actualIndex));
			}
		});
		comboBox.setBounds(323, 13, 189, 24);
		contentPane.add(comboBox);

		comboBox.addItem("Please select student");
		comboBox.addItem("Aritz Plazaola");
		comboBox.addItem("Zdravko Todorov");
		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true);

		textArea.setText("Query text will appear here");
		textArea.setBounds(30, 103, 456, 204);
		contentPane.add(textArea);
		textArea.setVisible(false);
		textArea.setFont(new Font("Dialog", Font.BOLD, 14));

		JButton btnNextQuery = new JButton("Next Query");
		btnNextQuery.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (actualIndex < 2)
					actualIndex++;
				if (comboBox.getSelectedItem().equals("Aritz Plazaola") && actualIndex < 3)
					textArea.setText(queries.getAritzQueryText().get(actualIndex));
				if (comboBox.getSelectedItem().equals("Zdravko Todorov") && actualIndex < 3)
					textArea.setText(queries.getZdravkoQueryText().get(actualIndex));
				System.out.println("Actual index -> " + actualIndex);
			}
		});
		btnNextQuery.setBounds(350, 319, 136, 25);
		contentPane.add(btnNextQuery);

		JButton btnPreviousQuery = new JButton("Previous Query");
		btnPreviousQuery.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (actualIndex > 0)
					actualIndex--;
				if (comboBox.getSelectedItem().equals("Aritz Plazaola") && actualIndex >= 0)
					textArea.setText(queries.getAritzQueryText().get(actualIndex));
				if (comboBox.getSelectedItem().equals("Zdravko Todorov") && actualIndex >= 0)
					textArea.setText(queries.getZdravkoQueryText().get(actualIndex));
				System.out.println("Actual index -> " + actualIndex);
			}
		});
		btnPreviousQuery.setBounds(30, 319, 141, 25);
		contentPane.add(btnPreviousQuery);

		JButton btnExecute = new JButton("Execute");
		btnExecute.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (!btnExecute.getText().equals("Back")) {

					ArrayList<String> result = query.executeQuery(comboBox.getSelectedItem().toString(), actualIndex);
					
					DefaultListModel<String> model = new DefaultListModel<String>();

					for (String r: result) {
						model.addElement(r);
					}
					
					list = new JList<String>(model);
					list.setFont(new Font("Dialog", Font.BOLD, 14));
					list.setBounds(30, 103, 456, 204);
					contentPane.add(list);
					
					textArea.setVisible(false);
					list.setVisible(true);
					btnExecute.setText("Back");
					btnNextQuery.setVisible(false);
					btnPreviousQuery.setVisible(false);
				} else {

					textArea.setVisible(true);
					list.setVisible(false);
					btnExecute.setText("Execute");
					btnNextQuery.setVisible(true);
					btnPreviousQuery.setVisible(true);
				}
			}
		});
		btnExecute.setBounds(203, 319, 117, 25);
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

				textArea.setVisible(true);
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

				textArea.setVisible(false);
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
