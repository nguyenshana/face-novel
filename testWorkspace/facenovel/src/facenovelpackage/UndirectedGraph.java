package facenovelpackage;

import java.util.*;

public class UndirectedGraph<T extends Profile>
{

	private ArrayList<T> network;

	public UndirectedGraph()
	{
		network = new ArrayList<T>();
	}

	// add a person to the network
	public void addVertex(T vertex)
	{
		network.add(vertex);
	}

	public ArrayList<T> getNetwork()
	{
		return network;
	}
	
	public void remove(T vertex)
	{
		Profile toRemove = (Profile) vertex;
		ArrayList<Profile> friendList = toRemove.getFriends();
		// iterate thru friendList
		// 
		for(int i = 0; i < friendList.size(); i++)
		{
			Profile person = friendList.get(i); // a profile
			person.removeFriend(toRemove); // remove toRemove from person
		}
		network.remove(toRemove);
	}

	// add remove

}