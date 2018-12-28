import java.lang.Math;

/**
 * @Programmer Zeel Shah
 * @Date Oct 15 2018
 * @StudentNo 250970094
 * 
 * This program implements a dictionary using a hash table. The dictionary stores all the possible configurations
 * that may happen.
 */
public class HashDictionary implements DictionaryADT {

	private SinglyLL table[]; // initialize the table

	/**
	 * The constructor initializes the HashDictionary object
	 * @param size of the dictionary
	 */
	public HashDictionary(int size)
	{
		//create a table of the size that is given and for each element add a singly linked list to it
		table = new SinglyLL[size];
		for (int i=0; i<size; i++)
		{
			table[i] = new SinglyLL();
		}
	}

	/**
	 * This method inserts the given pair into the dictionary
	 * @param pair
	 * @throws DictionaryException when the pair already exists in the list
	 * @return 1 if the list at the location given by the hash table already stores at;east one element 
	 * @return 0 if the list at the location given by the hash table is empty before the pair is stored
	 */
	public int put(Configuration pair) throws DictionaryException
	{
		// calculate the Hashcode of the data item
		int val = hash(pair.getStringConfiguration(),(int)Math.sqrt(pair.getStringConfiguration().length()));
		//check to see if the list at the location is empty or not
		int confirm = table[val].insert(pair);

		// if the confirm value is 1 it means there was already an element in the list before it 
		if (confirm==1)
		{
			return 1;
		}
		// if the confirm value is 0 it means there weren't any elements in the list before the new pair was added
		else if (confirm==0)
		{
			return 0;
		}
		//if the confirm value is -1 throw the dictionary exception because the pair already exists in the list
		else 
		{
			throw new DictionaryException("The data already exists in the dictionary");
		}
	}

	/**
	 * This method removes the given string configuration and it's score from the dictionary.
	 * @param configuration
	 * @throws Dictionary Exception if the configuration is not in the dictionary 
	 */
	public void remove(String config) throws DictionaryException
	{
		//calculate the hash code for the data item
		int val = hash(config,(int)Math.sqrt(config.length()));
		
		// check to see if the configuration was removed or not
		boolean confirm = table[val].remove(config);
		
		// if the configuration was not removed then throw the Dictionary Exception
		if (!confirm)
			throw new DictionaryException("The configuration was not in the dictionary");
	}
	
	/**
	 * This method gets the score associated with the configuration
	 * @param configuration
	 * @return score
	 */
	public int getScore(String config)
	{
		//get the hash code
		int val = hash(config,(int)Math.sqrt(config.length()));
		//return the score 
		return table[val].find(config);
	}
	
	/**
	 * This method calculates the hash code
	 * @param code
	 * @param multiplier
	 * @return the hash code
	 */
	private int hash(String code,int multiplier)
	{
		int value=(int) code.charAt(code.length()-1);
		for(int i=code.length()-2; i>=0; i--)
		{
			value=((value*multiplier +(int)code.charAt(i))%table.length);
		}
		return value;
	}

}