package facenovelpackage;

import java.util.ArrayList;

/**
	An implementation of a profile manager on a simple social network.
	@author Jesse Grabowski
	@author Joseph Erickson
	@version 5.0 */

public class ProfileManager
{
	private UndirectedGraph<Profile> allProfiles;

	/** Constructor for an instance of a profile manager. */
	public ProfileManager()
	{
		allProfiles = new UndirectedGraph<>();
	} // end default constructor

	/** Adds a profile to the social network.
		@param p  The profile to be added to the network. */
	public void addProfile(Profile p)
	{
		allProfiles.addVertex(p);
	} // end addProfile

	/** Creates a friendship between two users on the social network.
		@param a  The first profile in the friendship.
		@param b  The second profile in the friendship. */
	public void createFriendship(Profile a, Profile b)
	{
		a.addFriend(b);
		b.addFriend(a);
	} // end createFriendship

	/** Removes given profile from the network.
		@param person  The profile that's leaving the network. */
	public void leaveNetwork(Profile person)
	{
		allProfiles.remove(person);
	}
	
	/** Searches for the profile of the person with the given name and returns the profile if found. If not found, returns null.
		@param searchName  The full name of the person that we are searching.
		@return the profile of the person. */
	public Profile findPerson(String searchName)
	{
		for(Profile person : allProfiles.getNetwork()) 
		{
			if(person.getName().equals(searchName)) 
			{
				return person;
			}
		}
		return null;
	}
	
	/** Displays each profile's information and friends. 
	 	@param startPoint  The first profile that we will display. */
	public void display(Profile startPoint)
	{
		ArrayList<Profile> network = allProfiles.getNetwork();
		int startIndex = network.indexOf(startPoint);
		for(int i = startIndex; i < network.size(); i++)
		{
			network.get(i).display();
		}
	}
} // end ProfileManager
