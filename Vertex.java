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

public class Vertex {
    private float x;
    private float y;
    
    public Vertex(float x, float y) {
        this.x = x;
        this.y = y;
    }
    
    
    // Method:
    // Purpose:
    public float getX(){
        return x;
    }
    
    
    // Method:
    // Purpose:
    public float getY(){
        return y;
    }
    
    
    // Method:
    // Purpose:
    public void setX(float x){
        this.x = x;
    }
    
    
    // Method:
    // Purpose:
    public void setY(float y) {
        this.y = y;
    }
    
    
    // Method:
    // Purpose:
    @Override
    public String toString(){
        return String.format("(%f, %f)", x, y);
    }
}
