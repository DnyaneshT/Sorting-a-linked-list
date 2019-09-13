
//DSA program to create recursive linkedlist and sort it.
//Selection sort
//Implemented swapping of nodes 

import java.util.Random;

public class ll {

	public class Node {
		Node next;
		int data;
	}

	// create node with data data
	Node createNode(int data) {
		Node newnode = new Node();
		newnode.data = data;
		newnode.next = null;
		return newnode;
	}

	// add a node at the end of the list
	Node addNodeEnd(Node head, int data) {
		// If this is the first node
		if (head == null)
			return createNode(data);
		//
		else
			head.next = addNodeEnd(head.next, data);
		return head;
	}

	// count the number of nodes in the list
	int countNodes(Node head) {
		if (head == null)
			return 0;
		return 1 + countNodes(head.next);
	}

	// swap node x with node y
	Node swapNodes(Node head, Node x, Node y) {
		if (x == y)
			return head;
		if (x == null || y == null)
			return head;

		// find previous of x
		Node prevX = head;
		if (x == head)
			prevX = null;
		else {
			while (prevX.next != x && x != null)
				prevX = prevX.next;
		}

		// find previous of y
		Node prevY = head;
		if (y == head)
			prevY = null;
		else {
			while (prevY.next != y && y != null)
				prevY = prevY.next;
		}

		// if x is not the head
		if (prevX != null)
			prevX.next = y;
		else // make y head
			head = y;

		// if y is not head
		if (prevY != null)
			prevY.next = x;
		else // make x head
			head = x;

		// swap pointers
		Node temp = x.next;
		x.next = y.next;
		y.next = temp;

		return head;
	}

	// traverse the list
	void traverse(Node temp) {
		if (temp == null)
			return;
		System.out.println(temp.data);
		traverse(temp.next);
	}

	// Selection sorting of nodes
	Node selectionSortLL(Node head) {
		Node small = null;
		Node t1 = null;
		Node t2 = null;

		int noOfTotalNodes = countNodes(head);
		int noOfNodeTraversed = 0;

		//Using counter to keep track of the nodes because the pointer is lost after swapping
		while (noOfTotalNodes > 0) {
			t1 = head;
			int x = 0;
			// to traverse the linked list
			while (x < noOfNodeTraversed && t1 != null) {
				t1 = t1.next;
				x++;
			}

			small = t1;

			t2 = t1.next;
			while (t2 != null) {
				if (t2.data < small.data)
					small = t2;

				t2 = t2.next;
			}

			// swap nodes
			head = swapNodes(head, small, t1);

			noOfTotalNodes--;
			noOfNodeTraversed++;
		}
		return head;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Random r = new Random();
		Node head = null;
		ll l = new ll();

		// appending random data into the list
		for (int i = 0; i < 15; i++)
			head = l.addNodeEnd(head, r.nextInt(100));

		System.out.println("BEFORE SORTING : ");
		l.traverse(head);

		System.out.println("Sorting...");
		head = l.selectionSortLL(head);

		System.out.println("AFTER SORTING : ");
		l.traverse(head);

	}

}
