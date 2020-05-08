package facenovel;
import java.awt.image.*;
import java.util.*;

public class ProfileManager
{
    private UndirectedGraphs<Profile> allProfiles;

    public ProfileManager()
    {
        allProfiles = new UndirectedGraphs<>();
    } // end default constructor


    public void addProfile(Profile p)
    {
        allProfiles.addVertex(p);
    } // end addProfile

    public void createFriendship(Profile a, Profile b)
    {
        a.addFriend(b);
        b.addFriend(a);
    } // end createFriendship

    public void leaveNetwork(Profile person)
    {
        allProfiles.remove(person);
    }

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

    /** Displays each profile's information and friends. */
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