package The4th;

import edu.princeton.cs.algs4.*;
import java.util.*;
public class Huffman 
{
    private static final int R = 256;
    private Huffman() {}
    private static class Node implements Comparable<Node> 
    {
        private final char ch;
        private final int freq;
        private final Node left, right;
        Node(char ch, int freq, Node left, Node right)
        {
            this.ch    = ch;
            this.freq  = freq;
            this.left  = left;
            this.right = right;
        }
    
        private boolean isLeaf() 
        {
            assert ((left == null) && (right == null)) || ((left != null) && (right != null));
            return (left == null) && (right == null);
        }

        public int compareTo(Node that) 
        {
            return this.freq - that.freq;
        }
    }

    public static void compress() 
    {
    	System.out.println("please input the string you want to compress:");
    	Scanner in=new Scanner(System.in);
        String s = in.next();
        //in.close();
        System.out.println(s);
        char[] input = s.toCharArray();

        int[] freq = new int[R];
        for (int i = 0; i < input.length; i++)
        {
            freq[input[i]]++;
        }
        Node root = buildTree(freq);
        String[] st = new String[R]; 
        for(int i=0;i<R;i++)
        {
        	st[i]=new String();
        }
        buildCode(st, root,new String());
        boolean[] vis=new boolean[R];
       	for(int i=0;i<input.length;i++)
       	{
       		if(!vis[input[i]])
       		{
       			System.out.println(input[i]+":"+st[input[i]]);
       			vis[input[i]]=true;
       		}
    		
    	}
    }

    private static Node buildTree(int[] freq)
    { 
        MinPQ<Node> pq = new MinPQ<Node>();
        for (char i = 0; i < R; i++)
        {
            if (freq[i] > 0)
                pq.insert(new Node(i, freq[i], null, null));
        }
	    if (pq.size() == 1) 
	    {
	    	if (freq['\0'] == 0) pq.insert(new Node('\0', 0, null, null));
	        else                 pq.insert(new Node('\1', 0, null, null));
	    }
        while (pq.size() > 1)
        {
            Node left  = pq.delMin();
            Node right = pq.delMin();
            Node parent = new Node('\0', left.freq + right.freq, left, right);
            pq.insert(parent);
        }
        return pq.delMin();
    }
  
    private static void buildCode(String[] st, Node x, String s) 
    {
        if (!x.isLeaf())
        {
            buildCode(st, x.left,  s + '0');
            buildCode(st, x.right, s + '1');
        }
        else 
        {
            st[x.ch] = s;
        }
    }

    public static void main(String[] args) 
    {
       compress();  
    }

}

