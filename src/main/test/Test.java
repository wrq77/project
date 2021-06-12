package main.test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import graph.*;
import pathAlgo.*;

public class Test {

	public static void main(String[] args) {
		
		Graph g = new Graph();
		
//		readStop(g);
//		readRoute(g);

//		g.addStop("1,a,0,0");
//		g.addStop("2,b,0,0");
//		g.addStop("3,c,0,0");
//		g.addStop("4,d,0,0");
//		g.addStop("5,e,0,0");
//		g.addStop("6,f,0,0");
//		g.addStop("7,g,0,0");
//		g.addStop("8,h,0,0");
//
//		g.addRoute("r1,1,2,3,4,1");
//		g.addRoute("r2,2,4");
//		g.addRoute("r3,1,3");
//		g.addRoute("r4,4,5,6,7,8,5");
//		g.addRoute("r5,6,8");

		System.out.println(BFS.cc(g));
		Clustering cluster = new Clustering(g);
		cluster.deleteEdge();

//		Dijkstra d = new Dijkstra();
//		d.DijkstraSP(g, "Laurentina");
//		System.out.println(d.getSP("Colosseo"));
	}
	
	public static void readStop(Graph g ) {
		
		BufferedReader br = null;
		// read mystops.txt 
				try {
				    br = new BufferedReader(new FileReader("mystops.txt"));			
					String line = null;
					while ((line = br.readLine()) != null) {
						g.addStop(line);
					}
//					g.printStopnode();	
				} catch (IOException e) {
					e.printStackTrace();
				}finally {
					//close BufferedReader
					if (br != null) {
						try {
							br.close();
						}catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
		
	}
	
	public static void readRoute(Graph g ) {
			
			BufferedReader br = null;
			// read mystops.txt 
			try {
			    br = new BufferedReader(new FileReader("myroutes.txt"));			
				String line = null;
				while ((line = br.readLine()) != null) {
					g.addRoute(line);
				}
				//g.printGraph();
			} catch (IOException e) {
				e.printStackTrace();
			}finally {
				//close BufferedReader
				if (br != null) {
					try {
						br.close();
					}catch (IOException e) {
						e.printStackTrace();
					}
				}
		  }
			
	}

}

