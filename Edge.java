/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs445.project2;

/**
 *
 * @author Alfredo
 */
public class Edge {
    private Vertex start;
    private Vertex end;
    private float slope;
    
    public Edge(Vertex start, Vertex end) {
        this.start = start;
        this.end = end;
        calcSlope();
    }
    
    public Edge(float x1, float y1, float x2, float y2) {
        this.start = new Vertex(x1, y1);
        this.end = new Vertex(x2, y2);
        calcSlope();
    }
    
    public float getSlope() {
        return slope;
    }
    
    private void calcSlope() {
        slope = (end.getY() - start.getY()) / (end.getX() - start.getX());
    }
    
}
