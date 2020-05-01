package facenovel;
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

	// add remove

}
