package graph;

import graph.Edges;

import java.util.*;

public class Graph {
    //站点name为key值，站点
    public HashMap<String, Stops> myStops = new HashMap<String, Stops>();
    //站点id为key值，站点name
    private HashMap<String, String> findStopsById = new HashMap<String, String>();
    //站点name为key
    public HashMap<String, List<Edges>> myGraph = new HashMap<String, List<Edges>>();

    public Graph(){

    }

    //读取每行 myStops.txt 文件添加Stops
    public void addStop(String line){
        String[] stopInfo = line.split(",");
        myStops.put(stopInfo[1], new Stops(stopInfo[0], stopInfo[1], Double.valueOf(stopInfo[2]), Double.valueOf(stopInfo[3])));
        findStopsById.put(stopInfo[0], stopInfo[1]);
        myGraph.put(stopInfo[1], new ArrayList<>());
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
  //打印点
    public void printStopnode() {
    	System.out.println("The number of stops: "+findStopsById.size());
    	for (String key : findStopsById.keySet()) {
            System.out.println(key+": "+findStopsById.get(key));          
         }
    }
    
    public void printRoutes() {
    	for (String key : myGraph.keySet()) {
          System.out.print(key+": ");

          for (Edges e : myGraph.get(key)){
              System.out.print(e.dst.stopName + ", ");
          }
          System.out.println();
       }
    }
}
