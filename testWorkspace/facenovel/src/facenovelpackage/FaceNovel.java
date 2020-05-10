package facenovelpackage;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
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
	
	/** Constructor for FaceNovel class.
	@param m  an instance of ProfileManager. */
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
		
	    /** Constructor for addPerson class. */
		public addPerson()
		{
			f.setTitle("FaceNovel");
			windowsize = new Dimension(500, 500);
			f.setMinimumSize(windowsize);
			f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			buildPanel();
			f.add(panel);
	        f.getContentPane().add(panel, BorderLayout.NORTH);
			f.pack();
			f.setVisible(true);
		}
		
		/** Builds the panel. */
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
			
			panel = new JPanel(new GridBagLayout());
			
			uploadLabel = new JLabel("Enter your image's pathfield here:");
	        uploadTextField = new JTextField(20);
	        
	        GridBagConstraints c = new GridBagConstraints();
	        c.gridx = 0;
	        c.gridy = 0;
	        c.insets = new Insets(0,0,5,5);
			panel.add(navToSearchPerson, c);
			
			c.gridy = 1;
			panel.add(firstNameLabel,c );
			c.gridx = 1;
			panel.add(firstNameTextField,c);
			
			c.gridx = 0;
			c.gridy = 2;
			panel.add(lastNameLabel,c);
			c.gridx = 1;
			panel.add(lastNameTextField,c);
			
			c.gridx = 0;
			c.gridy = 3;
			panel.add(statusLabel,c);
			c.gridx = 1;
			panel.add(singleButton,c);
			c.gridx = 2;
			panel.add(marriedButton,c);
			
			c.gridx = 0;
			c.gridy = 4;
			panel.add(uploadLabel,c);
			c.gridx = 1;
	        panel.add(uploadTextField,c);
	        
	        c.gridx = 0;
	        c.gridy = 5;
	        panel.add(addButton,c);
	        
			
		}
		
		
		class goToSearch implements ActionListener
		{
			/** Performs the search when the search button is pushed. 
			@param e whether or not the button is pushed. */
			
			public void actionPerformed(ActionEvent e)
			{
				panel.removeAll();
				f.remove(panel);
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
			            m.addProfile(toAdd);
						toAdd.display();
						
						panel.removeAll();
						f.remove(panel);
						
						new searchPerson();
					} 
					catch (IOException a) 
					{
			            JOptionPane.showMessageDialog(null, "Invalid image, but account created."); //if image was not uploaded
					    toAdd.setProfilePicture(null);
			            m.addProfile(toAdd);
						toAdd.display();
						
						panel.removeAll();
						f.remove(panel);
						
						new searchPerson();
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
		
		/** Constructor for searchPerson class. */
		public searchPerson() 
		{
			f.setTitle("Search Person");
			windowsize = new Dimension(500, 500);
			f.setMinimumSize(windowsize);
			f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			buildPanel();
			f.add(panel);
			f.getContentPane().add(panel, BorderLayout.NORTH);
			f.pack();
			f.setVisible(true);
		}
	
		/** Builds the panel. */
		public void buildPanel()
		{
			navToAdd = new JButton("Create an Account");
			navToAdd.addActionListener(new goToAdd());
			
			messageLabel = new JLabel("Enter the person's first and last name:");
			textField = new JTextField(20);
			
			searchButton = new JButton("Search");
			searchButton.addActionListener(new SearchButtonListener());
			
			panel = new JPanel(new GridBagLayout());
			
			GridBagConstraints c = new GridBagConstraints();
	        c.gridx = 0;
	        c.gridy = 0;
	        c.insets = new Insets(0,0,5,5);
			panel.add(navToAdd,c);
			
			c.gridx = 0;
			c.gridy = 1;
			panel.add(messageLabel,c);
			c.gridx = 1;
			panel.add(textField,c);
			
			c.gridx = 0;
			c.gridy = 2;
			panel.add(searchButton,c);
		}
		
		class goToAdd implements ActionListener
		{
			/** Goes back to the add page when the "create an account" button is pushed.
			@param e whether or not the button is pushed. */
			public void actionPerformed(ActionEvent e)
			{
				panel.removeAll();
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
		
		private GridBagConstraints c;
		
		/** Constructor for profilePage.
		@param person  the specified profile. */
		public profilePage(Profile person) {
			
			this.person = person;
			setTitle("Profile");
			Dimension windowSize = new Dimension(600, 500);
			setMinimumSize(windowSize);
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setLayout(new BorderLayout());
			createProfilePanel(person);
			createImagePanel();
			add(profilePicPanel, BorderLayout.NORTH);
			add(panel, BorderLayout.CENTER);
			f.pack();
			pack();
			
			setVisible(true);
		}
		
		/** Creates a new panel for the given profile.
		@param person  the specified profile. */
		private void createProfilePanel(Profile person) {
			
			ArrayList<Profile> friendsList = person.getFriends();
			
			panel = new JPanel(new GridBagLayout());
			
			JButton editProfileButton = new JButton("Edit Profile");
			editProfileButton.addActionListener(new editButtonListener());
			
			c = new GridBagConstraints();
	        c.gridx = 0;
	        c.gridy = 0;
	        c.insets = new Insets(5,5,10,5);
			panel.add(editProfileButton,c);
			
			JButton deleteButton = new JButton("Delete Account");
			deleteButton.addActionListener(new deleteButtonListener());
			
			c.gridx = 1;
			panel.add(deleteButton,c);
			
			addFriendLabel = new JLabel("Add a friend: ");
			addFriendTextField = new JTextField(20); 
			JButton addFriendButton = new JButton("Add");
			addFriendButton.addActionListener(new addFriendButtonListener());
			
			c.gridx = 0;
			c.gridy = 1;
			panel.add(addFriendLabel,c);
			c.gridx = 1;
			panel.add(addFriendTextField,c);
			c.gridx = 2;
			panel.add(addFriendButton,c);
			

            // convert ArrayList to Array
            if(friendsList.size() > 0)
            {
            	c.gridx = 0;
    			c.gridy = 2;
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
                panel.add(friendsJList,c);
            }
		
			
		}
		
		class editButtonListener implements ActionListener
		{
			/** Navigates the user to the edit profile page when the "edit profile" button is pushed.
			@param e  whether or not the button is pushed. */
			public void actionPerformed(ActionEvent e)
			{
				panel.removeAll();
				dispose();
				new editPerson(person);
			}
		}
		
		class addFriendButtonListener implements ActionListener
		{
			/** Adds the profile to the user's friends list when the "add" button is pushed.
			@param e  whether or not the button is pushed. */
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
					JOptionPane.showMessageDialog(null, "Friend added. You may now close the Profile page and the changes will be relected.");
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
				dispose();
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
				
				profilePage friendProfile = new profilePage(friend);
			}
		}
		
		/** Creates an image panel for the profile picture. */
		private void createImagePanel() 
		{
			profilePicPanel = new JPanel(new GridBagLayout());
			c = new GridBagConstraints();

			if(person.getProfilePicture() != null)
			{
				JLabel pic = new JLabel();
				pic.setSize(80, 80);
				BufferedImage img = person.getProfilePicture();
				Image dimg = img.getScaledInstance(pic.getWidth(), pic.getHeight(),
				        Image.SCALE_SMOOTH);
				ImageIcon imageIcon = new ImageIcon(dimg);
				pic.setIcon(imageIcon);
				
		        c.gridx = 0;
		        c.gridy = 0;
		        c.insets = new Insets(5,5,5,5);
				profilePicPanel.add(pic);
			}
			
			nameLabel = new JLabel(person.getName());
			
			statusLabel = new JLabel(person.getStatus());
					
			c.gridy = 1;
			profilePicPanel.add(nameLabel,c);
			
			c.gridy = 2;
			profilePicPanel.add(statusLabel,c);
			
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
		private JPanel panel2;
		private JPanel panel3;

		private GridBagConstraints c;
		
		/** Constructor for the editPerson class.
		@param person  the profile that is being edited. */
		public editPerson(Profile person) 
		{
			this.person = person;
			setTitle("Edit Account");
			Dimension windowSize = new Dimension(200,200);
			setMinimumSize(windowSize);
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setLayout(new BorderLayout());
			buildEditPanel();
			add(panel, BorderLayout.NORTH);
			add(panel2, BorderLayout.CENTER);
			add(panel3, BorderLayout.SOUTH);
			pack();	
			setVisible(true);
			
		}
		
		/** Builds the edit panel and allows the user to edit the profile's elements. */
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
			
			panel = new JPanel(new GridBagLayout());
			panel2 = new JPanel(new GridBagLayout());
			panel3 = new JPanel(new GridBagLayout());
			
			c = new GridBagConstraints();
	        c.gridx = 0;
	        c.gridy = 0;
	        c.insets = new Insets(5,5,5,5);
			panel.add(changeStatusLabel,c);
			c.gridx = 1;
			panel.add(singleStatusButton,c);
			c.gridx++;
			panel.add(marriedStatusButton,c);
			c.gridx++;
			panel.add(changeStatusButton,c);
			
			c.gridx = 0;
			c.gridy = 0;
			panel2.add(uploadLabel,c);
			c.gridx++;
			panel2.add(uploadTextField,c);
			c.gridx++;
			panel2.add(uploadImgButton,c);
			
			JLabel infoText = new JLabel("When finished, close this window and the changes will be relected.");
			c.gridx = 0;
			c.gridy = 0;
			panel3.add(infoText,c);
			
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
