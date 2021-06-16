package graph;

import graph.Edges;

import java.util.*;

public class Graph {
    //set the key to the name of the stop -> stop
    private HashMap<String, Stops> myStops = new HashMap<String, Stops>();
    //set the key to the id of the stop -> stop name
    private HashMap<String, String> findStopsById = new HashMap<String, String>();
    //set the key to the name of the stop
    private HashMap<String, List<Edges>> myGraph = new HashMap<String, List<Edges>>();

    public double minLat = Double.MAX_VALUE;
    public double maxLat = Double.MIN_VALUE;
    public double minLon = Double.MAX_VALUE;
    public double maxLon = Double.MIN_VALUE;

    public Graph(){

    }

    //read every line of myStops.txt to add Stops
    public void addStop(String line){
        String[] stopInfo = line.split(",");
        myStops.put(stopInfo[1], new Stops(stopInfo[0], stopInfo[1], Double.valueOf(stopInfo[2]), Double.valueOf(stopInfo[3])));
        findStopsById.put(stopInfo[0], stopInfo[1]);
        myGraph.put(stopInfo[1], new ArrayList<>());
        this.minLat = Math.min(this.minLat, Double.parseDouble(stopInfo[2]));
        this.maxLat = Math.max(this.maxLat, Double.parseDouble(stopInfo[2]));
        this.minLon = Math.min(this.minLon, Double.parseDouble(stopInfo[3]));
        this.maxLon = Math.max(this.maxLon, Double.parseDouble(stopInfo[3]));

    }

    public void addRoute(String route){
        String[] stopsOfRoute = route.split(",");
        for(int i = 1; i < stopsOfRoute.length-1; i++){
            Edges edge = new Edges(stopsOfRoute[0],myStops.get(findStopsById.get(stopsOfRoute[i])),myStops.get(findStopsById.get(stopsOfRoute[i+1])));
            Edges edge2 = new Edges(stopsOfRoute[0],myStops.get(findStopsById.get(stopsOfRoute[i+1])),myStops.get(findStopsById.get(stopsOfRoute[i])));
            myGraph.get(findStopsById.get(stopsOfRoute[i])).add(edge);
            myGraph.get(findStopsById.get(stopsOfRoute[i+1])).add(edge2);
        }
    }
    
    //print unweighed graph
    public void printGraph() {
//    	System.out.println("The number of stops: "+myGraph.size());
    	for (String key : myGraph.keySet()) {
          System.out.print(key+": ");
          for (Edges e : myGraph.get(key)){
              System.out.print(e.getDst().getstopName() + " ");
          }
          System.out.println();
       }
    }
    
    //print weighed graph
    public void printWGraph() {
//    	System.out.println("The number of stops: "+myGraph.size());
    	for (String key : myGraph.keySet()) {
          System.out.print(key+": ");
          for (Edges e : myGraph.get(key)){
              System.out.print("("+e.getDst().getstopName() + ", "+e.getdistance() + ") ");
          }
          System.out.println();
       }
    }

    public HashMap<String, Stops> getMyStops(){
        return this.myStops;
    }

    public HashMap<String, List<Edges>> getMyGraph() {
        return myGraph;
    }

    public HashMap<String, String> getFindStopsById() {
        return findStopsById;
    }
}
