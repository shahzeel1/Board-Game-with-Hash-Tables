/**
 *@Programmer: Zeel Shah
 *@Date Oct 15 2018
 *@StudentNo 250970094
 *this class creates node objects for the linked list
 */
public class Node {
	
	// initialize the variable
	private Node next;
	private Configuration data;
	
	/**
	 * Constructor create node with data
	 * @param data
	 */
	public Node (Configuration data)
	{
		this.next=null;
		this.data=data;
	}

	/**
	 * Getter class to get the next value
	 * @return the next value
	 */
	public Node getNext()
	{
		return next;
	}
	
	/**
	 * Setter method to set the next value 
	 * @param nextNode
	 */
	public void setNext(Node nextNode)
	{
		this.next = nextNode;
	}
	
	
	/**
	 * Getter method to get the data stored in the node 
	 * @return
	 */
	public Configuration getData()
	{
		return this.data;
	}
	
	/**
	 * Setter method to set the Score
	 * @param newData
	 */
	
	public void setScore(Configuration newData)
	{
		this.data = newData;
	}
	
}
