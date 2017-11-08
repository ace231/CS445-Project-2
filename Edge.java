/*******************************
* File: Edge.java
* Author: Alfredo Ceballos
* Class: CS 445 - Computer Graphics
* 
* Assignment: Project 2
* Date last modified: 11/6/17
*
* Purpose: This class holds information on the edges created by each pair
* of vertices
*******************************/
package cs445.project2;

/**
 *
 * @author Alfredo
 */
public class Edge implements Comparable<Edge>{
    private Vertex start;
    private Vertex end;
    private float slope;
    private float invSlope;
    private float yMin;
    private float yMax;
    private float xVal;
    
    //Method: getInvSlope
    //Puporse: return inverse slope of edge
    public float getInvSlope() {
        return invSlope;
    }

    //Method: getyMin
    //Puporse: return y Min of edge
    public float getyMin() {
        return yMin;
    }
    
    //Method: getyMax
    //Puporse: return y Max of edge
    public float getyMax() {
        return yMax;
    }

    //Method: getxVal
    //Puporse: return X val of edge
    public float getxVal() {
        return xVal;
    }
    
    // Method: getSlope
    // Purpose: Return slope of edge
    public float getSlope() {
        return slope;
    }
    
    
    // Method: Edge constructor
    // Purpose: Creates an Edge object by being provided a start and end vertex
    // the slope and inverse slopes are then calculate. Assigns y min, y max, and
    // x val values for the scan line algorithm.
    public Edge(Vertex start, Vertex end) {
        this.start = start;
        this.end = end;
        calcSlope();
        if(start.getY() < end.getY()) {
            yMin = start.getY();
            xVal = start.getX();
            yMax = end.getY();
        } else if(start.getY() == end.getY()){
            if(start.getX() < end.getX()) {
                yMin = start.getY();
                xVal = start.getX();
                yMax = end.getY();
            } else {
                yMin = start.getY();
                xVal = end.getX();
                yMax = end.getY();
            }
        } else {
            yMin = end.getY();
            xVal = end.getX();
            yMax = start.getY();
        }
    }
    
    
    // Method: Edge constructor
    // Purpose: can create an Edge object by being provided with 2 sets of points
    // which are used to create vertex objects. The slope and inverse slope are
    // then calculated
    public Edge(float x1, float y1, float x2, float y2) {
        this.start = new Vertex(x1, y1);
        this.end = new Vertex(x2, y2);
        calcSlope();
    }
    
    
    @Override
	public int compareTo(Edge e) {
		if (this.yMin < e.yMin) {
			return -1;
		} else if (this.yMin == e.yMin) {
			if(this.xVal < e.xVal) {
				return -1;
			} else if(this.xVal > e.xVal) {
				return 1;
			} else{
				return -1;
			}
		} else { // if this.yMin > e.yMin
			return -1;
		}
		
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
    
    
    // Method: toString
    // Purpose: Used to print out information during program's execution
    @Override
    public String toString(){
        return String.format("Start:(%f, %f), End:(%f, %f)", 
                            start.getX(), start.getY(),
                            end.getX(), end.getY());
    }
}
