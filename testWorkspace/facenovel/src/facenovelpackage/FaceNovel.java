package facenovelpackage;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class FaceNovel 
{
	private JFrame f;
	private JFrame prof;
	private JFrame edit;
	private ProfileManager m;
	
	public FaceNovel(ProfileManager m) 
	{
		f = new JFrame();
		prof = new JFrame();
		edit = new JFrame();
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
		private Dimension windowsize;
		
		private JButton uploadButton;
	    private JLabel uploadLabel;
	    private JTextField uploadTextField;
		
		private GridLayout grid;
		
		public addPerson()
		{
			f.setTitle("FaceNovel");
			windowsize = new Dimension(500, 500);
			f.setMinimumSize(windowsize);
			f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
			
			statusLabel = new JLabel("Select your status:");
			singleButton = new JButton("Single");
			singleButton.addActionListener(new SingleButtonListener());
			marriedButton = new JButton("Married");
			marriedButton.addActionListener(new MarriedButtonListener());

			
			addButton = new JButton("Add");
			addButton.addActionListener(new AddButtonListener());
			
			panel = new JPanel();
//			grid = new GridLayout(5,4);
//			panel.setLayout(grid);
			
			uploadLabel = new JLabel("Enter your image's pathfield here:");
	        uploadTextField = new JTextField(20);

			
			panel.add(navToSearchPerson);
			panel.add(firstNameLabel);
			panel.add(firstNameTextField);
			panel.add(lastNameLabel);
			panel.add(lastNameTextField);
			panel.add(statusLabel);
			panel.add(singleButton);
			panel.add(marriedButton);
			panel.add(uploadLabel);
	        panel.add(uploadTextField);
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
				if(firstNameTextField.getText() == null || lastNameTextField.getText() == null)
				{
					JOptionPane.showMessageDialog(null, "Enter a first and last name.");
				}
				else
				{
//					JOptionPane.showMessageDialog(null, "." + firstNameTextField.getText() + "SPACE" + lastNameTextField.getText() + ".");
					toAdd.setName(firstNameTextField.getText(), lastNameTextField.getText());
					
					toAdd.setStatus(status);
					
					BufferedImage img = null;
					try 
					{
					    img = ImageIO.read(new File(uploadTextField.getText()));
					    toAdd.setProfilePicture(img);
			            JOptionPane.showMessageDialog(null, "Image successfully uploaded."); //if image was uploaded
			            m.addProfile(toAdd);
						toAdd.display();
						
						panel.removeAll();
						
						new searchPerson();
					} 
					catch (IOException a) 
					{
			            JOptionPane.showMessageDialog(null, "Invalid image."); //if image was not uploaded
					}
				}
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
		private Dimension windowsize;
		
		public searchPerson() 
		{
			f.setTitle("Search Person");
			windowsize = new Dimension(500, 500);
			f.setMinimumSize(windowsize);
			f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
		
		private String friendSelection;
		private JList friendsJList;

		private ImageIcon profilepic;
		private JPanel panel;
		private JPanel profilePicPanel;
		
		public profilePage(Profile person) {
			
			this.person = person;
		
			setTitle("Profile");
			
			setSize(1000, 1000);
			
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			
			setLayout(new BorderLayout());
			
			createProfilePanel(person);
			createImagePanel();
			
			add(profilePicPanel, BorderLayout.NORTH);
			add(panel, BorderLayout.CENTER);
			
			pack();
			
			setVisible(true);
		}
		
		private void createProfilePanel(Profile person) {
			
			ArrayList<Profile> friendsList = person.getFriends();
			
			panel = new JPanel();
			
			JButton editProfileButton = new JButton("Edit Profile");
			editProfileButton.addActionListener(new editButtonListener());
			
			panel.add(editProfileButton);
			
			addFriendLabel = new JLabel("Add a friend: ");
			addFriendTextField = new JTextField(20); 
			JButton addFriendButton = new JButton("Add");
			addFriendButton.addActionListener(new addFriendButtonListener());
			
			panel.add(addFriendLabel);
			panel.add(addFriendTextField);
			panel.add(addFriendButton);
			
			JButton deleteButton = new JButton("Delete Account");
			deleteButton.addActionListener(new deleteButtonListener());
			
			panel.add(deleteButton);


            // convert ArrayList to Array
            if(friendsList.size() > 0)
            {
            	String friendsListArray[] = new String[friendsList.size()];
                for(int i = 0; i < friendsList.size(); i++)
                {
                    friendsListArray[i] =  friendsList.get(i).getName();
                }
                friendsJList = new JList(friendsListArray);
                friendsJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                friendsJList.addListSelectionListener(new ListListener());
    			
                panel.add(friendsJList);
            }
		
			
		}
		
		class editButtonListener implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{
				panel.removeAll();
				new editPerson(person);
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
		
		private class ListListener implements ListSelectionListener
		{
			public void valueChanged(ListSelectionEvent e)
			{
				friendSelection = (String) friendsJList.getSelectedValue();
				Profile friend = m.findPerson(friendSelection);
				
				profilePage friendProfile = new profilePage(friend);
			}
		}
		
		private void createImagePanel() 
		{
			profilePicPanel = new JPanel();

			JLabel pic = new JLabel();
			pic.setSize(50, 50);
			BufferedImage img = person.getProfilePicture();
			Image dimg = img.getScaledInstance(pic.getWidth(), pic.getHeight(),
			        Image.SCALE_SMOOTH);
			ImageIcon imageIcon = new ImageIcon(dimg);
			pic.setIcon(imageIcon);
						
			profilePicPanel.add(pic);
			
			nameLabel = new JLabel(person.getName());
			statusLabel = new JLabel(person.getStatus());
						
			profilePicPanel.add(nameLabel);
			profilePicPanel.add(statusLabel);
			
		}
		
	} // end class profilePage
	
	
	public class editPerson extends JFrame
	{
		private Profile person;
		private String status;
		
		private JLabel uploadLabel;
		private JTextField uploadTextField;
		private JButton uploadImgButton;

		private ImageIcon profilepic;
		private JPanel panel;
		
		public editPerson(Profile person) 
		{
			this.person = person;
			
			setTitle("Edit Account");
			
			setSize(200, 200);
			
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			
			setLayout(new BorderLayout());
			
			buildEditPanel();
			
			add(panel, BorderLayout.NORTH);
			
			pack();
			
			setVisible(true);
			
		}
		
		private void buildEditPanel() 
		{
			JLabel changeStatusLabel = new JLabel("Change status: ");
			JButton singleStatusButton = new JButton("Single");
			singleStatusButton.addActionListener(new singleListener());
			
			JButton marriedStatusButton = new JButton("Married");
			marriedStatusButton.addActionListener(new marriedListener());
			
			JButton changeStatusButton = new JButton("Submit Change of Status");
			changeStatusButton.addActionListener(new changeStatusListener());
			
			uploadLabel = new JLabel("Enter your image's pathfield here:");
	        uploadTextField = new JTextField(20);
	        uploadImgButton = new JButton("Upload Image");
	        uploadImgButton.addActionListener(new changePicListener());
			
			// add an update image
			
			panel = new JPanel();
			
			panel.add(changeStatusLabel);
			panel.add(singleStatusButton);
			panel.add(marriedStatusButton);
			panel.add(changeStatusButton);
			
			panel.add(uploadLabel);
			panel.add(uploadTextField);
			panel.add(uploadImgButton);
			
		}
			
		class singleListener implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{
				status = "Single";
			}
		}
		
		class marriedListener implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{
				status = "Married";
			}
		}
		
		class changeStatusListener implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{
				person.setStatus(status);
				JOptionPane.showMessageDialog(null, "Status changed to " + status);
			}
		}
		
		class changePicListener implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{
				BufferedImage img = null;
				try 
				{
				    img = ImageIO.read(new File(uploadTextField.getText()));
				    person.setProfilePicture(img);
		            JOptionPane.showMessageDialog(null, "Image successfully uploaded."); //if image was uploaded

				} 
				catch (IOException a) 
				{
		            JOptionPane.showMessageDialog(null, "Invalid image."); //if image was not uploaded
				}
			}
		}
		
	}

	
	
	
	
	
	public static void main(String[] args)
	{
		ProfileManager m = new ProfileManager();

		System.out.println("Creating profiles and the network...");
		System.out.println();
		FaceNovel thing = new FaceNovel(m);
		thing.new addPerson();
	}
	
}// end class FaceNovel

