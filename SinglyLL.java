/**
 * @Programmer Zeel Shah
 * @Date Oct 15 2018
 * @StudentNo 250970094
 * This class creates a linked list object that stores Nodes. Through the class you can insert 
 * new nodes, remove nodes and find specific nodes. 
 */

public class SinglyLL {

	// initialize variables 
	Node head;
	int numOfData;

	/**Constructor initializes the linked list 
	 */
	public SinglyLL()
	{
		head=null;
		numOfData=0;

	}
	
	/** this method inserts the data of type Configuration into the list after checking it isn't already in the list
	 * @param data
	 * @return -1 if the data already exists in the list
	 * @return 1 a new node is added to a list that already had previous data items stored
	 * @return 0 if a new node is added to a brand new list
	 */
	public int insert (Configuration data)
	{
 		// if the list already has one or more Configurations in it check to make sure there are no duplicates, if there aren't any insert the data into the list
		if (numOfData>0)
		{
			
			if(LinearSearch(data))
			{
				return -1;
			}
			else
			{
				//add the data to the list
				Node newNode = new Node(data);
				newNode.setNext(head);
				head=newNode;
				numOfData++;
			}
			return 1;			
		}
		else // if the list is empty then add the data as the first element
		{	
			head = new Node(data);
			numOfData++;
			return 0;
		}
	}
	
	/**
	 * This method removes the configuration from the list 
	 * @param configuration
	 * @return true if the configuration was removed successfully 
	 * @return false if nothing was removed
	 */
	public boolean remove(String config) {
		Node pointer=head;
		int position;

		//if the number of data is greater than 0 it means there are elements in the list so the program needs to check it through
		if(numOfData>0)
		{
			// if the data exists in the list then get the position where it occurs
			position = LinearSearchString(config);
			// if the position is 0 that means the head is where the configuration is, and we can now remove it from the list
			if (position==0)
			{
				head = pointer.getNext();
				numOfData--;
				return true;
			}
			//if the position is less than the number of data in the list that means the data exists at the position and can be removed
			else if (position<numOfData)
			{
				for (int i=0; i<position-1; i++) 
					pointer = pointer.getNext(); 
				Node next = pointer.getNext().getNext(); 

				pointer.setNext(next);
				numOfData--;
				return true;
			}
			//if the program reaches here it means that the configuration is not in the list  
			else 
				return false;			
		}
		//there are no data elements in the list so that means there is nothing to remove 
		else 
			return false;

	}
	
	/**
	 * this method finds the score that is associated with the configuration
	 * @param configuration
	 * @return the position where the configuration occurs
	 * @return -1 if the configuration was not found
	 */
	public int find(String config)
	{
		
		String compare;
		Node pointer = head;
		
		//search the list if the configuration is found return the score
		for (int i=0; i<numOfData; i++)
		{
			compare = pointer.getData().getStringConfiguration();
			if (compare.equals(config))
				return pointer.getData().getScore();
			pointer=pointer.getNext();
		}
		//if configuration is not found return -1
		return -1;
	}
	
	/**
	 * this method searches through the list and sees if the configuration exists 
	 * @param data
	 * @return true if the data was found in the list
	 * @return false if the data was not found in the list
	 */
	private boolean LinearSearch(Configuration data)
	{
		int i =0;//increment value
		Node pointer =head;
		/* while there are still elements in the list and the string configuration of the pointer doesn't equal the 
		 * string configuration of the data then keep going through the list*/
		while (i<numOfData && pointer.getData().getStringConfiguration()!=data.getStringConfiguration())
			{
			i++;
			pointer = pointer.getNext();
			}
		/* If the increment value doesn't equal 0 but is there it's less than the number of data elements in the list
		 * or if the increment value is 0 and the head stores the data return true
		 */
		if (i!=0 && i<numOfData || i==0 & head.getData().getStringConfiguration()==data.getStringConfiguration())
		return true;		
		
		// if the data does not exist return false
		return false;

	}
	
	/**
	 * This method searches the list to see if the string configuration already exists in the list
	 * @param config
	 * @return the position the configuration occurs at 
	 * @return the number if data elements in the list if the string configuration is not found
	 */
	private int LinearSearchString(String config)
	{
		String compare;
		Node pointer = head;
		
		//check each element's string value in the list, if it equals the configuration return the position where the data is located
		//if the string configuration does not exist the number of data elements in the list will be returned
		for (int i=0; i<numOfData; i++)
		{
			compare = pointer.getData().getStringConfiguration();
			if (compare.equals(config))
				return i;
			pointer=pointer.getNext();
		}
		return numOfData;


	}

}



