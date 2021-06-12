public class Edges {
    public String routeId;
    public Stops src;
    public Stops dst;
    public double distance;

    Edges(String routeId, Stops src, Stops dst){
        this.routeId = routeId;
        this.src = src;
        this.dst = dst;
        this.distance =  Math.sqrt((src.stopLat - dst.stopLat)*(src.stopLat - dst.stopLat) + (src.stopLon - dst.stopLon)*(src.stopLon - dst.stopLon));
    }

}
