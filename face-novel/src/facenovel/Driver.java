package facenovel;

public class Driver
{
	public static void main(String[] args)
	{
		ProfileManager m = new ProfileManager();

      System.out.println("Creating profiles and the network.");

      Profile john = new Profile();
		john.setName("John", "Doe");
		john.setStatus("Married");
      m.addProfile(john);

		
	} // end main
} // end Drive

