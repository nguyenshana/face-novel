package facenovel;
import javax.swing.*;
import java.awt.event.*;

public class FaceNovel extends JFrame
{
	private JPanel panel;
	private JLabel messageLabel;
	private JTextField textField;
	private JButton searchButton;
	private final int WINDOW_WIDTH = 300;
	private final int WINDOW_HEIGHT = 200;
	
	public FaceNovel()
	{
		setTitle("FaceNovel");
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		buildPanel();
		add(panel);
		setVisible(true);
	}
	
	public void buildPanel()
	{
		messageLabel = new JLabel("Enter the person's first and last name:");
		textField = new JTextField(20);
		
		searchButton = new JButton("Search");
		searchButton.addActionListener(new SearchButtonListener());
		
		panel = new JPanel();
		
		panel.add(messageLabel);
		panel.add(textField);
		panel.add(searchButton);
	}
	
	private class SearchButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			String input = textField.getText();
			
			//*******Have to search firstName and lastName through the network**********//
			
			JOptionPane.showMessageDialog(null, input + " was not found."); //if profile was not found
			
			
		}
	}
	
	public static void main(String[] args)
	{
		new FaceNovel();
	}
}
