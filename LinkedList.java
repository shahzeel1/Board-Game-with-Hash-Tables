
public class LinkedList {
	Node head;
	int numOfData;
	String emoji;
	public LinkedList()
	{
		head=new Node(null);
		numOfData=0;
		System.out.println(":)");
		emoji=":)";
		
	}

	public int insert (Configuration data)
	{
		if (numOfData>0)
		{
			if(LinearSearch(data))
			{
				return -1;
			}
			else
			{
				Node newNode = new Node(data);
				newNode.setNext(head);
				head=newNode;
				numOfData++;
			}
			return 1;			
		}
		else
		{
			head = new Node(data);
			numOfData++;
			return 0;
		}
	}
	public boolean remove(String config) {
		Node pointer=head;
		int position;
		//check if the string is a data node in the list


		//check if the data is in the list
		if(numOfData>0)
		{
			position = LinearSearchString(config);
			if (position==0)
			{
				head = pointer.getNext();
				return true;
			}
			else if (position<numOfData)
			{
				for (int i=0; i<position-1; i++) 
					pointer = pointer.getNext(); 
				Node next = pointer.getNext().getNext(); 

				pointer.setNext(next);
				return true;
			}
			else 
				return false;			
		}
		else 
			return false;

	}
	public int find(String code)
	{
		String compare;
		Node pointer = head;
		for (int i=0; i<numOfData; i++)
		{
			compare = pointer.getData().getStringConfiguration();
			if (compare.equals(code))
				return pointer.getData().getScore();
			pointer=pointer.getNext();
		}
		return -1;
	}
	private boolean LinearSearch(Configuration data)
	{
		int i =0;
		Node pointer =head;
		while (i<numOfData && pointer.getNext().getData()!=data)
			i++;
		return i<numOfData;

	}
	private int LinearSearchString(String config)
	{
		String compare;
		Node pointer = head;
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
