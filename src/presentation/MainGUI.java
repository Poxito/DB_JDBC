package presentation;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dataAccess.Query;
import dataAccess.Transaction;
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
	JTextArea textAreaQuery = new JTextArea();
	private JTable table;
	private Queries queries = new Queries();
	private int actualIndex = 0;
	private Query query = new Query();
	private JList<String> list;
	private Transaction transaction = new Transaction();

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

		JRadioButton rdbtnQuery = new JRadioButton("Query");
		JRadioButton rdbtnTransaction = new JRadioButton("Transaction");

		JTextArea textAreaTrans = new JTextArea();
		textAreaTrans.setText("textAreatrans");
		textAreaTrans.setBounds(30, 103, 456, 204);
		contentPane.add(textAreaTrans);
		textAreaTrans.setVisible(false);
		textAreaTrans.setText("Transaction text will appear here");
		textAreaTrans.setFont(new Font("Dialog", Font.BOLD, 14));

		JLabel lblWichStudentsWork = new JLabel("Wich student's work do you want to test?");
		lblWichStudentsWork.setBounds(12, 12, 325, 26);
		contentPane.add(lblWichStudentsWork);

		JComboBox comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (comboBox.getSelectedItem().equals("Aritz Plazaola"))
					textAreaQuery.setText(queries.getAritzQueryText().get(actualIndex));
				if (comboBox.getSelectedItem().equals("Zdravko Todorov"))
					textAreaQuery.setText(queries.getZdravkoQueryText().get(actualIndex));
			}
		});
		comboBox.setBounds(323, 13, 189, 24);
		contentPane.add(comboBox);

		comboBox.addItem("Please select student");
		comboBox.addItem("Aritz Plazaola");
		comboBox.addItem("Zdravko Todorov");
		textAreaQuery.setWrapStyleWord(true);
		textAreaQuery.setLineWrap(true);

		textAreaQuery.setText("Query text will appear here");
		textAreaQuery.setBounds(30, 103, 456, 204);
		contentPane.add(textAreaQuery);
		textAreaQuery.setVisible(false);
		textAreaQuery.setFont(new Font("Dialog", Font.BOLD, 14));

		JLabel lblPage = new JLabel("1");
		lblPage.setBounds(440, 68, 28, 15);
		contentPane.add(lblPage);

		JLabel label = new JLabel("/  3");
		label.setBounds(461, 68, 35, 15);
		contentPane.add(label);

		JButton btnNextQuery = new JButton("Next");
		btnNextQuery.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (actualIndex < 2 && rdbtnQuery.isSelected())
					actualIndex++;
				if (actualIndex < 1 && rdbtnTransaction.isSelected())
					actualIndex++;
				if (comboBox.getSelectedItem().equals("Aritz Plazaola") && actualIndex < 3)
					textAreaQuery.setText(queries.getAritzQueryText().get(actualIndex));
				if (comboBox.getSelectedItem().equals("Zdravko Todorov") && actualIndex < 3)
					textAreaQuery.setText(queries.getZdravkoQueryText().get(actualIndex));

				if (actualIndex == 0) {
					textAreaTrans.setText("insert will be here");
				}
				if (actualIndex == 1) {
					textAreaTrans.setText("update will be here bitch!");
				}

				System.out.println("Actual index -> " + actualIndex);
				lblPage.setText(String.valueOf(actualIndex + 1));
			}
		});
		btnNextQuery.setBounds(350, 319, 136, 25);
		contentPane.add(btnNextQuery);

		JButton btnPreviousQuery = new JButton("Previous");
		btnPreviousQuery.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (actualIndex > 0)
					actualIndex--;
				if (comboBox.getSelectedItem().equals("Aritz Plazaola") && actualIndex >= 0)
					textAreaQuery.setText(queries.getAritzQueryText().get(actualIndex));
				if (comboBox.getSelectedItem().equals("Zdravko Todorov") && actualIndex >= 0)
					textAreaQuery.setText(queries.getZdravkoQueryText().get(actualIndex));

				if (actualIndex == 0)
					textAreaTrans.setText("insert will be here");
				if (actualIndex == 1)
					textAreaTrans.setText("update will be here");

				System.out.println("Actual index -> " + actualIndex);
				lblPage.setText(String.valueOf(actualIndex + 1));
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

					for (String r : result) {
						model.addElement(r);
					}

					list = new JList<String>(model);
					list.setFont(new Font("Dialog", Font.BOLD, 14));
					list.setBounds(30, 103, 456, 204);
					contentPane.add(list);

					textAreaQuery.setVisible(false);
					list.setVisible(true);
					btnExecute.setText("Back");
					btnNextQuery.setVisible(false);
					btnPreviousQuery.setVisible(false);
				} else {

					textAreaQuery.setVisible(true);
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
		label.setVisible(false);
		lblPage.setVisible(false);

		JLabel lblQueryOrTransaction = new JLabel("Query or transaction?");
		lblQueryOrTransaction.setBounds(12, 60, 203, 31);
		contentPane.add(lblQueryOrTransaction);

		rdbtnQuery.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				textAreaQuery.setVisible(true);
				textAreaTrans.setVisible(false);
				btnExecute.setVisible(true);
				btnNextQuery.setVisible(true);
				btnPreviousQuery.setVisible(true);
				lblPage.setVisible(true);
				label.setText("/  3");
				label.setVisible(true);
			}
		});
		rdbtnQuery.setBounds(192, 64, 93, 23);
		buttonGroup.add(rdbtnQuery);

		rdbtnTransaction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				textAreaQuery.setVisible(false);
				textAreaTrans.setVisible(true);
				btnExecute.setVisible(true);
				btnNextQuery.setVisible(true);
				btnPreviousQuery.setVisible(true);
				lblPage.setVisible(true);
				label.setText("/  2");
				label.setVisible(true);
			}
		});
		rdbtnTransaction.setBounds(300, 64, 126, 23);
		buttonGroup.add(rdbtnTransaction);

		contentPane.add(rdbtnTransaction);
		contentPane.add(rdbtnQuery);

	}
}
