package ca.queensu.cs.cisc235.tree;

import java.util.ArrayList;
import java.lang.Math;

public class A3 {

	/**
	 * Adds an element to a binary tree by specifying a path to a leaf node and
	 * direction to add the element at the leaf node.
	 * 
	 * <p>
	 * If the tree is empty, then the path must also be empty. In this case, the
	 * element is added as the root node of the tree.
	 * 
	 * <p>
	 * If the path has more than 1 element, then the second last element of the path
	 * must end in a leaf node of the tree; if it does not then the element cannot
	 * be added to the tree.
	 * 
	 * <p>
	 * The last element of the path points in the direction that the element should
	 * be added to the tree. If the last path element is {@code LR.LEFT} then the
	 * element is added as the left child of the leaf node. If the last path element
	 * is {@code LR.RIGHT} then the element is added as the right child of the leaf
	 * node.
	 * 
	 * @param t    a random binary tree
	 * @param elem the element to add to the tree
	 * @param path a sequence of 0 or more left-right directions
	 * @return true if the element was added to the tree, false otherwise
	 */
	//Q1
	public static boolean add(RandomBinaryTree<Integer> t, Integer elem, LR... path) {
		int pathsize=path.length;
		int currentp=0;
		BinaryNode<Integer> insertion=new BinaryNode<Integer>(elem);
		//add to the root
		if(pathsize==0) {
			if(t.root==null) {
				t.setRoot(insertion);
				t.size++;
				return true;
			}
			else {
				System.out.println("existing root");
				t.size++;
				return false;
			}
		}
		//add to the leaf nodes
		BinaryNode<Integer> currentnode=t.root;
		while(currentp<path.length-1) {
			if (path[currentp]==LR.LEFT){
				currentnode=currentnode.left;
			}else
			{
				currentnode=currentnode.right;
			}
			if(currentnode==null) {
				System.out.println("hitting null nodes");
				t.size++;
				return false;
			}
			currentp+=1;
		}
		if (path[path.length-1]==LR.LEFT){
			if(currentnode.left==null) {
				currentnode.setLeft(insertion);
			}else {
				System.out.println("existing node");
				t.size++;
				return false;
			}
		}else
		{
			if(currentnode.right.elem==null) {
				currentnode.setRight(insertion);
			}else {
				System.out.println("existing node");
				t.size++;
				return false;
			}
		}
		t.size++;
		return true;
	}

	/**
	 * Adds an element to a binary search tree by specifying a path to a leaf node
	 * and direction to add the element at the leaf node.
	 * 
	 * <p>
	 * This method is identical to
	 * {@code add(RandomBinaryTree<Integer> t, Integer elem, LR... path)} except
	 * that this method tests if the specified path adheres to the binary search
	 * tree property. If the path does not adhere to the binary search property then
	 * the element is not added to the tree.
	 * 
	 * @param t    a random binary tree
	 * @param elem the element to add to the tree
	 * @param path a sequence of 0 or more left-right directions
	 * @return true if the element was added to the tree, false otherwise
	 */
	//Q2
	public static boolean add(BinarySearchTree<Integer> t, Integer elem, LR... path) {
		int pathsize=path.length;
		int currentp=0;
		BinaryNode<Integer> insertion=new BinaryNode<Integer>(elem);
		//add to the root
		if(pathsize==0) {
			if(t.root()==null) {
				t.setRoot(insertion);
				t.size++;
				return true;
			}
			else {
				System.out.println("existing root");
				t.size++;
				return false;
			}
		}
		//add to the leaf nodes
		BinaryNode<Integer> currentnode=t.root;
		while(currentp<path.length-1) {
			if (path[currentp]==LR.LEFT){
				if(elem<currentnode.elem) {
					currentnode=currentnode.left;	
				}else {
					System.out.println("Invalid path");
					t.size++;
					return false;
				}
			}else
			{
				if(elem>=currentnode.elem) {
					currentnode=currentnode.right;	
				}else {
					System.out.println("Invalid path");
					t.size++;
					return false;
				}
			}
			if(currentnode==null) {
				System.out.println("hitting null nodes");
				t.size++;
				return false;
			}
			currentp+=1;
		}
		if (path[path.length-1]==LR.LEFT){
			if(currentnode.left==null) {
				if(elem<currentnode.elem) {
					currentnode.setLeft(insertion);	
				}else {
					System.out.println("Invalid path");
					t.size++;
					return false;
				}
			}else {
				System.out.println("existing node");
				t.size++;
				return false;
			}
		}else
		{
			if(currentnode.right==null) {
				if(elem>=currentnode.elem) {
					currentnode.setRight(insertion);	
				}else {
					System.out.println("Invalid path");
					t.size++;
					return false;
				}
			}else {
				System.out.println("existing node");
				t.size++;
				return false;
			}
		}
		t.size++;
		return true;	
	}
	
	/**
	 * Returns the height of the specified binary tree. The height of a tree
	 * is the length of the longest downward path from the root to a leaf node.
	 * 
	 * @param <E> the element type of the tree
	 * @param t a binary tree
	 * @return the height of the tree
	 */
	//Q3
	public static <E> int height(BinaryTree<E> t) {
		return h(t.root);
	}
	//helper method to calculate the height recursively
	public static <E> int h(BinaryNode<E> N) {
		int leftheight=0;
		int rightheight=0;
		if(N.hasLeft()) {
			leftheight=1+h(N.left);
		}
		if(N.hasRight()) {
			rightheight=1+h(N.right);
		}
		if(leftheight==0&rightheight==0) {
			return 0;
		}else if (leftheight>rightheight) {
			return leftheight;
		}
		else {
			return rightheight;
		}
	}
	//Q4
	//using randombinary tree
	
	public static void simulation_4(){
		int[] binaries= {128, 256, 512, 1024, 2048, 4096, 8192, 16384};
		ArrayList<Integer>averageheights =new ArrayList<>();
		ArrayList<Integer>maxheights =new ArrayList<>();
		for (int n :binaries) {
			ArrayList<Integer>heights =new ArrayList<>(500);
			int maximumheight=0;
			int sum=0;
			for(int i=1;i<=500;i++) {
				RandomBinaryTree<Integer> t=new RandomBinaryTree<>();
				ArrayList<Integer> v = permutation(n);
				for(Integer s :v) {
					t.add(s);
				}
				int h = height(t);
				heights.add(h);
				if(h>maximumheight) {
					maximumheight=h;
				}				
			}
			for(int ht: heights) {
				sum=sum+ht;
			}
			int average=(int)(sum/500);
			averageheights.add(average);
			maxheights.add(maximumheight);
		}
		System.out.println("Simualtion using Random Binary tree");
		System.out.println("Average heights:");
		for(int n :averageheights) {
			System.out.println(n);
		}
		System.out.println("Maximum heights:");
		for(int n :maxheights) {
			System.out.println(n);
		}
	}
	
	//Q5
	//using binary search tree
	public static void simulation_5(){
		int[] binaries= {128, 256, 512, 1024, 2048, 4096, 8192, 16384};
		ArrayList<Integer>averageheights =new ArrayList<>();
		ArrayList<Integer>maxheights =new ArrayList<>();
		for (int n :binaries) {
			ArrayList<Integer>heights =new ArrayList<>(500);
			int maximumheight=0;
			int sum=0;
			for(int i=1;i<=500;i++) {
				BinarySearchTree<Integer> t=new BinarySearchTree<>();
				ArrayList<Integer> v = permutation(n);
				for(Integer s :v) {
					t.add(s);
				}
				int h = height(t);
				heights.add(h);
				if(h>maximumheight) {
					maximumheight=h;
				}				
			}
			for(int ht: heights) {
				sum=sum+ht;
			}
			int average=(int)(sum/500);
			averageheights.add(average);
			maxheights.add(maximumheight);
		}
		System.out.println("Simualtion using Binary search tree");
		System.out.println("Average heights:");
		for(int n :averageheights) {
			System.out.println(n);
		}
		System.out.println("Maximum heights:");
		for(int n :maxheights) {
			System.out.println(n);
		}
	}
	public static ArrayList<Integer> permutation(int num) {
		//the original sorted list
		ArrayList<Integer> l = new ArrayList<>(num);  
		//the result of permutation
		ArrayList<Integer> results=new ArrayList<>(num);
		//fill the list with numbers from 1 to
        for (int i = 1; i <=num; i++) {
        	l.add(i);  
        }
        while(l.size()>0) {
        	int n = l.size()-1;
        	int index = (int)(Math.random() * n);
        	int temp = l.get(index);
            l.remove(index); 
            results.add(temp);
        }
        return results;
        
	} 
	public static void main(String[] args) {
		// RandomBinaryTree<Integer> t = new RandomBinaryTree<>();
		/*BinarySearchTree<Integer> t = new BinarySearchTree<>();
		boolean b;
		b = add(t, 5);
		System.out.println(b);
	    b = add(t, 3, LR.LEFT);
		System.out.println(b);
		b = add(t, 1, LR.LEFT, LR.LEFT);
		System.out.println(b);
		b = add(t, 2, LR.LEFT, LR.LEFT, LR.RIGHT);
		System.out.println(b);
		b = add(t, 4, LR.LEFT, LR.RIGHT);
		System.out.println(b);
		b = add(t, 7, LR.RIGHT);
		System.out.println(b);
		b = add(t, 6, LR.RIGHT, LR.LEFT);
		System.out.println(b);
		b = add(t, 9, LR.RIGHT, LR.RIGHT);
		System.out.println(b);
		b = add(t, 8, LR.RIGHT, LR.RIGHT, LR.LEFT);
		System.out.println(b);
		b = add(t, 10, LR.RIGHT, LR.RIGHT, LR.RIGHT);
		System.out.println(b);
		System.out.println(Traversals.toString(t));
		System.out.println(height(t));*/
		simulation_4();
		simulation_5();
	}
}
