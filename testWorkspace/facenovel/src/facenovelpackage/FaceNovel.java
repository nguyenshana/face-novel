package facenovelpackage;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.*;
import java.util.ArrayList;

public class FaceNovel 
{
	private JFrame f;
	private ProfileManager m;
	
	public FaceNovel(ProfileManager m) 
	{
		f = new JFrame();
		this.m = m;
	}
	
	
	public class addPerson 
	{
		private JPanel panel;
		
		private JButton navToSearchPerson;
		
		private JLabel firstNameLabel;
		private JLabel lastNameLabel;
		private JTextField firstNameTextField;
		private JTextField lastNameTextField;
		
		private JLabel statusLabel;
		private JButton singleButton;
		private JButton marriedButton;
		private String status;
		
		private JButton addButton;
		private final int WINDOW_WIDTH = 300;
		private final int WINDOW_HEIGHT = 200;
		
		public addPerson()
		{
			f.setTitle("FaceNovel");
			f.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
			f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			buildPanel();
			f.add(panel);
			f.setVisible(true);
		}
		
		public void buildPanel()
		{
			navToSearchPerson = new JButton("Search for a person");
			navToSearchPerson.addActionListener(new goToSearch());
			
			firstNameLabel = new JLabel("First name:");
			firstNameTextField = new JTextField(20);
			
			lastNameLabel = new JLabel("Last name:");
			lastNameTextField = new JTextField(20);
			
			statusLabel = new JLabel("Select your status");
			singleButton = new JButton("Single");
			singleButton.addActionListener(new SingleButtonListener());
			marriedButton = new JButton("Married");
			marriedButton.addActionListener(new MarriedButtonListener());

//			ADD PICTURE & ACTION THING
			
			addButton = new JButton("Add");
			addButton.addActionListener(new AddButtonListener());
			
			panel = new JPanel();
			
			panel.add(navToSearchPerson);
			panel.add(firstNameLabel);
			panel.add(firstNameTextField);
			panel.add(lastNameLabel);
			panel.add(lastNameTextField);
			panel.add(statusLabel);
			panel.add(singleButton);
			panel.add(marriedButton);
			panel.add(addButton);
			
		}
		
		class goToSearch implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{
				panel.removeAll();
				new searchPerson();
			}
		}
		
		class SingleButtonListener implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{
				status = "Single";
			}
		}
		
		class MarriedButtonListener implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{
				status = "Married";
			}
		}
		
		class AddButtonListener implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{
				Profile toAdd = new Profile();
				toAdd.setName(firstNameTextField.getText(), lastNameTextField.getText());
				toAdd.setStatus(status);
				m.addProfile(toAdd);
				toAdd.display();
				
				panel.removeAll();
				
				new searchPerson();
			}
		}
		
	}
	
	
	public class searchPerson 
	{
		private JPanel panel;
		private JButton navToAdd;
		private JLabel messageLabel;
		private JTextField textField;
		private JButton searchButton;
		private final int WINDOW_WIDTH = 300;
		private final int WINDOW_HEIGHT = 200;
		
		public searchPerson() 
		{
			f.setTitle("Search Person");
			f.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
			f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			buildPanel();
			f.add(panel);
			f.setVisible(true);
		}
	
	
		public void buildPanel()
		{
			navToAdd = new JButton("Create an Account");
			navToAdd.addActionListener(new goToAdd());
			
			messageLabel = new JLabel("Enter the person's first and last name:");
			textField = new JTextField(20);
			
			searchButton = new JButton("Search");
			searchButton.addActionListener(new SearchButtonListener());
			
			panel = new JPanel();
			
			panel.add(navToAdd);
			panel.add(messageLabel);
			panel.add(textField);
			panel.add(searchButton);
		}
		
		class goToAdd implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{
				panel.removeAll();
				new addPerson();
			}
		}
		
		class SearchButtonListener implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{
				String input = textField.getText();
				
				Profile searchedUpPerson = m.findPerson(input);
				
				if(searchedUpPerson == null)
				{
					JOptionPane.showMessageDialog(null, input + " was not found."); //if profile was not found
				}
				else
				{
					new profilePage(searchedUpPerson);
				}
			}
		}
	} // end class searchPerson
	
	
	
	
	
	
	public class profilePage extends JFrame
	{

		private Profile person;
		
		private JLabel nameLabel;
		private JLabel statusLabel;
		
		private JLabel addFriendLabel;
		private JTextField addFriendTextField;

		private ImageIcon profilepic;
		private JPanel profile;
		private JPanel profilePicPanel;
		
		public profilePage(Profile person) {
			this.person = person;
			
			setTitle("Profile");
			
			setSize(1000, 1000);
			
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			setLayout(new BorderLayout());
			
			createProfilePanel(person);
			createImagePanel();
			
			add(profile, BorderLayout.NORTH);
			add(profilePicPanel, BorderLayout.CENTER);
			
			pack();
			
			setVisible(true);
		}
		
		private void createProfilePanel(Profile person) {
			
			nameLabel = new JLabel(person.getName());
			statusLabel = new JLabel(person.getStatus());
			
			ArrayList<Profile> friends = person.getFriends();
			
			profile = new JPanel();
			
			profile.add(nameLabel);
			profile.add(statusLabel);
			
			addFriendLabel = new JLabel("Add a friend: ");
			addFriendTextField = new JTextField(20); 
			JButton addFriendButton = new JButton("Add");
			addFriendButton.addActionListener(new addFriendButtonListener());
			
			profile.add(addFriendLabel);
			profile.add(addFriendTextField);
			profile.add(addFriendButton);
			
			JButton deleteButton = new JButton("Delete Account");
			deleteButton.addActionListener(new deleteButtonListener());
			
			profile.add(deleteButton);
			
			for(Profile friend : friends)
			{
				JLabel amigo = new JLabel(friend.getName());
				profile.add(amigo);
			}
		
			
		}
		
		class addFriendButtonListener implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{
				Profile personProfile = m.findPerson(addFriendTextField.getText());
				if(personProfile == null)
				{
					JOptionPane.showMessageDialog(null, "Person not found");
				}
				else
				{
					person.addFriend(personProfile);
					JOptionPane.showMessageDialog(null, "Friend added");
				}
				
			}
		}
		
		class deleteButtonListener implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{
				m.leaveNetwork(person);
				JOptionPane.showMessageDialog(null, "Account deleted");
			}
		}
		
		private void createImagePanel() {
			
			profilepic = new ImageIcon("/Users/shana/Desktop/sun.jpg");
			JLabel profilePicLabel = new JLabel(profilepic);
			
			Dimension picsize = new Dimension(10, 10);
			profilePicLabel.setMaximumSize(picsize);
			
			profilePicPanel = new JPanel();
			
			profilePicPanel.add(profilePicLabel);
		}
		
	} // end class profilePage

	
	
	
	
	
	public static void main(String[] args)
	{
		ProfileManager m = new ProfileManager();

		System.out.println("Creating profiles and the network...");
		System.out.println();
		FaceNovel thing = new FaceNovel(m);
		thing.new addPerson();
	}
}// end class FaceNovel

