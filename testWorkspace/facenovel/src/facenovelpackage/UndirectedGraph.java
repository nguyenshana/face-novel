package facenovelpackage;
import java.util.*;

public class UndirectedGraph<T extends Profile>
{

	private ArrayList<T> network;
	
	/** Constructor for an instance of an undirected graph. */
	public UndirectedGraph()
	{
		network = new ArrayList<T>();
	}

	/** Adds an object/vertex to the graph.
	@param vertex  The vertex to be added to the network. */
	public void addVertex(T vertex)
	{
		network.add(vertex);
	}

	/** Returns the network.
	@return the network of the graph */
	public ArrayList<T> getNetwork()
	{
		return network;
	}
	
	/** Removes an object/vertex from the graph.
	@param vertex  The vertex to be removed from the network. */
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
}
