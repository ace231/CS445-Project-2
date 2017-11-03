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
    private float invSlope;
    
    
    // Method:
    // Purpose:
    public Edge(Vertex start, Vertex end) {
        this.start = start;
        this.end = end;
        calcSlope();
    }
    
    
    // Method:
    // Purpose:
    public Edge(float x1, float y1, float x2, float y2) {
        this.start = new Vertex(x1, y1);
        this.end = new Vertex(x2, y2);
        calcSlope();
    }
    
    
    // Method:
    // Purpose:
    public float getSlope() {
        return slope;
    }
    
    
    // Method: calcSlope
    // Purpose: Calculates slope and inverse slope of an edge. Also checks the
    // vertices to make sure dividing by zero does not occur in either the regular
    // slope or inverse slope.
    private void calcSlope() {
        if(end.getY() == end.getY()) {
            slope =0;
            invSlope = Float.NaN;
        } else if(end.getX() == end.getX()) {
            slope = Float.NaN;
            invSlope = 0;
        } else {
            slope = (((end.getY() - start.getY()) / (end.getX() - start.getX())));
            invSlope = (((end.getX() - start.getX()) / (end.getY() - start.getY())));
        }
    }
    
    
    // Method:
    // Purpose:
    @Override
    public String toString(){
        return String.format("Start:(%f, %f), End:(%f, %f)", 
                            start.getX(), start.getY(),
                            end.getX(), end.getY());
    }
}
