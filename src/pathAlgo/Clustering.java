package pathAlgo;

import graph.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Clustering{
    private Graph g;

    //EdgeName : ("Src,dst"), double value of betweenness
    HashMap<String, Double> betweenness = new HashMap<>();

    double shortestDistance = Double.MAX_VALUE;
    ArrayList<String> shortestPath = new ArrayList<>();

    public Clustering(Graph g){
        this.g = g;
    }

    // make the graph reach the requested maximum number of clusters.
    public void reachMaxClusters(int n){
        if(CountClusters() >= n){
            System.out.println("The graph already reached the requested maximum number of clusters");
            g.printGraph();
            return;
        }
        while(CountClusters()<n){
            deleteEdge();
        }
        System.out.println("now the number of cluster is " + n);
        g.printGraph();
    }


    private int CountClusters(){
        return BFS.cc(g);
    }


    public void deleteEdge(){
        //Call the function to Calculate betweenness of each edge
        calAllBetweenness();
        //Traverse to get the biggest betweenness
        String maxEdge = "";
        double maxbetweenness = -1;
        for(String name : betweenness.keySet()){
            if(betweenness.get(name) > maxbetweenness){
                maxbetweenness = betweenness.get(name);
                maxEdge = name;
            }
        }
        System.out.println("Deleted the edge " + maxEdge);
        //delete all this edges in the graph.myGraph
        String[] maxStops = maxEdge.split(",");
        for(int i=0; i<g.getMyGraph().get(maxStops[0]).size();i++){
            if(g.getMyGraph().get(maxStops[0]).get(i).getDst().getstopName().equals(maxStops[1])){
                g.getMyGraph().get(maxStops[0]).remove(i);
            }
        }
        for(int i=0; i<g.getMyGraph().get(maxStops[1]).size();i++){
            if(g.getMyGraph().get(maxStops[1]).get(i).getDst().getstopName().equals(maxStops[0])){
                g.getMyGraph().get(maxStops[1]).remove(i);
            }
        }

    }

    //Calculate betweenness of each edge
    private void calAllBetweenness(){

        //Initialize the HashMap corresponding to each edge
        betweenness = new HashMap<>();
        for(List<Edges> eList : g.getMyGraph().values()){
            for(Edges e : eList){
                if(!betweenness.containsKey(e.getDst().getstopName()+","+e.getSrc().getstopName())){
                    betweenness.put(e.getSrc().getstopName()+","+e.getDst().getstopName(), 0.0);
                }
            }
        }
        for(Stops from : g.getMyStops().values()){
            for(Stops to : g.getMyStops().values()){
                if(!from.getstopName().equals(to.getstopName())){
//                    System.out.println(from.getstopName()+"  to  "+to.getstopName());
                    getAllSP(from.getstopName(),to.getstopName());
                    //if the shortest path exist
                    if(shortestDistance != Double.MAX_VALUE){
                        double sum = 1.0/shortestPath.size();
                        for (String path :shortestPath){
                            String[] nodes = path.split(",");
                            for(int i=0;i< nodes.length-1;i++){
                                if(betweenness.containsKey(nodes[i]+","+nodes[i+1])){
                                    betweenness.put(nodes[i]+","+nodes[i+1], betweenness.get(nodes[i]+","+nodes[i+1])+sum);
                                }else{
                                    betweenness.put(nodes[i+1]+","+nodes[i], betweenness.get(nodes[i+1]+","+nodes[i])+sum);
                                }
                            }
                        }
                    }

                }
            }
        }
    }

    //Get all the shortest paths and path lengths from src to dst
    private void getAllSP(String src, String dst){
        shortestDistance = Double.MAX_VALUE;
        shortestPath = new ArrayList<>();
        Stops srcStops = g.getMyStops().get(src);
        Stops dstStops = g.getMyStops().get(dst);
        Nodes n = new Nodes();
        n.name = "begin";
        n.front = null;
        n.distance = 0;
        getAllPath(1, n, srcStops, dstStops);
    }


    //Get all the shortest paths and path lengths from src to dst
    private void getAllPath(double distanceBefore,Nodes lastNode, Stops src, Stops dst){

        Nodes n = lastNode;
        while (n.distance != 0){
            n = n.front;
            //If there is already this src in the previous linkedList point, exit the recursion
            if(n.name.equals(src.getstopName())){
                return;
            }
        }
        Nodes srcNode = new Nodes();
        srcNode.name = src.getstopName();
        if(!lastNode.name.equals("begin")){
            for(Edges e : g.getMyGraph().get(lastNode.name)){
                if (e.getDst().getstopName().equals(src.getstopName())){
                    srcNode.distance = distanceBefore + e.getdistance();
                }
            }
        }else {
            srcNode.distance = 0;
        }

        lastNode.next.add(srcNode);
        srcNode.front = lastNode;
        //If it is the dst node, exit the recursion
        if(src.getstopName().equals(dst.getstopName())){
            //If it is the shortest path
            if(srcNode.distance <= shortestDistance){
                String onePath = "";
                Nodes m = srcNode;
                //A Reversed route
                while (!m.name.equals("begin")){
                    onePath = onePath + m.name +",";
                    m = m.front;
                }
                if(srcNode.distance < shortestDistance){
                    shortestPath = new ArrayList<>();
                    shortestDistance = srcNode.distance;
                }
                shortestPath.add(onePath);
            }
            return;
        }
        for (Edges e : g.getMyGraph().get(src.getstopName())){
            getAllPath(srcNode.distance, srcNode, e.getDst(), dst);
        }

    }

    //Linked list
    private class Nodes {
        ArrayList<Nodes> next = new ArrayList<>();
        Nodes front;
        String name;
        double distance;
        Nodes(){

        }

    }

}