/*******************************
* File: Vertex.java
* Author: Alfredo Ceballos
* Class: CS 445 - Computer Graphics
* 
* Assignment: Project 2
* Date last modified: 11/2/17
*
* Purpose: Class holds x and y values for each vertex
*******************************/
package cs445.project2;

/**
 *
 * @author Alfredo
 */

public class Vertex {
    private float x;
    private float y;
    
    public Vertex(float x, float y) {
        this.x = x;
        this.y = y;
    }
    
    
    // Method: getX
    // Purpose: Returns x value for this vertex object
    public float getX(){
        return x;
    }
    
    
    // Method: getY
    // Purpose: Returns Y value for this vertex object
    public float getY(){
        return y;
    }
    
    
    // Method: setX
    // Purpose: Sets x value for this object
    public void setX(float x){
        this.x = x;
    }
    
    
    // Method: setY
    // Purpose: Sets y value for this object
    public void setY(float y) {
        this.y = y;
    }
    
    
    // Method: toString
    // Purpose: Prints info on Vertex object
    @Override
    public String toString(){
        return String.format("(%f, %f)", x, y);
    }
}
