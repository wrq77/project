package graph;

public class Edges {
    private String routeId;
    private Stops src;
    private Stops dst;
    private double distance;

    Edges(String routeId, Stops src, Stops dst){
        this.routeId = routeId;
        this.src = src;
        this.dst = dst;
        //If the coordinates are (0, 0), set the distance to 1
        if(src.getstopLat()==0 && dst.getstopLat()==0){
            distance = 1;
        }else{
            this.distance =  Math.sqrt((src.getstopLat() - dst.getstopLat())*(src.getstopLat() - dst.getstopLat()) + (src.getstopLon() - dst.getstopLon())*(src.getstopLon() - dst.getstopLon()));
        }
    }
    
    public Stops getSrc() {
        return src;
    }
    
    public Stops getDst() {
        return dst;
    }
    
    public String getrouteId() {
        return routeId;
    }
    
    public double getdistance() {
    	return distance;
    }

}
