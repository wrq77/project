package pathAlgo;

import java.util.*;


import graph.Edges;
import graph.Graph;
import graph.Stops;


public class Dijkstra {
    private static HashMap<String, Boolean> marked = new HashMap<String, Boolean>();
    private static HashMap<String, String> previous = new HashMap<String, String>();
    private static HashMap<String, Double> distance = new HashMap<String, Double>();
    private static String sourceNode;


    public static List<String> DijkstraSP(Graph G, String name) {
//    	String s;
//    	for (String key : G.getfindStopsById().keySet()) {
//    		if(name.equals(G.getfindStopsById().get(key))) {
//    			s = key;
//    			break;
//    		}       
//       }
		sourceNode = name;

		//store unvisited nodes
		HashSet<String> openedNodes = new HashSet<>();
		for (String key : G.getMyGraph().keySet()) {
			previous.put(key, "null");
			distance.put(key, Double.MAX_VALUE);
			openedNodes.add(key);
		}
		ArrayList<String> visitOrder = new ArrayList<>();
		distance.put(sourceNode, (double) 0);

		while (!openedNodes.isEmpty()) {
			double smallestDistance = Double.MAX_VALUE;
			String smallestNode = "null";
			for (String thisNode : openedNodes) {
				if (distance.get(thisNode) < smallestDistance) {
					smallestDistance = distance.get(thisNode);
					smallestNode = thisNode;
				}
			}
			openedNodes.remove(smallestNode);
			visitOrder.add(smallestNode);
			// If remained nodes are not available, it is not a connected graph,terminate the progress.
			if (smallestNode == "null") {
				break;
			}
			// Check all neighbours and update distances

			for (Edges e : G.getMyGraph().get(smallestNode)) {
				String childNode = e.getDst().getstopName();
				double alt = distance.get(smallestNode) + e.getdistance();
				if (alt < distance.get(childNode)) {
					marked.put(childNode, true);
					previous.put(childNode, smallestNode);
					distance.put(childNode, alt);
				}
			}

		}
		return visitOrder;
	}

	//get the shortest path to v
	public static ArrayList<String> getSP(String v) {
		ArrayList<String> shortestPath = new ArrayList<>();
		String thisNode = v;
		while (!thisNode.equals("null")) {
			shortestPath.add(thisNode);
			thisNode = previous.get(thisNode);
			if (thisNode == sourceNode) {
				shortestPath.add(sourceNode);
				break;
			}
		}
		Collections.reverse(shortestPath);
		return shortestPath;
	}
}