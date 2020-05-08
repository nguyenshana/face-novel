package facenovelpackage;

public class Driver
{
	public static void main(String[] args)
	{
		ProfileManager m = new ProfileManager();

		System.out.println("Creating profiles and the network...");
		System.out.println();

		Profile john = new Profile();
		john.setName("John", "Doe");
		john.setStatus("Married");
		m.addProfile(john);
		
		Profile joe = new Profile();
		joe.setName("Joe", "Mama");
		joe.setStatus("Single");
		m.addProfile(joe);
		
		m.createFriendship(john, joe);
		
		Profile sunny = new Profile();
		sunny.setName("Sunny", "Mistry");
		sunny.setStatus("Single");
		m.addProfile(sunny);
		
		m.leaveNetwork(john);
		
		m.display(joe);
		System.out.println();

		
	} // end main
} // end Drive
