/**
 * @Programmer: Zeel Shah
 * @Date: Oct 15, 2018
 * @StudentNo 250970094
 * 
 * This class creates a Configuration object which stores a string configuration and it's score 
 */
public class Configuration  {
	
	// initialize the variables 
	private String configuration;
	private int configScore;
	
	// this constructor stores the configuration and the score in the object
	public Configuration(String config, int score)
	{
		configuration = config;
		configScore=score;
		
	}
	
	// this method returns the string configuration
	public String getStringConfiguration()
	{
		return configuration;
	}
	// this method returns the score 
	public int getScore()
	{
		return configScore;
	}
	
}
