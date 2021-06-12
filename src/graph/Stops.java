package graph;

public class Stops {
    public String stopId;
    public String stopName;
    public double stopLat;
    public double stopLon;

    Stops(String stopId, String stopName,Double stopLat,Double stopLon){
        this.stopId = stopId;
        this.stopName = stopName;
        this.stopLat = stopLat;
        this.stopLon = stopLon;
    }
}
