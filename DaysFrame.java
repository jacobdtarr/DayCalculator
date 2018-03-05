import javax.swing.*;
import java.awt.*;
import java.time.*;
import java.lang.*;
import java.awt.event.*;
import java.time.temporal.*;

public class DaysFrame extends JFrame
{
	
	public DaysFrame() {
		ImageIcon image = new ImageIcon("sun.gif");
		setIconImage(image.getImage());
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setTitle("Time Between...");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		today = LocalDate.now();
		
		// Setup the GUI components
		setupLabels();
		setupTextFields();
		setupComboBox();
		setupButton();
		setupPanel();
		
		CalculateDaysListener calculateDaysListener = new CalculateDaysListener();
		count.addActionListener(calculateDaysListener);
		startingDateField.addActionListener(calculateDaysListener);
	}
	
	class CalculateDaysListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event) {
			
			if(DateTools.verifyDate(startingDateField.getText(), today))
			{
				firstDate = LocalDate.parse(startingDateField.getText(), DateTools.DATE_FORMAT);
				
				String countChoice = (String) dateOption.getSelectedItem();
				
				if (countChoice.equals("Days")) {
					JOptionPane.showMessageDialog(null, ChronoUnit.DAYS.between(firstDate, today) + " Day(s)");
				}
				else if (countChoice.equals("Months")) {
					JOptionPane.showMessageDialog(null, ChronoUnit.MONTHS.between(firstDate, today) + " Month(s)");
				}
				else if (countChoice.equals("Years")) {
					JOptionPane.showMessageDialog(null, ChronoUnit.YEARS.between(firstDate, today) + " Year(s)");
				}
			}
		}
		
	}
	
	/**
	* Creates text labels
	*/
	private void setupLabels() {
		startingDate = new JLabel("Starting date: ");
		endingDate = new JLabel("Ending date: ");
	}
	
	/**
	* Creates text fields
	*/
	private void setupTextFields() {
		startingDateField = new JTextField(7);
		endingDateField = new JTextField(7);
		
		endingDateField.setEditable(false);
		endingDateField.setText(String.format("%1$02d/%2$02d/%3$d", today.getDayOfMonth(), today.getMonthValue(), today.getYear()));
	}
	
	/**
	* Creates combo box to select what to count
	*/
	private void setupComboBox() {
		dateOption = new JComboBox();
		dateOption.addItem("Days");
		dateOption.addItem("Months");
		dateOption.addItem("Years");
	}
	
	/**
	* Creates button
	*/
	private void setupButton() {
		count = new JButton("Count");
	}
	
	/**
	* Creates and adds components to main panel
	*/
	private void setupPanel() {
		panel = new JPanel();
		panel.add(startingDate);
		panel.add(startingDateField);
		panel.add(endingDate);
		panel.add(endingDateField);
		panel.add(dateOption);
		panel.add(count);
		add(panel);
	}
		
	
	private final static int FRAME_WIDTH = 450, FRAME_HEIGHT = 100;
	private JPanel panel;
	private JLabel startingDate, endingDate;
	private JTextField startingDateField, endingDateField;
	private JComboBox dateOption;
	private JButton count;
	private LocalDate today;
	private LocalDate firstDate;
}