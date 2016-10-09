package problem107v3;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

public class Problem107v3 {
	
	public static int VER = 40;
	
	public static void print(LinkedList<Node> L){
		System.out.print("{ ");
		for(int i = 0; i < L.size() - 1; i++)
			System.out.print(   L.get(i).name + " <-" + L.get(i).d.get( L.get(i+1) ) + "-> ");
		System.out.println(  L.getLast().name + " <-" + L.getLast().d.get( L.getFirst() )  + "-> }" );
	}
	
	public static Node[] input() throws IOException{
		Node[] Array = new Node[VER];
		for(int i = 0; i < VER; i++) Array[i] = new Node(i);
		
		Scanner scan = new Scanner(new File("p107_network.txt"));
		for(int row = 0; row < VER; row++){
			String[] line = scan.nextLine().split(",");
			for(int col = 0; col < VER; col++)
				if( line[col].charAt(0) != '-' ) 
					Array[row].d.put(Array[col], Integer.parseInt(line[col]));
		}
		
		scan.close();
		return Array;
	}
	
	public static void Eliminate(LinkedList<Node> list, int start){ // start inclusive, end - exclusive
		// finds the heaviest edge removes it and removes every element after the the edge
		if( start < 0 || start > list.size() ) return;
		int max = list.getLast().d.get( list.get(start) );
		int index = list.size();
		
		for(int i = start; i < list.size()-1; i++)
			if( list.get(i).d.get(list.get( i+1 )) > max ){
				max = list.get(i).d.get(list.get( i+1 ));
				index = i;
			}
		
		if( index == list.size() )
			Node.remove(list.getLast(), list.get(start));
		else
			Node.remove(list.get(index), list.get(index+1));
		for(int i = list.size()-1; i > index; i--)
			list.removeLast();
	}
	
	public static void DepthFirstSearchAndEliminate(LinkedList<Node> L, int size) {
		if (L.size() > VER)
			return;

		// copying TreeMap in order to not to mess with loop itterator;
		Node[] keys = L.getLast().d.keySet().toArray(new Node[L.getLast().d.size()]);
		for (Node it : keys)
			if (L.getLast().d.containsKey(it) == true)
				if (L.size() < 2 || it.name != L.get(L.size() - 2).name)
					if (it.inLinkedList(L) == true) {
						Eliminate(L, it.getIndex(L));
						if (L.size() < size)
							return;
					} else {
						L.add(it);
						DepthFirstSearchAndEliminate(L, size + 1);
						if (L.size() < size + 1)
							return;
						L.removeLast();
					}
	}
	
	public static void produceMinimalSpanningTree(Node[] G){
		for(int col = 0; col < VER; col++){
			LinkedList<Node> L = new LinkedList<Node>();
			L.add( new Node( G[col] ) );
			DepthFirstSearchAndEliminate(L, 1);
		}
	}
	
	public static void main(String[] args) throws Exception{		
		// Node contains name of the veritce and connection to other veitces
		Node[] G = input();
		
		int SUM1 = Node.graphWeight(G);
		
		produceMinimalSpanningTree(G);
		
		int SUM2 = Node.graphWeight(G);
		
		System.out.println(SUM1 + " - " + SUM2 + " = " + (SUM1 - SUM2));
		
	}
	

}
