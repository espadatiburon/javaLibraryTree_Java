import java.util.Iterator;

public class PackageTree implements Iterable
{
	private PackageNode root; //used to hold the root of the tree
	private int size; //used to store the size of the tree
	private int height; //used to store the biggest height
	private int tempHeight; //used to get a temp height while adding to the tree
	
	//default constructor
	public PackageTree()
	{
		//the default values of the tree
		//height and tempHeight are both -1 because an empty tree should be -1 height
		root = null; 
		size = 0;
		height = -1;
		tempHeight = -1;
	}

	//used to add to the tree
	public boolean add(String className)
	{
		String[] myClass = className.split("\\."); //splits the string by . into an array
		if(myClass.length == 0) //if there is no value that is being added
		{
			return false; 
		}
		else if(root == null) //if there is no root already created
		{
			root = new PackageNode(myClass[0]); //creates the root of the tree
			size++; 
			tempHeight++;
			height++;
			//adds one to both tempHeight and height because we know that now that there's a root, height is at least 0
		}
		else if(!root.getValue().equals(myClass[0])) //checks if the root is the same value as the first piece being added
		{
			return false;
		}
		add(root, 1, myClass); //calls a recursive method that helps add to the tree
		return true; //returns true because we already checked for the conditions where it isn't able to add
	}

	//recursive function to add into the tree 
	private void add(PackageNode myRoot, int index, String[] myClass)
	{
		if(index == myClass.length) //base case for when to stop the recursive function
		{
			if(tempHeight > height) //checks if the tempHeight is greater than the previous biggest height
			{
				height = tempHeight; //store the new biggest height
			}
			tempHeight = 0; //resets tempHeight to 0 regardless of whether there is a new biggest height or not
			return;
		}
		else if(myRoot.getChildren().isEmpty()) //checks if the root has any children
		{
			myRoot.addChildren(new PackageNode(myClass[index])); //adds a children to the root 
			size++;
			tempHeight++;
			add(myRoot.getChildren().get(myRoot.getChildren().size() - 1),index + 1,  myClass); //recursive method call
			return;
		}
		else
		{
			for(int i = 0; i < myRoot.getChildren().size(); i++) 
			//for loop used to go through the ArrayList of children so we can see if there is a matching one
			{
				if(myRoot.getChildren().get(i).getValue().equals(myClass[index]))
				//used to check if there is a matching value
				{
					tempHeight++;
					add(myRoot.getChildren().get(i), index + 1, myClass); //goes down that part of the tree 
					return;
				}
			}
			//if there is no matching value, we add another value to the children of the root 
			myRoot.addChildren(new PackageNode(myClass[index])); 
			size++;
			tempHeight++;
			add(myRoot.getChildren().get(myRoot.getChildren().size() - 1), index + 1, myClass); //recursive call
			return;
		}
	}

	public boolean contains(String className)
	{
		String[] myPackages = className.split("\\."); //splits the string into an array by .
		PackageNode temp = root; //gets the root of the tree

		for(int i = 0; i < myPackages.length; i++) //for loop used to go through each value of the className
		{
			if(temp.getValue().equals(myPackages[i])) //checks if the value is equal 
			{
				boolean inside = false;
				if(i == myPackages.length - 1)  //if it is on the last piece of className
				{
					return true; //we know it is inside the tree, so we just return true
				}
				for(int h = 0; h < temp.getChildren().size(); h++) //goes through the children of the root
				{
					if(temp.getChildren().get(h).getValue().equals(myPackages[i+1])) //checks if the value is inside
					{
						temp = temp.getChildren().get(h); //goes down the tree because it is equal
						inside = true; //we know that it is inside 
						break; //breaks out of the for loop because we found what we wanted
					}
				}

				if(!inside) //if its not inside, then return false
				{
					return false;
				}
			}
			else //return false because it isnt the same as the root
			{
				return false;
			}
		}
		return true; //return true if none of the false conditions are fulfilled
	}


	//height and size are already both defined when adding, so we just return the values
	public int height()
	{
		return height;
	}
	
	public int size()
	{
		return size;
	}

	public Iterator<PackageNode> iterator()
	{
		return new LeafIterator(root);
	}

}