package gr.nerme.economics.gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

import org.softsmithy.lib.swing.JDoubleField;

import com.toedter.calendar.JDateChooser;

import gr.nerme.economics.db.ReadAndWrite;

public class Main {

	private JFrame frame;
	public List<String> dataList;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 774, 561);

		frame.getContentPane().add(tabbedPane);

		JPanel in_panel = new JPanel();
		tabbedPane.addTab("Income", null, in_panel, null);
		in_panel.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 749, 101);
		in_panel.add(panel);
		panel.setLayout(null);

		final JComboBox<String> categoryList = new JComboBox<String>();
		categoryList.setBounds(84, 37, 200, 20);
		for (String string : ReadAndWrite.getCategories()) {
			categoryList.addItem(string);
		}
		panel.add(categoryList);

		JLabel lblCategory = new JLabel("Category");
		lblCategory.setBounds(10, 40, 64, 14);
		panel.add(lblCategory);

		JLabel lblDate = new JLabel("Date");
		lblDate.setBounds(310, 40, 45, 14);
		panel.add(lblDate);

		JLabel lblAmount = new JLabel("Amount");
		lblAmount.setBounds(10, 71, 64, 14);
		panel.add(lblAmount);

		final JDoubleField amountField = new JDoubleField();
		amountField.setBounds(84, 68, 200, 20);
		panel.add(amountField);
		amountField.setColumns(10);

		JButton btnSave = new JButton("Save");
		btnSave.setBounds(349, 67, 87, 23);
		panel.add(btnSave);

		final JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(349, 37, 200, 20);
		panel.add(dateChooser);

		JLabel lblAddNewIncome = new JLabel("Add new Income");
		lblAddNewIncome.setBounds(303, 11, 103, 14);
		panel.add(lblAddNewIncome);

		btnSave.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Clicked");
				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				try {
					ReadAndWrite.saveToFile("2017_06", categoryList.getSelectedItem().toString(),
							dateFormat.format(dateChooser.getDate()), amountField.getDoubleValue());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				frame.invalidate();
				frame.validate();
				frame.repaint();
			}
		});

		JPanel out_panel = new JPanel();
		tabbedPane.addTab("Outcome", null, out_panel, null);
		out_panel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 53, 769, 480);
		out_panel.add(scrollPane);

		table = new JTable();
		table.setColumnSelectionAllowed(true);
		scrollPane.setViewportView(table);

		JLabel lblSelectMonth = new JLabel("Select Month");
		lblSelectMonth.setBounds(10, 11, 135, 14);
		out_panel.add(lblSelectMonth);

		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBounds(105, 8, 148, 20);
		comboBox.addItem("January");
		comboBox.addItem("February");
		comboBox.addItem("March");
		comboBox.addItem("April");
		comboBox.addItem("May");
		comboBox.addItem("June");
		comboBox.addItem("July");
		comboBox.addItem("August");
		comboBox.addItem("September");
		comboBox.addItem("October");
		comboBox.addItem("November");
		comboBox.addItem("December");
		out_panel.add(comboBox);

		JLabel lblSelectYear = new JLabel("Select Year");
		lblSelectYear.setBounds(309, 11, 88, 14);
		out_panel.add(lblSelectYear);

		final JComboBox<String> comboBox_1 = new JComboBox<String>();
		comboBox_1.setBounds(394, 8, 98, 20);
		for (String string : ReadAndWrite.getYears()) {

			comboBox_1.addItem(string);
		}
		out_panel.add(comboBox_1);

		JButton btnSearch = new JButton("Search");
		btnSearch.setBounds(591, 7, 89, 23);
		out_panel.add(btnSearch);

		tabbedPane.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				System.out.println("Clicked!!!");
				comboBox_1.removeAllItems();
				categoryList.removeAllItems();
				for (String string : ReadAndWrite.getCategories()) {
					categoryList.addItem(string);
				}
				for (String string : ReadAndWrite.getYears()) {

					comboBox_1.addItem(string);
				}
				try {
					table.setModel(new DefaultTableModel(ReadAndWrite.trimString(ReadAndWrite.readFromFile("2017_06")),
							new String[] { "Date", "Category", "Category" }));
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

			}
		});

		btnSearch.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

			}
		});

	}
}
