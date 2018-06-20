import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class LeafIterator implements Iterator<PackageNode> 
{
	private PackageNode next; //the next node
	private Queue<PackageNode> myQueue = new LinkedList<PackageNode>(); //creates a queue

	public LeafIterator(PackageNode root)
	{
		add(root); //adds the node into the queue
		this.next = myQueue.poll(); //next is whatever is in the queue
	}

	public void add(PackageNode myRoot)
	{
		if (myRoot.getChildren().isEmpty()) //checks if the root has any children 
		{
			myQueue.add(myRoot); //adds into the queue if it doesn't because we know this is a leaf
		} 
		else
		{
			for(int i = 0; i < myRoot.getChildren().size(); i++) 
			//otherwise, add each children it has
			{
				add(myRoot.getChildren().get(i));
			}
		}

	}

	//checks if there is a next one
	public boolean hasNext() 
	{
		if(next != null) //if there is a next then return true, otherwise false
		{
			return true; 
		}
		else
		{
			return false;
		}
	}

	//get the next value
	public PackageNode next() 
	{
		//pops out the next value and gets a new next 
		PackageNode myNode = next;
		next = myQueue.poll();
		return myNode;

	}

}