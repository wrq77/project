package main.test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import graph.*;
import pathAlgo.*;
import utils.*;

public class Test {

	public static void main(String[] args) {
		
		Graph g = new Graph();
		
		readStop(g);
		readRoute(g);

//		System.out.println(g.minLat+","+g.maxLat);
//		g.addStop("1,a,0,0");
//		g.addStop("2,b,1,0");
//		g.addStop("3,c,0,1");
//		g.addStop("4,d,1,1");
//		g.addStop("5,e,2,5");
//		g.addStop("6,f,3,3");
//		g.addStop("7,g,4,3");
//		g.addStop("8,h,5,5");
//		g.addStop("9,i,6,6");
//
//		g.addRoute("r1,1,2,3,4,1");
//		g.addRoute("r2,2,4");
//		g.addRoute("r3,1,3");
//		g.addRoute("r4,4,5,6,7,8,5");
//		g.addRoute("r5,6,8");
//		g.addRoute("r1,1,3,4");
//		g.addRoute("r2,2,3");

//		Clustering cluster = new Clustering(g);
//		cluster.reachMaxClusters(13);
//		cluster.deleteEdge();

//		BFS b = new BFS();
//		b.bfs(g, "a");
//		System.out.println(b.getSP("i"));

		Dijkstra d = new Dijkstra();
		d.DijkstraSP(g, "Colombo/Civilta' Del Lavoro");
//		System.out.println(d.getSP("Tuscolana/Appio Claudio"));

		DrawGraph dw = new DrawGraph(g, d.getSP("Aspertini/Conti F."));

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
				//g.printWGraph();
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

