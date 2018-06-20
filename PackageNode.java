import java.util.ArrayList;
import java.util.List;

public class PackageNode
{
	String value; //value fo the node
	List<PackageNode> children = new ArrayList<PackageNode>(); //ArrayList used to store the children because we can have N amount of children

	//used to create a Node
	public PackageNode(String myValue) 
	{ 
		value = myValue;
	}

	//used to check if the current node is a leaf
	public boolean isLeaf()
	{
		return children.isEmpty();
	}

	//used to get the value of the node
	public String getValue()
	{
		return value;
	}

	//add children to the end of the children arraylist
	public void addChildren(PackageNode myValue)
	{
		children.add(myValue);
	}

	//returns the children ArrayList
	public List<PackageNode> getChildren()
	{
		return children;
	}

}