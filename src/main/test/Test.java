package main.test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import graph.Graph;
import pathAlgo.BFS;

public class Test {

	public static void main(String[] args) {
		
		Graph g = new Graph();
		
		readStop(g);
		readRoute(g);

		System.out.println(BFS.bfs(g, "Promontori/Capo Spartivento"));

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

