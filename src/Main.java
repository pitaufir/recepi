import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Main {

	private JFrame frame;
	private JTextField textSearch;

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
		frame.setBounds(100, 100, 915, 533);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTextArea areaResultDisplay = new JTextArea();
		areaResultDisplay.setBounds(12, 13, 723, 460);
		frame.getContentPane().add(areaResultDisplay);
		
		textSearch = new JTextField();
		textSearch.setBounds(747, 13, 138, 60);
		frame.getContentPane().add(textSearch);
		textSearch.setColumns(10);
		
		JButton searchBtn = new JButton("Search");
		searchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String searchQuery = textSearch.getText();
				Thread thrd1 = new Thread(new searchRecipe(searchQuery, areaResultDisplay));
				thrd1.start();
			}
		});
		searchBtn.setBounds(747, 99, 138, 25);
		frame.getContentPane().add(searchBtn);
	}
}
