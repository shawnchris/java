package csc421;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;


public class Huffman {
	
	class Node implements Comparable<Node> {
		private final char ch;
		private final double freq;
		private final Node left, right;

		Node(char ch, double freq, Node left, Node right) {
			this.ch    = ch;
			this.freq  = freq;
			this.left  = left;
			this.right = right;
		}

		// compare, based on frequency
		public int compareTo(Node that) {
			if (this.freq > that.freq) return 1;
			else if (this.freq < that.freq) return -1;
				else return 0;
		}
	}
	
	public Node buildTree (double[] freqtab) {
		int n = freqtab.length;
		PriorityQueue<Node> mpq = new PriorityQueue<Node>();
		
		//Insert nodes into Min Priority Queue
		for (int i=0; i<n; i++) {
			Node node = new Node((char)('a'+i), freqtab[i], null, null);
			mpq.add(node);
		}
		
		//Build the tree
		for (int i=1; i<n; i++) {
			Node left = mpq.poll();
			Node right = mpq.poll();
			Node node = new Node(' ', left.freq+right.freq, left, right);
			mpq.add(node);
		}
		/*
		while (!mpq.isEmpty()) {
			Node node = mpq.poll();
			System.out.println(node.ch + " " + node.freq);
		}
		*/
		
		return mpq.poll();
	}
	
	public void printTree(Node root) {
		Queue<Node> q1 = new LinkedList<Node>();
		Queue<Node> q2 = new LinkedList<Node>();
		
		q1.add(root);
		while (!q1.isEmpty()) {
			while(!q1.isEmpty()) {
				Node node = q1.poll();
				System.out.print("(" + node.ch + ", ");
				System.out.format("%.3f", node.freq*100);
				System.out.print(") ");
				if(node.left!=null)
					q2.add(node.left);
				if(node.right!=null)
					q2.add(node.right);
			}
			System.out.println();
			Queue<Node> temp = q1;
			q1 = q2;
			q2 = temp;
		}
	}
	
	public void printHuffmanCode(Node root, String code) {
		if (root==null) return;
		
		if (root.ch!=' ')
			System.out.println(root.ch + "\t" + code);
		
		printHuffmanCode(root.left, code+"0");
		printHuffmanCode(root.right, code+"1");
	}

	public static void main(String[] args) {
		// The frequency table of assignment 5 1)
		double[] freqtab1 = {0.08167, 0.01492, 0.02782, 0.04253, 0.12702, 0.02228, 0.02015, 0.06094, 0.06966, 0.00153, 0.00772, 0.04025, 0.02406, 
				            0.06749, 0.07507, 0.01929, 0.00095, 0.05987, 0.06327, 0.09056, 0.02758, 0.00978, 0.02361, 0.00150, 0.01974, 0.00074};
		double[] freqtab2 = {0.038, 0.032, 0.045, 0.034, 0.047, 0.044, 0.040, 0.037, 0.044, 0.031, 0.038, 0.043, 0.045, 
				             0.038, 0.039, 0.030, 0.030, 0.030, 0.045, 0.045, 0.047, 0.031, 0.045, 0.031, 0.044, 0.031};
		
		Huffman bt = new Huffman();
		Node root = bt.buildTree(freqtab1);
		bt.printTree(root);
		bt.printHuffmanCode(root, "");
		
		root = bt.buildTree(freqtab2);
		bt.printTree(root);
		bt.printHuffmanCode(root, "");
		
	}

}
