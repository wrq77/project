package graph;

public class Stops {
    private String stopId;
    private String stopName;
    private double stopLat;
    private double stopLon;

    Stops(String stopId, String stopName,Double stopLat,Double stopLon){
        this.stopId = stopId;
        this.stopName = stopName;
        this.stopLat = stopLat;
        this.stopLon = stopLon;
    }
    
    public String getstopId() {
    	return stopId;
    }
    
    public String getstopName() {
    	return stopName;
    }
    
    public Double getstopLat() {
    	return stopLat;
    }
    
    public Double getstopLon() {
    	return stopLon;
    }
}
