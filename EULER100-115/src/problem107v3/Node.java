package problem107v3;

import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;

public class Node implements Comparable<Node>{
	
	public int name; // name of node
	public TreeMap< Node, Integer > d;
	
	Node(Node node){
		this.name = node.name;
		this.d    = new TreeMap< Node, Integer>(node.d);
	}
	
	Node(int name){
		this.name = name;
		d         = new TreeMap< Node, Integer >();
	}
	
	public static int graphWeight(Node[] G){
		int SUM = 0;
		for(Node j : G)
			for(Map.Entry<Node, Integer> it : j.d.entrySet())
				SUM += it.getValue();
		return SUM/2;
	}
	
	public int getIndex(LinkedList<Node> list){
		for(int i = 0; i < list.size(); i++)
			if( list.get(i).name == this.name ) return i;
		return -1;
	}
	
	public boolean inLinkedList( LinkedList<Node> list ){
		for(Node it : list) if( it.name == this.name ) return true;
		return false;
	}
		
	public void remove(Node A){ this.d.remove(A); }
	
	public static void remove( Node A, Node B){
		A.remove(B);
		B.remove(A); }
	
	public void print(){
		System.out.println(this.d);
	}
	
	@Override
	public String toString(){ return Integer.toString(name); }
	
	@Override
	public int compareTo(Node compareNode){
		return this.name - compareNode.name;
	}
	
}
