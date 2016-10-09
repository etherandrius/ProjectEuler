package problem102;

import java.util.ArrayList;

public class Triangle {

	public ArrayList<Integer> x; // size n
	public ArrayList<Integer> y; // size n
	
	public Triangle(String[] points){		
		this.x = new ArrayList<Integer>(points.length/2);
		this.y = new ArrayList<Integer>(points.length/2);		
		int i = 0;
		while( i < points.length){
			this.x.add(Integer.parseInt(points[i++], 10));
			this.y.add(Integer.parseInt(points[i++], 10));			
		}
	}
		
	public void print(){
		for(int i = 0; i < x.size(); i++)
			System.out.print(" (" +this.x.get(i) + ", " + this.y.get(i) + ")");
		System.out.println();
	}
	
	private double triangleArea(){		
		double area = ( x.get(0)*(y.get(1)-y.get(2)) + x.get(1)*(y.get(2)-y.get(0)) + x.get(2)*(y.get(0)-y.get(1)));
		return Math.abs(area)/2;
	}
	
	private double areaToOrigin(){
		double area = 0;
		//
		int tempx = x.get(0), tempy = y.get(0);
		x.set(0, 0); y.set(0, 0);
		area += triangleArea();
		x.set(0, tempx); y.set(0, tempy);
		//
		tempx = x.get(1); tempy = y.get(1);
		x.set(1, 0); y.set(1, 0);
		area += triangleArea();
		x.set(1, tempx); y.set(1, tempy);
		//
		tempx = x.get(2); tempy = y.get(2);
		x.set(2, 0); y.set(2, 0);
		area += triangleArea();
		x.set(2, tempx); y.set(2, tempy);
		return area;
	}
	
	public boolean containsOrigin(){
		return triangleArea() == areaToOrigin();
	}
	
	
}
