/*
Name: Harlan Chang
Class: CS20B
Assignment: Project 3
*/

import java.util.*;
import java.io.*;

public class Run 
{
	public static void main(String[] args)
	{
		PackageTree myTree = new PackageTree();

		System.out.println(myTree.add("java.lang.String"));
		System.out.println(myTree.add("com.google.Search")); //should not add into the true because root is different
		System.out.println(myTree.add("java.lang.Boolean"));
		System.out.println(myTree.add("java.util.Scanner"));
		System.out.println(myTree.add("java.util.LinkedList"));
		System.out.println(myTree.add("java.lang.Double"));
		System.out.println("Size of the tree is: " + myTree.size());
		System.out.println("Height of the tree is: " + myTree.height());
		
		Iterator<PackageNode> myIter = myTree.iterator(); 
		
		while(myIter.hasNext()) //while there is soemthing next in the iterator 
		{
			System.out.println(myIter.next().getValue()); //prints out the value of the next one
		}
	}
}