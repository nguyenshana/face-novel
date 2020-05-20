package facenovelpackage;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

// This class holds all of the GUI information

public class FaceNovel 
{
	private JFrame f;
	private ProfileManager m;
	
	private JLabel programName;
	
	/** Constructor for FaceNovel class.
	@param m  an instance of ProfileManager. */
	public FaceNovel(ProfileManager m) 
	{
		programName = new JLabel("FaceNovel");
		f = new JFrame();
		this.m = m;
	}
	
	
	
	
	/**	
	 * The addPerson class creates the Home page where you can create an account 
	 * */
	public class addPerson 
	{
		private JPanel panel;
		private JPanel topPanel;
		private JPanel bottomPanel;
		
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
		private Dimension windowsize;
		
	    private JLabel uploadLabel;
	    private JTextField uploadTextField;
		
	    /** Constructor for addPerson class. */
		public addPerson()
		{
			f.setTitle("FaceNovel");
			windowsize = new Dimension(600, 500);
			f.setMinimumSize(windowsize);
			f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			buildPanel();
			f.getContentPane().add(topPanel, BorderLayout.NORTH);
	        f.getContentPane().add(panel, BorderLayout.CENTER);
	        f.getContentPane().add(bottomPanel, BorderLayout.SOUTH);
			f.pack();
			f.setVisible(true);
		}
		
		/** Builds the panels that display the header, inputs and labels for
		 *  creating an account, and the submission button. */
		public void buildPanel()
		{
			// Navigation button to search page
			
			navToSearchPerson = new JButton("Search for a person");
			navToSearchPerson.addActionListener(new goToSearch());
			
			// Labels, text boxes, and buttons for adding a user
			
			JLabel title = new JLabel("Create An Account");
			firstNameLabel = new JLabel("First name:");
			firstNameTextField = new JTextField(20);
			lastNameLabel = new JLabel("Last name:");
			lastNameTextField = new JTextField(20);
			
			statusLabel = new JLabel("Select your status:");
			singleButton = new JButton("Single");
			singleButton.addActionListener(new SingleButtonListener());
			marriedButton = new JButton("Married");
			marriedButton.addActionListener(new MarriedButtonListener());
			
			uploadLabel = new JLabel("Profile img pathfield:");
	        uploadTextField = new JTextField(20);
	        
	    	addButton = new JButton("Register New User");
			addButton.addActionListener(new AddButtonListener());
			
			// Set up for layout
			
			topPanel = new JPanel(new GridBagLayout());
			panel = new JPanel(new GridBagLayout());
			bottomPanel = new JPanel(new GridBagLayout());
	        GridBagConstraints c = new GridBagConstraints();
	        
	        // adding Title and Navigation
	        
	        c.gridx = 0;
	        c.gridy = 0;
	        c.insets = new Insets(0,15,5,5);
	        topPanel.add(programName,c);
	        c.gridx = 1;
			topPanel.add(navToSearchPerson, c);
			
			c.gridx = 0;
			c.gridy = 0;
			c.gridwidth = 3;
			panel.add(title, c);
			c.gridy = 5;
			panel.add(addButton,c);
		
			// adding user fields 
			
			c.gridx = 0;
			c.gridy = 1;
	        c.anchor = GridBagConstraints.LINE_END;
	        c.gridwidth = 1;
			panel.add(firstNameLabel,c );
			c.gridx = 1;
			c.gridwidth = 2;
			panel.add(firstNameTextField,c);
			
			c.gridx = 0;
			c.gridy = 2;
			c.gridwidth = 1;
			panel.add(lastNameLabel,c);
			c.gridx = 1;
			c.gridwidth = 2;
			panel.add(lastNameTextField,c);
			
			c.gridx = 0;
			c.gridy = 3;
			c.gridwidth = 1;
			panel.add(statusLabel,c);
			c.gridx = 1;
			c.anchor = GridBagConstraints.LINE_START;
			panel.add(singleButton,c);
			c.gridx = 2;
			panel.add(marriedButton,c);
			
			c.gridx = 0;
			c.gridy = 4;
	        c.anchor = GridBagConstraints.LINE_END;
			panel.add(uploadLabel,c);
			c.gridx = 1;
			c.gridwidth = 2;
	        panel.add(uploadTextField,c);
	        
		}
		
		
		class goToSearch implements ActionListener
		{
			/** Goes to the search page when the button is pushed. 
			@param e whether or not the button is pushed. */
			
			public void actionPerformed(ActionEvent e)
			{
				topPanel.removeAll();
				panel.removeAll();
				bottomPanel.removeAll();
				f.remove(topPanel);
				f.remove(panel);
				f.remove(bottomPanel);
				
				new searchPerson();
			}
		}
		
		
		class SingleButtonListener implements ActionListener
		{
			/** Sets the status as "Single" when the single button is pushed.
			@param e whether or not the button is pushed. */
			public void actionPerformed(ActionEvent e)
			{
				status = "Single";
			}
		}
		
		class MarriedButtonListener implements ActionListener
		{
			/** Sets the status as "Married" when the married button is pushed.
			@param e whether or not the button is pushed. */
			public void actionPerformed(ActionEvent e)
			{
				status = "Married";
			}
		}
		
		class AddButtonListener implements ActionListener
		{
			/** Adds another profile to the network when the add button is pushed.
			@param e whether or not the button is pushed. */
			public void actionPerformed(ActionEvent e)
			{
				Profile toAdd = new Profile();
				
				if(firstNameTextField.getText().isEmpty() || lastNameTextField.getText().isEmpty())
				{
					JOptionPane.showMessageDialog(null, "Enter both a first and last name.");
				}
				else
				{
					toAdd.setName(firstNameTextField.getText().toUpperCase(), lastNameTextField.getText().toUpperCase());
					
					toAdd.setStatus(status);
					
					BufferedImage img = null;
					try 
					{
					    img = ImageIO.read(new File(uploadTextField.getText()));
					    toAdd.setProfilePicture(img);
			            m.addProfile(toAdd);
						toAdd.display();
						
						topPanel.removeAll();
						panel.removeAll();
						bottomPanel.removeAll();
						f.remove(topPanel);
						f.remove(panel);
						f.remove(bottomPanel);
						
						new searchPerson();
					} 
					catch (IOException a) 
					{
			            JOptionPane.showMessageDialog(null, "Invalid image, but account created."); //if image was not uploaded
					    toAdd.setProfilePicture(null);
			            m.addProfile(toAdd);
						toAdd.display();
						
						topPanel.removeAll();
						panel.removeAll();
						bottomPanel.removeAll();
						f.remove(topPanel);
						f.remove(panel);
						f.remove(bottomPanel);
						
						new searchPerson();
					}
				}
			}
		}
		
	}
	
	
	
	
	/**	
	 * The searchPerson class creates the page where you can search up people's profiles
	 * */
	public class searchPerson 
	{
		private JPanel topPanel;
		private JPanel panel;
		
		private JButton navToAdd;
		private JLabel messageLabel;
		private JTextField textField;
		private JButton searchButton;
		private Dimension windowsize;
		
		/** Constructor for searchPerson class. */
		public searchPerson() 
		{
			f.setTitle("Search Person");
			windowsize = new Dimension(600, 500);
			f.setMinimumSize(windowsize);
			f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			buildPanel();
			f.add(panel);
			f.getContentPane().add(topPanel, BorderLayout.NORTH);
			f.getContentPane().add(panel, BorderLayout.CENTER);
			f.pack();
			f.setVisible(true);
		}
	
		/** Builds the panels that display the navigation button and search box. */
		public void buildPanel()
		{
			// Navigation button to the Create Account page
			
			navToAdd = new JButton("Create an Account");
			navToAdd.addActionListener(new goToAdd());
			
			// Labels, text inputs, and buttons to search for a person
			
			messageLabel = new JLabel("Enter the person's first and last name:");
			textField = new JTextField(20);
			
			searchButton = new JButton("Search");
			searchButton.addActionListener(new SearchButtonListener());
			
			// Set up for layout
			
			topPanel = new JPanel(new GridBagLayout());
			panel = new JPanel(new GridBagLayout());
			GridBagConstraints c = new GridBagConstraints();
			
			// add the components
			
	        c.gridx = 0;
	        c.gridy = 0;
	        c.insets = new Insets(0,15,5,5);
	        topPanel.add(programName,c);
	        c.gridx = 1;
			topPanel.add(navToAdd,c);
			
			c.gridx = 0;
			c.gridy = 0;
			panel.add(messageLabel,c);
			c.gridy = 1;
			panel.add(textField,c);

			c.gridy = 2;
			panel.add(searchButton,c);
		}
		
		class goToAdd implements ActionListener
		{
			/** Goes back to the add page when the "create an account" button is pushed.
			@param e whether or not the button is pushed. */
			public void actionPerformed(ActionEvent e)
			{
				topPanel.removeAll();
				panel.removeAll();
				f.remove(topPanel);
				f.remove(panel);
				
				new addPerson();
			}
		}
		
		class SearchButtonListener implements ActionListener
		{
			/** Searches for the user's input inside the text field when the "search" button is pushed.
			@param e whether or not the button is pushed. */
			public void actionPerformed(ActionEvent e)
			{
				String input = textField.getText().toUpperCase();
				input = input.trim();
				
				Profile searchedUpPerson = m.findPerson(input);
				
				if(textField.getText().isEmpty())
				{
					JOptionPane.showMessageDialog(null, "Please enter a name."); 
				}
				else if(searchedUpPerson == null)
				{
					JOptionPane.showMessageDialog(null, input + " was not found.");
				}
				else
				{
					topPanel.removeAll();
					panel.removeAll();
					f.remove(topPanel);
					f.remove(panel);
					
					new profilePage(searchedUpPerson);
				}
			}
		}
	} // end class searchPerson
	
	

	
	/**	
	 * The profilePage class creates the profile page
	 * */
	public class profilePage
	{
		private Profile person;
		
		private JLabel nameLabel;
		private JLabel statusLabel;
		
		private JLabel addFriendLabel;
		private JTextField addFriendTextField;
		
		private String friendSelection;
		private JList friendsJList;

		private JPanel panel;
		private JPanel profilePicPanel;
		
		private GridBagConstraints c;
		
		/** Constructor for profilePage.
		@param person  the specified profile. */
		public profilePage(Profile person) {
			
			this.person = person;
			f.setTitle("Profile");
			Dimension windowSize = new Dimension(600, 500);
			f.setMinimumSize(windowSize);
			f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			f.setLayout(new BorderLayout());
			createProfilePanel(person);
			createImagePanel();
			f.add(profilePicPanel, BorderLayout.NORTH);
			f.add(panel, BorderLayout.CENTER);
			f.pack();
			
			f.setVisible(true);
		}
		
		/** Creates an the header panel with the image */
		private void createImagePanel() 
		{
			// Header buttons to refresh the profile page or go back to 
			// the Search Person page
			
			JButton refreshProfileButton = new JButton("Refresh Page");
			refreshProfileButton.addActionListener(new refreshButtonListener());
			
			JButton leaveProfileButton = new JButton("Leave Page");
			leaveProfileButton.addActionListener(new leaveProfileButtonListener());
			
			// Labels for the profile information
			
			nameLabel = new JLabel(person.getName());
			
			statusLabel = new JLabel(person.getStatus());
			
			// Buttons to edit or delete the profile
			
			JButton editProfileButton = new JButton("Edit Profile");
			editProfileButton.addActionListener(new editButtonListener());
			
			JButton deleteButton = new JButton("Delete Account");
			deleteButton.addActionListener(new deleteButtonListener());
			
			// Set up for layout 
			
			profilePicPanel = new JPanel(new GridBagLayout());
			c = new GridBagConstraints();
			
			// Adding header label and buttons
			
			c.gridx = 0;
			c.gridy = 0;
			c.gridwidth = 1;
			c.insets = new Insets(5,5,5,5);
			profilePicPanel.add(programName,c);
			
			c.gridx = 1;
			profilePicPanel.add(refreshProfileButton, c);
			
			c.gridx = 2;
			profilePicPanel.add(leaveProfileButton, c);

			// Adding the profile picture if there is one
			if(person.getProfilePicture() != null)
			{
				JLabel pic = new JLabel();
				pic.setSize(80, 80);
				BufferedImage img = person.getProfilePicture();
				Image dimg = img.getScaledInstance(pic.getWidth(), pic.getHeight(),
				        Image.SCALE_SMOOTH);
				ImageIcon imageIcon = new ImageIcon(dimg);
				pic.setIcon(imageIcon);
				
				c.gridx = 1;
		        c.gridy = 1;
				profilePicPanel.add(pic, c);
			}
			
			// Adding the Profile information
			
			c.gridx = 1;
			c.gridy = 2;
			profilePicPanel.add(nameLabel,c);
			
			c.gridy = 3;
			profilePicPanel.add(statusLabel,c);
			
			// Adding the edit profile information
			
	        c.gridx = 0;
	        c.gridy = 4;
	        c.anchor = GridBagConstraints.LINE_END;
			profilePicPanel.add(editProfileButton,c);
			
			c.gridx = 2;
			c.anchor = GridBagConstraints.LINE_START;
			profilePicPanel.add(deleteButton,c);
			
		}
		
		/** Creates a panel for the given profile that can add and display friends.
		@param person  the specified profile. */
		private void createProfilePanel(Profile person) 
		{
			// Labels and buttons for adding a friend
			
			addFriendLabel = new JLabel("Add a friend: ");
			addFriendTextField = new JTextField(20); 
			JButton addFriendButton = new JButton("Add");
			addFriendButton.addActionListener(new addFriendButtonListener());
			
			// Set up for layout
			
			panel = new JPanel(new GridBagLayout());
			c = new GridBagConstraints();
			
			// Adding friend information
			
			c.gridx = 0;
			c.gridy = 1;
			c.gridwidth = 1;
			panel.add(addFriendLabel,c);
			c.gridx = 1;
			c.gridwidth = 2;
			panel.add(addFriendTextField,c);
			c.gridx = 3;
			c.gridwidth = 1;
			panel.add(addFriendButton,c);
			

			ArrayList<Profile> friendsList = person.getFriends();

            if(friendsList.size() > 0)
            {
            	c.gridx = 0;
    			c.gridy = 2;
    			c.anchor = GridBagConstraints.NORTHEAST;
    			JLabel friendsLabel = new JLabel("Friends:");
    			panel.add(friendsLabel,c);
            	String friendsListArray[] = new String[friendsList.size()];
                for(int i = 0; i < friendsList.size(); i++)
                {
                    friendsListArray[i] =  friendsList.get(i).getName();
                }
                friendsJList = new JList(friendsListArray);
                friendsJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                friendsJList.addListSelectionListener(new ListListener());
    			
                c.gridx = 1;
                c.gridwidth = 2;
                panel.add(friendsJList,c);
            }
		
			
		}
		
		class leaveProfileButtonListener implements ActionListener
		{
			/** Navigates the user to the search for profiles page when the "Leave Page" button is pushed.
			@param e  whether or not the button is pushed. */
			public void actionPerformed(ActionEvent e)
			{
				profilePicPanel.removeAll();
				panel.removeAll();
				f.remove(profilePicPanel);
				f.remove(panel);
				
				new searchPerson();
			}
		}
		
		class refreshButtonListener implements ActionListener
		{
			/** Refreshes the profile page when the "Refresh Page" button is pushed.
			@param e  whether or not the button is pushed. */
			public void actionPerformed(ActionEvent e)
			{
				profilePicPanel.removeAll();
				panel.removeAll();
				f.remove(profilePicPanel);
				f.remove(panel);
				
				new profilePage(person);
			}
		}
		
		class editButtonListener implements ActionListener
		{
			/** Navigates the user to the edit profile page when the "edit profile" button is pushed.
			@param e  whether or not the button is pushed. */
			public void actionPerformed(ActionEvent e)
			{
				profilePicPanel.removeAll();
				panel.removeAll();
				f.remove(profilePicPanel);
				f.remove(panel);
				
				new editPerson(person);
			}
		}
		
		class addFriendButtonListener implements ActionListener
		{
			/** Adds the profile to the user's friends list when the "add" button is pushed.
			@param e  whether or not the button is pushed. */
			public void actionPerformed(ActionEvent e)
			{
				Profile personProfile = m.findPerson(addFriendTextField.getText().toUpperCase().trim());
				
				if(personProfile == null)
				{
					JOptionPane.showMessageDialog(null, "Person not found");
				}
				else
				{
					person.addFriend(personProfile);
					JOptionPane.showMessageDialog(null, "Friend added. Refresh the page for the changes to be reflected.");
				}
				
			}
		}
		
		class deleteButtonListener implements ActionListener
		{
			/** Allows the user to delete their account when the "delete account" button is pushed.
			@param e  whether or not the button is pushed. */
			public void actionPerformed(ActionEvent e)
			{
				m.leaveNetwork(person);
				
				profilePicPanel.removeAll();
				panel.removeAll();
				f.remove(profilePicPanel);
				f.remove(panel);
				
				new addPerson();
				JOptionPane.showMessageDialog(null, "Account deleted");
			}
		}
		
		private class ListListener implements ListSelectionListener
		{
			/** Navigates to the intended friend's profile when selected.
			@param e  whether or not the item on the list is selected. */
			public void valueChanged(ListSelectionEvent e)
			{
				friendSelection = (String) friendsJList.getSelectedValue();
				Profile friend = m.findPerson(friendSelection);
				
				profilePicPanel.removeAll();
				panel.removeAll();
				f.remove(profilePicPanel);
				f.remove(panel);
				
				new profilePage(friend);
			}
		}
		
		
	} // end class profilePage
	
	
	
	
	/**	
	 * The editPerson class creates the page that allows you to edit the user's information
	 * */
	public class editPerson
	{
		private Profile person;
		private String status;
		
		private JLabel uploadLabel;
		private JTextField uploadTextField;
		private JButton uploadImgButton;

		private JPanel panel;
		private JPanel panel2;

		private GridBagConstraints c;
		
		/** Constructor for the editPerson class.
		@param person  the profile that is being edited. */
		public editPerson(Profile person) 
		{
			this.person = person;
			f.setTitle("Edit Account");
			Dimension windowSize = new Dimension(200,400);
			f.setMinimumSize(windowSize);
			f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			f.setLayout(new BorderLayout());
			buildEditPanel();
			f.add(panel, BorderLayout.NORTH);
			f.add(panel2, BorderLayout.CENTER);
			f.pack();	
			f.setVisible(true);
			
		}
		
		/** Builds the edit panel and allows the user to edit the profile's elements. */
		private void buildEditPanel() 
		{
			// Navigation button
			
			JButton returnButton = new JButton("Return to Profile");
			returnButton.addActionListener(new returnButtonListener());
			
			JLabel edit = new JLabel("Edit Profile");
			
			// Edit profile buttons and labels
			
			JLabel changeStatusLabel = new JLabel("Change status: ");
			JButton singleStatusButton = new JButton("Single");
			singleStatusButton.addActionListener(new singleListener());
			
			JButton marriedStatusButton = new JButton("Married");
			marriedStatusButton.addActionListener(new marriedListener());
			
			JButton changeStatusButton = new JButton("Submit Change of Status");
			changeStatusButton.addActionListener(new changeStatusListener());
			
			uploadLabel = new JLabel("New profile picture's pathfield:");
	        uploadTextField = new JTextField(20);
	        uploadImgButton = new JButton("Upload Image");
	        uploadImgButton.addActionListener(new changePicListener());
			
			// Set up for layout
			
			panel = new JPanel(new GridBagLayout());
			panel2 = new JPanel(new GridBagLayout());
			c = new GridBagConstraints();

			// Add header information
			
	        c.gridx = 0;
	        c.gridy = 0;
	        c.insets = new Insets(5,10,5,0);
	        panel.add(programName, c);
	        
			c.gridx = 1;
			panel.add(returnButton,c);
			
			// Add edit profile information
	        
			c.gridx = 1;
			c.gridwidth = 2;
			panel2.add(edit, c);
			
			c.gridx = 0;
			c.gridy = 1;
			c.gridwidth = 1;
			c.anchor = GridBagConstraints.LINE_END;
			panel2.add(changeStatusLabel,c);
			c.gridx = 1;
			c.anchor = GridBagConstraints.CENTER;
			panel2.add(singleStatusButton,c);
			c.gridx = 2;
			panel2.add(marriedStatusButton,c);
			c.gridx = 4;
			c.anchor = GridBagConstraints.LINE_START;
			panel2.add(changeStatusButton,c);
			
			c.gridx = 0;
			c.gridy = 2;
			c.anchor = GridBagConstraints.LINE_END;
			panel2.add(uploadLabel,c);
			c.gridx = 1;
			c.gridwidth = 2;
			c.anchor = GridBagConstraints.LINE_START;
			panel2.add(uploadTextField,c);
			c.gridx = 4;
			c.gridwidth = 1;
			panel2.add(uploadImgButton,c);
			
		}
		
		class returnButtonListener implements ActionListener
		{
			/** Navigates to the profile page when the "Return to Profile" button is pressed
			@param e  whether or not the choice is pushed. */
			public void actionPerformed(ActionEvent e)
			{
				panel.removeAll();
				panel2.removeAll();
				f.remove(panel);
				f.remove(panel2);
				
				new profilePage(person);
			}
		}
		
		class singleListener implements ActionListener
		{
			/** Changes the status to single when the "Single" is selected.
			@param e  whether or not the choice is pushed. */
			public void actionPerformed(ActionEvent e)
			{
				status = "Single";
			}
		}
		
		class marriedListener implements ActionListener
		{
			/** Changes the status to married when the "Married" is selected.
			@param e  whether or not the choice is pushed. */
			public void actionPerformed(ActionEvent e)
			{
				status = "Married";
			}
		}
		
		class changeStatusListener implements ActionListener
		{
			/** Updates the profile's status when the "Submit Change of Status" button is pushed.
			@param e  whether or not the button is pushed. */
			public void actionPerformed(ActionEvent e)
			{
				person.setStatus(status);
				JOptionPane.showMessageDialog(null, "Status changed to " + status);
			}
		}
		
		class changePicListener implements ActionListener
		{
			/** Updates the profile's picture when the "Upload Image" button is pushed.
			@param e  whether or not the button is pushed. */
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
		
	} // end class editPerson

	
	
	/** Starts the entire project */
	public static void main(String[] args)
	{
		ProfileManager m = new ProfileManager();

		System.out.println("Creating profiles and the network...");
		System.out.println();
		FaceNovel project = new FaceNovel(m);
		project.new addPerson();
		
	}
	
}// end class FaceNovel
