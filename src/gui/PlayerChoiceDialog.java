package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * <h1>PlayerChoiceDialog</h1>
 * Custom dialog where the user chooses what
 * character they will be in the game.
 * @author Adam Nelson, Youjun Lee
 * @since 2017-11-15
 *
 */
public class PlayerChoiceDialog extends JDialog {

	private String playerChoice; //The name of the character the user will be.
	JComboBox<String> combo;
	
	/**
	 * Default constructor.
	 */
	public PlayerChoiceDialog(String playerChoice) {
		super();
		JPanel panel = new JPanel();
		setContentPane(panel);
		setSize(300,300);
		setResizable(true);
		setTitle("Character Choice");
		
		JLabel label = new JLabel("Choose a character:");
		combo = new JComboBox<String>();
		combo.addItem("Miss Scarlett");
		combo.addItem("Professor Plum");
		combo.addItem("Mrs. Peacock");
		combo.addItem("Mr. Green");
		combo.addItem("Colonel Mustard");
		combo.addItem("Mrs. White");
		combo.addActionListener(new ChoiceListener());
		
		panel.add(label, BorderLayout.WEST);
		panel.add(combo, BorderLayout.CENTER);
		setVisible(true);
	}
	
	public String getPlayerChoice() {
		return playerChoice;
	}

	private class ChoiceListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			playerChoice = (String) combo.getSelectedItem();
		}
	}
}
