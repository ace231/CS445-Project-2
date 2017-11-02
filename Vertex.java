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
    
    public float getX(){
        return x;
    }
    
    public float getY(){
        return y;
    }
    
    public void setX(float x){
        this.x = x;
    }
    
    public void setY(float Y) {
        this.y = y;
    }
    
    @Override
    public String toString(){
        return String.format("(%f, %f)", x, y);
    }
}
